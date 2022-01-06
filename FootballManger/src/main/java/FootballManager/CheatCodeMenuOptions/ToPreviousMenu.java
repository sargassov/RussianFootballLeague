package FootballManager.CheatCodeMenuOptions;

import FootballManager.manager.MenuClass;
import FootballManager.manager.Tournament;

public class ToPreviousMenu implements CheatCodeMenuOptionsInterface{
    @Override
    public void getOption(Tournament rfpl) {
        MenuClass.gameMenu();
    }
}
