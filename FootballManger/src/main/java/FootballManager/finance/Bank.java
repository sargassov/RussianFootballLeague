package FootballManager.finance;


import FootballManager.manager.Tournament;

import java.util.GregorianCalendar;

public class Bank {
    private static Tournament rfpl;
    private final String name;
    private final double percentValueDay;
    private final double percentValueWeek;
    private final double percentValueMonth;
    private double fullLoanAmountValue;
    private int tookMoney;
    private double remainMoney;
    private double alreadyPaid = 0.0;
    private final double maxLoanValue;
    public enum TypeOfReturn {PER_DAY, PER_WEEK, PER_MONTH};
    private TypeOfReturn typeOfReturn;
    private GregorianCalendar dateOfLoan;
    private GregorianCalendar remainsDate;


    public Bank(String info){
        String[] mass = info.split("/");
        this.name = mass[0];
        this.percentValueDay = Double.parseDouble(mass[1]);
        this.percentValueWeek = Double.parseDouble(mass[2]);
        this.percentValueMonth = Double.parseDouble(mass[3]);
        this.fullLoanAmountValue = Double.parseDouble(mass[4]);
        this.maxLoanValue = Double.parseDouble(mass[5]);
        System.out.println("BANKNAME = " + name);

    }

    public static void setRfpl(Tournament rfpl) {
        Bank.rfpl = rfpl;
    }

    public double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        this.remainMoney = remainMoney;
    }

    public void setTookMoney(int tookMoney) {
        this.tookMoney = tookMoney;
    }

    public int getTookMoney() {
        return tookMoney;
    }

    public void setRemainsDate(GregorianCalendar remainsDate) { this.remainsDate = remainsDate; }

    public GregorianCalendar getRemainsDate() { return remainsDate; }

    public GregorianCalendar getDateOfLoan() {
        return dateOfLoan;
    }

    public void setTypeOfReturn(TypeOfReturn typeOfReturn) { this.typeOfReturn = typeOfReturn; }

    public void setDateOfLoan(GregorianCalendar dateOfLoan) { this.dateOfLoan = dateOfLoan; }

    public TypeOfReturn getTypeOfReturn() { return typeOfReturn; }

    public double getAlreadyPaid() { return alreadyPaid; }

    public double getFullVal() {
        return fullLoanAmountValue;
    }

    public double getMaxLoan() {
        return maxLoanValue;
    }

    public double getPerDay() {
        return percentValueDay;
    }

    public double getPerMon() {
        return percentValueMonth;
    }

    public double getPerWeek() {
        return percentValueWeek;
    }

    public String getName() {
        return name;
    }

    public void returnLoan(){
        double amountToReturn = remainMoney - alreadyPaid;
        rfpl.myTeam.wealth -= amountToReturn;
        tookMoney = 0;
        remainMoney = 0.0;
        alreadyPaid = 0.0;
        typeOfReturn = null;
        dateOfLoan = null;
        remainsDate = null;
        rfpl.myTeam.loans.remove(this);
        rfpl.banks.add(this);
    }
}
