import java.io.*;

import weka.core.*;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
 
public class TextDirectoryToArff 
{ 
  public Instances createDatasetLearning(String directoryPath,String Classifier) throws Exception 
  {
 
	 String arffFileName=null;
	if(Classifier.equals("Naive Bayes"))
	{
		arffFileName="NaiveBayesFacebookData.arff";
	}
    FastVector atts = new FastVector(2);
    atts.addElement(new Attribute("filename", (FastVector) null));
    atts.addElement(new Attribute("contents", (FastVector) null));
    Instances data = new Instances("text_files_in_" + directoryPath, atts, 0);
    
    File dir = new File(directoryPath);
    String[] files = dir.list();
    String Fname=null;
    for (int i = 0; i < files.length; i++) {
    	if(files[i].endsWith("t.txt"))
    	{
    		Fname="entertainment";
    	}
    	else
    	{
    		Fname="life";
    	}
    	
    	
      if (files[i].endsWith(".txt")) {
    	  System.out.println(files[i]+"hiiiiiiiiii");
    try {
      double[] newInst = new double[2];
      newInst[0] = (double)data.attribute(0).addStringValue(Fname);
      File txt = new File(directoryPath + File.separator + files[i]);  
      InputStreamReader is;
      is = new InputStreamReader(new FileInputStream(txt));
      StringBuffer txtStr = new StringBuffer();
      int c;
      while ((c = is.read()) != -1) {
        txtStr.append((char)c);
      }
      newInst[1] = (double)data.attribute(1).addStringValue(txtStr.toString());
      data.add(new Instance(1.0, newInst));
     
    } catch (Exception e) {
      //System.err.println("failed to convert file: " + directoryPath + File.separator + files[i]);
    }
      }
     
    }
    System.out.println(data);
    ArffSaver saver = new ArffSaver();
    saver.setInstances(data);
    saver.setFile(new File("Myarff.arff"));
    saver.writeBatch();
    
    
    try {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(arffFileName, true)));
		BufferedReader br = new BufferedReader(new FileReader("Myarff.arff"));
		String line=null;
		line = br.readLine();
		line = br.readLine();
		line = br.readLine();
		line = br.readLine();
		line = br.readLine();
		line = br.readLine();
		line = br.readLine();
		
		//System.out.println(line);
	    while ((line = br.readLine())!=null) 
		{
		  	  out.println(line+"\n");
		}
		br.close();
		out.close();	      
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    File myFile = new File("Myarff.arff");   
    myFile.delete();  
    return data;
  }
 
  public static void main(String[] args) throws IOException {
 
	  
   /*   TextDirectoryToArff tdta = new TextDirectoryToArff();
      try
      {
         Instances dataset = tdta.createDatasetLearning("FacebookFeed");
         System.out.println(dataset);
      }
      catch (Exception e) 
      {
    System.err.println(e.getMessage());
    e.printStackTrace();
      }*/
  }
}