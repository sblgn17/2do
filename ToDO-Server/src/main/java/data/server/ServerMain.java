package data.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) {
        int port = ServerConfig.PORT;

        System.out.println("=== TaskManager Server startet auf Port " + port + " ===");

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client verbunden: " + client.getInetAddress());

                ClientHandler handler = new ClientHandler(client);
                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}