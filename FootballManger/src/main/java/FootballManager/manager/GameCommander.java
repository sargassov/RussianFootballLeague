package FootballManager.manager;

import java.util.Scanner;

public class GameCommander {
    public static void NewGameCommander(int command){
        if (command < 0 && command > 2) {
            System.out.print("\n\n\t\t\tincorrcect command. Write again: ");
            Scanner in = new Scanner(System.in);
            in.nextInt(command);
            GameCommander.NewGameCommander(command);
        }
    }
}
