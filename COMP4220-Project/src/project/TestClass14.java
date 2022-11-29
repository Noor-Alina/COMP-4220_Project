package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass14 {
	
	BookManagement bm = new BookManagement();
	
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		
		assertEquals("Book ISBN# 1672895710 Added to Inventory", bm.addInventory(1672895710));
	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.addInventory(2));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.addInventory(1234567890));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	@Test
	void testCase4() throws InputException, DatabaseException, SQLException{
		
		assertEquals("The book with ISBN 1780735211 has not arrived yet", bm.addInventory(1780735211));
	}
}