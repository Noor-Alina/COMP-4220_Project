package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass13 {
	
	BookManagement bm = new BookManagement();
	
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		
		assertEquals("Book ISBN# 4672895719 Added to Inventory", bm.addInventory(4672895719, "2022-10-01"));
	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.addInventory(2, "2022-11-27"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.addInventory(467985719, "2023-04-01"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase4(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.addInventory(1672235345, "2022-07-05"));

		    assertTrue(thrown.getMessage().contains("Book ISBN# is not valid"));
	}
	
	@Test
	void testCase5(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.addInventory(3334, "2021-06-13"));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	

}
