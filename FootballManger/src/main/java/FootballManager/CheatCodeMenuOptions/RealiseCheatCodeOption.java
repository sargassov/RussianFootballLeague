package FootballManager.CheatCodeMenuOptions;

import FootballManager.cheats.*;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

import java.util.Scanner;

public class RealiseCheatCodeOption implements CheatCodeMenuOptionsInterface{

    private static final int sVal = 40;
    private int counterOfTheCheat = 0;
    private Scanner scanner = new Scanner(System.in);
    private Cheat[] cheatCodePack = {
            new CreateLeoMessiCheat(),
            new NotInjuriesCheat(),
            new OneMillonEuroCheat(),
            new ThousandFansCheat(),
            new SponsorCheat()
    };

    @Override
    public void getOption(Tournament rfpl) {
        System.out.print(Corrector.getS(sVal));
        Corrector.wordUpperCase("realise chaet code");
        System.out.println("\n\n\n");

        System.out.println(Corrector.getS(sVal) + "ENTER A WANTED CHEAT: ");

        String currentCheatCode = scanner.nextLine();
        if(checkTheCheat(currentCheatCode)){
            cheatCodePack[counterOfTheCheat].toCheat();
        }
        else {
            System.out.println(Corrector.getS(sVal) + "Incorrectly cheat");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    private boolean checkTheCheat(String currentCheatCode) {

        counterOfTheCheat = 0;
        for(Cheat cheat : cheatCodePack){
            if(cheat.getCheatCodePassword().equals(currentCheatCode)) {
                return true;
            }
            counterOfTheCheat++;
        }

        return false;
    }
}
