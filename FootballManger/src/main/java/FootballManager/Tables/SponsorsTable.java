package FootballManager.Tables;


import FootballManager.finance.Sponsor;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.math.BigDecimal;
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
    private static boolean cheatActive;



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

            if(x == 19 && !cheatActive) continue;

            if(x > 1 && x != sponsorsTableList.size() - 1){

                Sponsor sponsor = rfpl.sponsorList.get(y);
                BigDecimal[] commandArr = {
                        BigDecimal.valueOf(sponsor.getDayWage() / 10000, 2),
                        BigDecimal.valueOf(sponsor.getMatchWage() / 10000, 2),
                        BigDecimal.valueOf(sponsor.getGoalBonusWage() / 10000, 2),
                        BigDecimal.valueOf(sponsor.getWinWage() / 10000, 2),
                        BigDecimal.valueOf(sponsor.getDeuceWage() / 10000, 2),
                        BigDecimal.valueOf(sponsor.getContractBonusWage() / 10000, 2)
                };

                String[] mass = sponsorsTableList.get(x).split("/");
                //mass[2] = rfpl.sponsorList.get(y).getName();
                mass[2] = Corrector.wordToCenter(rfpl.sponsorList.get(y).getName(), 27);
                for(int z = 3, a = 0; a < commandArr.length; z++, a++){
                    //String temp = "" + Corrector.priceInMillion(commandArr[a]);
                    mass[z] = Corrector.wordToCenter(commandArr[a].toString(), mass[z].length());
                }
//                String temp = "";
//                for(String s : mass){
//                    temp += (s +"|");
//                }
                sponsorsTableList.set(x, Corrector.stringStapler(mass));
                y++;
            }
            System.out.println(sponsorsTableList.get(x));
        }
    }

    public static void setCheatActive(boolean cheatActive) {
        SponsorsTable.cheatActive = cheatActive;
    }
}
