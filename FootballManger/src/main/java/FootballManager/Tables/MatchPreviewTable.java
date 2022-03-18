package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;
import FootballManager.time.DayMatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

public class MatchPreviewTable extends Table implements Data{
    private Tournament rfpl;
    private final String footballMatchPreviewTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\matchPreview.txt";
    private final static String FOOTBALL_MATCH_PREVIEW_NOT_FOUND = "FOOTBALL MATCH PREVIEW NOT FOUND";
    private List<String> footballMatchPreviewTableList;
    private Team oppenentTeam;
    private DayMatch match;

    public MatchPreviewTable(Team team, DayMatch match){
        this.match = match;
        oppenentTeam = team;
        Path path = Paths.get(footballMatchPreviewTablePath);
        if(Files.exists(path)){
            try {
                footballMatchPreviewTableList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(FOOTBALL_MATCH_PREVIEW_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {
        for(int x = 0, y = 0; x < footballMatchPreviewTableList.size(); x++){
            if(x > 1 && x != 3 && x != 15 && x < 23){
                String[] mass = footballMatchPreviewTableList.get(x).split("/");
                if(x == 2){
                    mass[1] = Corrector.wordToCenter(match.home.name, mass[1].length());
                    mass[2] = Corrector.wordToCenter(match.away.name, mass[2].length());
                }
                else{
                    final int value = y;
                    Player homePlayer = match.home.playerList.stream().filter(p -> p.strategyPlace == value).findFirst().get();
                    Player awayPlayer = match.away.playerList.stream().filter(p -> p.strategyPlace == value).findFirst().get();
                    mass[1] = Corrector.wordToCenter(homePlayer.number.toString(), mass[1].length());
                    mass[2] = Corrector.wordToCenter(homePlayer.name + "(" + homePlayer.power + ")" + isCap(homePlayer), mass[2].length());
                    mass[3] = Corrector.wordToCenter(awayPlayer.number.toString(), mass[3].length());
                    mass[4] = Corrector.wordToCenter(awayPlayer.name + "(" + awayPlayer.power + ")" + isCap(awayPlayer), mass[4].length());
                    y++;
                }
                footballMatchPreviewTableList.set(x, Corrector.stringStapler(mass));

            }

            System.out.println(footballMatchPreviewTableList.get(x));
        }
    }

    private String isCap(Player player) {
        if(player.isCapitan) return "[=C=]";
        return "";
    }
}
