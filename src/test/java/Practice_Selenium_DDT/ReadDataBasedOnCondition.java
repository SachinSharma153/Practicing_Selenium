package Practice_Selenium_DDT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnCondition {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\User195\\Downloads\\TestCaseTesting.xlsx"); 

		Workbook wb = WorkbookFactory.create(fis);
		
		boolean flag = false;
		
		Sheet sh = wb.getSheet("organize");
		
		String expectedData = "tc_03";
		
		int rowCount = sh.getLastRowNum();

		String data1="";
		String data2="";
		String data3="";
		
		
		for(int i=0;i<=rowCount;i++)
		{
			String data="";
			try 
			{
				data = sh.getRow(i).getCell(0).toString();	
				if(data.equals(expectedData))
				{
					flag = true;
					data1 = sh.getRow(i).getCell(1).toString();
					data2 = sh.getRow(i).getCell(2).toString();
					data3 = sh.getRow(i).getCell(3).toString();
				}
				
			
			} catch (Exception e) {
				// TODO: handle exception
				
				
			}
			
//			System.out.println(data);
		}
		if(flag==false)
		{
			System.out.println("The "+ expectedData + "data is not avaliable");
		}
		else 
		{
		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
		}
	}

}
