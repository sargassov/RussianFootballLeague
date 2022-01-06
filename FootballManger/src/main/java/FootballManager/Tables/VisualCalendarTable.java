package FootballManager.Tables;


import FootballManager.manager.Corrector;
import FootballManager.manager.Interface;
import FootballManager.manager.Team;
import FootballManager.manager.Tournament;
import FootballManager.time.Day;
import FootballManager.time.DayMatch;
import FootballManager.time.DayTrain;

import java.util.ArrayList;
import java.util.Calendar;

public class VisualCalendarTable extends Table implements Data {

    private static Interface cloneVisualCalendarTable;
    public static int startMonthForVisualCalendar;
    public int numberOfDayOfTheWeek;
    public int numberOfMonthInCalendarArray;
    public boolean quakeBeginOfMonth = true;
    public boolean eventQuakeBeginOfMonth = true;

    public static int[] quakes = {5, 8, 3, 6, 8, 4, 7, 8, 4, 6, 2};

    public static String[] months = {"JANUARY 2020", "FEBRUARY 2020", "MARCH 2020", "APRIL 2020", "MAY 2020",
            "JUNE 2020", "JULY", "AUGUST 2019", "SEPTEMBER 2019", "OCTOBER 2019", "NOVEMBER 2019", "DECEMBER 2019"};

    @Override
    public void toPrint(Tournament rfpl) {

        cloneVisualCalendarTable = new Interface(rfpl.visualCalendarInterface);
        numberOfMonthInCalendarArrayDetermining();
        ArrayList<Day> currentMonth = rfpl.calendar.get(numberOfMonthInCalendarArray);
        numberOfDayOfTheWeekDetermining(numberOfMonthInCalendarArray);
        System.out.println(numberOfDayOfTheWeek);


        for (int x = 0, currentDayInMonth = 0, currentEventInMonth = 0; x < cloneVisualCalendarTable.fields.size(); x++) {
            if (x == 1) monthTitleToCenter();
            else if (x > 2 && x % 3 == 2) {
                String[] mass = cloneVisualCalendarTable.fields.get(x).split("\\|");

                for (int y = 2; y < mass.length; y++) {
//
                    if (quakeBeginOfMonth){
                        y = numberOfDayOfTheWeek;
                        quakeBeginOfMonth = false;
                    }
                    inputNumbers(currentDayInMonth, currentMonth, y, mass);
                    currentDayInMonth++;
                }

                cloneVisualCalendarTable.fields.set(x, Corrector.stringStapler(mass));
            }

            else if (x > 3 && x % 3 == 0) {
                String[] mass = cloneVisualCalendarTable.fields.get(x).split("\\|");

                for (int y = 2; y < mass.length; y++) {
                    inputEvent();
                    if (eventQuakeBeginOfMonth){
                        y = numberOfDayOfTheWeek;
                        eventQuakeBeginOfMonth = false;
                    }
                    if (currentEventInMonth < currentMonth.size()) {
                        Day currentDayInCalendar = currentMonth.get(currentEventInMonth);
                        if(currentDayInCalendar.isPassed){
                            if(currentDayInCalendar instanceof DayMatch){
                                mass[y] = Corrector.wordToCenter(
                                        ((DayMatch) currentDayInCalendar).homeScore +
                                                ":" + ((DayMatch) currentDayInCalendar).awayScore, mass[y].length());
                            }
                            else  mass[y] = Corrector.wordToCenter("X", mass[y].length());
                        }
                        else {
                            if(currentDayInCalendar instanceof DayMatch){
                                String sign;
                                Team team;
                                if(((DayMatch) currentDayInCalendar).home.equals(rfpl.myTeam)){
                                    sign = " H";
                                    team = ((DayMatch) currentDayInCalendar).away;
                                }
                                else {
                                    sign = " A";
                                    team = ((DayMatch) currentDayInCalendar).home;
                                }
                                mass[y] = Corrector.wordToCenter(trims(team.name) + sign, mass[y].length());
                            }
                            else if (currentDayInCalendar.date.get(Calendar.DAY_OF_WEEK) == 1) mass[y] = Corrector.wordToCenter("weekend", mass[y].length());
                            else if (currentDayInCalendar instanceof DayTrain) mass[y] = Corrector.wordToCenter("train", mass[y].length());
                        }
                    }
                    currentEventInMonth++;
                }

                cloneVisualCalendarTable.fields.set(x, Corrector.stringStapler(mass));
            }


            System.out.println(cloneVisualCalendarTable.fields.get(x));
        }


//        cloneVisualCalendarTable = new Interface(rfpl.visualCalendarInterface); //копирование интерфейса
//        int monthInACalendarArray = startMonthForVisualCalendar - 7; //установка сартового месяца со сдвигом
//        if(monthInACalendarArray < 0) monthInACalendarArray += 12;
//        else if(monthInACalendarArray > 10) monthInACalendarArray = 0;
//
//        ArrayList<Day> currentMonth = rfpl.calendar.get(monthInACalendarArray);
//        int currenterDay = 0, reverseCurrenterDay = 0;
//
//        for (int x = 0; x < cloneVisualCalendarTable.fields.size(); x++) {
//            if(x == 1) cloneVisualCalendarTable.fields.set(x, changeStringMethod(cloneVisualCalendarTable.fields.get(x)));//подставляем название месяца
//            else if(x >= 5 && x % 3 == 2){
//                String[] cutString = cloneVisualCalendarTable.fields.get(x).split("\\|");
//                for(int y = 2; currenterDay < currentMonth.size(); y++){
//                    if(currentMonth.get(currenterDay).date.get(Calendar.DAY_OF_WEEK) == y){
//                        editAnInfoIntoNumber(cutString, currentMonth, currenterDay, y);
//                        cloneVisualCalendarTable.fields.set(x, stringConnecter(cutString));
//                        currenterDay++;
//                        reverseCurrenterDay++;
//                    }
//                    if(y == 7) y = 0;
//                    if(y == 1) break;
//                }
//            }
//            else if(x >= 6 && x % 3 == 0){
//                currenterDay -= reverseCurrenterDay;
//                reverseCurrenterDay = 0;
//                String[] cutString = cloneVisualCalendarTable.fields.get(x).split("\\|");
//                for(int y = 2; currenterDay < currentMonth.size(); y++){
//                    if(currentMonth.get(currenterDay).date.get(Calendar.DAY_OF_WEEK) == y){
//                        if(y > 1){ editEvent(currentMonth, currenterDay, cutString, y, rfpl); }
//                        else{ cutString[cutString.length - 1] = Corrector.wordToCenter("weekend", 11); }
//                        cloneVisualCalendarTable.fields.set(x, stringConnecter(cutString));
//                        currenterDay++;
//                    }
//                    if(y == 7) y = 0;
//                    if(y == 1) break;
//                }
//            }
//            System.out.println(cloneVisualCalendarTable.fields.get(x));
//        }
//    }
//
//    private static String changeStringMethod(String currentString){
//        String neo = currentString;
//        String currentMonth = Corrector.wordToCenter(months[startMonthForVisualCalendar], 27);
//        neo = neo.replaceAll(del, currentMonth);
//        return neo;
//    }
//
//    private  static void editEvent(ArrayList<Day> days, int monthDayCounter, String[] cutString, int y, Tournament rfpl){
//        if(days.get(monthDayCounter) instanceof DayMatch){
//            DayMatch match = ((DayMatch) days.get(monthDayCounter));
//            if(match.home.name.equals(rfpl.myTeam.name)){
//                if(match.away.name.length() > 11){ cutString[y] = Corrector.wordToCenter(match.away.name.substring(0,8) + " A", 11);}
//                else {cutString[y] = Corrector.wordToCenter(match.away.name + " H", 11);}
//            }
//            else if(match.away.name.equals(rfpl.myTeam.name)){
//                if(match.home.name.length() > 11) {cutString[y] = Corrector.wordToCenter(match.home.name.substring(0,8) + " H", 11);}
//                else {cutString[y] = Corrector.wordToCenter(match.home.name + " A", 11);}
//            }
//        }
//        else{
//            cutString[y] = Corrector.wordToCenter("train", 11);
//        }
//    }
//
//    private static String stringConnecter(String[] mass){
//        String result = "";
//        for(String string : mass){
//            result += string;
//            result += "|";
//        }
//        return result;
//    }
//
//    private static void editAnInfoIntoNumber(String[] cutString, ArrayList<Day> month, int dayCounter, int y){
//        //if()
//
//        if(y > 1){
//            cutString[y] = Corrector.wordToCenter(toStr(month.get(dayCounter).date.get(Calendar.DAY_OF_MONTH)), 11);
//        }
//        else{
//            cutString[cutString.length - 1] = Corrector.wordToCenter(toStr(month.get(dayCounter).date.get(Calendar.DAY_OF_MONTH)), 11);
//        }
//    }
//
//    private static String toStr(int x){
//        String neo = new String("");
//        neo += x;
//        return neo;
    }

