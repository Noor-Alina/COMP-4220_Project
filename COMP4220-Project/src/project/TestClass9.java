package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass9 {
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		assertEquals("Book with ISBN 1672895710 has not been loaned within the last 6 months and is available for selling", checkAvailability(1672895710));
	}
	
	@Test
    void testCase2() {

        InputException thrown = assertThrows(
                InputException.class,
                () -> checkAvailability(2));

        assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> checkAvailabilty(7674887239));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
    
    @Test
    void testCase4() throws InputException, DatabaseException, SQLException{
		assertEquals("Book with ISBN 1672235345 has been loaned within the last 6 months and is not available for selling", checkAvailability(1672235345));
	}

}