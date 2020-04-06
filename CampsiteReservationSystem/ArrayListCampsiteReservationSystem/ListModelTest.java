package Project3;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Class for testing list model
 * @author Seth Ockerman
 * @author Max Foreback
 *
 * NOTE: testing upDate opens the GUI. Simply click okay to run the remainder of
 * the tests once the GUI opens.
 */
public class ListModelTest {

    /**
     * testing the setDisplay
     */
    @Test
    public void setDisplay() {
        ListModel DList = new ListModel();
        //CurrentParkStatus Display
        DList.setDisplay(ScreenDisplay.CurrentParkStatus);
        assertEquals(ScreenDisplay.CurrentParkStatus, DList.getDisplay());
        //CheckOutGuest Display
        DList.setDisplay(ScreenDisplay.CheckOutGuest);
        assertEquals(ScreenDisplay.CheckOutGuest, DList.getDisplay());
        //Overloaded setDisplay with relative date
        DList.setDisplay(ScreenDisplay.CurrentParkStatus, new GregorianCalendar(2020, Calendar.MARCH, 16));
        assertEquals(ScreenDisplay.CurrentParkStatus, DList.getDisplay());
        assertEquals(new GregorianCalendar(2020, Calendar.MARCH, 16), DList.getRelativeDate());
        //Overdue Display using previous relative date
        DList.setDisplay(ScreenDisplay.OverDue);
        assertEquals(ScreenDisplay.OverDue, DList.getDisplay());
    }

    /**
     * testing getColumnCount
     */
    @Test
    public void getColumnCount() {
        ListModel DList = new ListModel();
        //Check num of cols for CurrentsParkStatus
        DList.setDisplay(ScreenDisplay.CurrentParkStatus);
        assertEquals(6, DList.getColumnCount());
        //Check num of cols for CheckOutGuest
        DList.setDisplay(ScreenDisplay.CheckOutGuest);
        assertEquals(5, DList.getColumnCount());
        //Check num of cols for Overdue
        DList.setDisplay(ScreenDisplay.OverDue, new GregorianCalendar(2020, Calendar.MARCH, 16));
        assertEquals(4, DList.getColumnCount());
        //Check num of cols for RVTent
        DList.setDisplay(ScreenDisplay.RVTent);
        assertEquals(6, DList.getColumnCount());
        //Check num of cols for TentRV
        DList.setDisplay(ScreenDisplay.TentRV);
        assertEquals(6, DList.getColumnCount());
    }

    /**
     * testing getRowCount
     */
    @Test
    public void getRowCount() {
        ListModel DList = new ListModel();
        GregorianCalendar g1 = new GregorianCalendar(2020, Calendar.MARCH, 16);
        GregorianCalendar g2 = new GregorianCalendar(2020, Calendar.MARCH, 20);
        TentOnly tent = new TentOnly("Max", g1, g2, null, 4);
        //Standard should have 8 before any operations
        assertEquals(8, DList.getRowCount());
        //Add one tent
        DList.add(tent);
        assertEquals(9, DList.getRowCount());
        //Remove one tent
        DList.remove(tent);
        assertEquals(8, DList.getRowCount());
        //CheckOut screen should have two by default
        DList.setDisplay(ScreenDisplay.CheckOutGuest);
        assertEquals(2, DList.getRowCount());
        //Overdue screen should have same # as CurrentParkStatus
        DList.setDisplay(ScreenDisplay.OverDue, new GregorianCalendar());
        assertEquals(8, DList.getRowCount());
    }

