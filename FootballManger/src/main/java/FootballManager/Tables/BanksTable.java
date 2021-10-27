package FootballManager.Tables;


import FootballManager.finance.Bank;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BanksTable extends Table implements Data {

    private final static int HEIGHT = 23;
    private final static int WIDTH = 93;
    private Tournament rfpl;
    private final String banksTablePath = "src/textFiles/banksTable.txt";
    private final static String BANKS_FILE_NOT_FOUND = "BANKS_FILE_NOT_FOUND";
    private List<String> banksTableList;
    private List<Bank>usefulBankList;
    private int coeff;

    public BanksTable(){}

    public BanksTable(List<Bank> usefulBankList, int coeff){
        Path path = Paths.get(banksTablePath);
        this.coeff = coeff;
        this.usefulBankList = usefulBankList;

        if(Files.exists(path)){
            try {
                banksTableList = Files.readAllLines(Paths.get(banksTablePath), StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println(BANKS_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }

    }


    @Override
    public void toPrint(Tournament rfpl) {
        this.rfpl = rfpl;
        if(usefulBankList == null)
            usefulBankList = new ArrayList<>();
        for(Bank b : rfpl.banks){
            if(b.getMaxLoan() >= coeff) usefulBankList.add(b);
        }

        for(int x = 0, y = 0; x < usefulBankList.size(); y++){

            if(y > 1){
                String[] mass = banksTableList.get(y).split("/");
                Bank bank = usefulBankList.get(x);

                double[] commandArr = {bank.getPerDay(), bank.getPerWeek(), bank.getPerMon(),
                       bank.getFullVal(), bank.getMaxLoan()};
                coefficient(commandArr);

                mass[2] = Corrector.wordToCenter(bank.getName(), LINELENGTH27);
                for(int z = 3, a = 0; z < mass.length; z++, a++){
                    String temp = "" + commandArr[a];
                    mass[z] = Corrector.wordToCenter(temp, LINELENGTH5);
                }

                String temp = "";
                for(String s : mass){
                    temp +=(s +"|");
                }
                banksTableList.set(y, temp);
                x++;
            }
            System.out.println(banksTableList.get(y));
        }
    }

    private void coefficient(double[] commandArr) {

        for (int i = 0; i < 4; i++) {
            if(i < 3) { commandArr[i] *= (double) coeff / 100; }
            else commandArr[i] *= coeff;

            String temp = "" + commandArr[i];
            commandArr[i] = Double.parseDouble((temp.substring(0, temp.indexOf('.') + 2)));
        }
    }


}
