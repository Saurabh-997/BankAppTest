package bankingApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bankingApp.Currency;
import bankingApp.Money;

public class MoneyTest {
	protected Currency CAD, HKD, NOK, EUR;
	protected Money CAD80, EUR15, CAD500, EUR30, CAD0, EUR0, CADnegative100,CAD10;
	
	@Before
	public void setUp() throws Exception {
		// setup sample currencies
		CAD = new Currency("CAD", 0.75);
		HKD = new Currency("HKD", 0.13);
		EUR = new Currency("EUR", 1.14);
		
		// setup sample money amounts
		CAD80 = new Money(100, CAD);
		
		EUR15 = new Money(10, EUR);
		CAD500 = new Money(500, CAD);
		EUR30 = new Money(20, EUR);
		CAD10 = new Money(10, CAD);
		CAD0 = new Money(0, CAD);
		EUR0 = new Money(0, EUR);
		CADnegative100 = new Money(-100, CAD);
	}

	@Test
	public void testGetAmount() {
		assertEquals(10, EUR15.getAmount(),0);
		assertEquals(200, CAD500.getAmount(),0);
		assertEquals(-100, CADnegative100.getAmount(),0);
	}

	@Test
	public void testGetCurrency() {
		assertEquals(EUR, EUR15.getCurrency());
		assertEquals(CAD, CAD500.getCurrency());

//		fail("Write test case here");
	}

	@Test
	public void testToString() {
		
		assertEquals("10.0 EUR", EUR15.toString());
		assertEquals("200.0 CAD", CAD500.toString());
//		fail("Write test case here");
	}

	@Test
	public void testGetUniversalValue() {
		assertEquals(150.0, CAD500.getUniversalValue(),0);
		assertEquals(11.4, EUR15.getUniversalValue(),0);
//		fail("Write test case here");
	}

	@Test
	public void testEqualsMoney() {
		
		assertTrue(CAD10.equals(new Money(6.58, EUR)));
//		fail("Write test case here");
	}

	@Test
	public void testAdd() {
		assertEquals(25.20,CAD10.add(new Money(10,EUR)).getAmount(),0.00);
//		fail("Write test case here");
	}

	@Test
	public void testSubtract() {
		assertEquals(8.48,CAD10.subtract(new Money(1,EUR)).getAmount(),0);
//		fail("Write test case here");
	}

	@Test
	public void testIsZero() {
		assertFalse((CAD10.isZero()));
		assertTrue((CAD0.isZero()));
//		fail("Write test case here");
	}

	@Test
	public void testNegate() {
		assertEquals(-100, CAD80.negate().getAmount(),0);
//		fail("Write test case here");
	}

	@Test
	public void testCompareTo() {
		assertEquals(-1, CAD10.compareTo(EUR15));
		assertEquals(0, CAD10.compareTo(CAD10));
//		fail("Write test case here");
	}
}
