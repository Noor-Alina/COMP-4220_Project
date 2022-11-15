package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass6 {

	SoftwareManagement sm = new SoftwareManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException{
		
		assertEquals("Order made, Order# 56690", sm.order(4672895719, 15561, "abc12@uwindsor.ca"));
	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.order(2, "5A7", "abc12@gmail.com"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> sm.order(4672235719, 15781, "abc12@uwindsor.ca"));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	
	@Test
	void testCase4(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.order(4672235345, 16475, "abcd@gmail.com"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
}
