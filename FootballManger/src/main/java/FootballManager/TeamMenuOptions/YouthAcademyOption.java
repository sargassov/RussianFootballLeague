package FootballManager.TeamMenuOptions;

import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

import java.util.ArrayList;

public class YouthAcademyOption implements TeamMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        System.out.print(Corrector.getS(55));
        Corrector.wordUpperCase("youth academy");
        if(rfpl.wasAtTheYouthAcademy){
            System.out.println("\n\n");
            System.out.println(Corrector.getS(48) + "You have arleady were here at this playing day!\n\n\n\n");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                return;
            }
            return;
        }
        System.out.println("\n\n" + Corrector.getS(35) + "Welcome to youth academy pool. You can buy young players here\n");
        System.out.println(Corrector.getS(40) + "Now you can add a one of five players in your team:\n\n");
        ArrayList<Player> youthPlace = new ArrayList<>();
        int[] places = new int[5];
        for(int x = 0; x < 5; x++){
            int rand = (int)(Math.random() * rfpl.youthPool.size());
            Player player = new Player(rfpl.youthPool.get(rand).name, 0);
            String tech = player.name;
            String tech2 = Corrector.posInString(player.position);
            tech = Corrector.inspacer(tech);
            tech2 = Corrector.Inspacer4Sym(tech2);
            places[x] = rand;
            youthPlace.add(player);
            System.out.println(Corrector.getS(41) + (x+1) + ".\t" + tech + "\t" + tech2 + "\t(" +
                    player.power + ")\t" + Corrector.priceInMillion(player.price) + "\n");
        }
        System.out.println(Corrector.getS(50) +
                "Choose a player to add in your club.\n" +
                Corrector.getS(38) + "Warning! You can buy player only if you have enough credits!");
        int choose = Corrector.inputIntMethod(0, 5);
        choose--;
        if(choose == -1) return;
        else{
            if(rfpl.myTeam.wealth >= youthPlace.get(choose).price){
                System.out.println(Corrector.getS(50) + "Do you want to buy " + youthPlace.get(choose).name +
                        "?\n" + Corrector.getS(62) + "1 - \"Yes\"" +
                        "\n" + Corrector.getS(62) + "0 - \"No\": ");
                int anotherChoose = Corrector.inputIntMethod(0, 1);
                if(anotherChoose == 1){
                    Player youngPlayer = youthPlace.get(choose);
                    rfpl.myTeam.playerList.add(youngPlayer);
                    System.out.println(Corrector.getS(46) + "Choose a player number to new young player: ");
                    youngPlayer.number = Integer.parseInt(new PlayerEditorOption().guessNumber(rfpl));
                    youngPlayer.club = rfpl.myTeam.name;
                    rfpl.myTeam.wealth -= youngPlayer.price;
                    rfpl.youthPool.remove(places[choose]);
                    rfpl.wasAtTheYouthAcademy = true;

                    new ListPlayerOption().getOption(rfpl);
                }
            }
            else System.out.println("\n\n\t\t\tYour club hasn't enough credits to buy this player\n");
        }

    }
}
