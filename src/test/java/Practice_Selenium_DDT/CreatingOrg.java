package Practice_Selenium_DDT;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class CreatingOrg {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\User195\\Desktop\\CommonData.properties");
		

		
		Properties pObj = new Properties();
		
		pObj.load(fis);
		
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);

		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@title ='Create Organization...']")).click();
		
		String orgname = "Org_1229";
		
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebElement w = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		
		String actualValue = w.getText();
		
		System.out.println(actualValue);
		
		if(w.getText().contains(orgname))
		{
			System.out.println("The orgname is " + orgname +" and the actualValue is " + actualValue);
		}
		
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();

		driver.findElement(By.xpath("//a[@href='index.php?module=Users&action=Logout']")).click();
	
				driver.quit();
	}

}
