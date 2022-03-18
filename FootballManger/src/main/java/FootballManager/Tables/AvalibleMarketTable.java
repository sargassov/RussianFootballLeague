package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;
import FootballManager.markets.Market;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

public class AvalibleMarketTable extends Table implements Data{

    private final static int HEIGHT = 23;
    private final static int WIDTH = 93;
    private Tournament rfpl;
    private final String avalibleMarketTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\avalibleMarketTable.txt";
    private final static String AVALIBLE_MARKET_FILE_NOT_FOUND = "AVALIBLE MARKET_FILE_NOT_FOUND";
    private List<String> avalibleMarketTableList;

    public AvalibleMarketTable(){
        Path path = Paths.get(avalibleMarketTablePath);

        if(Files.exists(path)){
            try {
                avalibleMarketTableList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(AVALIBLE_MARKET_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }
    @Override
    public void toPrint(Tournament rfpl) {

        boolean endTable = false;
        int count = 0;
        for (int i = 0; i < avalibleMarketTableList.size();) {

            if(i == 7){
                for (int x = 0; x < rfpl.myTeam.markets.size(); x++) {
                    Market market = rfpl.myTeam.markets.get(x);
                    String[] mass = avalibleMarketTableList.get(i).split("/");
                    mass[1] = Corrector.wordToCenter("" + (count + 1), mass[1].length());
                    mass[2] = Corrector.wordToCenter(market.getMarketType().toString(), mass[2].length());
                    mass[3] = Corrector.wordToCenter(
                            "" + market.getStartDate().get(Calendar.DAY_OF_MONTH) + "." +
                                    "" + (market.getStartDate().get(Calendar.MONTH) + 1) + "." +
                                    "" + market.getStartDate().get(Calendar.YEAR), mass[3].length());
                    mass[4] = Corrector.wordToCenter(
                            "" + market.getFinishDate().get(Calendar.DAY_OF_MONTH) + "." +
                                    "" + (market.getFinishDate().get(Calendar.MONTH) + 1) + "." +
                                    "" + market.getFinishDate().get(Calendar.YEAR), mass[4].length());

                    String marketField = Corrector.stringStapler(mass);
//                    for (int y = 0; y < mass.length; y++) {
//                        marketField += (mass[y] + "|");
//                    }

                    System.out.println(marketField);
                    count++;
                }
            }

            if(i != 7)System.out.println(avalibleMarketTableList.get(i));
            i++;
        }
    }
}
