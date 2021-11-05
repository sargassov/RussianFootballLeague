package FootballManager.CalendarMenuOptions;


import FootballManager.Tables.Table;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;
import FootballManager.time.DayMatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

public class PlayingCalendar extends Table implements CalendarMenuOptionsInterface {

    private String playingCalendarPath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\playingCalendarTable.txt";
    //private static final String PLAYING_CALENDAR_FILE_NOT_FOUND = "PLAYING CALENDAR FILE NOT FOUND";
    private List<String> playingCalendarList;
    private Path path;



    public PlayingCalendar(){
        path = Paths.get(playingCalendarPath);

    }

    @Override
    public void getOption(Tournament rfpl) {

        int tour = 0;

        while(true){
            System.out.print(Corrector.getS(40));
            Corrector.wordUpperCase("playing calendar " + rfpl.myTeam.name + "\n\n");
            System.out.print(Corrector.getS(60));
            Corrector.wordUpperCase((tour + 1) + " tour:");
            System.out.println();

            try {
                playingCalendarList = Files.readAllLines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int x = 0, y = 0; x < playingCalendarList.size(); x++){

                //System.out.println("tour = " + tour);
                String[] mass = playingCalendarList.get(x).split("/");
                if(mass.length > 1){
                    DayMatch dayMatch = rfpl.shedule.get(tour).get(y);

                    mass[1] = Corrector.wordToCenter("" + (tour + 1), mass[1].length());
                    mass[2] = Corrector.wordToCenter(dayMatch.home.name + " - " + dayMatch.away.name, mass[2].length());
                    mass[3] = Corrector.wordToCenter(dayMatch.stadium.getTitle(), mass[3].length());
                    if(!dayMatch.itWas)
                        mass[4] = Corrector.wordToCenter(dayMatch.date.get(Calendar.DAY_OF_MONTH) + "." +
                            nameMonth(dayMatch.date.get(Calendar.MONTH)) + "." +
                            dayMatch.date.get(Calendar.YEAR), mass[4].length());
                    else
                        mass[4] = Corrector.wordToCenter("" + dayMatch.homeScore + " : " + dayMatch.awayScore, mass[4].length());
                    y++;
                }

                if(mass.length > 1)
                    playingCalendarList.set(x, massInLine(mass));

                System.out.println(playingCalendarList.get(x));

            }

//                for(DayMatch dayMatch : rfpl.shedule.get(tour)){
//                    System.out.print(isUserTeam(dayMatch.home.name, rfpl) + " - " +
//                            isUserTeam(dayMatch.away.name, rfpl) + " at " +
//                            dayMatch.stadium.getTitle() + " in " + dayMatch.date.get(Calendar.DAY_OF_MONTH) +
//                            "." + nameMonth(dayMatch.date.get(Calendar.MONTH)) + "." +
//                            dayMatch.date.get(Calendar.YEAR));
//                    if(dayMatch.itWas)
//                        System.out.println(" " +
//                            dayMatch.homeScore + ":" + dayMatch.awayScore + "\n\n");
//                    else
//                        System.out.println("\n");
//                }

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

        //////////////////////////////
//        int y = 0;
//        for(DayMatch d : rfpl.shedule.get(0)){
//            d.itWas = true;
//            d.homeScore = y;
//            d.awayScore = y;
//            y++;
//        }


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
