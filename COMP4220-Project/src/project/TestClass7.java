package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass7 {

    BookManagement bm = new BookManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException, SQLException {
        
        assertEquals("1672895710, Introduction of Astronomy, Roberto, Giles, Cengage Learning, 202.00, 18, 0", bm.search(1672895710));
    }
    
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> bm.search(2));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }

    @Test
    void testCase4(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> bm.search(1234567890));

            assertTrue(thrown.getMessage().contains("Input Not Found"));
    }
    
}

