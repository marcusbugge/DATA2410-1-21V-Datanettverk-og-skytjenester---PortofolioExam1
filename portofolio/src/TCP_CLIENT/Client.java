package TCP_CLIENT;

import botHandler.ListHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A simple TCP-client that connects to a port and ip-address given as command-line-parameters.
 * Starts a new thread for each connected client.
 */

public class Client {
    private String server_ip;
    private int server_port;
    private String botName;

    static ArrayList<String> availableBots;
    static ArrayList<String> usedBots;

    public Client(String ip, int port, String navn) throws IOException {
        this.server_ip = ip;
        this.server_port = port;
        this.botName = navn;

        Socket socket = new Socket(server_ip, server_port);
        System.out.println("Connected to " + botName + " .... Waiting for message from server");

        ClientReaderThread con = new ClientReaderThread(socket, botName);
        new Thread(con).start();
    }


    public static void main(String[] args) throws IOException {

        ListHandler list = new ListHandler();

        if (list.getAvailableBots().isEmpty()) {
            list.addBots();
        }

        ArrayList<String> availableBots = list.getAvailableBots();
        ArrayList<String> usedBots = list.getUsedBots();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Which bot do you want to start?");
        System.out.println("Available bots: " + availableBots);

        String botToStart = br.readLine();

        for (String bot : availableBots) {
            if (bot.equals(botToStart)) {
                new Client("localhost", 9999, botToStart);
                list.moveBotToUsedList(bot);
                break;
            }
        }

        /*
        Client client = null;

        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        String botName = args[2];

        for (String bot : allBots) {
            if (botName.equals(bot)) {
                client = new Client(ip, port, botName);
                onlineBots.add(botName);
            }
        }
        if (client == null) {
            System.out.println("Available bots: " + allBots);
        }*/

    }
}