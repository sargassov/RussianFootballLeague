package FootballManager.time;

import FootballManager.NewDayOptions.FootballMatchOption;
import FootballManager.NewDayOptions.MatchSimulator;
import FootballManager.coaches.Coach;
import FootballManager.coaches.CoachProgram;
import FootballManager.coaches.LevelOfCoach;
import FootballManager.finance.Bank;
import FootballManager.finance.Sponsor;
import FootballManager.manager.*;
import FootballManager.markets.Market;

import java.util.*;

public class DayChanger {

    private static Tournament rfpl;
    private static List<String> noteOfChanges;
    private static boolean matchDay;

    public static void toChangeDay(){

        isDayMatch();
        if(!FootballMatchOption.continueFlag) return;
        noteOfChanges = new ArrayList<>();
        addDate();
        openYouthAcademy();
        setTrainingEffects();
        setFinanceUpdates();
        setMarketingChanges();
        //setCoachSalary();

        for(String s: noteOfChanges){
            System.out.println(s);
        }


    }

    private static void setMarketingChanges() {
        for(int x = 0; x < rfpl.myTeam.markets.size(); x++){
            Stadium stadium = rfpl.myTeam.stadium;
            int capacity = stadium.getUsualAverageCapacity();
            System.out.println("markets size = " + rfpl.myTeam.markets.size());
            int newFansValue = (int) (capacity + capacity / 100 * rfpl.myTeam.markets.get(x).getCapacityCoeff());
            stadium.setUsualAverageCapacity(newFansValue);
            stadium.setSimpleCapacity(newFansValue);
            noteOfChanges.add("New Stadium capacity is " + stadium.getUsualAverageCapacity());
            if(!isActual(rfpl.myTeam.markets.get(x))){
                rfpl.myTeam.markets.remove(rfpl.myTeam.markets.get(x));
                System.out.println("MARKET REMOVE");
                System.out.println("markets size = " + rfpl.myTeam.markets.size());
                x--;
            }
        }

    }

    private static boolean isActual(Market m) {
        GregorianCalendar today = (GregorianCalendar) rfpl.currentDate;
        GregorianCalendar endOfAction = m.getFinishDate();

        if(today.get(Calendar.DAY_OF_MONTH) == endOfAction.get(Calendar.DAY_OF_MONTH)
        && today.get(Calendar.MONTH) == endOfAction.get(Calendar.MONTH)
        && today.get(Calendar.YEAR) == endOfAction.get(Calendar.YEAR)) return false;

        System.out.println(today.get(Calendar.DAY_OF_MONTH) + "." + today.get(Calendar.MONTH) + "." + today.get(Calendar.YEAR));
        System.out.println(endOfAction.get(Calendar.DAY_OF_MONTH) + "." + endOfAction.get(Calendar.MONTH) + "." + endOfAction.get(Calendar.YEAR));

        return true;
    }

    private static void setCoachSalary() {

    }

    private static void setFinanceUpdates() {
        Team myTeam = rfpl.myTeam;
        List<Bank> loans = myTeam.loans;
        Sponsor sponsor = myTeam.sponsor;


        myTeam.wealth += sponsor.getDayWage();
        noteOfChanges.add("Sponsor " + sponsor.getName() + " gave your team " + sponsor.getDayWage() + " Euro. It is day wage.");
        if(rfpl.currentDay instanceof DayMatch){
            myTeam.wealth += sponsor.getMatchWage();
            noteOfChanges.add("Sponsor " + sponsor.getName() + " gave your team " + sponsor.getMatchWage() + " Euro. It is match wage.");
        }

        myTeam.wealth -= expenses(loans);

    }

    private static long expenses(List<Bank> loans) {
        long expenses = 0;

        for(int x = 0; x < loans.size(); x++){
            if(loans.get(x).getTypeOfReturn().equals(Bank.TypeOfReturn.PER_DAY)) {

                if(loans.get(x).getRemainMoney() < loans.get(x).getPayPerDay()){
                    lastPayment(loans.get(x));
                    x--;
                    continue;
                }

                expenses += loans.get(x).getPayPerDay();
                loans.get(x).setAlreadyPaid(loans.get(x).getAlreadyPaid() + loans.get(x).getPayPerDay());
                loans.get(x).setRemainMoney(loans.get(x).getRemainMoney() - loans.get(x).getPayPerDay());
                noteOfChanges.add(loans.get(x).getPayPerDay() + " Euro was paid to the Bank " + loans.get(x).getName() + ". Daily payment");
            }
            else if(loans.get(x).getTypeOfReturn().equals(Bank.TypeOfReturn.PER_WEEK)
                    && rfpl.currentDay.date.get(Calendar.DAY_OF_WEEK) ==
                    loans.get(x).getDateOfLoan().get(Calendar.DAY_OF_WEEK)) {

                if(loans.get(x).getRemainMoney() < loans.get(x).getPayPerWeek()){
                    lastPayment(loans.get(x));
                    x--;
                    continue;
                }

                expenses += (loans.get(x).getPayPerWeek());
                loans.get(x).setAlreadyPaid(loans.get(x).getAlreadyPaid() + loans.get(x).getPayPerWeek());
                loans.get(x).setRemainMoney(loans.get(x).getRemainMoney() - loans.get(x).getPayPerWeek());
                noteOfChanges.add(loans.get(x).getPayPerWeek() + " Euro was paid to the Bank " + loans.get(x).getName() + ". Weekly payment");
            }
            else if(loans.get(x).getTypeOfReturn().equals(Bank.TypeOfReturn.PER_MONTH)
                    && rfpl.currentDay.date.get(Calendar.DAY_OF_MONTH) ==
                    loans.get(x).getDateOfLoan().get(Calendar.DAY_OF_MONTH)) {

                if(loans.get(x).getRemainMoney() < loans.get(x).getPayPerMonth()){
                    lastPayment(loans.get(x));
                    x--;
                    continue;
                }

                expenses += loans.get(x).getPayPerMonth();
                loans.get(x).setAlreadyPaid(loans.get(x).getAlreadyPaid() + loans.get(x).getPayPerMonth());
                loans.get(x).setRemainMoney(loans.get(x).getRemainMoney() - loans.get(x).getPayPerMonth());
                noteOfChanges.add(loans.get(x).getPayPerMonth() + " Euro was paid to the Bank " + loans.get(x).getName() + ". Monthly payment");
            }
        }
        return expenses;
    }

