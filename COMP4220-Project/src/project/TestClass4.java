package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass4 {

    SoftwareManagement sm = new SoftwareManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException {
        
        assertEquals("Order placed, order# 56690", sm.sell(167934082, 4672895719, 15561, 12345678910111);
    }
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> sm.sell(5, 2, "5A7", 12345565677 ));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> sm.sell(5, 467985719, 15781, 12345678922611 ));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase4(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> sm.sell(167937082, 4672235719, 12181, 12345678998761));

            assertTrue(thrown.getMessage().contains("Input Not Found"));
    }
    
    @Test
    void testCase5(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> sm.sell(167937154, 4672895719, 15561, 12345565677));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
}

