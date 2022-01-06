package FootballManager.FinanceMenuOptions;


import FootballManager.Tables.ExpensesTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

public class ExpensesOption implements FinanceMenuOptionsInterface {

    private static final int sVal = 40;

    @Override
    public void getOption(Tournament rfpl) {

        System.out.print("\n\n\n\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase("expenses mode " + rfpl.myTeam.name);
        System.out.println("\n\n");

        new ExpensesTable().toPrint(rfpl);

        int choise = Corrector.inputIntMethod(0, 2);
        if(choise == 1) new RevenueOption().tryToChangeSponsor();
        else if(choise == 2) new BanksOption();

    }
}
