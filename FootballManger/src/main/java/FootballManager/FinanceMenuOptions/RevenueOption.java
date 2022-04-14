package FootballManager.FinanceMenuOptions;


import FootballManager.Tables.RevenueTable;
import FootballManager.Tables.SponsorsTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

public class RevenueOption implements FinanceMenuOptionsInterface {

    private static final int sVal = 40;
    private static Tournament rfpl;

    @Override
    public void getOption(Tournament rfpl) {
        RevenueOption.rfpl = rfpl;
        System.out.print("\n\n\n\n\n\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase("Revenue mode " + rfpl.myTeam.name);
        System.out.println("\n");

        new RevenueTable().toPrint(rfpl);

        int choise = Corrector.inputIntMethod(0, 2);

        if(choise == 1) tryToChangeSponsor();
        else if(choise == 2) new BanksOption().getOption(rfpl);


    }

    public static void setRfpl(Tournament rfpl) {
        RevenueOption.rfpl = rfpl;
    }

    public void tryToChangeSponsor() {
        if(rfpl.myTeam.changeSponsor){
            System.out.println("\n\n\n" + Corrector.getS(sVal) + "You can't change sponsor again!\n\n\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        chooseNewSponsor();
    }

    private void chooseNewSponsor(){
        System.out.print("\n\n\n\n\n\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase("Change sponsor mode ");
        System.out.println("\n");
        new SponsorsTable().toPrint(rfpl);
        System.out.print(Corrector.getS(sVal) + "You can change sponsor one time of a game.\n" +
                Corrector.getS(sVal) + "!!Then you must give away contract bonus wage (ConW.)!!\n\n\n" +
                Corrector.getS(sVal) + "If you want to change sponsor press number of it\n" +
                Corrector.getS(sVal) + "or press \"0\" to quit: ");

        int choise2 = Corrector.inputIntMethod(0, rfpl.sponsorList.size());
        if(choise2 == 0) return;

        choise2 -= 1;
        rfpl.myTeam.breakSponsorContract();
        rfpl.myTeam.offerSponsorContract(rfpl.sponsorList.get(choise2));
        rfpl.myTeam.changeSponsor = true;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
