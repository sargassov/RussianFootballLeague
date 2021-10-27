package FootballManager.coaches;


import FootballManager.manager.Position;

public class GoalkeeperCoach extends Coach {
    public  GoalkeeperCoach(String name){
        coachPos = Position.GOALKEEPER;
        this.name = name;
    }
}
