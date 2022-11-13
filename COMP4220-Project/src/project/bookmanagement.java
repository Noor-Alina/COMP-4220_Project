package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class BookManagement extends Throwable{
	
	/*
	 * Getting the current date
	 */
	public static String getDate() {
		
		  LocalDate date = LocalDate.now();
	      String str = "'" + date + "'";
	      
	      return str;
	}
	
	/*
	 * Implementing the function for TestClass2
	 */
	public String reserveInStock(long student_id, long book_isbn, int emp_id, String email) throws InputException, DatabaseException, SQLException{
		
		String outputString ="";
		
		//Checking if the first three inputs are valid
		if(student_id > 999999999 || student_id < 100000001 || book_isbn > 999999999 || book_isbn < 1000000001 || emp_id > 99999 || emp_id < 10001)
			throw new InputException();
		
		//Checking if the email is valid by checking the email type
		if(email.length() <= 12 || !email.substring(email.length()-12).equals("@uwindsor.ca"))
			throw new InputException();
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement exist = connect.createStatement();
        String sql = "SELECT email FROM studentInfo WHERE EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

        ResultSet rs = exist.executeQuery(sql);
        
        //If the inputs exist in the database, insert the data into the reservedBook
        if(rs.next()) {
        	
        	if(!rs.getString(1).equals(email))
        		throw new DatabaseException();
        	
        	Statement insert = connect.createStatement();
        	sql = "INSERT into ReservedBooks (student_id, book_isbn, emp_id, reservedInStock, reserved_date)VALUES (" + student_id + ", " + book_isbn +  ", " + emp_id +  ", " + 1 + ", " + getDate() + ")";
        	int ret = insert.executeUpdate(sql);
        	if(ret == 1) {
        		
        		Statement res_id = connect.createStatement();
        		sql = "SELECT reservation_id FROM ReservedBooks WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn;
        		ResultSet rs2 = res_id.executeQuery(sql);
        		if(rs2.next()) {
        			
        			outputString = "Reservation#" + rs2.getString(1) + "\n\nStudent Number: " + student_id +"\n\nE-mail:"+ email + "\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id + "\n\nDate: " + getDate() + "\n\nYour reservation period is 7 days from " + getDate() + "!";
        		}
        	}
        }
        
        //Throw exception
        else {
        	
        	throw new DatabaseException();
        }
		return outputString;
	}
	
	
	/*
	 * Implementing the InputException for any other combinations of input
	 */
	public String reserveInStock(String student_id, long book_isbn, int emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	public String reserveInStock(long student_id, String book_isbn, int emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	public String reserveInStock(long student_id, long book_isbn, String emp_id, String email) throws InputException{
	 
		throw new InputException();
	}
	
	public String reserveInStock(String student_id, String book_isbn, String emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
}