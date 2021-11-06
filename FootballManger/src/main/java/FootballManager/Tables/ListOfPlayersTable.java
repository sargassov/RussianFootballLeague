package FootballManager.Tables;


import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ListOfPlayersTable extends Table implements Data{


    private Tournament rfpl;
    private final String listOfPlayersTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\listOfPlayersTable.txt";
    private final static String LIST_OF_PLAYERS_TABLES_FILE_NOT_FOUND = "LIST OF PLAYERS TABLES FILE NOT FOUND";
    private List<String> listOfPlayersTableList;

    public ListOfPlayersTable(){
        Path path = Paths.get(listOfPlayersTablePath);
        if(Files.exists(path)){
            try {
                listOfPlayersTableList = Files.readAllLines(Paths.get(listOfPlayersTablePath), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println(LIST_OF_PLAYERS_TABLES_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }


    @Override
    public void toPrint(Tournament rfpl) {

        this.rfpl = rfpl;

        for(int x = 0; x < listOfPlayersTableList.size(); x++){

            String[] mass = listOfPlayersTableList.get(x).split("/");
            if(x == 1){
                mass[1] = Corrector.wordToCenter("P L A Y E R S  " +
                        " O F  " + rfpl.myTeam.nameOfTeamInRegister(), mass[1].length());
            }
            else if(x == 5){

                for(int count = 0; count < rfpl.myTeam.playerList.size(); count++){
                    Player pl = rfpl.myTeam.playerList.get(count);
                    Object[] compareObj = {count + 1, pl.name, pl.club, pl.number, pl.natio,
                            Corrector.posInString(pl.position), pl.gkAble, pl.defAble, pl.midAble,
                            pl.forwAble, pl.captainAble, pl.isInjury, pl.trainingAble, pl.yearBirth,
                            pl.strategyPlace, pl.power, pl.tire, pl.timeBeforeTreat, pl.price};


                    for(int y = 0; y < 19; y++){
                        if(y == 1 || y == 2 || y == 4 || y == 5){
                            mass[y + 1] = Corrector.wordToCenter((String) compareObj[y], mass[y + 1].length());
                        }
                        else if(y == 11){
                            booleanIntoTable(compareObj[y], mass, y);
                        }
                        else if(y == 14){
                            mass[y + 1] = pl.strategyPlaceInPosition();
                        }
                        else if(y == 18){
                            mass[y + 1] = Corrector.wordToCenter("" + compareObj[y], mass[y + 1].length());
                        }
                        else  {
                            mass[y + 1] = Corrector.wordToCenter("" + (int)compareObj[y], mass[y + 1].length());
                        }
                    }
                    constructAndWrite(mass, x);
                }
            }
            if(x != 5)  constructAndWrite(mass, x);
        }
        System.out.println("COMMANDS:\nMenu - \"1\"\nQuit to back menu - \"0\": ");
    }

    private void constructAndWrite(String[] mass, int x) {
        listOfPlayersTableList.set(x, "");
        for(String s : mass){
            listOfPlayersTableList.set(x, listOfPlayersTableList.get(x) + (s + "|"));
        }

        System.out.println(listOfPlayersTableList.get(x));

    }

    private void booleanIntoTable(Object obj, String[] mass, int y) {
        if((boolean) obj){
            mass[y + 1] = Corrector.wordToCenter("Yes", mass[y + 1].length());
        }
        else {
            mass[y + 1] = Corrector.wordToCenter("No", mass[y + 1].length());
        }
    }
}
