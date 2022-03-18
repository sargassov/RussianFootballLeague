package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeagueTable extends Table implements Data{

    private static Tournament rfpl;
    private final String leagueTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\leagueTable.txt";
    private final static String LEAGUE_FILE_NOT_FOUND = "LEAGUE FILE NOT FOUND";
    private List<String> leagueTableList;

    public LeagueTable(){

        Path path = Paths.get(leagueTablePath);
        if(Files.exists(path)){
            try {
                leagueTableList = Files.readAllLines(Paths.get(leagueTablePath), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println(LEAGUE_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {

        LeagueTable.rfpl = rfpl;
        sortTeams();

        for (int i = 0, x = 0; i < leagueTableList.size(); i++) {

            if(i > 1 && x < rfpl.teams.length){

                int[] intMass = {rfpl.teams[x].games, rfpl.teams[x].wins, rfpl.teams[x].draws,
                        rfpl.teams[x].defeats, rfpl.teams[x].goalScored, rfpl.teams[x].goalMissed,
                        rfpl.teams[x].getPoints()};

                String[] mass = leagueTableList.get(i).split("/");

                mass[1] = rfpl.teams[x].name + Corrector.getS(mass[1].length() -
                        rfpl.teams[x].name.length());
                for(int y = 2; y < mass.length; y++){
                    mass[y] = Corrector.wordToCenter("" + intMass[y - 2], mass[y].length());
                }

                leagueTableList.set(i, massInLine(mass));
                x++;
            }
            System.out.println(leagueTableList.get(i));
        }
    }

    private void sortTeams() {

//        rfpl.teams[12].wins = 4;
//        rfpl.teams[14].wins = 3;
//        rfpl.teams[6].draws = 9;
//        rfpl.teams[1].wins = 1;
//        rfpl.teams[1].draws = 6;

        for(int x = 0; x < rfpl.teams.length; x++){
            for (int y = 0; y < rfpl.teams.length - x - 1; y++){
                if(rfpl.teams[y].getPoints() < rfpl.teams[y + 1].getPoints()){
                    change(rfpl.teams, y);
                }
            }
        }

        for(int y = 0; y < rfpl.teams.length; y++){
            for(int x = 0; x < rfpl.teams.length - 1; x++){
                if((rfpl.teams[x].getPoints() == rfpl.teams[x + 1].getPoints()) &&
                        rfpl.teams[x].wins < rfpl.teams[x + 1].wins){
                    change(rfpl.teams, x);
                }
            }
        }



    }

    private void change(Team[]teams, int y) {
        Team t = teams[y];
        teams[y] = teams[y + 1];
        teams[y + 1] = t;
    }
}
