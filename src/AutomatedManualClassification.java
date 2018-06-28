import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class AutomatedManualClassification 
{
	
	//this function takes excel file name and classifies them into life and other posts based on if else condition
	void classifyExcelSheet(String ExcelFileName)
	{
		
		
		HSSFWorkbook workbook=null;
	    HSSFSheet sheet=null;
		int TotalRows=0;
		FileInputStream file=null;
		 try 
		 {
		     file = new FileInputStream(new File(ExcelFileName));
			 try 
			 {
				workbook = new HSSFWorkbook(file);
			 }
			 catch (IOException e) 
			 {
					e.printStackTrace();
		     }
		 } 
		 catch (FileNotFoundException e) 
		 {		
			e.printStackTrace();
		 }
		 sheet = workbook.getSheet("Friends_posts");
		 TotalRows= sheet.getLastRowNum();
		  System.out.println(TotalRows);
		  ExcelReader a=new ExcelReader();
		  String Message=null;
		  boolean Classified=false;
		  String ClassifiedValue=null;
		  String ClassNameToUpdate=null;
		  for(int i=2;i<TotalRows;i++)
		  {
			    //read the message form message column, i indicates row and 3 indicates column
			  try 
			{
				Message=a.ReadExcelSheet(ExcelFileName, i, 3);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			   //check wether its classified already
			try 
			{
				ClassifiedValue=a.ReadExcelSheet(ExcelFileName, i, 11);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			 
			  if(!ClassifiedValue.equals("-"))
			  {
				  
				  System.out.println("Classified by Tag classifier "+ ClassifiedValue+"\n");
				  Classified=true;
			  }
			  
			  //if not classified do it now
			  if( Classified==false)
			  {
				  boolean result;
				  result=classify(Message);
				  if(result==true)
				  {
					  ClassNameToUpdate="Life (by if else condition)";
				  }
				  else
				  {
					  ClassNameToUpdate="Others (by if else condition)";
				  }
				  a.UpdateSheetToLearn(ExcelFileName, ClassNameToUpdate,i-1, 10);
			  }
		  }
	}
	
	
	

	//this function takes post and returns true for Life and false for others
	boolean classify(String Post)
  {
		 //variables to keep count of words in post
	    int lifecount=0;
	    int otherscount=0;
	    
	    //variable to take post and split them
	    String[] PostWords = Post.split(" ");
	    
	    //variable to hold string value of text file 
	    String word=null;
		

		//take the count of lifePosts' words for post taken 
	try
	{
		   InputStream fis = new FileInputStream("LifePostsRules.txt");
	       BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	       while ((word = br.readLine()) != null)
	       { 
	    	   
	    	   for(int i=0;i<PostWords.length ;i++)
	    	   {
	    		 //System.out.println(PostWords[i]+" == "+word);
	    	     if (word.equalsIgnoreCase(PostWords[i]))
	    	     {
	    	    	 
	    	    	 lifecount++;
	    		    // System.out.println("Life Event count :"+lifecount);   
	    		 
	    	     }
	    	   
		    	 
	    	   }
	    	 
	       }
	      br.close();
	}
    catch(Exception e)
	{
    	 System.out.println("problem found while counting words of life");
    	 e.printStackTrace();
	}
	
	//take the count of others' words for post taken 
	try
	{
			   InputStream fis = new FileInputStream("OtherPostsRules.txt");
		       BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		       while ((word = br.readLine()) != null)
		       {
		    	   for(int i=0;i<PostWords.length;i++)
		    	   {
		    	     if (word.equalsIgnoreCase(PostWords[i]))
		    	     {
		    	    	 otherscount++;
		    		  //   System.out.println("others' Event count :"+otherscount);   
		    	     }
		    	   }
		       }
		      br.close();
	}
	catch(Exception e)
		{
	    	 System.out.println("problem found while counting words of others");
		}
	
	//display the count of lifecount and otherscount
	System.out.println("\n\nFinal count of life words in post :"+lifecount);
	System.out.println("Final count of others words in post :"+otherscount+"\n\n");
	
	//decide under which category does the given posts lie
	if(lifecount>otherscount)
	{
		System.out.println("lifecount > Others count");
		System.out.println("Decided as life event");
		return true;
	}
	else if(lifecount<otherscount)
	{
		System.out.println("lifecount < Others count");
		System.out.println("Decided as Other event");
		return false;
		
	}
	else if(lifecount==otherscount)
	{
		System.out.println("lifecount == Others count");
		System.out.println("Decided as Other event");
		return false;
	}
	
	//flow wont come here, but for coding conventions need to return the following
	return false;
 }
	
	
  public static void main(String[] args) 
  {
	   
  }
}
