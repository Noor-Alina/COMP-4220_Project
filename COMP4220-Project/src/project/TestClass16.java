package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass16 {

    BookManagement bm = new BookManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException, SQLException {
        
        assertEquals("Book with ISBN  4672895719 has not been loaned within the last 6 months and is available for selling", bm.checkAvailability(4672895719));
    }
    
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> bm.checkAvailability(2));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }

    @Test
    void testCase4(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> bm.checkAvailability(7674887239));

            assertTrue(thrown.getMessage().contains("Input Not Found"));
    }

    @Test
    void testCase4() throws InputException, DatabaseException, SQLException {
        
        assertEquals("Book with ISBN 1672235345 has been loaned within the last 6 months and is not available for selling", bm.checkAvailability(1672235345));
    }
    
}

