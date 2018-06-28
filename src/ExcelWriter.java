
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Date;


import javax.swing.JFrame;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.restfb.types.NamedFacebookType;



public class ExcelWriter extends JFrame
{ 
	//Variables to write in spreadsheet
	Row row;
	Cell cell;
	
	//Variables to keep track of rows and columns in spread sheet while writing
	//Row count varies in both the sheets.
	int Friends_row_cnt=0;
	int Like_row_cnt=0;
	int Commentrowcnt=0;
    int cellcnt=0;

    //Get the workbook instance and Create a sheet for that work book for saving face book data
    HSSFWorkbook workbook=null;
    HSSFSheet sheet=null;
    
    
    
    
    
	ExcelWriter()
	{ 	
				
		File f = new File("FacebookData.xls");
		 
		  if(f.exists())
		  {
			  
			 
			  
			  System.out.println("File existed");
			  Friends_row_cnt= GetFriendsRowCount()+1;
			  Commentrowcnt= GetCommentsRowCount()+1;
			  Like_row_cnt= GetLikeRowCount()+1;
			 
			  try {
				FileInputStream file1 = new FileInputStream(new File("FacebookData.xls"));
				 try 
				 {
					workbook = new HSSFWorkbook(file1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  sheet = workbook.getSheetAt(0);
			  
			  
		  }
		  else
		  {
		
			   workbook = new HSSFWorkbook();
			   sheet = workbook.createSheet("Friends_posts");
			//Instantiate FacebookData.xls sheet1 with header for columns for Basic Facebook data storage
		
			
			row = sheet.createRow(Friends_row_cnt++);
			cell = row.createCell(cellcnt++);cell.setCellValue("ID");
			cell = row.createCell(cellcnt++);cell.setCellValue("Name of person");    
			cell = row.createCell(cellcnt++);cell.setCellValue("Message");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Comments Count");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Share Count");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Application");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Caption");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Description");
		    cell = row.createCell(cellcnt++);cell.setCellValue("link");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Date");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Classification");
		    cellcnt=0;//to make suer that next writing data will write at 0th column of sheet
		    
		    //Write these header for columns in spread sheet
		    try
			{
			    FileOutputStream out = new FileOutputStream(new File("FacebookData.xls"));
			    workbook.write(out);
			    out.close();
			   // System.out.println("Excel base sheet created successfully..");     
			} 
			catch (FileNotFoundException e) 
			{
			    e.printStackTrace();
			}
			catch (IOException e) 
			{
				
			    e.printStackTrace();
			}
		    
		    
		    
		    
		    
		    
		    
		    sheet = workbook.createSheet("Like_Page_posts");	    
			
			row = sheet.createRow(Like_row_cnt++);
			cell = row.createCell(cellcnt++);cell.setCellValue("ID");
			cell = row.createCell(cellcnt++);cell.setCellValue("Name of liked page");    
			cell = row.createCell(cellcnt++);cell.setCellValue("Message");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Comments Count");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Share Count");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Application");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Caption");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Description");
		    cell = row.createCell(cellcnt++);cell.setCellValue("link");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Date");
		    cellcnt=0;//to make suer that next writing data will write at 0th column of sheet
		    
		    //Write these header for columns in spread sheet
		    try
			{
			    FileOutputStream out = new FileOutputStream(new File("FacebookData.xls"));
			    workbook.write(out);
			    out.close();
			   // System.out.println("Excel base sheet created successfully..");     
			} 
			catch (FileNotFoundException e) 
			{
			    e.printStackTrace();
			}
			catch (IOException e) 
			{
			    e.printStackTrace();
			}
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    //Instantiate second sheet of FacebookData.xls for storing comments
		    sheet = workbook.createSheet("Comments");
		    
		    row = sheet.createRow(Commentrowcnt++);
		    
		    cell = row.createCell(cellcnt++);cell.setCellValue("ID");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Comments");
		    cell = row.createCell(cellcnt++);cell.setCellValue("LikesCount");
		    cell = row.createCell(cellcnt++);cell.setCellValue("Created Time");
		    
