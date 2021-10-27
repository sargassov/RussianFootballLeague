package FootballManager.coaches;


import FootballManager.manager.Position;

public class DefenderCoach extends Coach {
    public DefenderCoach(String name){
        coachPos = Position.DEFENDER;
        this.name = name;
    }
}
