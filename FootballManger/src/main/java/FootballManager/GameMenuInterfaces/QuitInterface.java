package FootballManager.GameMenuInterfaces;


import FootballManager.manager.Tournament;

import java.util.List;

public class QuitInterface implements GameMenuInterface {



    @Override
    public void Do(Tournament rfpl) {
        System.out.println("QUIT");

    }

    @Override
    public void toPrintMenu(List<String> fields) {

    }
}
