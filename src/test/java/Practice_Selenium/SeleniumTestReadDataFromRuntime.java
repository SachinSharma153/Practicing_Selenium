package Practice_Selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumTestReadDataFromRuntime {

	@Test
	
	public void seleniumTest() throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\apraj\\Downloads\\data\\CommonData.properties");
		
		Properties pObj = new Properties();
		
		pObj.load(fis);
	
		
		
		
		
		
		
		
		
		
		
		
	}

}
