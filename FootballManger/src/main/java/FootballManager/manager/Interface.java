package FootballManager.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Interface {

    private static Tournament rfpl;
    public List<String> fields;
    private final static String[] pathArr = {"FootballManger\\src\\main\\java\\FootballManager\\textFiles\\GameMenuInterface.txt",
            "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\TECH.txt",
            "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\TeamInterface.txt",
            "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\TrainingInterface.txt",
            "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\TransferInterface.txt",
            "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\CalendarInterface.txt",
            "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\financeMenu.txt",
            "FootballManger\\src\\main\\java\\FootballManager\\textFiles\\stadiumInterface.txt",
            "src\\textFiles\\TECH.txt", "src\\textFiles\\TECH.txt"};

    public Interface(){}

    public Interface(String fileName) {

        try {
            Path path = Paths.get(fileName);
            if(Files.exists(path)){
                fields = Files.readAllLines(path);
            }

        } catch (IOException e) {
            e.printStackTrace();
            out.println("\n\n\t\t\tLOAD FILE " + fileName + " \n\n");
            exit(0);
        }
    }

    public static void createInterfaces(Tournament tournament){

        rfpl = tournament;

        rfpl.interfaces = new ArrayList<>();
        for(String path : pathArr){
            rfpl.interfaces.add(new Interface(path));
        }

    }

    public Interface(Interface other){
        int x = 0;
        this.fields = new ArrayList<>();
        for(String s : other.fields){
            this.fields.add(s);
            x++;
        }
    }

    public static void readCoachInterface() throws IOException {
        try (FileReader coachInt = new FileReader("FootballManger\\src\\main\\java\\FootballManager\\textFiles\\coach.txt")) {
            rfpl.myTeam.coachInterface = new ArrayList<>();
            BufferedReader reader = new BufferedReader(coachInt);
            String line = reader.readLine();
            while (line != null) {
                rfpl.myTeam.coachInterface.add(line);
                line = reader.readLine();
            }
        }
    }

}
