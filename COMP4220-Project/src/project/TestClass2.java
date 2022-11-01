package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass2 {

		
		SoftwareManagement sm = new SoftwareManagement();

		@Test
		void testCase1() throws InputException, DatabaseException{
			
			assertEquals("reservation made, reservation#56690", sm.reserve(167934082, 4672895719, 15561, "abc12@uwindsor.ca"));
		}
		
		@Test
		void testCase2() {
			
			InputException thrown = assertThrows(
					InputException.class,
					   () -> sm.reserve(5, 2, "5A7", "abc12@gmail.com"));
	
				assertTrue(thrown.getMessage().contains("Invalid Input"));
		}
		
		@Test
		void testCase3() {
			
			InputException thrown = assertThrows(
					InputException.class,
					   () -> sm.reserve(5, 467985719, 15781, "abc12@uwindsor.ca"));
	
				assertTrue(thrown.getMessage().contains("Invalid Input"));
		}
		
		@Test
		void testCase4(){
			
			DatabaseException thrown = assertThrows(
					DatabaseException.class,
					   () -> sm.reserve(167937080, 4672235783, 12181, "abc12@uwindsor.ca"));
	
				assertTrue(thrown.getMessage().contains("Input Not Found"));
		}

		@Test
		void testCase5(){

			InputException thrown = assertThrows(
					InputException.class,
					   () -> sm.reserve(167937080, 467985719, 15781, "abc12@gmail.ca"));
	
				assertTrue(thrown.getMessage().contains("Invalid Input"));

		}
}

