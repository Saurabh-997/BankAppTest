package bankingApp;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bankingApp.AccountDoesNotExistException;
import bankingApp.AccountExistsException;
import bankingApp.Bank;
import bankingApp.Currency;

public class BankTest {
	protected Currency CAD;
	protected Currency HKD;
	protected Bank RBC;
	protected Bank TD;
	protected Bank HSBC;
	
	
	@Before
	public void setUp() throws Exception {
		
		// setup some test currencies
		this.HKD = new Currency("HKD", 0.13);
		this.CAD = new Currency("CAD", 0.75);
		
		// setup test banks
		this.RBC = new Bank("Royal Bank of Canada", CAD);
		this.TD = new Bank("TD Bank", CAD);
		this.HSBC = new Bank("Hong Kong Shanghai Banking Corporation", HKD);
		
		// add sample customers to the banks
		
		
		// HINT:  uncomment these lines AFTER you test the openAccount() function
		// You can quickly uncomment / comment by highlighting the lines of code and pressing 
		// CTRL + / on your keyboard  (or CMD + / for Macs)
		
//		this.RBC.openAccount("Marcos");
//		this.RBC.openAccount("Albert");
//		this.TD.openAccount("Jigesha");
//		this.HSBC.openAccount("Pritesh");
	}

	@Test
	public void testGetName() {
		assertEquals("Royal Bank of Canada", this.RBC.getName());
//		fail("Write test case here");
	}

	@Test
	public void testGetCurrency() {
		assertEquals(CAD, this.RBC.getCurrency());
//		fail("Write test case here");
	}

	@Test(expected = AccountExistsException.class)
	public void testOpenAccount() throws AccountExistsException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		this.RBC.openAccount("Marcos");
		
		this.RBC.openAccount("Albert");
		assertTrue(this.RBC.getAccountlist().containsKey("Marcos"));
		assertTrue(this.RBC.getAccountlist().containsKey("Albert"));
		this.RBC.openAccount("Marcos");
//		fail("Write test case here");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testDeposit() throws AccountExistsException,AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		this.RBC.openAccount("Marcos");
		this.RBC.deposit("Marcos", new Money(25.50, this.CAD));
		double initialAmount=this.RBC.getAccountlist().get("Marcos").getBalance().getAmount();
		this.RBC.deposit("Marcos", new Money(25.50, this.CAD));
		assertEquals(initialAmount+25.50,
				this.RBC.getAccountlist().get("Marcos").getBalance().getAmount());
		this.RBC.deposit("Ali", new Money(25.50, this.CAD));
//		fail("Write test case here");
	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testWithdraw() throws AccountExistsException,AccountDoesNotExistException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		this.RBC.openAccount("Marcos");
		this.RBC.deposit("Marcos", new Money(50.50, this.CAD));
		double initialAmount=this.RBC.getAccountlist().get("Marcos").getBalance().getAmount();
		this.RBC.withdraw("Marcos", new Money(25.50, this.CAD));
		assertEquals(initialAmount-25.50,
				this.RBC.getAccountlist().get("Marcos").getBalance().getAmount());
		this.RBC.deposit("Ali", new Money(25.50, this.CAD));
//		fail("Write test case here");
	}
	
	@Test(expected = AccountDoesNotExistException.class)
	public void testGetBalance() throws AccountDoesNotExistException, AccountExistsException {
		// If the function throws an exception, you should also test
		// that the exception gets called properly.
		
		// See the example in class notes for testing exceptions.
		this.RBC.openAccount("Marcos");
		assertEquals(0.0, this.RBC.getBalance("Marcos"));
		this.RBC.getBalance("Boom");
//		fail("Write test case here");
	}
	
	@Test(expected = AccountDoesNotExistException.class)
	public void testTransfer() throws AccountDoesNotExistException, AccountExistsException {
		// Note: You should test both types of transfers:
		// 1. Transfer from account to account
		
		// See the Bank.java file for more details on Transfers
		
		this.RBC.openAccount("Marcos");
		this.RBC.openAccount("Bill");
		this.RBC.deposit("Marcos", new Money(25.50, this.CAD));
		this.RBC.transfer("Marcos","Bill", new Money(15.50, this.CAD));
		assertEquals(15.5, this.RBC.getBalance("Bill"));
		assertEquals(10.0, this.RBC.getBalance("Marcos"));
		
		// 2. Transfer between banks
		this.TD.openAccount("Bill");
		this.RBC.deposit("Marcos", new Money(25.50, this.CAD));
		this.RBC.transfer("Marcos",this.TD,"Bill", new Money(25.50, this.CAD));
		assertEquals(25.5, this.TD.getBalance("Bill"));
		assertEquals(10.0, this.RBC.getBalance("Marcos"));
		this.RBC.transfer("boom","Bill", new Money(25.50, this.CAD));
		this.RBC.transfer("boom",this.TD,"Bill", new Money(25.50, this.CAD));

		
		
//		fail("Write test case here");
	}
	
}
