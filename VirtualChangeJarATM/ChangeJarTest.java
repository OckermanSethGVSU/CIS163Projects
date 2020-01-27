package ChangeJarPro;

import javafx.scene.control.TextFormatter;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ChangeJarTest {

	/**
	 *  JUnit for ChangeJar
	 * @Author Seth Ockerman
	 */


	// Testing valid constructors with wide range of values
	@Test
	public void testConstructor() {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);

		// passed in values
		assertEquals (6, jar1.getQuarters());
		assertEquals (5, jar1.getDimes());
		assertEquals (4, jar1.getNickels());
		assertEquals (2, jar1.getPennies());

		ChangeJar jar2 = new ChangeJar();
		assertEquals (0, jar2.getQuarters());
		assertEquals (0, jar2.getDimes());
		assertEquals (0, jar2.getNickels());
		assertEquals (0, jar2.getPennies());

		ChangeJar jar3 = new ChangeJar(jar1);
		assertEquals (6, jar3.getQuarters());
		assertEquals (5, jar3.getDimes());
		assertEquals (4, jar3.getNickels());
		assertEquals (2, jar3.getPennies());

		ChangeJar jar4 = new ChangeJar("1.33");
		assertEquals (5, jar4.getQuarters());
		assertEquals (0, jar4.getDimes());
		assertEquals (1, jar4.getNickels());
		assertEquals (3, jar4.getPennies());

		ChangeJar jar5 = new ChangeJar(100.33);
		assertEquals (401, jar5.getQuarters());
		assertEquals (0, jar5.getDimes());
		assertEquals (1, jar5.getNickels());
		assertEquals (3, jar5.getPennies());

		ChangeJar jar6 = new ChangeJar("100.33");
		assertEquals (401, jar5.getQuarters());
		assertEquals (0, jar5.getDimes());
		assertEquals (1, jar5.getNickels());
		assertEquals (3, jar5.getPennies());
	}



	// testing valid takeOut with wide range of
	// quarters, dimes, nickels, pennies
	@Test
	public void testTakeOut1() {
		ChangeJar jar = new ChangeJar(3,3,2,2);
		jar.takeOut(1,1,1,1);
		assertEquals (2, jar.getQuarters());
		assertEquals (2, jar.getDimes());
		assertEquals (1, jar.getNickels());
		assertEquals (1, jar.getPennies());
	}

	// testing valid takeOut with wide range of amounts
	@Test
	public void testTakeOut2() {
		ChangeJar jar1 = new ChangeJar(5,3,4,3);
		jar1.takeOut(1.22);
		assertEquals (1, jar1.getQuarters());
		assertEquals (1, jar1.getDimes());
		assertEquals (4, jar1.getNickels());

		ChangeJar jar2 = new ChangeJar(100,0,0,1);
		jar2.takeOut(25.01);
		assertEquals(jar2.getAmount(),0.0,0.001);
	}

	// testing putIn for valid low numbers
	@Test
	public void testPutIn() {
		ChangeJar jar = new ChangeJar();
		jar.putIn(2,3,4,5);
		assertEquals (2, jar.getQuarters());
		assertEquals (3, jar.getDimes());
		assertEquals (4, jar.getNickels());
		assertEquals (5, jar.getPennies());

		jar.putIn(1.00);
		assertEquals(6,jar.getQuarters());
		assertEquals (3, jar.getDimes());
		assertEquals (4, jar.getNickels());
		assertEquals (5, jar.getPennies());

		ChangeJar big1000 = new ChangeJar(1000.0);
		jar.putIn(big1000);
		assertEquals(4006,jar.getQuarters());
		assertEquals (3, jar.getDimes());
		assertEquals (4, jar.getNickels());
		assertEquals (5, jar.getPennies());
	}

	// Testing equals for valid numbers
	@Test
	public void testEqual () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 5, 4, 2);

		assertFalse(jar1.equals(jar2));
		assertTrue(jar1.equals(jar3));

		assertFalse(ChangeJar.equals(jar1,jar2));
		assertTrue(ChangeJar.equals(jar1,jar3));

	}

	// testing compareTo all returns
	@Test
	public void testCompareTo () {
		ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar3 = new ChangeJar(2, 3, 4, 2);
		ChangeJar jar4 = new ChangeJar(2, 5, 4, 2);

		assertTrue(jar2.compareTo(jar1) > 0);
		assertTrue(jar3.compareTo(jar1) < 0);
		assertTrue(jar1.compareTo(jar4) == 0);

		assertTrue(ChangeJar.compareTo(jar2,jar1) > 0);
		assertTrue(ChangeJar.compareTo(jar1,jar2) < 0);
		assertTrue(ChangeJar.compareTo(jar1,jar4) == 0);
	}
	// testing subtract
	@Test
	public void testSubtract(){
		ChangeJar test = new ChangeJar(6,6,6,6);
		ChangeJar target = new ChangeJar(5,5,5,5);
		test.subtract(1,1,1,1);
		assertTrue(ChangeJar.compareTo(test,target) == 0);
		test.subtract(target);
		assertTrue(test.getQuarters() == 0 && test.getNickels() == 0);
		target.dec();
		assertTrue(target.getPennies() == 4);
	}

	// testing add
	@Test
	public void testAdd(){
		ChangeJar test = new ChangeJar(6,6,6,6);
		ChangeJar target = new ChangeJar(5,5,5,5);
		target.add(1,1,1,1);
		assertTrue(ChangeJar.compareTo(test,target) == 0);
		test.add(target);
		assertTrue(test.getQuarters() == 12 && test.getNickels() == 12);
		test.add();
		assertTrue(test.getPennies() == 13);
	}

	// testing takeOut
	@Test
	public void testTakeOut(){
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
	}
	// load and save combined.
	@Test
	public void testLoadSave() {
		ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);

		jar1.save("file1");
		jar1 = new ChangeJar();  // resets to zero

		jar1.load("file1");
		assertTrue(jar1.equals(jar2));
	}

	// IMPORTANT: only one test per exception!!!
	// testing negative number for nickels in takeOut
	@Test
	(expected = IllegalArgumentException.class)
	public void testTakeOutNeNickels() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.takeOut(1,1,-1,1);
	}

	// testing negative number for dimes in takeOut
	@Test
	(expected = IllegalArgumentException.class)
	public void testTakeOutNegDimes() {
		ChangeJar jar1 = new ChangeJar(2,2,2,2);
		jar1.takeOut(1,-1,1,1);
		jar1.takeOut(1,-400,0,1);
	}

	// testing negative number quarters for the constructor
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegQuarters() {
		new ChangeJar(-300, 0, 0, 0);
	}

	// testing negative numbers of all
	@Test(expected = IllegalArgumentException.class)
	public void testingNegativesWithAllNumbers(){
		ChangeJar negative = new ChangeJar(-45,-50,-32,-0);
	}

	// testing negative strings
	@Test(expected = IllegalArgumentException.class)
		public void negativeString(){
			ChangeJar negative = new ChangeJar("-0.31");
		}

	// testing passing characters
	@Test(expected = IllegalArgumentException.class)
	public void charactersInString(){
		ChangeJar negative = new ChangeJar("abc.31");
	}

	// testing forgot decimal
	@Test(expected = IllegalArgumentException.class)
	public void noDecimalString(){
		ChangeJar negative = new ChangeJar("031");
	}

	// testing forgot zeros after decimal
	@Test(expected = IllegalArgumentException.class)
	public void forgotZeroesString(){
		ChangeJar negative = new ChangeJar("31");
	}

	// break doubles with negative
	@Test(expected = IllegalArgumentException.class)
	public void negativeDouble(){
		ChangeJar negative = new ChangeJar(-0.31);
	}

	// way too many decimals
	@Test(expected = IllegalArgumentException.class)
	public void tooManyDecimalsDouble(){
		ChangeJar negative = new ChangeJar(0.311111111);
	}

	// wrong object
	@Test(expected = IllegalArgumentException.class)
	public void incorrectInt(){
		ChangeJar wrong = new ChangeJar(new ChangeJar(-0.31));
	}
	// subtract negative number
	@Test(expected = IllegalArgumentException.class)
	public void negativeSubtraction(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.subtract(-5,-5,-5,-5);
	}

	// subtract results in a negative
	@Test(expected = IllegalArgumentException.class)
	public void negativeResultSubtraction(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.subtract(5,5,5,5);
	}

	// subtraction between two objects that results in negative
	@Test(expected = IllegalArgumentException.class)
	public void negativeObjectSubtractionResult(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		ChangeJar test2 = new ChangeJar(5,5,5,5);
		test.subtract(test2);
	}

	// decrease to a negative amounnt of pennies
	@Test(expected = IllegalArgumentException.class)
	public void cannotDecrease(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		for(int i = 0; i < 100; ++i){
			test.dec();
		}
	}

	// adding ridiculous amount of pennies to see if anything breaks
	@Test
	public void ridiculousAdd(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		for(int i = 0; i < 5000; ++i){
			test.add();
		}
		assertEquals(5004,test.getPennies());
	}

	// adding negative numbers
	@Test(expected = IllegalArgumentException.class)
	public void negativeAdd(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.add(-5,5,-5,-10);
	}

	// testing breakout in a variety of ways
	@Test(expected = IllegalArgumentException.class)
	public void breakTakeOut(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.takeOut(-0.50);
	}

	@Test(expected = IllegalArgumentException.class)
	public void breakTakeOut1(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.takeOut(400.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void breakTakeOut2(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.takeOut(8,8,8,8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void breakTakeOut3(){
		ChangeJar test = new ChangeJar(4,4,4,0);
		test.takeOut(1.06);
	}

	@Test(expected = IllegalArgumentException.class)
	public void breakTakeOutDouble(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.takeOut(-0.50);
	}
	// ending of trying to break takeOut

	// negative add
	@Test(expected = IllegalArgumentException.class)
	public void negativeDoubleAdd(){
		ChangeJar test = new ChangeJar(4,4,4,4);
		test.putIn(-4.00);
	}

	//testing mutate
	@Test
	public void testMutate(){
		// trying add and subtract with it off
		ChangeJar basic = new ChangeJar();
		ChangeJar.mutate(false);
		basic.putIn(85.00);
		basic.putIn(new ChangeJar(4,4,4,4));
		basic.putIn(4,4,4,4);
		basic.takeOut(33.00);
		basic.takeOut(new ChangeJar(2,1,1,4));
		basic.takeOut(1,1,1,1);
		basic.add();
		basic.add();
		basic.dec();
		basic.subtract(1,1,1,1);
		basic.subtract(new ChangeJar(1,1,1,1));
		assertEquals(0,basic.getQuarters());
		assertEquals(0,basic.getDimes());
		assertEquals(0,basic.getNickels());
		assertEquals(0,basic.getPennies());

		ChangeJar.mutate(true);
		basic.add();
		assertEquals(1,basic.getPennies());
	}

}
