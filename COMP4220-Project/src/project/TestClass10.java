package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass10 {

	BookManagement bm = new BookManagement();
	
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		
		assertEquals("Book requested", bm.requestBook(1672895710));
	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.requestBook(2));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.requestBook(131122664));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	
	
	
}
