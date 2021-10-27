package FootballManager.StadiumMenuOptions;


import FootballManager.manager.MenuClass;
import FootballManager.manager.Tournament;

public class ToPreviousMenu implements StadiumMenuOptionInterface{
    @Override
    public void getOption(Tournament rfpl) { MenuClass.gameMenu(); }
}
