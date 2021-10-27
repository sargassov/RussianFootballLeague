package FootballManager.TeamMenuOptions;


import FootballManager.manager.Corrector;
import FootballManager.manager.Player;
import FootballManager.manager.Tournament;

import java.util.ArrayList;

public class ListPlayerOptionSorts {
    public static void sort(Tournament rfpl, int choise) {
        int size;
        for(int x = 0; x < rfpl.myTeam.playerList.size() - 1; x++){
            for(int y = 0; y < rfpl.myTeam.playerList.size() - x - 1; y++){
                String field1 = chooseStringField(rfpl.myTeam.playerList, y, choise);
                String field2 = chooseStringField(rfpl.myTeam.playerList, y + 1, choise);
                char[] first = field1.toCharArray();
                char[] second = field2.toCharArray();
                if(field1.length() < field2.length()){ size = field1.length(); }
                else size = field2.length();
                for(int z = 0; z < size; z++){
                    if(first[z] > second[z]){
                        Player temp = rfpl.myTeam.playerList.get(y);
                        rfpl.myTeam.playerList.set(y, rfpl.myTeam.playerList.get(y + 1));
                        rfpl.myTeam.playerList.set(y + 1, temp);
                        break;
                    }
                    else if (first[z] < second[z]){
                        break;
                    }
                }
            }
        }
    }

    public static void sort(Tournament rfpl, int choise, int difParametr) {
        for(int x = 0; x < rfpl.myTeam.playerList.size() - 1; x++){
            for(int y = 0; y < rfpl.myTeam.playerList.size() - x - 1; y++){
                int first = chooseintField(rfpl.myTeam.playerList, y, choise);
                int second = chooseintField(rfpl.myTeam.playerList, y + 1, choise);
                if(choise == 3) { if(first <= second) continue; }
                else { if(first >= second) continue; }
                Player temp = rfpl.myTeam.playerList.get(y);
                rfpl.myTeam.playerList.set(y, rfpl.myTeam.playerList.get(y + 1));
                rfpl.myTeam.playerList.set(y + 1, temp);
            }
        }
    }

    public static void sort(Tournament rfpl, boolean difParametr){
        for(int x = 0; x < rfpl.myTeam.playerList.size() - 1; x++){
            for(int y = 0; y < rfpl.myTeam.playerList.size() - x - 1; y++){
                if(! (rfpl.myTeam.playerList.get(y).isInjury) && rfpl.myTeam.playerList.get(y + 1).isInjury){
                    Player temp = rfpl.myTeam.playerList.get(y);
                    rfpl.myTeam.playerList.set(y, rfpl.myTeam.playerList.get(y + 1));
                    rfpl.myTeam.playerList.set(y + 1, temp);
                }
            }
        }

    }

    public static void sort(Tournament rfpl) {
        for(int x = 0; x < rfpl.myTeam.playerList.size() - 1; x++){
            for(int y = 0; y < rfpl.myTeam.playerList.size() - x - 1; y++){
                if(rfpl.myTeam.playerList.get(y).strategyPlace == -100){
                    Player temp = rfpl.myTeam.playerList.get(y);
                    rfpl.myTeam.playerList.set(y, rfpl.myTeam.playerList.get(y + 1));
                    rfpl.myTeam.playerList.set(y + 1, temp);
                }
                else if((rfpl.myTeam.playerList.get(y).strategyPlace > rfpl.myTeam.playerList.get(y + 1).strategyPlace) &&
                        rfpl.myTeam.playerList.get(y).strategyPlace != -100 && rfpl.myTeam.playerList.get(y + 1).strategyPlace != -100){
                    Player temp = rfpl.myTeam.playerList.get(y);
                    rfpl.myTeam.playerList.set(y, rfpl.myTeam.playerList.get(y + 1));
                    rfpl.myTeam.playerList.set(y + 1, temp);
                }
            }
        }
    }

    public static void sort(Tournament rfpl, double difParametr) {
        for(int x = 0; x < rfpl.myTeam.playerList.size() - 1; x++){
            for(int y = 0; y < rfpl.myTeam.playerList.size() - x - 1; y++){
                if(rfpl.myTeam.playerList.get(y).price < rfpl.myTeam.playerList.get(y + 1).price){
                    Player temp = rfpl.myTeam.playerList.get(y);
                    rfpl.myTeam.playerList.set(y, rfpl.myTeam.playerList.get(y + 1));
                    rfpl.myTeam.playerList.set(y + 1, temp);
                }
            }
        }
    }

    private static int chooseintField(ArrayList<Player> list, int place, int choise){
        if(choise == 3) return list.get(place).number;
        else if(choise == 5) return list.get(place).gkAble;
        else if(choise == 6) return list.get(place).defAble;
        else if(choise == 7) return list.get(place).midAble;
        else if(choise == 8) return list.get(place).forwAble;
        else if(choise == 9) return list.get(place).captainAble;
        else if(choise == 11) return list.get(place).trainingAble;
        else if(choise == 12) return list.get(place).yearBirth;
        else if(choise == 14) return list.get(place).power;
        else if(choise == 15) return list.get(place).tire;
        else return list.get(place).timeBeforeTreat;
    }

    private static String chooseStringField(ArrayList<Player>list, int place, int choise){
        if(choise == 1) return list.get(place).name;
        else if(choise == 2) return list.get(place).natio;
        else return Corrector.posInString(list.get(place).position);
    }
}