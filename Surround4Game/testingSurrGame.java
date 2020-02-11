package surroundpack;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * JUnit tests for surround 4 game
 * @author Max Foreback
 * @author Seth Ockerman
 */
public class testingSurrGame {

	//Testing a corner win with the default constructor
	@Test
	public void testCornersDef() {
		Surround4Game g = new Surround4Game();
		g.select(0, 1);
		g.nextPlayer();
		g.select(0,0);
		g.nextPlayer();
		g.select(1, 0);
		assertTrue(g.getWinner(10) == 0);
	}

	//Testing a side win with the default constructor
	@Test
	public void testSidesDef() {
		Surround4Game g = new Surround4Game();
		g.select(0, 2);
		g.nextPlayer();
		g.select(0,3);
		g.nextPlayer();
		g.select(1, 3);
		g.nextPlayer();
		g.select(5, 5);
		g.nextPlayer();
		g.select(0, 4);
		assertTrue(g.getWinner(10) == 0);
	}

	//Testing a middle win with the default constructor
	@Test
	public void testMidDef() {
		Surround4Game g = new Surround4Game();
		g.select(4, 4);
		g.nextPlayer();
		g.select(5,4);
		g.select(3, 4);
		g.select(4, 5);
		g.select(4, 3);
		assertTrue(g.getWinner(10) == 1);
	}

	//Testing a top left corner win with board size 10
	@Test
	public void testCornersPara() {
		Surround4Game g = new Surround4Game(10, 0);
		g.select(0, 1);
		g.nextPlayer();
		g.select(0,0);
		g.nextPlayer();
		g.select(1, 0);
		assertTrue(g.getWinner(10) == 0);
	}

	//Testing a top right corner win with board size 10
	@Test
	public void testCornersPara2() {
		Surround4Game g = new Surround4Game(10, 0);
		g.select(0, 8);
		g.nextPlayer();
		g.select(0,9);
		g.nextPlayer();
		g.select(1, 9);
		assertTrue(g.getWinner(10) == 0);
	}

	//Testing a bottom left corner win with board size 15
	@Test
	public void testCornersPara3() {
		Surround4Game g = new Surround4Game(15, 0);
		g.select(13, 0);
		g.nextPlayer();
		g.select(14,0);
		g.nextPlayer();
		g.select(14, 1);
		assertTrue(g.getWinner(15) == 0);
	}

	//Testing a bottom right corner win with board size 6
	@Test
	public void testCornersPara4() {
		Surround4Game g = new Surround4Game(6, 0);
		g.select(4, 5);
		g.nextPlayer();
		g.select(5,5);
		g.nextPlayer();
		g.select(5, 4);
		assertTrue(g.getWinner(6) == 0);
	}

	//Testing a top side win with board size 14
	@Test
	public void testTopSidePara() {
		Surround4Game g = new Surround4Game(14, 0);
		g.select(0, 2);
		g.nextPlayer();
		g.select(0,3);
		g.nextPlayer();
		g.select(1, 3);
		g.nextPlayer();
		g.select(5, 5);
		g.nextPlayer();
		g.select(0, 4);
		assertTrue(g.getWinner(14) == 0);
	}

	//Testing a left side win with board size 4
	@Test
	public void testLeftSidePara() {
		Surround4Game g = new Surround4Game(4, 0);
		g.select(2, 0);
		g.nextPlayer();
		g.select(1,0);
		g.select(3, 0);
		g.select(2, 1);
		assertTrue(g.getWinner(4) == 1);
	}

	//Testing a bottom side win with board size 13
	@Test
	public void testBotSidePara() {
		Surround4Game g = new Surround4Game(13, 0);
		g.select(12, 9);
		g.nextPlayer();
		g.select(12,8);
		g.select(12, 10);
		g.select(11, 9);
		assertEquals(1, g.getWinner(13));
	}

	//Testing a right side win with board size 19
	@Test
	public void testRightSidePara() {
		Surround4Game g = new Surround4Game(19, 0);
		g.select(9, 18);
		g.nextPlayer();
		g.select(8,18);
		g.select(10, 18);
		g.select(9, 17);
		assertEquals(1, g.getWinner(19));
	}

	//Testing a mid win with board size 19
	@Test
	public void testMidPara1() {
		Surround4Game g = new Surround4Game(19, 0);
		g.select(9, 8);
		g.nextPlayer();
		g.select(9,9);
		g.select(9, 7);
		g.select(10, 8);
		g.select(8, 8);
		assertEquals(1, g.getWinner(19));
	}

	//Testing a mid win with board size 7
	@Test
	public void testMidPara2() {
		Surround4Game g = new Surround4Game(7, 0);
		g.select(4, 2);
		g.nextPlayer();
		g.select(4,1);
		g.select(4, 3);
		g.select(3, 2);
		g.select(5, 2);
		assertEquals(1, g.getWinner(7));
	}

	//Testing a mid win with board size 5
	@Test
	public void testMidPara3() {
		Surround4Game g = new Surround4Game(5, 0);
		g.select(2, 2);
		g.nextPlayer();
		g.select(2,3);
		g.select(2, 1);
		g.select(3, 2);
		g.select(1, 2);
		assertEquals(1, g.getWinner(5));
	}

	//Testing a mid win with board size 14
	@Test
	public void testMidPara4() {
		Surround4Game g = new Surround4Game(14, 0);
		g.select(11, 3);
		g.nextPlayer();
		g.select(11,2);
		g.select(11, 4);
		g.select(10, 3);
		g.select(12, 3);
		assertEquals(1, g.getWinner(14));
	}

	//Testing a mid win with board size 9
	@Test
	public void testMidPara5() {
		Surround4Game g = new Surround4Game(9, 0);
		g.select(7, 7);
		g.nextPlayer();
		g.select(6,7);
		g.select(8, 7);
		g.select(7, 6);
		g.select(7, 8);
		assertEquals(1, g.getWinner(9));
	}
}
