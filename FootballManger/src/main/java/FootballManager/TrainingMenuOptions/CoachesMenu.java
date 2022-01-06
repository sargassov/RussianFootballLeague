package FootballManager.TrainingMenuOptions;


import FootballManager.Tables.CoachesSlotsTable;
import FootballManager.coaches.*;
import FootballManager.manager.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CoachesMenu implements TrainingMenuOptionsInterface {
    String blank  = "                               |            XXXXXXXXXXXXXXXXXXXXXXXXXXX            |";
    @Override
    public void getOption(Tournament rfpl) {
        while(true){
            System.out.print(Corrector.getS(50));
            Corrector.wordUpperCase("Coaches");
            System.out.println("\n\n");
            new CoachesSlotsTable().toPrint(rfpl);
            ArrayList<String> menuString = new ArrayList<>(Arrays.asList("MENU:\n", "==> 1. To add a new coach\n",
                    "==> 2. To delete a coach\n", "==> 3. To add a new player\n",
                    "==> 4. To delete a player\n\n", "==> 0. To quit: "));
            for(int menuStringCounter = 0, space = 54; menuStringCounter < menuString.size(); menuStringCounter++){
                System.out.print(Corrector.getS(space) + menuString.get(menuStringCounter));
                if(space == 54) space = 44;
            }
            int choise = Corrector.inputIntMethod(0, 4);
            if(choise == 0) return;
            else if(choise == 1) addNewCoach(rfpl);
            else if(choise == 2) deleteCoach(rfpl);
            else if(choise == 3) addPlayer(rfpl);
            else deletePlayer(rfpl);
        }

    }

    private void deletePlayer(Tournament rfpl){
        if(rfpl.myTeam.coaches.size() > 1 && onePlayerAtTraining(rfpl)){
            System.out.println("\n" + Corrector.getS(35) + "Choose a player to debar him out of training:");
            int x = 1;
            for(Coach c : rfpl.myTeam.coaches){
                try{
                    System.out.println(Corrector.getS(37) + x + " " + c.getName() + " (" + c.getPlayerOnTrain().name + ")");
                }
                catch (NullPointerException ex){}
                x++;
            }
            while(true){
                int choise = Corrector.inputIntMethod(2, rfpl.myTeam.coaches.size());
                choise--;
                if(rfpl.myTeam.coaches.get(choise).getPlayerOnTrain() != null){
                    rfpl.myTeam.coaches.get(choise).setPlayerOnTrain(null);
                    //rfpl.my_team.coachInterface.set(4 * choise + 4, blank);
                    int tech = choise;
                    for(; tech < 6; tech++){
                        rfpl.myTeam.coachInterface.set(4 * tech + 3, blank);
                        rfpl.myTeam.coachInterface.set(4 * tech + 4, blank);
                    }
                    break;
                }
                else{
                    System.out.println("\n\n\t\t\t\tThere isn't player at this coach\n\n");
                }
            }

        }
        else{
            System.out.println("\n\n\t\t\tYou havent players on the training");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean onePlayerAtTraining(Tournament rfpl) {

        for (Coach c : rfpl.myTeam.coaches){
            if(c.getPlayerOnTrain() != null) return true;
        }

        return false;
    }

    private void addPlayer(Tournament rfpl){
        if(rfpl.myTeam.coaches.size() > 1){
            System.out.println("\n\n" + Corrector.getS(28) + "Choose a coach to add a player to him. Positions must be equal");
            int x = 1;
            for(Coach c : rfpl.myTeam.coaches){
                if(x != 1)
                    System.out.println(Corrector.getS(45) + x + " " + c.getName());
                x++;
            }
            ArrayList<Player> positionPlayers = new ArrayList<Player>();
            int choose = Corrector.inputIntMethod(2, rfpl.myTeam.coaches.size());
            choose--;
            ArrayList<Position> posArr = new ArrayList<>(Arrays.asList(Position.GOALKEEPER,
                    Position.DEFENDER, Position.MIDFIELDER, Position.FORWARD));
            for(Position p : posArr){
                if(rfpl.myTeam.coaches.get(choose).getCoachPos().equals(p)){
                    for(Player player : rfpl.myTeam.playerList) {
                        if(player.position.equals(p) && !playerArleadyChoosedOnTrain(rfpl, player))
                            positionPlayers.add(player);
                    }
                }
            }
            int y = 1, choise;
            if(positionPlayers.size() == 0){
                notEnoughPlayersMessage();
                return;
            }
            for(Player p : positionPlayers){
                System.out.println(Corrector.getS(47) + y + ". " + p.name);
                y++;
            }
            choise = Corrector.inputIntMethod(1, positionPlayers.size());
            choise--;
            Coach currentCoach = rfpl.myTeam.coaches.get(choose);
            currentCoach.setPlayerOnTrain(positionPlayers.get(choise));
            rfpl.myTeam.coaches.set(choose, currentCoach);
        }
        else {
            System.out.println("\n\n\n" + Corrector.getS(45) + "You haven't coaches yet\n\n");
            try {Thread.sleep(2000);}
            catch (InterruptedException ex) {}
        }
    }

    private static void notEnoughPlayersMessage(){
        System.out.println("\n\n" + Corrector.getS(48) + "Not enough players\n\n");
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException ex) {}
    }

    private static boolean playerArleadyChoosedOnTrain(Tournament rfpl, Player player){
        boolean isMatch = false;
        for(Coach c : rfpl.myTeam.coaches){
            if(!(c instanceof Manager)) {
                if(c.getPlayerOnTrain() != null)
                    if (c.getPlayerOnTrain().name.equals(player.name)){
                        isMatch = true;
                        return isMatch;
                    }
            }
        }
        return isMatch;
    }

    private void deleteCoach(Tournament rfpl){
        if(rfpl.myTeam.coaches.size() == 1){
            System.out.println("\n" + Corrector.getS(27) + "You can't delete a coach. You haven't any coaches in your club\n");
            try{ Thread.sleep(2000); }
            catch (InterruptedException ex) {}
        }
        else{
            while(true){
                int coachCount = 2;
                System.out.println("\n\n" + Corrector.getS(50) + "Your Coaches: ");
                for(Coach c : rfpl.myTeam.coaches){
                    if(!(c instanceof Manager)){
                        System.out.println(Corrector.getS(44) + coachCount + ". " + c.getName());
                        coachCount++;
                    }
                }
                System.out.println("\n" + Corrector.getS(28) + "Choose a number of coach to delete him, or" +
                        " press \"0\" to quit: ");
                int coachDeleteChoise = Corrector.inputIntMethod(0, rfpl.myTeam.coaches.size());
                if(coachDeleteChoise == 0) return;
                else if(coachDeleteChoise == 1){
                    System.out.println("\n\n" + Corrector.getS(42) + rfpl.myTeam.coaches.get(0).getName() + " can't be deleted\n");
                    continue;
                }
                coachDeleteChoise--;
                rfpl.myTeam.coaches.remove(coachDeleteChoise);
                int tech = coachDeleteChoise;
                for(; tech < 6; tech++){
                    rfpl.myTeam.coachInterface.set(4 * tech + 3, blank);
                    rfpl.myTeam.coachInterface.set(4 * tech + 4, blank);
                }
                break;
            }
        }
    }

    private void addNewCoach(Tournament rfpl){
        if(rfpl.myTeam.coaches.size() < 6){
            Coach coach;
            System.out.println("\n");
            ArrayList<String> menuString = new ArrayList<>(Arrays.asList("What king of coach do you need:\n",
                    "==> 1. Goalkeeper coach\n", "==> 2. Defender coach\n", "==> 3. Midfielder coach\n",
                    "==> 4. Forward coach\n\n", "==> 0. Back to menu: "));
            for(int menuStringCounter = 0, space = 42; menuStringCounter < menuString.size(); menuStringCounter++){
                System.out.print(Corrector.getS(space) + menuString.get(menuStringCounter));
                if(space == 42) space = 44;
            }

            int choise = Corrector.inputIntMethod(0, 4);
            if(choise == 0) return;
            else if(choise == 1) coach = new GoalkeeperCoach("Goalkeeper Coach");
            else if(choise == 2) coach = new DefenderCoach("Defender Coach");
            else if(choise == 3) coach = new MidfielderCoach("Midfielder Coach");
            else coach = new ForwardCoach("Forward Coach");
            //coach.setSalary(300000);

            System.out.println("\n");
            menuString = new ArrayList<>(Arrays.asList("What type of " + coach.getName() + " you need?\n",
                    "==> 1. Local " + coach.getName() + " 1 mln. EURO\n", "==> 2. Profi "+ coach.getName() + " 3 mln. EURO\n",
                    "==> 3. World " + coach.getName() + " 5 mln. EURO\n\n", "==> 0. To qiut: "));
            for(int menuStringCounter = 0, space = 39; menuStringCounter < menuString.size(); menuStringCounter++){
                System.out.print(Corrector.getS(space) + menuString.get(menuStringCounter));
                if(space == 39) space = 38;
            }

            choise = Corrector.inputIntMethod(0, 3);
            if(choise == 0) return;
            menuString = new ArrayList<>(Arrays.asList("Local ", "Profi ", "World "));
            choise--;
            for(int cycleCount = 0, costCoachCount = 1_000_000; cycleCount < 3; cycleCount++, costCoachCount += 2_000_000) {
                if (choise == cycleCount) {
                    if (rfpl.myTeam.wealth >= costCoachCount) {
                        coach.setName(menuString.get(cycleCount) + coach.getName());
                        rfpl.myTeam.coaches.add(coach);
                        rfpl.myTeam.wealth -= costCoachCount;
                        rfpl.myTeam.personalExpenses -= costCoachCount;
                    } else MessageClass.notEnoughMoney();
                }
            }
            if(coach.getName().contains("Local")) {
                coach.setLevelOfCoach(LevelOfCoach.LOCAL);
            }
            else if(coach.getName().contains("Profi")){
                coach.setLevelOfCoach(LevelOfCoach.PROFI);
                //coach.setSalary(500000);
            }
            if(coach.getName().contains("World")){
                coach.setLevelOfCoach(LevelOfCoach.WORLD);
                //coach.setSalary(1_000_000);
            }
        }
        else {
            System.out.println(Corrector.getS(40) + "You can't buy new coaches. Your bagage is full!\n\n");
            return;
        }
    }

}
