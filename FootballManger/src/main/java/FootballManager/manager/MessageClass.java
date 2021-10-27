package FootballManager.manager;
import static java.lang.System.out;

public class MessageClass {
    public static void WelcomeMessage(){
        out.println("\n\n\n\n\n\t\t\t\t\tF O O T B A L L   M A N A G E R");
        out.print("\t\t\t\t\t   Russian League 2019/2020\n\n\n\n\n\n\n\n\n\n");
        for (short x = 0; x < 10; x++) {
            for (short y = 0; y < 120; y++) {
                if (x % 3 == 0) out.print("_");
                if (x == 2 && y == 0) out.print("\t\t\t\t\t\t 1. Start Game");
                if (x == 5 && y == 0) out.print("\t\t\t\t\t\t  2. Load Game");
                if (x == 8 && y == 0) out.print("\t\t\t\t\t\t   0. Exit");
            }
            out.print("\n");
        }
        out.println("\n\n");
    }
    public static void incorrectName(){
        out.print("\n\n\t\t\tIncorrect name of the club, Write again: ");
    }

    public static void notEnoughMoney() {
        out.println("\n\n\t\t\t\t\tYour club haven't money to make this offer\n\n");
    }
}
