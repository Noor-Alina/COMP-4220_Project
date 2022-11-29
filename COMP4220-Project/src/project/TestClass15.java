package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass15 {

	BookManagement bm = new BookManagement();
	
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		
		assertEquals("Book order added to the order inventory", bm.placedForOrder(1672895710, 15561));
	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.placedForOrder(2, "5A7"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.placedForOrder(1672235719, 15781));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	
	@Test
	void testCase4(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.placedForOrder(1672235345, 155615));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	

	@Test
	void testCase5(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.placedForOrder(167223534567l, 15561));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	
}
