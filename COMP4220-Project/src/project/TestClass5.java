package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass5 {

    EmployeeManagement em = new EmployeeManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		assertEquals("Employee#15561 set to work on 2022-11-15", em.setHours(15561, "2022-11-15", "11:00", "15:00"));
	}
	
	@Test
    void testCase2() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> em.setHours("5A7", "2022-11-27", "12:00", "14:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> em.setHours(15781, "2023-04-01", "08:00", "10:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase4() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> em.setHours(12181, "2022-07-05", "07:00", "12:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }

    @Test
    void testCase5() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> em.setHours(12345, "2022-05-03", "13:00", "18:00"));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
}
