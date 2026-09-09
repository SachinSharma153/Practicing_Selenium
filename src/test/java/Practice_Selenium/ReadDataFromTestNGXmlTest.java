package Practice_Selenium;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;


public class ReadDataFromTestNGXmlTest {

@Test

public void sampleTest(XmlTest test ) {
	System.out.println("Running SampleTest");
	
	System.out.println(test.getParameter("browser"));
	System.out.println(test.getParameter("url"));
	System.out.println(test.getParameter("username"));
	System.out.println(test.getParameter("password"));
	
	

}

}
