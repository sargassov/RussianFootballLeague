package FootballManager.LeagueMenuOptions;

import FootballManager.Tables.LeagueTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

public class LeagueTableOption implements LeagueMenuOptionsInterface{

    private static int sVal = 40;
    private static Tournament rfpl;
    private static String name = "R U S S A I N   F O O T B A L L   L E A G U E";

    @Override
    public void getOption(Tournament rfpl) {
        LeagueTableOption.rfpl = rfpl;
        System.out.println(Corrector.getS(sVal) + name + "\n\n");

        new LeagueTable().toPrint(rfpl);

        System.out.println(Corrector.getS(sVal) + "MENU:\n" +
                Corrector.getS(sVal) + "To quit press \"0\":");

        int choise = Corrector.inputIntMethod(0, 0);
        if(choise == 0) return;

    }
}
