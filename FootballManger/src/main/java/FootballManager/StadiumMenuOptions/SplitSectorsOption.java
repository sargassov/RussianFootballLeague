package FootballManager.StadiumMenuOptions;


import FootballManager.Tables.SplitSectorsTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Stadium;
import FootballManager.manager.Tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitSectorsOption implements StadiumMenuOptionInterface{

    private static int sVal = 40;
    private static Tournament rfpl;
    private static List<String> textEscort;
    private Stadium stadium;

    public SplitSectorsOption(){
        textEscort = new ArrayList<>(Arrays.asList(Corrector.getS(sVal) + "Simple sector press \"1\"",
                Corrector.getS(sVal) + "Family sector press \"2\"",
                Corrector.getS(sVal) + "Fan sector press \"3\"",
                Corrector.getS(sVal) + "Away sector press \"4\"",
                Corrector.getS(sVal) + "VIP sector press \"5\"",
                Corrector.getS(sVal) + "To return press \"0\""));
    }



    @Override
    public void getOption(Tournament rfpl) {

        SplitSectorsOption.rfpl = rfpl;
        stadium = rfpl.myTeam.stadium;

        System.out.print("\n\n\n\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase("split sectors mode " + rfpl.myTeam.name);
        System.out.println("\n\n\n");

        new SplitSectorsTable().toPrint(rfpl);

        System.out.print("\n\n" + Corrector.getS(sVal) + "MENU:\n" +
                Corrector.getS(sVal) + "To split sectors press \"1\":\n" +
                Corrector.getS(sVal) + "To return press \"0\":");

        int choise = Corrector.inputIntMethod(0, 1);
        if(choise != 0) {
            splitSectorsMode();
        }

    }

    private void splitSectorsMode() {
        System.out.print("\n\n" + Corrector.getS(sVal) + "Choose sector TO SPLIT:\n");

        for (String s : textEscort){
            System.out.println(s);
        }

        int choise = Corrector.inputIntMethod(0, 5);
        if(choise != 0) {
            getCapacityFromSector(--choise);
        }
    }

    private void getCapacityFromSector(int toSector) {
        System.out.print("\n\n" + Corrector.getS(sVal) + "Choose sector FROM SPLIT\n" +
                Corrector.getS(sVal) + "or write \"0\" to quit\n");

        List <Integer> sectorCapacity = new ArrayList<>(Arrays.asList(stadium.getSimpleCapacity(),
                stadium.getFamilyCapacity(), stadium.getFanCapacity(), stadium.getAwayCapacity(), stadium.getVipCapacity()));
        ArrayList <Integer> listOfVariables = createListOfVariables(sectorCapacity, toSector);


        int choise = Corrector.inputIntFromTheList(listOfVariables);
        if(choise != 0) {
            System.out.println("\n\n" + Corrector.getS(sVal) + "How many places you want to add?");
            int secondChoise = Corrector.inputIntMethod(1, sectorCapacity.get(choise - 1));

            System.out.println("choise = " +  choise);
            System.out.println("to sector = " + toSector);
            setNewCapacity(++toSector, secondChoise, true);
            setNewCapacity(choise, secondChoise, false);
            getOption(rfpl);

        }
    }

    private ArrayList<Integer> createListOfVariables(List<Integer> sectorCapacity, int choosedSector) {
        ArrayList<Integer> listOfVariables = new ArrayList<>();
        listOfVariables.add(0);

        for(int x = 0; x < sectorCapacity.size(); x++){
            if(choosedSector != x && sectorCapacity.get(x) != 0){
                System.out.println(textEscort.get(x));
                listOfVariables.add(x + 1);
            }
            x++;
        }

        return listOfVariables;
    }

    private void setNewCapacity(int type, int newCapacity, boolean addOrRemove){

        if(addOrRemove){
            if(type == 1) stadium.setSimpleCapacity(stadium.getSimpleCapacity() + newCapacity);
            else if(type == 2) stadium.setFamilyCapacity(stadium.getFamilyCapacity() + newCapacity);
            else if(type == 3) stadium.setFanCapacity(stadium.getFanCapacity() + newCapacity);
            else if(type == 4) stadium.setAwayCapacity(stadium.getAwayCapacity() + newCapacity);
            else stadium.setVipCapacity(stadium.getVipCapacity() + newCapacity);
        }
        else{
            if(type == 1) stadium.setSimpleCapacity(stadium.getSimpleCapacity() - newCapacity);
            else if(type == 2) stadium.setFamilyCapacity(stadium.getFamilyCapacity() - newCapacity);
            else if(type == 3) stadium.setFanCapacity(stadium.getFanCapacity() - newCapacity);
            else if(type == 4) stadium.setAwayCapacity(stadium.getAwayCapacity() - newCapacity);
            else stadium.setVipCapacity(stadium.getVipCapacity() - newCapacity);
        }

    }

}
