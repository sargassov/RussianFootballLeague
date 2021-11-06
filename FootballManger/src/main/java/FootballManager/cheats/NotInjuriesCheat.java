package FootballManager.cheats;

import FootballManager.manager.Player;

public class NotInjuriesCheat extends Cheat{
    public NotInjuriesCheat() {
        this.cheatCodePassword = "nowihaveallplayersinhealth";
        this.characters = "You include a health of every your player";
    }

    @Override
    public void toCheat(){
        for(Player p : rfpl.myTeam.playerList){
            p.isInjury = false;
        }
    }
}
