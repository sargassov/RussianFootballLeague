package FootballManager.time;


import FootballManager.manager.Stadium;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;

import java.util.Calendar;

public class DayMatch extends Day {
    public Team home;
    public Team away;
    public Stadium stadium;
    public static Tournament rfpl;
    public int homeScore;
    public int awayScore;
    public boolean itWas = false;

    public DayMatch(){}

    public void setHome(Team home) {
        this.home = home;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public static void setRfpl(Tournament rfpl) {
        DayMatch.rfpl = rfpl;
    }

    @Override
    public String toString() {
        return "DayMatch{" +
                "home=" + home.name +
                ", away=" + away.name +
                ", stadium=" + stadium.getTitle() +
                ", ((" + homeScore +
                " : " + awayScore +
                "))" + itWas +
                '}';
    }
}
