package Project3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class that controls dialog that pops up when user checks out
 * @author Seth Ockerman
 * @author Max Foreback
 */
public class CheckOutOnDialog extends JDialog implements ActionListener {
	// creating textField for date
	private JTextField txtDate;

	// creating ok button
	private JButton okButton;

	//creating cancel button
	private JButton cancelButton;

	// int for close status. one equals cancel and zero equals ok
	private int closeStatus;

	// creating a campsite
	private CampSite campSite;
	/**
	 * final variable. OK = 0
	 */
	static final int OK = 0;
	/**
	 * final variable. Cancel = 1;
	 */
	static final int CANCEL = 1;

	/*********************************************************
	 Instantiate a Custom Dialog as 'modal' and wait for the
	 user to provide data and click on a button.

	 @param parent reference to the JFrame application
	 @param campSite an instantiated object to be filled with data
	 *********************************************************/
	public CheckOutOnDialog(JFrame parent, CampSite campSite) {
		// call parent and create a 'modal' dialog
		super(parent, true);

		this.campSite = campSite;
		setTitle("Check Out dialog box");
		closeStatus = CANCEL;
		setSize(300,100);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        txtDate = new JTextField(dateFormat.format(campSite.
				getEstimatedCheckOut().getTime()),30);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1,2));
		textPanel.add(new JLabel("Check out Date: "));
		textPanel.add(txtDate);

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
			GregorianCalendar gTemp = new GregorianCalendar();


			Date d = null;
			boolean valid = false;
			try {
				d = df.parse(txtDate.getText());
				gTemp.setTime(d);
				int[] val;
				try {
					String[] splited = txtDate.getText().split("/");
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
								"e format matched");
						closeStatus = 1;
						dispose();
				}
				if(valid) {

						if (CampSite.daysBetween(campSite.getCheckIn(), gTemp) > 0) {
							campSite.setActualCheckOut(gTemp);
						} else {
							JOptionPane.showMessageDialog(null, "Date entered for checkout was before checkIn. Try again");
							closeStatus = 1;
							dispose();
						}
					}



			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null,"Errors in formatting date. Try again");
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

