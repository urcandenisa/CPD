package server.model;


import java.util.Collection;
import java.util.Iterator;

public class LocationService {

    private Collection<Location> rents;

    public LocationService() {
        this.rents = new Collection<Location>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Location> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Location location) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Location> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
    }

    public LocationService(Collection<Location> rents) {
        this.rents = rents;
    }

    public synchronized void insertRent(Location location){
        if(!rents.contains(location)) {
            rents.add(location);
            System.out.println("Location successfully inserted on Airbnb");
        } else {
            System.out.println("Location already exists on Airbnb");
        }
    }

    public Location findLocationByAddress(Address address) {
        if(rents == null) return null;

        if(rents.size() == 0 ) return null;

        for(Location location : rents) {
            if(location.getAddress().equals(address)) {
                return location;
            }
        }
        return null;
    }

    public Collection<Location> getRents() {
        return rents;
    }

    public void setRents(Collection<Location> rents) {
        this.rents = rents;
    }
}
