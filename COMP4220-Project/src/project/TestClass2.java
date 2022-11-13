package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass2 {

		
		BookManagement bm = new BookManagement();

		@Test
		void testCase1() throws InputException, DatabaseException{
			
			assertEquals("reservation made, reservation#56690", bm.reserveInStock(167934082, 4672895710l, 15561, "abc12@uwindsor.ca"));
		}
		
		@Test
		void testCase2() {
			
			InputException thrown = assertThrows(
					InputException.class,
					   () -> bm.reserveInStock(5, 2, "5A7", "abc12@gmail.com"));
	
				assertTrue(thrown.getMessage().contains("Invalid Input"));
		}
		
		@Test
		void testCase3() {
			
			InputException thrown = assertThrows(
					InputException.class,
					   () -> bm.reserveInStock(5, 467985719, 15781, "abc12@uwindsor.ca"));
	
				assertTrue(thrown.getMessage().contains("Invalid Input"));
		}
		
		@Test
		void testCase4(){
			
			DatabaseException thrown = assertThrows(
					DatabaseException.class,
					   () -> bm.reserveInStock(167937080, 4672235783l, 12181, "abc12@uwindsor.ca"));
	
				assertTrue(thrown.getMessage().contains("Input Not Found"));
		}

		@Test
		void testCase5(){

			InputException thrown = assertThrows(
					InputException.class,
					   () -> bm.reserveInStock(167937080, 467985719, 15781, "abc12@gmail.ca"));
	
				assertTrue(thrown.getMessage().contains("Invalid Input"));

		}
}

