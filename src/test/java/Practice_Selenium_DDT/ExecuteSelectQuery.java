package Practice_Selenium_DDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQuery {

	public static void main(String[] args) throws SQLException {
		
		Driver driverRef = new Driver();
		
		DriverManager.registerDriver(driverRef);
		
		Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","admin");
		System.out.println(conn);
		System.out.println("=====Done=====");
		
		Statement stat = conn.createStatement();
		
		ResultSet resultset = stat.executeQuery("Select * from project");
		
		while (resultset.next()) {
			System.out.println(resultset.getString(1));
			System.out.println(resultset.getString(2));
		}
		
	
		conn.close();
	}

}
