package Project3;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static Project3.ScreenDisplay.CurrentParkStatus;

/**
 * a class that creates a model that stores data about a camp site in a grid format
 * @author Seth Ockerman
 * @author Max Foreback
 */
public class ListModel extends AbstractTableModel {
    // arrayList of campsites
    private ArrayList<CampSite> listCampSites;

    // arrays for sorting the RV Tent
    ArrayList<CampSite> firstHalfRVTent;
    ArrayList<CampSite> secondHalfRVTent;

    // arrays for sorting the Tent RV
    ArrayList<CampSite> firstHalfTentRV;
    ArrayList<CampSite> secondHalfTentRV;

    // arraylist of campsites after a filter has been displayed
    private ArrayList<CampSite> fileredListCampSites;

    // the current screen display
    private ScreenDisplay display;

    // array of strings that will be displayed in the current park columns
    private String[] columnNamesCurrentPark = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ", "Max Power", "Num of Tenters"};
    // name of each column for the checkOut listModel
    private String[] columnNamesforCheckouts = {"Guest Name", "Est. Cost",
            "Check in Date", "ACTUAL Check out Date ", " Real Cost"};
    // name of each column in the overday ListModel
    private String[] columnNamesforOverDue = {"Guest Name", "Est. Cost",
            "Estimated CheckoutDate", "Days Overdue"};
    // name of each column in the RVTent listModel
    private String[] columnNamesRVTent = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ", "Max Power", "Num of Tenters"};
    // name of each column in the TentRv listModel
    private String[] columnNamesTentRV = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ", "Max Power", "Num of Tenters"};


    // creating a formatter object with basic date setup
    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    // string representation of the date
    private String date;

    // relative date for overdue
    private GregorianCalendar relativeDate;

    /**
     * Constructor that creates a list model, sets the display
     * and arrayList for campSites
     */
    public ListModel() {
        super();
        display = CurrentParkStatus;
        listCampSites = new ArrayList<CampSite>();
        UpdateScreen();
        createList();
    }

    /**
     * Constructor that max is messing with
     */
    public ListModel(int i) {
        super();
        display = CurrentParkStatus;
        listCampSites = new ArrayList<CampSite>();
        UpdateScreen();
    }

    /**
     * sets the display equal to the display passed in
     * @param selected the desired current screen
     */
    public void setDisplay(ScreenDisplay selected) {
        display = selected;
        UpdateScreen();
    }

