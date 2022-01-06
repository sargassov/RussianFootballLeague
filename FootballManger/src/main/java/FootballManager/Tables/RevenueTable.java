package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RevenueTable extends Table implements Data {

    private final static int HEIGHT = 23;
    private final static int WIDTH = 93;
    private Tournament rfpl;
    private static final String revenueTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\revenueTable.txt";
    private List<String> revenuetableList;
    private final static String REVENUE_FILE_NOT_FOUND = "LOANS_FILE_NOT_FOUND";

    private final String[] commandArr = {budget, allCost, stadiumWage, sponsor, dayWage, matchWage, goalBonus,
            winBonus, drawBonus, profit};

    public RevenueTable(){
        Path path = Paths.get(revenueTablePath);

        if(Files.exists(path)){
            try {
                revenuetableList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(REVENUE_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }

    }

    @Override
    public void toPrint(Tournament rfpl) {
        this.rfpl = rfpl;

//        BigDecimal[] compareArr = {
//                BigDecimal.valueOf(getExpense(1) / 10000, 2),
//                BigDecimal.valueOf(getExpense(2) / 10000, 2),
//                BigDecimal.valueOf(getExpense(3) / 10000, 2),
//                BigDecimal.valueOf(getTransferExpense() / 10000, 2),
//                BigDecimal.valueOf(getPersonalExpense() / 10000, 2),
//                BigDecimal.valueOf(getStadiumExpense() / 10000, 2),
//                BigDecimal.valueOf(getMarketExpenses() / 10000, 2),
//                BigDecimal.valueOf(getProficitDeficit() / 10000, 2)
//        };

        Object[] compareObj = {
                BigDecimal.valueOf(rfpl.myTeam.wealth / 10000, 2),
                BigDecimal.valueOf(allCostAmount() / 10000, 2),
                BigDecimal.valueOf(stadiumRevenue() / 10000, 2),
                rfpl.myTeam.sponsor.getName(),
                BigDecimal.valueOf(rfpl.myTeam.sponsor.getDayWage() / 10000, 2),
                BigDecimal.valueOf(rfpl.myTeam.sponsor.getMatchWage() / 10000, 2),
                BigDecimal.valueOf(rfpl.myTeam.sponsor.getGoalBonusWage() / 10000, 2),
                BigDecimal.valueOf(rfpl.myTeam.sponsor.getWinWage() / 10000, 2),
                BigDecimal.valueOf(rfpl.myTeam.sponsor.getDeuceWage() / 10000, 2),
                BigDecimal.valueOf(proficitDeficit() / 10000, 2)};

        for (int i = 0, j = 0; i < revenuetableList.size(); i++) {

            if(i > 1 && j < compareObj.length){
                String[] mass = revenuetableList.get(i).split("/");
//                String techString;
//
//                if(j == 0 || j == 9) {
//                    long techLong = (long)compareObj[j];
//                    techString = "" + Corrector.priceInMillion(techLong);
//                }
//
//                else if(j != 3){
//                    int techInt = (int)compareObj[j];
//                    techString = "" + Corrector.priceInMillion(techInt);
//                }
//                else { techString = (String) compareObj[j]; }

                mass[3] = Corrector.wordToCenter(compareObj[j].toString(), mass[3].length());
                revenuetableList.set(i, massInLine(mass));
                j++;
            }
            System.out.println(revenuetableList.get(i));
        }

        System.out.print(REVENUE_AND_EXPENSES_MENU);
    }

    private long allCostAmount(){
        int allCost = 0;
        for(Player player : rfpl.myTeam.playerList) allCost += player.price;
        return allCost;
    }

    private long stadiumRevenue(){
        return rfpl.myTeam.stadium.allTicketCost();
    }

    private long proficitDeficit(){
        return rfpl.myTeam.wealth - rfpl.myTeam.startWealth;
    }

}
