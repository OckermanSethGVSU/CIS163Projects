package surroundpack;

/**
 * Individual GUI competent for user to select
 * @author Max Foreback
 * @author Seth Ockerman
 */
public class Cell {
	// current player
	private int playerNumber;
	// risk level of a cell being surrounded
	// 1 is medium
	// 2 is high
	private int risk =0;
	
	/**
	 * Constructor that creates a cell selected by the player passed in
	 * @param playerNumber the player number you want passed in as an int
	 */
	public Cell(int playerNumber) {
		super();
		this.playerNumber = playerNumber;
	}

	/**
	 * returns the player number of a given selected cell
	 * @return the player number as an int
	 */
	public int getPlayeNumber() {
		return playerNumber;
	}

	/**
	 * gets the risk
	 * @return risk as an int
	 */
	public int getRisk(){
		return risk;
	}

	/**
	 * sets the risk
	 * @param riskValue how high the risk. One for medium. Two for high
	 */
	public void setRisk(int riskValue){
		if(riskValue > -1 && riskValue < 3){
			risk = riskValue;
		}
	}

}

