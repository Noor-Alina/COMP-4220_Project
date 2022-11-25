package project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

//import jdk.javadoc.internal.doclets.toolkit.taglets.ThrowsTaglet;

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
	 * Getting the estimated arrival date of a book
	 */
	public static String getArrivalDate() {
		 
			LocalDate orderDate = LocalDate.now();
		  
			//adding 14 days to the localDate
			LocalDate arrivalDate = orderDate.plusDays(14);
			String str = "'" + arrivalDate + "'";
			
			return str;
	}
	
	/*
	 * Setting the reservation book period of in stock book
	 */
	public static String getInStockReservationPeriod() {
		
		LocalDate reservationDate = LocalDate.now();
		
		//adding 7 day period for reservation
		LocalDate reservationEndDate = reservationDate.plusDays(7);
		
		String str = "'" + reservationDate + "'" + "--" + "'" +  reservationEndDate + "'";
		
		return str;
	}
	
	/*
	 * Setting the reservation book period of out of stock book
	 */
	public static String getOutOfStockReservationPeriod() {
		
		LocalDate reservationDate = LocalDate.now();
		
		//adding 7 day period for reservation
		LocalDate reservationStartDate = reservationDate.plusDays(14);
		LocalDate reservationEndDate = reservationStartDate.plusDays(7);

		
		String str = "'" + reservationStartDate + "'" + "--" + "'" +  reservationEndDate + "'";
		
		return str;
	}
	
	
	/*
	 * Implementing the function for TestClass1
	 */
	public String placeSpecificOrder(long student_id, long book_isbn, int emp_id, String email) throws InputException, DatabaseException, SQLException{
		
		String outputString ="";
		
		//Checking if the first three inputs are valid
		if(student_id > 999999999 || student_id < 100000001 || book_isbn > 9999999999l || book_isbn < 1000000001 || emp_id > 99999 || emp_id < 10001)
			throw new InputException();
		
		//Checking if the email is valid by checking the email type
		if(email.length() <= 12 || !email.substring(email.length()-12).equals("@uwindsor.ca"))
			throw new InputException();
		
		//Connecting to MySQL database
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
		        Statement exist = connect.createStatement();
		        String sql = "SELECT email FROM studentInfo WHERE student_id = "+ student_id + " AND EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

		        ResultSet rs = exist.executeQuery(sql);
		        
		        //If the inputs exist in the database, insert the data into the placedspecificorder
		        if(rs.next()) 
		        {
		        	//Throwing Exception if email is not verified 
		        	if(!rs.getString(1).equals(email))
		        		throw new DatabaseException();
		        	
		        	
		        	//Connecting to MySQL database
		        	Statement specificOrder = connect.createStatement();
		        	sql = "INSERT into PlacedSpecificOrder(student_id, book_isbn, emp_id, order_date) VALUES(" + student_id + ", " + book_isbn + ", " + emp_id + ", " + getDate() + ")";
		        	int ret = specificOrder.executeUpdate(sql);
		        	
		        	
		        	
		        	//Retrieving the order_id for print out
		        	if(ret == 1) 
		        	{
		        		
		        		Statement ord_id = connect.createStatement();
		        		sql = "SELECT order_id FROM PlacedSpecificOrder WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn +" AND emp_id = " + emp_id;
		        		ResultSet rs2 = ord_id.executeQuery(sql);
		        		
		        		if(rs2.next()) 
		        		{
		        			
		        			outputString = "Order#" + rs2.getString(1) + "\n\nStudent Number: " + student_id +"\n\nE-mail:"+ email + "\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id + "\n\nOrder Date: " + getDate() + "\n\nYour order will arrive on " + getArrivalDate();
		        		}
		        		
		        	}
		        	
		        	//Throwing exception for insertion failure
		        	else 
		        	{
		        		
		        		throw new SQLException();
		        	}
		        }
		        
		        
		        //Throw exception if the inputs don't exist in the database
		        else 
		        {
		        	
		        	throw new DatabaseException();
		        }
		        
				return outputString;
}

        
        //Throw exception if the inputs don't exist in the database
	
	
	/*
	 * Implementing the InputException for any other combinations of input of TestClass2
	 */
	public String placeSpecificOrder(String student_id, long book_isbn, int emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	public String placeSpecificOrder(long student_id, String book_isbn, int emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	public String placeSpecificOrder(long student_id, long book_isbn, String emp_id, String email) throws InputException{
	 
		throw new InputException();
	}
	
	public String placeSpecificOrder(String student_id, String book_isbn, String emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	
	
	/*
	 * Implementing the function for TestClass2
	 */
	public String reserveInStock(long student_id, long book_isbn, int emp_id, String email) throws InputException, DatabaseException, SQLException{
		
		String outputString ="";
		
		//Checking if the first three inputs are valid
		if(student_id > 999999999 || student_id < 100000001 || book_isbn > 9999999999l || book_isbn < 1000000001 || emp_id > 99999 || emp_id < 10001)
			throw new InputException();
		
		//Checking if the email is valid by checking the email type
		if(email.length() <= 12 || !email.substring(email.length()-12).equals("@uwindsor.ca"))
			throw new InputException();
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement exist = connect.createStatement();
        String sql = "SELECT email FROM studentInfo WHERE student_id = "+ student_id + " AND EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

        ResultSet rs = exist.executeQuery(sql);
        
        //If the inputs exist in the database, insert the data into the reservedBook
        if(rs.next()) 
        {
        	
        	//Throwing Exception if email is not verified 
        	if(!rs.getString(1).equals(email))
        		throw new DatabaseException();
        	
        	//Inserting the data into reservedBook
        	Statement insert = connect.createStatement();
        	sql = "INSERT into ReservedBooks (student_id, book_isbn, emp_id, reservedInStock, reserved_date)VALUES (" + student_id + ", " + book_isbn +  ", " + emp_id +  ", " + 1 + ", " + getDate() + ")";
        	int ret = insert.executeUpdate(sql);
        	
        	//Retrieving the reservation_id for print out
        	if(ret == 1) 
        	{
        		
        		Statement res_id = connect.createStatement();
        		sql = "SELECT reservation_id FROM ReservedBooks WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn;
        		ResultSet rs2 = res_id.executeQuery(sql);
        		if(rs2.next()) 
        		{
        			
        			outputString = "Reservation#" + rs2.getString(1) + "\n\nStudent Number: " + student_id +"\n\nE-mail:"+ email + "\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id + "\n\nDate: " + getDate() + "\n\nYour reservation period is " + getInStockReservationPeriod() + "!";
        		}
        		
        		//Changing the sell stock as the book was reserved for selling
            	Statement stockchange = connect.createStatement();
            	sql = "UPDATE bookInfo SET sell_stock = sell_stock - 1 WHERE book_isbn = " + book_isbn + " AND sell_stock > 0";
            	int ret2 = stockchange.executeUpdate(sql);
            	
            	//Throw exception if stock wasn't changed
            	if(ret2 != 1) 
            	{
            		throw new SQLException();
            	}
        	}
        	
        	//Throwing exception for insertion failure
        	else 
        	{
        		
        		throw new SQLException();
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
	 * Implementing the InputException for any other combinations of input of TestClass2
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
	
	
	
	
	/*
	 * Implementing the function for TestClass3
	 */
	public String reserveOutOfStock(long student_id, long book_isbn, int emp_id, String email) throws InputException, DatabaseException, SQLException{
		
		String outputString ="";
		
		//Checking if the first three inputs are valid
		if(student_id > 999999999 || student_id < 100000001 || book_isbn > 9999999999l || book_isbn < 1000000001 || emp_id > 99999 || emp_id < 10001)
			throw new InputException();
		
		//Checking if the email is valid by checking the email type
		if(email.length() <= 12 || !email.substring(email.length()-12).equals("@uwindsor.ca"))
			throw new InputException();
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement exist = connect.createStatement();
        String sql = "SELECT email FROM studentInfo WHERE student_id = "+ student_id + " AND EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

        ResultSet rs = exist.executeQuery(sql);
        
        //If the inputs exist in the database, insert the data into the reservedBook Table
        if(rs.next()) 
        {
        	
        	//Throwing Exception if email is not verified 
        	if(!rs.getString(1).equals(email))
        		throw new DatabaseException();
        	
        	
        	//Inserting the data into reservedBook
        	Statement insert = connect.createStatement();
        	sql = "INSERT into ReservedBooks (student_id, book_isbn, emp_id, reservedInStock, reserved_date)VALUES (" + student_id + ", " + book_isbn +  ", " + emp_id +  ", " + 0 + ", " + getDate() + ")";
        	int ret = insert.executeUpdate(sql);
        	
        	
        	//Add the details
        	Statement addOrderItem = connect.createStatement();
        	long default_quantity = 10;
        	sql = "INSERT INTO OrderInventory (book_isbn, item_quantity, arrival_date) VALUES(" + book_isbn + ", " + default_quantity + ", " + getArrivalDate() + ")";
        	int ret2 = addOrderItem.executeUpdate(sql);
        	
        	
        	//Retrieving the reservation_id for print out
        	if(ret2 == 1) 
        	{
        		
        		Statement res_id = connect.createStatement();
        		sql = "SELECT reservation_id FROM ReservedBooks WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn;
        		ResultSet rs2 = res_id.executeQuery(sql);
        		
        		if(rs2.next())
        		{
        			
        			outputString = "Reservation#" + rs2.getString(1) + "\n\nStudent Number: " + student_id +"\n\nE-mail:"+ email + "\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id + "\n\nDate: " + getDate() + "\n\nYour order will arrive on " + getArrivalDate() + ". Your reservation period is " + getOutOfStockReservationPeriod();
        		}
        		
        	}
        	
        	//Throwing exception for insertion failure
        	else 
        	{
        		
        		throw new SQLException();
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
	 * Implementing the InputException for any other combinations of input of TestClass3
	 */
	
	public String reserveOutOfStock(String student_id, long book_isbn, int emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	public String reserveOutOfStock(long student_id, String book_isbn, int emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	public String reserveOutOfStock(long student_id, long book_isbn, String emp_id, String email) throws InputException{
	 
		throw new InputException();
	}
	
	public String reserveOutOfStock(String student_id, String book_isbn, String emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	
	
	
	/*
	 * Implementing the function for TestClass4
	 */
	public String sell(long student_id, long book_isbn, int emp_id, long card_code) throws InputException, DatabaseException, SQLException{
		
		String outputString ="";
		
		//Checking if the inputs are valid
		if(student_id > 999999999 || student_id < 100000001 || book_isbn > 9999999999l || book_isbn < 1000000001 || emp_id > 99999 || emp_id < 10001 || card_code > 99999999999999l || card_code < 10000000000001l)
			throw new InputException();
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
		Statement exist = connect.createStatement();
		String sql = "SELECT * FROM studentInfo WHERE student_id = "+ student_id + " AND EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

        ResultSet rs = exist.executeQuery(sql);
        
        //If the first three inputs exist in the database, inserting into the soldBook database
        if(rs.next()) 
        {
        	
        	//Checking if a reservation was done
        	Statement exist2 = connect.createStatement();
        	sql = "SELECT reservation_id from ReservedBooks where student_id ="+ student_id + " AND book_isbn = "+ book_isbn;
        	
        	ResultSet rs2 = exist2.executeQuery(sql);
        	
        	//If reservation exist, don't change the sell stock of the book
    		if(rs2.next()) 
    		{
    			
    			int res_id = rs2.getInt(1);
    			//Delete the data from reserveBook
            	Statement delete = connect.createStatement();
            	sql = "DELETE FROM ReservedBooks WHERE reservation_id=" + res_id;
            	int ret2 = delete.executeUpdate(sql);
            	
            	//Entry deleted from reservedBooks
            	if(ret2 == 1) {}
                
            	//Throwing exception for deletion failure
            	else 
            	{
            		
            		throw new SQLException();
            	}
    		}
    		
    		//Decrement the sell stock of the book if the reservation was not done
    		else 
    		{
    			
    			//Changing the sell stock as the book was reserved for selling
            	Statement stockchange = connect.createStatement();
            	sql = "UPDATE bookInfo SET sell_stock = sell_stock - 1 WHERE book_isbn = " + book_isbn + " AND sell_stock > 0";
            	int ret2 = stockchange.executeUpdate(sql);
            	
            	//Throw exception if stock wasn't changed
            	if(ret2 != 1) 
            	{
            		throw new SQLException();
            	}
    		}
    		
        	//Taking the last4 digits of the card
        	String last4digitString = Long.toString(card_code).substring(Long.toString(card_code).length() - 4);
        	
			//Inserting the data into soldBook
        	Statement insert = connect.createStatement();
        	sql = "INSERT into SoldBooks (student_id, book_isbn, emp_id, payment_id, purchase_date)VALUES (" + student_id + ", " + book_isbn +  ", " + emp_id +  ", " + last4digitString + ", " + getDate() + ")";
        	int ret = insert.executeUpdate(sql);
        	
        	//Retrieving the purchase_id for print out
        	if(ret == 1) 
        	{
        		
        		Statement pur_id = connect.createStatement();
        		sql = "SELECT purchase_id FROM SoldBooks WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn;
        		ResultSet rs3 = pur_id.executeQuery(sql);
        		if(rs3.next()) 
        		{
        			
        			outputString = "Order#" + rs3.getString(1) + "\n\nStudent Number: " + student_id +"\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id + "\n\nCard Code: **********" + last4digitString + "\n\nDate: " + getDate();
        		}	
        	}
        	
        	//Throwing exception for insertion failure
        	else 
        	{
        		
        		throw new SQLException();
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
	 * Implementing the InputException for any other combinations of input of TestClass2
	 */
	public String sell(String student_id, long book_isbn, int emp_id, long card_code) throws InputException{
		 
		throw new InputException();
	}
	
	public String sell(long student_id, String book_isbn, int emp_id, long card_code) throws InputException{
		 
		throw new InputException();
	}
	
	public String sell(long student_id, long book_isbn, String emp_id, long card_code) throws InputException{
	 
		throw new InputException();
	}
	
	public String sell(long student_id, long book_isbn, int emp_id, String card_code) throws InputException{
		 
		throw new InputException();
	}
	
	public String sell(String student_id, String book_isbn, String emp_id, String email) throws InputException{
		 
		throw new InputException();
	}
	
	/*
	 * Implementing the function for TestClass8
	 */
	public String placedForOrder(long book_isbn, int emp_id) throws InputException, SQLException, DatabaseException {
		
		String outputString ="";
		
		//Checking if the inputs are valid
		if(book_isbn > 9999999999l || book_isbn < 1000000001 || emp_id > 99999 || emp_id < 10001)
			throw new InputException();
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
		Statement exist = connect.createStatement();
		String sql = "SELECT book_isbn FROM bookInfo WHERE book_isbn = "+ book_isbn + " AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

		ResultSet rs = exist.executeQuery(sql);
		        
		//If the first two inputs exist in the database, inserting into the orderInventory database
		if(rs.next()) {
			
			//Add the details
        	Statement addOrderItem = connect.createStatement();
        	long default_quantity = 10;
        	sql = "INSERT INTO OrderInventory (book_isbn, item_quantity, arrival_date) VALUES(" + book_isbn + ", " + default_quantity + ", " + getArrivalDate() + ")";
        	int ret2 = addOrderItem.executeUpdate(sql);
        	
        	
        	//Outputting the insertion success prompt
        	if(ret2 == 1) 
        	{
        		
        		outputString = "Book order added to the order inventory";
        	}
        	
        	//Outputting the insertion failure prompt
        	else {
        		
        		throw new SQLException();
        	}
        		
		}
		
		else {
			
			throw new DatabaseException();
		}
		
		
		return outputString;
	}
	
	/*
	 * Implementing the InputException for any other combinations of input of TestClass8
	 */
	public String placedForOrder(String book_isbn, int emp_id) throws InputException {
		
		throw new InputException();
	}
	
	public String placedForOrder(long book_isbn, String emp_id) throws InputException {
		
		throw new InputException();
	}
	
	public String placedForOrder(String book_isbn, String emp_id) throws InputException {
		
		throw new InputException();
	}
	
	/*
	 * Implementing the function for TestClass7
	 */
	public String search(long book_isbn) throws InputException, SQLException, DatabaseException {
		
		String outputString ="";
		
		//Checking if the inputs are valid
		if(book_isbn > 9999999999l || book_isbn < 1000000001)
			throw new InputException();
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
		Statement exist = connect.createStatement();
		String sql = "SELECT * FROM bookInfo WHERE book_isbn = "+ book_isbn;

		ResultSet rs = exist.executeQuery(sql);
		        
		//If the first two inputs exist in the database, inserting into the orderInventory database
		 
        while(rs.next()) {
        	
        	for (int i = 1; i <= 8; i++) {
        		
        		if (i != 8) {
        		outputString += rs.getString(i) + ", ";
        		}
        		
        		else {
        			outputString += rs.getString(i);
        		}
        		
        	}
        	
        }
        
        if (outputString.isEmpty())
        	throw new DatabaseException();
        	
		return outputString;
	}
	
	/*
	 * Implementing the InputException for any other combinations of input of TestClass7
	 */
	public String search(String book_isbn) throws InputException{
		
		throw new InputException();
	}
}