package FootballManager.Tables;

import FootballManager.manager.Team;
import FootballManager.manager.Tournament;
import FootballManager.time.DayMatch;

import java.util.List;

public class MatchTable extends Table implements Data{
    private Tournament rfpl;
    private final String footballMatchTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\match.txt";
    private final static String FOOTBALL_MATCH_NOT_FOUND = "FOOTBALL MATCH NOT FOUND";
    private List<String> footballMatchTableList;
    private Team oppenentTeam;
    private DayMatch match;


    @Override
    public void toPrint(Tournament rfpl) {

    }
}
