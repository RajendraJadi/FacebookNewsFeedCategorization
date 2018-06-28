

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;



import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;


import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.User;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.custom.StyledText;


public class Main {

	protected Shell shlFacebookNewsFeed;
	Label label_2;
	Button btnGraphicalAnalysis;
	Button btnNewButton_1;
	int postcount=1;
	int fricount=0;
	int likecount=0;
	Button button_2 ;
	ProgressBar progressBar;
	Button btnExit;
    Button btnUpdateExcelSheet;
    Button btnOpenExcelSheet;
    Button btnTrainClassifier;
    int FeedDisplaySwitch=0;
    CLabel lblUserName;
    CLabel lblGender;
    Button button;
    Button btnChangeUser;
    Label label;
    private Button button_3;
    private Label label_3;
    private Button button_4;
    private Button button_5;
    Button btnNewButton;
    Composite composite;
    Composite composite_1;
    Composite composite_2;
    StyledText TextLife;
    private StyledText TextEntertainment;
    private StyledText TextLiked;
    private Label label_1;
    private Label label_4;
    private Label label_5;
    private Label label_6;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		 System.out.println(System.getProperty("user.dir"));
		try {
			Main window = new Main();
			window.open();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlFacebookNewsFeed.open();
		shlFacebookNewsFeed.layout();
		while (!shlFacebookNewsFeed.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws IOException 
	 */
	protected void createContents() 
	{
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		shlFacebookNewsFeed = new Shell();
		shlFacebookNewsFeed.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\logo.png"));
		shlFacebookNewsFeed.setSize(1404, 745);
		shlFacebookNewsFeed.setLocation(0, 0);
		//shlFacebookNewsFeed.setSize(1404, 724);
		//shlFacebookNewsFeed.setMinimumSize(new Point(10, 10));
		shlFacebookNewsFeed.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//shlFacebookNewsFeed.setSize(1248, 640);
		shlFacebookNewsFeed.setText("Facebook News Feed Classifier");
		
		//shows user
		button = new Button(shlFacebookNewsFeed, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\user.jpg"));
		//button.setBounds(278, 100, 89, 84);
		
		Label label_2 = new Label(shlFacebookNewsFeed, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(0, 94, 1352, 2);
		
		
		
		
		btnChangeUser = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnChangeUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				shlFacebookNewsFeed.close();
				
				try 
				{
					ChangeAccessToken a=new ChangeAccessToken();
					
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		//btnChangeUser.setBounds(20, 154, 146, 25);
		btnChangeUser.setText("Not you?? Change User");

		lblUserName = new CLabel(shlFacebookNewsFeed, SWT.NONE);
		lblUserName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//lblUserName.setBounds(10, 100, 256, 21);
		lblUserName.setText("Accessing user name please wait...");	
		
        lblGender = new CLabel(shlFacebookNewsFeed, SWT.SHADOW_NONE);
		lblGender.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblGender.setText("");
		//lblGender.setBounds(10, 127, 256, 21);
		FacebookClient facebookClient = null;
		String AccessToken = null;
		button_2 = new Button(shlFacebookNewsFeed, SWT.NONE);
		//button_2.setEnabled(false);
		button_2.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\OPEN.png"));
		int flag=0;
		try
		{
		  BufferedReader br = new BufferedReader(new FileReader("AccessToken.txt"));	 
		  AccessToken = br.readLine();
		  br.close();
		  
		  facebookClient= new DefaultFacebookClient(AccessToken);
		  
		  User user = facebookClient.fetchObject("me", User.class);
		  imagedetection a= new imagedetection();
		  a.saveImage("http://graph.facebook.com/"+user.getId()+"/picture?width=100&height=100","Profile.jpg");
		  lblUserName.setText("User Name: "+user.getName());	
		  lblGender.setText("Gender   : "+user.getGender());
		  button_2.setEnabled(true);
		   // System.out.println(facebookClient.fetchObject("me", User.class, Parameter.with("fields",pictureUrl)));
		 // setGUI();
		  //using graph API to fetch user imager url ,saving image then displayinh on main GUI
		 
		  button.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\Profile.jpg"));
		  flag=1;
		}
		catch(Exception e)
		{
			
			lblUserName.setText("User Name: <Cannot access>");	
			lblGender.setText("App is in offline mode. Get connected to facebook" );
			btnChangeUser.setBounds(20, 154, 365, 25);
			btnChangeUser.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\connect.png"));

			
			Button button_2 = new Button(shlFacebookNewsFeed, SWT.NONE);
			button_2.setEnabled(false);
			
			/*shlFacebookNewsFeed.close();
			try 
			{
				 
				new ChangeAccessToken();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}*/
		}
		
		if(flag==1)
			{
			button.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\Profile.jpg"));
			button_2.setEnabled(true);
			}
		
		
		
		
		btnUpdateExcelSheet = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnUpdateExcelSheet.setToolTipText("Update Excel Sheet");
		btnUpdateExcelSheet.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\Update.png"));
		btnUpdateExcelSheet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{   
				shlFacebookNewsFeed.close();		
				new UpdateExcelSheet(); 
			}
		});
		//btnUpdateExcelSheet.setBounds(20, 198, 140, 100);
		
		btnOpenExcelSheet = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnOpenExcelSheet.setToolTipText("Open Stored Facebook Data excel aheet");
		btnOpenExcelSheet.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\ExcelFile.png"));
		btnOpenExcelSheet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				File file = new File("FacebookData.xls");
		        try {
		            Desktop.getDesktop().open(file);
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }

			}
		});
		//btnOpenExcelSheet.setBounds(265, 221, 128, 55);
		
		btnTrainClassifier = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnTrainClassifier.setToolTipText("Train SVM classifier with pre fetched data");
		btnTrainClassifier.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\svm.jpg"));
		btnTrainClassifier.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TrainingData window = new TrainingData();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		//btnTrainClassifier.setBounds(20, 329, 140, 75);
		
		btnGraphicalAnalysis = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnGraphicalAnalysis.setText("Automated Learner");
		btnGraphicalAnalysis.setToolTipText("Graphical analysis of Categorized Data");
		btnGraphicalAnalysis.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//new ScatterPoints("points");
				EventQueue.invokeLater(new Runnable() {

		            @Override
		            public void run() {
		            	AutomatedLiveLearner a= new AutomatedLiveLearner();
		                
		            }
		        });
				
				 //BarChartDemo1 demo = new BarChartDemo1("Bar Chart Demo 1");
			     //   demo.pack();
			      //  RefineryUtilities.centerFrameOnScreen(demo);
			      //  demo.setVisible(true);
			}
		});
		btnGraphicalAnalysis.setImage(null);
		//btnGraphicalAnalysis.setBounds(20, 440, 128, 71);
		
		btnExit = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnExit.setToolTipText("Quit application");
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\exit.jpg"));
		//btnExit.setBounds(278, 641, 128, 37);
		
		Button button_1 = new Button(shlFacebookNewsFeed, SWT.NONE);

		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_1.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\Title.png"));
		button_1.setBounds(10, 28, 686, 48);
		
		btnNewButton_1 = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AutomatedTestBuilder frame;
				try {
					frame = new AutomatedTestBuilder(0);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
					
						
				
			
			}
		});
		btnNewButton_1.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\test.jpg"));
		//btnNewButton_1.setBounds(265, 345, 115, 42);
		
		label = new Label(shlFacebookNewsFeed, SWT.SEPARATOR | SWT.HORIZONTAL);
		//label.setBounds(0, 190, 406, 2);
		 
		progressBar = new ProgressBar(shlFacebookNewsFeed, SWT.NONE);
		
		btnNewButton = new Button(shlFacebookNewsFeed, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\friends.png"));
		button_3 = new Button(shlFacebookNewsFeed, SWT.NONE);
		button_3.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\like.jpg"));
		label_3 = new Label(shlFacebookNewsFeed, SWT.SEPARATOR | SWT.HORIZONTAL);
		button_4 = new Button(shlFacebookNewsFeed, SWT.NONE);
		button_4.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\lifeevent.png"));
		button_5 = new Button(shlFacebookNewsFeed, SWT.NONE);
		button_5.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\entertainmentEvent.png"));
		composite = new Composite(shlFacebookNewsFeed, SWT.NONE);
		
		TextEntertainment = new StyledText(composite, SWT.BORDER);
		
		composite_1 = new Composite(shlFacebookNewsFeed, SWT.NONE);
		
		TextLiked = new StyledText(composite_1, SWT.BORDER);
	
		composite_2 = new Composite(shlFacebookNewsFeed, SWT.NONE);
		
		TextLife = new StyledText(composite_2, SWT.BORDER);
		
		label_1 = new Label(shlFacebookNewsFeed, SWT.SEPARATOR | SWT.VERTICAL);
		
		label_5 = new Label(shlFacebookNewsFeed, SWT.SEPARATOR);
		label_4 = new Label(shlFacebookNewsFeed, SWT.SEPARATOR);
		label_4.setBounds(639, 137, 5, 570);
		label_1.setBounds(994, 94, 5, 603);
		label_5.setBounds(93, 94, 5, 603);
		
		label_6 = new Label(shlFacebookNewsFeed, SWT.SEPARATOR);
		
		
		
		
		
		
		//button_2.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\OPEN.png"));
		button_2.addSelectionListener(new SelectionAdapter() {
			 int btnflag=1;
			 
			public void widgetSelected(SelectionEvent e) 
			{
				if(btnflag==1)
				{
					
					setGUI();
					btnflag=0;
					
					showclassifiedfeed();
					
				}
				
				else if(btnflag==0)
				{
					btnflag=1;
					resetGUI();
					
			       
				}
				
			   
			     
				
			}
		});
	//	button_2.setBounds(621, 100, 75, 545);
		
		
		resetGUI();
	}
	void setGUI()
	{
		
		
		
		
		btnGraphicalAnalysis.setEnabled(false);
		btnGraphicalAnalysis.setBounds(20, 440, 128, 71);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(265, 345, 115, 42);
		btnExit.setEnabled(false);
		btnExit.setBounds(278, 641, 128, 37);
	    btnUpdateExcelSheet.setEnabled(false);
	    btnUpdateExcelSheet.setBounds(20, 198, 140, 100);
	    btnOpenExcelSheet.setEnabled(false);
	    btnOpenExcelSheet.setBounds(265, 221, 128, 55);
	    btnTrainClassifier.setEnabled(false);
	    btnTrainClassifier.setBounds(20, 329, 140, 75);
		
		//progressBar.setBounds(0, 0, 1370, 8);
		//progressBar.setVisible(true);
		
		button_2.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\CLOSE.png"));
		
		int graph=20;
		int btn1=265;
		int exit=278;
		int update=20;
		int open=265;
		int train=20;
		
		//move buttons
		for (int i=0;i<400;i++)
		{
			graph--;
			btn1--;
			exit--;
			update--;
			open--;
			train--;
			
		
			btnGraphicalAnalysis.setBounds(graph, 440, 128, 71);
			btnNewButton_1.setBounds(btn1, 345, 115, 42);
			btnExit.setEnabled(false);
			btnExit.setBounds(exit, 641, 128, 37);
		    btnUpdateExcelSheet.setEnabled(false);
		    btnUpdateExcelSheet.setBounds(update, 198, 140, 100);
		    btnOpenExcelSheet.setEnabled(false);
		    btnOpenExcelSheet.setBounds(open, 221, 128, 55);
		    btnTrainClassifier.setEnabled(false);
		    btnTrainClassifier.setBounds(train, 329, 140, 75);
	
		    TextLife.getScrollbarsMode();
		}
		
		
        for(int i=960;i>=0;i--)
        {
	      shlFacebookNewsFeed.setLocation(i/2, 0);
        }
        for(int i=750;i<1550;i++)
        {
	    	shlFacebookNewsFeed.setSize(i, 745);
        }
        button_2.setLocation(10, 100);
		button.setLocation(1250, 5);
		
		lblUserName.setLocation(950, 15);
		lblGender.setLocation(950, 40) ;
		btnChangeUser.setVisible(false) ;
	    label.setVisible(false);
		button_3.setBounds(1086, 100, 230, 31);
		label_3.setBounds(93, 137, 1259, 2);
		button_4.setBounds(238, 145, 137, 31);		
		button_5.setBounds(680, 145, 290, 31);		
		composite.setBounds(650, 182, 338, 515);		
		composite_1.setBounds(1005, 145, 347, 552);		
		composite_2.setBounds(103, 182, 530, 515);	    
		btnNewButton.setBounds(513, 100, 183, 31);
		TextLife.setBounds(10, 10, 510, 495);
		TextEntertainment.setBounds(10, 10, 318, 495);
		TextLiked.setBounds(10, 10, 327, 532);
		label_6.setBounds(3000, 94, 5, 603);
		
		
	}
	
	void resetGUI()
	{
	    
        label_1.setBounds(3000, 94, 5, 603);
		button_3.setBounds(3000, 100, 230, 31);
		label_3.setBounds(3000, 137, 1259, 2);
		label_4.setBounds(3000, 94, 5, 603);		
		label_5.setBounds(3000, 129, 5, 603);		
		button_4.setBounds(3000, 145, 137, 31);		
		button_5.setBounds(3000, 145, 290, 31);		
		composite.setBounds(3000, 182, 338, 515);		
		composite_1.setBounds(3000, 145, 347, 552);		
		composite_2.setBounds(3000, 182, 530, 515);	    
		btnNewButton.setBounds(3000, 100, 183, 31);
		TextLife.setBounds(3000, 10, 510, 495);
		TextEntertainment.setBounds(3000, 10, 318, 495);
		TextLiked.setBounds(3000, 10, 327, 532);
		
		
		
		
		progressBar.setVisible(false);
		button_2.setBounds(621, 100, 75, 607);
		button.setBounds(278, 100, 89, 84);
		label.setBounds(0, 190, 406, 2);
		button_2.setImage(SWTResourceManager.getImage("C:\\Users\\sony\\Desktop\\7 th sem\\Capston Project\\workspace\\coding\\FacebookNewsFeedCategorization\\OPEN.png"));
		 for(int i=1550;i>750;i--)
	        {
		    	shlFacebookNewsFeed.setSize(i, 745);
	        }
        for(int i=0;i<=900;i++)
        {
	      shlFacebookNewsFeed.setLocation(i/2, 0);
        }
        
        lblUserName.setBounds(10, 100, 256, 21);
        btnGraphicalAnalysis.setEnabled(true);
		btnGraphicalAnalysis.setBounds(20, 440, 128, 71);
		btnNewButton_1.setEnabled(true);
		btnNewButton_1.setBounds(265, 345, 115, 42);
		btnExit.setEnabled(true);
		btnExit.setBounds(278, 641, 128, 37);
	    btnUpdateExcelSheet.setEnabled(true);
	    btnUpdateExcelSheet.setBounds(20, 198, 140, 100);
	    btnOpenExcelSheet.setEnabled(true);
	    btnOpenExcelSheet.setBounds(265, 221, 128, 55);
	    btnTrainClassifier.setEnabled(true);
	    btnTrainClassifier.setBounds(20, 329, 140, 75);
		button.setLocation(278, 100);
		
		lblGender.setLocation(10, 127) ;
		btnChangeUser.setVisible(true) ;
	    label.setVisible(true);	
	    lblUserName.setLocation(10, 100);
	    btnChangeUser.setBounds(20, 154, 197, 25);
	    label_6.setBounds(412, 94, 5, 603);
       
	     
	    try
		{
	    	FacebookClient facebookClient = null;
	    	String AccessToken=null;
		  BufferedReader br = new BufferedReader(new FileReader("AccessToken.txt"));	 
		  AccessToken = br.readLine();
		  br.close();
		  
		  facebookClient = new DefaultFacebookClient(AccessToken);
		  User user = facebookClient.fetchObject("me", User.class);
		  button_2.setEnabled(true);
		  
		}
		catch(Exception e)
		{
			
			button_2.setEnabled(false);
			
		}
	    
	}
	
	
	void showclassifiedfeed()
	{
		String AccessToken=null;
		
		JProgressBar jb=new JProgressBar();
		JFrame Loading = new JFrame("Loading News Feed Please wait...");
		Loading.setUndecorated(true);
		Loading.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
	  
	   Loading.setAlwaysOnTop(true);
	   Loading.setEnabled(false);
	
		
		Loading.getContentPane().add(jb);
		Loading.setIconImage(null);
		jb.setIndeterminate(true);
		Loading.setVisible(true);
		Loading.setBounds(-19, 130, 1500, 40);
		Loading.setResizable(false);
		jb.setBounds(0, 0, 1000, 10);
		
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
		
		FacebookClient facebookClient = null;
		try
		{
		facebookClient= new DefaultFacebookClient(AccessToken);
		
		}
		catch(Exception e)
		{
			
        	System.out.println("accesstoken error");
		}
		
		
		
		int i=0;
		
		Connection<Post> myFeed = facebookClient.fetchConnection("me/home", Post.class);

        //displaying some user data	
		
		// Connections getting feed, writing data and are iterable
		for (List<Post> newsFeedConnectionPage : myFeed)
		{
		
		  for (Post post : newsFeedConnectionPage)
		  {
		 
			 
		    //MORE THAN ONE VALUE
		       if(post.getComments()==null)
		       {
		       	   
		       }
		       else
		       {
		    	     
		      //write the sheet into the FacebookData.xls file using our ExcelWriter class
		      if(post.getFrom().getCategory()==null)// decides as friends posts
		      {
		    	  
		    	  
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
		    	  {
		    		  TextEntertainment.append("Name  :"+post.getFrom().getName()+"\n");
		    		  TextEntertainment.append("Post  :"+post.getMessage()+"\n");
		    		  TextEntertainment.append("--------------------------------------------------------------------------------"+"\n");
		    		  TextEntertainment.append("DECIDED BY TAG CLASSIFIER"+"\n");
		    		  TextEntertainment.append("--------------------------------------------------------------------------------"+"\n\n\n");
		    	  }
		    	  else if(FriendExists)// decides as Life event post
		    	  {
		    		  TextLife.append("Name  :"+post.getFrom().getName()+"\n");
		    		  TextLife.append("Post  :"+post.getMessage()+"\n");
		    		  TextLife.append("--------------------------------------------------------------------------------"+"\n");
		    		  TextLife.append("DECIDED BY TAG CLASSIFIER"+"\n");
		    		  TextLife.append("--------------------------------------------------------------------------------"+"\n\n\n");
	    			  
		    	  }
		    	  else  //this will be not classified
		    		  {
		    			  
		    		  // WRITE CODE OF CLASSIFIER
		    		  String ClassNameToUpdate=null;
		    		  NavieBayesClassifier classifier= new NavieBayesClassifier();
					  try {
						ClassNameToUpdate=classifier.GetClass(post.getMessage());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  //System.out.println(Message);
					  System.out.println("Prededcted Class ==="+ClassNameToUpdate);
					  if(ClassNameToUpdate.equals("life"))
					  {
						  TextLife.append("Name  :"+post.getFrom().getName()+"\n");
			    		  TextLife.append("Post  :"+post.getMessage()+"\n");
			    		  TextLife.append("--------------------------------------------------------------------------------"+"\n");
			    		  TextLife.append("DECIDED BY NAVIE BAIS CLASSIFIER"+"\n");
			    		  TextLife.append("--------------------------------------------------------------------------------"+"\n\n\n");
					  }
					  if(ClassNameToUpdate.equals("entertainment"))
					  {
						  TextEntertainment.append("Name  :"+post.getFrom().getName()+"\n");
			    		  TextEntertainment.append("Post  :"+post.getMessage()+"\n");
			    		  TextEntertainment.append("--------------------------------------------------------------------------------"+"\n");
			    		  TextEntertainment.append("DECIDED BY NAVIE BIAS CLASSIFIER"+"\n");
			    		  TextEntertainment.append("--------------------------------------------------------------------------------"+"\n\n\n");
					  }
					  
		    		  
		    		  }
			    	  
		    	  
		    	  
		      }
		      else if(post.getFrom().getCategory()!=null)// decides as like page post
		      {
		    	  TextLiked.append("Name  :"+post.getFrom().getName()+"\n");
	    		  TextLiked.append("Post  :"+post.getMessage()+"\n");
	    		  TextLiked.append("--------------------------------------------------------------------------------"+"\n");
	    		  TextLiked.append("DECIDED BY TAG CLASSIFIER"+"\n");
	    		  TextLiked.append("--------------------------------------------------------------------------------"+"\n\n\n");
		      }
		     
		      
		    
		              }
		       
		    }
		 // progressBar.setState(i);  
		  i=i+35;  
		}
		
		Loading.dispose();
		
		
		
		
		
		
		
		
		
	}
}
