package FinalProject;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Junit testing the MySingleLinked list class
 * @author Seth Ockerman
 * @author Max Foreback
 */
public class MySingleWithOutTailLinkedListTest {
    /**
     * testing size function with a variety of different elements
     */
    @Test
    public void size() {
        // creating tents and RV's to test
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        try {
            d1 = df.parse("1/1/2020");
            g1.setTime(d1);

            d2 = df.parse("1/4/2020");
            g2.setTime(d2);

            d3 = df.parse("03/20/2020");
            g3.setTime(d3);

            d4 = df.parse("05/20/2020");
            g4.setTime(d4);

            d5 = df.parse("01/01/9000");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TentOnly tentOnly1 = new TentOnly("T1", g1, g1,g2,4);
        TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g1, 8);
        RV RV1 = new RV("RV1",g1,g1,g2, 1000);
        RV RV2 = new RV("RV2",g1,g2,g1, 1000);

        // adding and removing and checking size
        MySingleWithOutTailLinkedList sizeTest = new MySingleWithOutTailLinkedList();
        assertEquals(0,sizeTest.size());

        sizeTest.add(tentOnly1);
        assertEquals(1,sizeTest.size());

        sizeTest.add(tentOnly1);
        assertEquals(2,sizeTest.size());

        sizeTest.add(RV1);
        sizeTest.add(RV2);
        assertEquals(4,sizeTest.size());

        sizeTest.remove(0);
        assertEquals(3,sizeTest.size());

        sizeTest.remove(2);
        assertEquals(2,sizeTest.size());

        sizeTest.remove(0);
        assertEquals(1,sizeTest.size());

        sizeTest.remove(0);
        assertEquals(0,sizeTest.size());

    }

    /**
     * testing clear
     */
    @Test
    public void clear() {
        // creating tents and RV's to test
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        try {
            d1 = df.parse("1/1/2020");
            g1.setTime(d1);

            d2 = df.parse("1/4/2020");
            g2.setTime(d2);

            d3 = df.parse("03/20/2020");
            g3.setTime(d3);

            d4 = df.parse("05/20/2020");
            g4.setTime(d4);

            d5 = df.parse("01/01/9000");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TentOnly tentOnly1 = new TentOnly("T1", g1, g1,g2,4);
        TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g1, 8);
        TentOnly tentOnly3 = new TentOnly("T3",g1,g3,g1,45);
        RV RV1 = new RV("RV1",g1,g1,g2, 1000);
        RV RV5 = new RV("RV5",g1,g5,g1, 1000);

        //adding then clearing to make sure size is 0
        MySingleWithOutTailLinkedList testClear = new MySingleWithOutTailLinkedList();
        testClear.add(tentOnly1);
        testClear.add(tentOnly2);
        testClear.add(RV1);
        testClear.add(tentOnly3);
        testClear.add(RV5);
        assertEquals(RV5,testClear.get(4));
        assertEquals(5,testClear.size);
        testClear.clear();
        assertEquals(0,testClear.size);


    }

