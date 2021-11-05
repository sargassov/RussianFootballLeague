package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

import java.io.IOException;
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

        Object[] compareObj = {
                rfpl.myTeam.wealth, allCostAmount(), stadiumRevenue(), rfpl.myTeam.sponsor.name,
                rfpl.myTeam.sponsor.dayWage, rfpl.myTeam.sponsor.matchWage, rfpl.myTeam.sponsor.goalBonusWage,
                rfpl.myTeam.sponsor.winWage, rfpl.myTeam.sponsor.deuceWage, proficitDeficit()};

        for (int i = 0, j = 0; i < revenuetableList.size(); i++) {

            if(i > 1 && j < compareObj.length){
                String[] mass = revenuetableList.get(i).split("/");
                String techString;

                if(j != 3){
                    double techDouble = (double)compareObj[j];
                    techString = Double.toString(Corrector.coefficient(techDouble));
                }
                else { techString = (String) compareObj[j]; }

                mass[3] = Corrector.wordToCenter(techString, LINELENGTH10);
                revenuetableList.set(i, massInLine(mass));
                j++;
            }
            System.out.println(revenuetableList.get(i));
        }

        System.out.print(REVENUE_AND_EXPENSES_MENU);
    }

    private double allCostAmount(){
        double allCost = 0.0;
        for(Player player : rfpl.myTeam.playerList) allCost += player.price;
        allCost *= 100;
        int temp = (int)allCost;
        return (double) temp / 100;
    }

    private double stadiumRevenue(){
        double regCap = rfpl.myTeam.regularCapacity;
        double tempCost = rfpl.myTeam.temporaryTicketCost;
        double result = regCap * tempCost / 1_000_000;
        result *= 100;
        int temp = (int)result;
        return (double) temp / 100;
    }

    private double proficitDeficit(){
        return rfpl.myTeam.wealth - rfpl.myTeam.startWealth;
    }

}
