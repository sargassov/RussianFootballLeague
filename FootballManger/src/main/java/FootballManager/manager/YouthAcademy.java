package FootballManager.manager;

import java.util.Arrays;
import java.util.List;

public class YouthAcademy {

    private static Tournament rfpl;
    private static List<Position> posList = Arrays.asList(Position.GOALKEEPER, Position.DEFENDER, Position.MIDFIELDER,
            Position.FORWARD);

    public static void youthAcademyPlayersAdd(Tournament tournament) {

        rfpl = tournament;

        for (Team team : rfpl.teams)
            if (!rfpl.myTeam.name.equals(team.name)) {
                fantasyDraft(team);
            }
        }

    private static void fantasyDraft(Team team) {
        for(Position pos : posList){
            for (Player player : rfpl.youthPool){
                if(player.position.equals(pos)){
                    player.club = team.name;
                    player.number = Player.YouthNumberCorrector(team.playerList);
                    rfpl.youthPool.remove(player);
                    team.playerList.add(player);
                    break;
                }
            }
        }
    }
}
