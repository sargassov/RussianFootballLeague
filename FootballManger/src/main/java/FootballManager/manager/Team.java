package FootballManager.manager;


import FootballManager.coaches.Coach;
import FootballManager.coaches.Manager;
import FootballManager.finance.Bank;
import FootballManager.finance.Sponsor;
import FootballManager.markets.Market;
import FootballManager.strategies.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
    public String name;
    public String town;
    public Stadium stadium;
    public int games = 0;
    public int wins = 0;
    public int draws = 0;
    public int defeats = 0;
    public int goalScored = 0;
    public int goalMissed = 0;
    public int teamPower = 0;
    public int capacityStad = 0;
    public int temporaryTicketCost = 60;
    public int regularCapacity;
    public ArrayList<Coach> coaches = new ArrayList<Coach>();
    public ArrayList<Bank>loans = new ArrayList<>();
    public long wealth;
    public final long startWealth;
    public long transferExpenses;
    public long personalExpenses;
    public long marketExpenses;
    public long stadiumExpenses;
    public ArrayList<Player> playerList = new ArrayList<Player>();
    public Strategy strategy = new Strategy();
    public ArrayList<String> coachInterface;
    public Sponsor sponsor;
    public int maxValueOfLoans = 5;
    public boolean changeSponsor = false;
    public Tournament rfpl;
    private Random random;
    public List<Market> markets;

    public Team(String info, Tournament rfpl) {

        this.rfpl = rfpl;
        String[] StringMass = info.split("/");

        name = StringMass[0];
        town = StringMass[1];
        stadium = new Stadium(StringMass[2], Integer.parseInt(StringMass[4]));

        Manager manager = new Manager(StringMass[3]);
        if(coaches.size() == 0) {coaches.add(manager);}

        wealth = Integer.parseInt(StringMass[5]) * 1_000_000;
        startWealth = wealth;

//        transferExpenses = 0.0;
//        personalExpenses = 0.0;
//        marketExpenses = 0.0;
        markets = new ArrayList<>();

        addToSponsor();

    }

    private void addToSponsor() {

        random = new Random();
        sponsor = rfpl.sponsorList.get(random.nextInt(16));
        wealth += sponsor.getContractBonusWage();
        regularCapacity = capacityStad / 4;
        System.out.println(name + " " + sponsor.getName());
    }

    public void breakSponsorContract(){
        wealth -= sponsor.getContractBonusWage();
        System.out.println("\n" + Corrector.getS(35) + "Contract with " + sponsor.getName() + " was broken!\n" +
                Corrector.getS(35) + sponsor.getContractBonusWage() + " gived away from team budget!");
        sponsor = null;

    }

    public void offerSponsorContract(Sponsor sponsor) {
        this.sponsor = sponsor;
        wealth += sponsor.getContractBonusWage();
        System.out.println("\n" + Corrector.getS(30) + "Contract with " + sponsor.getName() + " was offered!\n" +
                Corrector.getS(30) + sponsor.getContractBonusWage() + " came into team budget!");
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

    public int getPoints(){
        return wins * 3 + draws;
    }
}
