package HW1A_2;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Flight implements Comparable<Flight>{

    private String flightNo;
    private Calendar departureTime;
    private Calendar arrivalTime;

    public Flight(String flightNo, Calendar departureTime, Calendar arrivalTime){
        this.flightNo = flightNo;
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
    }

    public int getFlightTime(){
        return getFlightTime(this.departureTime, this.arrivalTime);
    }

    public Calendar getDepartureTime(){
        return this.departureTime;
    }

    public Calendar getArrivalTime(){
        return this.arrivalTime;
    }

    public void setArrivalTime(Calendar arrivalTime){
        if(arrivalTime.compareTo(this.departureTime)>0){
            this.arrivalTime = arrivalTime;
        }
        else{
            System.out.println("Error: you must set arrival times that come after departure times.");
        }
        return;

    }

    public void setDepartureTime(Calendar departureTime){
        this.departureTime = departureTime;
    }

    public static int getFlightTime(Calendar departure, Calendar arrival){
//        int total = 0;
//        total += (365*24*60) * arrival.get(Calendar.YEAR) - departure.get(Calendar.YEAR);
//        total += (24*60) * arrival.get(Calendar.DAY_OF_YEAR) - departure.get(Calendar.DAY_OF_YEAR);
//        total += (60) * arrival.get(Calendar.HOUR_OF_DAY) - departure.get(Calendar.HOUR_OF_DAY);
//        return total;
        long timeDiff = arrival.getTime().getTime() - departure.getTime().getTime();
        return (int) (timeDiff/1000) / 60;
    }

    @Override
    public int compareTo(Flight o) {
        return this.departureTime.compareTo(((Flight) o).departureTime);
    }

    public String toString(){
        return (this.flightNo + " | Depart: " + this.departureTime.getTime().toString() + " | Arrives: " + this.
                arrivalTime.getTime().toString());
    }

}