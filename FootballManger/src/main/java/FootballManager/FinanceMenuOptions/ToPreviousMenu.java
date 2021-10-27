package FootballManager.FinanceMenuOptions;


import FootballManager.manager.MenuClass;
import FootballManager.manager.Tournament;

public class ToPreviousMenu implements FinanceMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        MenuClass.gameMenu();
    }
}
