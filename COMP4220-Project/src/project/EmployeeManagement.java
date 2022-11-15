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

public class EmployeeManagement extends Throwable{
	
	/*
	 * Getting the current date
	 */
	public static String getDate() {
		
		  LocalDate date = LocalDate.now();
	      String str = "'" + date + "'";
	      
	      return str;
	}
	
	/*
	 * Implementing the function for TestClass5
	 */
	public String setHours(int emp_id, String work_date, String starting_time, String ending_time) throws InputException, SQLException, DatabaseException {
	
		String outputString ="";
		
		//Checking if the first input are valid
		if(emp_id > 99999 || emp_id < 10001)
			throw new InputException();
		
		String year = work_date.substring(0, 4);
		
		if (Integer.parseInt(year) != 2022) {
			throw new InputException();
		}
		
		boolean valid;
		
		try {
            LocalDate.parse(work_date,
                    DateTimeFormatter.ofPattern("uuuu-MM-dd")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
            valid = true;

        } catch (DateTimeParseException e) {
			throw new InputException();
        }
		
		int start = Integer.parseInt(starting_time.substring(0, 2));
		int end = Integer.parseInt(ending_time.substring(0, 2));
		
		if (start < 8 || start > 17 || end < 8 || end > 17 || start >= end) {
			throw new InputException();
		}
		
		//Connecting to MySQL database
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement exist = connect.createStatement();
        
        //checking if employee already working that day
        String sql = "SELECT * FROM employeeManagement WHERE emp_id = "+ emp_id + " AND work_date = " + work_date + " AND EXISTS (SELECT emp_id from employeeInfo WHERE emp_id = "+ emp_id + ")";

        ResultSet rs = exist.executeQuery(sql);
        
        //employee already working that day
        if(rs.next()) {
        	
        	outputString = "Employee is already scheduled to work";
        }
        
        else {
        	Statement specificOrder = connect.createStatement();
        	sql = "INSERT into employeeManagement(emp_id, work_date, starting_time, ending_time) VALUES(" + emp_id + ", '" + work_date + "', '" + starting_time + "', '" + ending_time + "')";
        	int ret = specificOrder.executeUpdate(sql);
        	
        	if (ret == 1) {
        		outputString = "Employee#" + emp_id + " set to work on " + work_date;
        	}
        	
        	else {
        		throw new DatabaseException();
        	}
        }
		return outputString;
	}

	/*
	 * Implementing the InputException for any other combinations of input of TestClass8
	 */
	public String setHours(String string, String work_date, String starting_time, String ending_time) throws InputException {
		throw new InputException();
	}
	
	/*
	 * Implementing the function for TestClass6
	 */
	public ArrayList<String> viewHours() throws SQLException {
		
		ArrayList<String> output = new ArrayList<String>();
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement exist = connect.createStatement();
        
        //checking if employee already working that day
        String sql = "SELECT I.emp_fname, I.emp_lname, E.starting_time, E.ending_time FROM EmployeeInfo I, employeeManagement E WHERE I.emp_id = E.emp_id AND E.work_date = "+ getDate();

        ResultSet rs = exist.executeQuery(sql);
        
        
        while(rs.next()) {
        	String recordName = "";
        	for (int i = 1; i <= 4; i++) {
        		recordName += rs.getString(i) + " ";
        		
        	}
        	output.add(recordName);
        }
        
        if (output.isEmpty())
        	output.add("No one is working today");
		return output;
	} 
}