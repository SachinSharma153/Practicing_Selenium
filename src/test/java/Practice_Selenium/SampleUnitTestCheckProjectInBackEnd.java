package Practice_Selenium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckProjectInBackEnd {		
@Test
		
public void projectCheck() throws SQLException {
	
//	String expectedProjectName = "SOC 2 Compliance Audit Prep";
	String expectedProjectName = "Audit Prep";
	boolean flag = false;
	
	Driver driverRef = new Driver();
	
	DriverManager.registerDriver(driverRef);
	
	Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","admin");
	System.out.println(conn);
	System.out.println("=====Done=====");
	
	Statement stat = conn.createStatement();
	
	ResultSet resultset = stat.executeQuery("Select * from project");
	
	while (resultset.next()) {
		String actProjectName = resultset.getString(2);
		if(expectedProjectName.equals(actProjectName))
		{
			flag = true;
			System.out.println(expectedProjectName + " is avaliable==PASS");
		}
	}
	if(flag==false) 
	{
		System.out.println(expectedProjectName + " is not avaliable==FAILED");
		Assert.fail();
	}
	
	
	
	conn.close();
	
	
	}
}

