package FootballManager.CalendarMenuOptions;


import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;
import FootballManager.time.DayMatch;

import java.util.Calendar;

public class PlayingCalendar implements CalendarMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        int tour = 0;
        while(true){
            System.out.print(Corrector.getS(40));
            Corrector.wordUpperCase("playing calendar " + rfpl.myTeam.name + "\n\n");
            System.out.println((tour + 1) + " tour:\n\n");
                for(DayMatch dayMatch : rfpl.shedule.get(tour)){
                    System.out.println(isUserTeam(dayMatch.home.name, rfpl) + " - " +
                            isUserTeam(dayMatch.away.name, rfpl) + " at " +
                            dayMatch.stadium.getTitle() + " in " + dayMatch.date.get(Calendar.DAY_OF_MONTH) +
                            "." + nameMonth(dayMatch.date.get(Calendar.MONTH)) + "." +
                            dayMatch.date.get(Calendar.YEAR) + "\n\n");
                }

            System.out.println("\"2\" - To look a new game day\n" +
                    "\"1\" - To look a previous game day\n\n" +
                    "\"0\" - To qiut a previous menu: ");
            int choise = Corrector.inputIntMethod(0, 2);
            if(choise == 0) break;
            else if(choise == 2){
                tour++;
                if(tour == rfpl.shedule.size())
                    tour = 0;
            }
            else{
                tour--;
                if(tour == -1)
                    tour = rfpl.shedule.size() - 1;
            }
        }


    }

    private String nameMonth(int i) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return months[i];
    }

    private String isUserTeam(String team, Tournament rfpl){
        if(team.equals(rfpl.myTeam.name))
            return team.toUpperCase();
        return team;
    }
}
