package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BuildNewSectorsTable extends Table implements Data{
    private final static int HEIGHT = 23;
    private final static int WIDTH = 93;
    private Tournament rfpl;
    private final String buildNewSectorsTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\buildNewSectors.txt";
    private final static String BUILD_NEW_SECTORS_FILE_NOT_FOUND = "BUILD NEW SECTORS FILE NOT FOUND";
    private List<String> buildNewSectorsTableList;

    public BuildNewSectorsTable(){
        Path path = Paths.get(buildNewSectorsTablePath);

        if(Files.exists(path)){
            try {
                buildNewSectorsTableList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(BUILD_NEW_SECTORS_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }


    @Override
    public void toPrint(Tournament rfpl) {

        for (int i = 0; i < buildNewSectorsTableList.size(); i++) {

            if(i == 2){
                String[] mass = buildNewSectorsTableList.get(i).split("/");
                mass[3] = Corrector.wordToCenter("" + rfpl.myTeam.stadium.getFullCapacity(),
                        mass[3].length());

                String s = "";
                for(String str : mass){
                    s += (str + "|");
                }
                buildNewSectorsTableList.set(i, s);
            }

            System.out.println(buildNewSectorsTableList.get(i));
        }
    }
}
