package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass12 {

    EmployeeManagement em = new EmployeeManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException, SQLException {
        
        assertEquals("Employee created", em.createEmployee(12345, "Mellisa", "Palmer"));
    }
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> em.createEmployee(5A7, "Jen", "Tiger"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> em.createEmployee(13245, "Je@n", "Tiger"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase4(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> em.createEmployee(13245, "Jen", "Tige9r"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
}

