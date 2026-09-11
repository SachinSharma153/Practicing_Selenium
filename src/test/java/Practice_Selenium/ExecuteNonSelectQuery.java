package Practice_Selenium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQuery {

	public static void main(String[] args) throws SQLException {
		
		Driver driverRef = new Driver();
		
		DriverManager.registerDriver(driverRef);
		
		Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","admin");
		System.out.println(conn);
		System.out.println("=====Done=====");
		
		Statement stat = conn.createStatement();
		
		int result = stat.executeUpdate("INSERT INTO project VALUES (    12,    'AI Agents',    'Develop cross-platform AI Agents for Work Automation',    'Completed',    '2023-04-15',    '2025-01-19',    5400.0,    '2026-04-15 13:02:11');");
		System.out.println(result);
	
		conn.close();

	}

}
