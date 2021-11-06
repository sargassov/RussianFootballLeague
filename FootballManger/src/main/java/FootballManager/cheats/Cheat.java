package FootballManager.cheats;

import FootballManager.manager.Tournament;

public class Cheat {

    protected String cheatCodePassword;
    protected String characters;
    protected static Tournament rfpl;


    public void toCheat(){}

    public String getCharacters() {
        return characters;
    }

    public String getCheatCodePassword() {
        return cheatCodePassword;
    }

    public static void setRfpl(Tournament rfpl) {
        Cheat.rfpl = rfpl;
    }
}
