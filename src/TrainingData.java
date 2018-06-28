import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;


public class TrainingData {

	JFrame frame;
	private JTextField txtFacebookdataxls;
	private JLabel lblEntertainmentNo;
	private JLabel lblStartingIndexNo;
	private JLabel lblLife;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnBack;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public TrainingData() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		// set the GUI
		
		frame = new JFrame("Train the data");
		frame.setBounds(100, 100, 506, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblProvideThePath = new JLabel("Provide the path to the excel sheet");
		lblProvideThePath.setBounds(12, 12, 477, 16);
		frame.getContentPane().add(lblProvideThePath);
		
		txtFacebookdataxls = new JTextField();
		txtFacebookdataxls.setText("FacebookData.xls");
		txtFacebookdataxls.setBounds(12, 40, 477, 20);
		frame.getContentPane().add(txtFacebookdataxls);
		txtFacebookdataxls.setColumns(10);	
		
		btnBack = new JButton("Back");
		btnBack.setBounds(5, 238, 165, 20);
		frame.getContentPane().add(btnBack);
		
		JButton btnGenerateTrainingData = new JButton("Generate Training Data");
		btnGenerateTrainingData.setBounds(5, 165, 165, 20);
		frame.getContentPane().add(btnGenerateTrainingData);
		
		JLabel labelSelectClassifier = new JLabel("Select the classifier below to train");
		labelSelectClassifier.setBounds(3, 60, 250, 30);
		frame.getContentPane().add(labelSelectClassifier);
		
		//set combobox
		String[] ClassifierNames = { "Naive Bayes" };
		final JComboBox Classifier = new JComboBox(ClassifierNames);
		Classifier.setBounds(3, 90, 200, 30);
		frame.getContentPane().add(Classifier);		
		
		frame.setAlwaysOnTop(true);
			
		//Add action listener to both the buttons
		btnGenerateTrainingData.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.setVisible(false);
				
				JFrame frame1= new JFrame("Please wait till it get's trained");
				frame1.setBounds(200, 200, 500, 60);
				frame1.setVisible(true);
				final JProgressBar jb=new JProgressBar();
				jb.setIndeterminate(true);
				jb.setBounds(5, 190, 480, 20);
				frame1.add(jb);
				jb.setVisible(true);
				//read combobox selected value
				String ClassifierSelected= Classifier.getSelectedItem().toString();
				
				System.out.println(ClassifierSelected);
				new File(System.getProperty("user.dir")+"/FacebookFeed").mkdir();
				int Eno=1;
				int Lno=1;
				String path = txtFacebookdataxls.getText();
				FileInputStream file = null;
				try {
					file = new FileInputStream(new File(path));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
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
			     
			    //Iterate through each rows from first sheet
			    Iterator<Row> rowIterator = sheet.iterator();
			    while(rowIterator.hasNext()) {
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
			            System.out.print(cell.getStringCellValue() + "\t\t");
			            String category = cell.getStringCellValue();
			            System.out.println(category);
			            
			            //Create files in directory FacebookFeed to be used for creation of arff files
			            if(category.equals("life"))
			    
			            {
			            
			            	PrintWriter writer;
							try {
								writer = new PrintWriter(System.getProperty("user.dir")+"/FacebookFeed/"+Lno+"Life.txt", "ASCII");
								writer.println(message);	
				            	writer.close();
				            	Lno++;
							} catch (FileNotFoundException e) {
							
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								
								e.printStackTrace();
							}
			            	
			            }
			           if(category.equals("entertainment"))
			    
			            {
			            	PrintWriter writer;
							try {
								writer = new PrintWriter(System.getProperty("user.dir")+"/FacebookFeed/"+Eno+"Entertainment.txt", "ASCII");
								writer.println(message);	
				            	writer.close();
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
			TextDirectoryToArff a = new TextDirectoryToArff();
			try 
			{
				a.createDatasetLearning("FacebookFeed",ClassifierSelected);
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
			}
			
			

		    
			frame.dispose();
			
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
		            frame1.setVisible(false);
			    frame.setVisible(true);
			   
			  
			} 
		});
		
		
		
		
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
			}
		});
		
		
		
		
	}
}
