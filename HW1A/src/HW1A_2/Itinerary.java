package HW1A_2;

import java.util.*;

public class Itinerary {

    private List<Flight> flights = new ArrayList<>();

    public Itinerary(List<Flight> flights){
        Collections.sort(flights);
        this.flights = flights;
    }

    public static void main(String[] args) {
        ArrayList<Flight> flights = new ArrayList<>();

        flights.add(new Flight("US230",
                new GregorianCalendar(2019, 4, 5, 5, 5, 0),
                new GregorianCalendar(2019, 4, 5, 6, 15, 0)));
        flights.add(new Flight("US235",
                new GregorianCalendar(2019, 4, 5, 6, 55, 0),
                new GregorianCalendar(2019, 4, 5, 7, 45, 0)));
        flights.add(new Flight("US237",
                new GregorianCalendar(2019, 4, 5, 9, 35, 0),
                new GregorianCalendar(2019, 4, 5, 12, 55, 0)));

        Itinerary itinerary = new Itinerary(flights);
        for(Flight f : flights){
            System.out.println(f);
        }
        System.out.println("Total travel time: " + itinerary.getTotalTravelTime());
        System.out.println("Total flight time: " + itinerary.getTotalFlightTime());
    }

    public int getTotalTravelTime(){
        return Flight.getFlightTime(flights.get(0).getDepartureTime(), flights.get(flights.size()-1).
                getArrivalTime());
    }

    public int getTotalFlightTime(){
        int total = 0;
        for(Flight f : flights){
            total += f.getFlightTime();
        }
        return total;
    }


}