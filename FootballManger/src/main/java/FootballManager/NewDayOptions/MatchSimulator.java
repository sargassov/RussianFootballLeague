package FootballManager.NewDayOptions;

import FootballManager.manager.Team;
import FootballManager.time.DayMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchSimulator {

    private enum Result{
        HOMEWIN,
        VISITORWIN,
        DRAW
    }

    Random random = new Random();

    public void simulate(ArrayList<DayMatch> matchTour){
        for(DayMatch dayMatch: matchTour){
            int probability = random.nextInt(10000);

            if(true) winByForce(dayMatch);
        }
    }

    private void winByForce(DayMatch dayMatch) {
        if(dayMatch.home.teamPower > dayMatch.away.teamPower){winByOneTeam(dayMatch.home, dayMatch.away, Result.HOMEWIN, 3, 0, dayMatch);}
        else if(dayMatch.home.teamPower < dayMatch.away.teamPower){winByOneTeam(dayMatch.away, dayMatch.home, Result.VISITORWIN, 3, 0, dayMatch);}
        else {draw(dayMatch.home, dayMatch.away, 1, 1, dayMatch);}
    }

    private void winByOneTeam(Team winner, Team loser, Result result, int winnerScored, int loserScored, DayMatch dayMatch) {
        winner.wins += 1;
        winner.games += 1;
        winner.goalScored += winnerScored;
        winner.goalMissed += loserScored;
        loser.defeats += 1;
        loser.games += 1;
        loser.goalScored += loserScored;
        loser.goalMissed += winnerScored;

        if(result.equals(Result.HOMEWIN)){
            dayMatch.homeScore = winnerScored;
            dayMatch.awayScore = loserScored;
        }
        if(result.equals(Result.VISITORWIN)){
            dayMatch.awayScore = winnerScored;
            dayMatch.homeScore = loserScored;
        }
        dayMatch.itWas = true;
    }

    private void draw(Team homeTeam, Team visitorTeam, int homeScored, int visitorScored, DayMatch dayMatch){
        homeTeam.draws += 1;
        homeTeam.games += 1;
        homeTeam.goalScored += homeScored;
        homeTeam.goalMissed += visitorScored;
        visitorTeam.draws += 1;
        visitorTeam.games += 1;
        visitorTeam.goalScored += visitorScored;
        visitorTeam.goalMissed += homeScored;
        dayMatch.homeScore = homeScored;
        dayMatch.awayScore = visitorScored;
        dayMatch.itWas = true;

    }

}
