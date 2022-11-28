package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass16 {

	LibraryManagement lm = new LibraryManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException, SQLException {
        
    	assertEquals("Book with ISBN 1780262046 has not been loaned within the last 6 months and is available for selling", lm.checkAvailability(1780262046));
    }
    
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> lm.checkAvailability(2));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }

    @Test
    void testCase3(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> lm.checkAvailability(1674887239));

            assertTrue(thrown.getMessage().contains("Input Not Found"));
    }

    @Test
    void testCase4() throws InputException, DatabaseException, SQLException {
        
    	assertEquals("Book with ISBN 1672895710 has been loaned within the last 6 months and is not available for selling", lm.checkAvailability(1672895710));
    }  
}