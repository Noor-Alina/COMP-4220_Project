
package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass13 {
	 LibraryManagement lm = new LibraryManagement();
	 BookManagement bm = new BookManagement();
	 
	@Test
	void testCase1() throws SQLException {
		assertEquals("There are no requests at this time", lm.viewLibraryRequests().get(0));
	}
	
	@Test
	void testCase2() throws InputException, DatabaseException, SQLException{
		bm.addInventory(1672895710);
		assertEquals("1 1672895710 Pending ", lm.viewLibraryRequests().get(0));
	}

}
