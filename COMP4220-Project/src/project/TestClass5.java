package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass5 {

    SoftwareManagement sm = new SoftwareManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException {
        
        assertEquals("Book in stock", sm.search(4672895719));
    }
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> sm.search(2));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3() throws InputException, DatabaseException {
        
        assertEquals("Book not in stock", sm.search(467985716));
    }

    @Test
    void testCase4(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> sm.search(1234567890));

            assertTrue(thrown.getMessage().contains("Input Not Found"));
    }
    
}

