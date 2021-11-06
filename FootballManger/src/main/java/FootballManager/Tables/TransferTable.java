package FootballManager.Tables;


import FootballManager.manager.*;

public class TransferTable extends Table implements Data {

    private static Interface cloneTransferTable;
    private static String emptySpace51 = "                                                   ";
    private int yourClubList;
    private int alterTeam;
    private int alterClubList;

    public TransferTable(int alterTeam, int yourClubList, int alterClubList){
        this.alterClubList = alterClubList;
        this.alterTeam = alterTeam;
        this.yourClubList = yourClubList;
    }

    @Override
    public void toPrint(Tournament rfpl) {
        cloneTransferTable = new Interface(rfpl.transferPrintInterface);
        for(int x = 0; x < cloneTransferTable.fields.size(); x++){
            if(x == 2) enterTeamsIntable(rfpl, alterTeam);
            if(x > 6) {
                enterPlayerIntable(rfpl, yourClubList, alterTeam, alterClubList, x);
                yourClubList++;
                if(yourClubList == rfpl.myTeam.playerList.size()) yourClubList = 0;
                alterClubList++;
                if(alterClubList == rfpl.teams[alterTeam].playerList.size()) alterClubList = 0;
            }
            System.out.println(cloneTransferTable.fields.get(x));
        }

    }

    private static void enterTeamsIntable(Tournament rfpl, int alterTeam){
        String name = rfpl.myTeam.name;
        name = enterWealth(rfpl, name);
        String alterTeamName = rfpl.teams[alterTeam].name;
        alterTeamName = enterWealth(rfpl, alterTeamName);
        String tech = cloneTransferTable.fields.get(2);
        name = Corrector.wordToCenter(name, 27);
        alterTeamName = Corrector.wordToCenter(alterTeamName, 27);
        tech = tech.replaceFirst(del,name);
        tech = tech.replaceFirst(del,alterTeamName);
        cloneTransferTable.fields.set(2, tech); ////////////////ТУТ ПРОБЛЕМЫ

    }

    private static void enterPlayerIntable(Tournament rfpl, int yourClubList, int alterTeam, int alterClubList, int str){
        String tech = cloneTransferTable.fields.get(str);
        Player yourPlayer = rfpl.myTeam.playerList.get(yourClubList);
        Player alterPlayer = rfpl.teams[alterTeam].playerList.get(alterClubList);
        String yourPlayerInfo = enterPlayerInfo(yourPlayer);
        String alterPlayerInfo = enterPlayerInfo(alterPlayer);
        tech = tech.replaceFirst(emptySpace51, yourPlayerInfo);
        tech = tech.replaceFirst(emptySpace51, alterPlayerInfo);
        cloneTransferTable.fields.set(str, tech);
    }

    private static String enterWealth(Tournament rfpl, String name){
        for(Team team : rfpl.teams){
            if(team.name.equals(name)){
                name = name + " " + team.wealth + " mln.";
            }
        }
        return name;
    }

    private static String enterPlayerInfo(Player player){
        String info = " " + player.name;
        info = Corrector.inspacer(info) + " ";
        info += player.power + Corrector.getS(4);
        info += Corrector.posInString(player.position);
        info += Corrector.getS(8 - Corrector.posInString(player.position).length());
        info += player.price;
        info = string51Chars(info);
        return info;
    }

    private static String string51Chars(String str){
        if(str.length() < 51){
            for(int x = str.length(); x < 51; x++){
                str += " ";
            }
        }
        return str;
    }
}
