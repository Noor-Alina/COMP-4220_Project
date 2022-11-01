package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass3 {

	SoftwareManagement sm = new SoftwareManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException{
		
		assertEquals("Reservation made, Reservation# 56690", sm.reserveOutOfStock(167934082, 4672895719, 15561, "abc12@uwindsor.ca"));
	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.reserveOutOfStock(5, 2, "5A7", "abc12@gmail.com"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.reserveOutOfStock(5, 467985719, 15781, "abc12@uwindsor.ca"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase4(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> sm.reserveOutOfStock(167937082, 4672235719, 12181, "abc12@uwindsor.ca"));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	@Test
	void testCase5(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.reserveOutOfStock(167937154, 4672235345, 16475, "abcd@gmail.com"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
}
