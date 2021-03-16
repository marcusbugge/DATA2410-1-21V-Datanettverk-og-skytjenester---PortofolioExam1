package botHandler;

import java.util.Random;

/**
 * A class that takes in a string and finds the verb that also is in "String[] verbs".
 * Then takes the client's name and runs their function and generates their answer by choosing a
 * random sentence in a array.
 * <p>
 * If a the input string contains a "bad verb" one of the bots will show their bad-bot-side.
 */

public class Bot {

    // A lists of simple verbs that our program will recognize from the server.
    private final String[] verbs = {"sing", "talk", "walk", "play", "drink", "dance"};
    private final String[] badVerbs = {"fight", "kill", "murder"};
    private final String[] helloWords = {"hi", "hey", "hello", "hallo"};

    private String botAnswer;
    private String foundVerb;
    private boolean foundVerbBoolean = false;
    private boolean foundBadVerbBoolean = false;

    private Random rn = new Random();
    private int randomNumb = rn.nextInt(3 + 1);
    private int randomNumb2 = rn.nextInt(1 + 1);


    public Bot(String stringFromClient, String botName) {

        for (String s : helloWords) {
            if (stringFromClient.contains(s)) {
                setBotAnswer("Hello!");
            }
        }

        if (botAnswer == null) {
            verbFinder(stringFromClient);
            botToStart(botName);
        }
    }

    public String getBotAnswer() {
        return botAnswer;
    }

    public void setBotAnswer(String botAnswer) {
        this.botAnswer = botAnswer;
    }

    public void verbFinder(String stringFromClient) {
        String[] array = stringFromClient.split("[ ,?]");

        for (int j = 0; j < verbs.length; j++) {
            for (int h = 0; h < badVerbs.length; h++) {
                for (int i = 0; i < array.length; i++) {
                    if (array[i].toLowerCase().equals(verbs[j].toLowerCase())) {
                        foundVerb = array[i];
                        foundVerbBoolean = true;
                    } else if (array[i].toLowerCase().equals(badVerbs[h])) {
                        foundVerb = array[i];
                        foundBadVerbBoolean = true;
                    }
                }
            }
        }
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
            setBotAnswer(array[randomNumb]);
        } else if (foundBadVerbBoolean) {
            String[] array = {
                    "Im a good guy, " + foundVerb + "ing is not for me!",
                    "I have to pass.. sorry"
            };
            setBotAnswer(array[randomNumb2]);
        } else {
            setBotAnswer("Im sorry i dont know what that means");
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
            setBotAnswer(array[randomNumb]);
        } else if (foundBadVerbBoolean) {
            String[] array = {
                    "Im not into that, sorry",
                    foundVerb + "ing is not for me. I know a person in this that chat really wants to join though!"
            };
            setBotAnswer(array[randomNumb2]);
        } else {
            setBotAnswer("I dont understand.");
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
            setBotAnswer(array[randomNumb]);
        } else if (foundBadVerbBoolean) {
            String[] array = {
                    "Im not into that, sorry",
                    foundVerb + "ing is not for me."
            };
            setBotAnswer(array[randomNumb2]);
        } else {
            setBotAnswer("Please say something else");
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
            setBotAnswer(array[randomNumb]);
        } else if (foundBadVerbBoolean) {
            String[] array = {
                    foundVerb.toUpperCase() + "ING!?!??! Im in!",
                    "Im a usually a good person. but " + foundVerb + "ing sounds really fun... >;-)"
            };
            setBotAnswer(array[randomNumb2]);
        } else {
            setBotAnswer("Im stupid, i dont know what that means.");
        }
    }
}
