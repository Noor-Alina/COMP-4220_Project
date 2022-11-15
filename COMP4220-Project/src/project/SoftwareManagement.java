package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class SoftwareManagement extends Throwable{

	public static void main(String args[]) throws SQLException, InputException, DatabaseException{
		
		long student_id =167934082;
        long book_isbn = 1672895710;
        int emp_id = 15561;
		
		/*
		 * 
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement st = connect.createStatement();
        long student_id =167934082;
        long book_isbn = 1672895710;
        int emp_id = 15561;
        
        String email = "abc12@uwindsor.ca";
        String sql = "SELECT email FROM studentInfo WHERE student_id = "+ student_id + " AND EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";
        ResultSet rs = st.executeQuery(sql);
        
        //If the inputs exist in the database, insert the data into the placedspecificorder
        if(rs.next()) 
        {
        	//Throwing Exception if email is not verified 
        	if(!rs.getString(1).equals(email))
        		throw new DatabaseException();
        	
        	
        	//Connecting to MySQL database
        	Connection insert = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        	Statement specificOrder = connect.createStatement();
        	sql = "INSERT into PlacedSpecificOrder(student_id, book_isbn, emp_id, order_date) VALUES(" + student_id + ", " + book_isbn + ", " + emp_id + ", " + getDate() + ")";
        	int ret = specificOrder.executeUpdate(sql);
        	
        	
        	
        	//Retrieving the order_id for print out
        	if(ret == 1) 
        	{
        		
        		Statement ord_id = connect.createStatement();
        		sql = "SELECT order_id FROM PlacedSpecificOrder WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn +"AND emp_id = " + emp_id;
        		ResultSet rs2 = ord_id.executeQuery(sql);
        		if(rs2.next()) 
        		{
        			
        			String outputString = "Order# " + rs2.getString(1) + "\n\nStudent Number: " + student_id +"\n\nE-mail:"+ email + "\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id + "\n\nOrder Date: " + getDate() + "\n\nYour Order will arrive on " + getArrivalDate() +".";
        			System.out.println(outputString);
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
    
}

        
        /*String email = "abc12@uwindsor.ca";
        String sql = "SELECT email FROM studentInfo WHERE student_id = "+ student_id + " AND EXISTS (SELECT student_id from studentInfo where student_id ="+ student_id + ") AND EXISTS (SELECT book_isbn from bookInfo WHERE book_isbn = "+ book_isbn + ") AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

        ResultSet rs = st.executeQuery(sql);
        
        if(rs.next()) {
        	
        	if(!rs.getString(1).equals(email)) {
        		System.out.println(rs.getString(1));
        		System.out.println(email);
        	}
        	
        	Statement insert = connect.createStatement();
        	sql = "INSERT into ReservedBooks (student_id, book_isbn, emp_id, reservedInStock, reserved_date)VALUES (" + student_id + ", " + book_isbn +  ", " + emp_id +  ", " + 1 + ", " + getDate() + ")";
        	int ret = insert.executeUpdate(sql);
        	if(ret == 1) {
        		
        		Statement res_id = connect.createStatement();
        		sql = "SELECT reservation_id FROM ReservedBooks WHERE student_id = " + student_id +" AND book_isbn = " + book_isbn;
        		ResultSet rs2 = res_id.executeQuery(sql);
        		if(rs2.next()) {
        			
        			 String out = "Reservation#" + rs2.getString(1) + "\n\nStudent Number: " + student_id +"\n\nE-mail:"+ email + "\n\nISBN-10: " + book_isbn + "\n\nEmployee Number: " + emp_id + "\n\nDate: " + getDate() + "\n\nYour reservation period is 7 days from " + getDate() + "!";
        			 System.out.println(out);
        		}
        	}
        	Statement stockchange = connect.createStatement();
        	sql = "UPDATE bookInfo SET sell_stock = sell_stock - 1 WHERE book_isbn = " + book_isbn + " AND sell_stock > 0";
        	int ret2 = stockchange.executeUpdate(sql);
        	if(ret2 != 1) {
        		throw new SQLException();
        	}
        }
        
        else {
        	
        	System.out.println("error");
        }*/
        
        BookManagement bManagement = new BookManagement();
        
        //System.out.println(bManagement.reserveInStock(student_id, book_isbn, emp_id, "abc12@uwindsor.ca"));
        
        //System.out.println(bManagement.sell(student_id, book_isbn, emp_id, 12345678910111l));
        
        System.out.println(bManagement.placeSpecificOrder(student_id, book_isbn, emp_id, "abc12@uwindsor.ca"));
        
    	//System.out.println(bManagement.reserveOutOfStock(student_id, book_isbn, emp_id, "abc12@uwindsor.ca"));

        
	}
	
	public static String getDate() {
		
		  LocalDate date = LocalDate.now();
	      String str = "'" + date + "'";
	      
	      return str;
	}
	
	public static String getArrivalDate() {
		 
		LocalDate orderDate = LocalDate.now();
	  
		//adding 14 days to the localDate
		LocalDate arrivalDate = orderDate.plusDays(14);
		String str = "'" + arrivalDate + "'";
		
		return str;
}
	
}
