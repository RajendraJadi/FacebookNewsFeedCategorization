import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import weka.classifiers.meta.FilteredClassifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;


public class NavieBayesClassifier {

	 String text;   //string to be teste    
     Instances instances;
     FilteredClassifier classifier;//classifier object
     public void load(String fileName) 
     {
        try 
        {
                     BufferedReader reader = new BufferedReader(new FileReader(fileName));
                     String line;
                     text = "";
                     while ((line = reader.readLine()) != null) 
                     {
                        text = text + " " + line;
                     }
                     System.out.println("===== Loaded text data: " + fileName + " =====");
                     reader.close();
                     System.out.println(text);
         }
         catch (IOException e) 
         {
                   System.out.println("Problem found when reading: " + fileName);
         }
     }
                     
    
     public void loadModel(String fileName) 
     {
             try
             {
               ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
               Object tmp = in.readObject();
               classifier = (FilteredClassifier) tmp;
               in.close();
               System.out.println("===== Loaded model: " + fileName + " =====");
             }
             catch (Exception e) 
             {
                     // Given the cast, a ClassNotFoundException must be caught along with the IOException
                     System.out.println("Problem found when reading: " + fileName);
             }
     }
     
   
     public void makeInstance()
     {
             // Create the attributes, class and text
             FastVector fvNominalVal = new FastVector(2);
             fvNominalVal.addElement("entertainment");
             fvNominalVal.addElement("life");
             Attribute attribute1 = new Attribute("class", fvNominalVal);
             Attribute attribute2 = new Attribute("text",(FastVector) null);
             // Create list of instances with one element
             FastVector fvWekaAttributes = new FastVector(2);
             fvWekaAttributes.addElement(attribute1);
             fvWekaAttributes.addElement(attribute2);
             instances = new Instances("Test relation", fvWekaAttributes, 1);
             // Set class index
             instances.setClassIndex(0);
             // Create and add the instance
             Instance instance = new Instance(2);
            instance.setValue(attribute2, text);
             // Another way to do it:
             // instance.setValue((Attribute)fvWekaAttributes.elementAt(1), text);
             instances.add(instance);
              System.out.println("===== Instance created with reference dataset =====");
             System.out.println(instances);
     }
     
     
     public String classify() 
     {
     	String Class=null;
     	
             try
             {
                     double pred = classifier.classifyInstance(instances.instance(0));
                     System.out.println("===== Classified instance =====");
                   
                     System.out.println("predicted value: " + pred);
                     System.out.println("Class predicted: " + instances.classAttribute().value((int) pred));
                     Class=instances.classAttribute().value((int) pred);
             }
             catch (Exception e) 
             {
                     System.out.println("Problem found when classifying the text");
             }                
             return Class;
     }
     
     String GetClass(String Message) throws FileNotFoundException, UnsupportedEncodingException
     {
     	 String ClassDetected=null;
     	 PrintWriter writer = new PrintWriter("post.txt");
     	 writer.println(Message);
     	 writer.close();
     	NavieBayesClassifier classifier;
          {
                  classifier = new NavieBayesClassifier();
                  classifier.load("post.txt");
                  classifier.loadModel("NaiveBayesfacebookClassifier.dat");
                  classifier.makeInstance();
                  ClassDetected=classifier.classify();
          }
     	return ClassDetected;
     }
     
     public static void main (String[] args) 
     {
     
    	 NavieBayesClassifier classifier;
            
             {
                     classifier = new NavieBayesClassifier();
                     classifier.load("post.txt");
                     classifier.loadModel("NaiveBayesfacebookClassifier.dat");
                     classifier.makeInstance();
                     classifier.classify();
             }
     }
}
