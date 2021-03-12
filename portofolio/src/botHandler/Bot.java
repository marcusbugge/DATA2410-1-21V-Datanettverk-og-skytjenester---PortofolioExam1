package botHandler;

import java.util.Random;

/**
 *
 * A class that takes in a string and finds the verb that also is in "String[] verbs".
 * Then takes the client's name and runs their function and generates their answer by choosing a
 * random sentence in a array.
 *
 */

public class Bot {

    private final String[] verbs = {"sing", "talk", "walk", "play", "drink"};
    private final String[] helloWords = {"hi", "hey", "hello", "hallo"};
    private String svarFraBot;
    private String foundVerb;
    private boolean foundVerbBoolean = false;

    private Random rn = new Random();
    private int randomNumb = rn.nextInt(3 + 1);

    public Bot(String strengFraClient, String botName) {

        for (String s : helloWords) {
            if (strengFraClient.contains(s)) {
                setSvarFraBot("Hello!");
            }
        }

        if (svarFraBot == null) {
            verbFinder(strengFraClient);
            botToStart(botName);
        }
    }

    public String getSvarFraBot() {
        return svarFraBot;
    }

    public void setSvarFraBot(String svarFraBot) {
        this.svarFraBot = svarFraBot;
    }

    public void botToStart(String botName) {

        switch (botName) {

            case "feppe":
                botFeppe();
                break;

            case "bugge":
                botBugge();
                break;

            case "henke":
                botHenke();
                break;

            case "brede":
                botBrede();
                break;
        }

    }

    private void botBrede() {

        if (foundVerbBoolean) {
            String[] array = {
                    foundVerb + "ing " + "sounds fun!",
                    "Yesss, id love to " + foundVerb,
                    "Ehmmm, I can't " + foundVerb + ", but maybe you can learn me?",
                    "I really want to go " + foundVerb + " with you."
            };
            setSvarFraBot(array[randomNumb]);
        } else {
            setSvarFraBot("Im sorry i dont know what that means");
        }
    }

    private void botHenke() {

        if (foundVerbBoolean) {
            String[] array = {
                    foundVerb + "ing " + "sounds fun!",
                    "Yesss, id love to " + foundVerb,
                    "Ehmmm, I can't " + foundVerb + ", but maybe you can learn me?",
                    "I really want to go " + foundVerb + " with you."
            };
            setSvarFraBot(array[randomNumb]);
        } else {
            setSvarFraBot("I dont understand.");
        }

    }

    private void botBugge() {

        if (foundVerbBoolean) {
            String[] array = {
                    foundVerb + "ing " + "sounds fun!",
                    "Yesss, id love to " + foundVerb,
                    "Ehmmm, I can't " + foundVerb + ", but maybe you can learn me?",
                    "I really want to go " + foundVerb + " with you."
            };
            setSvarFraBot(array[randomNumb]);
        } else {
            setSvarFraBot("Please say something else");
        }

    }

    private void botFeppe() {

        if (foundVerbBoolean) {
            String[] array = {
                    foundVerb + "ing " + "sounds fun!",
                    "Yesss, id love to " + foundVerb,
                    "Ehmmm, I can't " + foundVerb + ", but maybe you can learn me?",
                    "I really want to go " + foundVerb + " with you."
            };
            setSvarFraBot(array[randomNumb]);
        } else {
            setSvarFraBot("Im stupid, i dont know what that means.");
        }
    }

    public void verbFinder(String strengFraClient) {
        String[] array = strengFraClient.split("[ ,?]");

        for (int j = 0; j < verbs.length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].toLowerCase().equals(verbs[j].toLowerCase())) {
                    foundVerb = array[i];
                    foundVerbBoolean = true;
                }
            }
        }
    }
}