    /**
     * testing add with variety of different situations
     */
    @Test
    public void add() {
       // creating tents and RV's to test
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = null;
        Date d2 = null;
        Date d3 = null;
        Date d4 = null;
        Date d5 = null;
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        try {
            d1 = df.parse("1/1/2020");
            g1.setTime(d1);

            d2 = df.parse("1/4/2020");
            g2.setTime(d2);

            d3 = df.parse("03/20/2020");
            g3.setTime(d3);

            d4 = df.parse("05/20/2020");
            g4.setTime(d4);

            d5 = df.parse("01/01/9000");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TentOnly tentOnly1 = new TentOnly("T1", g1, g1,g2,4);
        TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g1, 8);
        TentOnly tentOnly3 = new TentOnly("T3",g1,g3,g1,45);
        TentOnly tentOnly4 = new TentOnly("T4",g1,g4,g1,45);
        TentOnly tentOnly5 = new TentOnly("T4",g1,g5,g1,45);
        RV RV1 = new RV("RV1",g1,g1,g2, 1000);
        RV RV2 = new RV("RV2",g1,g2,g1, 1000);
        RV RV3 = new RV("RV3",g1,g3,g1, 1000);
        RV RV4 = new RV("RV4",g1,g4,g1, 1000);
        RV RV5 = new RV("RV5",g1,g5,g1, 1000);


        // test adding single RV and single Tent
        MySingleWithOutTailLinkedList singleRV = new MySingleWithOutTailLinkedList();
        singleRV.add(RV1);
        assertEquals(RV1,singleRV.get(0));

        MySingleWithOutTailLinkedList singleTent = new MySingleWithOutTailLinkedList();
        singleTent.add(tentOnly1);
        assertEquals(tentOnly1,singleTent.get(0));

        // adding RV then tent
        MySingleWithOutTailLinkedList TentRV = new MySingleWithOutTailLinkedList();
        TentRV.add(tentOnly1);
        TentRV.add(RV1);
        assertEquals(tentOnly1,TentRV.get(0));
        assertEquals(RV1,TentRV.get(1));

        // adding Tent than RV
        MySingleWithOutTailLinkedList RVTent = new MySingleWithOutTailLinkedList();
        RVTent.add(RV1);
        RVTent.add(tentOnly1);
        assertEquals(tentOnly1,RVTent.get(0));
        assertEquals(RV1,RVTent.get(1));
        assertEquals(RVTent.size,2);

        //adding out of order tents
        MySingleWithOutTailLinkedList outOfOrderTents = new MySingleWithOutTailLinkedList();
        outOfOrderTents.add(tentOnly3);
        outOfOrderTents.add(tentOnly1);
        outOfOrderTents.add(tentOnly2);
        assertEquals(tentOnly1,outOfOrderTents.get(0));
        assertEquals(tentOnly2,outOfOrderTents.get(1));
        assertEquals(tentOnly3,outOfOrderTents.get(2));

        //adding out of order RV's
        MySingleWithOutTailLinkedList outOfOrderRVs = new MySingleWithOutTailLinkedList();
        outOfOrderRVs.add(RV3);
        outOfOrderRVs.add(RV1);
        outOfOrderRVs.add(RV2);
        assertEquals(RV1,outOfOrderRVs.get(0));
        assertEquals(RV2,outOfOrderRVs.get(1));
        assertEquals(RV3,outOfOrderRVs.get(2));

        //adding alternating tent and RV's out of order
        MySingleWithOutTailLinkedList outOfOrderAlt = new MySingleWithOutTailLinkedList();

        outOfOrderAlt.add(tentOnly2);
        outOfOrderAlt.add(RV3);
        outOfOrderAlt.add(tentOnly1);
        outOfOrderAlt.add(RV2);
        outOfOrderAlt.add(tentOnly3);
        outOfOrderAlt.add(RV1);

        assertEquals(tentOnly1,outOfOrderAlt.get(0));
        assertEquals(tentOnly2,outOfOrderAlt.get(1));
        assertEquals(tentOnly3,outOfOrderAlt.get(2));
        assertEquals(RV1,outOfOrderAlt.get(3));
        assertEquals(RV2,outOfOrderAlt.get(4));
        assertEquals(RV3,outOfOrderAlt.get(5));

        // out of order bigger list RVs plus new head at end
        MySingleWithOutTailLinkedList lottaRV = new MySingleWithOutTailLinkedList();
        lottaRV.add(RV2);
        lottaRV.add(RV5);
        lottaRV.add(RV3);
        lottaRV.add(RV1);
        lottaRV.add(RV4);
        lottaRV.add(tentOnly2);
        assertEquals(tentOnly2,lottaRV.get(0));
        assertEquals(RV1,lottaRV.get(1));
        assertEquals(RV2,lottaRV.get(2));
        assertEquals(RV3,lottaRV.get(3));
        assertEquals(RV4,lottaRV.get(4));
        assertEquals(RV5,lottaRV.get(5));

        // out of order bigger list Tents plus rv at beginning and end
        MySingleWithOutTailLinkedList lottaTent= new MySingleWithOutTailLinkedList();
        lottaTent.add(RV4);
        lottaTent.add(tentOnly3);
        lottaTent.add(tentOnly1);
        lottaTent.add(tentOnly2);
        lottaTent.add(tentOnly5);
        lottaTent.add(tentOnly4);
        lottaTent.add(RV3);


        assertEquals(tentOnly1,lottaTent.get(0));
        assertEquals(tentOnly2,lottaTent.get(1));
        assertEquals(tentOnly3,lottaTent.get(2));
        assertEquals(tentOnly4,lottaTent.get(3));
        assertEquals(tentOnly5,lottaTent.get(4));
        assertEquals(RV3,lottaTent.get(5));
        assertEquals(RV4,lottaTent.get(6));

        // big out of order list
        MySingleWithOutTailLinkedList lottaCamp = new MySingleWithOutTailLinkedList();
        lottaCamp.add(RV2);
        lottaCamp.add(RV1);
        lottaCamp.add(tentOnly5);
        lottaCamp.add(tentOnly3);
        lottaCamp.add(tentOnly4);
        lottaCamp.add(RV5);
        lottaCamp.add(RV3);
        lottaCamp.add(tentOnly1);
        lottaCamp.add(RV4);
        lottaCamp.add(tentOnly2);

        assertEquals(tentOnly1,lottaCamp.get(0));
        assertEquals(tentOnly2,lottaCamp.get(1));
        assertEquals(tentOnly3,lottaCamp.get(2));
        assertEquals(tentOnly4,lottaCamp.get(3));
        assertEquals(tentOnly5,lottaCamp.get(4));
        assertEquals(RV1,lottaCamp.get(5));
        assertEquals(RV2,lottaCamp.get(6));
        assertEquals(RV3,lottaCamp.get(7));
        assertEquals(RV4,lottaCamp.get(8));
        assertEquals(RV5,lottaCamp.get(9));


    }

