import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ChangeAccessToken extends JFrame
{
	
	JLabel waitL= new JLabel(" Connecting to Facebook ");
	JLabel name =new JLabel("Accessing Name");
	JLabel status =new JLabel("Accessing status");	
	JButton Exit=new JButton("Exit");
	public ChangeAccessToken() throws IOException 
	{
		JFrame wait=new JFrame();
	    //wait.setBounds(400, 250, 610, 200);
		wait.setTitle("Connecting");
		//wait.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		wait.setLocation(550,300);
		wait.setSize(240,100);
		waitL.setBounds(200, 10, 100,100);
		 JLabel im = new JLabel(new ImageIcon("loading1.gif"));
		 im.setBounds(140,0,70,70);
		    wait.add(im);
		wait.add(waitL);
	    
	    wait.setResizable(false);
	    wait.setAlwaysOnTop(true);
	    //wait.setUndecorated(true);
	    wait.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    wait.setVisible(true);
		System.out.println("hi1sdf");    
	    String AccessToken="";
		final FirefoxDriver driver = new FirefoxDriver();
		wait.setVisible(false);		   
		driver.get("https://www.facebook.com/login.php?next=https%3A%2F%2Fdevelopers.facebook.com%2Ftools%2Fexplorer%3Fmethod%3DGET%26path%3D1606400914%253Ffields%253Did%252Cname");
		
		
		
		System.out.println("hi1");
		JFrame a= new JFrame("Timer");
		JLabel l=new JLabel("U have");
		a.add(l);
		a.setAlwaysOnTop(true);
		a.setDefaultCloseOperation(a.DO_NOTHING_ON_CLOSE);
		a.setBounds(700, 400, 200, 70);
		a.setVisible(true);
		for(int i=50;i>0;i--)
		{
		  l.setText("You have "+ i+ " seconds to login");
		  try {
			 Thread.sleep(1000);
		     } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
		System.out.println("hi111111");
		/*AccessToken=JOptionPane.showInputDialog("Please Login to facebook through opened browser and paste accesstoken then press ok");
*/
		
		FileWriter fw1 = new FileWriter("ParsedPage.txt");
		BufferedWriter bw1 = new BufferedWriter(fw1);
		bw1.write(driver.getPageSource());
		bw1.close();
		BufferedReader br = new BufferedReader(new FileReader("ParsedPage.txt"));
		String line;
		int i=0;
		while ((line = br.readLine()) != null) 
		{
			if(i==24)
			{
		   // System.out.println(i+"  "+line);
		    break;
			}
		  i++;
		}
		br.close();
		
		
		try
		{
         String res[] = line.split(",");
         for (int i1 = 0; i1 < res.length; i1++)
         {
         	if(res[i1].length()>100)
         	{
               System.out.println(res[i1]);
               AccessToken=res[i1];
               AccessToken= AccessToken.substring(1, AccessToken.length() - 1); // --> "ello World"
         	}
         }
         
         
         
         FileWriter fw = new FileWriter("AccessToken.txt");
 		BufferedWriter bw = new BufferedWriter(fw);
 		bw.write(AccessToken);
 		bw.close();
		}
		catch(Exception e)
		{
			driver.close();
			a.dispose();
			Main window = new Main();
			
			window.open();
		}
        //System.out.println(res.length);
        
		//System.out.println(driver.getPageSource());
		
		
		
	    driver.close();
	    System.out.println(driver.findElementById("access_token").getText());
	    
		
	    a.dispose();
		Main window = new Main();
	
		window.open();
	   
	}

}
