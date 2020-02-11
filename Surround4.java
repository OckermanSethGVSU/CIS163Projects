package surroundpack;
import javax.swing.*;

/**
 * Creating menu user interface for surround 4 game
 * @author Max Foreback
 * @author Seth Ockerman
 */
public class Surround4 {

	public static void main (String[] args)  {
		//  Creating Menu GUI Components
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem newGameItem;
		JMenuItem undo;

		// Instantiating JFrame and giving it a close function
		JFrame frame = new JFrame ("Surround game");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		// add file drop down with a quit and new game option
		fileMenu = new JMenu("File");

		// adding undoButton, quitButton, and newGame button to menu
		undo = new JMenuItem("Undo Last Move");
		quitItem = new JMenuItem("quit");
		newGameItem = new JMenuItem("New Game");

		// adding new game,undo, and quit components to the file menu
		fileMenu.add(undo);
		fileMenu.add(newGameItem);
		fileMenu.add(quitItem);
		// create and add fileMenu to the menus GUI components
		menus = new JMenuBar();
		menus.add(fileMenu);
		frame.setJMenuBar(menus);
		// creating the JPanel and adding the JPanel to the frame
		Surround4Panel panel = new Surround4Panel(quitItem, newGameItem, undo);
		frame.add(panel);
		// set size of frame and make it visible
		frame.setSize(680, 680);
		frame.setVisible(true);
	}



}
