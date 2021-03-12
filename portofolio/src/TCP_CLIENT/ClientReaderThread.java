package TCP_CLIENT;

import botHandler.Bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A thread that executes when a new client is connected to the server.
 * Always expects a line from the server, then handles servers' response, and sends back their
 * answer of choice back to the server
 */

public class ClientReaderThread implements Runnable {
    Socket server;
    BufferedReader in;
    PrintWriter pOut;
    String botName;

    public ClientReaderThread(Socket s, String name) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        pOut = new PrintWriter(server.getOutputStream(), true);
        this.botName = name;

    }

    @Override
    public void run() {

        try {
            while (true) {
                    String fromServer = in.readLine();

                    if (fromServer != null) {

                    if (fromServer.contains("/kick " + botName)) {
                        break;
                    } else if (fromServer.startsWith("Host: ") && !fromServer.contains("/kick")) {

                        System.out.println();
                        System.out.println(fromServer);

                        generateBot(fromServer);

                    } else {
                        System.out.println(fromServer);
                        if (fromServer.contains("/kick")) {

                            System.out.println();
                            System.out.println("Me: Byebye...");
                            pOut.println(botName + ": " + "Byebye...");

                        }
                    }
                }
            }

            server.close();
            in.close();
            pOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateBot(String fromServer) throws InterruptedException {
        Bot bot = new Bot(fromServer, botName);
        String botAnswer = bot.getSvarFraBot();

        System.out.println("Me: " + botAnswer);

        pOut.println(botName + ": " + botAnswer);
    }
}