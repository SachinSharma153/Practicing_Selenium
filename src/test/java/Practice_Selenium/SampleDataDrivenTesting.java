package Practice_Selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SampleDataDrivenTesting {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("C:\\Users\\User195\\Desktop\\CommonData.properties");
		
		
		Properties pObj = new Properties();
		
		pObj.load(fis);
		
		System.out.println(pObj.getProperty("browser"));
		System.out.println(pObj.getProperty("url"));
	}

}
