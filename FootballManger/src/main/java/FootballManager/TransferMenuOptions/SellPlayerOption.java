package FootballManager.TransferMenuOptions;


import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

public class SellPlayerOption implements TransferMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        while(true){
            System.out.println("\n\n");
            Corrector.getS(48);
            Corrector.wordUpperCase("sell player " + rfpl.myTeam.name + " " + rfpl.myTeam.wealth + " mln. euro");
            System.out.println("\n");
            int counter = 1;
            for(Player player : rfpl.myTeam.playerList){
                System.out.println(counter + ". " + player.name + " " + player.price/2);
                counter++;
            }
            System.out.println("\nEnter a player to sell him, or press \"0\" to quit: ");
            int choise = Corrector.inputIntMethod(0, rfpl.myTeam.playerList.size());
            if(choise == 0) break;
            --choise;
            System.out.println("Do you want to sell " + rfpl.myTeam.playerList.get(choise).name + " for a half of" +
                    " price?\n\"1\" - Yes\n\"0\" - No: ");
            int yesOrNoChoise = Corrector.inputIntMethod(0, 1);
            if(yesOrNoChoise == 0) continue;
            else{
                rfpl.myTeam.wealth += rfpl.myTeam.playerList.get(choise).price / 2;
                rfpl.myTeam.playerList.remove(choise);
            }
        }


    }
}
