import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.User;


public class UpdateExcelSheet extends JFrame
{
	int i=0;
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
	
	 UpdateExcelSheet()
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
		Exit.setEnabled(false);
		Exit.addActionListener(new ActionListener() 
	    {
	    	 
            public void actionPerformed(ActionEvent e)
            {
            	
            	//Exit.setEnabled(true);
            	//setVisible(false);
            	
            	Main window = new Main();
    			window.open();
            	
            	
                //Execute when button is pressed
                //System.exit(0);
               
            }
        });    
		L.setBounds(20, 60, 550, 20);
		L1.setBounds(20, 75, 550, 20);
		add(L1);
		jb.setValue(0);
		jb.setStringPainted(true);  
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
		
		 
		try
		{
		facebookClient= new DefaultFacebookClient(AccessToken);
		User user = facebookClient.fetchObject("me", User.class);
		Log.append("User name            : " + user.getName()+"\n");
		name.setText("Hi "+user.getName()+"...!!!!");
		}
		catch(Exception e)
		{
			Exit.setEnabled(true);
        	setVisible(false);
        	JOptionPane.showMessageDialog(this, "Connect to Facebook to update.","Error",JOptionPane.ERROR_MESSAGE);
        	Main window = new Main();
			window.open();
		}
		//facebookClient= new DefaultFacebookClient(JOptionPane.showInputDialog("CAACEdEose0cBAI7G0gn20xASxC4prenf4uBcVpTldK5sKHZCEXsxoFgdEvUmdLpkU4E7CnuxiIxUUfkmYTkZBxUOUSQjhA9di71DsqUnoVLpnOaI72r3UqeOBMFciBwfSd6VhuSB9IJNnD70HfntLLDZCopDE5pmi7PIGnWQCvjz3UI4nBkI3EhQHfs6thpFWgt1vZBRswZDZD"));
		
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
			  
			  //Log.append("Tags           :"+ post.getComments());
			 // Log.append("Tags           :"+ post.getMessageTags() );
			  //Log.append("Post ID           :"+ post.getWithTags().); 
			 /* Log.append("Post ID           :"+ post.getId()); 
			  Log.append("Post ID & Name    :"+ post.getFrom().getCategory() );
			  Log.append("Post Type         :"+ post.getStatusType());
		      Log.append("Post Message      :" + post.getMessage());        
		      Log.append("Post Name         :"+ post.getName());
		      Log.append("Post likes cnt    :"+ post.getLikes() );
		      Log.append("Post share cnt    :"+ post.getSharesCount()); 
		      Log.append("Post creating app :"+ post.getApplication());
		      Log.append("Post caption (link):"+ post.getCaption());
		      Log.append("Post Desc(link)    :"+ post.getDescription());
		   
		      
		      Log.append("Post Date    :"+ post.getCreatedTime().getDate() );
		      */
		    //MORE THAN ONE VALUE
		       if(post.getComments()==null)
		       {
		       	   Log.append("Post Comments     : NULL"+"\n");
		       }
		       else
		       {
		    	   for(Comment comment: post.getComments().getData())
		    	   {
		    		   status.setText("Accessing comments.");
		    		   Log.append("Accessing comments."+"\n");
		    		   commentscnt++;
		    	   	 /*  Log.append("Post Comment Message     :"+ comment.getMessage() );
		    	   	   Log.append("Post Comment Like cnt    :"+ comment.getLikeCount() );
		   
		    	   	   
		    	   	   Log.append("Post Comment CreatedTime :"+ comment.getCreatedTime() );
		    	   	 */   int temmp=10;
		    	   	   temmp =writer.Comments_sheet(post.getId(), comment.getMessage(),comment.getLikeCount(), comment.getCreatedTime());
		    	   	   if(temmp==0)
		    	   	   {
		  		    	 //Log.append("writing data into comments facebookdata.xls failed"+"\n");
		    	   	   }
		    	   	   else if(temmp==2)
		    	   	   {
		    	   		JOptionPane.showMessageDialog(this, "Your FacebookData.xls sheet's rows limit is exceed please cut paste it to some other location and run application again ","Error",JOptionPane.ERROR_MESSAGE);
		    	   		System.exit(0);
		    	   	   }
		          }		    
		
		      
		    	   
		      
		      //write the sheet into the FacebookData.xls file using our ExcelWriter class
		      if(post.getFrom().getCategory()==null)// decides as friends posts
		      {
		    	  status.setText("Writing in excel sheet.");
		    	  Log.append("Writing in excel sheet."+"\n");
		    	  status.setText("Accessing Picture.");
		    	  Log.append("Accessing Picture."+"\n");
		    	  if(post.getPicture()!=null)
			      {
		    		   Log.append("Post picture link    :"+ post.getPicture()+"\n");
			      imagedetection i =new imagedetection();
			      try {
					i.saveImage(post.getPicture(),"feedimage.jpg");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			      }
		    	  
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
		    		  if(writer.Friends_sheet(post.getId(), post.getFrom().getName(),  post.getMessage(),commentscnt, post.getSharesCount(), post.getApplication(), post.getCaption(), post.getDescription(), post.getPicture(), post.getCreatedTime().getDate() +"-"+post.getCreatedTime().getMonth(),"entertainment" )==false)
					     {
					    	 Log.append("writing data into base facebookdata.xls failed"+"\n");
					    	 
					     }
		    	  }
		    	  else if(FriendExists)// decides as Life event post
		    	  {
		    		  if(writer.Friends_sheet(post.getId(), post.getFrom().getName(),  post.getMessage(),commentscnt, post.getSharesCount(), post.getApplication(), post.getCaption(), post.getDescription(), post.getPicture(), post.getCreatedTime().getDate() +"-"+post.getCreatedTime().getMonth(),"life" )==false)
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
		      else if(post.getFrom().getCategory()!=null)// decides as like page post
		      {
		    	  if(writer.Like_sheet(post.getId(), post.getFrom().getName(),  post.getMessage(),post.getLikesCount(), post.getSharesCount(), post.getApplication(), post.getCaption(), post.getDescription(), post.getLink(), post.getCreatedTime().getDate() +"-"+(post.getCreatedTime().getMonth()) )==false)
				     {
				    	 Log.append("writing data into base facebookdata.xls failed"+"\n");
				     }
		      }
		     
		       commentscnt=0;
		    
		              }
		       
		    }
		  jb.setValue(i);  
		  i=i+35;  
		}
		Exit.setEnabled(true);
		setVisible(false);
		File file = new File("FacebookData.xls");
        try
        {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
			Main window = new Main();
			window.open();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
