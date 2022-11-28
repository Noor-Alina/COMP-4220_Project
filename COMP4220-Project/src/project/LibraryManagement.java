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

public class LibraryManagement extends Throwable{
	
	public ArrayList<String> viewLibraryRequests() throws SQLException {
		
		ArrayList<String> output = new ArrayList<String>();
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement exist = connect.createStatement();
        
        //checking if there is a request from library
        String sql = "SELECT * FROM LibraryRequest L WHERE L.request_status = 'Pending' ";

        ResultSet rs = exist.executeQuery(sql);
        
        while(rs.next()) {
        	String recordstatus = "";
        	for (int i = 1; i <= 4; i++) {
        		recordstatus += rs.getString(i) + " ";      		
        	}
        	output.add(recordstatus);
        }
        
        if (output.isEmpty())
        	output.add("There are no requests at this time");
		return output;
	} 
	
	
}