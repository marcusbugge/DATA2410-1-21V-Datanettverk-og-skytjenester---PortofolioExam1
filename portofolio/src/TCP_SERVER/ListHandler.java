package TCP_SERVER;

import java.util.ArrayList;

/**
 *
 * Takes care of all lists and the functions that handles offline and online bots by name.
 *
 */

public class ListHandler {

    public static ArrayList<String> availableBots = new ArrayList<>();
    public static ArrayList<String> usedBots = new ArrayList<>();


    public void addBots() {
        // Adding supported bots
        availableBots.add("Feppe");
        availableBots.add("Henke");
        availableBots.add("Brede");
        availableBots.add("Bugge");
    }

    public ArrayList<String> getAvailableBots() {
        return availableBots;
    }

    public ArrayList<String> getUsedBots() {
        return usedBots;
    }

    // Moves the connected from available to online
    public void moveBotToUsedList(String botName) {
        String name = capitalizeFirstLetter(botName);
        boolean found = false;
        for (String bot : availableBots) {
            if (botName.equals(bot.toLowerCase())) {
                found = true;
            }
        }

        if (found) {
            availableBots.remove(name);
            usedBots.add(capitalizeFirstLetter(botName));
        }
    }

    // Moves the connected from online to available
    public void moveBotFromUsedList(String botName) {
        String name = capitalizeFirstLetter(botName);
        boolean found = false;

        for (String bot : usedBots) {
            if (name.equals(bot)) {
                found = true;
            }
        }

        if (found) {
            availableBots.add(name);
            usedBots.remove(name);
        }
    }

    // Function that capitalizes the first letter of a sentence
    public String capitalizeFirstLetter(String botName) {
        return botName.substring(0, 1).toUpperCase() + botName.substring(1);
    }
}
