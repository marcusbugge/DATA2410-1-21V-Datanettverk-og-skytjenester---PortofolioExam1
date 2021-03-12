package TCP_SERVER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class that handles all of the input to the servers console.
 * Can also kick out the bots of the chatroom/server by name.
 */

public class ServerInputThread implements Runnable {

    BufferedReader inKeyboard = new BufferedReader(new InputStreamReader(System.in));
    CopyOnWriteArrayList<ServerClientThread> arrayList;

    public ServerInputThread(CopyOnWriteArrayList<ServerClientThread> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void run() {

        try {
            while (true) {
                String toClients = inKeyboard.readLine();

                if (toClients.equals("exit")) {
                    System.exit(0);
                }

                if (!Server.listClient.isEmpty() && !toClients.isBlank()) {

                    if (toClients.startsWith("/kick")) {

                        String[] array = toClients.split(" ");
                        String botToKick = array[1];
                        for (String botName : Server.availableBots) {
                            if (botName.equals(botToKick)) {
                                System.out.println(botToKick + " was kicked out of the room...");
                            }
                        }

                    } else {
                        System.out.println("Message sent: " + toClients);
                    }

                    for (ServerClientThread h : ServerClientThread.arrayListClients) {
                        h.out.println("Host: " + toClients.toLowerCase());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
