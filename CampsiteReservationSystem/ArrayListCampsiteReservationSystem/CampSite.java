package Project3;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Abstract class that hold relevant data and method to a camp sight
 * @author Seth Ockerman
 * @author Max Foreback
 */
public abstract class CampSite implements Serializable {
    // serial number of UID
    private static final long serialVersionUID = 1L;

    // guests name as a string
    protected String guestName;

    // checkInDate
    protected GregorianCalendar checkIn;
    //date they think they will checkOut
    protected GregorianCalendar estimatedCheckOut;

    // date they actually checkout
    protected GregorianCalendar actualCheckOut;

    //blank constructor that creates a campsite
    public CampSite() {
    }

    /**
     * Gets the cost based on time rented
     * @param checkOut date you checkIn
     * @param CheckIn date you checkOut
     * @return the amount due as a double
     */
    public abstract double getCost(GregorianCalendar checkOut, GregorianCalendar CheckIn);

    /**
     * Creates campSite based on parameters
     * @param guestName name of guest as string
     * @param checkIn date they check in
     * @param estimatedCheckOut date they think they will check out
     * @param actualCheckOut date they actually check ut
     */
    public CampSite(String guestName,
                    GregorianCalendar checkIn,
                    GregorianCalendar estimatedCheckOut,
                    GregorianCalendar actualCheckOut) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.estimatedCheckOut = estimatedCheckOut;
        this.actualCheckOut = actualCheckOut;
    }

    /**
     * gets guest name as string
     * @return name as string
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Sets a campsites name variable
     * @param guestName the name you want it set to as a string
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * gets the date a guest checks in
     * @return the date checked in
     */
    public GregorianCalendar getCheckIn() {
        return checkIn;
    }

    /**
     * sets a campsites getIn date based on passed in date
     * @param checkIn date they checked in
     */
    public void setCheckIn(GregorianCalendar checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * gets when the date they think they will checkout
     * @return the date they think they will check out
     */
    public GregorianCalendar getEstimatedCheckOut() {
        return estimatedCheckOut;
    }

    /**
     * sets a campsites estimated check out date variable
     * @param estimatedCheckOut the date you think you will check out
     */
    public void setEstimatedCheckOut(GregorianCalendar estimatedCheckOut) {
        this.estimatedCheckOut = estimatedCheckOut;
    }

    /**
     * gets the date they actually checked out
     * @return the date they checked out
     */
    public GregorianCalendar getActualCheckOut() {
        return actualCheckOut;
    }

    /**
     * sets the day they actually checked out
     * @param actualCheckOut gregorian calender date
     */
    public void setActualCheckOut(GregorianCalendar actualCheckOut) {
        this.actualCheckOut = actualCheckOut;
    }


    /**
     *  Overrides the object classes default to string method
     *  with a version that instead returns a formatted string
     *  representing key data about a campsite
     * @return a formatted string
     */
    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String checkInOnDateStr;
        if (getCheckIn() == null)
            checkInOnDateStr = "";
        else
            checkInOnDateStr = formatter.format(getCheckIn().getTime());

        String  estCheckOutStr;
        if (getEstimatedCheckOut() == null)
            estCheckOutStr = "";
        else
            estCheckOutStr = formatter.format(getEstimatedCheckOut().getTime());

        String checkOutStr;
        if (getActualCheckOut() == null)
            checkOutStr = "";
        else
            checkOutStr = formatter.format(getActualCheckOut().getTime());

        return "CampSite{" +
                "guestName='" + guestName + '\'' +
                ", checkIn=" + checkInOnDateStr +
                ", estimatedCheckOut=" + estCheckOutStr +
                ", actualCheckOut=" + checkOutStr +
                '}';
    }
    /**
     * helper method that determines the days between the two dates
     * @param startDate the start date
     * @param endDate the end date
     * @return the days between two dates as a long
     */
    public static long daysBetween(GregorianCalendar startDate, GregorianCalendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(end - start);
    }

    /**
     * helper method that determines how many days a campsite is overdue based on relative date
     * @param date
     * @return
     */
    public long daysOverdue(GregorianCalendar date){
        return daysBetween(date,this.estimatedCheckOut);
    }
}
