package FootballManager.finance;

public class Sponsor {
    public String name;
    public double dayWage;
    public double matchWage;
    public double goalBonusWage;
    public double winWage;
    public double deuceWage;
    public double contractBonusWage;

    public Sponsor(String line) {
        String[] lineMass = line.split("/");
        name = lineMass[0];
        System.out.println(name);
        dayWage = Double.parseDouble(lineMass[1]);
        matchWage = Double.parseDouble(lineMass[2]);
        goalBonusWage = Double.parseDouble(lineMass[3]);
        winWage = Double.parseDouble(lineMass[4]);
        deuceWage = Double.parseDouble(lineMass[5]);
        contractBonusWage = Double.parseDouble(lineMass[6]);
    }

}
