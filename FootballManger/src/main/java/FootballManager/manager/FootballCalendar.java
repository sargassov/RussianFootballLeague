package FootballManager.manager;


import FootballManager.time.Day;
import FootballManager.time.DayMatch;
import FootballManager.time.DayTrain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class FootballCalendar implements Cloneable {

    private static Tournament rfpl;
    private static GregorianCalendar currentDate;
    private static Draw[] drawList;
    private static List<Integer> num = new ArrayList<>();
    private static final String calendarFile = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\calendar.txt";
    private static final String LOAD_CALENDAR_FILE = "\n\n\t\t\tLOAD FILE WITH THE CALENDAR\n\n";
    private static List<String> gameWeeks;
    private static final GregorianCalendar usualdate = new GregorianCalendar(2019, Calendar.AUGUST, 3);
    private static final int[] monthArr = {31, 30, 31, 30, 31, 31, 29, 31, 30, 31, 6};
    private static int count = 0;

    public static void sheduleCreator(Tournament tournament) throws IOException {

        rfpl = tournament;

        String result = "";
        currentDate = usualdate; //ткущая дата равна используемой дате

        takeDraw(); // подготовление текущей жеребьевки
        gameWeeks = readTheCalendarFile(); //присвоение листу туров информации из файла с календарем

        for(String s : gameWeeks){
            System.out.println(s);
        }


        DayMatch.setRfpl(rfpl); //отправка статической лиги в класс матча

        for (short x = 0; x < 30; x++) {
            String[] matches = gameWeeks.get(x).split("/"); // выбираем каждый игровой тур
            int y = 0;

            for (String currentMatch : matches) { // выбираем каждый матч
                String[] currentTeam = currentMatch.split("-"); // выбираем каждую команду из матча
                //checkinDrawList(Integer.parseInt(currentTeam[0]), Integer.parseInt(currentTeam[1])); //отправляем инфо о полученных командах в метод

                DayMatch dayMatch = checkinDrawList(Integer.parseInt(currentTeam[0]),
                        Integer.parseInt(currentTeam[1]));

                rfpl.shedule.get(x).set(y, dayMatch);
                rfpl.shedule.get(x).get(y).date = (GregorianCalendar) currentDate.clone();
                y++;
            }

            if (x % 2 == 1) {
                currentDate.add(Calendar.DAY_OF_MONTH, 7);
            } else {
                currentDate.add(Calendar.DAY_OF_MONTH, 14);
            }
        }
    }

    private static DayMatch checkinDrawList(int home, int away) {

        DayMatch dayMatch = new DayMatch(); // создание игрового матча

        for (Draw draw : drawList) {
            if (draw.placeOfLot == home) {
                dayMatch.setHome(draw.nameOfTeam);
                dayMatch.setStadium(draw.nameOfTeam.stadium);
            }
            if (draw.placeOfLot == away) {
                dayMatch.setAway(draw.nameOfTeam);
            }
        }
        return dayMatch;
    }

    private static List<String> readTheCalendarFile() { //читаем проект календаря из файла
        Path path = Paths.get(calendarFile);

        if(Files.exists(path)){
            try{
                return Files.readAllLines(path, StandardCharsets.UTF_8);

            } catch (IOException e) {
                System.out.println(LOAD_CALENDAR_FILE);
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void takeDraw() {

        drawList = new Draw[16]; // создание корзины для жеребьевки из 16 команд
        numInit(); // создание листа из 16 чисел

        for (short x = 0; x < rfpl.teams.length; x++) {
            Draw draw = new Draw(); // создание конкретного жребия
            int count = (int) (Math.random() * num.size()); // генерация случайного числа
            draw.placeOfLot = num.get(count) + 1; // присваивание жребия номера из листа
            num.remove(count); // удаление уже использованного номера
            draw.nameOfTeam = rfpl.teams[x]; // присвоение жребию команды из списка
            drawList[x] = draw; // складывание конкретного жребия в лист
            System.out.println(draw.placeOfLot + " " + draw.nameOfTeam.name);
        }
    }

    private static void numInit() {
        for (int i = 0; i < rfpl.teams.length; i++) {
            num.add(i);
        }
    }

    public static void editCalendar() {

        printDayMatches();

        rfpl.calendar = new ArrayList<>();
        currentDate = new GregorianCalendar(2019, 7, 1);

        for (int s : monthArr) {

            ArrayList<Day> month = new ArrayList<>(s);
            for (int x = 0; x < s; x++) {

                month.add(getDay()); // в месяц добавляется либо день матча либл день тренировки
                currentDate.add(Calendar.DAY_OF_MONTH, 1);
            }

            System.out.println(month.size());
            rfpl.calendar.add(month);// месяц добавляется в календарь в объекте лиги
        }

        System.out.println("calendar size = " + rfpl.calendar.size());

        calendarPrinting();

        rfpl.currentDay = rfpl.calendar.get(0).get(0);
        rfpl.currentDay.isToday = true;
        rfpl.currentDate = (Calendar) rfpl.currentDay.date.clone();
        rfpl.startDate = (GregorianCalendar) currentDate.clone();

    }

    private static void calendarPrinting() {
        for (ArrayList<Day> month : rfpl.calendar) {
            for (Day day : month) {
                //out.print(day.toString());
                if (day instanceof DayMatch) {
                    DayMatch dm = (DayMatch) day;
                    System.out.println(dm.toString() + "AAA");
                } else {
                    DayTrain dt = (DayTrain) day;
                    System.out.println(dt.toString());
                }
            }
        }
    }

    private static Day getDay(){
        Day day;
        if(dayAndMonthEqual(rfpl.shedule.get(count).get(0).date, currentDate)){
            day = getDayMatchFromShedule(rfpl.shedule.get(count), rfpl.myTeam.name);
            count++;
            //if(count > 29) count = 29;
        }

        else {
            day = new DayTrain();
            day.date = (GregorianCalendar) currentDate.clone();
            //SheduleComparer(rfpl, day);
        }
        return day;
    }

    private static boolean dayAndMonthEqual(GregorianCalendar date1, GregorianCalendar date2){
        return date1.get(Calendar.DAY_OF_MONTH) ==
                date2.get(Calendar.DAY_OF_MONTH) && date1.get(Calendar.MONTH) ==
                date2.get(Calendar.MONTH);
    }

    private static void printDayMatches() {

        for (ArrayList<DayMatch> dayMatches : rfpl.shedule) {
            for (DayMatch dm : dayMatches) {
                System.out.println(dm.toString());
            }
        }
    }

    private static DayMatch getDayMatchFromShedule(ArrayList<DayMatch>shedule, String TeamName){
        for(DayMatch dayMatch : shedule){
            if(dayMatch.home.name.equals(TeamName) || dayMatch.away.name.equals(TeamName)){
                return dayMatch;
            }
        }
        return new DayMatch();
    }

    private void sheduleComparer(Tournament rfpl, Day day) {
        for (ArrayList<DayMatch> tour : rfpl.shedule) {
            if (day.date.get(Calendar.DAY_OF_MONTH) == tour.get(0).date.get(Calendar.DAY_OF_MONTH) &&
                    day.date.get(Calendar.MONTH) == tour.get(0).date.get(Calendar.MONTH)) {
                for (DayMatch match : tour) {
                    if (match.home.equals(rfpl.myTeam) || match.away.equals(rfpl.myTeam)) {
                        day = match;
                        day.MatchParameter = true;
                    }
                }
            } else {
                DayTrain train = new DayTrain();
                day = train;
            }
        }
    }

    public static String getStadiumName(String name) {
        for (Team team : rfpl.teams) {
            if (name.equals(team.name)) {
                return team.stadium.getTitle();
            }
        }
        return "";
    }

}
