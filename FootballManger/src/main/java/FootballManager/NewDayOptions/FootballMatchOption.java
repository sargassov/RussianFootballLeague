package FootballManager.NewDayOptions;

import FootballManager.Tables.MatchPreviewTable;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;
import FootballManager.time.DayMatch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class FootballMatchOption{

    private Tournament rfpl;
    private DayMatch match;
    private Scanner scanner = new Scanner(System.in);
    public static boolean continueFlag = true;


    public FootballMatchOption(Tournament rfpl) {
        this.rfpl = rfpl;
    }

    public void matchPreview(){
        new MatchPreviewTable(seekOpponentTeam(), match).toPrint(rfpl);

        int select = scanner.nextInt();
        if(select == 0){
            continueFlag = false;
            return;
        }

        ArrayList<DayMatch> matchTour = new ArrayList<>();
        for(ArrayList<DayMatch> tour : rfpl.shedule){
            if(!tour.get(0).itWas){
                matchTour = tour;
                break;
            }
        }

        new MatchSimulator().simulate(matchTour);

        for(DayMatch match: matchTour){
            System.out.println(match);
        }
        continueFlag = true;
    }

    private Team seekOpponentTeam() {
        Team team;
        for(int x = 0; x < rfpl.shedule.size(); x++){
            Calendar currentDay = rfpl.shedule.get(x).get(0).date;
            if(currentDay.get(Calendar.DAY_OF_MONTH) == rfpl.currentDay.date.get(Calendar.DAY_OF_MONTH) &&
                    currentDay.get(Calendar.MONTH) == rfpl.currentDay.date.get(Calendar.MONTH)) {
                for (int y = 0; y < rfpl.shedule.get(x).size(); y++){
                    if(rfpl.shedule.get(x).get(y).home.equals(rfpl.myTeam)){
                        match = rfpl.shedule.get(x).get(y);
                        return rfpl.shedule.get(x).get(y).away;
                    }
                    else if(rfpl.shedule.get(x).get(y).away.equals(rfpl.myTeam)){
                        match = rfpl.shedule.get(x).get(y);
                        return rfpl.shedule.get(x).get(y).home;
                    }
                }
            }
        }
        return null;
    }
}
