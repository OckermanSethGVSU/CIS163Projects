package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class that controls dialog that pops up when user makes a tent reservation
 * @author Seth Ockerman
 * @author Max Foreback
 */
public class ReservationTentOnlyDialog extends JDialog implements ActionListener {
    // text field for guest name
    private JTextField txtGuestName;

    // text field for date checked in
    private JTextField txtDateCheckin;

    // text field for date checked out
    private JTextField txtDateCheckout;

    // text field for number of buttons
    private JTextField txtNumberOfTenters;

    // creating ok button
    private JButton okButton;

    // creating cancel button
    private JButton cancelButton;

    // int for close status. zero means ok and one means cancel
    private int closeStatus;

    // creating tent object
    private TentOnly tentOnly;

    /**
     * final variable. OK = 0
     */
    public static final int OK = 0;

    /**
     * final variable. CANCEL = 1;
     */
    public static final int CANCEL = 1;

    /*********************************************************
     Instantiate a Custom Dialog as 'modal' and wait for the
     user to provide data and click on a button.

     @param parent reference to the JFrame application
     @param tentOnly an instantiated object to be filled with data
     *********************************************************/

    public ReservationTentOnlyDialog(JFrame parent, TentOnly tentOnly) {
        // call parent and create a 'modal' dialog
        super(parent, true);
        this.tentOnly = tentOnly;

        setTitle("TentOnly dialog box");
        closeStatus = CANCEL;
        setSize(400,200);

        // prevent user from closing window
        //SEEMS TO BE ERROR FROM STARTED CODE. DELETE LATER
        //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // instantiate and display two text fields
        txtGuestName = new JTextField("Judy",30);
        txtDateCheckin = new JTextField(15);
        txtDateCheckout = new JTextField(15);
        txtNumberOfTenters = new JTextField("4", 15);

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        currentDate.add(Calendar.DATE, 1);
        String datetomorrow = formatter.format(currentDate.getTime());


        txtDateCheckin.setText(dateNow);
        txtDateCheckout.setText(datetomorrow);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(5,2));

        textPanel.add(new JLabel(""));
        textPanel.add(new JLabel(""));

        textPanel.add(new JLabel("Name of Tenter: "));
        textPanel.add(txtGuestName);
        textPanel.add(new JLabel("Date on Check in: "));
        textPanel.add(txtDateCheckin);
        textPanel.add(new JLabel("Date on Check out (est.): "));
        textPanel.add(txtDateCheckout);
        textPanel.add(new JLabel("Number of Tenters"));
        textPanel.add(txtNumberOfTenters);

        getContentPane().add(textPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setVisible (true);
    }

    /**************************************************************
     Respond to either button clicks
     @param e the action event that was just fired
     **************************************************************/
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        // if OK clicked the fill the object
        if (button == okButton) {
            // save the information in the object
            closeStatus = OK;
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

            Date d1 = null;
            Date d2 = null;
            boolean valid = false;
            int[] val;
            try {
                String[] splited = txtDateCheckout.getText().split("/");
                val = new int[splited.length];
                for (int i = 0; i < splited.length; i++) {
                    val[i] = Integer.parseInt(splited[i]);
                }
                valid= GUICampReservationSystem.isDateValid(val[0],val[1],val[2]);
                if(!valid){
                    JOptionPane.showMessageDialog(null, "Date entered was invalid. Ensur" +
                            "e you are using a possible combination of months, days, and years. Try again");
                }
            }
            catch(Exception x){
                JOptionPane.showMessageDialog(null, "Date entered was invalid. Ensur" +
                        "e format matched: MM/DD/YYYY");
                closeStatus = 1;
                dispose();
            }
            try {
                GregorianCalendar gregTemp = new GregorianCalendar();
                d1 = df.parse(txtDateCheckin.getText());
                gregTemp.setTime(d1);
                tentOnly.setCheckIn(gregTemp);

                gregTemp = new GregorianCalendar();
                d2 = df.parse(txtDateCheckout.getText());
                gregTemp.setTime(d2);
                if(valid) {
                    tentOnly.setEstimatedCheckOut(gregTemp);
                }

            } catch (ParseException e1) {
                  JOptionPane.showMessageDialog(null,"Errors in formatting date. Try again");
                  closeStatus = 1;
                  dispose();
            }

            tentOnly.setGuestName(txtGuestName.getText());
            try {
                tentOnly.setNumberOfTenters(Integer.parseInt(txtNumberOfTenters.getText()));
                if(tentOnly.getNumberOfTenters() < 0){
                    JOptionPane.showMessageDialog(null,"Negative number of tenters Try again");
                    closeStatus = 1;
                    dispose();
                }
            }
            catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null,"Errors in formatting number of tenters. Try again");
                closeStatus = 1;
                dispose();
            }
        }


        // make the dialog disappear
        dispose();
    }

    /**************************************************************
     Return a String to let the caller know which button
     was clicked

     @return an int representing the option OK or CANCEL
     **************************************************************/
    public int getCloseStatus(){
        return closeStatus;
    }
}

