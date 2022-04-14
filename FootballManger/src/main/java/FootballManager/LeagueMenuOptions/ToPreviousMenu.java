package FootballManager.LeagueMenuOptions;

import FootballManager.manager.MenuClass;
import FootballManager.manager.Tournament;

public class ToPreviousMenu implements LeagueMenuOptionsInterface{
    @Override
    public void getOption(Tournament rfpl) {
        MenuClass.gameMenu();
    }
}
