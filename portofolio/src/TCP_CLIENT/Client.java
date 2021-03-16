package TCP_CLIENT;

import TCP_SERVER.ListHandler;

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

    public Client(String ip, int port, String navn) throws IOException {
        this.server_ip = ip;
        this.server_port = port;
        this.botName = navn;

        Socket socket = new Socket(server_ip, server_port);

        ClientReaderThread con = new ClientReaderThread(socket, botName);
        new Thread(con).start();
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String botToStart = br.readLine().toLowerCase();
        new Client("localhost", 9999, botToStart);


        /*Client client = null;

        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        String botName = args[2];

        for (String bot : availableBots) {
            if (botName.equals(bot)) {
                client = new Client(ip, port, botName);
                list.moveBotToUsedList(botName);
            }
        }
        if (client == null) {
            System.out.println("Available bots: " + availableBots);
        }
        */
    }
}