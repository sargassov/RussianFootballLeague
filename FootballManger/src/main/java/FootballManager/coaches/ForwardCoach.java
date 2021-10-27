package FootballManager.coaches;


import FootballManager.manager.Position;

public class ForwardCoach extends Coach {
    public ForwardCoach(String name){
        coachPos = Position.FORWARD;
        this.name = name;
    }
}
