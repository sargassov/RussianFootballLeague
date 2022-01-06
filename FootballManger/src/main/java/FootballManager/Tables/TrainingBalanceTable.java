package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TrainingBalanceTable extends Table implements Data{

    private static Tournament rfpl;
    private static final String trainigBalanceInfoPath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\trainigBalanceTable.txt";
    private static final String TRAINING_BALANCE_FILE_NOT_FOUND = "TRAINING BALANCE FILE NOT FOUND";
    private List<String> trainigBalanceInfoList;

    public TrainingBalanceTable(){

        Path path = Paths.get(trainigBalanceInfoPath);
        if(Files.exists(path)){
            try {
                trainigBalanceInfoList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(TRAINING_BALANCE_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {

        for(int x = 0, y = 0; x < trainigBalanceInfoList.size();){
            if(trainigBalanceInfoList.get(x).contains("/")){
                String[] mass = trainigBalanceInfoList.get(x).split("/");
                mass[1] = Corrector.wordToCenter(rfpl.myTeam.playerList.get(y).name, mass[1].length());
                mass[2] = Corrector.wordToCenter("" + rfpl.myTeam.playerList.get(y).trainingAble, mass[2].length());
                mass[3] = Corrector.wordToCenter("" + rfpl.myTeam.playerList.get(y).trainingBalance, mass[3].length());

                String answerString = "";
                for (String s: mass){
                    answerString = answerString + (s + "|");
                }
                System.out.println(answerString);

                if(rfpl.myTeam.playerList.size() - 1 != y) y++;
                else {
                    x = 7;
                }
            }
            else {
                System.out.println(trainigBalanceInfoList.get(x));
                x++;
            }
        }

    }
}
