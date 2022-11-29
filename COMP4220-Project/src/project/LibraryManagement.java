package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.text.SimpleDateFormat;  
import java.util.Date;

import jdk.javadoc.internal.doclets.toolkit.taglets.ThrowsTaglet;  

public class LibraryManagement extends Throwable{
	
	/*
	 * Getting the current date
	 */
	public static String getDate() {
		
		  LocalDate date = LocalDate.now();
	      String str = "'" + date + "'";
	      
	      return str;
	}
	
	/*
	 * Getting the estimated arrival date of a book
	 */
	public static String getDueDate() {
		 
			LocalDate loanDate = LocalDate.now();
		  
			//adding 7 days to the localDate
			LocalDate dueDate = loanDate.plusDays(7);
			String str = "'" + dueDate + "'";
			
			return str;
	}
	
	/*
	 * Setting the loaned book period
	 */
	public static String getLoanPeriod() {
		
		LocalDate loanDate = LocalDate.now();
		
		//adding 7 day period for reservation
		LocalDate dueDate = loanDate.plusDays(7);
		
		String str = "'" + loanDate + "'" + "--" + "'" +  dueDate + "'";
		
		return str;
	}
	
	/*
	 * Checking if a date is 6 months older than the current date
	 */
	public static boolean is6MonthOld(String lastLoanedDate) {
		
		LocalDate currentDate = LocalDate.now();
		LocalDate currentDateMinus6Months = currentDate.minusMonths(6);
		
		String[] numberStrings = lastLoanedDate.split("-");
		LocalDate date1 = LocalDate.of(Integer.parseInt(numberStrings[0]), Integer.parseInt(numberStrings[1]), Integer.parseInt(numberStrings[2]));
		
	    if (date1.isBefore(currentDateMinus6Months)){
	    	
	    	return true;
	    }
	    
	    else {
	    	
	    	return false;
	    }
	}
	
	public ArrayList<String> viewLibraryRequests() throws SQLException {
		
		ArrayList<String> output = new ArrayList<String>();
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement exist = connect.createStatement();
        
        //checking if there is a request from library
        String sql = "SELECT * FROM LibraryRequest L WHERE L.request_status = 'Pending' ";

        ResultSet rs = exist.executeQuery(sql);
        
        while(rs.next()) {
        	String recordstatus = "";
        	for (int i = 1; i <= 3; i++) {
        		recordstatus += rs.getString(i) + " ";      		
        	}
        	output.add(recordstatus);
        }
        
        if (output.isEmpty())
        	output.add("There are no requests at this time");
		return output;
	}

