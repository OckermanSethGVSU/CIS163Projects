package  ChangeJarPro; ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * GUI User Interface
 *
 * @author Seth Ockerman
 */
public class ChangeJarPanel extends JPanel{

    /**
     * Jar that will be interacted with using the GUI
     */
    private ChangeJar jar;

    /**
     * Simple Jar for compareTo and equals functions
     */
    private ChangeJar fives = new ChangeJar(5,5,5,5);

    // formats credits
    NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /**
     * All buttons
     */
    JButton takeOutButton,putInButton,incButton,decButton,mutateButton,depositButton,withdrawButton,compareToButton,equalsButton;
    JButton addChangeJarButton,subtractChangeJarButton,loadButton,saveButton,createChangeJarIntsButton;
    JButton createChangeJarDoubleButton, createChangeJarDefaultButton,createChangeJarStringButton;
    JTextField qField, dField, nField, pField,amountField,compareToField,equalsField,saveField,loadField,createChangeJarStringField;
    JCheckBox mutateBox;

    /** labels for message, credits, compareTo Jar, and each coin counter*/
    JLabel message, credits;
    JLabel quarterCounter,dimeCounter,nickelCounter,pennyCounter;
    JLabel compareToJar;

    // method that updates the GUI
    private void updateMethod(){
        quarterCounter.setText("Quarters: " + jar.getQuarters());
        dimeCounter.setText("Dimes: " + jar.getDimes());
        nickelCounter.setText("Nickels: " + jar.getNickels());
        pennyCounter.setText("Pennies: " + jar.getPennies());
    }

    public ChangeJarPanel(){

        // create the game object as well as the ChangeJarGUI Frame
        jar = new ChangeJar(10,10,10,10);

        // on default, mutable is set to false
        jar.mutate(false);

        // set the layout to GridBag
        setLayout(new GridLayout(25,2));
        setBackground(Color.lightGray);

       // add mutate button and textfield
        mutateButton = new JButton("Mutate");
        mutateBox = new JCheckBox("On");
        add(mutateBox);
        add(mutateButton);






        // textfields for coins and place on ChangeJarGUI
        add(new JLabel("Quarters:"));
        qField = new JTextField("0", 3);
        add(qField);

        add(new JLabel("Dimes:"));
        dField = new JTextField("0", 3);
        add(dField);


        add(new JLabel("Nickels:"));
        nField = new JTextField("0", 3);
        add(nField);

        add(new JLabel("Pennies:"));
        pField = new JTextField("0", 3);
        add(pField);

        // buttons to put in and take out
        putInButton = new JButton("Put in");
        add(putInButton);
        takeOutButton = new JButton("Take Out");
        add(takeOutButton);

        // buttons to create ChangeJar based on passed in coins
        createChangeJarIntsButton= new JButton("Create ChangeJar based on passed in coins");
        add(createChangeJarIntsButton);
        add(new JLabel());

        // add counter for total cash
        credits = new JLabel();
        credits.setText(fmt.format(jar.getAmount()));
        add(new JLabel("Total:"));
        add(credits);

        // add button for +1 and -1
        incButton = new JButton("Plus 0.01");
        add(incButton);

        decButton = new JButton("Minus 0.01");
        add(decButton);

        // setup for button double deposit and withdraw plus create changeJar off double
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        createChangeJarDoubleButton = new JButton("Create changeJar based on double");
        amountField = new JTextField("0.00");
        add(new JLabel("Enter amount: "));
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(createChangeJarDoubleButton);
        add(new JLabel());

        // adding counter for each coin
        quarterCounter = new JLabel();
        quarterCounter.setText("Quarters: " + jar.getQuarters());
        add(quarterCounter);

        dimeCounter = new JLabel();
        dimeCounter.setText("Dimes: " + jar.getDimes());
        add(dimeCounter);

        nickelCounter = new JLabel();
        nickelCounter.setText("Nickels: "+ jar.getNickels());
        add(nickelCounter);

        pennyCounter = new JLabel();
        pennyCounter.setText("Pennies: " + jar.getPennies());
        add(pennyCounter);

        // adding compare to button and textField
        compareToButton = new JButton("CompareTo");
        add(compareToButton);
        compareToField = new JTextField();
        add(compareToField);

        // adding equals button and textField
        equalsButton = new JButton("Equals");
        add(equalsButton);
        equalsField = new JTextField();
        add(equalsField);

        // creating add and subtract ChangeJar buttons
        addChangeJarButton = new JButton("Add ChangeJar");
        add(addChangeJarButton);

        subtractChangeJarButton = new JButton("Subtract ChangeJar");
        add(subtractChangeJarButton);

        // adding save and load buttons and textFields
        saveButton = new JButton("Save");
        add(saveButton);
        saveField = new JTextField();
        add(saveField);

        loadButton = new JButton("Load");
        add(loadButton);
        loadField = new JTextField();
        add(loadField);

        // adding basic JLabel
        add( new JLabel("String and Default ChangeJar Creation Below"));
        add(new JLabel());

        // adding default constructor button
        createChangeJarDefaultButton = new JButton("Create Default ChangeJar");
        add(createChangeJarDefaultButton);
        add(new JLabel());

        // adding string constructor button and textfield
        createChangeJarStringButton = new JButton("Create ChangeJar");
        add(createChangeJarStringButton);
        createChangeJarStringField = new JTextField();
        add(createChangeJarStringField);



        // adding jLabel of the jar that will be compared to
        compareToJar = new JLabel("Second ChangeJar values: " + fives.toString());
        add(compareToJar);

        // register the listeners
        takeOutButton.addActionListener(new ButtonListener());
        putInButton.addActionListener(new ButtonListener());
        incButton.addActionListener(new ButtonListener());
        decButton.addActionListener(new ButtonListener());
        mutateButton.addActionListener(new ButtonListener());
        depositButton.addActionListener(new ButtonListener());
        compareToButton.addActionListener(new ButtonListener());
        equalsButton.addActionListener(new ButtonListener());
        compareToButton.addActionListener(new ButtonListener());
        addChangeJarButton.addActionListener(new ButtonListener());
        subtractChangeJarButton.addActionListener(new ButtonListener());
        withdrawButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        createChangeJarIntsButton.addActionListener(new ButtonListener());
        createChangeJarDoubleButton.addActionListener(new ButtonListener());
        createChangeJarStringButton.addActionListener(new ButtonListener());
        createChangeJarDefaultButton.addActionListener(new ButtonListener());
}


