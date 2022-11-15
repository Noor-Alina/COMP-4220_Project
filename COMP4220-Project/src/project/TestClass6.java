package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestClass6 {
    
	EmployeeManagement em = new EmployeeManagement();
	@Test
	void testCase1() throws SQLException {
		assertEquals("No one is working today", em.viewHours().get(0));
	}
	
	@Test
	void testCase2() throws InputException, DatabaseException, SQLException{
		em.setHours(15561, "2022-11-15", "11:00", "15:00");
		assertEquals("Margaret Robin 11:00 15:00 ", em.viewHours().get(0));
	}
}