package FootballManager.cheats;

public class OneMillonEuroCheat extends Cheat{

    public OneMillonEuroCheat() {
        this.cheatCodePassword = "millioneurointomyclub";
        this.characters = "You were add a 1M EURO to your club's wealth";
    }

    @Override
    public void toCheat(){
        rfpl.myTeam.wealth += 1.0;
    }
}
