package server;

import server.model.Address;
import server.model.Location;
import server.model.LocationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ClientHandler extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private LocationService locationService;

    public ClientHandler(Socket socket, LocationService locationService) {
        this.socket = socket;
        this.locationService = locationService;
    }

    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {

                String trim = inputLine.toString().substring(0, 4).trim();

                String address = inputLine.substring(inputLine.indexOf("address") + 8, inputLine.indexOf("months"));

                int space = address.indexOf(' ');
                String street = address.substring(0, space).trim();

                int space2 = address.indexOf( ' ', space + 1);
                String number = address.substring(space + 1, space2).trim();

                int space3 = address.indexOf( ' ', space2 + 1);
                String city = address.substring(space2 + 1, space3).trim();

                String country = address.substring(space3 + 1).trim();

                Address addressModel = new Address(street, number, city, country);
                //System.out.println(addressModel);

                Location location = new Location(addressModel);

                String[] months = inputLine.substring(inputLine.indexOf("months") + 7).split(",");

                if(trim.compareTo("post") == 0) {

                    System.out.println("post");

                    for(String month: months) {
                        location.insertMonthlyRent(month.trim(), new Boolean(true));
                    }

                    System.out.println(location);

                    locationService.insertRent(location);

                }
                if(trim.compareTo("rent") == 0) {

                    System.out.println("rent");

                    Map<String, Boolean> monthlyRent = location.getMonthlyRent();

                    for(String month : months) {
                        for(Map.Entry<String, Boolean> entry : monthlyRent.entrySet()) {
                            if(month.trim().compareTo(entry.getKey()) == 0) {
                                if(entry.getValue() == true) {
                                    entry.setValue(false);
                                }
                            }
                        }
                    }

                }
                if(inputLine.toString().compareTo(".") == 0) {
                    System.out.println("terminate");
                }
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
