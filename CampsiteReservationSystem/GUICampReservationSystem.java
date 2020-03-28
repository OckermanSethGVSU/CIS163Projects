package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

/*****************************************************************
 *
 *  Campers Reservation System GUI
 * @author Seth Ockerman
 * @author Max Foreback
 *****************************************************************/
public class GUICampReservationSystem extends JFrame implements ActionListener{
    // menu bar
    private JMenuBar menus;

    // menus in the menu bar
    private JMenu fileMenu;
    private JMenu actionMenu;

    // menu items in each of the menus
    private JMenuItem openSerItem;
    private JMenuItem exitItem;
    private JMenuItem saveSerItem;
    private JMenuItem openTextItem;
    private JMenuItem saveTextItem;
    private JMenuItem reserveRVItem;
    private JMenuItem reserveTentOnlyItem;
    private JMenuItem checkOutItem;

    private JMenuItem currentParkItemScn;
    private JMenuItem checkOUtItemScn;
    private JMenuItem overDueItemScn;
    private JMenuItem sortTentRvItemScn;
    private JMenuItem sortRvTentItemScn;

    private JPanel panel;

    /** Holds the list engine */
    private ListModel DList;

    // Holds jTable
    private JTable jTable;

    // Scroll pane
    private JScrollPane scrollList;

