package botHandler;

import java.util.ArrayList;

public class ListHandler {

    public ArrayList<String> availableBots = new ArrayList<>();
    public ArrayList<String> usedBots = new ArrayList<>();

    public void addBots() {
        availableBots.add("feppe");
        availableBots.add("henke");
        availableBots.add("brede");
        availableBots.add("bugge");
    }

    public ArrayList<String> getAvailableBots() {
        return availableBots;
    }

    public ArrayList<String> getUsedBots() {
        return usedBots;
    }

    public void moveBotToUsedList(String botName) {
        availableBots.remove(botName);
        usedBots.add(botName);
    }
}
