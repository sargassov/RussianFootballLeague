package FootballManager.coaches;


import FootballManager.manager.Player;
import FootballManager.manager.Position;

public class Coach {
    protected CoachProgram currentCoachProgram = CoachProgram.STANDART;
    protected String name;
    protected Player playerOnTrain = null;
    public Coach(){}
    protected Position coachPos;
    protected LevelOfCoach levelOfCoach;
    //protected long salary;

    public CoachProgram getCurrentCoachProgram() {
        return currentCoachProgram;
    }

    public void setCurrentCoachProgram(CoachProgram currentCoachProgram) {
        this.currentCoachProgram = currentCoachProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayerOnTrain() {
        return playerOnTrain;
    }

    public void setPlayerOnTrain(Player playerOnTrain) {
        this.playerOnTrain = playerOnTrain;
    }

    public Position getCoachPos() {
        return coachPos;
    }

    public void setCoachPos(Position coachPos) {
        this.coachPos = coachPos;
    }

    public LevelOfCoach getLevelOfCoach() {
        return levelOfCoach;
    }

    public void setLevelOfCoach(LevelOfCoach levelOfCoach) {
        this.levelOfCoach = levelOfCoach;
    }

//    public long getSalary() {
//        return salary;
//    }
//
//    public void setSalary(long salary) {
//        this.salary = salary;
//    }
}
