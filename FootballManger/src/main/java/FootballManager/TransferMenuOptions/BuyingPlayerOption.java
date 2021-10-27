package FootballManager.TransferMenuOptions;


import FootballManager.Tables.TransferTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;
import FootballManager.strategies.Strategy;

public class BuyingPlayerOption implements TransferMenuOptionsInterface {

    private int alterTeam = 0;
    private int yourClubList = 0;
    private int alterClubList = 0;

    @Override
    public void getOption(Tournament rfpl) {
        while(true){
            System.out.println("\n");
            System.out.print(Corrector.getS(38));
            Corrector.wordUpperCase("transfer list menu");
            System.out.println("\n\n");
            if(rfpl.myTeam.name.equals(rfpl.teams[alterTeam].name)){
                alterTeam++;
            }
            new TransferTable(alterTeam, yourClubList, alterClubList).toPrint(rfpl);
            System.out.println("\nTO BUY PLAYER - \"7\"\nNext team - \"1\" Prevoius team - \"2\"\n" +
                    "Next your player - \"3\" Prevois your player - \"4\"\n" +
                    "Next alter player - \"5\" Prevoius alter player - \"6\"\n" +
                    "To quit - \"0\": ");
            int choise = Corrector.inputIntMethod(0, 7);
            if(choise == 0) break;
            else if(choise == 1) {
                alterTeam++;
                while(alterTeam == rfpl.teams.length || rfpl.teams[alterTeam].name.equals(rfpl.myTeam.name)){
                    if(alterTeam == rfpl.teams.length) alterTeam = 0;
                    if(rfpl.teams[alterTeam].name.equals(rfpl.myTeam.name)) alterTeam++;
                }
                yourClubList = 0;
                alterClubList = 0;
            }
            else if(choise == 2){
                alterTeam--;
                while(alterTeam < 0 || rfpl.teams[alterTeam].name.equals(rfpl.myTeam.name)){
                    if(alterTeam < 0)  alterTeam = rfpl.teams.length - 1;
                    if(rfpl.teams[alterTeam].name.equals(rfpl.myTeam.name)) alterTeam--;
                }

                yourClubList = 0;
                alterClubList = 0;
            }
            else if(choise == 3) {yourClubList++; if(yourClubList == rfpl.myTeam.playerList.size()) yourClubList = 0;}
            else if(choise == 4) {yourClubList--; if(yourClubList < 0) yourClubList = rfpl.myTeam.playerList.size() - 1;}
            else if(choise == 5) {alterClubList++; if(alterClubList == rfpl.teams[alterTeam].playerList.size()) alterClubList = 0;}
            else if (choise == 6) { alterClubList--; if(alterClubList < 0) alterClubList = rfpl.teams[alterTeam].playerList.size() - 1;}
            else toByuPlayer(rfpl, alterTeam, yourClubList, alterClubList);
        }
    }

    private void toByuPlayer(Tournament rfpl, int alterTeam, int yourClubList, int alterClubList){
        System.out.println("\nEnter a number of alter player to byu him, or press \"0\" to quit: ");
        int choise = Corrector.inputIntMethod(0, 13);
        if(choise == 0) return;
        --choise;
        int tableChoise = alterClubList + choise;
        if(tableChoise > rfpl.teams[alterTeam].playerList.size() - 1) tableChoise -= rfpl.teams[alterTeam].playerList.size();
        if(rfpl.myTeam.wealth >= rfpl.teams[alterTeam].playerList.get(tableChoise).price){
            System.out.println("Do you want yo byu " + rfpl.teams[alterTeam].playerList.get(tableChoise).name +
                    " for " + rfpl.teams[alterTeam].playerList.get(tableChoise).price + " ?" +
                    "\n\"1\" - Yes\n\"0\" - No: ");
            int yesOrNoChoise = Corrector.inputIntMethod(0, 1);
            if(yesOrNoChoise == 0) return;
            else{
                Player player = rfpl.teams[alterTeam].playerList.get(tableChoise);
                rfpl.myTeam.wealth -= player.price;
                rfpl.myTeam.transferExpenses -= player.price;
                rfpl.teams[alterTeam].wealth += player.price;
                rfpl.teams[alterTeam].transferExpenses += player.price;
                player.is11Th = false;
                player.strategyPlace = -100;
                rfpl.teams[alterTeam].playerList.remove(player);
                player.club = rfpl.myTeam.name;
                System.out.println(player.name + " has just join in " + rfpl.myTeam.name + " from " +
                        rfpl.teams[alterTeam].name + " for " + player.price + " mln. EURO!\n" +
                        "Choose a player number: ");
                player.number = Corrector.getNumber(rfpl);
                rfpl.myTeam.playerList.add(player);
                //rfpl.my_team.strategy.concretStrategyList = new ConcretStrategy[18];
                //rfpl.teams[alterTeam].strategy.concretStrategyList = new ConcretStrategy[18];
                Strategy.autoStrategyCreatorMethod(rfpl.teams, rfpl.indexOfUserTeam);
                Strategy.autoStrategyCreatorMethod(rfpl.teams, alterTeam);
                Strategy.concretPowerTeamCounter(rfpl.teams[alterTeam]);
                rfpl.teams[alterTeam].teamPower /= 11;
            }
        }
        else System.out.println("\n\n" + rfpl.myTeam.name + " can't buy " + rfpl.teams[alterTeam].playerList.get(alterClubList + choise)
        .name + ". Your club hasn't enough money!");
    }

}