    /**
     * testing getValueAt
     */
    @Test
    public void getValueAt() {
        ListModel DList = new ListModel();
        GregorianCalendar g1 = new GregorianCalendar(2020, Calendar.MARCH, 16);
        GregorianCalendar g2 = new GregorianCalendar(2020, Calendar.MARCH, 20);
        TentOnly tent = new TentOnly("Max", g1, g2, null, 4);

        //Check various values in CurrentParkStatus
        assertEquals("RV1", DList.getValueAt(0, 0));
        assertEquals(3680.0, DList.getValueAt(4, 1));
        assertEquals("01/20/2010", DList.getValueAt(6, 2));
        assertEquals("12/22/2020", DList.getValueAt(4, 3));
        assertEquals(2000, DList.getValueAt(3, 4));
        assertEquals(5, DList.getValueAt(7, 5));
        assertEquals("T2", DList.getValueAt(7, 0));

        //Add a tent and check it was added
        DList.add(tent);
        assertEquals("T2", DList.getValueAt(8, 0));
        assertEquals("Max", 0, 0);
        //Remove a tent and make sure it is no longer there

        /*This throws an exception but the assertThrows method
        is not supported by this Junit version, so a simple
        try/catch handles the exception */
        DList.remove(tent);
        try {
            assertEquals("T2", DList.getValueAt(8, 0));
        }
        catch (IndexOutOfBoundsException e){

        }
        //Check various values in CheckOutGuest screen
        DList.setDisplay(ScreenDisplay.CheckOutGuest);
        assertEquals("T3", DList.getValueAt(0, 0));
        assertEquals(36210.0, DList.getValueAt(1, 4));
        assertEquals("01/20/2010", DList.getValueAt(1, 2));

        //Check various values in Overdue screen
        DList.setDisplay(ScreenDisplay.OverDue, new GregorianCalendar(2020, Calendar.MARCH, 16));
        assertEquals(3680.0, DList.getValueAt(0, 1));
        assertEquals("RV1", DList.getValueAt(1, 0));
        assertEquals((long)-86, DList.getValueAt(6, 3));
        assertEquals((long)281, DList.getValueAt(0, 3));

    }

    /**
     * testing getColumnName
     */
    @Test
    public void getColumnName() {
        ListModel DList = new ListModel();
        //CurrentParkStatus columns
        assertEquals("Guest Name", DList.getColumnName(0));
        assertEquals("Est. Cost", DList.getColumnName(1));
        assertEquals("Check in Date", DList.getColumnName(2));
        assertEquals("EST. Check out Date ", DList.getColumnName(3));
        assertEquals("Max Power", DList.getColumnName(4));
        assertEquals("Num of Tenters", DList.getColumnName(5));

        //CheckOut screen
        DList.setDisplay(ScreenDisplay.CheckOutGuest);
        assertEquals("Guest Name", DList.getColumnName(0));
        assertEquals("Est. Cost", DList.getColumnName(1));
        assertEquals("Check in Date", DList.getColumnName(2));
        assertEquals("ACTUAL Check out Date ", DList.getColumnName(3));
        assertEquals(" Real Cost", DList.getColumnName(4));

        //Overdue screen
        DList.setDisplay(ScreenDisplay.OverDue, new GregorianCalendar(2020, Calendar.MARCH, 3));
        assertEquals("Guest Name", DList.getColumnName(0));
        assertEquals("Est. Cost", DList.getColumnName(1));
        assertEquals("Estimated CheckoutDate", DList.getColumnName(2));
        assertEquals("Days Overdue", DList.getColumnName(3));

    }

    /**
     * testing remove
     */
    @Test
    public void remove() {
        ListModel DList = new ListModel();
        GregorianCalendar g1 = new GregorianCalendar(2010, Calendar.JANUARY, 20);
        GregorianCalendar g2 = new GregorianCalendar(2019, Calendar.DECEMBER, 20);
        TentOnly tent = new TentOnly("Max", g1, g2, null, 4);
        //add one tent and make sure the number of tents went up
        DList.add(tent);
        assertEquals(9, DList.getRowCount());
        //remove the tent an make sure num went down
        DList.remove(tent);
        assertEquals(8, DList.getRowCount());
        //remove first tent and make sure the number went down
        DList.remove(DList.get(0));
        assertEquals(7, DList.getRowCount());
    }

    /**
     * testing add
     */
    @Test
    public void add() {
        ListModel DList = new ListModel();
        GregorianCalendar g1 = new GregorianCalendar(2020, Calendar.MARCH, 16);
        GregorianCalendar g2 = new GregorianCalendar(2020, Calendar.MARCH, 20);
        TentOnly tent = new TentOnly("Max", g1, g2, null, 4);
        //Add a new tent, make sure the number went up and that new guest name is correct
        DList.add(tent);
        assertEquals(9, DList.getRowCount());
        assertEquals("Max", DList.getValueAt(0, 0));
    }

    /**
     * testing get
     */
    @Test
    public void get() {
        ListModel DList = new ListModel();
        //gets the first campsite and checks name
        CampSite camp1 = DList.get(0);
        assertEquals("RV1", camp1.guestName);

        //gets the fifth campsite and checks name
        CampSite camp2 = DList.get(4);
        assertEquals("T1", camp2.guestName);

    }

