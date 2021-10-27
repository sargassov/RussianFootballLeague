package FootballManager.Tables;


import FootballManager.finance.Bank;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class LoansTable extends Table implements Data{

    private final static int HEIGHT = 23;
    private final static int WIDTH = 93;
    private Tournament rfpl;
    private final String loansTablePath = "src/textFiles/loansTable.txt";
    private final static String LOANS_FILE_NOT_FOUND = "LOANS_FILE_NOT_FOUND";
    private List<String> loansTableList;

    public LoansTable(){
        Path path = Paths.get(loansTablePath);
        if(Files.exists(path)){
            try {
                loansTableList = Files.readAllLines(Paths.get(loansTablePath), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println(LOANS_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }


    @Override
    public void toPrint(Tournament rfpl) {
        this.rfpl = rfpl;

        for(int loansVal = 0, tableVal = 0; loansVal < rfpl.myTeam.loans.size(); tableVal++){

            if(tableVal > 1){
                String[] mass = loansTableList.get(tableVal).split("/");
                Bank bank = rfpl.myTeam.loans.get(loansVal);

                String[] commandArr = {
                        "" + bank.getTookMoney(),
                        typeOfReturnToString(bank.getTypeOfReturn()),
                        dateToString(bank.getDateOfLoan()),
                        "" + bank.getAlreadyPaid(),
                        remainsToPay(bank),
                        dateToString(bank.getRemainsDate())
                };

                mass[2] = Corrector.wordToCenter(bank.getName(), LINELENGTH27);
                for(int z = 3, a = 0; z < mass.length; z++, a++){
                    String temp = commandArr[a];
                    if(z == 5 || z == 8){
                        mass[z] = Corrector.wordToCenter(temp, LINELENGTH10);
                    }
                    else if(z == 6 || z == 7){
                        mass[z] = Corrector.wordToCenter(temp.substring(0, temp.indexOf('.') + 2), LINELENGTH5);
                    }
                    else{
                        mass[z] = Corrector.wordToCenter(temp, LINELENGTH5);
                    }

                }
                String temp = "";
                for(String s : mass){
                    temp +=(s +"|");
                }
                loansTableList.set(tableVal, temp);
                loansVal++;
            }

            System.out.println(loansTableList.get(tableVal));
        }
        System.out.println(loansTableList.get(loansTableList.size() - 1) + "\n\n");
    }

    private String remainsToPay(Bank bank) {

        String remains = "" + (bank.getRemainMoney() - bank.getAlreadyPaid());
        return  remains;
    }

    private String dateToString(GregorianCalendar dateOfLoan) {
        String stringDate = "";
        stringDate += dateOfLoan.get(Calendar.DAY_OF_MONTH);
        stringDate += ("." + (dateOfLoan.get(Calendar.MONTH) + 1));
        stringDate += ("." + dateOfLoan.get(Calendar.YEAR));

        return stringDate;
    }

    private String typeOfReturnToString(Bank.TypeOfReturn typeOfReturn){
        if(typeOfReturn.equals(Bank.TypeOfReturn.PER_DAY)) return " Day ";
        else if(typeOfReturn.equals(Bank.TypeOfReturn.PER_WEEK)) return "Week ";
        else return "Month";
    }
}
