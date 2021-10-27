package FootballManager.StadiumMenuOptions;


import FootballManager.Tables.StadiumInfoTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;
import FootballManager.time.Day;

import java.util.ArrayList;

public class InfoOption implements StadiumMenuOptionInterface{

    private static int sVal = 40;
    private static Tournament rfpl;

    @Override
    public void getOption(Tournament rfpl) {

        InfoOption.rfpl = rfpl;
        System.out.print("\n\n\n\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase("stadium info " + rfpl.myTeam.name);
        System.out.println("\n\n\n");

        new StadiumInfoTable().toPrint(rfpl);

        System.out.println("\n\n" + Corrector.getS(sVal) + "MENU:\n" +
                Corrector.getS(sVal) + "To rename a stadium press \"1\":\n" +
                Corrector.getS(sVal) + "To return press \"0\":");

        int choise = Corrector.inputIntMethod(0, 1);
        if(choise == 1) renameStadium();
    }

    private void renameStadium() {
        System.out.print("\n\n" + Corrector.getS(sVal) + "Write a new stadium title: \n");
        String newTitle = Corrector.InputStringMethod();
        System.out.print("\n\n" + Corrector.getS(sVal) + "Do you want give new title (" +
                newTitle + ") for your stadium\n" +
                Corrector.getS(sVal) + "Press 1 if yes, or 0 if no: ");
        int choise = Corrector.inputIntMethod(0, 1);
        if(choise == 1){
            rfpl.myTeam.stadium.setTitle(newTitle);

            for(ArrayList<Day> tour : rfpl.calendar){
                for(Day day : tour){

                }
            }

            System.out.print("\n\n" + Corrector.getS(sVal) + "Now youe stadium is called (" +
                    rfpl.myTeam.stadium.getTitle() + ")");
            getOption(rfpl);
        }
    }
}
