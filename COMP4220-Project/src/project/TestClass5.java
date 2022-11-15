package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestClass5 {

    SoftwareManagement sm = new SoftwareManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException{
		assertEquals("Employee isn't working today", sm.setHours(15561, "2022-10-01", "11:00", "15:00"));
	}
	
	@Test
    void testCase2() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> sm.setHours("5A7", "2022-11-27", "12:00", "14:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> sm.setHours(15781, "2023-04-01", "08:00", "10:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase4() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> sm.setHours(12181, "2022-07-05", "07:00", "12:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }

    @Test
    void testCase5() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> sm.setHours(12345, "2022-05-03", "13:00", "18:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
}
