package server.model;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private Address address;
    private Map<String, Boolean> monthlyRent;

    public Location() {
    }

    public Location(Address address) {
        this.address = address;
        this.monthlyRent = new HashMap<>();
    }

    public synchronized void insertMonthlyRent(String key, Boolean value) {
        this.monthlyRent.put(key, value);
    }

    public Location(Address address, Map<String, Boolean> monthlyRent) {
        this.address = address;
        this.monthlyRent = monthlyRent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<String, Boolean> getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Map<String, Boolean> monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address=" + address +
                ", monthlyRent=" + monthlyRent +
                '}';
    }
}
