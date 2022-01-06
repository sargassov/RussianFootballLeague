package FootballManager.LeagueMenuOptions;

import FootballManager.Tables.LeagueTable;
import FootballManager.Tables.ListOfPlayersTable;
import FootballManager.TeamMenuOptions.ListPlayerOptionSorts;
import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;

import java.util.ArrayList;
import java.util.List;

public class ViewAllPlayersOption implements LeagueMenuOptionsInterface{

    private static int sVal = 40;
    private static Tournament rfpl;
    private static String name = "view all players option";
    private ArrayList<Player> playerList;


    @Override
    public void getOption(Tournament rfpl) {
        System.out.print(Corrector.getS(sVal));
        Corrector.wordUpperCase(name);
        System.out.println("\n\n\n");
        ViewAllPlayersOption.rfpl = rfpl;

        new ListOfPlayersTable(true, allPlayersListCreator()).toPrint(rfpl);

        int choise = Corrector.inputIntMethod(0, 1);
        if(choise == 0) return;

        else{
            System.out.print("\n\nM E N U:\n\n==> 0. Quit\n\nSORTING:\n\n==> 1. Sort by name\n==> 2. Sort by nationality\n==> " +
                    "3. Sort by number\n==> 4. Sort by player position\n==> 5. Sort by goalkeeper ability\n==> 6." +
                    " Sort by defender ability\n==> 7. Sort by midfielder ability\n==> 8. Sort by forward ability\n==> " +
                    " 9. Sort by captain ability\n==> 10. Sort by injuries\n==> 11. Sort by time training abilities\n==> 12." +
                    " Sort by year of birth\n==> 13. Sort by position in the team\n==> 14. Sort by power\n==> 15. Sort by " +
                    "tire\n==> 16. Sort by time before treatment\n==> 17. Sort by price\n==> 18. Sort by club: ");
        }

        final int finalInt = Corrector.inputIntMethod(0, 19);
        if(finalInt == 0) return;
        else if(finalInt == 1 || finalInt == 2 || finalInt == 4 || finalInt == 18) ListPlayerOptionSorts.sort(playerList, finalInt);
        else if(finalInt == 3 || finalInt == 5 || finalInt == 6 || finalInt == 7 || finalInt == 8 ||
                finalInt == 9 || finalInt == 11 || finalInt == 12 || finalInt == 14 || finalInt == 15 || finalInt == 16)
            ListPlayerOptionSorts.sort(playerList, finalInt, 0);
            //ListPlayerOptionSorts.sort(rfpl, choise, 0);
        else if(finalInt == 10) ListPlayerOptionSorts.sort(playerList, true);
        else if(finalInt == 13) ListPlayerOptionSorts.sort(playerList);
        else ListPlayerOptionSorts.sort(playerList, 0.0);

        getOption(rfpl);

    }

    private ArrayList<Player> allPlayersListCreator() {
        if(playerList == null){
            playerList = new ArrayList<>();
            for(Team t : rfpl.teams){
                playerList.addAll(t.playerList);
            }
        }
        return playerList;
    }
}
