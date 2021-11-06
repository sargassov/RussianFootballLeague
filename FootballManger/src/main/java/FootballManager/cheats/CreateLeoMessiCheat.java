package FootballManager.cheats;

import FootballManager.manager.Player;

public class CreateLeoMessiCheat extends Cheat{

    private static boolean flag = true;

    public CreateLeoMessiCheat() {
        this.cheatCodePassword = "icallleomessiinmyclub";
        this.characters = "You were create a Leo Messi. Now he playing in your club";
    }

    @Override
    public void toCheat(){
        Player player = new Player("Messi Leonel/1987/Arg/" +
                rfpl.myTeam.name + "/Forw/1/16/76/120/4.5/89/10");

        numberIsClosed(player);

        if(flag){
            rfpl.myTeam.playerList.add(player);
            flag = false;
        }
    }

    private void numberIsClosed(Player player) {

        for(int x = 0; x < rfpl.myTeam.playerList.size(); x++){
            if(rfpl.myTeam.playerList.get(x).number.equals(player.number)){
                player.number++;
                numberIsClosed(player);
            }
        }
    }
}
