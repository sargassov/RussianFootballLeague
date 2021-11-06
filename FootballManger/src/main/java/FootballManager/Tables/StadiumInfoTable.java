package FootballManager.Tables;


import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StadiumInfoTable extends Table implements Data{

    private static Tournament rfpl;
    private static final String stadiumInfoPath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\stadiumInfo.txt";
    private static final String STADIUM_INFO_FILE_NOT_FOUND = "STADIUM INFO FILE NOT FOUND";
    private List<String> stadiumInfoList;

    public StadiumInfoTable(){

        Path path = Paths.get(stadiumInfoPath);
        if(Files.exists(path)){
            try {
                stadiumInfoList = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println(STADIUM_INFO_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }
    @Override
    public void toPrint(Tournament rfpl) {
        StadiumInfoTable.rfpl = rfpl;
        String tech;

        Object[] stadInfoCompareArr = {
                rfpl.myTeam.name, rfpl.myTeam.stadium.getTitle(), rfpl.myTeam.stadium.getFullCapacity(),
                rfpl.myTeam.stadium.getSimpleCapacity(), rfpl.myTeam.stadium.getSimpleTicketCost(),
                rfpl.myTeam.stadium.getFamilyCapacity(), rfpl.myTeam.stadium.getFamilyTicketCost(),
                rfpl.myTeam.stadium.getFanCapacity(), rfpl.myTeam.stadium.getFanTicketCost(),
                rfpl.myTeam.stadium.getAwayCapacity(), rfpl.myTeam.stadium.getAwayTicketCost(),
                rfpl.myTeam.stadium.getVipCapacity(), rfpl.myTeam.stadium.getVipTicketCost(),
                rfpl.myTeam.stadium.getUsualAverageCapacity(), rfpl.myTeam.stadium.matchTicketRevenueAmount(),
                rfpl.myTeam.stadium.getStadiumExpenses()};

        for (int x = 0, y = 0; x < stadiumInfoList.size(); x++) {

            if(x > 1){
                String[] mass = stadiumInfoList.get(x).split("/");
                if(mass.length == 4){

                    if(y < 2){ tech = (String) stadInfoCompareArr[y]; }

                    else if (y < 14){ tech = "" + (int)stadInfoCompareArr[y]; }

                    else{ tech = "" + (double)stadInfoCompareArr[y]; }

                    mass[3] = Corrector.wordToCenter(tech, LINELENGTH25);
                    stadiumInfoList.set(x, "");

                    for (int i = 0; i < mass.length; i++) {
                        stadiumInfoList.set(x, stadiumInfoList.get(x) + (mass[i] + "|"));
                    }
                    y++;
                }
            }

            System.out.println(stadiumInfoList.get(x));
        }
    }


}
