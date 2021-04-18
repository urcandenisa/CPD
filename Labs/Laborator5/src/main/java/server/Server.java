package server;

import server.model.LocationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private LocationService locationService;

    public Server() {
        this.locationService = new LocationService();
    }

    public Server(ServerSocket serverSocket, LocationService locationService) {
        this.serverSocket = serverSocket;
        this.locationService = locationService;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The server is starting");
        while (true) {
            try {
                System.out.println("Waiting for connection");
                new ClientHandler(serverSocket.accept(), locationService).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(5555);
    }
}

