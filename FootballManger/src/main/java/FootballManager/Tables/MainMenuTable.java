package FootballManager.Tables;


import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;
import FootballManager.time.DayMatch;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainMenuTable extends Table implements Data{

    private Tournament rfpl;
    private List<String> fields;

    public MainMenuTable(List<String> fields){
        this.fields = fields;
    }


    @Override
    public void toPrint(Tournament rfpl) {
        this.rfpl = rfpl;

        Corrector.notNullChecking(fields);

        for (String string : fields) {
            if(!string.contains("NEXT")) System.out.println(string);
            else{
                String newString = string;
                if(rfpl.currentDay instanceof DayMatch){
                    newString += " MATCH";
                }

                newString += "    (" +
                            + rfpl.currentDay.date.get(Calendar.DAY_OF_MONTH) + " " +
                            monthInWord(rfpl.currentDay.date.get(Calendar.MONTH)) + " "
                            + rfpl.currentDay.date.get(Calendar.YEAR) + ")";

                System.out.println(newString);
            }
        }
    }

    private static String monthInWord(int num){

        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");

        return months.get(num);
    }
}
