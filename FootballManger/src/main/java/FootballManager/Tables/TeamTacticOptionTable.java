package FootballManager.Tables;


import FootballManager.TeamMenuOptions.TeamTacticOption;
import FootballManager.manager.*;
import FootballManager.strategies.ConcretStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static FootballManager.manager.Corrector.getS;

public class TeamTacticOptionTable extends Table implements Data{

    private Tournament rfpl;
    private static int spVal;
    private Player targetPlayer;
    private TeamTacticOption tacticOption;

    public TeamTacticOptionTable(TeamTacticOption tacticOption, int spVal){
        TeamTacticOptionTable.spVal = spVal;
        this.tacticOption = tacticOption;
    }


    @Override
    public void toPrint(Tournament rfpl) {

        this.rfpl = rfpl;

        System.out.print("\n\n" + getS(spVal) + "U S E F U L   S T R A T E G Y  ");
        Corrector.wordUpperCase(rfpl.myTeam.name);
        System.out.println("\n");
        int coef = 0;
//        Interface tacticInterface = new Interface(tacticDeterminer(rfpl.myTeam));
//
//        ArrayList<Player> players;
//        for(int strInTacticInterface = 0, numPosition = 0, per = 0;
//            strInTacticInterface < tacticInterface.fields.size(); strInTacticInterface++){
//
//            if(per < 4){
//                players = fillPlyers(per, numPosition);
//                System.out.println(players.size());
//                if(strInTacticInterface % 5 == 2){
//                    String[] mass = tacticInterface.fields.get(strInTacticInterface).split("\\|");
//                    for(int x = 0; x < players.size(); x++){
//                        mass[x + 1] = Corrector.wordToCenter(players.get(x).name, mass[x + 1].length());
//                    }
//                    tacticInterface.fields.set(strInTacticInterface, Corrector.stringStapler(mass));
//                }
//                if(strInTacticInterface % 5 == 3){
//                    String[] mass = tacticInterface.fields.get(strInTacticInterface).split("\\|");
//                    for(int x = 0; x < players.size(); x++){
//                        mass[x + 1] = Corrector.wordToCenter(players.get(x).power.toString(), mass[x + 1].length());
//                    }
//                    tacticInterface.fields.set(strInTacticInterface, Corrector.stringStapler(mass));
//                }
//                numPosition += players.size();
//                per++;
//            }
//            System.out.println(tacticInterface.fields.get(strInTacticInterface));
//        }

        for (ConcretStrategy concretStrategy : rfpl.myTeam.strategy.getConcretStrategyList()) {
            targetPlayer = tacticOption.getTargetPlayer(rfpl, coef);

            if(targetPlayer == null){
                if(coef < 11) System.out.print(getS(spVal));
                if (coef == 11) System.out.println("\n\n");
                if(coef > 10) System.out.print(getS(48));
                System.out.println("Without Player (0) ");
                coef++;
                continue;
            }

            if (coef < 11) {

                if (concretStrategy.position.equals(Position.GOALKEEPER)) System.out.println(getS(spVal) + (coef + 1) + ". Goalkeeper: " +
                        targetPlayer.name + " (" + targetPlayer.gkAble + ") " + isCaptain(targetPlayer));
                else if (concretStrategy.position.equals(Position.DEFENDER)) System.out.println(getS(spVal) + (coef + 1) +  ". Defender: " +
                        targetPlayer.name + " (" + targetPlayer.defAble + ") " + isCaptain(targetPlayer));
                else if (concretStrategy.position.equals(Position.MIDFIELDER)) System.out.println(getS(spVal) + (coef + 1) +  ". Midfielder: " +
                        targetPlayer.name + " (" + targetPlayer.midAble + ") " + isCaptain(targetPlayer));
                else System.out.println(getS(spVal) + (coef + 1) +  ". Forward: " +
                            targetPlayer.name + " (" + targetPlayer.forwAble + ") " + isCaptain(targetPlayer));
            }
            else if (coef == 11) System.out.println("\n");
            if(coef > 10) System.out.println(getS(48) + (coef + 1) + ". Substitution: " + targetPlayer.name);
            coef++;
        }
        System.out.println("\n\n\t\tTeam Power: " + rfpl.myTeam.teamPower / 11 + "\n");
        //system("pause");
        System.out.print(getS(32) +"If you choose a new strategy this placement will be lost\n"
                + getS(44) + "==> 1. (4 - 4 - 2)" +
                "\n" + getS(44) + "==> 2. (3 - 5 - 2)\n" + getS(44) +"==> 3. (3 - 4 - 3)" +
                "\n" + getS(44) + "==> 4. (4 - 5 - 1)\n" + getS(44) + "==> 5. (5 - 4 - 1)" +
                "\n" + getS(44) + "==> 0. Back to previos menu: ");
    }


    private String isCaptain(Player player){
        if(player.isCapitan)
            return "CAPTAIN";
        return "";
    }

//    private ArrayList<Player> fillPlyers(int per, int numPosition) {
//        ArrayList<Player> players = new ArrayList<>();
//        ArrayList<Player> list = rfpl.myTeam.playerList;
//        Position[] positions = {Position.GOALKEEPER, Position.DEFENDER,
//            Position.MIDFIELDER, Position.FORWARD};
//
//        for(int i = numPosition; i < 11; i++){
//            for(Player p : list){
//                if(p.position.equals(positions[per]) && p.strategyPlace == i) players.add(p);
//                break;
//            }
//        }
//
//        return players;
//    }
//
//    private ArrayList<Player> determinePosition(Position position) {
//        ArrayList<Player> list = new ArrayList<>();
//        for(Player p : rfpl.myTeam.playerList){
//            if(p.position.equals(position)) list.add(p);
//        }
//        return list;
//    }
//
//    private Player takeFromTeam(int y) {
//        for(Player p : rfpl.myTeam.playerList){
//            if(p.strategyPlace == y) return p;
//        }
//        return null;
//    }
//
//    private String tacticDeterminer(Team team) {
//        int defCount = 0, midCount = 0, forwCount = 0;
//
//        for(ConcretStrategy strategy : team.strategy.getConcretStrategyList()){
//            if(strategy.position.equals(Position.DEFENDER)) defCount++;
//            else if(strategy.position.equals(Position.MIDFIELDER)) midCount++;
//            else if(strategy.position.equals(Position.FORWARD)) forwCount++;
//        }
//
//        if(defCount == 5) return "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\5-4-1position.txt";
//        else if(defCount == 3 && midCount == 5) return "FootballManger\\src\\main\\java/FootballManager\\textFiles\\3-5-2position.txt";
//        else if (forwCount == 3) return "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\3-4-3position.txt";
//        else if (defCount == 4 && midCount == 4) return "FootballManger\\src\\main\\java\\FootballManager\\textFiles/4-4-2position.txt";
//        else if(midCount == 5 && defCount == 4) return "FootballManger\\src\\main\\java\\FootballManager\\textFiles/4-5-1position.txt";
//
//        return null;
//    }
}
