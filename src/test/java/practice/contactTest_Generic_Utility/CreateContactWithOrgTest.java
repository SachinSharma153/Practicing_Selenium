package practice.contactTest_Generic_Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrgTest {

	//This is integration test scenario based on the (Organization, Contact) modules
    public static void main(String[] args) throws IOException {

        // 1. Read Common Data from Properties File
        Properties pObj = new Properties();
        try (FileInputStream fis = new FileInputStream(
                "C:\\Users\\apraj\\Downloads\\data\\Generic_Utility\\CommonData.properties")) {
            pObj.load(fis);
        }

        String browser = pObj.getProperty("browser");
        String url = pObj.getProperty("url");
        String username = pObj.getProperty("username");
        String password = pObj.getProperty("password");

        // 2. Read Test Script Data from Excel File
        String orgNameFromExcel;
        String contactLastName;

        try (FileInputStream fisExcel = new FileInputStream(
                "C:\\Users\\apraj\\Downloads\\data\\Generic_Utility\\TestScriptingdata.xlsx");
             Workbook wb = WorkbookFactory.create(fisExcel)) {

            Sheet sh = wb.getSheet("contact");
            Row row = sh.getRow(12);

            int randomInt = new Random().nextInt(1000);
            orgNameFromExcel = row.getCell(2).toString().trim() + randomInt;
            contactLastName = row.getCell(3).getStringCellValue().trim();
        }

        // 3. WebDriver Initialization
        WebDriver driver;
        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver();
        }

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();

            // Step 1: Login to Application
            driver.get(url);
            driver.findElement(By.name("user_name")).sendKeys(username);
            driver.findElement(By.name("user_password")).sendKeys(password);
            driver.findElement(By.id("submitButton")).click();

            // Step 2: Navigate to Organizations and Create New Org
            driver.findElement(By.linkText("Organizations")).click();
            driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
            driver.findElement(By.name("accountname")).sendKeys(orgNameFromExcel);
            driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();

            // Verify Organization Header
            String headerInfo = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
            if (headerInfo.contains(orgNameFromExcel)) {
                System.out.println("[PASS] Organization created: " + orgNameFromExcel);
            } else {
                System.out.println("[FAIL] Organization not created: " + orgNameFromExcel);
            }

            // Step 3: Navigate to Contacts and Initiate Creation
            driver.findElement(By.linkText("Contacts")).click();
            driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
            driver.findElement(By.name("lastname")).sendKeys(contactLastName);

            // Step 4: Open Organization Lookup Window
            String parentWindow = driver.getWindowHandle();
            driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

            // Switch to Child Lookup Window
            Set<String> allWindows = driver.getWindowHandles();
            for (String winHandle : allWindows) {
                if (!winHandle.equals(parentWindow)) {
                    driver.switchTo().window(winHandle);
                    if (driver.getCurrentUrl().contains("module=Accounts")) {
                        break;
                    }
                }
            }

            // Search and Select Created Organization
            driver.findElement(By.name("search_text")).sendKeys(orgNameFromExcel);
            driver.findElement(By.name("search")).click();
            driver.findElement(By.xpath("//a[text()='" + orgNameFromExcel + "']")).click();

            // Switch Back to Parent Window & Save Contact
            driver.switchTo().window(parentWindow);
            driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();

            // Step 5: Verify Contact Creation
            headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
            if (headerInfo.contains(contactLastName)) {
                System.out.println("[PASS] Contact created: " + contactLastName);
            } else {
                System.out.println("[FAIL] Contact not created: " + contactLastName);
            }

            String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText().trim();
            if (actualOrgName.equals(orgNameFromExcel)) {
                System.out.println("[PASS] Linked organization verified: " + orgNameFromExcel);
            } else {
                System.out.println("[FAIL] Linked organization mismatch: " + actualOrgName);
            }

            // Step 6: Logout
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
            driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

        } finally {
            driver.quit();
        }
    }
}