    private static void lastPayment(Bank bank) {

        rfpl.myTeam.wealth -= bank.getRemainMoney();
        bank.setRemainMoney(0);
        bank.returnLoan();
    }

//    private static int perMonthExpenses(Bank bank) {
//        if(rfpl.currentDay.date.get(Calendar.DAY_OF_MONTH) ==
//                bank.getDateOfLoan().get(Calendar.DAY_OF_MONTH)){
//            int monthlyPay = (int) (bank.getFullVal() - bank.getPercentMon() * bank.getFullVal() / 100);
//            //bank.setAlreadyPaid(bank.getAlreadyPaid() + monthlyPay);
//            bank.setRemainMoney((int) bank.getRemainMoney() - monthlyPay);
//            noteOfChanges.add(monthlyPay + "M Euro was paid to the Bank " + bank.getName());
//            return bank.getPercentMon();
//        }
//        return 0;
//    }


    private static void setTrainingEffects() {

        noteOfChanges.add("\nYesterday training effects of your opponents:\n");
        for(Team t : rfpl.teams){
            if(t.equals(rfpl.myTeam)) continue;
            else {
                for(int x = 0; x < 5; x++){
                    Player p = t.playerList.get((int) (Math.random() * t.playerList.size()));
                    p.trainingBalance += p.trainingAble * (int) (Math.random() * 5);
                    noteOfChanges.add(p.name + " " + p.team.name + " + " + p.trainingAble);
                    if(p.trainingBalance >= 100)
                        p.setNewPower();
                }
            }
        }

        myTeamTrainingEffects();
    }

    private static void myTeamTrainingEffects() {
        noteOfChanges.add("\nYour team's training effects:\n");
        for(int x = 1; x < rfpl.myTeam.coaches.size(); x++){
            Coach coach = rfpl.myTeam.coaches.get(x);
            double coeff = 1.0;
            if(coach.getPlayerOnTrain() != null){
                if(coach.getPlayerOnTrain().equals(CoachProgram.HARD))
                    coeff *= 2;
                else if(coach.getCurrentCoachProgram().equals(CoachProgram.INTENSIVE))
                    coeff *= 1.5;

                if(coach.getLevelOfCoach().equals(LevelOfCoach.PROFI)) coeff *= 1.5;
                else if(coach.getLevelOfCoach().equals(LevelOfCoach.WORLD)) coeff *= 2;

                coach.getPlayerOnTrain().trainingBalance += (int)(coach.getPlayerOnTrain().trainingAble * coeff);
                noteOfChanges.add("Your player " + coach.getPlayerOnTrain().name + " has upeer by +" + (int)(coach.getPlayerOnTrain().trainingAble * coeff) +
                        " pts.");
                if(coach.getPlayerOnTrain().trainingBalance >= 100)
                    coach.getPlayerOnTrain().setNewPower();
            }

        }
    }


    private static void openYouthAcademy() {
        if(rfpl.wasAtTheYouthAcademy)
            noteOfChanges.add("\nYouth academy now is open again");

        rfpl.wasAtTheYouthAcademy = false;
    }

    private static void addDate() {

        rfpl.currentDay.isToday = false;
        rfpl.currentDay.isPassed = true;
        rfpl.currentDate.add(Calendar.DAY_OF_MONTH, 1);
        noteOfChanges.add("Today is a new day: " + rfpl.currentDate.get(Calendar.DAY_OF_MONTH) + "." +
                Corrector.intInMonth(rfpl.currentDate.get(Calendar.MONTH)) +
                "." + rfpl.currentDate.get(Calendar.YEAR));

        for (ArrayList<Day> month : rfpl.calendar){
            for (Day day: month){
                if(day.date.get(Calendar.DAY_OF_MONTH) == rfpl.currentDate.get(Calendar.DAY_OF_MONTH) &&
                        day.date.get(Calendar.MONTH) == rfpl.currentDate.get(Calendar.MONTH) &&
                        day.date.get(Calendar.YEAR) == rfpl.currentDate.get(Calendar.YEAR)){

                    rfpl.currentDay = day;
                    rfpl.currentDay.isToday = true;

                }
            }
        }
    }

    private static void isDayMatch() {
        if(rfpl.currentDay instanceof DayMatch){
            System.out.println("You Must Play Match");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new FootballMatchOption(rfpl).matchPreview();
        }
    }

    public static void setRfpl(Tournament rfpl) {
        DayChanger.rfpl = rfpl;
    }
}
