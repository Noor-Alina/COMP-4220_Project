package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass4 {

    BookManagement bm = new BookManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException, SQLException {
        
        assertEquals("Order placed, order# 56690", bm.sell(167934082, 1672895719, 15561, 12345678910111l));
    }
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> bm.sell(5, 2, "5A7", 12345565677l ));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> bm.sell(5, 167985719, 15781, 12345678922611l ));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase4(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> bm.sell(167937082, 1672235719, 12181, 12345678998761l));

            assertTrue(thrown.getMessage().contains("Input Not Found"));
    }
    
    @Test
    void testCase5(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> bm.sell(167937154, 1672895719, 15561, 12345565677l));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
}

