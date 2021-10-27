package FootballManager.time;

import java.util.Calendar;

public class DayTrain extends Day {
    //Coach[] coaches = new Coach[5];
    //String[] CoachSlot = new String[5];

    //public DayTrain(){}

    @Override
    public String toString(){
        return "(" + date.get(Calendar.DAY_OF_MONTH) + " " + date.get(Calendar.MONTH) + " " + date.get(Calendar.YEAR) +
                ") training";
    }
}
