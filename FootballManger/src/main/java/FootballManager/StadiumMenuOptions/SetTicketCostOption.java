package FootballManager.StadiumMenuOptions;


import FootballManager.Tables.SetTicketCostTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Stadium;
import FootballManager.manager.Tournament;

public class SetTicketCostOption implements StadiumMenuOptionInterface{

    private static Tournament rfpl;
    private static int sVal = 40;

    @Override
    public void getOption(Tournament rfpl) {

        SetTicketCostOption.rfpl = rfpl;
        System.out.print("\n\n\n\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase("set ticket mode " + rfpl.myTeam.name);
        System.out.println("\n\n\n");

        new SetTicketCostTable().toPrint(rfpl);

        System.out.print("\n\n" + Corrector.getS(sVal) + "MENU:\n" +
                Corrector.getS(sVal) + "To set a new ticket cost press \"1\":\n" +
                Corrector.getS(sVal) + "To return press \"0\":");

        int choise = Corrector.inputIntMethod(0, 1);
        if(choise == 1) {
            setTicketCost();
            new SetTicketCostOption().getOption(rfpl);
        }
    }

    private void setTicketCost() {

        Stadium stadium = rfpl.myTeam.stadium;

        System.out.print("\n\n" +
                Corrector.getS(sVal) + "To set simple ticket cost press \"1\":\n" +
                Corrector.getS(sVal) + "To set family ticket cost press \"2\":\n" +
                Corrector.getS(sVal) + "To set fan ticket cost press \"3\":\n" +
                Corrector.getS(sVal) + "To set away ticket cost press \"4\":\n" +
                Corrector.getS(sVal) + "To set VIP ticket cost press \"5\":\n" +
                Corrector.getS(sVal) + "To return press \"0\":");

        int choise = Corrector.inputIntMethod(0, 5);
        if(choise != 0) {
            System.out.print("\n\n" + Corrector.getS(sVal) + "Write new cost:\n");
            int cost = Corrector.inputIntMethod(1, 10000);
            setChoise(stadium, choise, cost);
        }

    }

    private void setChoise(Stadium stadium, int choise, int cost) {
        if(choise == 1) stadium.setSimpleTicketCost(cost);
        if(choise == 2) stadium.setFamilyTicketCost(cost);
        if(choise == 3) stadium.setFanTicketCost(cost);
        if(choise == 4) stadium.setAwayTicketCost(cost);
        if(choise == 5) stadium.setVipTicketCost(cost);

    }
}