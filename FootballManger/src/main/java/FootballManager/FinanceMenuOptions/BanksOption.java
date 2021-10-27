package FootballManager.FinanceMenuOptions;


import FootballManager.Tables.BanksTable;
import FootballManager.Tables.LoansTable;
import FootballManager.finance.Bank;
import FootballManager.manager.Corrector;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BanksOption implements FinanceMenuOptionsInterface{

    private static int sVal = 40;
    private Tournament rfpl;
    private Team myTeam;
    private int coeff;
    private List<Bank> usefulBankList;
    private static final int absoluteMinimumLoan = 10;
    private static final int absoluteMaximimLoan = 85;

    private final static String DO_YOU_WANT_TO_TAKE_A_LOAN = Corrector.getS(sVal) + "Do you want to take a new loan?\n" +
            Corrector.getS(sVal) + "1 - \"Yes\"\n" + Corrector.getS(sVal)+ "0 - \"No: ";

    private final static String TAKE_OR_RETURN_LOAN = Corrector.getS(sVal) + "If you want to remain a loan (Press 2)\n" +
            Corrector.getS(sVal) + "If you want to take a new (Press 1)\n" +
            Corrector.getS(sVal) + "Or (Press 0) to quit: ";



    @Override
    public void getOption(Tournament rfpl) {

        this.rfpl = rfpl;
        myTeam = rfpl.myTeam;
        usefulBankList = new ArrayList<>();
        System.out.println("\n\n\n\n");
        Corrector.wordUpperCase(Corrector.getS(sVal / 2) + "banks menu for " + rfpl.myTeam.name + "\n\n\n");

        if(rfpl.myTeam.loans.size() == 0){
            System.out.println(Corrector.getS(sVal) + "Now " + rfpl.myTeam.name + " haven't any loans\n\n");
            doYouWantToTakeLoan();
        }

        else{
            System.out.println(Corrector.getS(sVal) + rfpl.myTeam.name + " have these loans now:\n");
            new LoansTable().toPrint(rfpl);

            if(rfpl.myTeam.maxValueOfLoans > 0){ doYouWantToTakeLoan();}

            else{ tookTooMuch(); }
        }
    }

    private void howMuchYouWantToTake() {
        System.out.println(Corrector.getS(sVal) + "How many you want to take? (Between 10 and 85)\n\n\n\n");
        coeff = Corrector.inputIntMethod(absoluteMinimumLoan, absoluteMaximimLoan);

        System.out.println(Corrector.getS(sVal) + "Possible loans for " + coeff + "M. Euro :\n\n\n");
        new BanksTable(usefulBankList, coeff).toPrint(rfpl);

        if(usefulBankList.size() > 0){
            takeLoan();
        }
        else{
            System.out.println(Corrector.getS(sVal) + "There are no bank offers to you intrest\n\n\n");
        }
    }

    private void tookTooMuch() {
        System.out.println(Corrector.getS(sVal) + "You took too much loans. You can't take a new!\n" +
                Corrector.getS(sVal) + "Press \"1\" To return the loan: " +
                Corrector.getS(sVal) + "Press \"0\" to Quit: ");
        Corrector.inputIntMethod(0, 1);
    }

    private void doYouWantToTakeLoan() {
        int choise;
        if(rfpl.myTeam.loans.size() == 0){
            System.out.println(DO_YOU_WANT_TO_TAKE_A_LOAN);
            choise = Corrector.inputIntMethod(0, 1);
        }
        else{
            System.out.println(TAKE_OR_RETURN_LOAN);
            choise = Corrector.inputIntMethod(0, 2);
        }

        if(choise == 2){
            loanReverse();
        }
        if(choise == 1) {
            howMuchYouWantToTake();
        }
    }

    private void loanReverse() {
        System.out.println("\n\n" + Corrector.getS(sVal) + "Choose a loan to reverse and press number of it:  " +
                "or press 0 to quit: ");
        int choise = Corrector.inputIntMethod(0, rfpl.myTeam.loans.size());
        if(choise != 0){
            choise--;
            Bank bank = rfpl.myTeam.loans.get(choise);

            if(rfpl.myTeam.wealth >= (bank.getRemainMoney() - bank.getAlreadyPaid())){
                rfpl.myTeam.loans.get(choise).returnLoan();
                System.out.println(Corrector.getS(sVal) + "Now your loan was returned");
            }

            else
                System.out.println(Corrector.getS(sVal) + "Your club havent money to return this loan now!");
        }
    }

    private void takeLoan() {

        System.out.println("\n\n" + Corrector.getS(sVal) + "To take a new bank loan press a number of it, " +
                "or press 0 to quit: ");

        int choise = Corrector.inputIntMethod(0, usefulBankList.size());
        if(choise == 0) return;
        choise -= 1;

        Bank bank = usefulBankList.get(choise);
        myTeam.loans.add(bank);
        rfpl.banks.remove(bank);
        myTeam.maxValueOfLoans--;
        choosenBank(bank);

    }

    private void choosenBank(Bank bank) {
        System.out.println(Corrector.getS(sVal) + "You choose " + myTeam.loans.get(myTeam.loans.size() - 1).getName() +
                " bank.\n" + Corrector.getS(sVal) + "You want to take: " + coeff + "M. Euro");


        System.out.println(Corrector.getS(sVal) + "What type of returning of loan you want to choose\n" +
                Corrector.getS(sVal) + "Everyday for " + bank.getPerDay() + " percent, Press \"1\",\n" +
                Corrector.getS(sVal) + "Once a week for " + bank.getPerWeek() + " percent, Press \"2\",\n" +
                Corrector.getS(sVal) + "Once a month for " + bank.getPerMon() + " percent, Press \"3\": ");

        int chooseForPeriodPay = Corrector.inputIntMethod(1, 3);
        if(chooseForPeriodPay == 1) bank.setTypeOfReturn(Bank.TypeOfReturn.PER_DAY);
        else if(chooseForPeriodPay == 2) bank.setTypeOfReturn(Bank.TypeOfReturn.PER_WEEK);
        else bank.setTypeOfReturn(Bank.TypeOfReturn.PER_MONTH);

        bank.setDateOfLoan((GregorianCalendar) rfpl.currentDate);
        bank.setRemainsDate(guessRemainsDate(bank));
        bank.setTookMoney(coeff);
        bank.setRemainMoney(bank.getFullVal() * bank.getTookMoney());

        System.out.println("\n"+Corrector.getS(sVal) + "Your bank loan is successful. You took " + coeff + " M.Euro");
        myTeam.wealth += coeff;
    }

    private GregorianCalendar guessRemainsDate(Bank bank) {
        int timesBeforeRemain;
        Calendar finishDate = (Calendar) bank.getDateOfLoan().clone();
        if(bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_DAY)){
            timesBeforeRemain = (int)((bank.getFullVal() * 100) / bank.getPerDay());
            finishDate.add(Calendar.DAY_OF_MONTH, timesBeforeRemain);
            return (GregorianCalendar) finishDate;
        }
        else if(bank.getTypeOfReturn().equals(Bank.TypeOfReturn.PER_WEEK)){
            timesBeforeRemain = (int)((bank.getFullVal() * 100) / bank.getPerWeek());
            finishDate.add(Calendar.DAY_OF_MONTH, (timesBeforeRemain * 7));
            return (GregorianCalendar) finishDate;
        }
        else{
            timesBeforeRemain = (int)((bank.getFullVal() * 100) / bank.getPerMon());
            finishDate.add(Calendar.MONTH, timesBeforeRemain);
            return (GregorianCalendar) finishDate;
        }
    }


}
