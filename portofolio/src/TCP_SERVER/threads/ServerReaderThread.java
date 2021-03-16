package TCP_SERVER.threads;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Handles all the connections between the clients and server. Always waiting for a response from the clients and
 * send it to all the connected clients is the list.
 */

public class ServerReaderThread implements Runnable {
    public Socket client;
    private BufferedReader inClient;
    PrintWriter out;

    public static ArrayList<ServerReaderThread> arrayListClients;

    public ServerReaderThread(Socket clientSocket, ArrayList<ServerReaderThread> arrayListClients) throws IOException {

        this.client = clientSocket;
        this.arrayListClients = arrayListClients;

        inClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {

                try {
                    if (!client.isClosed()) {

                        // Waiting for message from the client
                        String fromClient = inClient.readLine();

                        if (fromClient != null) {

                            // Prints out the message from the clients
                            System.out.println(fromClient);

                            // Sends the message to all connected clients
                            sendToAll(fromClient);
                        } else {
                            break;
                        }
                    }

                } catch (Exception e) {
                    break;
                }
            }

            client.close();
            inClient.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String messageToClient) {
        for (ServerReaderThread client : arrayListClients) {
            if (client.client != this.client) {
                client.out.println(messageToClient);
            }
        }
    }
}