package FootballManager.cheats;

public class ThousandFansCheat extends Cheat{
    public ThousandFansCheat() {
        this.cheatCodePassword = "athousandfanscameintomyclub";
        this.characters = "You were add a 1000 fans to your club's stadium";
    }

    @Override
    public void toCheat(){
        rfpl.myTeam.stadium.setUsualAverageCapacity(
                rfpl.myTeam.stadium.getUsualAverageCapacity() + 1000
        );
    }
}
