package FootballManager.TrainingMenuOptions;


import FootballManager.coaches.Coach;
import FootballManager.coaches.CoachProgram;
import FootballManager.coaches.Manager;
import FootballManager.manager.Corrector;
import FootballManager.manager.Tournament;

public class TrainingProgramsMenu implements TrainingMenuOptionsInterface {
    @Override
    public void getOption(Tournament rfpl) {
        while(true){
            System.out.println();
            System.out.print(Corrector.getS(45));
            Corrector.wordUpperCase("training programs");

            boolean isCoachHavePlayerToCoach = isCoachHavePlayerToCoachMETHOD(rfpl);

            if(rfpl.myTeam.coaches.size() > 1 && isCoachHavePlayerToCoach)welcomeMessage(rfpl);
            else {
                goodbyeMessage(rfpl);
                return;
            }

            while(true){
                System.out.println("\n\n" + Corrector.getS(33) + "If you need to change level of training, choose a current coach\n" +
                        Corrector.getS(51) + "or press \"0\" to quit: ");
                int choise = Corrector.inputIntMethod(0, rfpl.myTeam.coaches.size());
                if(choise == 0) return;
                if(choise != 1){
                    if(rfpl.myTeam.coaches.get(--choise).getPlayerOnTrain() != null) {
                        System.out.println("\n\n" + Corrector.getS(24) +
                                "Choose a level of intensity of training of " + rfpl.myTeam.coaches.get(choise).getName() + " (" +
                                rfpl.myTeam.coaches.get(choise).getPlayerOnTrain().name + ") " +
                                "\n" +  Corrector.getS(54) + "\"1\" - Standart" +
                                "\n" +  Corrector.getS(54) + "\"2\" - Hard" +
                                "\n" +  Corrector.getS(54) + "\"3\" - Intensive");
                        int choise2 = Corrector.inputIntMethod(1, 3);
                        if(choise2 == 1) rfpl.myTeam.coaches.get(choise).setCurrentCoachProgram(CoachProgram.STANDART);
                        else if(choise2 == 2) rfpl.myTeam.coaches.get(choise).setCurrentCoachProgram(CoachProgram.HARD);
                        else rfpl.myTeam.coaches.get(choise).setCurrentCoachProgram(CoachProgram.INTENSIVE);
                        break;
                    }
                    else{
                        System.out.println("\n\n" + Corrector.getS(48) + "This coach hasn't players yet\n\n");
                    }
                }
                else System.out.println("\n\n" + Corrector.getS(47)
                        + rfpl.myTeam.coaches.get(0).getName() + " can't be a coach\n\n");


            }
        }



    }

    private static boolean isCoachHavePlayerToCoachMETHOD(Tournament rfpl){
        for(Coach c : rfpl.myTeam.coaches){
            if(!(c instanceof Manager) && c.getPlayerOnTrain() != null){
                return true;
            }
        }
        return false;
    }


    private static String coachProgramToString(Coach coach){
        if(coach.getCurrentCoachProgram() == CoachProgram.STANDART){return "Standart"; }
        else if(coach.getCurrentCoachProgram() == CoachProgram.HARD){return "Hard"; }
        else {return "Intensive"; }
    }

    private static void welcomeMessage(Tournament rfpl){
        System.out.println("\n\n" + Corrector.getS(42) + "Choose a training program for each coach:");
        int x = 1;
        for(Coach c : rfpl.myTeam.coaches){
            if(!(c instanceof Manager)){
                if(c.getPlayerOnTrain() != null){
                    System.out.println(Corrector.getS(35) + x + "\t" + c.getName() + "\t(" + c.getPlayerOnTrain().name + ")\t" +
                            coachProgramToString(c));
                }
            }
            x++;
        }
    }

    private static void goodbyeMessage(Tournament rfpl){
        System.out.println("\n\n" + Corrector.getS(35) + "Your club hasn't any coaches, or any player to coach yet\n\n");
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException ex){}
    }
}
