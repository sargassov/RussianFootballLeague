package FootballManager.TeamMenuOptions;

import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PlayerEditorOption implements TeamMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        System.out.print("\n\n" + Corrector.getS(50));
        Corrector.wordUpperCase("editor");
        System.out.println("\n\n\t\tYou can create a new player and add to your team if your team have enough credits\n\n");
        Scanner sc = new Scanner(System.in);
        String line = "";
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(" surname", " name", " year of birth",
                " nationality (only 3 letters)", " position (G/D/M/F)", " goalkeeper ability (1 - 99)", " defender ability (1 - 99)",
                " midfielder ability (1 - 99)", " forward ability (1 - 99)", " captain ability (1 - 99)", " number (only unique number)"));
        for(int x = 0; x < 11; x++){
            int num = 0;
            System.out.print("Enter a" + arr.get(x) + ": ");
            if(x == 0 || x == 1 || x == 3 || x == 4){
                String written = Corrector.InputStringMethod();
                //out.println("method written = " + written);
                if(x == 3) {
                    written = isRightValueOfSymbols(written, 3);
                    written += ("/" + rfpl.myTeam.name);
                }
                if(x == 4) written = isRightValueOfSymbols(written, 1, true);
                line += written;
            }
            else if (x == 2) {num = Corrector.inputIntMethod(1980, 2004); line += num;}
            else if (x < 10) {num = Corrector.inputIntMethod(1, 99); line += num;}
            else line += guessNumber(rfpl);


            if(x == 0) line += " ";
            if(x > 0 && x < 10) line += "/";
            if (x == 8) line += "0/";
            System.out.println(line);
        }
        playerCreator(rfpl, line);
    }

    public String guessNumber (Tournament rfpl){
        short num = (short)Corrector.inputIntMethod(1, 99);
        String line = "";
        ArrayList<Integer> closedNumbers = (ArrayList<Integer>) rfpl.myTeam.playerList.stream().map(p->p.number).sorted()
                .collect(Collectors.toList());
        ArrayList<Short> openNumbers = new ArrayList<>();
        for(short i = 1; i < 100; i++){
            if(!closedNumbers.contains(i)) openNumbers.add(i);
        }
        while(!openNumbers.contains(num)){
            System.out.print(Corrector.getS(48) + "You can take only free unique number: ");
            int lineCounter = 0;
            for(Short sh : openNumbers){
                if(lineCounter % 10 == 0) System.out.print("\n" + Corrector.getS(50) + sh + " ");
                else System.out.print(sh + " ");
                lineCounter++;
            }
            num = (short)Corrector.inputIntMethod(1, 99);
        }
        line += num;
        System.out.println(line);
        return line;
    }

    private void playerCreator (Tournament rfpl, String line){
        Player player = new Player(line);
        System.out.println("\t\t\t\tYou were created a new player " + player.name + ". Price for this player is: "
                + player.price + " Euro.");
        if(rfpl.myTeam.wealth >= player.price){
            System.out.println("\n\n\t\t\t\t\t\tDo you want to buy this player\n\t\t\t\t\t\t\"1\" - Yes\n\t\t\t\t\t\t\"0\" - No");
            int choise = Corrector.inputIntMethod(0, 1);
            if(choise == 1) {
                rfpl.myTeam.playerList.add(player);
                rfpl.myTeam.wealth -= player.price;
                rfpl.myTeam.transferExpenses -= player.price;
                new ListPlayerOption().getOption(rfpl);
            }
        }
        else System.out.println("You can't buy this player. Not enough credits");
        return;
    }

    private String isRightValueOfSymbols(String written, int value){
        while(written.length() != value || !Corrector.isTheString(written)){
            System.out.println("\n\n\t\t\t\tYou wrote something incorrectly. Try again: ");
            Scanner scanner = new Scanner(System.in);
            written = scanner.nextLine();
        }
        return written;
    }
    private String isRightValueOfSymbols(String written, int value, boolean b) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> correctList = new ArrayList<>(Arrays.asList("G", "g", "D", "d", "M", "m", "F", "f"));
        do{
            if(!correctList.contains(written)){
                System.out.println("\n\n\t\t\t\tYou wrote something incorrectly. Try again: ");
                written = scanner.nextLine();
            }
        }   while(!correctList.contains(written));
        if (written.equals("G") || written.equals("g")) written = "Gk";
        else if (written.equals("D") || written.equals("d")) written = "Def";
        else if (written.equals("M") || written.equals("m")) written = "Mid";
        else written = "Forw";
        return written;
    }
}