	/*
	 * Implementing the function for TestClass17
	 */
	public String loan(long student_id, long book_isbn, int emp_id) throws InputException, DatabaseException, SQLException{
		
		String outputString ="";
		
		//Checking if the inputs are valid
		if(student_id > 999999999 || student_id < 100000001 || book_isbn > 9999999999l || book_isbn < 1000000001 || emp_id > 99999 || emp_id < 10001)
			throw new InputException();
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
		Statement exist = connect.createStatement();
		String sql = "SELECT loan_stock FROM bookInfo WHERE book_isbn = "+ book_isbn + " AND EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

        ResultSet rs = exist.executeQuery(sql);
        
        //If the first three inputs exist in the database, inserting into the loanBook database
        if(rs.next()) 
        {
        	
        	//If the loan stock is more than 0, loan the book
        	if(rs.getInt(1) > 0) {
        		
        		//Changing the loan stock as the book is being loaned
            	Statement stockchange = connect.createStatement();
            	sql = "UPDATE bookInfo SET loan_stock = loan_stock - 1 WHERE book_isbn = " + book_isbn + " AND loan_stock > 0";
            	int ret1 = stockchange.executeUpdate(sql);
            	
            	//Throw exception if stock wasn't changed
            	if(ret1 != 1) 
            	{
            		throw new SQLException();
            	}
            	
            	//Inserting the data into LoanedBooks
            	Statement insert = connect.createStatement();
            	sql = "INSERT into LoanedBooks (student_id, book_isbn, emp_id, loan_date)VALUES (" + student_id + ", " + book_isbn +  ", " + emp_id +  ", " + getDate() + ")";
            	int ret = insert.executeUpdate(sql);
            	
            	//Updating the lastLoandedDate for the book
            	Statement lastLoaned = connect.createStatement();
        		sql = "SELECT book_isbn FROM LibraryBookCheck WHERE book_isbn = " + book_isbn;
        		ResultSet rs2 = lastLoaned.executeQuery(sql);
            	
        		//If the book was already loaned, update the last loaned date for the book
        		if(rs2.next()) {
        			
        			//Changing the loan stock as the book is being loaned
                	Statement lastDateChange = connect.createStatement();
                	sql = "UPDATE LibraryBookCheck SET lastloaned_date = " + getDate() + " WHERE book_isbn = " + book_isbn;
                	int ret2 = lastDateChange.executeUpdate(sql);
                	
                	//Throw exception if date wasn't changed
                	if(ret2 != 1) 
                	{
                		throw new SQLException();
                	}
        		}
        			
        		//If the book wasn't loaned, insert the last loaned date for the book
        		else {
        			
        			//Inserting the data into LoanedBooks
                	Statement insert2 = connect.createStatement();
                	sql = "INSERT into LibraryBookCheck (book_isbn, lastloaned_date)VALUES (" + book_isbn +  ", " + getDate() + ")";
                	int ret2 = insert2.executeUpdate(sql);
                	
                	if(ret2 != 1) {
                		
                		throw new SQLException();
                	}
        		}
        		
            	//Retrieving the loan_id for print out
            	if(ret == 1) 
            	{
            		
            		Statement loan_id = connect.createStatement();
            		sql = "SELECT loan_id FROM LoanedBooks WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn;
            		ResultSet rs3 = loan_id.executeQuery(sql);
            		if(rs3.next()) 
            		{
            			
            			outputString = "Loan#" + rs3.getString(1) + "\n\nStudent Number: " + student_id +"\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id +  "\n\nDate: " + getDate() + "\n\nYour loan period is " + getLoanPeriod() + "!";
            		}	
            	}
            	
            	//Throwing exception for insertion failure
            	else 
            	{
            		
            		throw new SQLException();
            	}
        	}
        	
        	else {
        		
        		outputString = "Book is out of stock";
        	}
        }
        
        //Throw exception if the inputs don't exist in the database
        else 
        {
        	
        	throw new DatabaseException();
        }
		
		return outputString;
	}

	/*
	 * Implementing the InputException for any other combinations of input of TestClass17
	 */
	public String loan(String student_id, long book_isbn, int emp_id) throws InputException{
		 
		throw new InputException();
	}
	
	public String loan(long student_id, String book_isbn, int emp_id) throws InputException{
		 
		throw new InputException();
	}
	
	public String loan(long student_id, long book_isbn, String emp_id) throws InputException{
	 
		throw new InputException();
	}
	
	public String loan(String student_id, String book_isbn, String emp_id) throws InputException{
		 
		throw new InputException();
	}

	
	
	/*
	 * Implementing the function for TestClass9 and TestClass16
	 */
	public String checkAvailability(long book_isbn) throws InputException, DatabaseException, SQLException {
		
		String outputString = "";
		
		//Checking if the input is valid
		if(book_isbn > 9999999999l || book_isbn < 1000000001)
			throw new InputException();
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
		Statement lastLoaned = connect.createStatement();
		String sql = "SELECT lastLoaned_date FROM LibraryBookCheck WHERE book_isbn = " + book_isbn;
		ResultSet rs = lastLoaned.executeQuery(sql);
    	
		//If the book was already loaned, checking the last loaned date
		if(rs.next()) {
			
			if(is6MonthOld(rs.getString(1))){
				
				outputString = "Book with ISBN " + book_isbn + " has not been loaned within the last 6 months and is available for selling";
			}
			
			//If the book was loaned within 6 months
			else {
				
				outputString = "Book with ISBN " + book_isbn + " has been loaned within the last 6 months and is not available for selling";
			}
			
		}
		
		//If the book was never loaned
		else {
			
			//Check if the book is in our database and being loaned
			Statement beingLoaned = connect.createStatement();
			sql = "SELECT loan_stock FROM BookInfo WHERE book_isbn = " + book_isbn + " AND loan_stock > 0";
			ResultSet rs2 = beingLoaned.executeQuery(sql);
			
			if(rs2.next()) {
				
				outputString = "Book with ISBN " + book_isbn + " has not been loaned within the last 6 months and is available for selling";
			}
			
			else {
				
				throw new DatabaseException();
			}
		}
		
		return outputString;
	}
	
	/*
	 * Implementing the InputException for any other combinations of input of TestClass9 and TestClass16
	 */
	public String checkAvailability(String book_isbn) throws InputException{

		throw new InputException();
	}
	
}