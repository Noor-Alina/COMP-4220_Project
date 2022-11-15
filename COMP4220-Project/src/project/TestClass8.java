package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass8 {

	SoftwareManagement sm = new SoftwareManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException{
		
		assertEquals("Order made, Order# 56690", sm.order(1672895719, 15561));
	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.order(2, "5A7"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> sm.order(1672235719, 15781));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	
	@Test
	void testCase4(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.order(1672235345, 155615));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	

	@Test
	void testCase5(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.order(167223534567, 15561));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	
}
