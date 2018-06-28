import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import com.restfb.types.User;


public class AutomatedLiveLearner extends JFrame
{
	

		JProgressBar jb;  
		
		JLabel L= new JLabel("Please wait, Excel Sheet is being prepared with your Facebok Data....");
		JLabel L1= new JLabel("Excel Sheet will be opened shortly..!!");
		JLabel waitL= new JLabel("Connecting to Facebook please wait..");
		JLabel name =new JLabel("Accessing Name");
		JLabel status =new JLabel("Accessing status");
		JLabel LogL =new JLabel("LOG:");
		JTextArea Log = new JTextArea("");
		JButton Exit=new JButton("Back");
		JButton ShowMore=new JButton("Show More");
		JButton ShowLittle=new JButton("Show Little");
		 ImageIcon guy = new ImageIcon("loading.gif");
		 AutomatedLiveLearner()
		{
		
			 //check if opened 
			JFrame a= new JFrame();
			JOptionPane.showMessageDialog(a, "Make sure that FacebookData.xls is not being used by other application before proceeding");
			
		
		
			this.setBounds(400, 200, 610, 250);
			 JLabel im = new JLabel(new ImageIcon("updating.gif"));
	         //setIconImage(customIcon);
			//JLabel im = new JLabel("hi...skdjfhskfd");
		    setUndecorated(true);
		    setBackground(Color.BLUE);
			setResizable(false);
			this.setAlwaysOnTop(true);
			JScrollPane scroll = new JScrollPane (Log,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			this.setTitle("Facebook Data Access");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
			//getContentPane().setBackground(Color.BLACK);
			//setIconImage(null);
			
		    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    im.setBounds(400,20,200,80);
		    add(im);
			jb=new JProgressBar(0,2000);  
			jb.setBounds(40,40,200,30);  
			jb.setIndeterminate(true);
			name.setBounds(20,-50,200,200);
			status.setBounds(40,40,200,200);
			Exit.setBounds(450,180,100,30);
			add(Exit);
			ShowMore.setBounds(20,180,100,30);
			add(ShowMore);
			ShowLittle.setBounds(20,410,100,30);
			add(ShowLittle);
			LogL.setBounds(20,220,100,30);
			add(LogL);
			
			ShowLittle.addActionListener(new ActionListener() 
		    {
		    	 
	            public void actionPerformed(ActionEvent e)
	            {
	            	
	                //Execute when button is pressed
	            	setBounds(400, 200, 610, 250);
	            	ShowMore.setEnabled(true);
	               
	            }
	        });    
			ShowMore.addActionListener(new ActionListener() 
		    {
		    	 
	            public void actionPerformed(ActionEvent e)
	            {
	            	
	                //Execute when button is pressed
	            	setBounds(400, 200, 610, 500);
	            	ShowMore.setEnabled(false);
	               
	            }
	        });    
			scroll.setBounds(20, 250, 550, 150);
			Exit.setEnabled(true);
			Exit.addActionListener(new ActionListener() 
		    {
		    	 
	            public void actionPerformed(ActionEvent e)
	            {
	            	
	            	//Exit.setEnabled(true);
	            	//setVisible(false);
	            	

	                //Execute when button is pressed
	                System.exit(0);
	               
	            }
	        });    
			L.setBounds(20, 60, 550, 20);
			L1.setBounds(20, 75, 550, 20);
			add(L1);
		
	
			jb.setBounds(20, 100, 550, 20);
			add(scroll);
			//add(Log);
			add(L);
			add(jb);  
			add (name);
			add(status);
			validate();
			//setSize(400,400);  
			this.setLayout(null);  		
			String AccessToken=null;
			
			 BufferedReader br;
			try {
				br = new BufferedReader(new FileReader("AccessToken.txt"));
				 try {
					AccessToken = br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	 
			 
			this.setVisible(true);
			int commentscnt=0;
			FacebookClient facebookClient = null;
			//Instantiate ExcelWriter for writing data
			ExcelWriter writer= new ExcelWriter();
			int rowcnt=writer.GetFriendsRowCount();
			 
			try
			{
			facebookClient= new DefaultFacebookClient(AccessToken);
			User user = facebookClient.fetchObject("me", User.class);
			name.setText("Name:"+ user.getName());
			
			}
			catch(Exception e)
			{
				Exit.setEnabled(true);
	        	setVisible(false);
	        	JOptionPane.showMessageDialog(this, "Please Re-run the applicatoin cound'nt Connect to Facebook","Error",JOptionPane.ERROR_MESSAGE);
	        	//Main window = new Main();
				//window.open();
			}
			
			
			// build friends list
			try {
				FriendsDictionaryBuilder B = new FriendsDictionaryBuilder(AccessToken);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			status.setText("Accessing your friends list");
			
			
			Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
			Connection<Post> myFeed = facebookClient.fetchConnection("me/home", Post.class);

	        //displaying some user data	
			Log.append("Count of my friends  : " + myFriends.getData().size()+"\n");
			// Connections getting feed, writing data and are iterable
			for (List<Post> newsFeedConnectionPage : myFeed)
			{
			
			  for (Post post : newsFeedConnectionPage)
			  {
				  status.setText("Accessing your News Feed.");
				  Log.append("Accessing your News Feed."+"\n");
			      
			      //write the sheet into the FacebookData.xls file using our ExcelWriter class
			      if(post.getFrom().getCategory()==null)// decides as friends posts
			      {
			    	  status.setText("Writing in excel sheet.");
			    	  Log.append("Writing in excel sheet."+"\n");
			    			    	  
			    	  boolean FriendExists=false;
			    	  if(post.getMessage()!=null)
			    	  {
			    	    Classifier C= new Classifier();
			    	    try {
							FriendExists =C.CheckFriendName(post.getMessage());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	  }
			    	  if(post.getCaption()!=null)// decdes as entertainment
			    	  {   status.setText("Writing in excel sheet.");
			    	  	  Log.append("Writing in excel sheet."+"\n");
			    		  if(writer.Friends_sheet(post.getId(), post.getFrom().getName(),  post.getMessage(),commentscnt, post.getSharesCount(), post.getApplication(), post.getCaption(), post.getDescription(), post.getPicture(), post.getCreatedTime().getDate() +"-"+post.getCreatedTime().getMonth(),"entertainment(Tag)")==false)
						     {
						    	 Log.append("writing data into base facebookdata.xls failed"+"\n");
						    	 
						     }
			    	  }
			    	  else if(post.getMessage()==null)
			    	  {
			    		  
			    		  if(writer.Friends_sheet(post.getId(), post.getFrom().getName(),  post.getMessage(),commentscnt, post.getSharesCount(), post.getApplication(), post.getCaption(), post.getDescription(), post.getPicture(), post.getCreatedTime().getDate() +"-"+post.getCreatedTime().getMonth(),"entertainment(Tag)")==false)
						     {
						    	 Log.append("writing data into base facebookdata.xls failed"+"\n");
						    	 
						     }
			    	  }
			    	  else if(FriendExists)// decides as Life event post
			    	  {
			    		  if(writer.Friends_sheet(post.getId(), post.getFrom().getName(),  post.getMessage(),commentscnt, post.getSharesCount(), post.getApplication(), post.getCaption(), post.getDescription(), post.getPicture(), post.getCreatedTime().getDate() +"-"+post.getCreatedTime().getMonth(),"life(Tag)")==false)
						     {
						    	 Log.append("writing data into base facebookdata.xls failed");
						    	 
						     }
		    			  
			    	  }
			    	  else  //this will be not classified
			    		  {
			    			  if(writer.Friends_sheet(post.getId(), post.getFrom().getName(),  post.getMessage(),commentscnt, post.getSharesCount(), post.getApplication(), post.getCaption(), post.getDescription(), post.getPicture(), post.getCreatedTime().getDate() +"-"+post.getCreatedTime().getMonth(),"-" )==false)
							     {
							    	 Log.append("writing data into base facebookdata.xls failed");
							    	 
							     }
			    			  
			    		  }
				    	  
			    	  
			    	  
			      }
			   
			     
			       commentscnt=0;
			    
			              
			       
			    }
			  
			}
			Exit.setEnabled(true);
			setVisible(false);
			this.dispose();
			
			
			
			
			//classify the data fetched form facebook
			try {
				AutomatedTestBuilder builder=new AutomatedTestBuilder(1);
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
			
			
			
			
			
			//train the classifier
			JFrame frame1= new JFrame("Please wait till it get's trained");
			frame1.setBounds(200, 200, 500, 60);
			frame1.setVisible(true);
		
			jb.setIndeterminate(true);
			jb.setBounds(5, 190, 480, 20);
			frame1.add(jb);
			jb.setVisible(true);
			//read combobox selected value
			String ClassifierSelected= "Naive Bayes";
			
			System.out.println(ClassifierSelected);
			new File(System.getProperty("user.dir")+"/FacebookFeed").mkdir();
			int Eno=1;
			int Lno=1;
			String path = "FacebookData.xls";
			FileInputStream file = null;
			try {
				file = new FileInputStream(new File(path));
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			}
		     
		    //Get the workbook instance for XLS file 
		    HSSFWorkbook workbook = null;
			try {
				workbook = new HSSFWorkbook(file);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		 
		    //Get first sheet from the workbook
		    HSSFSheet sheet = workbook.getSheet("Friends_posts");
		     rowcnt-=10;
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    while(rowIterator.hasNext()) {
		    	rowcnt--;
		    	if(rowcnt<0)
		    		break;
		    	
		        Row row = rowIterator.next();
		        int rand = (int) (Math.random() * 10);
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		       
		             
		            Cell cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            System.out.print(cell.getStringCellValue() + "\t\t");
		            String message = cell.getStringCellValue();
		            
		            
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            cell = cellIterator.next();
		            System.out.print(cell.getStringCellValue() + "\t\t");
		            String category = cell.getStringCellValue();
		            System.out.println(category);
		            
		            //Create files in directory FacebookFeed to be used for creation of arff files
		            if(category.equals("life"))
		    
		            {
		            
		            	PrintWriter writer1;
						try {
							writer1 = new PrintWriter(System.getProperty("user.dir")+"/FacebookFeed/"+Lno+"Life.txt", "ASCII");
							writer1.println(message);	
			            	writer1.close();
			            	Lno++;
						} catch (FileNotFoundException e) {
						
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							
							e.printStackTrace();
						}
		            	
		            }
		           if(category.equals("entertainment"))
		    
		            {
		            	PrintWriter writer2;
						try {
							writer2 = new PrintWriter(System.getProperty("user.dir")+"/FacebookFeed/"+Eno+"Entertainment.txt", "ASCII");
							writer2.println(message);	
			            	writer2.close();
			            	Eno++;
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	
		            }    
		           
		        
		    }
		    try 
		    {
				file.close();
			}
		    catch (IOException e) 
		    {
				
				e.printStackTrace();
			}
		
		//Create FacebookData.arff file
		TextDirectoryToArff a1 = new TextDirectoryToArff();
		try 
		{
			a1.createDatasetLearning("FacebookFeed",ClassifierSelected);
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
		}
		  
		
		if(ClassifierSelected.equals("Naive Bayes"))
		{
		    //train the classifier using the NaiveBayesFacebookData.arff file created
			NavieBayesLearner Ln=new NavieBayesLearner();
			Ln.LearnDriver("NaiveBayesFacebookData.arff","NaiveBayesfacebookClassifier.dat");
			Ln.EvaluateDriver("NaiveBayesFacebookData.arff");
		}
		
	 
		// clean the directory files created
			File file1 = new File("FacebookFeed");        
	        String[] myFiles;      
	            if(file1.isDirectory())
	            {  
	                myFiles = file1.list();  
	                for (int i=0; i<myFiles.length; i++) 
	                {  
	                    File myFile = new File(file1, myFiles[i]);   
	                    myFile.delete();  
	                }  
	             }  
	          /*  
	           File myFile = new File("FacebookData.xls");   
                myFile.delete();  
	            frame1.setVisible(false);
			*/
			
			
			
			
			
			
			
			
	      
		}


		 public static void main(String[] args) 
			{
			   AutomatedLiveLearner a= new AutomatedLiveLearner(); 
			}

}
