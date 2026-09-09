package Practice_Selenium;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelTestScript {

	public static void main(String[] args) throws EncryptedDocumentException, IOException{

	FileInputStream fis = new FileInputStream("C:\\Users\\User195\\Downloads\\TestCaseTesting.xlsx");

	Workbook wb = WorkbookFactory.create(fis);
	
	Sheet sh =  wb.getSheet("org");

	Row row = sh.getRow(1);
	
	//Cell cell =   row.getCell(2);
//	String data = row.getCell(3).getStringCellValue();
	
//	double data1 = row.getCell(3).getNumericCellValue();
	
	String data = row.getCell(3).toString(); 
	
	//String data = cell.getStringCellValue();
	
	System.out.println(data);
	
	wb.close();
	
	} 
}
