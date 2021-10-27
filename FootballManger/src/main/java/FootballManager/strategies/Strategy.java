package FootballManager.strategies;


import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.exit;

public class Strategy {

    private static Tournament rfpl;
    private String name;
    private ConcretStrategy[] concretStrategyList;
    private ArrayList<String> visualizer;
    private String beginAddress = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\";

    private final static String _442 = "4 - 4 - 2";
    private final static String _352 = "3 - 5 - 2";
    private final static String _343 = "3 - 4 - 3";
    private final static String _451 = "4 - 5 - 1";
    private final static String _541 = "5 - 4 - 1";
    private final static String[] posParams = {"Gk", "Def", "Mid", "Forw"};

    public Strategy(){}

    public ConcretStrategy[] getConcretStrategyList() {
        return concretStrategyList;
    }

    public Strategy(String description) {

        visualizer = new ArrayList<>();
        concretStrategyList = new ConcretStrategy[18];
        name = description;
        description += ".txt";
        beginAddress += description;

        Corrector.notNullChecking(description);

        try(FileReader inStrategy = new FileReader(beginAddress)){
            BufferedReader reader = new BufferedReader(inStrategy);
            String line = reader.readLine();
            short x = 0;

            while(line != null){
                if (x < concretStrategyList.length) {
                    concretStrategyList[x] = new ConcretStrategy();
                    concretStrategyList[x].position = Corrector.stringInPos(line);
                }
                else {
                    visualizer.add(line);
                }
                x++;
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("\n\n\t\t\tLOAD FILE " + description + "\n\n");
            exit(0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void strategyCreator(Tournament tournament) {

        rfpl = tournament;

        rfpl.strategies = Arrays.asList(
                new Strategy(_442),
                new Strategy(_352),
                new Strategy(_343),
                new Strategy(_451),
                new Strategy(_541));

    }

    public static void autoStrategyCreator(){
        Team[] teams = rfpl.teams;
        for (int i = 0; i < teams.length; i++) { //для каждой из 16 команд
            short ran = (short) (Math.random() * 5);//выбирается случайная из 5 стартегий
            teams[i].strategy = rfpl.strategies.get(ran);//и устаналвливается как базовая
            autoStrategyCreatorMethod(teams, i);

        }
    }

    public static void autoStrategyCreatorMethod(Team[] teams, int i){

        Corrector.notNullChecking(teams);

        Player selectionPlayer;
        int y = 0;
        for (ConcretStrategy concretStrategy : teams[i].strategy.concretStrategyList) {//перебирается каждая из позиций стратегии

            int maxVal = 0, currentVal = 0;
            if(findEqualStrategyPlace(y, teams[i].playerList)) continue;

            selectionPlayer = null;
            for (Player player : teams[i].playerList) {//перебираются все игроки команды

                int[] ableParams = {player.gkAble, player.defAble, player.midAble, player.forwAble};

                if (concretStrategy.position.equals(player.position)
                        && !player.isInjury && !player.is11Th && player.strategyPlace < 0) {
                    for(int f = 0; f < posParams.length; f++){
                        if(concretStrategy.position.equals(Corrector.stringInPos(posParams[f]))) {
                            currentVal = ableParams[f];
                            break;
                        }
                    }

                    if (currentVal > maxVal) {
                        selectionPlayer = player;
                        maxVal = currentVal;
                    }
                }
            }
            if (selectionPlayer != null && !findEqualStrategyPlace(y, teams[i].playerList)) {
                selectionPlayer.is11Th = true;
                selectionPlayer.strategyPlace = y;

            }

            y++;
        }
    }

    private static boolean findEqualStrategyPlace(int y, ArrayList<Player> list){

        Corrector.notNullChecking(list);

        for(Player player : list){
            if(player.strategyPlace == y)
                return true;
        }
        return false;
    }

    public static void capitanDeterminer() {
        int captainVal = -1;
        for(Team team : rfpl.teams){
            Player capitan = team.playerList.get(0);
            for (Player player : team.playerList) {
                if(player.captainAble > captainVal){
                    captainVal = player.captainAble;
                    capitan = player;
                }
            }
            capitan.isCapitan = true;
            captainVal = -1;
        }
    }

    public static void powerTeamCounter() {
        for(Team team : rfpl.teams){
            concretPowerTeamCounter(team);
        }
    }

    public static void concretPowerTeamCounter(Team team){

        Corrector.notNullChecking(team);
        System.out.println("size of the team = " + team.playerList.size());
        team.teamPower = 0;
        for(Player player : team.playerList){
            if(player.strategyPlace > -1 && player.strategyPlace < 11){
                team.teamPower += player.power;
                if(player.isCapitan)
                    team.teamPower += player.captainAble;
            }
        }
    }
}
