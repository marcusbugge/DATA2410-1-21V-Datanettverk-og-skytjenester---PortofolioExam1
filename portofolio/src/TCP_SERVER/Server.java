package TCP_SERVER;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
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

    public static ArrayList<String> availableBots = new ArrayList<>();
    public static ArrayList<String> usedBots = new ArrayList<>();
    public static CopyOnWriteArrayList<ServerClientThread> listClient = new CopyOnWriteArrayList<>();

    static ExecutorService pool = Executors.newFixedThreadPool(5);

    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);

        ServerInputThread th = new ServerInputThread(listClient);
        new Thread(th).start();

        try {

            System.out.println(" ////////////////////// Welcome to CHATROOM V.1 ////////////////////// ");

            while (true) {

                if (listClient.isEmpty()) {
                    System.out.println("Waiting for a client to connect....");
                }

                socket = serverSocket.accept();
                System.out.println();
                System.out.println(" ------- A bot joined the room from " + socket.getRemoteSocketAddress() + " ------- ");
                System.out.println();

                ServerClientThread serverClientThread = new ServerClientThread(socket, listClient);

                listClient.add(serverClientThread);

                pool.execute(serverClientThread);

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
