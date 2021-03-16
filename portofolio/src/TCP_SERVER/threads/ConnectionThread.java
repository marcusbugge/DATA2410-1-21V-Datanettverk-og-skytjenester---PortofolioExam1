package TCP_SERVER.threads;

import TCP_SERVER.ListHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * A thread that is called right after the socket is connected at the server. It will get the bot's name and check
 * if that name is used or not. It also send out a message to all other clients connected when they connect to the
 * same server. If the name already is in use, the server sends >out< back to the client and the client gets warned
 * about the case and the socket gets closed
 *
 */

public class ConnectionThread implements Runnable {

    BufferedReader in;
    Socket client;
    PrintWriter out;
    ListHandler listHandler;
    ArrayList<ServerReaderThread> clients;

    public ConnectionThread(Socket client, ListHandler lists, ArrayList<ServerReaderThread> clientList) throws IOException {
        this.clients = clientList;
        this.client = client;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        listHandler = lists;
    }

    @Override
    public void run() {
        try {
            String botNameFromClient = in.readLine().toLowerCase();

            boolean found = false;

            for (String botName : ListHandler.availableBots) {
                if (botNameFromClient.equals(botName.toLowerCase())) {
                    found = true;
                }
            }
            if (found) {
                System.out.println();
                System.out.println(">>> " + listHandler.capitalizeFirstLetter(botNameFromClient) +
                        " joined the room from " + client.getRemoteSocketAddress() + " <<<");
                System.out.println();

                listHandler.moveBotToUsedList(botNameFromClient);

                out.println(" ////////////////////// Welcome to CHATROOM V.1 ////////////////////// ");
                sendToAll(">>> " + listHandler.capitalizeFirstLetter(botNameFromClient) +
                        " joined the room!");


            } else {
                out.println("That client is already connected");
                out.println("Available bots: " + ListHandler.availableBots.toString());

                out.println("end");
                client.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendToAll(String s) {
        for (ServerReaderThread client : clients) {
            if (client.client != this.client) {
                client.out.println("");
                client.out.println(s);
            }
        }
    }
}
