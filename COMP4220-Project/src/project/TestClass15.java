package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass15 {

    BookManagement bm = new BookManagement();


    @Test
    void testCase1() throws InputException, DatabaseException, SQLException{

        assertEquals("Coursebook order added to the order inventory", bm.courseBook(29798, 2));
    }

    @Test
    void testCase2(){

        InputException thrown = assertThrows(
                InputException.class,
                   () -> bm.courseBook(2, 5));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }


    @Test
    void testCase3(){

        InputException thrown = assertThrows(
                InputException.class,
                   () -> bm.courseBook(29789, 1000));

            assertTrue(thrown.getMessage().contains("Invalid Input"));
    }


}
