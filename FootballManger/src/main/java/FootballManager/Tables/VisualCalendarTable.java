package FootballManager.Tables;


import FootballManager.manager.Corrector;
import FootballManager.manager.Interface;
import FootballManager.manager.Tournament;
import FootballManager.time.Day;
import FootballManager.time.DayMatch;

import java.util.ArrayList;
import java.util.Calendar;

public class VisualCalendarTable extends Table implements Data {

    private static Interface cloneVisualCalendarTable;
    public static int startMonthForVisualCalendar;

    public static String[] months = {"JANUARY 2020", "FEBRUARY 2020", "MARCH 2020", "APRIL 2020", "MAY 2020",
            "JUNE 2020", "JULY", "AUGUST 2019", "SEPTEMBER 2019", "OCTOBER 2019", "NOVEMBER 2019", "DECEMBER 2019"};

    @Override
    public void toPrint(Tournament rfpl) {
        cloneVisualCalendarTable = new Interface(rfpl.visualCalendarInterface);
        int monthToPrint = startMonthForVisualCalendar - 7;
        if(monthToPrint < 0) monthToPrint += 12;
        else if(monthToPrint > 10) monthToPrint = 0;
        ArrayList<Day> days = rfpl.calendar.get(monthToPrint);
        int monthDayCounter = 0, reverseMonthDayCounter = 0;
        for (int x = 0; x < cloneVisualCalendarTable.fields.size(); x++) {
            if(x == 1) cloneVisualCalendarTable.fields.set(x, changeStringMethod(cloneVisualCalendarTable.fields.get(x)));
            else if(x >= 5 && x % 3 == 2){
                String[] cutString = cloneVisualCalendarTable.fields.get(x).split("\\|");
                for(int y = 2; monthDayCounter < days.size(); y++){
                    if(days.get(monthDayCounter).date.get(Calendar.DAY_OF_WEEK) == y){
                        editAnInfoIntoNumber(cutString, days, monthDayCounter, y);
                        cloneVisualCalendarTable.fields.set(x, stringConnecter(cutString));
                        monthDayCounter++;
                        reverseMonthDayCounter++;
                    }
                    if(y == 7) y = 0;
                    if(y == 1) break;
                }
            }
            else if(x >= 6 && x % 3 == 0){
                monthDayCounter -= reverseMonthDayCounter;
                reverseMonthDayCounter = 0;
                String[] cutString = cloneVisualCalendarTable.fields.get(x).split("\\|");
                for(int y = 2; monthDayCounter < days.size(); y++){
                    if(days.get(monthDayCounter).date.get(Calendar.DAY_OF_WEEK) == y){
                        if(y > 1){ editEvent(days, monthDayCounter, cutString, y, rfpl); }
                        else{ cutString[cutString.length - 1] = Corrector.wordToCenter("weekend", 11); }
                        cloneVisualCalendarTable.fields.set(x, stringConnecter(cutString));
                        monthDayCounter++;
                    }
                    if(y == 7) y = 0;
                    if(y == 1) break;
                }
            }
            System.out.println(cloneVisualCalendarTable.fields.get(x));
        }
    }

    private static String changeStringMethod(String currentString){
        String neo = currentString;
        String currentMonth = Corrector.wordToCenter(months[startMonthForVisualCalendar], 27);
        neo = neo.replaceAll(del, currentMonth);
        return neo;
    }

    private  static void editEvent(ArrayList<Day> days, int monthDayCounter, String[] cutString, int y, Tournament rfpl){
        if(days.get(monthDayCounter) instanceof DayMatch){
            DayMatch match = ((DayMatch) days.get(monthDayCounter));
            if(match.home.name.equals(rfpl.myTeam.name)){
                if(match.away.name.length() > 11){ cutString[y] = Corrector.wordToCenter(match.away.name.substring(0,8) + " A", 11);}
                else {cutString[y] = Corrector.wordToCenter(match.away.name + " H", 11);}
            }
            else if(match.away.name.equals(rfpl.myTeam.name)){
                if(match.home.name.length() > 11) {cutString[y] = Corrector.wordToCenter(match.home.name.substring(0,8) + " H", 11);}
                else {cutString[y] = Corrector.wordToCenter(match.home.name + " A", 11);}
            }
        }
        else{
            cutString[y] = Corrector.wordToCenter("train", 11);
        }
    }

    private static String stringConnecter(String[] mass){
        String result = "";
        for(String string : mass){
            result += string;
            result += "|";
        }
        return result;
    }

    private static void editAnInfoIntoNumber(String[] cutString, ArrayList<Day> days, int monthDayCounter, int y){
        if(y > 1){
            cutString[y] = Corrector.wordToCenter(toStr(days.get(monthDayCounter).date.get(Calendar.DAY_OF_MONTH)), 11);
        }
        else{
            cutString[cutString.length - 1] = Corrector.wordToCenter(toStr(days.get(monthDayCounter).date.get(Calendar.DAY_OF_MONTH)), 11);
        }
    }

    private static String toStr(int x){
        String neo = new String("");
        neo += x;
        return neo;
    }
}
