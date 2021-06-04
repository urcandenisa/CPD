package socket.client;

import model.Topics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private Socket clientSocket;

    private PrintWriter out;
    private BufferedReader in;

    private List<Topics> subscribedTopics;
    private boolean hasToken;
    private int id;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
        this.subscribedTopics = new ArrayList<>();
    }

    public void startConnection(String ip, int port) {
        System.out.println("Client " + id + " started on port " + port);
        try {
            clientSocket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendMessage(String message) {
        out.println(message);

        if(hasToken) {
            try {
                return "Client " + id + " has the token: " + in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return "Client " + id + ": You don't have the permission";
    }

    public boolean hasToken() {
        return hasToken;
    }

    public void setHasToken(boolean hasToken) {
        this.hasToken = hasToken;
    }

    public void stopConnection() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Topics> getSubscribedTopics() {
        return subscribedTopics;
    }

    public void setSubscribedTopics(List<Topics> subscribedTopics) {
        this.subscribedTopics = subscribedTopics;
    }
}
