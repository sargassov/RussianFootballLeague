package FootballManager.TrainingMenuOptions;


import FootballManager.manager.MenuClass;
import FootballManager.manager.Tournament;

public class ToPreviousMenu implements TrainingMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        MenuClass.gameMenu();

    }
}
