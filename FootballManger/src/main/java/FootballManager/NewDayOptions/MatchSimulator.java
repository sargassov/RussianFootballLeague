package FootballManager.NewDayOptions;

import FootballManager.manager.Team;
import FootballManager.time.DayMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchSimulator {

    Random random = new Random();

    public void simulate(ArrayList<DayMatch> matchTour){
        for(DayMatch dayMatch: matchTour){

            Team homeTeam = dayMatch.home;
            Team visitorTeam = dayMatch.away;

            Integer probability = 0;
            probability += ptsForHomeStadium();
            probability += ptsForTeamForce(homeTeam, visitorTeam);
            probability += ptsForStarPlayers(homeTeam, visitorTeam);
            probability += ptsForInjuries(homeTeam, visitorTeam);
            probability += ptsForCaptainProfi(homeTeam, visitorTeam);
            System.out.println(probability);

            //if(true) winByForce(dayMatch);
        }
    }

    private Integer ptsForCaptainProfi(Team homeTeam, Team visitorTeam) {
        boolean homeTeamCapOnField;
        boolean visitorTeamCapOnField;
        int sumPts = 0;

        homeTeamCapOnField = capOnFieldDetermining(homeTeam);
        visitorTeamCapOnField = capOnFieldDetermining(visitorTeam);

        if(homeTeamCapOnField && !visitorTeamCapOnField) return -10;
        else if(!homeTeamCapOnField && visitorTeamCapOnField) return 10;
        return 0;
    }

    private boolean capOnFieldDetermining(Team team) {
        return team.playerList.stream()
                .filter(p -> (p.strategyPlace > -1 && p.strategyPlace < 11))
                .anyMatch(p -> p.isCapitan);
    }

    private Integer ptsForInjuries(Team homeTeam, Team visitorTeam) {
        int homeTeamInj;
        int visitorTeamInj;

        homeTeamInj = (int) homeTeam.playerList.stream().filter(p -> p.isInjury).count();
        visitorTeamInj = (int) visitorTeam.playerList.stream().filter(p -> p.isInjury).count();

        if(homeTeamInj > 3 && visitorTeamInj < 3) return 10;
        if(homeTeamInj > 5 && visitorTeamInj < 5) return 20;
        if(homeTeamInj < 3 && visitorTeamInj > 3) return -10;
        if(homeTeamInj < 5 && visitorTeamInj > 3) return -20;
        else return 0;
    }

    private Integer ptsForStarPlayers(Team homeTeam, Team visitorTeam) {
        boolean homeStarPlayersConsist;
        boolean visitorStarPlayersConsist;

        homeStarPlayersConsist = homeTeam.playerList.stream().anyMatch(p -> p.power > 90);
        visitorStarPlayersConsist = visitorTeam.playerList.stream().anyMatch(p -> p.power > 90);

        if(homeStarPlayersConsist && !visitorStarPlayersConsist) return -10;
        if(!homeStarPlayersConsist && visitorStarPlayersConsist) return 10;
        else return 0;
    }

    private Integer ptsForTeamForce(Team homeTeam, Team visitorTeam) {
        int delta = visitorTeam.teamPower - homeTeam.teamPower;

        if(delta > 5 && delta <= 10) return 6;
        if(delta > 10 && delta <= 15) return 12;
        if(delta > 15 && delta <= 20) return 25;
        if(delta > 20) return 35;
        if(delta > -10 && delta <= -5) return -6;
        if(delta > -15 && delta <= -10) return -12;
        if(delta > -20 && delta <= -15) return -25;
        if(delta <= -21) return -35;
        return 0;
    }

    private int ptsForHomeStadium() {
        return -30;
    }

    private void winByForce(DayMatch dayMatch) {

        Team homeTeam = dayMatch.home;
        Team visitorTeam = dayMatch.away;
        int delta = homeTeam.teamPower - visitorTeam.teamPower;

        if(delta > -5 && delta < 5)
            ResultSimulator.draw(homeTeam, visitorTeam, 1, 1, dayMatch);
        else {
            if(homeTeam.teamPower > visitorTeam.teamPower){ResultSimulator.winByOneTeam(homeTeam, visitorTeam, Result.HOMEWIN, 3, 0, dayMatch);}
            else if(homeTeam.teamPower < visitorTeam.teamPower){ResultSimulator.winByOneTeam(visitorTeam, homeTeam, Result.VISITORWIN, 3, 0, dayMatch);}
        }
    }



}