    /**
     * sets the display equal to the display passed in
     * and takes a relative date
     * @param selected the desired current screen
     * @param x the date relative for comparing it
     */
    public void setDisplay(ScreenDisplay selected, GregorianCalendar x) {
        display = selected;
        relativeDate = x;
        UpdateScreen();
    }
    /*
    * I honestly don't know what this does yet besides the name updateScreen
     */
    private void UpdateScreen() {

        switch (display) {
            case CurrentParkStatus:
                // displays all not checked out campsites in alphabetical order
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.actualCheckOut == null).collect(Collectors.toList());

                // Note: This uses Lambda function
                Collections.sort(fileredListCampSites, (n1, n2) -> n1.getGuestName().compareTo(n2.guestName));
                break;

            case CheckOutGuest:
                // displays all campsites that have already checked out
                fileredListCampSites = (ArrayList<CampSite>)
                        listCampSites.stream().
                        filter(n -> n.getActualCheckOut() != null)
                        .collect(Collectors.toList());
                        break;
            case OverDue:
                // filters first ones that haven't actually checked out yet then sorts by days overdue
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n.getActualCheckOut() == null)
                        .collect(Collectors.toList());
                // sorting by days overdue
                Collections.sort(fileredListCampSites, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite o1, CampSite o2) {
                        if( o1.daysOverdue(relativeDate) < o2.daysOverdue(relativeDate)){
                            return 1;
                        }
                        if( o1.daysOverdue(relativeDate) > o2.daysOverdue(relativeDate)){
                            return -1;
                        }
                        return 0;
                    }
                });
                break;
            case RVTent:
                // LAMBA FUNCTION USED HERE

                // adding all RVs to a first half array
                firstHalfRVTent = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n instanceof RV )
                        .collect(Collectors.toList());
                // sorting by alphabetic order
                Collections.sort(firstHalfRVTent, (n1, n2) ->  n1.getGuestName().compareTo(n2.getGuestName()));

                // adding all Tents to a second half array
                secondHalfRVTent = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n instanceof TentOnly)
                        .collect(Collectors.toList());
                // sorting by alphabetic order
                Collections.sort(secondHalfRVTent, (n1, n2) ->  n1.getGuestName().compareTo(n2.getGuestName()));

                // clears list than adds first sorted RV's then sorted tents
                fileredListCampSites.clear();
                fileredListCampSites.addAll(firstHalfRVTent);
                fileredListCampSites.addAll(secondHalfRVTent);
                break;

            case TentRV:
                // ANONYMOUS CLASS USED HERE
                // adding all Tents to a firstHalf array
                firstHalfTentRV = (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n instanceof TentOnly)
                        .collect(Collectors.toList());
                // sorting based on name. If name is same, sort by days overdue
                Collections.sort(firstHalfTentRV, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite n1, CampSite n2) {
                         int returnValue = n1.getGuestName().compareTo(n2.guestName);
                         if(returnValue == 0){
                             returnValue = n1.getEstimatedCheckOut().compareTo(n2.getEstimatedCheckOut());
                         }
                         return returnValue;
                    }
                });
                // adding all RVs to second half array
                secondHalfTentRV= (ArrayList<CampSite>) listCampSites.stream()
                        .filter(n -> n instanceof RV)
                        .collect(Collectors.toList());
                // sorting based on name. If name is same, sort by days overdue
                Collections.sort(secondHalfTentRV, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite n1, CampSite n2) {
                        int returnValue = n1.getGuestName().compareTo(n2.guestName);
                        if(returnValue == 0){
                            returnValue = n1.getEstimatedCheckOut().compareTo(n2.getEstimatedCheckOut());
                        }
                        return returnValue;
                    }
                });
                // clears list than adds first sorted Tents then sorted RVs
                fileredListCampSites.clear();
                fileredListCampSites.addAll(firstHalfTentRV);
                fileredListCampSites.addAll(secondHalfTentRV);
                break;

            default:
                throw new RuntimeException("upDate is in undefined state: " + display);
        }
        fireTableStructureChanged();
        }

    /**
     * Gets name of column based on preset
     * @param col the column you want the name of
     * @return the string name of an object
     */
    @Override
    public String getColumnName(int col) {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark[col];
            case CheckOutGuest:
                return columnNamesforCheckouts[col];
            case OverDue:
                return columnNamesforOverDue[col];
            case RVTent:
                return columnNamesRVTent[col];
            case TentRV:
                return columnNamesTentRV[col];
        }
        throw new RuntimeException("Undefined state for Col Names: " + display);
    }

    /**
     * gets the number of columns in the display
     * @return the number of columns as an int
     * @throws IllegalArgumentException
     */
    @Override
    public int getColumnCount() {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark.length;
            case CheckOutGuest:
                return columnNamesforCheckouts.length;
            case OverDue:
                return columnNamesforOverDue.length;
            case RVTent:
                return columnNamesRVTent.length;
            case TentRV:
                return columnNamesTentRV.length;
        }
        throw new IllegalArgumentException();
    }

    /**
     * gets how many rows there is
     * @return the number of rows as an arrayList
     */
    @Override
    public int getRowCount() {
        return fileredListCampSites.size();     // returns number of items in the arraylist
    }

    /**
     * Unclear. I believe it gets the object at the coordinates
     * @param row the row of the object you want
     * @param col the column of the object you want
     * @return the object at the coordinates
     */
    @Override
    public Object getValueAt(int row, int col) {
        switch (display) {
            case CurrentParkStatus:
                return currentParkScreen(row, col);
            case CheckOutGuest:
                return checkOutScreen(row, col);
            case OverDue:
                return overDueScreen(row,col);
            case RVTent:
                return RVTentParkScreen(row,col);
            case TentRV:
                return TentRVParkScreen(row,col);
          }
        throw new IllegalArgumentException();
    }

    /*
    * Table model setup for currentParkScreen
     */
    private Object currentParkScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut, fileredListCampSites.get(row).getCheckIn()));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                                getTime()));

            case 4:
                // look at this later
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }
    /*
    *configures listModel Screen for checkout
    * @param row the row it is getting data from
    * @param col the col it is getting data from
     */
    private Object checkOutScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        estimatedCheckOut,fileredListCampSites.get(row).getCheckIn()));
            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.
                                getTime()));

            case 3:
                return (formatter.format(fileredListCampSites.get(row).actualCheckOut.
                                getTime()));

            case 4:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        actualCheckOut,  fileredListCampSites.get(row).getCheckIn()));

            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }
    /*
     *configures listModel Screen for the OverDueScreen
     * @param row the row it is getting data from
     * @param col the col it is getting data from
     */
    private Object overDueScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        estimatedCheckOut,fileredListCampSites.get(row).getCheckIn()));
            case 2:
                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 3:
                return fileredListCampSites.get(row).daysOverdue(relativeDate);
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }
    /*
    * setting up listModel for RVTent screen
    * @param row the row it is getting data from
     * @param col the col it is getting data from
     */
    private Object RVTentParkScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut, fileredListCampSites.get(row).getCheckIn()));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
                // look at this later
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    /*
     * setting up listModel for TentRV screen
     * @param row the row it is getting data from
     * @param col the col it is getting data from
     */
    private Object TentRVParkScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut, fileredListCampSites.get(row).getCheckIn()));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
                // look at this later
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }
    /**
     * adds campsite to the arrayList
     * @param a the campsite to be added
     */
    public void add(CampSite a) {
        listCampSites.add(a);
        UpdateScreen();
    }

    /**
     * adds campsite to the arrayList
     * @param a the campsite to be added
     */
    public void remove(CampSite a) {
        listCampSites.remove(a);
        UpdateScreen();
    }

    /**
     * gets the campsite at an index
     * @param i the index of the desired campsite
     * @return the campsite at the index
     */
    public CampSite get(int i) {
        return fileredListCampSites.get(i);
    }

    /**
     * updates the screen of the campsite at an index
     * @param index the index of the campsite you want updated
     * @param unit what type of unit to update it to
     */
    public void upDate(int index, CampSite unit) {
        UpdateScreen();
    }

    /**
     * saves data base to files
     * @param filename the name you want to assign to the data base
     */
    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listCampSites);
            os.close();
        } catch (IOException ex) {
            throw new RuntimeException("Saving problem! " + display);

        }
    }
    // attempt 1
    public String saveTextRep() throws IOException {
        String first = listCampSites.get(0).toString();
        return first;

    }

    /**
     * loads the campsites from a file
     * @param filename the name of the file you want loaded
     */
    public void loadDatabase(String filename) {
        listCampSites.clear();

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listCampSites = (ArrayList<CampSite>) is.readObject();
            UpdateScreen();
            is.close();
        } catch (Exception ex) {
            throw new RuntimeException("Loading problem: " + display);

        }
    }

    /**
     * Saves the campsites from a text file in GUI.
     * Here converts database into a String.
     * @return a full string containing the database
     */
    public String saveTextString() {
        String temp = fileredListCampSites.toString();
        for (int i = 0; i < temp.length(); i++)
            if(temp.charAt(i) == '}' && temp.charAt(i + 1) == '}')
                temp = temp.substring(0, i + 2) + '\n' + temp.substring(i + 3);
            return temp;
    }

    /**
     * loads the campsites from a text file
     */
    public void loadTextFile() {
        //Clear the current campsites
        listCampSites.clear();
        //Create scanners and date formats
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try{
        Scanner scnr = new Scanner(new File("MyFile.txt"));
        Scanner scr = new Scanner(new File("MyFile.txt"));
        //number of checkouts in the file
        int checkOuts = 0;
        while(scr.hasNextLine()) {
            checkOuts++;
            scr.nextLine();
        }
        /*iterates through each line, getting specified information and creating new
            tentOnly or RV sites when needed. */
        for(int i = 0; i < checkOuts; i++){
            GregorianCalendar g1 = new GregorianCalendar();
            GregorianCalendar g2 = new GregorianCalendar();
            GregorianCalendar g3 = new GregorianCalendar();
            String temp = scnr.nextLine();
            String name = temp.substring(temp.indexOf("Name='") + 6, temp.lastIndexOf("'"));
            String inDate = temp.substring(temp.indexOf("checkIn=") + 8, temp.indexOf("estim") - 2);
            String outDate = temp.substring(temp.indexOf("estimatedCheckOut=") + 18, temp.indexOf("actu") - 2);
            String actualOut = temp.substring(temp.indexOf("actualCheckOut=") + 15, temp.indexOf("}"));
            if(actualOut.equals("")) {
                actualOut = null;
            }
            else {
                Date d3 = df.parse(actualOut);
                g3.setTime(d3);
            }
            Date d1 = df.parse(inDate);
            g1.setTime(d1);
            Date d2 = df.parse(outDate);
            g2.setTime(d2);
            //Creating tent only campsites
            if(temp.charAt(1) == 'T') {
                String numCampers = temp.substring(temp.indexOf("OfTenters=") + 10, temp.indexOf("Cam") - 1);
                if(actualOut != null) {
                    TentOnly t1 = new TentOnly(name, g1, g2, g3, Integer.parseInt(numCampers));
                    listCampSites.add(t1);
                }
                else {
                    TentOnly t1 = new TentOnly(name, g1, g2, null, Integer.parseInt(numCampers));
                    listCampSites.add(t1);
                }
            }
            //Creating RV only campsites
            else if(temp.charAt(1) == 'R') {
                String numCampers = temp.substring(temp.indexOf("power=") + 6, temp.indexOf("Cam") - 1);
                if(actualOut != null) {
                    RV r1 = new RV(name, g1, g2, g3, Integer.parseInt(numCampers));
                    listCampSites.add(r1);
                }
                else {
                    RV r1 = new RV(name, g1, g2, null, Integer.parseInt(numCampers));
                    listCampSites.add(r1);
                }
            }
            else System.out.println("Error reading file");

        }
        }
        catch (FileNotFoundException f){
            System.out.println("File not found");
        }
        catch (ParseException p){
            System.out.println("Parsing problems");
        }
        finally {
            UpdateScreen();
        }
    }

    /**
     * creates list of defualt campsite reservations
     * @throws RuntimeException there was an error in creation of the default list
     */
    public void createList() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2020");
            g1.setTime(d1);
            Date d2 = df.parse("12/22/2020");
            g2.setTime(d2);
            Date d3 = df.parse("12/20/2019");
            g3.setTime(d3);
            Date d4 = df.parse("3/25/2020");
            g4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            g5.setTime(d5);
            Date d6 = df.parse("3/29/2020");
            g6.setTime(d6);

            TentOnly tentOnly1 = new TentOnly("T1", g3, g2,null,4);
            TentOnly tentOnly11 = new TentOnly("T1", g3,g1, null, 8);
            TentOnly tentOnly111 = new TentOnly("T1", g5,g3, null, 8);
            TentOnly tentOnly2 = new TentOnly("T2", g5, g3,null, 5);
            TentOnly tentOnly3 = new TentOnly("T3", g3, g1, g1,7);

            RV RV1 = new RV("RV1",g4,g6,null, 1000);
            RV RV2 = new RV("RV2",g5,g3,null, 1000);
            RV RV22 = new RV("RV2", g3,g1,null, 2000);
            RV RV222 = new RV("RV2", g3,g6,null, 2000);
            RV RV3 = new RV("RV3",g5,g4,g3, 1000);

            add(tentOnly1);
            add(tentOnly2);
            add(tentOnly3);
            add(tentOnly11);
            add(tentOnly111);

            add(RV1);
            add(RV2);
            add(RV3);
            add(RV22);
            add(RV222);

        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }
    }

    public ScreenDisplay getDisplay(){
        return this.display;
    }

    public GregorianCalendar getRelativeDate(){
        return this.relativeDate;
    }
}

