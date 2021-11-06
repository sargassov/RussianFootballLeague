package FootballManager.Tables;


import FootballManager.manager.Corrector;
import FootballManager.manager.Stadium;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SetTicketCostTable extends Table implements Data{

    private static Tournament rfpl;
    private static final String setTicketCostPath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\setTicketCost.txt";
    private static final String SET_TICKET_COST_FILE_NOT_FOUND = "SET TICKET COST FILE NOT FOUND";
    private List<String> setTicketCostList;


    public SetTicketCostTable(){

        Path path = Paths.get(setTicketCostPath);
        if(Files.exists(path)){
            try {
                setTicketCostList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(SET_TICKET_COST_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {
        String tech;
        Stadium stadium = rfpl.myTeam.stadium;

        Object[] setTicketCompareArr = {
                stadium.getFullCapacity(), stadium.getFullSectorCapacity(),
                stadium.getFullNotSectorCapacity(), stadium.getUsualAverageCapacity(),
                stadium.getSimpleCapacity(), stadium.getSimpleTicketCost(),
                stadium.getFamilyCapacity(), stadium.getFamilyTicketCost(),
                stadium.getFanCapacity(), stadium.getFanTicketCost(), stadium.getAwayCapacity(),
                stadium.getAwayTicketCost(), stadium.getVipCapacity(), stadium.getVipTicketCost()};

        for (int x = 0, y = 0; x < setTicketCostList.size(); x++) {

            if(x > 1){
                String[] mass = setTicketCostList.get(x).split("/");
                if(mass.length == 4){

                    tech = "" + (int)setTicketCompareArr[y];

                    mass[3] = Corrector.wordToCenter(tech, LINELENGTH9);
                    setTicketCostList.set(x, "");

                    for (int i = 0; i < mass.length; i++) {
                        setTicketCostList.set(x, setTicketCostList.get(x) + (mass[i] + "|"));
                    }
                    y++;
                }
            }

            System.out.println(setTicketCostList.get(x));
        }
    }
}