    /**
     * testing that a null date cannot be added to the list
     */
    @Test(expected = NullPointerException.class)
    public void nullDateAdd(){
        RV nullDateRV = new RV("RV3",null,null,null, 1000);
        // adding null dates; should throw null pointer
        MySingleWithOutTailLinkedList theNulls = new MySingleWithOutTailLinkedList();
        theNulls.add(nullDateRV);
        assertNull(theNulls.get(0));

    }

    /**
     * testing that a null element cannot be added
     */
    @Test(expected = NullPointerException.class)
    public void nullAdd() {
        RV nullRV = null;
        // adding null; should throw null pointer
        MySingleWithOutTailLinkedList theNulls = new MySingleWithOutTailLinkedList();
        theNulls.add(nullRV);
        assertNull(theNulls.get(0));

    }

    /**
     * testing that a invalid index cannot be removed
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeWrongIndex() {
        MySingleWithOutTailLinkedList blankList= new MySingleWithOutTailLinkedList();
        blankList.remove(-1);
        blankList.remove(3);


    }

    /**
     * testing adding objects with the same estimated checkout date and with same name
     */
    @Test
    public void addEquals(){
        // create RV and Tents for testing
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        try {
            Date d1 = df.parse("1/1/2020");
            g1.setTime(d1);

            Date d2 = df.parse("1/1/2020");
            g2.setTime(d2);

            Date d3 = df.parse("03/20/2020");
            g3.setTime(d3);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Tent 1 and 2 have same date, should put Max tent first and Seth tent second
        TentOnly tentOnly1 = new TentOnly("Seth", g1, g2,g2,4);
        TentOnly tentOnly2 = new TentOnly("Max", g1,g2,g1, 8);
        TentOnly tentOnly3 = new TentOnly("T3",g2,g3,g1,45);
        TentOnly tentOnly5 = new TentOnly("Zoro", g1, g2,g2,4);
        //Tent 2 and 3 have same date, should put Max RV first and Seth RV second
        RV RV1 = new RV("RV1",g1,g2,g2, 1000);
        RV RV2 = new RV("Seth",g1,g3,g2, 1000);
        RV RV3 = new RV("Max",g1,g3,g2, 1000);
        RV RV4 = new RV("Zoro",g1,g3,g2, 1000);
        RV RV1Copy = new RV("RV1",g1,g2,g2, 1000);

        //list with three campsites, two rvs and two tents that are equal
        MySingleWithOutTailLinkedList equalsList = new MySingleWithOutTailLinkedList();

        //add RVs first then tents, out of order.
        equalsList.add(RV1);
        equalsList.add(RV2);
        equalsList.add(RV3);
        equalsList.add(tentOnly1);
        equalsList.add(tentOnly2);
        equalsList.add(tentOnly3);

        assertEquals(tentOnly2, equalsList.get(0));
        assertEquals(tentOnly1, equalsList.get(1));
        assertEquals(tentOnly3, equalsList.get(2));
        assertEquals(RV1, equalsList.get(3));
        assertEquals(RV3, equalsList.get(4));
        assertEquals(RV2, equalsList.get(5));

        //add another tent so there are three with the same date
        TentOnly tentOnly4 = new TentOnly("Bill", g1, g2, g3, 4);

        equalsList.add(tentOnly4);

        assertEquals(tentOnly4, equalsList.get(0));
        assertEquals(tentOnly2, equalsList.get(1));
        assertEquals(tentOnly1, equalsList.get(2));
        assertEquals(tentOnly3, equalsList.get(3));
        assertEquals(RV1, equalsList.get(4));
        assertEquals(RV3, equalsList.get(5));
        assertEquals(RV2, equalsList.get(6));

        //list with only two elements with same date
        MySingleWithOutTailLinkedList equalsList2 = new MySingleWithOutTailLinkedList();

        equalsList2.add(tentOnly1);
        equalsList2.add(tentOnly2);

        assertEquals(tentOnly2, equalsList2.get(0));
        assertEquals(tentOnly1, equalsList2.get(1));

        // trying to add four tents with equal dates
        MySingleWithOutTailLinkedList equalsList3 = new MySingleWithOutTailLinkedList();
        equalsList3.add(tentOnly1);
        equalsList3.add(tentOnly2);
        equalsList3.add(tentOnly3);
        equalsList3.add(tentOnly4);
        equalsList3.add(tentOnly5);
        assertEquals(tentOnly4,equalsList3.get(0));
        assertEquals(tentOnly2,equalsList3.get(1));
        assertEquals(tentOnly1,equalsList3.get(2));
        assertEquals(tentOnly5,equalsList3.get(3));
        assertEquals(tentOnly3,equalsList3.get(4));

        //alternating RV Tent with equals thrown in
        MySingleWithOutTailLinkedList alt = new MySingleWithOutTailLinkedList();
        alt.add(RV2);
        alt.add(tentOnly1);
        alt.add(RV3);
        alt.add(tentOnly2);
        alt.add(RV4);
        alt.add(RV1);
        alt.add(tentOnly4);
        assertEquals(tentOnly4,alt.get(0));
        assertEquals(tentOnly2,alt.get(1));
        assertEquals(tentOnly1,alt.get(2));
        assertEquals(RV1,alt.get(3));
        assertEquals(RV3,alt.get(4));
        assertEquals(RV2,alt.get(5));
        assertEquals(RV4,alt.get(6));


        // same object added and different object same name
        MySingleWithOutTailLinkedList dup = new MySingleWithOutTailLinkedList();
        dup.add(RV1);
        dup.add(RV1);
        dup.add(RV1Copy);
        assertEquals(RV1,dup.get(0));

        //test two with the same name when three have the same date. Second added should come after
        MySingleWithOutTailLinkedList dupName = new MySingleWithOutTailLinkedList();
        RV max = new RV("Max",g1,g2,g2, 1000);
        RV max2 = new RV("Max",g1,g2,g3, 1000);

        dupName.add(RV1);
        dupName.add(tentOnly3);
        dupName.add(tentOnly1);
        dupName.add(max2);
        dupName.add(RV4);
        dupName.add(max);
        assertEquals(max2, dupName.get(2));
        assertEquals(max, dupName.get(3));
        assertEquals(RV1,dupName.get(4));

        //List size two with the same names
        MySingleWithOutTailLinkedList help = new MySingleWithOutTailLinkedList();
        help.add(max2);
        help.add(max);

        assertEquals(max2, help.get(0));
        assertEquals(max, help.get(1));

        // now with 3
        help.add(max2);
        assertEquals(max2, help.get(0));
        assertEquals(max, help.get(1));
        assertEquals(max2,help.get(2));

        // now with 4
        help.add(max);
        assertEquals(max2, help.get(0));
        assertEquals(max, help.get(1));
        assertEquals(max2,help.get(2));
        assertEquals(max,help.get(3));

        // equals with alternating tents and RV's
        MySingleWithOutTailLinkedList mixed = new MySingleWithOutTailLinkedList();
        mixed.add(max);
        mixed.add(tentOnly1);
        mixed.add(max2);
        mixed.add(tentOnly1);
        assertEquals(tentOnly1,mixed.get(0));
        assertEquals(tentOnly1,mixed.get(1));
        assertEquals(max,mixed.get(2));
        assertEquals(max2,mixed.get(3));
    }

    /**
     * tes
     */
    @Test
    public void remove() {
        //create dates
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = null;
        Date d2 = null;
        Date d5 = null;
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        try {
            d1 = df.parse("1/1/2020");
            g1.setTime(d1);

            d2 = df.parse("1/4/2020");
            g2.setTime(d2);

            d5 = df.parse("01/01/9000");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //creating tents and RVs for testing
        TentOnly tentOnly1 = new TentOnly("T1", g1, g1,g2,4);
        TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g1, 8);
        TentOnly tentOnly4 = new TentOnly("T4",g2,g5,g1,45);
        RV RV1 = new RV("RV1",g1,g1,g2, 1000);
        RV RV2 = new RV("RV2",g1,g2,g1, 1000);
        RV RV5 = new RV("RV5",g1,g5,g1, 1000);
        //create general list
        MySingleWithOutTailLinkedList remTest = new MySingleWithOutTailLinkedList();
        remTest.add(RV1);
        remTest.add(RV2);
        remTest.add(RV5);
        remTest.add(tentOnly1);
        remTest.add(tentOnly2);
        remTest.add(tentOnly4);

        assertEquals(tentOnly1, remTest.get(0));
        assertEquals(tentOnly2, remTest.get(1));
        assertEquals(tentOnly4, remTest.get(2));
        assertEquals(RV1, remTest.get(3));
        assertEquals(RV2, remTest.get(4));
        assertEquals(RV5, remTest.get(5));
        assertEquals(6, remTest.size());

        //Remove a campsite
        remTest.remove(2);

        //Check that index(s) and size adjusted accordingly
        assertEquals(tentOnly1, remTest.get(0));
        assertEquals(tentOnly2, remTest.get(1));
        assertEquals(RV1, remTest.get(2));
        assertEquals(RV2, remTest.get(3));
        assertEquals(RV5, remTest.get(4));
        assertEquals(5, remTest.size());

        // check that it can remove with size 1
        MySingleWithOutTailLinkedList one = new MySingleWithOutTailLinkedList();
        one.add(RV1);
        one.remove(0);
        assertEquals(0,one.size);

        // check that it can remove from front with size 2
        MySingleWithOutTailLinkedList frontTwo = new MySingleWithOutTailLinkedList();
        frontTwo.add(RV1);
        frontTwo.add(tentOnly1);
        frontTwo.remove(0);
        assertEquals(RV1,frontTwo.get(0));

        // check that it can remove from back with size two
        MySingleWithOutTailLinkedList backTwo  = new MySingleWithOutTailLinkedList();
        backTwo.add(RV1);
        backTwo.add(RV5);
        backTwo.remove(1);
        assertEquals(RV1,backTwo.get(0));
        assertEquals(1,backTwo.size);

        //check it can remove from back with big list
        MySingleWithOutTailLinkedList backBig = new MySingleWithOutTailLinkedList();
        backBig.add(tentOnly1);
        backBig.add(tentOnly4);
        backBig.add(tentOnly2);
        backBig.add(RV5);
        backBig.add(RV2);
        backBig.add(RV1);
        backBig.remove(5);
        assertEquals(tentOnly1,backBig.get(0));
        assertEquals(tentOnly2,backBig.get(1));
        assertEquals(tentOnly4,backBig.get(2));
        assertEquals(RV1,backBig.get(3));
        assertEquals(RV2,backBig.get(4));
        assertEquals(5,backBig.size);

        //check it can remove from front with big list
        MySingleWithOutTailLinkedList frontBig = new MySingleWithOutTailLinkedList();
        frontBig.add(tentOnly1);
        frontBig.add(tentOnly4);
        frontBig.add(tentOnly2);
        frontBig.add(RV5);
        frontBig.add(RV2);
        frontBig.add(RV1);
        frontBig.remove(0);
        assertEquals(tentOnly2,frontBig.get(0));
        assertEquals(tentOnly4,frontBig.get(1));
        assertEquals(RV1,frontBig.get(2));
        assertEquals(RV2,frontBig.get(3));
        assertEquals(RV5,frontBig.get(4));
        assertEquals(5,frontBig.size);
    }

    /**
     * testing the get function
     */
    @Test
    public void get() {
        //create dates and tents and RV's
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        try {
            Date d1 = df.parse("6/3/2001");
            g1.setTime(d1);

            Date d2 = df.parse("4/2/2020");
            g2.setTime(d2);

            Date d5 = df.parse("01/01/3000");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        MySingleWithOutTailLinkedList getTest = new MySingleWithOutTailLinkedList();

        //adding and getting with size 1
        getTest.add(new TentOnly("Max's Tent", g1, g2, null, 4));
        assertEquals("Max's Tent", getTest.get(0).getGuestName());
        //adding and getting with size 2
        getTest.add(new RV("Seth's RV", g2, g5, null, 1234));
        assertEquals("Seth's RV", getTest.get(1).getGuestName());
    }
}