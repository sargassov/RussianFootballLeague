package FootballManager.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stadium {

    private String title;
    private Integer fullCapacity;
    private Integer vipCapacity;
    private Integer vipTicketCost;
    private Integer familyCapacity;
    private Integer familyTicketCost;
    private Integer fanCapacity;
    private Integer fanTicketCost;
    private Integer simpleCapacity;
    private Integer simpleTicketCost;
    private Integer awayCapacity;
    private Integer awayTicketCost;
    private Integer usualAverageCapacity;
    private double stadiumExpenses;
    List<Integer> tempCapacityArr;
    List<Integer> tempCostArr;

    public Stadium(String title, int fullCapacity){
        this.fullCapacity = fullCapacity;
        this.title = title;
        stadiumExpenses = 0.0;

        usualAverageCapacity = 10000;

        simpleCapacity = usualAverageCapacity;
        vipCapacity = 0;
        fanCapacity = 0;
        familyCapacity = 0;
        awayCapacity = 0;

        simpleTicketCost = 40;
        vipTicketCost = 0;
        fanTicketCost = 0;
        awayTicketCost = 0;
        familyTicketCost = 0;

        tempCapacityArr = new ArrayList<>(Arrays.asList(simpleCapacity, familyCapacity,
                fanCapacity, awayCapacity, vipCapacity));
        tempCostArr = new ArrayList<>(Arrays.asList(simpleTicketCost, familyTicketCost,
                fanTicketCost, awayTicketCost, vipTicketCost));

    }

    public void setSimpleTicketCost(Integer simpleTicketCost) {
        this.simpleTicketCost = simpleTicketCost;
    }

    public void setFamilyTicketCost(Integer familyTicketCost) {
        this.familyTicketCost = familyTicketCost;
    }

    public void setFanTicketCost(Integer fanTicketCost) {
        this.fanTicketCost = fanTicketCost;
    }

    public void setAwayTicketCost(Integer awayTicketCost) {
        this.awayTicketCost = awayTicketCost;
    }

    public void setSimpleCapacity(Integer simpleCapacity) { this.simpleCapacity = simpleCapacity; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVipTicketCost(int vipTicketCost) {
        this.vipTicketCost = vipTicketCost;
    }

    public void setVipCapacity(int vipCapacity) {
        this.vipCapacity = vipCapacity;
    }

    public void setUsualAverageCapacity(int usualAverageCapacity) {
        this.usualAverageCapacity = usualAverageCapacity;
    }

    public void setFullCapacity(int fullCapacity) {
        this.fullCapacity = fullCapacity;
    }

    public void setFanCapacity(int fanCapacity) {
        this.fanCapacity = fanCapacity;
    }

    public void setFamilyCapacity(int familyCapacity) {
        this.familyCapacity = familyCapacity;
    }

    public void setAwayCapacity(int awayCapacity) {
        this.awayCapacity = awayCapacity;
    }

    public int getVipTicketCost() {
        return vipTicketCost;
    }

    public int getUsualAverageCapacity() {
        return usualAverageCapacity;
    }

    public int getSimpleTicketCost() {
        return simpleTicketCost;
    }

    public int getFanTicketCost() {
        return fanTicketCost;
    }

    public int getFamilyTicketCost() {
        return familyTicketCost;
    }

    public int getAwayTicketCost() {
        return awayTicketCost;
    }

    public int getFullCapacity() {
        return fullCapacity;
    }

    public double getStadiumExpenses() {
        return stadiumExpenses;
    }

    public int getAwayCapacity() {
        return awayCapacity;
    }

    public int getFamilyCapacity() {
        return familyCapacity;
    }

    public int getFanCapacity() {
        return fanCapacity;
    }

    public int getSimpleCapacity() {
        return simpleCapacity;
    }

    public int getVipCapacity() {
        return vipCapacity;
    }

    public String getTitle() {
        return title;
    }

    public void setStadiumExpenses(double stadiumExpenses) {
        this.stadiumExpenses = stadiumExpenses;
    }

    public double matchTicketRevenueAmount() {
        double ticketAmount = 0.0;

        for(int i = 0; i < tempCapacityArr.size(); i++){
            ticketAmount += tempCapacityArr.get(i) * tempCostArr.get(i);
        }

        return ticketAmount / 1000000;
    }

    public int getFullSectorCapacity() {

        int fullSectorCapacity = 0;

        for(Integer integer : tempCapacityArr){
            fullSectorCapacity += integer;
        }

        return fullSectorCapacity;
    }

    public int getFullNotSectorCapacity() {

        return fullCapacity - getFullSectorCapacity();
    }
}
