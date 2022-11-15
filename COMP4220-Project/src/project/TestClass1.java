package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass1 {

	BookManagement bm = new BookManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		
		assertEquals("Order#30001\n\nStudent Number: 167934082\n\nE-mail:abc12@uwindsor.ca\n\nISBN-10: 1672895710\n\nEmployee Number: 15561\n\nOrder Date: '2022-11-14'\n\nYour order will arrive on '2022-11-28'",bm.placeSpecificOrder(167934082, 1672895710, 15561, "abc12@uwindsor.ca"));
	}
	
	@Test
	void testCase2() {
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.placeSpecificOrder(5, 2, "5A7", "abc12@gmail.com"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3() {
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.placeSpecificOrder(5, 467985719, 15781, "abc12@uwindsor.ca"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase4(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.placeSpecificOrder(167937080, 1672235783, 12181, "abc12@uwindsor.ca"));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
}