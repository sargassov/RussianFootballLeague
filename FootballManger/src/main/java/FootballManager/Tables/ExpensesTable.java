package FootballManager.Tables;


import FootballManager.finance.Bank;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExpensesTable extends Table implements Data{

    private static Tournament rfpl;
    private final String expensesTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\expensesTable.txt";
    private final static String EXPENSES_FILE_NOT_FOUND = "BANKS_FILE_NOT_FOUND";
    private List<String> expensesTableList;
    private final double delimeter = 100.0;

    public ExpensesTable(){

        Path path = Paths.get(expensesTablePath);
        if(Files.exists(path)){
            try {
                expensesTableList = Files.readAllLines(Paths.get(expensesTablePath), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println(EXPENSES_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {

        ExpensesTable.rfpl = rfpl;

        double[] compareArr = {
                getExpense(1),
                getExpense(2),
                getExpense(3),
                Corrector.coefficient(getTransferExpense()),
                Corrector.coefficient(getPersonalExpense()),
                Corrector.coefficient(getStadiumExpense()),
                Corrector.coefficient(getMarketExpenses()),
                Corrector.coefficient(getProficitDeficit())
        };


        for (int i = 0, j = 0; i < expensesTableList.size(); i++) {

            if(i > 1 && j < compareArr.length){
                String[] mass = expensesTableList.get(i).split("/");
                mass[3] = Corrector.wordToCenter("" + compareArr[j], LINELENGTH10);
                expensesTableList.set(i, "");
                for(int x = 0; x < mass.length; x++){
                    expensesTableList.set(i, (expensesTableList.get(i) + (mass[x] + "|")));
                }
                j++;
            }
            System.out.println(expensesTableList.get(i));
        }

        System.out.print(REVENUE_AND_EXPENSES_MENU);
    }

    private double getProficitDeficit() {
        return rfpl.myTeam.wealth - rfpl.myTeam.startWealth;
    }

    private double getStadiumExpense() {
        return rfpl.myTeam.stadiumExpenses;
    }

    private double getExpense(int coeff){

        double expense = 0.0;

        for(Bank bank : rfpl.myTeam.loans){
            if(coeff == 1 && bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_DAY))
                expense -= bank.getPerDay() * bank.getTookMoney() / delimeter;
            else if(coeff == 2 && bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_WEEK))
                expense -= bank.getPerWeek() * bank.getTookMoney() / delimeter;
            else if(coeff == 3 && bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_MONTH))
                expense -= bank.getPerMon() * bank.getTookMoney() / delimeter;
        }

        return expense;
    }

    private double getTransferExpense(){
        return rfpl.myTeam.transferExpenses;
    }

    private double getPersonalExpense(){
        return rfpl.myTeam.personalExpenses;
    }

    private double getMarketExpenses() {return rfpl.myTeam.marketExpenses;}
}