		    // Write these header for columns in spread sheet
		    try
			{
			    FileOutputStream out = new FileOutputStream(new File("FacebookData.xls"));
			    workbook.write(out);
			    out.close();
			   // System.out.println("Excel comment sheet created successfully..");     
			} 
			catch (FileNotFoundException e) 
			{
			    e.printStackTrace();
			}
			catch (IOException e) 
			{
			    e.printStackTrace();
			}
		
		
		  }
		
	}
	
	
	int GetFriendsRowCount()
    {
		FileInputStream file=null;
		 try {
			     file = new FileInputStream(new File("FacebookData.xls"));
				 try 
				 {
					workbook = new HSSFWorkbook(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		sheet = workbook.getSheet("Friends_posts");
		int a= sheet.getLastRowNum();
	  try {
		file.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return a;
    }
	int GetCommentsRowCount()
    {
		FileInputStream file=null;
		try {
			 file = new FileInputStream(new File("FacebookData.xls"));
			 try 
			 {
				workbook = new HSSFWorkbook(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	sheet = workbook.getSheet("Comments");
	int a =sheet.getLastRowNum();
    try {
		file.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return a;	

    }
	int GetLikeRowCount()
    {
		FileInputStream file=null;
		try {
			file = new FileInputStream(new File("FacebookData.xls"));
			 try 
			 {
				workbook = new HSSFWorkbook(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	sheet = workbook.getSheet("Like_Page_posts");
	int a=sheet.getLastRowNum();
	
	try {
		file.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
   return a;	
    	
    }
	
	
	
	//function to write data in Friends sheet of facebookData.xls file
	boolean Friends_sheet(String Id,String Name,String Message, int commentcnt,Long Shares,NamedFacebookType Application,String Caption,String Description,String Link, String Date,String classification)
	{
		//Set the cell values of excel sheet
		cellcnt=0;
		sheet = workbook.getSheet("Friends_posts");
		row = sheet.createRow(Friends_row_cnt++);
		
		 cell = row.createCell(cellcnt++);
	       if(Id==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Id);    
	   
	     cell = row.createCell(cellcnt++);
	       if(Name==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Name);    
	   
		   
		cell = row.createCell(cellcnt++);
	       if(Message==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Message);
	        	       
	    cell = row.createCell(cellcnt++);
	           cell.setCellValue(commentcnt);    
	      
	    cell = row.createCell(cellcnt++);
	       if(Shares==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Shares);
	     
	    cell = row.createCell(cellcnt++);
	       if(Application==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Application.getName() );
	       
        cell = row.createCell(cellcnt++);
	       if(Caption==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Caption);    
	       
	    cell = row.createCell(cellcnt++);
	       if(Description==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Description);    
	    
       cell = row.createCell(cellcnt++);
	       if(Link==null)
	    	   cell.setCellValue("NULL");
	       else
	       {   if(Link.length()>254)
	             cell.setCellValue("TOO LENGTHY LINK");
	           else  
	        	   cell.setCellValue(Link);
	       }
	   
	     cell = row.createCell(cellcnt++);
	       if(Date==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Date);  

		    cell = row.createCell(cellcnt++);
		       if(classification==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(classification);    

	       //Write data into excel sheet
	       try
			{
			    FileOutputStream out = new FileOutputStream(new File("FacebookData.xls"));
			    workbook.write(out);
			    out.close();
			   // System.out.println("Excel Base written successfully..");
			    return true;
			} 
	       catch(Exception e)
	       {
	    	  
				return false;
	       }
			
	}
	
	
	
	//function to write data in Friends sheet of facebookData.xls file
		boolean Like_sheet(String Id,String Name,String Message, Long Likes,Long Shares,NamedFacebookType Application,String Caption,String Description,String Link, String Date)
		{
			//Set the cell values of excel sheet
			cellcnt=0;
			sheet = workbook.getSheet("Like_Page_posts");
			row = sheet.createRow(Like_row_cnt++);
			
			 cell = row.createCell(cellcnt++);
		       if(Id==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Id);    
		   
		     cell = row.createCell(cellcnt++);
		       if(Name==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Name);    
		      
			cell = row.createCell(cellcnt++);
		       if(Message==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Message);
		        	       
		    cell = row.createCell(cellcnt++);
		       if(Likes==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Likes);    
		      
		    cell = row.createCell(cellcnt++);
		       if(Shares==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Shares);
		     
		    cell = row.createCell(cellcnt++);
		       if(Application==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Application.toString());
		       
	        cell = row.createCell(cellcnt++);
		       if(Caption==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Caption);    
		       
		    cell = row.createCell(cellcnt++);
		       if(Description==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Description);    
		    
	       cell = row.createCell(cellcnt++);
		       if(Link==null)
		    	   cell.setCellValue("NULL");
		       else
		       {   if(Link.length()>254)
		             cell.setCellValue("TOO LENGTHY LINK");
		           else  
		        	   cell.setCellValue(Link);
		       }
		   
		     cell = row.createCell(cellcnt++);
		       if(Date==null)
		    	   cell.setCellValue("NULL");
		       else
		           cell.setCellValue(Date);  
		       
		       //Write data into excel sheet
		       try
				{
				    FileOutputStream out = new FileOutputStream(new File("FacebookData.xls"));
				    workbook.write(out);
				 //   System.out.println("Excel Base written successfully..");
				    return true;
				} 
		       catch(Exception e)
		       {
		    	  
					return false;
		       }
		}
	
	
	
	//function to write comments in Comment sheet of facebookData.xls file
	int Comments_sheet(String Id,String Message,Long Likecnt,Date Time)
	{
		cellcnt=0;
		sheet = workbook.getSheet("Comments");
		
		if(Commentrowcnt>65500)
			return 2;
		
		row = sheet.createRow(Commentrowcnt++);

		cell = row.createCell(cellcnt++);
	       if(Id==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Id);
	     
	    cell = row.createCell(cellcnt++);
	       if(Message==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Message);
	       
	    cell = row.createCell(cellcnt++);
	       if(Likecnt==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Likecnt);
	     
	    cell = row.createCell(cellcnt++);
	       if(Time==null)
	    	   cell.setCellValue("NULL");
	       else
	           cell.setCellValue(Time);
	       
		
		return 0;
	}
	

	
}
