package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        try {
            System.out.println("The client is starting");
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error when initializing connection");
        }
    }

    public String sendMessage(String msg) {
        try {
            out.println(msg);
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error when closing the client");
        }
    }

    public static void main(String[] args) throws IOException {
        Client myFirstClient = new Client();
        myFirstClient.startConnection("127.0.0.1",5555);
        myFirstClient.sendMessage("post " + "address " + "Fabricii 78 Cluj Romania " + "months " + "iunie, iulie");
        myFirstClient.sendMessage(".");
        myFirstClient.stopConnection();

        Client mySecondClient = new Client();
        mySecondClient.startConnection("127.0.0.1",5555);
        mySecondClient.sendMessage("rent " + "address " + "Fabricii 78 Cluj Romania " + "months " + "iunie");
    }
}