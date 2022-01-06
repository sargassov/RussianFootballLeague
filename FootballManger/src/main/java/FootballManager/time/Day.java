package FootballManager.time;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Day {
    public GregorianCalendar date = new GregorianCalendar();
    public boolean MatchParameter;
    public boolean isPassed;
    public boolean isToday;
    @Override
    public String toString(){
        return date.get(Calendar.DAY_OF_MONTH) + " " + date.get(Calendar.MONTH) + " " + date.get(Calendar.YEAR);
    }
}
