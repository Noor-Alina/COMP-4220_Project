package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestClass6 {
    
	SoftwareManagement sm = new SoftwareManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException{
		assertEquals("Employee isn't working today", sm.viewHours(15561, "2022-10-01"));
	}
	
	@Test
	void testCase2() {
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.viewHours("5A7", "2022-11-27"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3() {
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> sm.viewHours(15781, "2023-04-01"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase4(){
        assertEquals("10-14", sm.viewHours(12181, "2022-07-05"));
	}
}
