package ChangeJarPro;

import java.io.*;
import java.util.*;

/**
 * The purpose this class is to simulate a change Jar.
 * @author Seth Ockerman
 */

public class ChangeJar {
    /**
     * The number of quarters in the current Jar
     */
    private int quarters;

    /**
     * The number of dimes in the current Jar
     */
    private int dimes;

    /**
     * The number of nickels in the current Jar
     */
    private int nickels;

    /**
     * The number of pennies in the current Jar
     */
    private int pennies;

    /**
     * variable that allows the state of a changeJar to be changed if true and not if false
     */
    private static boolean change = true;

    /**
     *  The is the default constructor for ChangeJar
    */
    public ChangeJar() {
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
    }


    /**
     *   This constructor creates a Change Jar from an existing
     *    Change Jar.
     * @param other is an existing Change Jar
     */
    public ChangeJar(ChangeJar other) {
        quarters = other.quarters;
        dimes = other.dimes;
        nickels = other.nickels;
        pennies = other.pennies;
    }

    /**
     *   This constructor creates a Change Jar from with some
     *   initial values for Quarters, Dimes, Nickels, and Pennies.
     * @param quarters is the number of quarters to start with.
     * @param dimes is the number of dimes to start with.
     * @param nickels is the number of nickels to start with.
     * @param pennies is the number of pennies to start with.
     * @throws IllegalArgumentException if numbers are negative
     */
    public ChangeJar(int quarters, int dimes, int nickels, int pennies) {
        super();

        if ((quarters < 0) || (dimes < 0) || (nickels < 0) || (pennies < 0))
            throw new IllegalArgumentException("Ensure your numbers are positive");

        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    /**
     * Constructor that accepts strings
     * @param amount The amount of money
     */
    public ChangeJar(String amount) {
        changed(amount);
    }

    /**
     *   This constructor creates a Change Jar from an amount entered as a double
     * @param sum the amount of money entered as a double
     */
    public ChangeJar(double sum) {
        String amount = Double.toString(sum);
        changed(amount);
    }

    /**
     * method that converts all the change to pennies
     * @param temp the change jar that you want passed in
     * @return the amount of money in the jar as pennies
     */
    private static int convertToPennies(ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    // changed string amount to correct designation
    // @param the amount as a string
    private void changed(String amount) throws IllegalArgumentException {
        try {
            String temp = amount;
            String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
            for (int i = 0; i < 26; ++i) {
                amount = amount.toLowerCase();
                if (amount.contains(abc[i])) {
                    throw new IllegalArgumentException("Character was entered");
                }
            }

            if (amount.contains(".") == false) {
                throw new IllegalArgumentException("Remember to include the decimal");
            }
            if ((amount.substring(amount.indexOf("."), amount.length() - 1)).length() > 2) {
                throw new IllegalArgumentException("Too many numbers after the decimal");
            }
            if (amount.contains("-")) {
                throw new IllegalArgumentException(("Negative signs are not permitted"));
            }

            if ((amount.substring(amount.indexOf("."), amount.length() - 1)).length() == 0) {
                throw new IllegalArgumentException("Remember to include 0's after the decimal");
            }
            if ((amount.substring(amount.indexOf("."), amount.length() - 1)).length() != 2) {
                amount = amount + "0";
            }
            amount = amount.replace(".", "");


            int intAmount = Integer.parseInt(amount);
            this.quarters = 0;
            this.dimes = 0;
            this.nickels = 0;
            this.pennies = 0;
            if (((intAmount % 25) != 0) && ((intAmount % 100) != 0)) {
                this.quarters = intAmount / 25;
                intAmount = intAmount - (quarters * 25);
                if (intAmount != 0) {
                    this.dimes = intAmount / 10;
                    intAmount = intAmount - (dimes * 10);
                    if (intAmount != 0) {
                        this.nickels = intAmount / 5;
                        intAmount = intAmount - (5 * nickels);
                        if (intAmount != 0) {
                            this.pennies = intAmount / 1;
                            intAmount = intAmount - (1 * pennies);
                            if (intAmount != 0) {
                                System.out.println("We got a problem");
                            }
                        }
                    }
                }

            } else {
                quarters = intAmount / 25;
                intAmount = intAmount - (quarters * 25);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Remember to only include digits.");
        }
    }

    /**
     * Method that will either allow the amount in the change jar to be mutated or not mutated based
     * on if the boolean is true of false
     * @param selected the boolean stating if it can be mutated
     */
    public static void mutate(boolean selected) {
        if(selected){
            change = true;
        }
        if(!selected){
            change = false;
        }
    }

    /**
     * Method that returns true if this change jar is
     * equal to the other change jar
     * @param s the change jar to be compared to
     * @return a boolean that returns true if the jars match but false otherwise
     */
    public boolean equals(Object s) {
        boolean match = false;
        ChangeJar convert = (ChangeJar)s;
        if (this.getQuarters() == convert.getQuarters()) {
            if (this.getDimes() == convert.getDimes()) {
                if (this.getNickels() == convert.getNickels()) {
                    if (this.getPennies() == convert.getPennies()) {
                        match = true;
                    }
                }
            }
        }
        return match;

    }

    /**
     * Method that returns true if each change jar has the exact same
     * number of coins
     * @param s  the first changeJar
     * @param s1 the second changeJar
     * @return Boolean representation. True means they are identical and false that they are not
     */
    public static boolean equals(ChangeJar s, ChangeJar s1) {
        boolean match = false;
        if (s1.getQuarters() == s.getQuarters()) {
            if (s1.getDimes() == s.getDimes()) {
                if (s1.getNickels() == s.getNickels()) {
                    if (s1.getPennies() == s.getPennies()) {
                        match = true;
                    }
                }
            }
        }
        return match;
    }

    /**
     * Method that compares the value of the change in the  changeJar it is called on to the
     * change jar that is passed is
     * @param s the changeJar to be compared to
     * @return an int representation. 1 means the change jar the method is called on is greater
     * -1 means that the change jar the method is called on is smaller than
     * the passed in changeJar. 0 means the two change
     * jars are equal
     */
    public int compareTo(ChangeJar s) {
        int thisSum = (25 * this.getQuarters()) + (10 * this.getDimes()) + (5 * this.getNickels()) + (1 * this.getPennies());
        int sum1 = (25 * s.getQuarters()) + (10 * s.getDimes()) + (5 * s.getNickels()) + (1 * s.getPennies());

        if (thisSum > sum1) {
            return 1;
        } else if (thisSum < sum1) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Method that determines if the amount of change in changeJar1
     * is equal to the amount of change in changeJar2
     * @param jar1 the first jar
     * @param jar2 the second jar
     * @return an int representation. 1 means the change jar the method is called on is greater
     * -1 means that the change jar the method is called on is smaller than
     * the passed in changeJar. 0 means the two change
     * jars are equal
     */
    public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
        int jar1Sum = (25 * jar1.getQuarters()) + (10 * jar1.getDimes()) + (5 * jar1.getNickels()) + (1 * jar1.getPennies());
        int jar2Sum = (25 * jar2.getQuarters()) + (10 * jar2.getDimes()) + (5 * jar2.getNickels()) + (1 * jar2.getPennies());

        if (jar1Sum > jar2Sum) {
            return 1;
        } else if (jar1Sum < jar2Sum) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * A method that subtracts the parameters from the “this” ChangeJar object
     * @param quarters amount of quarters to subtracted
     * @param dimes    amount of dimes to be subtracted
     * @param nickels  amount of nickels to be subtracted
     * @param pennies  amount of pennies to be subtracted
     * @throws IllegalArgumentException if subtraction will result in a negative amount of coins
     */
    public void subtract(int quarters, int dimes, int nickels, int pennies) {
        if (change) {
            if (valid(this, quarters, dimes, nickels, pennies) && change) {
                this.quarters -= quarters;
                this.dimes -= dimes;
                this.nickels -= nickels;
                this.pennies -= pennies;
            } else {
                throw new IllegalArgumentException("Negative result from subtraction or mutate is set to false");
            }
        }

    }

    /**
     * A method that subtracts ChangeJar other from
     * the “this” ChangeJar object
     * @param other the object that will be used to subtract
     * @throws IllegalArgumentException if subtraction will result in a negative amount of coins
     */
    public void subtract(ChangeJar other) {
        if(change){
        if(valid(this,other.quarters,other.dimes,other.nickels,other.pennies)){
            this.quarters -= other.quarters;
            this.dimes -= other.dimes;
            this.nickels -= other.nickels;
            this.pennies -= other.pennies;
        }
        else{
            throw new IllegalArgumentException("Negative result from subtraction");
        }
    }
    }

    /**
     * Decreases the pennies of a change jar by one
     * @throws IllegalArgumentException if there are not enough pennies to subtract one
     */
    public void dec() {
        if (change) {
            if (this.pennies - 1 >= 0) {
                --this.pennies;
            } else {
                throw new IllegalArgumentException("Not enough pennies");
            }
        }
    }

    /**
     * Increases the pennies of a change jar by one
     */
    public void add() {
        if(change) {
            ++this.pennies;
        }
    }

    /**
     * A method that adds the parameters from the “this” ChangeJar object
     * @param quarters the amount of quarters to be added
     * @param dimes    the amount of dimes to be added
     * @param nickels  the amount of nickels to be added
     * @param pennies  the amount of pennies to be added
     * @throws IllegalArgumentException if they try to add a negative number
     */
    public void add(int quarters, int dimes, int nickels, int pennies) {
        if(change) {
            if (quarters >= 0 && dimes >= 0 && nickels >= 0 && pennies >= 0) {
                this.quarters += quarters;
                this.dimes += dimes;
                this.nickels += nickels;
                this.pennies += pennies;
            } else {
                throw new IllegalArgumentException("Tried to add a negative number");
            }
        }
    }

    /**
     * A method that add ChangeJar other to the “this”
     * ChangeJar object
     * @param other the change jar to be added
     */
    public void add(ChangeJar other) {
        if(change) {
            this.quarters += other.getQuarters();
            this.dimes += other.getDimes();
            this.nickels += other.getNickels();
            this.pennies += other.getPennies();

        }
    }

    /**
     * Method that takes out a passed in amount of each coin
     * @param quarters number of quarters to be taken out
     * @param dimes number of dimes to be taken out
     * @param nickels number of nickels to be taken out
     * @param pennies number of pennies to be taken out
     * @throws IllegalArgumentException if subtraction will result in a negative amount of coins
     */
    public void takeOut(int quarters, int dimes, int nickels, int pennies) {
        if(change){
        if(valid(this,quarters,dimes,nickels,pennies )){
            this.quarters -= quarters;
            this.dimes -= dimes;
            this.nickels -= nickels;
            this.pennies -= pennies;
        }
        else{
            throw new IllegalArgumentException("Negative result from subtraction");
        }

    }
    }

    /**
     * Method that takes the change from one change jar and subtracts it from another change jar
     * @param other the object that will be used to subtract from this.ChangeJar
     * @throws IllegalArgumentException if subtraction will result in a negative amount of coins
     */
    public void takeOut(ChangeJar other) {
        if(change) {
            if (valid(this, other.getQuarters(), other.getDimes(), other.getNickels(), other.getPennies())) {
                this.quarters -= other.quarters;
                this.dimes -= other.dimes;
                this.nickels -= other.nickels;
                this.pennies -= other.pennies;
            } else {
                throw new IllegalArgumentException("Negative result from subtraction");
            }
        }
    }

    /**
     * Method that subtracts a flat amount from a changeJar
     * @param amount the double amount to be subtracted
     * @return if possible, it returns the subtracted changeJar
     * @throws IllegalArgumentException if subtraction will result in a negative amount of coins
     */
    public ChangeJar takeOut(double amount) {
        if(change) {
            ChangeJar temp = new ChangeJar(amount);
            int[] combo = combo(amount, temp);
            if (combo != null) {
                this.subtract(temp);
                return this;
            } else {
                return null;
            }
        }
        else{
            return null;
        }
    }

    /**
     *  adds to change jar based on passed in ints for each coin
     * @param quarters int amount of quarters to be added
     * @param dimes int amount of dimes to be added
     * @param nickels int amount of nickels to be added
     * @param pennies int amount of pennies to be added
     */
    public void putIn(int quarters, int dimes, int nickels, int pennies){
        if(change) {
            ChangeJar temp = new ChangeJar(quarters, dimes, nickels, pennies);
            this.add(temp);
        }
    }

    /**
     * mathod that adds the change from one change jar to this.ChangeJar
     * @param other the object to be added
     */
    public void putIn(ChangeJar other){
        if(change) {
            this.add(other);
        }
    }

    /**
     * method that puts in an amount of change to a changeJar
     * @param amount the double to be added
     */
    public void putIn(double amount){
        if (change) {
            ChangeJar temp = new ChangeJar(amount);
            this.add(temp);
        }
    }


    /**
     * Method that returns a string representation of a changeJar object
     * @return the string representation of a changeJar object
     */
    public String toString() {

        // here is a hint
        String s = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + ", ";
        s = s + this.dimes + " Dime" + ((dimes != 1) ? "s" : "") + ", ";
        s = s + this.nickels + " Nickel" + ((nickels != 1) ? "s" : "") + ", ";
        s = s + this.pennies + " Penn" + ((pennies != 1) ? "ies" : "y") + ". ";
        return s;
    }

    // determines if method is valid
    private boolean valid(ChangeJar s, int quarters, int dimes, int nickels, int pennies){
        boolean valid = false;
        if(((s.pennies - pennies) >= 0) && ((s.dimes - dimes) >= 0) &&
                ((s.nickels - nickels) >= 0) && ((s.quarters - quarters) >= 0) &&
                (quarters >= 0) && (dimes >= 0) && nickels >= 0 && pennies >= 0){
            valid = true;
        }

        return valid;
    }

    /**
     * Function that saves an ChangeJar as a file
     * @param fileName the name you want for the file
     */
    public void save(String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                    fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println((this.quarters));
        out.println(this.dimes);
        out.println(this.nickels);
        out.println(this.pennies);
        out.close();
    }

    /**
     * loads a changeJar file
     * @param fileName the name of the file you want to open
     */
    public void load(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            this.quarters = scanner.nextInt();
            this.dimes = scanner.nextInt();
            this.nickels = scanner.nextInt();
            this.pennies = scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // problem reading the file
        catch(IOException error){
            System.out.println("Oops! Something went wrong.");
        }
    }

    /**
     * gets total monetary value of change in ChangeJar
     * @return the value as a double
     */
    public double getAmount() {
        return convertToPennies(this) / 100.0;
    }

    /**
     * gets number of quarters from ChangeJar
     * @return number of quarters as an int
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * sets number of quarters of a changeJar
     * @param quarters passed in int that sets number of quarters
     */
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /**
     * gets number of dimes from ChangeJar
     * @return number of dimes as an int
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * sets number of dimes of a changeJar
     * @param dimes passed in int that sets number of dimes
     */
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /**
     * gets number of nickels from a ChangeJar
     * @return number of nickels as an int
     */
    public int getNickels() {
        return nickels;
    }

    /**
     * sets number of nickels of a changeJar
     * @param nickels passed in int that sets number of nickels
     */
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    /**
     * gets number of pennies from a ChangeJar
     * @return number of pennies as an int
     */
    public int getPennies() {
        return pennies;
    }

    /**
     * sets number of pennies of a changeJar
     * @param pennies passed in int that sets number of pennies
     */
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    // testing to see if an amount can be created given a change Jars  coins
    private int[] combo(double amount, ChangeJar jar) {
        int[] combo = {0, 0, 0, 0};

        for (int i = 0; i <= jar.getQuarters(); ++i) {
            if ((i * 0.25) == amount) {
                combo[0] = i;
                return combo;
            } else {
                for (int j = 0; j <= jar.getDimes(); ++j) {
                    if (((i * 0.25) + (j * 0.10)) == amount) {
                        combo[0] = i;
                        combo[1] = j;
                        return combo;
                    } else {
                        for (int l = 0; l <= jar.getNickels(); ++l) {
                            if (((i * 0.25) + (j * 0.10) + (l * 0.05)) == amount) {
                                combo[0] = i;
                                combo[1] = j;
                                combo[2] = l;
                                return combo;
                            } else {
                                for (int m = 0; m <= jar.getPennies(); ++m) {
                                    if ((((i * 0.25) + (j * 0.10) + (l * 0.05)) + (m * 0.01)) == amount) {
                                        combo[0] = i;
                                        combo[1] = j;
                                        combo[2] = l;
                                        combo[3] = m;
                                        return combo;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(combo[0] == 0 && combo[1] == 0 && combo[2] == 0 && combo[3] == 0){
            return null;
        }
        return combo;
    }
    public static void main(String args[]) {
        // testing string constructors with a few different types of values
        ChangeJar s = new ChangeJar("2.82");
        System.out.println("Amount 2.82: " + s);
        s = new ChangeJar(".28");
        System.out.println("Amount 0.28: " + s);

        // testing default constructor
        ChangeJar s1 = new ChangeJar();
        System.out.println("Amount 0: " + s1);

        // testing double constructor
        ChangeJar s2 = new ChangeJar(41.99);
        System.out.println("Amount 41.99: " + s2);

        //testing constructor with ints passed in for each coin
        ChangeJar intTest = new ChangeJar(1,1,1,1);
        System.out.println("Amount 0.41: " + intTest);

        // testing equals
        ChangeJar match1 = new ChangeJar(1.00);
        ChangeJar match2 = new ChangeJar(1.00);
        ChangeJar differentMore = new ChangeJar(150.00);
        ChangeJar differentLess = new ChangeJar(0.50);
        if(match1.equals(match2) && equals(match1,match2)){
            System.out.println("equals function works when they match");
        }
        if((equals(match1,differentMore) == false) && (match1.equals(differentMore) == false)){
            System.out.println("equals function works when they don't match");
        }
        // testing compare two function
        if((match1.compareTo(match2) == 0) && (compareTo(match1, match2) == 0)){
            System.out.println("Compare to works when they are equal");
        }
        if((match1.compareTo(differentLess) == 1) && (compareTo(match1, differentLess) == 1)){
            System.out.println("Compare to works when less");
        }
        if((match1.compareTo(differentMore) == -1) && (compareTo(match1, differentMore) == -1)){
            System.out.println("Compare to works when more");
        }
        // test convert to Pennies
        convertToPennies(match1);
        if(match1.getPennies() == 100){
            System.out.println("Convert to pennies method is working");
        }
        // test subtract
        differentMore.subtract(596,0,0,0);
        if(compareTo(differentMore,match1) == 0){
            System.out.println("Subtract works");
        }
        differentMore.subtract(differentLess);
        if(compareTo(differentMore,differentLess) == 0){
            System.out.println("Subtract with objects works");
        }
        // test add
        differentLess.add(2,0,0,0);
        if(compareTo(differentLess,match1)== 0){
            System.out.println("Add works");
        }
        ChangeJar halfDollar = new ChangeJar(0.50);
        ChangeJar halfDollar2 = new ChangeJar(0.50);
        halfDollar.add(halfDollar2);
        if(halfDollar.getQuarters() == 4){
            System.out.println("Add with objects works");
        }
        for(int i = 0; i < 100; ++i){
            halfDollar.add();
        }
        if(halfDollar.getPennies() == 100){
            System.out.println("Simple add works");
        }
        for(int i = 0; i < 100; ++i){
            halfDollar.dec();
        }
        if(halfDollar.getPennies() == 0){
            System.out.println("Simple subtract works ");
        }

        // testing takeout
        halfDollar.takeOut(0.50);
        if(halfDollar.getQuarters() == 2){
            System.out.println("Take out with double works");
        }
        ChangeJar subtract50 = new ChangeJar(0.50);
        halfDollar.takeOut(subtract50);

        if(halfDollar.getQuarters() == 0){
            System.out.println("Take out with an object works");
        }
        subtract50.takeOut(.25);
        if(subtract50.getQuarters() == 1){
            System.out.println("Take out with double  works");

        }
        subtract50.takeOut(1,0,0,0);
        if(subtract50.getQuarters() == 0){
            System.out.println("Take out with ints works");

        }

        //testing private combo method
        ChangeJar test = new ChangeJar(6,2,4,1);
        int[] combo = test.combo(1.31,test);
        System.out.println("Quarters: " + combo[0]);
        System.out.println("Dimes: " + combo[1]);
        System.out.println("Nickels: " + combo[2]);
        System.out.println("Pennies: " + combo[3]);

        test.save("Test");

        ChangeJar blank = new ChangeJar();
        blank.load("Test");
        System.out.println(blank);


    }

}