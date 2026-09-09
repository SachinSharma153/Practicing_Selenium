package Practice_Selenium;

import org.testng.annotations.Test;

public class ReadRuntimeMavenParameterTest {

@Test
public void runtimeParameterTest() {
	 
	String URL = System.getProperty("url");
	System.out.println("Env Data===>URL"+URL);
	String BROWSER = System.getProperty("url");
	System.out.println("Env Data===>URL"+BROWSER);
	String USERNAME = System.getProperty("url");
	System.out.println("Env Data===>URL"+USERNAME);
	String PASSWORD = System.getProperty("url");
	System.out.println("Env Data===>URL"+PASSWORD);
}

}