    /*****************************************************************
     * A constructor that starts a new GUI for a Campsite reservation system
     *****************************************************************/
    public GUICampReservationSystem(){
        //adding menu bar and menu items
        menus = new JMenuBar();
        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Action");
        openSerItem = new JMenuItem("Open File");
        exitItem = new JMenuItem("Exit");
        saveSerItem = new JMenuItem("Save File");
        openTextItem = new JMenuItem("Open Text");
        saveTextItem = new JMenuItem("Save Text");
        reserveRVItem = new JMenuItem("Reserve a RV Site");
        reserveTentOnlyItem = new JMenuItem("Reserve a TentOnly site");
        checkOutItem = new JMenuItem("CheckOut of TentOnly or RV");

        currentParkItemScn = new JMenuItem("Current Park Screen");
        checkOUtItemScn = new JMenuItem("Check out screen");
        overDueItemScn = new JMenuItem("OverDue Screen");
        sortRvTentItemScn = new JMenuItem("Sort RV, tent Screen");
        sortTentRvItemScn = new JMenuItem("Sort tent, RV Screen");

        //adding items to bar
        fileMenu.add(openSerItem);
        fileMenu.add(saveSerItem);
        fileMenu.addSeparator();
        fileMenu.add(openTextItem);
        fileMenu.add(saveTextItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        fileMenu.addSeparator();
        fileMenu.add(currentParkItemScn);
        fileMenu.add(checkOUtItemScn);
        fileMenu.add(overDueItemScn);
        fileMenu.add (sortRvTentItemScn);
        fileMenu.add(sortTentRvItemScn);

        actionMenu.add(reserveRVItem);
        actionMenu.add(reserveTentOnlyItem);
        actionMenu.addSeparator();
        actionMenu.add(checkOutItem);

        menus.add(fileMenu);
        menus.add(actionMenu);

        //adding actionListeners
        openSerItem.addActionListener(this);
        saveSerItem.addActionListener(this);
        openTextItem.addActionListener(this);
        saveTextItem.addActionListener(this);
        exitItem.addActionListener(this);
        reserveRVItem.addActionListener(this);
        reserveTentOnlyItem.addActionListener(this);
        checkOutItem.addActionListener(this);

        currentParkItemScn.addActionListener(this);
        checkOUtItemScn.addActionListener(this);
        overDueItemScn.addActionListener(this);
        sortRvTentItemScn.addActionListener(this);
        sortTentRvItemScn.addActionListener(this);

        //setting menu
        setJMenuBar(menus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adding final components
        panel = new JPanel();
        DList = new ListModel();
        jTable = new JTable(DList);
        scrollList = new JScrollPane(jTable);
        panel.add(scrollList);
        add(panel);
        scrollList.setPreferredSize(new Dimension(800,300));

        setVisible(true);
        setSize(950,450);
    }

    public void actionPerformed(ActionEvent e) {
        // stores the object that was clicked
        Object comp = e.getSource();
        // sets display to currentPark screen if clicked
        if (currentParkItemScn == comp)
            DList.setDisplay(ScreenDisplay.CurrentParkStatus);
        // sets display to checkout screen if clicked
        if (checkOUtItemScn == comp)
            DList.setDisplay(ScreenDisplay.CheckOutGuest);
        //sets display to overdue screen if clicked
        if(overDueItemScn == comp ){
            //creates date
            Date d = null;
            /*
            * sets screen to Overdue screen if clicked
            * attempts to parse user input into date
            * if fails to parse, it asks them to enter correctly formatted date
             */
            boolean valid = false;

            try {
                while(!valid){
                    String date = JOptionPane.showInputDialog("Enter relative date: format dd MM yyyy");
                    DateFormat df = new SimpleDateFormat("dd MM yyyy");
                    String[] splited = date.split("\\s+");
                    int[] val = new int[splited.length];

                    try {
                        for (int i = 0; i < splited.length; i++) {
                            val[i] = Integer.parseInt(splited[i]);
                        }
                        valid = isDateValid(val[1], val[0], val[2]);
                        if(!valid){
                            JOptionPane.showMessageDialog(null, "Invalid day, month, year combination. Try again" +
                                    "; Enter relative date: format dd MM yyyy");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid format. Try again" +
                                "; Enter relative date: format dd MM yyyy");

                    }

                    while (d == null && valid) {
                        try {
                            d = df.parse(date);
                        } catch (ParseException ex) {
                            date = JOptionPane.showInputDialog("Incorrect format or impossible date" +
                                    "; Enter relative date: format dd MM yyyy");
                        }
                    }
                }
                // creating calender and setting the time equal to the passed in value
                Calendar cal = new GregorianCalendar();
                cal.setTime(d);
                // finally sets display
                DList.setDisplay(ScreenDisplay.OverDue, (GregorianCalendar) cal);
            }
            // allows it to exit by catching null pointer exception
            catch (NullPointerException x) {
                System.out.println("Blanket catch");
                // blanket catch. Could be improved. Right now the it handles cancel which there is functionality to do
            }


        }
        // sets display to RVTent screen if clicked
        if(sortRvTentItemScn == comp){
            DList.setDisplay(ScreenDisplay.RVTent);
        }
        // sets display to TentRV screen if clicked
        if(sortTentRvItemScn == comp){
            DList.setDisplay(ScreenDisplay.TentRV);
        }
        // opens file. Based around saving it as a database
        if (openSerItem == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if (openSerItem == comp)
                    DList.loadDatabase(filename);
            }
        }
        // saves file as a database
        if (saveSerItem == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if (saveSerItem == e.getSource())
                    DList.saveDatabase(filename);
            }
        }

        // saves information to text file "MyText.txt"
        if (saveTextItem == comp) {

            try {
                FileWriter writer = new FileWriter("MyFile.txt", false);
                writer.write(DList.saveTextString());
                writer.close();
            } catch (IOException z) {
                System.out.println("Error writing to file");
            }
        }

        // loads information to text file "MyText.txt"
        if (openTextItem == comp) {
            DList.loadTextFile();
        }

        //MenuBar options

        // exits if clicked
        if(e.getSource() == exitItem){
            System.exit(1);
        }
        // Reserves RV
        if(e.getSource() == reserveRVItem){
            boolean valid = true;
           try {
               RV RV = new RV();
               ReservationRVDialog dialog = new ReservationRVDialog(this, RV);
               if (CampSite.daysBetween(RV.getCheckIn(), RV.getEstimatedCheckOut()) < 0) {
                   valid = false;
                   JOptionPane.showMessageDialog(null, "Tried to check in after checkout date. Try again");
               }
               if (dialog.getCloseStatus() == ReservationRVDialog.OK && valid) {
                   DList.add(RV);
               }

           } catch (NullPointerException ex) {
               // blanket catch. Could be improved. Right now the it handles cancel which there is functionality to do
           }


        }
        // Reserves Tent
        if(e.getSource() == reserveTentOnlyItem){
            boolean valid = true;
            try {
                TentOnly tentOnly = new TentOnly();
                ReservationTentOnlyDialog dialog = new ReservationTentOnlyDialog(this, tentOnly);
                if (CampSite.daysBetween(tentOnly.getCheckIn(), tentOnly.getEstimatedCheckOut()) < 0) {
                    valid = false;
                    JOptionPane.showMessageDialog(null, "Tried to check in after checkout date. Try again");
                }
                if (dialog.getCloseStatus() == ReservationTentOnlyDialog.OK && valid) {
                    DList.add(tentOnly);
                }
            } catch (NullPointerException ex) {
                // blanket catch. Could be improved. Right now the it handles cancel which there is functionality to do
            }
        }
        // checks out a campsite
        if (checkOutItem == e.getSource()) {

            try{
            // getting location of campsite to be checked out
            int index = jTable.getSelectedRow();
            // if not null, create a calender

                    if (index != -1) {
                        if(DList.get(index).getActualCheckOut() == null) {
                            GregorianCalendar dat = new GregorianCalendar();
                        CampSite unit = DList.get(index);
                        // shows checkout dialog
                        CheckOutOnDialog dialog = new CheckOutOnDialog(this, unit);

                        JOptionPane.showMessageDialog(null,
                                "  Be sure to thank " + unit.getGuestName() +
                                        "\n for camping with us and the price is:  " +
                                        unit.getCost(unit.getActualCheckOut(), unit.getCheckIn()) +
                                        " dollars");

                        // DList.remove(DList.get(index));
                        DList.upDate(index, unit);
                    }
                        else{
                            JOptionPane.showMessageDialog(null,
                                    "This unit has already been checked out");
                        }
                }


            } catch (NullPointerException ex) {
               // blanket catch. Could be improved. Right now the it handles cancel which there is functionality to do
            }
        }
    }

