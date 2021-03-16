package TCP_SERVER;

import TCP_SERVER.threads.ConnectionThread;
import TCP_SERVER.threads.ServerInputThread;
import TCP_SERVER.threads.ServerReaderThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A TCP-Server that runs on a given port as command-line-parameter. Starts a thread that handles all the user-input
 * in the server. The main-thread is always waiting for connections from the clients.
 * When the clients connect they are added to a list.
 */

public class Server {
    private final ServerSocket serverSocket;
    private Socket socket;

    public static ArrayList<ServerReaderThread> listClient = new ArrayList<>();
    static ExecutorService pool = Executors.newFixedThreadPool(5);

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        ListHandler list = new ListHandler();
        list.addBots();

        ServerInputThread th = new ServerInputThread(listClient);
        new Thread(th).start();

        try {

            System.out.println(" ////////////////////// Welcome to CHATROOM V.1 ////////////////////// ");

            while (true) {

                if (listClient.isEmpty()) {
                    System.out.println("Waiting for a client to connect....");
                }

                socket = serverSocket.accept();

                ConnectionThread con = new ConnectionThread(socket, list, listClient);
                new Thread(con).start();

                ServerReaderThread serverReaderThread = new ServerReaderThread(socket, listClient);
                listClient.add(serverReaderThread);
                pool.execute(serverReaderThread);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // int port = Integer.parseInt(args[0]);
        try {
            new Server(9999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
