package FootballManager.TeamMenuOptions;


import FootballManager.Tables.ListOfPlayersTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

import java.util.ArrayList;

public class ListPlayerOption implements TeamMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        new ListOfPlayersTable().toPrint(rfpl);
        int choise = Corrector.inputIntMethod(0, 1);
        if(choise == 0) return;
        else{
            System.out.println("\n\nM E N U:\n\n==> 0. Quit\n\nSORTING:\n\n==> 1. Sort by name\n==> 2. Sort by nationality\n==> " +
                    "3. Sort by number\n==> 4. Sort by player position\n==> 5. Sort by goalkeeper ability\n==> 6." +
                    " Sort by defender ability\n==> 7. Sort by midfielder ability\n==> 8. Sort by forward ability\n==> " +
                    " 9. Sort by captain ability\n==> 10. Sort by injuries\n==> 11. Sort by time training abilities\n==> 12." +
                    " Sort by year of birth\n==> 13. Sort by position in the team\n==> 14. Sort by power\n==> 15. Sort by " +
                    "tire\n==> 16. Sort by time before treatment\n==> 17. Sort by price\n==> 18. Sort by club\n\nPLAYER:" +
                    "\n\n==> 19. Player menu: ");
        }

        final int finalInt = Corrector.inputIntMethod(0, 19);
        if(finalInt == 0) return;
        else if(finalInt == 1 || finalInt == 2 || finalInt == 4 || finalInt == 18) ListPlayerOptionSorts.sort(rfpl, finalInt);
        else if(finalInt == 3 || finalInt == 5 || finalInt == 6 || finalInt == 7 || finalInt == 8 ||
                finalInt == 9 || finalInt == 11 || finalInt == 12 || finalInt == 14 || finalInt == 15 || finalInt == 16)
            ListPlayerOptionSorts.sort(rfpl, finalInt, 0);
            //ListPlayerOptionSorts.sort(rfpl, choise, 0);
        else if(finalInt == 10) ListPlayerOptionSorts.sort(rfpl, true);
        else if(finalInt == 13) ListPlayerOptionSorts.sort(rfpl);
        else if(finalInt == 17)  ListPlayerOptionSorts.sort(rfpl, 0.0);
        else playerViewMenu(rfpl, rfpl.myTeam.playerList);

        getOption(rfpl);

    }


    private void playerViewMenu(Tournament rfpl, ArrayList<Player> temp) {
        System.out.print("\n\n" + Corrector.getS(24));
        Corrector.wordUpperCase("\t\t\tplayer view menu");
        System.out.println("\n\n" + Corrector.getS(38) + "Counter \tName\n\n");
        int x = 0;
        for(Player player : rfpl.myTeam.playerList){
            //Corrector.getS(40);
            System.out.println(Corrector.getS(40) + (x + 1) + "\t\t" + player.name);
            x++;
        }
        System.out.println("\nCounter number to enter a concrete player menu\nPress \"0\" to quit\n\n");
        int choise = Corrector.inputIntMethod(0, rfpl.myTeam.playerList.size());
        if(choise == 0) return;
        concretePlayerViewMenu(choise, rfpl.myTeam.playerList);
    }

    private static void concretePlayerViewMenu(int choise, ArrayList<Player> list){
        choise--;

        System.out.println("choise = " + choise);
        System.out.print("\n\n" + Corrector.getS(40));
        Player choosen = list.get(choise);
        Corrector.wordUpperCase(list.get(choise).name);
        System.out.print("\n\n" + Corrector.getS(40) + "Playing in club: " + choosen.club +
                "\n" + Corrector.getS(43) + " Nationality: " + choosen.natio +
                "\n" + Corrector.getS(47) + "Position: " + choosen.position +
                "\n" + Corrector.getS(41) + "Number in team: " + choosen.number +
                "\n\n" + Corrector.getS(41) + "Player's POWER: " + choosen.power +
                "\n" + Corrector.getS(33) + "Goalkeeper's abilities: " + choosen.gkAble +
                "\n" + Corrector.getS(37) + "Defender abilities: " + choosen.defAble +
                "\n" + Corrector.getS(35) + "Midfielder abilities: " + choosen.midAble +
                "\n" + Corrector.getS(38) + "Forward abilities: " + choosen.forwAble +
                "\n" + Corrector.getS(32) + "      Captain abilities: " + choosen.captainAble +
                "\n\n" + Corrector.getS(45) + "Is injured: " + boolToS(choosen.isInjury) +
                "\n" + Corrector.getS(47) + "Training: " + choosen.trainingAble +
                "\n" + Corrector.getS(25) + "Does player a captain function: " + boolToS(choosen.isCapitan) +
                "\n" + Corrector.getS(32) + "Is player in first-team: " + boolToS(choosen.is11Th) +
                "\n" + Corrector.getS(41) + "Player's price: " + choosen.price + " million Euro\n");

        System.out.println("\n\n" + Corrector.getS(50) + "\"1\" to previous\n" +
                Corrector.getS(50) + "\"2\"" + " to next\n" +
                Corrector.getS(50) + "\"0\" to quit: ");
        int anotherChoise = Corrector.inputIntMethod(0, 2);
        if (anotherChoise == 0) {
            return;
        }
        else if(anotherChoise == 1) {
            if (choise == 0) { choise = list.size();}
            concretePlayerViewMenu(choise, list);
        }
        else {
            choise += 2;
            if (choise == list.size() + 1) { choise = 1; }
            concretePlayerViewMenu(choise, list);
        }
    }

    private static String boolToS(boolean bool){
        if(bool)
            return "Yes";
        else
            return "No";
    }
}