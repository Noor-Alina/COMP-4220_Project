package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import java.util.*;

public class SoftwareManagement extends Throwable{

	public static void main(String args[]) throws SQLException, InputException, DatabaseException{
		
		//Adding the BookManagement class variable for using the BookManagement method
		BookManagement bm = new BookManagement();
		
		//Declaring variables for taking user input
		long student_id;
        long book_isbn;
        int emp_id;
        long card_code;
        String email;
		
		
		System.out.println("Choose one of the options below:\n\n1) Place a specific order\n\n2) Reserve a Book\n\n3) Purchase a Book\n\n");
		Scanner in = new Scanner(System.in);
		int userIn = Integer.parseInt(in.next());
		
		//User wants to place a specific order
		if(userIn == 1) {
			
			//Taking the student number of the student
			System.out.println("Please enter the student number of the student\n\n");
			student_id = Long.parseLong(in.next());
			
			//Taking the book_isbn of the book
			System.out.println("Please enter the ISBN-10 of the book\n\n");
			book_isbn = Long.parseLong(in.next());
			
			//Taking the employee number of the employee
			System.out.println("Please enter the employee number of the employee\n\n");
			emp_id = Integer.parseInt(in.next());
			
			//Taking the email of the student
			System.out.println("Please enter the email of the student\n\n\n");
			email = in.next();
			
			//Print the receipt
			System.out.println(bm.placeSpecificOrder(student_id, book_isbn, emp_id, email));
		}
		
		//User wants to reserve a book
		else if(userIn == 2){
			
			//Taking the student number of the student
			System.out.println("Please enter the student number of the student\n\n");
			student_id = Long.parseLong(in.next());
			
			//Taking the book_isbn of the book
			System.out.println("Please enter the ISBN-10 of the book\n\n");
			book_isbn = Long.parseLong(in.next());
			
			//Taking the employee number of the employee
			System.out.println("Please enter the employee number of the employee\n\n");
			emp_id = Integer.parseInt(in.next());
			
			//Taking the email of the student
			System.out.println("Please enter the email of the student\n\n\n");
			email = in.next();
			
			//Connecting to the database
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
	        Statement st = connect.createStatement();
	        
	        String sql = "SELECT sell_stock FROM bookInfo WHERE book_isbn = "+ book_isbn;
	        ResultSet rs = st.executeQuery(sql);
	        
	        //Checking the sell stock of the book
	        if(rs.next()) {
	        	
	        	int stock = rs.getInt(1);
	        	
	        	//The book is in stock, so we can use the reserveInStock method
	        	if(stock > 0) {
	        		
	        		System.out.println(bm.reserveInStock(student_id, book_isbn, emp_id, email));
	        	}
	        	
	        	//The book is out of stock, so we can use the reserveOutOfStock method
	        	else {
	        		
	        		System.out.println(bm.reserveOutOfStock(student_id, book_isbn, emp_id, email));
	        	}
	        }
	        
	        else {
	        	
	        	throw new SQLException();
	        }
	        
		}
		
		//User wants to sell a book
		else if(userIn == 3) {
			
			//Taking the student number of the student
			System.out.println("Please enter the student number of the student\n\n");
			student_id = Long.parseLong(in.next());
			
			//Taking the book_isbn of the book
			System.out.println("Please enter the ISBN-10 of the book\n\n");
			book_isbn = Long.parseLong(in.next());
			
			//Taking the employee number of the employee
			System.out.println("Please enter the employee number of the employee\n\n");
			emp_id = Integer.parseInt(in.next());
			
			//Taking the card code of the student
			System.out.println("Please enter the card number of the student\n\n");
			card_code = Long.parseLong(in.next());
			
			//Print the receipt
			System.out.println(bm.sell(student_id, book_isbn, emp_id, card_code));
		}
	}
}