package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass17 {
	
	LibraryManagement lm = new LibraryManagement();
    
    @Test
    void testCase1() throws InputException, DatabaseException, SQLException {
        
        assertEquals("Loan#20001\n\nStudent Number: 167934082\n\nISBN-10: 1672895710\n\nEmployee Number: 15561\n\nDate: '2022-11-28'\n\nYour loan period is '2022-11-28'--'2022-12-05'!", lm.loan(167934082, 1672895710, 15561));
    }
    
    @Test
    void testCase2(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> lm.loan(5, 2, "5A7"));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase3(){
        
        InputException thrown = assertThrows(
                InputException.class,
                   () -> lm.loan(5, 167985719, 15781));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }
    
    @Test
    void testCase4(){
        
        DatabaseException thrown = assertThrows(
                DatabaseException.class,
                   () -> lm.loan(167937080, 1672235719, 12181));

            assertTrue(thrown.getMessage().contains("Input Not Found"));
    }
}
