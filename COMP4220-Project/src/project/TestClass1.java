package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculator.MyException;

class TestClass1 {

	LibraryManagement lm = new LibraryManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException{
		
		assertEquals("order placed, Order#56690", lm.placeOrder(167934082, 4672895719, 15561));
	}
	
	@Test
	void testCase2() {
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> lm.placeOrder(5, 2, "5A7"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3() {
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> lm.placeOrder(5, 467985719, 15781));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase4(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> lm.placeOrder(167937080, 4672235783, 12181));

		    assertTrue(thrown.getMessage().contains("Student Number Not Found"));
	}
}