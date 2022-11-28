package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass11 {

    EmployeeManagement em = new EmployeeManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException, SQLException {
        
        assertEquals("Student created", em.createStudent(157934082, "Connie", "Tucker", "abc12@uwindsor.ca"));
    }
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> em.createStudent("5A7", "Joe", "Tucker", "abc12@uwindsor.ca"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> em.createStudent(115567788, "Jo@e", "Tucker", "abc12@uwindsor.ca"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase4(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> em.createStudent(115567788, "Joe", "Tuck9er", "abc12@uwindsor.ca"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase5(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> em.createStudent(115567788, "Joe", "Tucker", "abc12@gmail.ca"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
}

