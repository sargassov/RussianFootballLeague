package FootballManager.LeagueMenuOptions;

import FootballManager.Tables.LeagueTable;
import FootballManager.Tables.ResultTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

public class ResultsTableOption implements LeagueMenuOptionsInterface{

    private static int sVal = 40;
    private static Tournament rfpl;
    private static String name = "R E S U L T  T A B L E";

    @Override
    public void getOption(Tournament rfpl) {
        ResultsTableOption.rfpl = rfpl;
        System.out.println(Corrector.getS(sVal) + name + "\n\n");

        ResultTable.setRfpl(rfpl);
        new ResultTable().toPrint(rfpl);

        System.out.println(Corrector.getS(sVal) + "MENU:\n" +
                Corrector.getS(sVal) + "To quit press \"0\":");

        int choise = Corrector.inputIntMethod(0, 0);
        if(choise == 0) return;
    }
}
