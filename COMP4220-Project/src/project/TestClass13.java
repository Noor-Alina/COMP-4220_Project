<<<<<<< Updated upstream
package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass13 {
	@Test
	void testCase1() throws SQLException {
		assertEquals("There are no requests at this time", viewLibraryRequests().get(0));
	}
	
	@Test
	void testCase2() throws InputException, DatabaseException, SQLException{
		createRequest(1,1781616499);
		assertEquals("1: 1781616499: 'Pending' ", viewLibraryRequests.get(0));
	}

}
=======
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
	
	
}
>>>>>>> Stashed changes
