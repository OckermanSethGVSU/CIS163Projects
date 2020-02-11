package surroundpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static surroundpack.Surround4Game.*;

/**
 * User interface
 * @author Max Foreback
 * @author Seth Ockerman
 */
public class Surround4Panel extends JPanel {

    // creating GUI Components for S4Game
    private JButton[][] board;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private int player;
    private ButtonListener listen;
    private JMenuItem quitItem;
    private Surround4Game game;
    private JMenuItem newGameItem;
    private JMenuItem undo;
    private JLabel sec;
    private JLabel rank1;

    //creating array to hold previous actions
    private ArrayList<Integer> pastActions = new ArrayList<Integer>();

    // creating variables for JOption panes for starting board size, numPlayers, and the starting player
    private int bSize;
    private int numPlayers;
    private int startingPlayer;
    private int seconds = 30;

    // Timer creation
    Timer countDown = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(seconds > 0 ) {
                seconds = seconds - 1;
                sec.setText("Time remaining in turn: " + String.valueOf(seconds));

            }
            else{
                JOptionPane.showMessageDialog(null,"Ran out of time for turn, next player");
                game.nextPlayer();
                seconds = 30;
            }
        }
    });

    /**
     * Menu user interface
     * @param pQuitItem is the JMenuItem to quit the game
     * @param newGameItem is the JMenuButton to start a new game
     */
    public Surround4Panel(JMenuItem pQuitItem, JMenuItem newGameItem, JMenuItem undo) {

        //Joption for num of cells

        // Asks user for board size
        String strBdSize = JOptionPane.showInputDialog(null, "Enter size of board: greater than 3 " +
                "less than 20");
        // if the user hits quit, value will be null. So quit game
        if(strBdSize == null){
            System.exit(1);
        }
        /*attempt to parse entered string into int
        * if it fails it will generate a default board
         */

        try {
            int testSize = Integer.parseInt(strBdSize);
            if(testSize > 3 && testSize < 20){
                bSize = testSize;
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong or no dimensions entered. Creating default 10x10 board");
                bSize = 10;
            }

        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"Wrong or no dimensions entered. Creating default 10x10 board");
            bSize = 10;
        }

        // Joption for num of players

        // asks user for number of players
        String strNumP = JOptionPane.showInputDialog(null, "Enter number of players: greater than 1 " +
                "less than 20");
        // if user hits cancel value will be null and program will exit
        if(strNumP == null){
            System.exit(1);
        }
        // tries to parse user input into a int. If it can't it will generate a game with a defualt two players
        try {
            int testSize = Integer.parseInt(strNumP);
            if(testSize > 1 && testSize < 20){
                numPlayers = testSize;
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong or no number of players entered: Creating default of 2");
                numPlayers = 2;
            }

        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"Wrong or no number of players entered: Creating default of 2");
            numPlayers = 2;
        }

        //Joption for starting player

        // asks user which player they want to start
        String strStartP = JOptionPane.showInputDialog(null, "Which player do you want to start? Remember that player numbers" +
                " are zero indexed.");
        // if user hits cancel value is null and program exits
        if(strStartP == null){
            System.exit(1);
        }
        // tries to parse string into int. If invalid, player one starts
        try {
            int testSize = Integer.parseInt(strStartP);
            if(testSize >= 0 && testSize < numPlayers){
                startingPlayer = testSize;
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid or no player entered: Player one will start");
                startingPlayer = 1;
            }

        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"Invalid or  no player entered: Player one will start");
            startingPlayer = 1;
        }

        // instantiating GUI components
        quitItem = pQuitItem;
        this.newGameItem = newGameItem;
        this.undo = undo;
        listen = new ButtonListener();

        // setting layout of panel 1
        setLayout(new BorderLayout());
        panel1 = new JPanel();
        createBoard(bSize);
        add(panel1, BorderLayout.CENTER);
        // registering the game with the JPanel with passed in values for board size
        game = new Surround4Game(bSize,startingPlayer);
		// adding action listeners
        quitItem.addActionListener(listen);
		newGameItem.addActionListener(listen);
		undo.addActionListener(listen);

		// creating panel 2  which hold the timer and adding it
        panel2 = new JPanel();
        sec = new JLabel("Time Remaining for turn: " + String.valueOf(seconds));
        panel2.add(sec);
        add(panel2,BorderLayout.BEFORE_FIRST_LINE);
        // creating panel 3 which hold the win counter and adding it
        panel3 = new JPanel(new BorderLayout());
        rank1 = new JLabel("First place is player " + getFirstPlace() + " with " + getFirstPlaceScore() + " points" + "--" +
                "Second place is player " + getSecondPlace() + " with " + getSecondPlaceScore() + " points" + "--" +
                "Third place is player " + getThirdPlace() + " with " + getThirdPlaceScore() + " points");
        panel3.add(rank1);
        add(panel3, BorderLayout.AFTER_LAST_LINE);
        // ready to play; start the countdown
        countDown.start();

	}

    /*
     * Listener for buttons in GUI
     * @author Max Foreback
     * @author Seth Ockerman
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // if user hits quit, exit
            if (e.getSource() == quitItem) {
                System.exit(1);
            }
            // if user hits undo, undo the most recent action

            if(e.getSource() == undo){
                if (JOptionPane.showConfirmDialog(null, "Undo action? You will not get another turn", "Undo",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    undoAction();
                }
            }
            // if user hits new game, begin new game process
            if (e.getSource() == newGameItem){
                // reset the board to default, clear wins, clears past actions, and stops count down
                pastActions.clear();
                countDown.stop();
                game.reset(bSize,startingPlayer);
                clearWins();
                getWinners();
                // sets win counter text
                rank1.setText("First place is player " + getFirstPlace() + " with " + getFirstPlaceScore() + " points" + "--" +
                        "Second place is player " + getSecondPlace() + " with " + getSecondPlaceScore() + " points" + "--" +
                        "Third place is player " + getThirdPlace() + " with " + getThirdPlaceScore() + " points");


                // get new board size
			    String strBdSize = JOptionPane.showInputDialog(null, "Enter size of board: greater than 3 " +
                        "less than 20");
                if(strBdSize == null){
                    System.exit(1);
                }
                try {
                    int testSize = Integer.parseInt(strBdSize);
                    if(testSize > 3 && testSize < 20){
                        bSize = testSize;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Wrong or no dimensions entered. Creating default 10x10 board");
                        bSize = 10;
                    }

                }
                catch(IllegalArgumentException x){
                    JOptionPane.showMessageDialog(null,"Wrong or no dimensions entered. Creating default 10x10 board");
                    bSize = 10;
                }
                // get new number of players
                String strNumP = JOptionPane.showInputDialog(null, "Enter number of players: greater than 1 " +
                        "less than 20");
                if(strNumP == null){
                    System.exit(1);
                }
                try {
                    int testSize = Integer.parseInt(strNumP);
                    if(testSize > 1 && testSize < 20){
                        numPlayers = testSize;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Wrong or no number of players entered: Creating default of 2");
                        numPlayers = 2;
                    }

                }
                catch(IllegalArgumentException y){
                    JOptionPane.showMessageDialog(null,"Wrong or no number of players entered: Creating default of 2");
                    numPlayers = 2;
                }

                //Get new starting player
                String strStartP = JOptionPane.showInputDialog(null, "Which player do you want to start? Remember that player numbers" +
                        " are zero indexed.");
                if(strStartP == null){
                    System.exit(1);
                }
                try {
                    int testSize = Integer.parseInt(strStartP);
                    if(testSize >= 0 && testSize < numPlayers){
                        startingPlayer = testSize;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid or no player entered: Player one will start");
                        startingPlayer = 1;
                    }

                }
                catch(IllegalArgumentException r){
                    JOptionPane.showMessageDialog(null,"Invalid or  no player entered: Player one will start");
                    startingPlayer = 1;
                }
                //removes all elements from the panel
                panel1.removeAll();
                // creates new board and registers that game to the board
                createBoard(bSize);
                game = new Surround4Game(bSize,startingPlayer);
                // tell the GUI to update
                panel1.revalidate();
                panel1.repaint();
                // reset timer and tell it to start
                seconds = 30;
                countDown.start();
                // show the results and wait for applause
                displayBoard();

            }
            // loop to select the intended cell
			for (int row = 0; row < board.length; row++){
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) {
                            //this next line used to be commented out and i have no clue why
                            		board[row][col].setText(""+game.getCurrentPlayer());
                            player = game.nextPlayer(numPlayers);
                            seconds = 30;
                            addAction(row, col);
                        } else
                            // if already picked or invalid, lets user know
                            JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");
                }
			}

            // checks risk of each cell and updates color accordingly
			game.setRisk(bSize);
            // updates board after risk
			displayBoard();
            //checks if board is full
            boardIsFull();
			// checks if there is a winner
            int winner = game.getWinner(bSize);
           // if there is a winner, give the user the option to play again or quit
            if (winner != -1) {
                // clears past actions and  stops the timer
                pastActions.clear();
                countDown.stop();
                // lets player know they won
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                getWinners();
                // updates player rankings JLabel to reflect new standings
                rank1.setText("First place is player " + getFirstPlace() + " with " + getFirstPlaceScore() + " points" + "--" +
                        "Second place is player " + getSecondPlace() + " with " + getSecondPlaceScore() + " points" + "--" +
                        "Third place is player " + getThirdPlace() + " with " + getThirdPlaceScore() + " points");
                // gives option to play again or quit
                if (JOptionPane.showConfirmDialog(null, "Play Again?", "New Game",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    //creates new game, resets board/timer/game array
                    game = new Surround4Game(bSize,startingPlayer);
                    seconds = 30;
                    countDown.start();
                    game.reset(bSize,startingPlayer);
                    resetBoardColor();
                    // displays results
                    displayBoard();

                }
                // if they click exit, exit
                else {
                    System.exit(1);
                }
            }

            displayBoard();
        }
    }
    /*
     * Creates board of a given size
     * @param s is the number of rows and columns the board will have
     */
    private void createBoard(int s) {
        // creates inner array
        board = new JButton[s][s];
        panel1.setLayout(new GridLayout(s, s));
        // populates the array with cells and assigns each an action listener
        for (int i = 0; i < s; i++) // rows
            for (int j = 0; j < s; j++) {
                board[i][j] = new JButton("");
                board[i][j].addActionListener(listen);
                panel1.add(board[i][j]);
            }
    }

    /*
     * Displays the current board in the GUI
     * Checks risk and updates
     * checks num of player and updates as such
     */
    private void displayBoard() {
        for (int row = 0; row < bSize; row++)
            for (int col = 0; col < bSize; col++) {
                    Cell c = game.getCell(row, col);
                    if (c != null) {
                        board[row][col].setText("" + c.getPlayeNumber());
                        if (c.getRisk() == 2) {
                            board[row][col].setBackground(Color.red);
                        }
                        if (c.getRisk() == 1) {
                            board[row][col].setBackground(Color.blue);
                        }
                    } else
                        board[row][col].setText("");
            }
    }



     // resets the color of the board
    private void resetBoardColor(){
        for (int row = 0; row < bSize; row++) {
            for (int col = 0; col < bSize; col++) {
                board[row][col].setBackground(new JButton().getBackground());
            }
        }

    }

   // resets if board is full
    private void boardIsFull(){
        int count = 0;
        for(int row = 0; row < bSize;row++){
            for(int col = 0; col < bSize; col++){
                if(board[row][col] != null) {
                    if (board[row][col].getText() != "") {
                        ++count;
                    }
                }
            }
        }
        // goes through new game procedure if they want to
        if(count == (bSize * bSize)){
            if (JOptionPane.showConfirmDialog(null, "Board is filled. Try again??", "New Game",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                game = new Surround4Game(bSize,startingPlayer);
                seconds = 30;
                countDown.start();
                game.reset(bSize,startingPlayer);
                resetBoardColor();
                displayBoard();

            } else {
                System.exit(1);
            }
        }
    }
    // adds action to action list
    private void addAction(int r,int c){
        pastActions.add(r);
        pastActions.add(c);
    }
    // removes action from board, resets cell, and skips turn
    private void undoAction(){
        if(pastActions.size() > 0) {
            int preCol = pastActions.get(pastActions.size() - 1);
            System.out.println(preCol);
            pastActions.remove(pastActions.size() - 1);
            int preRow = pastActions.get(pastActions.size() - 1);
            pastActions.remove(pastActions.size() - 1);
            board[preRow][preCol].setText("");
            board[preRow][preCol].setBackground(new JButton().getBackground());
            game.resetCell(preRow,preCol);

        }
        else System.out.println("Fail");
    }





}


