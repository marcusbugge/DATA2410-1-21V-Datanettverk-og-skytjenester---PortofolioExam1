package TCP_SERVER;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Handles all the connections between the clients and server. Always waiting for a response from the clients and
 * send it to all the connected clients is the list.
 */

public class ServerClientThread implements Runnable {
    private Socket client;
    private BufferedReader inClient;
    PrintWriter out;

    public static CopyOnWriteArrayList<ServerClientThread> arrayListClients;

    public ServerClientThread(Socket clientSocket,
                              CopyOnWriteArrayList<ServerClientThread> arrayListClients) throws IOException {

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
                    e.printStackTrace();
                }
            }

            client.close();
            inClient.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendToAll(String messageToClient) {
        for (ServerClientThread client : arrayListClients) {
            if (client.client != this.client) {
                client.out.println(messageToClient);
            }
        }
    }
}