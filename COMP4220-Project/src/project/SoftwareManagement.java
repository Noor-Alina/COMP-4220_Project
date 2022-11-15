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
		EmployeeManagement em = new EmployeeManagement(); 
		
		//Declaring variables for taking user input
		long student_id;
        long book_isbn;
        int emp_id;
        long card_code;
        String email;
        String work_date;
        String starting_time;
        String ending_time;
        int admin_id;
        
        final int adminid = 101;
		
		
		System.out.println("Choose one of the options below:\n\n1) Place a specific order\n\n2) Reserve a Book\n\n3) Purchase a Book\n\n4) Set Employee Hours\n\n5) View Today's Employee Working Hours\n\n6) Search for a book\n\n7) Add Book to Order\n\n0)- EXIT");
		Scanner in = new Scanner(System.in);
		int userIn = Integer.parseInt(in.next());
		
		while (userIn != 0) {
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
		
		else if (userIn == 4) {
			
			System.out.println("Please enter the Admin ID, 0 to EXIT\n\n");
			admin_id = Integer.parseInt(in.next());
			
			while (admin_id != 0){
				
				if (admin_id == 101) {
					
					System.out.println("Please enter the employee ID\n\n");
					emp_id = Integer.parseInt(in.next());
				
				
					System.out.println("Please enter the date for which to set hours\n\n");
					work_date = in.next();
					
					System.out.println("Please enter the employee's shift starting time\n\n");
					starting_time = in.next();
					
					System.out.println("Please enter the employee's shift ending time\n\n");
					ending_time = in.next();
					
					System.out.println(em.setHours(emp_id, work_date, starting_time, ending_time));
					
					System.out.println("Please enter the Admin ID, 0 to EXIT\n\n");
					admin_id = Integer.parseInt(in.next());
				}
				
				else {
					
					System.out.println("Please enter the Admin ID, 0 to EXIT\n\n");
					admin_id = Integer.parseInt(in.next());
				}
			} 
		}
		
		else if (userIn == 5) {
			
			System.out.println(em.viewHours());
		}
		
		else if (userIn == 6){
			
			//Taking the book_isbn of the book
			System.out.println("Please enter the ISBN-10 of the book you would like to search\n\n");
			book_isbn = Long.parseLong(in.next());
			
			//call search function
			System.out.println(bm.search(book_isbn));
		}
		
		else if (userIn == 7) {
			
			System.out.println("Please enter the Admin ID, 0 to EXIT\n\n");
			admin_id = Integer.parseInt(in.next());
			
			while (admin_id != 0){
			
				if (admin_id == 101) {
					//Taking the book_isbn of the book
					System.out.println("Please enter the ISBN-10 of the book\n\n");
					book_isbn = Long.parseLong(in.next());
					
					System.out.println("Please enter the employee ID\n\n");
					emp_id = Integer.parseInt(in.next());
					
					System.out.println(bm.placedForOrder(book_isbn, emp_id));
					
					System.out.println("Please enter the Admin ID, 0 to EXIT\n\n");
					admin_id = Integer.parseInt(in.next());
				}
				
				else {
					System.out.println("Please enter the Admin ID, 0 to EXIT\n\n");
					admin_id = Integer.parseInt(in.next());
				}
			} 
	
			System.out.println("EXITING Set Hours\n\n");				
		}
		
		System.out.println("Choose one of the options below:\n\n1) Place a specific order\n\n2) Reserve a Book\n\n3) Purchase a Book\n\n4) Set Employee Hours\n\n5) View Today's Employee Working Hours\n\n6) Search for a book\n\n7) Add Book to Order\n\n0)- EXIT");
		userIn = Integer.parseInt(in.next());	
		}	
	}
}