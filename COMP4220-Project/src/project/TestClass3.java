package project;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TestClass3 {

	BookManagement bm = new BookManagement();
	
	@Test
	void testCase1() throws InputException, DatabaseException, SQLException{
		
		assertEquals("Reservation#50002\n\nStudent Number: 167934082\n\nE-mail:abc12@uwindsor.ca\n\nISBN-10: 1672895710\n\nEmployee Number: 15561\n\nDate: '2022-11-14'\n\nYour order will arrive on '2022-11-28'. Your reservation period is '2022-11-28'--'2022-12-05'", bm.reserveOutOfStock(167934082, 1672895710, 15561, "abc12@uwindsor.ca"));

	}
	
	@Test
	void testCase2(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.reserveOutOfStock(5, 2, "5A7", "abc12@gmail.com"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase3(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.reserveOutOfStock(5, 467985719, 15781, "abc12@uwindsor.ca"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
	
	@Test
	void testCase4(){
		
		DatabaseException thrown = assertThrows(
				DatabaseException.class,
		           () -> bm.reserveOutOfStock(167937082, 1672235719, 12181, "abc12@uwindsor.ca"));

		    assertTrue(thrown.getMessage().contains("Input Not Found"));
	}
	
	@Test
	void testCase5(){
		
		InputException thrown = assertThrows(
				InputException.class,
		           () -> bm.reserveOutOfStock(167937154, 1672235345, 16475, "abcd@gmail.com"));

		    assertTrue(thrown.getMessage().contains("Invalid Input"));
	}
}
