package surroundpack;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * backend for surround4 Game
 * @author Max Foreback
 * @author Seth Ockerman
 */
public class Surround4Game {
    // inner array that holds cells
    private Cell[][] board;
    // the current player
    private int player;
    // number of wins
    private static ArrayList <Integer> wins = new ArrayList <Integer>();

    // very important. Do not touch
    // variables for keeping track of  win placements
    private static int[] winners = new int[18];
    private static int firstPlaceScore = 0;
    private static int secondPlaceScore = 0;
    private static int thirdPlaceScore = 0;
    private static int firstPlace = 1;
    private static int secondPlace = 2;
    private static int thirdPlace = 3;

    /**
     * Constructor that creates a surround 4 game
     */
    public Surround4Game() {
        //super();
        board = new Cell[10][10];
        this.player = 0;
    }

    /**
     * Constructor that creates a surround 4 game based on passed in ints
     * @param s number of cells as an int
     * @param starting the starting player's number
     */
    public Surround4Game(int s,int starting) {
        //super();
        board = new Cell[s][s];
        this.player = starting;

    }

    /**
     * resets game board
     */
    public void reset() {
        player = 1;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                board[r][c] = null;
                board[r][c].setRisk(0);
            }
        }

    }

    /**
     * Resets specific cell
     * @param r row of cell desired
     * @param c column of cell desired
     */
    public void resetCell(int r, int c){
        board[r][c] = null;

    }

    /**
     * resets game board with different sized boards based on passed in ints
     * @param s the dimension of the board to reset
     * @param starting the player who will start
     */
    public void reset(int s,int starting) {
        player = starting;
        for (int r = 0; r < s; r++) {
            for (int c = 0; c < s; c++) {
                board[r][c] = null;
            }
        }
    }

    /**
     * gets the cell at a passed in grid location
     * @param row the row of the cell you want as an int
     * @param col the col of the cell you want as an int
     * @return the specified cell
     */
    public Cell getCell(int row, int col) { return board[row][col]; }

    /**
     * returns the current player
     * @return the number of the current player
     */
    public int getCurrentPlayer() {
        return player;
    }

    /**
     * function that switches to the next player
     * @return the new player number as an int
     */
    public int nextPlayer() {
        player = (player + 1) % 2;
        return player;

    }

    /**
     * function that switches to the next player
     * @param numPlayers is the number of players
     * @return returns the next player in the sequence
     */
    public int nextPlayer(int numPlayers) {
        player = (player + 1) % numPlayers;
        return player;
    }
    /**
     * checks if a cell is selected based on a passed in row and column
     * @param row the row of the desired cell as an int
     * @param col the column of the desired cell as an int
     * @return true if selected false if not
     */
    public boolean select(int row, int col) {
        if (board[row][col] == null) {  //|| (cats() && board[row][col].getPlayeNumber() != player)) {
            Cell c = new Cell(player);
            board[row][col] = c;
            return true;
        } else
            return false;
    }

    /**
     * gets the winner based on the moves
     * @return the player that won
     */
    public int getWinner(int s) {
        // uses nested loops to check if someone won and adds win to win counter for each player
        for (int row = 0; row < s; row++) {
            for (int col = 0; col < s; col++) {
                // Checks top left corner for winner
                if (row == 0 && col == 0) {
                    if (board[0][1] != null && board[1][0] != null && board[0][0] != null) {
                        if (board[0][1].getPlayeNumber() + 1 == board[1][0].getPlayeNumber() + 1 &&
                                board[0][1].getPlayeNumber() != board[0][0].getPlayeNumber()) {
                            wins.add(board[0][1].getPlayeNumber());
                            return board[0][1].getPlayeNumber();
                        }
                    }
                }
                //Checks top right corner for winner
                if (row == 0 && col == s - 1) {
                    if (board[0][s - 2] != null && board[1][s - 1] != null && board[0][s-1] != null)
                        if (board[0][s-2].getPlayeNumber() + 1 == board[1][s-1].getPlayeNumber() + 1 &&
								board[0][s-2].getPlayeNumber() != board[0][s-1].getPlayeNumber()
						) {
                            wins.add(board[0][s - 2].getPlayeNumber());
                            return board[0][s - 2].getPlayeNumber();
                        }
                }
                //Checks bottom left corner for winner
                if (row == s - 1 && col == 0) {
                    if (board[s - 2][0] != null && board[s - 1][1] != null && board[s-1][0] != null)
                        if (board[s - 2][0].getPlayeNumber() + 1 == board[s - 1][1].getPlayeNumber() + 1 &&
								board[s-2][0].getPlayeNumber() != board[s-1][0].getPlayeNumber()

						) {
                            wins.add(board[s - 2][0].getPlayeNumber());
                            return board[s - 2][0].getPlayeNumber();
                        }
                }
                //Checks bottom right corner for winner
				if (row == s-1 && col == s-1) {
					if (board[s-1][s-2] != null && board[s-2][s-1] != null && board[s-1][s-1] != null)
						if (board[s-1][s-2].getPlayeNumber() + 1 == board[s-2][s-1].getPlayeNumber() + 1 &&
								board[s-1][s-2].getPlayeNumber() != board[s-1][s-1].getPlayeNumber()) {
                            wins.add(board[s - 1][s - 2].getPlayeNumber());
                            return board[s - 1][s - 2].getPlayeNumber();
                        }
				}


				// checks left border for winner
                if (row != 0 && row != s-1 && col == 0)
                    if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] != null
                    && board[row][col] != null)
                        if (board[row - 1][col].getPlayeNumber() == board[row][col + 1].getPlayeNumber() &&
                                board[row - 1][col].getPlayeNumber() == board[row + 1][col].getPlayeNumber()
                                && board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            wins.add(board[row - 1][col].getPlayeNumber());
                            return board[row - 1][col].getPlayeNumber();
                        }
                //checks right border for winner
                if (row != 0 && row != s-1 && col == s-1)
                    if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] != null
                            && board[row][col] != null)
                        if (board[row - 1][col].getPlayeNumber() == board[row][col - 1].getPlayeNumber() &&
                                board[row - 1][col].getPlayeNumber() == board[row + 1][col].getPlayeNumber()
                                && board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            wins.add(board[row - 1][col].getPlayeNumber());
                            return board[row - 1][col].getPlayeNumber();
                        }
                //checks top border for winner
                if (col != 0 && col != s-1 && row == 0)
                    if (board[row][col - 1] != null && board[row][col + 1] != null && board[row + 1][col] != null
                            && board[row][col] != null)
                        if (board[row][col - 1].getPlayeNumber() == board[row][col + 1].getPlayeNumber() &&
                                board[row + 1][col].getPlayeNumber() == board[row][col - 1].getPlayeNumber()
                                && board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            wins.add(board[row + 1][col].getPlayeNumber());
                            return board[row + 1][col].getPlayeNumber();
                        }
                //checks bottom border for winner
                if (col != 0 && col != s-1 && row == s-1)
                    if (board[row][col - 1] != null && board[row][col + 1] != null && board[row - 1][col] != null
                            && board[row][col] != null)
                        if (board[row][col - 1].getPlayeNumber() == board[row][col + 1].getPlayeNumber() &&
                                board[row - 1][col].getPlayeNumber() == board[row][col - 1].getPlayeNumber()
                                && board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            wins.add(board[row - 1][col].getPlayeNumber());
                            return board[row - 1][col].getPlayeNumber();
                        }

            }
        }
        // checks the middle of the board for a surround win
        for (int row = 1; row < s -1 ; ++row) {
            for (int col = 1; col < s -1 ; ++col) {

                if (board[row][col] != null) {
                    int playerNum = board[row][col].getPlayeNumber();
                    if (board[row - 1][col] != null && board[row][col + 1] != null
                            && board[row + 1][col] != null && board[row][col - 1] != null) {
                        if (playerNum != board[row - 1][col].getPlayeNumber() &&
                                playerNum != board[row][col + 1].getPlayeNumber() &&
                                playerNum != board[row + 1][col].getPlayeNumber() &&
                                playerNum != board[row][col - 1].getPlayeNumber() && match(row,col)) {
                            wins.add(board[row][col + 1].getPlayeNumber());
                            wins.add(board[row][col + 1
                                    ].getPlayeNumber());
                            return board[row][col + 1].getPlayeNumber();
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Sets the risk of the cell
     * @param s the size of the board
     */
    public void setRisk(int s) {
        // uses nested loops to check risk levels
        for (int row = 0; row < s; row++) {
            for (int col = 0; col < s; col++) {
                // Checks top left corner for risk level
                if (row == 0 && col == 0) {
                    if (board[0][1] != null && board[0][0] != null) {
                        if (board[0][1].getPlayeNumber() != board[0][0].getPlayeNumber()) {
                            board[0][0].setRisk(2);
                        }

                    }
                    if (board[0][0] != null && board[1][0] != null) {
                        if (board[1][0].getPlayeNumber() != board[0][0].getPlayeNumber()) {
                            board[0][0].setRisk(2);
                        }
                    }
                }
                //Checks top right corner for risk level
                if (row == 0 && col == s - 1) {
                    if (board[0][s - 2] != null && board[0][s - 1] != null) {
                        if (board[0][s - 2].getPlayeNumber() != board[0][s - 1].getPlayeNumber()) {
                            board[0][s - 1].setRisk(2);
                        }
                    }
                    if (board[1][s - 1] != null && board[0][s - 1] != null) {
                        if (board[1][s - 1].getPlayeNumber() != board[0][s - 1].getPlayeNumber()) {
                            board[0][s - 1].setRisk(2);
                        }
                    }
                }
                //Checks bottom left corner for risk level
                if (row == s - 1 && col == 0) {
                    if (board[s - 2][0] != null && board[s - 1][0] != null) {
                        if (board[s - 2][0].getPlayeNumber() != board[s - 1][0].getPlayeNumber()) {
                            board[s - 1][0].setRisk(2);
                        }
                    }
                    if (board[s - 1][0] != null && board[s - 1][1] != null) {
                        if (board[s - 1][1].getPlayeNumber() != board[s - 1][0].getPlayeNumber()) {
                            board[s - 1][0].setRisk(2);
                        }
                    }

                }


                //Checks bottom right corner for risk
                if (row == s - 1 && col == s - 1) {
                    if (board[s - 1][s - 2] != null && board[s - 1][s - 1] != null) {
                        if (board[s - 1][s - 2].getPlayeNumber() != board[s - 1][s - 1].getPlayeNumber()) {
                            board[s - 1][s - 1].setRisk(2);
                        }
                    }
                    if (board[s - 1][s - 1] != null && board[s - 2][s - 1] != null) {
                        if (board[s - 2][s - 1].getPlayeNumber() != board[s - 1][s - 1].getPlayeNumber()) {
                            board[s - 1][s - 1].setRisk(2);
                        }
                    }
                }


                // checks left border for risk
                if (row != 0 && row != s - 1 && col == 0) {
                    //checking above for low risk
                    if (board[row + 1][col] != null && board[row][col] != null)
                        if (board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    //checking below for low risk
                    if (board[row - 1][col] != null && board[row][col] != null)
                        if (board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    //checking in front for low risk
                    if (board[row][col + 1] != null && board[row][col] != null)
                        if (board[row][col + 1].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    // checking above and below for high risk
                    if (board[row + 1][col] != null && board[row][col] != null && board[row - 1][col] != null) {
                        if (board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && doubleMatch(row, col)) {
                            board[row][col].setRisk(2);
                        }
                    }
                    //checking above and infront for high risk
                    if (board[row - 1][col] != null && board[row][col] != null && board[row][col + 1] != null) {
                        if (board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row][col + 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && borderCornerMatch(row, col, "above", "left")) {
                            board[row][col].setRisk(2);
                        }
                    }
                    //checking below and infront for high risk
                    if (board[row + 1][col] != null && board[row][col] != null && board[row][col + 1] != null) {
                        if (board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row][col + 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && borderCornerMatch(row, col, "below", "left")) {
                            board[row][col].setRisk(2);
                        }
                    }
                }
                //checks right border for risk
                if (row != 0 && row != s - 1 && col == s - 1) {
                    //checking above for low risk
                    if (board[row - 1][col] != null && board[row][col] != null)
                        if (board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    //checking below for low risk
                    if (board[row + 1][col] != null && board[row][col] != null)
                        if (board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    //checking in front for low risk
                    if (board[row][col - 1] != null && board[row][col] != null)
                        if (board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }

                    // checking above and below for high risk
                    if (board[row - 1][col] != null && board[row][col] != null && board[row + 1][col] != null) {
                        if (board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber() &&
                                board[row - 1][col].getPlayeNumber() == board[row + 1][col].getPlayeNumber()) {
                            board[row][col].setRisk(2);
                        }
                    }


                    //checking above and infront for high risk
                    if (board[row - 1][col] != null && board[row][col] != null && board[row][col - 1] != null) {
                        if (board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && borderCornerMatch(row, col, "above", "right")) {
                            board[row][col].setRisk(2);
                        }
                    }
                    //checking below and infront for high risk
                    if (board[row + 1][col] != null && board[row][col] != null && board[row][col - 1] != null) {
                        if (board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && borderCornerMatch(row, col, "below", "right")) {
                            board[row][col].setRisk(2);
                        }
                    }
                }
                //checks top border for risk
                if (col != 0 && col != s - 1 && row == 0) {

                    //checking left for low risk
                    if (board[row][col - 1] != null && board[row][col] != null) {
                        if (board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    }
                    //checking right for low risk
                    if (board[row][col] != null && board[row][col+1] != null) {
                        if (board[row][col].getPlayeNumber() != board[row][col+1].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    }
                    //checking below for low risk
                    if (board[row + 1][col] != null && board[row][col] != null) {
                        if (board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    }
                    //checking left and low for high risk
                    if (board[row][col - 1] != null && board[row][col] != null && board[row+1][col] != null)
                        if (board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row+1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(2);
                        }
                    //checking right and low for high risk
                    if (board[row][col + 1] != null && board[row][col] != null && board[row + 1][col] != null) {
                        if (board[row][col + 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row + 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(2);
                        }
                    }

                    // checking left and right for high risk
                    if (board[row][col - 1] != null && board[row][col] != null && board[row][col + 1] != null) {
                        if (board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row][col + 1].getPlayeNumber() != board[row][col].getPlayeNumber() &&
                                board[row][col + 1].getPlayeNumber() == board[row][col - 1].getPlayeNumber()) {
                            board[row][col].setRisk(2);
                        }
                    }
                }

                //checks bottom border for risk
                if (col != 0 && col != s - 1 && row == s - 1) {

                    //checking left for low risk
                    if (board[row][col - 1] != null && board[row][col] != null) {
                        if (board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    }
                    //checking right for low risk
                    if (board[row][col+1] != null && board[row][col] != null) {
                        if (board[row][col+1].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    }
                    //checking up for low risk
                    if (board[row - 1][col] != null && board[row][col] != null) {
                        if (board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(1);
                        }
                    }
                    //checking left and high for high risk
                    if (board[row][col - 1] != null && board[row][col] != null && board[row - 1][col] != null)
                        if (board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(2);
                        }
                    //checking right and up for high risk
                    if (board[row][col + 1] != null && board[row][col] != null && board[row - 1][col] != null) {
                        if (board[row][col + 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row - 1][col].getPlayeNumber() != board[row][col].getPlayeNumber()) {
                            board[row][col].setRisk(2);
                        }
                    }

                    // checking left and right for high risk
                    if (board[row][col - 1] != null && board[row][col] != null && board[row][col + 1] != null) {
                        if (board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber()
                                && board[row][col + 1].getPlayeNumber() != board[row][col].getPlayeNumber() &&
                                board[row][col + 1].getPlayeNumber() == board[row][col - 1].getPlayeNumber()) {
                            board[row][col].setRisk(2);
                        }
                    }
                }
            }
        }
        // checks the middle of the board for risk
        for (int row = 1; row < s - 1; ++row) {
            for (int col = 1; col < s - 1; ++col) {
                if (board[row][col] != null) {
                    int playerNum = board[row][col].getPlayeNumber();
                    if (board[row - 1][col] != null || board[row][col + 1] != null
                            || board[row + 1][col] != null || board[row][col - 1] != null) {
                        int count = 0;
                        if(board[row-1][col] != null && board[row-1][col].getPlayeNumber() != board[row][col].getPlayeNumber())
                            count++;
                        if(board[row+1][col] != null && board[row+1][col].getPlayeNumber() != board[row][col].getPlayeNumber())
                            count++;
                        if(board[row][col + 1] != null && board[row][col+1].getPlayeNumber() != board[row][col].getPlayeNumber())
                            count++;
                        if(board[row][col-1] != null && board[row][col - 1].getPlayeNumber() != board[row][col].getPlayeNumber())
                            count++;
                        if (count == 3)
                            board[row][col].setRisk(2);
                        if(count == 2)
                            board[row][col].setRisk(1);
                        }
                    }
                }
            }
        }



    // method to make sure surrounding cells play num match
    private boolean match(int row, int col){
        int playerNum = board[row-1][col].getPlayeNumber();
        boolean match = false;
        if (board[row - 1][col] != null && board[row][col + 1] != null
                && board[row + 1][col] != null && board[row][col - 1] != null) {
            if (playerNum == board[row - 1][col].getPlayeNumber() &&
                    playerNum == board[row][col + 1].getPlayeNumber() &&
                    playerNum == board[row + 1][col].getPlayeNumber() &&
                    playerNum == board[row][col - 1].getPlayeNumber()){
                match = true;
            }
        }
        return match;
    }

    /* checks if two cells(above and below target) match
    * @param row the desired row
    * @param col the desired col
    */
    private boolean doubleMatch(int row,int col){
        boolean match = false;
        if(board[row + 1][col].getPlayeNumber() == board[row - 1][col].getPlayeNumber() ){
            match = true;
        }
        return match;
    }
    /* checks if the border cases match(only for left and right border)
       takes target row and column.
       can specify above or below using string x
       can specify left or right using string y
     */
    private boolean borderCornerMatch(int row,int col,String x,String y) {
        boolean match = false;
        if (y.equals("left")) {
            if (x.equals("above")) {
                if (board[row - 1][col].getPlayeNumber() == board[row][col + 1].getPlayeNumber()) {
                    match = true;
                }
            }
            if (x.equals("below")) {
                if (board[row + 1][col].getPlayeNumber() == board[row][col + 1].getPlayeNumber()) {
                    match = true;
                }
            }

        }
        if(y.equals("right")){
            if (x.equals("above")) {
                if (board[row - 1][col].getPlayeNumber() == board[row][col - 1].getPlayeNumber()) {
                    match = true;
                }
            }
            if (x.equals("below")) {
                if (board[row + 1][col].getPlayeNumber() == board[row][col - 1].getPlayeNumber()) {
                    match = true;
                }
            }
        }
        return match;
    }

    /**
     * gets number of wins
     * @return number of wins as an int
     */
    public static ArrayList<Integer> getWins(){
        return wins;
    }

    public static void clearWins(){
        wins = new ArrayList<Integer>();
    }


    /**
     * returns winners
     */
    public static void getWinners(){
        //array winners holds the wins for each player. Player nums = index nums
        //array wins hold which player won every game. elements are player nums, indexes are game nums
        Arrays.fill(winners, 0);

        //creating temporary local variables to hold place values
        int fp = 0;
        int fps = 0;
        int sp = 0;
        int sps = 0;
        int tp = 0;
        int tps = 0;

        //iterate through and populate the winners array
        for (int i = 0; i < winners.length; i++){
            for (int j = 0; j < getWins().size(); j++){
                if(i == getWins().get(j)){
                    winners[i] = winners[i] + 1;
                }
            }
        }
        //create temporary array to store values
        ArrayList <Integer> temp = new ArrayList<Integer>();
        for(int i = 0; i < winners.length; i++)
            temp.add(winners[i]);

        //find first place and remove it from temp array. The same with second and third.
        for(int i = 0; i < temp.size(); i++) {
            if (temp.get(i) > fps){
                fps = temp.get(i);
                fp = i;
            }
        }
        temp.set(fp, -10);
        for(int i = 0; i < temp.size(); i++) {
            if (temp.get(i) > sps){
                sps = temp.get(i);
                sp = i;
            }
        }
        temp.set(sp, -10);
        for(int i = 0; i < temp.size(); i++) {
            if (temp.get(i) > tps){
                tps = temp.get(i);
                tp = i;
            }
        }
        temp.set(tp, -10);

        //Assign local variables to static variables
        firstPlace = fp;
        firstPlaceScore = fps;
        secondPlace = sp;
        secondPlaceScore = sps;
        thirdPlace = tp;
        thirdPlaceScore = tps;

    }

    /**
     * gets first place score
     * @return first place score
     */
    public static int getFirstPlaceScore(){
        return firstPlaceScore;
    }

    /**
     * gets second place score
     * @return the second space score
     */
    public static int getSecondPlaceScore(){
        return secondPlaceScore;
    }

    /**
     * gets third place score
     * @return the third place score
     */
    public static int getThirdPlaceScore(){
        return thirdPlaceScore;
    }

    /**
     * gets first place index
     * @return first place index
     */
    public static int getFirstPlace(){
        return firstPlace;
    }

    /**
     * gets second place index
     * @return second place index
     */
    public static int getSecondPlace(){
        return secondPlace;
    }

    /**
     * gets third place index
     * @return third place index
     */
    public static int getThirdPlace(){
        return thirdPlace;
    }
}






