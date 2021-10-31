package FootballManager.Tables;


import FootballManager.finance.Sponsor;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SponsorsTable extends Table implements Data{

    private final static int HEIGHT = 23;
    private final static int WIDTH = 93;
    private Tournament rfpl;
    private final String sponsorsTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\sponsorTable.txt";
    private final static String SPONSORS_FILE_NOT_FOUND = "SPONSORS_FILE_NOT_FOUND";
    private List<String> sponsorsTableList;



    public SponsorsTable(){
        Path path = Paths.get(sponsorsTablePath);

        if(Files.exists(path)){
            try{
                sponsorsTableList = Files.readAllLines(path, StandardCharsets.UTF_8);

            } catch (IOException e) {
                System.out.println(SPONSORS_FILE_NOT_FOUND);
                e.printStackTrace();}
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {
        this.rfpl = rfpl;

        for(int x = 0, y = 0; x < sponsorsTableList.size(); x++){

            if(x > 1 && x != sponsorsTableList.size() - 1){

                Sponsor sponsor = rfpl.sponsorList.get(y);
                double[] commandArr = {sponsor.dayWage, sponsor.matchWage, sponsor.goalBonusWage,
                        sponsor.winWage, sponsor.deuceWage, sponsor.contractBonusWage};

                String[] mass = sponsorsTableList.get(x).split("/");
                mass[2] = rfpl.sponsorList.get(y).name;
                mass[2] = Corrector.wordToCenter(mass[2], LINELENGTH27);
                for(int z = 3, a = 0; a < commandArr.length; z++, a++){
                    String temp = "" + commandArr[a];
                    mass[z] = Corrector.wordToCenter(temp, LINELENGTH5);
                }
                String temp = "";
                for(String s : mass){
                    temp += (s +"|");
                }
                sponsorsTableList.set(x, temp);
                y++;
            }
            System.out.println(sponsorsTableList.get(x));
        }
    }

}
