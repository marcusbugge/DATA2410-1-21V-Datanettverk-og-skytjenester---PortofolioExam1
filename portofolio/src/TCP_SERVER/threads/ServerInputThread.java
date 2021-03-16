package TCP_SERVER.threads;

import TCP_SERVER.ListHandler;
import TCP_SERVER.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A class that handles all of the input to the servers console.
 * Can also kick out the bots of the chatroom/server by name.
 * <p>
 * Can type /exit to shut down the server
 */

public class ServerInputThread implements Runnable {

    BufferedReader inKeyboard = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<ServerReaderThread> arrayList;
    ListHandler listHandler = new ListHandler();

    public ServerInputThread(ArrayList<ServerReaderThread> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void run() {

        try {
            while (true) {

                String toClients = inKeyboard.readLine();

                checkForCommands(toClients);

                if (!Server.listClient.isEmpty() && !toClients.isBlank()) {

                    if (toClients.startsWith("/kick")) {

                        String[] array = toClients.split(" ");
                        String botToKick = array[1];

                        for (String botName : ListHandler.availableBots) {
                            if (botName.equals(botToKick)) {
                                System.out.println(botToKick + " was kicked out of the room...");
                                listHandler.moveBotFromUsedList(botToKick);
                            }
                        }

                    } else {
                        System.out.println("Message sent: " + toClients);
                    }

                    for (ServerReaderThread h : ServerReaderThread.arrayListClients) {
                        h.out.println("Host: " + toClients.toLowerCase());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkForCommands(String s) {

        if (s.equals("exit")) {
            System.exit(0);
        } else if (s.equals("--help")) {
            System.out.println("Commands:");
            System.out.println("    /kick name to kick a user from chat");
            System.out.println("    /exit to shutdown server");
        }

    }
}
