package FootballManager.cheats;

import FootballManager.Tables.SponsorsTable;
import FootballManager.finance.Sponsor;

public class SponsorCheat extends Cheat{

    public SponsorCheat() {
        this.cheatCodePassword = "iwantagreatsponsor";
        this.characters = "You were activate a Super Sponsor. You can offer a contract if don't chanhe sponsor before";
    }

    @Override
    public void toCheat() {
        rfpl.sponsorList.add(new Sponsor("Хал'Ва/4000000/4000000/2000000/3500000/1800000/100000000"));
        SponsorsTable.setCheatActive(true);
    }

    @Override
    public String getCharacters() {
        return super.getCharacters();
    }

    @Override
    public String getCheatCodePassword() {
        return super.getCheatCodePassword();
    }
}