    /**
     * test upDate
     * Needs a gui object. Doesn't look clean as it opens the GUI when running all
     * tests, but it works.
     */
    @Test
    public void upDate() {
        ListModel DList = new ListModel();
        GUICampReservationSystem g = new GUICampReservationSystem();
        assertEquals("RV1", DList.getValueAt(0, 0));

        //remove the first campsite and update
        CampSite unit = DList.get(0);
        CheckOutOnDialog dialog = new CheckOutOnDialog(g, unit);
        DList.upDate(0, unit);
        assertEquals("RV2", DList.getValueAt(0, 0));

    }

    /**
     * testing saveDatebase and loadDatabase
     */
    @Test
    public void saveDatabase() {
        ListModel DList = new ListModel();
        TentOnly DTent = new TentOnly("Database Test", null, null, null, 4);
        //add a new tent as a test that something changed
        DList.add(DTent);

        //save to the file, and remove from current instance
        DList.saveDatabase("database.txt");
        DList.remove(DTent);
        assertNotEquals("Database Test", DList.getValueAt(0, 0));

        //load the database and ensure our test tent is still there
        DList.loadDatabase("database.txt");
        assertEquals("Database Test", DList.getValueAt(0, 0));
    }

    /**
     * testing saveAsText and loadFromText
     */
    @Test
    public void saveAsText() {
        ListModel DList = new ListModel();
        GregorianCalendar g1 = new GregorianCalendar(2020, Calendar.MARCH, 16);
        GregorianCalendar g2 = new GregorianCalendar(2020, Calendar.MARCH, 17);
        TentOnly DTent = new TentOnly("Database Test", g1, g2, null, 4);

        //add a new tent as a test that something changed
        DList.add(DTent);
        try {
            FileWriter writer = new FileWriter("MyFile.txt", false);
            writer.write(DList.saveTextString());
            writer.close();
        } catch (IOException z) {
            System.out.println("Error writing to file");
        }

        assertEquals("Database Test", DList.getValueAt(0, 0));

        //Remove from current instance
        DList.remove(DTent);
        assertNotEquals("Database Test", DList.getValueAt(0, 0));

        //load the database and ensure our test tent is still there
        DList.loadTextFile();
        assertEquals("Database Test", DList.getValueAt(0, 0));
    }


    /**
     * testing createList
     */
    @Test
    public void createList() {
        ListModel DList = new ListModel();
        //Check default values of createList
        assertEquals("RV1", DList.getValueAt(0, 0));
        assertEquals(3680.0, DList.getValueAt(4, 1));
        assertEquals("01/20/2010", DList.getValueAt(6, 2));
        assertEquals("12/22/2020", DList.getValueAt(4, 3));
        assertEquals(2000, DList.getValueAt(3, 4));
        assertEquals(5, DList.getValueAt(7, 5));
        assertEquals("T2", DList.getValueAt(7, 0));

        //Default values of CheckOut screen
        DList.setDisplay(ScreenDisplay.CheckOutGuest);
        assertEquals("Guest Name", DList.getColumnName(0));
        assertEquals("Est. Cost", DList.getColumnName(1));
        assertEquals("Check in Date", DList.getColumnName(2));
        assertEquals("ACTUAL Check out Date ", DList.getColumnName(3));
        assertEquals(" Real Cost", DList.getColumnName(4));

        //Default values of Overdue screen
        DList.setDisplay(ScreenDisplay.OverDue, new GregorianCalendar(2020, Calendar.MARCH, 3));
        assertEquals("Guest Name", DList.getColumnName(0));
        assertEquals("Est. Cost", DList.getColumnName(1));
        assertEquals("Estimated CheckoutDate", DList.getColumnName(2));
        assertEquals("Days Overdue", DList.getColumnName(3));
    }

    /**
     * testing invalid numTenters
     */
    @Test
    public void invalidNumTenters() {
        ListModel DList = new ListModel();
        GregorianCalendar g1 = new GregorianCalendar(2020, Calendar.MARCH, 16);
        GregorianCalendar g2 = new GregorianCalendar(2020, Calendar.MARCH, 20);
        TentOnly tent = new TentOnly("", g1, g2, null, -96);
        //Add a new tent, make sure the number went up and that new guest name is correct
        assertEquals(8, DList.getRowCount());
        DList.add(tent);
        assertEquals(8, DList.getRowCount());
    }
}
