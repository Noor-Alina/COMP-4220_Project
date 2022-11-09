package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SoftwareManagement extends Throwable{

	public static void main(String args[]) throws SQLException{
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bookmanagement", "guest", "guest123");
        Statement st = connect.createStatement();
        String sql = "SELECT * from studentinfo";

        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()) {
        	
        	String dataString= "";
        	
        	for(int i= 1; i <= 4; i++) {
        		
        		dataString += rs.getString(i) + ":";
        	}
        	
        	System.out.println(dataString);
        }
	}
}
