package FootballManager.Tables;


import FootballManager.manager.Corrector;

public class Table {

    protected static String del = "XXXXXXXXXXXXXXXXXXXXXXXXXXX";
    protected final String sponsor = "Team's Sponsor";
    protected final String dayWage = "Team's Day Wage";
    protected final String matchWage = "Team's Match Wage";
    protected final String goalBonus = "Team's Goal Bonus";
    protected final String winBonus = "Team's Win Bonus";
    protected final String drawBonus = "Team's Draw Bonus";
    protected final String budget = "Total Budget";
    protected final String allCost = "All Players Cost";
    protected final String stadiumWage = "All Stadium Tickets Cost";
    protected final String profit = "Profit/Deficit";
    protected static final int sVal = 40;

    protected static final String REVENUE_AND_EXPENSES_MENU = "\n\n" + Corrector.getS(35) + "MENU:\n" +
            Corrector.getS(sVal) + "To choose a new sponsor contract press \"1\":\n" +
            Corrector.getS(sVal) + "To take a new loan press \"2\":\n" +
            Corrector.getS(sVal) + "To return press \"0\":\n";

    protected String massInLine(String[] mass){

        String returned = "";
        for(String s : mass){
            returned += (s + "|");
        }

        return returned;
    }







































}
