package FootballManager.StadiumMenuOptions;


import FootballManager.Tables.BuildNewSectorsTable;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

public class BuildSittingPlaceOption implements StadiumMenuOptionInterface{

    private static int sVal = 40;
    private static Tournament rfpl;
    private static int costCoeff = 1000;

    @Override
    public void getOption(Tournament rfpl) {

        BuildSittingPlaceOption.rfpl = rfpl;
        System.out.print("\n\n\n\n" + Corrector.getS(sVal));
        Corrector.wordUpperCase("build new sitting place " + rfpl.myTeam.name);
        System.out.println("\n\n\n");

        new BuildNewSectorsTable().toPrint(rfpl);

        int newCapacity = Corrector.inputIntMethod(1000, 1000000);
        if(!financeControll(newCapacity)) return;

        System.out.println("\n\n" + Corrector.getS(sVal) + "Do you want to add " + newCapacity +
                " for " + (double) (newCapacity / costCoeff) + "M EURO?");
        int choise = Corrector.inputIntMethod(0, 1);
        if (choise == 0) return;

        rfpl.myTeam.stadium.setFullCapacity(rfpl.myTeam.stadium.getFullCapacity() + newCapacity);
        rfpl.myTeam.wealth -= (double)(newCapacity / costCoeff);
        rfpl.myTeam.stadiumExpenses -= (double) (newCapacity / costCoeff);

        new BuildNewSectorsTable().toPrint(rfpl);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private boolean financeControll(int newCapacity) {
        if(rfpl.myTeam.wealth >= (double) (newCapacity / costCoeff)){
            return true;
        }
        else{
            System.out.println(Corrector.getS(sVal) + "Your club hasn't credits to this transaction!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
