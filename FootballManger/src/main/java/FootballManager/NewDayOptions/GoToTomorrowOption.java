package FootballManager.NewDayOptions;

import FootballManager.manager.MenuClass;
import FootballManager.manager.Tournament;
import FootballManager.time.Day;
import FootballManager.time.DayChanger;

import java.util.ArrayList;
import java.util.Calendar;

public class GoToTomorrowOption implements NewDayMenuOptionsInterface{
    @Override
    public void getOption(Tournament rfpl) {

        DayChanger.toChangeDay();



    }
}