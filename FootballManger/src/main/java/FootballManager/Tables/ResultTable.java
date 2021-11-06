package FootballManager.Tables;

import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ResultTable extends Table implements Data{

    private static Tournament rfpl;
    private final String resultTablePath = "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\results.txt";
    private final static String RESULTS_FILE_NOT_FOUND = "RESULTS FILE NOT FOUND";
    private List<String> resultTableList;
    private int numberOfTeam = 0;
    private int highOrLowRow = 0;

    public ResultTable(){

        Path path = Paths.get(resultTablePath);
        if(Files.exists(path)){
            try {
                resultTableList = Files.readAllLines(Paths.get(resultTablePath), StandardCharsets.UTF_8);
                creatrResultList();
            } catch (IOException e) {
                System.out.println(RESULTS_FILE_NOT_FOUND);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toPrint(Tournament rfpl) {

        for(int x = 0; x < resultTableList.size(); x++){


            String[] mass = resultTableList.get(x).split("/");
            if(x > 2 && mass.length > 1){

                    if(x % 3 == 0){
                        mass[1] = rfpl.teams[numberOfTeam].name +
                                Corrector.getS(mass[1].length() - rfpl.teams[numberOfTeam].name.length());
                    }

                    for(int y = 2; y < mass.length; y++){
                        if(x % 3 ==0)
                            if(rfpl.resultsMass[numberOfTeam][y - 2] != null)
                                mass[y] = rfpl.resultsMass[numberOfTeam][y - 2];

                        if(x % 3 == 1)
                            if(rfpl.resultsMass[y - 2][numberOfTeam] != null){
                                //mass[y] = rfpl.resultsMass[y - 2][numberOfTeam];
                                String str = rfpl.resultsMass[y - 2][numberOfTeam].trim();
                                String changer[] = str.split(":");
                                mass[y] = Corrector.wordToCenter(changer[1] + ":" + changer[0], mass[y].length());
                            }



                    }




            }


            if(mass.length > 1) resultTableList.set(x, massInLine(mass));
            System.out.println(resultTableList.get(x));
            if( x > 4 && x % 3 == 2){
                numberOfTeam++;
            }
        }

    }

    private void creatrResultList() {
        rfpl.resultsMass = new String[16][16];

        for(int a = 0; a < rfpl.resultsMass.length; a++){
            for(int b = 0; b < rfpl.resultsMass[a].length; b++){
                if(a != b){

                    for(int x = 0; x < rfpl.shedule.size(); x++){
                        for(int y = 0; y < rfpl.shedule.get(x).size(); y++){
                            if(rfpl.shedule.get(x).get(y).home.equals(rfpl.teams[a]) &&
                                    rfpl.shedule.get(x).get(y).away.equals(rfpl.teams[b]) &&
                                    rfpl.shedule.get(x).get(y).itWas){

                                rfpl.resultsMass[a][b] = " " + rfpl.shedule.get(x).get(y).homeScore +
                                        ":" + rfpl.shedule.get(x).get(y).awayScore + " ";

                            }
                        }
                    }
                }
            }
        }




    }

    public static void setRfpl(Tournament rfpl) {
        ResultTable.rfpl = rfpl;
    }
}
