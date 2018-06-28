
import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.swing.JTextArea;
import javax.swing.JButton;



public class AutomatedTestBuilder extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
				
					AutomatedTestBuilder frame = null;
					try {
						frame = new AutomatedTestBuilder(0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setVisible(true);
				
		
	}

	// from variable is taken to know which from object it is initiated and to decide excel file name 
	public AutomatedTestBuilder(int from) throws IOException 
	{
		String UpdateExcelFile=null;
		if(from==1)
		{
			UpdateExcelFile="FacebookData.xls";
		}
		else if(from==0)
		{
		 UpdateExcelFile=JOptionPane.showInputDialog("Give the Excel File name to be tested");
		}
		 JLabel lblFileName;
		JProgressBar progressBar ;
		  
		 JLabel lblCompleted ;

		setTitle("Building Test Set");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFileName = new JLabel("File Name :");
		lblFileName.setBounds(12, 12, 412, 16);
		contentPane.add(lblFileName);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(12, 92, 553, 29);
		contentPane.add(progressBar);
		
		lblCompleted = new JLabel("Completed");
		lblCompleted.setBounds(12, 65, 401, 16);
		contentPane.add(lblCompleted);
		validate();
		

		 JLabel lblNewLabel = new JLabel(new ImageIcon("TestGIF.gif"));
		 lblNewLabel.setBounds(398, 0, 167, 92);
		 contentPane.add(lblNewLabel);
		
		
		this.setVisible(true);
		
		
		/*String UpdateExcelFile =null;
		 BufferedReader br;
	
			br = new BufferedReader(new FileReader("temp.txt"));
			UpdateExcelFile = br.readLine();
			br.close();
			
		*/
			 lblFileName.setText("File Name: "+UpdateExcelFile);
			 
			 JTextArea textArea = new JTextArea();
			 textArea.setBounds(12, 135, 553, 227);
			 contentPane.add(textArea);
		HSSFWorkbook workbook=null;
	    HSSFSheet sheet=null;
	    int TotalRows=0;
		FileInputStream file=null;
		 try 
		 {
		     file = new FileInputStream(new File(UpdateExcelFile));
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
	  try 
	  {
		file.close();
	  }
	  catch (IOException e) 
	  {
		e.printStackTrace();
	  }
	  JScrollPane scroll = new JScrollPane (textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	  scroll.setBounds(12, 135, 553, 227);
	  getContentPane().add(scroll);
	  
	  JButton btnBack = new JButton("back");
	  btnBack.setBounds(479, 398, 86, 20);
	  contentPane.add(btnBack);
	  btnBack.setEnabled(false);
	  progressBar.setStringPainted(true);
	  ExcelReader a=new ExcelReader();
	  String Message=null;
	  boolean Classified=false;
	  String ClassifiedValue=null;
	  String ClassNameToUpdate=null;
	  for(int i=2;i<TotalRows;i++)
	  {
		    
		  try {
			Message=a.ReadExcelSheet(UpdateExcelFile, i, 3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  textArea.append("Message :"+Message+"\n");
		 // System.out.println(Message);
		  try {
			ClassifiedValue=a.ReadExcelSheet(UpdateExcelFile, i, 11);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // System.out.println(ClassifiedValue);
		  if(!ClassifiedValue.equals("-"))
		  {
			  if(ClassifiedValue.equals("entertainment(Tag)"))
				  ClassNameToUpdate="entertainment";
			  else if(ClassifiedValue.equals("life(Tag)"))
				  ClassNameToUpdate="life";
			  textArea.append("Classified by Tag classifier ");
			  
			  Classified=true;
		  }
		  
		  if( Classified==false)
		  {
			  
			  //building classifier
			  NavieBayesClassifier classifier= new NavieBayesClassifier();
			  try {
				ClassNameToUpdate=classifier.GetClass(Message);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 
		  }
		  textArea.append("Real class "+ ClassifiedValue+"\n");
		  textArea.append("Predicted class "+ ClassNameToUpdate+"\n\n\n");
		  a.UpdateSheetToLearn(UpdateExcelFile, ClassNameToUpdate,i-1, 11);
		  Classified=false;
		  lblCompleted.setText("Completed "+i+" posts out of "+TotalRows);
		  progressBar.setValue((int) (((float)i/(float)TotalRows)*(float)100));
		
		
	  }
	 btnBack.setEnabled(true);
	 if(from==0)
	 {
	 File file1 = new File(UpdateExcelFile);
     try
     {
         Desktop.getDesktop().open(file1);
     } catch (IOException e) {
         e.printStackTrace();
     }
     this.dispose();
     this.setVisible(false);
     this.dispose();
	 }
     /*
     NavieBayesLearner Ln=new NavieBayesLearner();
     String summary= Ln.EvaluateDriver("NaiveBayesFacebookData.arff");
	 JFrame frame = new JFrame("Results of presently classified data through NavieBayes");
	 frame.setBounds(100, 100, 506, 620);
		textArea = new JTextArea();
		textArea.setBounds(5, 270, 470, 300);
	    frame.add(textArea);
		textArea.setText(summary);
		frame.setVisible(true);
		*/
	}
}
