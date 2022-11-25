package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class trackRequests {
	@Test
	void testCase1() throws SQLException {
		assertEquals("There are no requests at this time", libraryRequests().get(0));
	}
	
	@Test
	void testCase2() throws InputException, DatabaseException, SQLException{
		createRequest(1,1781616499, 15561);
		assertEquals("1: 1781616499: 15561: 'Pending' ", libraryRequests.get(0));
	}

}
