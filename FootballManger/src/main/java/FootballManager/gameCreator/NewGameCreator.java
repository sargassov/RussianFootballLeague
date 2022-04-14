package FootballManager.gameCreator;


import FootballManager.manager.*;
import FootballManager.strategies.Strategy;

import java.io.IOException;

public class NewGameCreator {

    private Tournament rfpl;
    private static final String league = "Russian Premier League";


    public NewGameCreator(){
        rfpl = new Tournament(league);
    }


    public void createGame() throws IOException, InterruptedException {

        new OpenSource(rfpl).unpack();
        Thread.sleep(400);

        Strategy.strategyCreator(rfpl);
        Strategy.autoStrategyCreator();
        Strategy.capitanDeterminer();
        Strategy.powerTeamCounter();
        MenuClass.newGameMenu(rfpl);
        YouthAcademy.youthAcademyPlayersAdd(rfpl);
        FootballCalendar.sheduleCreator(rfpl);
        FootballCalendar.editCalendar();
        Interface.createInterfaces(rfpl);
        Interface.readCoachInterface();


        MenuClass.gameMenu();
    }

}