    /****************************************************************
     Inner class to repond to the user action

     ****************************************************************/
    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent event){

            int quarters, dimes, nickels, pennies;

            if (event.getSource() == takeOutButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.takeOut(quarters,dimes,nickels,pennies);


                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if (event.getSource() == putInButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.add(quarters,dimes,nickels,pennies);


                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if (event.getSource() == createChangeJarIntsButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar = new ChangeJar(quarters,dimes,nickels,pennies);
                    updateMethod();
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            if(event.getSource() == incButton){
                try{
                    jar.add();
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
            if(event.getSource() == decButton){
                try{
                    jar.dec();
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error: Tried to Subtract Below Zero");
                }
            }
            if(event.getSource() == depositButton) {
                try{
                    double amt = Double.parseDouble(amountField.getText());
                    jar.putIn(amt);
                    updateMethod();


                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Amount entered incorrectly. Make sure it is in the format" +
                            " 0.00");
                }
            }
            if(event.getSource() == withdrawButton) {
                try{
                    double amt = Double.parseDouble(amountField.getText());
                    jar.takeOut(amt);
                    updateMethod();


                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Amount entered incorrectly or coins cannot make the amount. Make sure it is in the format" +
                            " 0.00");
                }
            }
            if(event.getSource() == createChangeJarDoubleButton) {
                try{
                    double amt = Double.parseDouble(amountField.getText());
                    jar = new ChangeJar(amt);
                    updateMethod();


                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Amount entered incorrectly. Make sure it is in the format" +
                            " 0.00");
                }
            }
            if(event.getSource() == mutateButton){
                try{
                    if(mutateBox.isSelected()){
                        jar.mutate(true);
                    }
                    if(!mutateBox.isSelected()){
                        jar.mutate(false);
                    }
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }

            if(event.getSource() == compareToButton){
                try{
                    compareToField.setText((Integer.toString(jar.compareTo(fives))));
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }

            if(event.getSource() == equalsButton){
                try{
                    equalsField.setText(Boolean.toString(jar.equals(fives)));
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }

            if(event.getSource() == addChangeJarButton){
                try{
                    jar.add(fives);
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
            if(event.getSource() == subtractChangeJarButton){
                try{
                    jar.subtract(fives);
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Negative result from subtraction");
                }
            }

            if(event.getSource() == saveButton){
                try{
                    jar.save(saveField.getText());
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
            if(event.getSource() == loadButton){
                try{
                    jar.load(loadField.getText());
                    updateMethod();
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }
            if(event.getSource() == createChangeJarDefaultButton){
                try{
                    jar = new ChangeJar();
                    updateMethod();
                }
                catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Error");
                }
            }

            if(event.getSource() == createChangeJarStringButton) {
                try{
                    jar = new ChangeJar(createChangeJarStringField.getText());
                    updateMethod();


                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Amount entered incorrectly. Make sure it is in the format" +
                            " 0.00");
                }
            }
            // update the labels
            credits.setText(fmt.format(jar.getAmount()));
            updateMethod();
        }

    }
}
