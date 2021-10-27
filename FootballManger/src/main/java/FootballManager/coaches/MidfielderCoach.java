package FootballManager.coaches;


import FootballManager.manager.Position;

public class MidfielderCoach extends Coach {
    public MidfielderCoach(String name){
        coachPos = Position.MIDFIELDER;
        this.name = name;
    }
}