    private String trims(String name) {
        int overIndex = "Krylya So".length();
        if(name.length() > overIndex)
            return name.substring(0, "Krylya So".length());
        else return name;
    }

    private void inputEvent() {

    }

    private void inputNumbers(int currentDayInMonth, ArrayList<Day> currentMonth, int y, String[] mass) {

        if (currentDayInMonth < currentMonth.size()) {
            Day currentDayInCalendar = currentMonth.get(currentDayInMonth);
            mass[y] = Corrector.wordToCenter("" + currentDayInCalendar.date.get(Calendar.DAY_OF_MONTH), mass[y].length());
        }
    }

    private void writeNumberOfDay() {

    }

    private void numberOfDayOfTheWeekDetermining(int numberOfMonthInCalendarArray) {
        numberOfDayOfTheWeek = quakes[numberOfMonthInCalendarArray];
        if(numberOfDayOfTheWeek == 1) numberOfDayOfTheWeek = 8;
    }

    private void numberOfMonthInCalendarArrayDetermining() {
        numberOfMonthInCalendarArray = startMonthForVisualCalendar - 7; //установка сартового месяца со сдвигом
        if(numberOfMonthInCalendarArray < 0) numberOfMonthInCalendarArray += 12;
        else if(numberOfMonthInCalendarArray > 10) numberOfMonthInCalendarArray = 0;
    }

    private String monthTitleToCenter() {
        return cloneVisualCalendarTable.fields.set(1, Corrector.wordToCenter(
                months[startMonthForVisualCalendar], cloneVisualCalendarTable.fields.get(1).length()));
    }
}
