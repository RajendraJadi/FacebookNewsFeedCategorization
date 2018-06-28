import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class ExcelReader {

	String ReadExcelSheet(String ExcelFileName,int rowNo,int colNo) throws IOException
	{
		System.out.println("sdf");
		String Message=null;
		int rowcnt=0,colcnt=0;
		FileInputStream file = new FileInputStream(new File(ExcelFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		//Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		//Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();

		//Get iterator to all cells of current row
	    int flag=0;
		while(rowIterator.hasNext()) 
		{
			Row row = rowIterator.next();
			rowcnt++;
			
			//For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext())
			{	
				Cell cell = cellIterator.next();
				colcnt++;			
				if(rowcnt == rowNo && colNo==colcnt)
				{
					
					switch(cell.getCellType()) 
					{
					case Cell.CELL_TYPE_BOOLEAN:
						//System.out.print(cell.getBooleanCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						//System.out.print(cell.getNumericCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						//System.out.print(cell.getStringCellValue() + "\t\t");
						Message=cell.getStringCellValue();
						break;
					}
					flag=1;
					break;
				}
			}
			if(flag==1)
				break;
			colcnt=0;
		}
		file.close();
		return Message;
	}
	
	boolean UpdateSheetToLearn(String ExcelFileName,String Message,int rowNo,int colNo)
	{
		try {
		FileInputStream file = new FileInputStream(new File(ExcelFileName));
		 
	    HSSFWorkbook workbook = new HSSFWorkbook(file);
	    HSSFSheet sheet = workbook.getSheetAt(0);
	    Cell cell = null;
	 
	    //Update the value of cell
	    cell = sheet.getRow(rowNo).createCell(colNo);
	    cell.setCellValue(Message);
	    
	     
	    file.close();
	     
	    FileOutputStream outFile =new FileOutputStream(new File(ExcelFileName));
	    workbook.write(outFile);
	    outFile.close();
	     
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
		return true;
	}
	
	
	public static void main(String[] args) 
	{
		String mess=null;
	     ExcelReader a= new ExcelReader();
	     try {
			mess=a.ReadExcelSheet("FacebookData.xls", 2, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.print(mess);

	}
}
