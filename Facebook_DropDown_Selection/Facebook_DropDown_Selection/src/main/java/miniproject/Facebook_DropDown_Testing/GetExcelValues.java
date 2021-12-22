package miniproject.Facebook_DropDown_Testing;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelValues {
	
	static String output[][];
	
	File file;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;

	GetExcelValues() throws InvalidFormatException, IOException{
		
		//Locating the Excel Sheet
		
		file = new File("./Excel/Registration_Values (1).xlsx");
	
		wb = new XSSFWorkbook(file);
		sheet = wb.getSheetAt(0);

	}
	
	public String[][] readExcel() throws Exception{
		
		String temp="";
	
		int iter = 0,row_iter = 1;
		
		//iter for iteration
		
		//row_iter for row iteration
		
		int row_count = sheet.getLastRowNum();

		//row_count gives the number of rows in Excel Sheet
		
		int noOfColumns = sheet.getRow(0).getLastCellNum();

		//noOfColumns gives the number of columns in Excel Sheet
		
		output = new String[row_count][noOfColumns]; 
		
		while(row_iter < row_count+1){	
	
			row = sheet.getRow(row_iter);
		
				while(iter < noOfColumns){	

						temp = String.valueOf(row.getCell(iter));						
						
						output[row_iter-1][iter] = temp;
									
						iter++;								
						//increasing column count
				}
				
				iter = 0; 									
				//resetting column values
				
				row_iter++; 								
				//increasing row count
			}
		
		wb.close();										
		//closing workbook
		
		return output;
		
	}
    
}