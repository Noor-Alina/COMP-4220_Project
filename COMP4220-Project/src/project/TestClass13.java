
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
