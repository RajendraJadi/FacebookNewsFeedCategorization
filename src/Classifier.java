import java.io.*;


public class Classifier
{
	//retruns true if friends name found in list(Life event) else Entertainment post
	boolean CheckFriendName(String message) throws IOException
    {
		    
		
		String line;
		
		

		 String[] arr = message.split(" ");    
		 for ( String MessageWord : arr) 
		 {

			 //System.out.print(MessageWord+"\n\n\n");
		       //System.out.println(MessageWord);
               InputStream fis = new FileInputStream("FreindsNames.txt");
		       BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		       while ((line = br.readLine()) != null)
		       {
		    	   String[] friendsName = line.split(" ");
		    	  // System.out.print(friendsName[0]+"\n");
		    	   if (friendsName[0].equals(MessageWord))
		    	   {
		    		   System.out.print("Life Event");   
		    		   return true;
		    	   }
		          
		       }
		      br.close();
		 }
	     	return false;
	}
	/*public static void main(String[] args) throws IOException 
	{
		Classifier c=new Classifier();
		c.CheckFriendName("Aarzo  dsf");
	}*/

}
