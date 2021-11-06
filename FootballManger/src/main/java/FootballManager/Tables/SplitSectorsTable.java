package FootballManager.Tables;


import FootballManager.manager.Corrector;
import FootballManager.manager.Stadium;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SplitSectorsTable extends Table implements Data{

    private static Tournament rfpl;
    private static final String splitSectorsPath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\splitSectorsTable.txt";
    private static final String SPLIT_SECTORS_TABLE_FILE_NOT_FOUND = "SPLIT SECTORS TABLE FILE NOT FOUND";
    private List<String> splitSectorsList;


    public SplitSectorsTable(){

        Path path = Paths.get(splitSectorsPath);
        if(Files.exists(path)){
            try {
                splitSectorsList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(SPLIT_SECTORS_TABLE_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {
        String tech;
        Stadium stadium = rfpl.myTeam.stadium;

        Object[] setTicketCompareArr = {
                stadium.getFullCapacity(), stadium.getFullSectorCapacity(), stadium.getFullNotSectorCapacity(),
                stadium.getSimpleCapacity(), stadium.getFamilyCapacity(), stadium.getFanCapacity(),
                stadium.getAwayCapacity(), stadium.getVipCapacity()};

        for (int x = 0, y = 0; x < splitSectorsList.size(); x++) {

            if(x > 1){
                String[] mass = splitSectorsList.get(x).split("/");
                if(mass.length == 4){

                    tech = "" + (int)setTicketCompareArr[y];

                    mass[3] = Corrector.wordToCenter(tech, LINELENGTH9);
                    splitSectorsList.set(x, "");

                    for (int i = 0; i < mass.length; i++) {
                        splitSectorsList.set(x, splitSectorsList.get(x) + (mass[i] + "|"));
                    }
                    y++;
                }
            }

            System.out.println(splitSectorsList.get(x));
        }
    }

}
