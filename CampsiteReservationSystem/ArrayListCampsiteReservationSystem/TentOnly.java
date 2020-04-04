package Project3;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * class that handles data and function of a tent campsite reservation
 * @author Seth Ockerman
 * @author Max Foreback
 */
public class TentOnly extends CampSite {
    // number of tenters as an int
    private int numberOfTenters;

    /**
     * constructor that creates a default tent reservation
     */
    public TentOnly() {
    }

    /**
     * constuctor that creates a tent reservation with passed in data
     * @param guestName the name of the guest
     * @param checkIn the date the guest checked in
     * @param estimatedCheckOut the date the guest thinks they will check out
     * @param actualCheckOut the date the guest actually checks out
     * @param numberOfTenters the number of people tenting
     */
    public TentOnly(String guestName,
                    GregorianCalendar checkIn,
                    GregorianCalendar estimatedCheckOut,
                    GregorianCalendar actualCheckOut,
                    int numberOfTenters) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.numberOfTenters = numberOfTenters;
    }

    /**
     * gets the number of tenters at a tent sight
     * @return the number of tenters as an int
     */
    public int getNumberOfTenters() {
        return numberOfTenters;
    }

    /**
     * sets the number of tenters at a tent sight
     * @param numberOfTenters the number of tenters as an int
     */
    public void setNumberOfTenters(int numberOfTenters) {
        this.numberOfTenters = numberOfTenters;
    }

    /**
     * calculates cost for tent sight reservations
     * @param checkOut date you checkIn
     * @param CheckIn date you checkOut
     * @return the cost as a double
     */
    @Override
    public double getCost(GregorianCalendar checkOut, GregorianCalendar CheckIn) {
        double cost = 10;
        if (numberOfTenters > 10)
            cost = 20;
        long help = daysBetween(checkIn, checkOut);
        cost = cost * help;
        return cost;
    }


    /**
     * string representation of a campsite reservation
     * @return a string of a campsite
     */
    @Override
    public String toString() {
        return "TentOnly{" +
                "numberOfTenters=" + numberOfTenters + " " +
                super.toString() +
                '}';
    }

}
