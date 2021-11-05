package FootballManager.GameMenuInterfaces;


import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.util.List;

public class LeagueMenuInterface implements GameMenuInterface {

    @Override
    public void Do(Tournament rfpl) {
        while(true){
            toPrintMenu(rfpl.interfaces.get(8).fields);
            int choise = Corrector.inputIntMethod(0, 2);
            rfpl.leagueMenuOptionsInterfaces.get(choise).getOption(rfpl);
        }
    }

    @Override
    public void toPrintMenu(List<String> fields) {
        for (String string : fields) {
            System.out.println(string);
        }
    }
}
