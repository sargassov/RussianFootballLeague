package FootballManager.TrainingMenuOptions;

import FootballManager.Tables.TrainingBalanceTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

public class TrainingBalanceOption implements TrainingMenuOptionsInterface{

    private final static int sVal = 40;

    @Override
    public void getOption(Tournament rfpl) {
        new TrainingBalanceTable().toPrint(rfpl);

        System.out.print("\n" + Corrector.getS(sVal));
        System.out.println("MENU:\n\n" +
                Corrector.getS(sVal) + "==>0. To Quit");

        int select = Corrector.inputIntMethod(0, 0);
    }
}
