import broker.TokenHandler;
import socket.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client1 = new Client(1);
        client1.startConnection("127.0.0.1", 6666);

        Client client2 = new Client(2);
        client2.startConnection("127.0.0.1", 6666);

        Client client3 = new Client(3);
        client3.startConnection("127.0.0.1", 6666);

        List<Client> clientList = new ArrayList<>();
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);

        TokenHandler tokenHandler = new TokenHandler();
        tokenHandler.passToken(clientList);
    }
}
