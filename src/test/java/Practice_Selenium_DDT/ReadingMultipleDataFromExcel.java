package Practice_Selenium_DDT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\User195\\Downloads\\TestData_TC_01.xlsx"); 

		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("Sheet1");
		
		for(int i=1;i<sh.getLastRowNum();i++)
		{
			Row row = sh.getRow(i);
			
			String column1Data = row.getCell(0).toString();
			String column2Data = row.getCell(1).toString();	
			
			System.out.print(column1Data + '\t' + column2Data + '\n');
 		}

		wb.close();
		
	}

}