    /**
     * main method that creates a user interface from the GUICampReservationSystem
     * @param args
     */
    public static void main(String[] args) {
        new GUICampReservationSystem();
    }

    /**
    * checks if it is a leap year
    * @param y the year
     */
    public static boolean isLeapYear(int y){
        boolean leapYear = false;
        if (y % 4 == 0 && y % 100 != 0){
            leapYear = true;
        }
        else if (y % 100 == 0 && y % 400 == 0){
            leapYear = true;
        }
        return leapYear;
    }

    /**
     * checks if date is valid
     * @param m the month to check
     * @param d the day to check
     * @param y the year to check
     */
    public static boolean isDateValid( int m, int d, int y){
        boolean isDateValid = false;
        boolean month = false;
        boolean year = false;
        boolean day = false;

        //checks year
        if (y > 0){
            year = true;
        }

        // checks month
        if (m > 0 && m < 13){
            month = true;
        }
        // checks days for months with 31 days: Jan, March, April, May, Jly, August, October, December
        if ( m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12){
            if ( d > 0 && d < 32){
                day = true;
            }
        }

        //checks days for months with 30 days: April, June, September, November
        if (m == 4 || m == 6 || m == 9 || m == 11){
            if (d > 0 && d < 31){
                day = true;
            }
        }

        // handles feb and the leap year stuff
        if (m == 2){
            if ( isLeapYear(y) && d > 0 && d < 30){
                day = true;
            }
            else if(d > 0 && d < 29){
                day = true;
            }
        }

        //makes sure day, month, and year are valid
        if (day && month && year){
            isDateValid = true;
        }

        return isDateValid;
    }
}
