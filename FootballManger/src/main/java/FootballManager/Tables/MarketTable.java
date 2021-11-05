package FootballManager.Tables;

import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MarketTable extends Table implements Data{
    private final static int HEIGHT = 23;
    private final static int WIDTH = 93;
    private Tournament rfpl;
    private final String marketTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\marketTable.txt";
    private final static String MARKET_FILE_NOT_FOUND = "MARKET_FILE_NOT_FOUND";
    private List<String> marketTableList;

    public MarketTable(){
        Path path = Paths.get(marketTablePath);

        if(Files.exists(path)){
            try {
                marketTableList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(MARKET_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }


    @Override
    public void toPrint(Tournament rfpl) {

        for (int i = 0; i < marketTableList.size(); i++) {
            System.out.println(marketTableList.get(i));
        }
    }
}
