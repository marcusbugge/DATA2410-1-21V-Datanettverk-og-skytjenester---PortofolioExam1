package TCP_CLIENT;

import TCP_SERVER.ListHandler;
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
    BufferedReader keyboard;
    PrintWriter pOut;
    String botName;
    ListHandler listHandler = new ListHandler();

    public ClientReaderThread(Socket s, String name) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        pOut = new PrintWriter(server.getOutputStream(), true);
        this.botName = name;


        // Sends the connected client's name to the server so the server can check if the name is already connected
        // If it is connected the connection will just end.
        pOut.println(botName);

    }

    @Override
    public void run() {

        try {
            while (true) {

                String fromServer = in.readLine();

                if (fromServer.equals("end")) System.exit(0);

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
                            System.out.println("Me: Adios...");
                            pOut.println(listHandler.capitalizeFirstLetter(botName) + ": " + "Adios...");

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

    private void generateBot(String fromServer) {
        Bot bot = new Bot(fromServer, botName);
        String botAnswer = bot.getBotAnswer();

        System.out.println("Me: " + botAnswer);

        pOut.println(listHandler.capitalizeFirstLetter(botName) + ": " + botAnswer);
    }
}