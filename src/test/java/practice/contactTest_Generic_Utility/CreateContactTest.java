package practice.contactTest_Generic_Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		// Reading commonData from PropertyFiles
	    FileInputStream fis = new FileInputStream(
	            "C:\\Users\\apraj\\Downloads\\data\\Generic_Utility\\CommonData.properties");

	    Properties pObj = new Properties();
	    pObj.load(fis);

	    String BROWSER = pObj.getProperty("browser");
	    String URL = pObj.getProperty("url");
	    String USERNAME = pObj.getProperty("username");
	    String PASSWORD = pObj.getProperty("password");




	    // Reading TestScript Data from the Excel file
	    FileInputStream fis1 = new FileInputStream(
	            "C:\\Users\\apraj\\Downloads\\data\\Generic_Utility\\TestScriptingdata.xlsx");
	    
	    Workbook wb = WorkbookFactory.create(fis1);

	    Sheet sh = wb.getSheet("contact");

	    Row row = sh.getRow(1);

	    wb.close();
	    
	    //generate a random number
	    
		Random random = new Random();
		
		int randomInt = random.nextInt(1000);
	    
	    String lastName_From_excel = row.getCell(2).toString() + randomInt;


	    // WebDriver initialization
	    WebDriver driver = null;

	    if (BROWSER.equals("chrome")) {

	        driver = new ChromeDriver();

	    } else if (BROWSER.equals("firefox")) {

	        driver = new FirefoxDriver();

	    } else if (BROWSER.equals("edge")) {

	        driver = new EdgeDriver();

	    } else {

	        driver = new ChromeDriver();
	    }


	    // Step 1 : Login to the App
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	    driver.get(URL);
	    
	    driver.manage().window().maximize();

	    driver.findElement(By.name("user_name")).sendKeys(USERNAME);

	    driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

	    driver.findElement(By.id("submitButton")).click();


	    // Step 2 : Navigate to the organizations module
	    driver.findElement(By.linkText("Contacts")).click();


	    // Step 3 : Click on the Create organizations button
	    driver.findElement(
	            By.xpath("//img[@title ='Create Contact...']"))
	            .click();


	    // Step 4 : Enter the create organization details and create new organization
	    // String orgname = orgName_From_excel;

	    driver.findElement(
	            By.xpath("//input[@name='lastname']"))
	            .sendKeys(lastName_From_excel);

	    driver.findElement(
	            By.xpath("(//input[@class='crmbutton small save'])[1]"))
	            .click();

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	    //verify lastname data from the saved information page and data from the lastName_From_excel

	    String actualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
	    if(actualLastName.equals(lastName_From_excel))
	    {
	    	System.out.println(lastName_From_excel + "is created == PASS");
	    }
	    else
	    {
	    	System.out.println(lastName_From_excel + "is not created == FAILED");
	    }
	    
	    //verify lastName info with Expected Result
	    String actualLastNameFromSavedInfo = driver.findElement(By.xpath("//span[@class ='dvHeaderText']")).getText();
	    if(actualLastNameFromSavedInfo.contains(lastName_From_excel))
	    {
	    	System.out.println(lastName_From_excel + "information is created == PASS");
	    }
	    else
	    {
	    	System.out.println(lastName_From_excel + "information is not created == FAILED");
	    }
	    

	    // Step 5 : Logout
	    Actions action = new Actions(driver);

	    action.moveToElement(
	            driver.findElement(
	                    By.xpath("//img[@src='themes/softed/images/user.PNG']")))
	            .perform();

	    driver.findElement(
	            By.xpath("//a[@href='index.php?module=Users&action=Logout']"))
	            .click();

	    driver.quit();


	}

}
