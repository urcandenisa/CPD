package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private ServerSocket serverSocket;

    public void startConnection(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started on port " + port);
        while (true) {
            try {
                new ClientHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopConnection() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startConnection(6666);
    }
}


