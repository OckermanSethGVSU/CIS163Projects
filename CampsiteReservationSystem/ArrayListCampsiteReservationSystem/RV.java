package Project3;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * class that handles data and function of an RV campsite reservation
 * @author Max Foreback
 * @author Seth Ockerman
 */
public class RV extends CampSite {

    // the amount of power that a user wants to use
    private int power;

    /**
     * creates RV reservation object
     */
    public RV() {
    }

    /**
     * creates RV reservation with passed in values
     * @param guestName the name of the guest
     * @param checkIn the date they check in
     * @param estimatedCheckOut the date they think they will check out
     * @param actualCheckOut the date they actually check out
     * @param power the power they want provided
     */
    public RV(String guestName, GregorianCalendar checkIn, GregorianCalendar estimatedCheckOut, GregorianCalendar actualCheckOut, int power) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.power = power;
        }

    /**
     * the power that a renter uses
     * @return the power as an int
     */
    public int getPower() {
        return power;
    }

    /**
     * set the amount of power a user wants provided
     * @param power the power a user wants as an int
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     *
     * @param checkOut date you checkIn
     * @param checkIn date you checkOut
     * @return the cost as a double
     */
    @Override
    public double getCost(GregorianCalendar checkOut, GregorianCalendar checkIn) {
        double cost = 10;
        if(power > 1000)
            cost = 20;
        long days = daysBetween(checkIn, checkOut);
        cost = cost * days;
        return cost;
    }



    /**
     * a string representation of a RV
     * @return string of an RV
     */
    @Override
    public String toString() {
        return "RV{" +
                "power=" + power +
                super.toString() +
                '}';
    }
}