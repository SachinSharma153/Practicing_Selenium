package Practice_Selenium_DDT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		//file opened in  the read mode which cannot be written or modified here
		FileInputStream fis = new FileInputStream("C:\\Users\\User195\\Downloads\\TestData_TC_01.xlsx");

		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("Sheet2");
		
		//it returns the row where the 1st row
		Row row = sh.getRow(1);
		
		//here i want to write the data in the status column it will return the cell
		Cell cell = row.createCell(4);
		
		cell.setCellType(CellType.STRING);
		
		cell.setCellValue("PASS");
		//if you want to save the data you have to open excel in the write mode
		FileOutputStream fos = new FileOutputStream("C:\\Users\\User195\\Downloads\\TestData_TC_01.xlsx");
		
		wb.write(fos);

		System.out.println("============EXECUTED==============");
		wb.close();
	}

}
