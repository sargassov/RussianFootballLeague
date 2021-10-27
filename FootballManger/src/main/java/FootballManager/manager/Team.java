package FootballManager.manager;


import FootballManager.coaches.Coach;
import FootballManager.coaches.Manager;
import FootballManager.finance.Bank;
import FootballManager.finance.Sponsor;
import FootballManager.strategies.Strategy;

import java.util.ArrayList;
import java.util.Random;

public class Team {
    public String name;
    public String town;
    public Stadium stadium;
    public short games = 0;
    public short wins = 0;
    public short draws = 0;
    public short defeats = 0;
    public short goalScored = 0;
    public short goalMissed = 0;
    public short teamPower = 0;
    public int capacityStad = 0;
    public int temporaryTicketCost = 60;
    public int regularCapacity;
    public ArrayList<Coach> coaches = new ArrayList<Coach>();
    public ArrayList<Bank>loans = new ArrayList<>();
    public double wealth;
    public double startWealth;
    public double transferExpenses;
    public double personalExpenses;
    public ArrayList<Player> playerList = new ArrayList<Player>();
    public Strategy strategy = new Strategy();
    public ArrayList<String> coachInterface;
    public Sponsor sponsor;
    public int maxValueOfLoans = 5;
    public boolean changeSponsor = false;
    public Tournament rfpl;
    private Random random;

    public Team(String info, Tournament rfpl) {

        this.rfpl = rfpl;
        String[] StringMass = info.split("/");

        name = StringMass[0];
        town = StringMass[1];
        stadium = new Stadium(StringMass[2], Integer.parseInt(StringMass[4]));

        Manager manager = new Manager(StringMass[3]);
        if(coaches.size() == 0) {coaches.add(manager);}

        wealth = Double.parseDouble(StringMass[5]);
        startWealth = Double.parseDouble(StringMass[5]);

        transferExpenses = 0.0;
        personalExpenses = 0.0;

        addToSponsor();

    }

    private void addToSponsor() {

        random = new Random();
        sponsor = rfpl.sponsorList.get(random.nextInt(16));
        wealth += sponsor.contractBonusWage;
        regularCapacity = capacityStad / 4;
        System.out.println(name + " " + sponsor.name);
    }

    public void breakSponsorContract(){
        wealth -= sponsor.contractBonusWage;
        System.out.println("\n" + Corrector.getS(35) + "Contract with " + sponsor.name + " was broken!\n" +
                Corrector.getS(35) + sponsor.contractBonusWage + " gived away from team budget!");
        sponsor = null;

    }

    public void offerSponsorContract(Sponsor sponsor) {
        this.sponsor = sponsor;
        wealth += sponsor.contractBonusWage;
        System.out.println("\n" + Corrector.getS(30) + "Contract with " + sponsor.name + " was offered!\n" +
                Corrector.getS(30) + sponsor.contractBonusWage + " came into team budget!");
    }

    public int teamCounter(){
        for (int i = 0; i < rfpl.teams.length; i++) {
            if(name.equals(rfpl.teams[i].name))
                return i;
        }
        return -1;
    }

    public String nameOfTeamInRegister() {

        char[] nameOfTeam = name.toCharArray();
        StringBuilder toReturn = new StringBuilder();
        for(int x = 0; x < nameOfTeam.length; x++){
            toReturn.append(nameOfTeam[x]);
            toReturn.append(' ');
        }

        return new String(toReturn);
    }
}
