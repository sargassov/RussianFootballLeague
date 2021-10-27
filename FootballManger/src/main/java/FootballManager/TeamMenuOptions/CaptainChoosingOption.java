package FootballManager.TeamMenuOptions;


import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CaptainChoosingOption implements TeamMenuOptionsInterface {
    private Tournament rfpl;
    private Player captain;
    private Team myTeam;
    private static int sVal = 40;

    @Override
    public void getOption(Tournament rfpl) {

        System.out.print("\n\n" + Corrector.getS(40)); // тeкущего капитана добавать
        Corrector.wordUpperCase("captain choosing menu\n");
        this.rfpl = rfpl;
        myTeam = rfpl.myTeam;

        captain = getCaptain(myTeam.playerList);
        if(captain == null) noCapitan();
        else capitanOf();

        int choise = Corrector.inputIntMethod(0, 1);
        if(choise == 0) return;
        else {
            System.out.println(rfpl.myTeam.teamPower);
            if(captain != null && captain.strategyPlace > -1){
                myTeam.teamPower -= captain.captainAble;
                captain.isCapitan = false;
            }

            System.out.println(rfpl.myTeam.teamPower);
            choosingForNewCaptain(rfpl);
        };
    }

    private void capitanOf() {
        System.out.print(Corrector.getS(sVal) + "A captain of " + myTeam.name + " is  ");
        Corrector.wordUpperCase(captain.name);
        System.out.println("(" + captain.captainAble + ")\n\n" + Corrector.getS(sVal) +
                "    Do you want to change ?\n\n" + Corrector.getS(sVal) + "1 -\"Yes\"\n" +
                Corrector.getS(sVal) + "0 - \"No\": ");
    }

    private void noCapitan() {
        System.out.print(Corrector.getS(sVal) + "There is no captain in " + myTeam.name +
                ". Choose it?\n" + Corrector.getS(sVal) + "1 - \"Yes\"\n" +
                Corrector.getS(sVal) + "0 - \"No\": ");
    }

    private void choosingForNewCaptain(Tournament rfpl) {

        System.out.println("Other players:\n");
        List<Player> compareCaps =  rfpl.myTeam.playerList.stream()
                .sorted((player1, player2) -> player2.captainAble - player1.captainAble)
                .collect(Collectors.toList());

        System.out.println("Number\t Name\t\t\tCaptain abilities\n\n");

        int count = 1;
        for(Player player : compareCaps){
            System.out.println(count + "\t" + Corrector.inspacer(player.name) + "\t" + player.captainAble);
            count++;
        }

        System.out.print("\n\nChoose a captain: ");
        int choise = Corrector.inputIntMethod(1, rfpl.myTeam.playerList.size());
        choise--;
        compareCaps.get(choise).isCapitan = true;

        System.out.println(rfpl.myTeam.teamPower);
        if(compareCaps.get(choise).strategyPlace > -1)
            rfpl.myTeam.teamPower += compareCaps.get(choise).captainAble;

        System.out.println(rfpl.myTeam.teamPower);
        System.out.print("\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase(compareCaps.get(choise).name);
        System.out.println(" is new captain of " + rfpl.myTeam.name);
    }

    private Player getCaptain (ArrayList< Player > list) {
        Optional<Player> opt = list.stream()
                .filter(player -> player.isCapitan)
                .findAny();
        if (opt.isPresent())
            return opt.get();
        return null;
    }
}




