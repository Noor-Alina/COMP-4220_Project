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
		
		assertEquals("Book ISBN# 4672895719 Added to Inventory", bm.addInventory(4672895719));
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

		    assertTrue(thrown.getMessage().contains("Book ISBN# 1234567890 is not valid"));
	}
	
	@Test
	void testCase4(){
		
		assertEquals("The book ISBN 1672895710 has not arrived yet", bm.addInventory(1672895710));
	}
}