package FootballManager.NewDayOptions;

import FootballManager.manager.MenuClass;
import FootballManager.manager.Tournament;

public class ToPreviousMenu implements NewDayMenuOptionsInterface{
    @Override
    public void getOption(Tournament rfpl) {
        MenuClass.gameMenu();
    }
}
