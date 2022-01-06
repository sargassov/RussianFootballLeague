package FootballManager.Tables;


import FootballManager.finance.Bank;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;

public class ExpensesTable extends Table implements Data{

    private static Tournament rfpl;
    private final String expensesTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\expensesTable.txt";
    private final static String EXPENSES_FILE_NOT_FOUND = "BANKS_FILE_NOT_FOUND";
    private List<String> expensesTableList;
    private final int delimeter = 100;

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

        BigDecimal[] compareArr = {
                BigDecimal.valueOf(getExpense(1) / 10000, 2),
                BigDecimal.valueOf(getExpense(2) / 10000, 2),
                BigDecimal.valueOf(getExpense(3) / 10000, 2),
                BigDecimal.valueOf(getTransferExpense() / 10000, 2),
                BigDecimal.valueOf(getPersonalExpense() / 10000, 2),
                BigDecimal.valueOf(getStadiumExpense() / 10000, 2),
                BigDecimal.valueOf(getMarketExpenses() / 10000, 2),
                BigDecimal.valueOf(getProficitDeficit() / 10000, 2)
        };


        for (int i = 0, j = 0; i < expensesTableList.size(); i++) {

            if(i > 1 && j < compareArr.length){
                String[] mass = expensesTableList.get(i).split("/");
                mass[3] = Corrector.wordToCenter("" + compareArr[j], mass[3].length());
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

    private long getProficitDeficit() {
        return rfpl.myTeam.wealth - rfpl.myTeam.startWealth;
    }

    private long getStadiumExpense() {
        return rfpl.myTeam.stadiumExpenses;
    }

    private long getExpense(int coeff){

        int expense = 0;

        for(Bank bank : rfpl.myTeam.loans){
            if(coeff == 1 && bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_DAY))
                expense -= bank.getPayPerDay();
            else if(coeff == 2 && bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_WEEK))
                expense -= bank.getPayPerWeek();
            else if(coeff == 3 && bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_MONTH))
                expense -= bank.getPayPerMonth();
        }

        return (long)expense;
    }

    private long getTransferExpense(){
        return rfpl.myTeam.transferExpenses;
    }

    private long getPersonalExpense(){
        return rfpl.myTeam.personalExpenses;
    }

    private long getMarketExpenses() {return rfpl.myTeam.marketExpenses;}
}
