package FootballManager.manager;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Corrector {
    public static int inputIntMethod(int minimal, int maximal){
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("\n\n\t\t\t\tYou weren't entered a number! Try again!\n\n");
                sc.next();
            }
            number = sc.nextInt();
            if(number < minimal || number > maximal){
                System.out.println("\n\n\t\t\t\tThe choise is out of the range between " + minimal + " and " + maximal + ". Try again!");
                continue;
            }
        } while (number < minimal || number > maximal);
        return number;
    }

    public static <T> void notNullChecking(T obj){
        try{
            if(obj == null){
                throw new NullPointerException();
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public static String posInString(Position position){
        if(position.equals(Position.GOALKEEPER)) return "Gk";
        else if(position.equals(Position.MIDFIELDER)) return "Mid";
        else if(position.equals(Position.DEFENDER)) return "Def";
        else return "Forw";

    }

    public static Position stringInPos(String str){
        if(str.equals("Gk")) return Position.GOALKEEPER;
        else if(str.equals("Def")) return Position.DEFENDER;
        else if(str.equals("Mid")) return Position.MIDFIELDER;
        else return Position.FORWARD;

    }


    public static int inputSomeIntMethod(int[] ints){
        Scanner sc = new Scanner(System.in);
        int number;

        while(true){
            number = sc.nextInt();
            for(int i : ints)
                if(number == i)
                    return number;
            System.out.print("\n\n\t\t\t\tThe choise is out of the list: ");
            for(int i : ints){
                System.out.println(i + " ");
            }
            System.out.println(". Try again!");
        }
    }


    public static String InputStringMethod() {
        Scanner sc = new Scanner(System.in);
        String line, temp;
        temp = sc.nextLine();
        while(!isTheString(temp) || temp.equals("")) {
            System.out.println("\n\n\t\t\t\tYou were written an incorrect line. Try again!\n\n");
            temp = sc.next();
        }
        line = temp;
        return line;
    }

    public static int indexOfArrayTeam(Team[] array, Team t){
        for (int i = 0; i < array.length; i++) {
            if(array[i].name.equals(t.name)){
                return i;
            }
        }
        return -1;
    }

    public static int inputNumberFromTheList(ArrayList<Integer> list){
        Scanner sc = new Scanner(System.in);
        boolean isFound = false;
        int number;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("\n\n\t\t\t\tYou weren't entered a number! Try again!\n\n");
                sc.next();
            }
            number = sc.nextInt();
            number--;
            for(int s: list){
                System.out.print(s + " ");
                if(number == s) {
                    isFound = true;
                    break;
                }
            }
            if(!isFound) System.out.println("\n\n\t\t\t\tYou choosed an incorrect player! Try again!\n\n");
        } while (!isFound);
        return number;
    }

    public static int inputIntFromTheList(ArrayList<Integer> list){
        Scanner sc = new Scanner(System.in);
        boolean isFound = false;
        int number;
        do {
            number = sc.nextInt();
            for(int s: list){
                System.out.print(s + " ");
                if(number == s) {
                    isFound = true;
                    break;
                }
            }
            if(!isFound) System.out.println("\n\n\t\t\t\tYou choosed an incorrect! Try again!\n\n");
        } while (!isFound);
        return number;
    }

    public static String getS(int value){
        String string = "";
        for(short x = 0; x < value; x++){
            string += " ";
        }
        return string;
    }

    public static void wordUpperCase(String word){
        for (char c : word.toCharArray()) {
            System.out.print(Character.toUpperCase(c) +  " ");
        }
    }

    public static String inspacer(String word){
        int len = 27 - word.length();
        String newWord = new String(word.toCharArray());
        for(int count = 0; count < len; count++){
            newWord += " ";
        }
        return newWord;
    }

    public static double coefficient(double val) {

        String temp = "" + val;
        val = Double.parseDouble((temp.substring(0, temp.indexOf('.') + 2)));

        return val;
    }

    public static String Inspacer4Sym(String word){
        int len = 4 - word.length();
        String newWord = new String(word.toCharArray());
        for(int count = 0; count < len; count++){
            newWord += " ";
        }
        return newWord;
    }

    public static boolean isTheString(String tech){
        //int interval;
        for(int x = 0; x < tech.length(); x++){
            int interval = (int)(tech.toCharArray()[x]);
            if ((interval != 32 && interval < 65) || (interval > 90 && interval < 97) || (interval > 122)){
                return false;
            }
        }
        return true;
    }

    public static String wordToCenter(String tech, int longWord){
        int longCurrentWord = tech.length();
        for(int x = 0; x < longWord - longCurrentWord; x++){
            if(x % 2 == 0) tech = " " + tech;
            else tech += " ";
        }
        return tech;
    }

    public static Integer getNumber(Tournament rfpl){
        short number;
        ArrayList<Integer>usedNumbers = new ArrayList<>(rfpl.myTeam.playerList.stream()
                .map(p->p.number).sorted().collect(Collectors.toList()));
        ArrayList<Short>freeNumbers = new ArrayList<>();
        for(short x = 1; x < 100; x++) if(!usedNumbers.contains(x)) freeNumbers.add(x);
        do{
            number = (short) inputIntMethod(1, 99);
            if(!freeNumbers.contains(number)){
                System.out.println("Wrong number! You can take only: ");
                for(int y = 0; y < freeNumbers.size(); y++){
                    System.out.print(freeNumbers.get(y) + " ");
                    if(y % 9 == 0 && y != 0) System.out.println();
                }
            }
        } while(!freeNumbers.contains(number));
        return (int) number;
    }
}
