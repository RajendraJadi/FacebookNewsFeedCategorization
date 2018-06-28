import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class NavieBayesLearner {

	Instances trainData;
    
    StringToWordVector filter;
    FilteredClassifier classifier;
            
  
    public void loadDataset(String fileName) {
            try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    ArffReader arff = new ArffReader(reader);
                    trainData = arff.getData();
                    System.out.println("===== Loaded dataset: " + fileName + " =====");
                    reader.close();
            }
            catch (IOException e) {
                    System.out.println("Problem found when reading: " + fileName);
            }
    }
    
   
    public String evaluate()
    {
    	 String Summary=null;
         String Details=null;
            try {
                    trainData.setClassIndex(0);
                    filter = new StringToWordVector();
                    filter.setAttributeIndices("last");
                    classifier = new FilteredClassifier();
                    classifier.setFilter(filter);
                    classifier.setClassifier(new NaiveBayes());
                    Evaluation eval = new Evaluation(trainData);
                    eval.crossValidateModel(classifier, trainData, 4, new Random(1));
                   
                   
                    Summary=eval.toSummaryString();
                    Details=eval.toClassDetailsString();
                    System.out.println(eval.toSummaryString());
                    System.out.println(eval.toClassDetailsString());
                    System.out.println("===== Evaluating on filtered (training) dataset done =====");
                    
            }
            catch (Exception e) {
                    System.out.println("Problem found when evaluating");
            }
            return Summary+Details;
    }
    
 
    public void learn() {
            try {
            	//building classifier
                    trainData.setClassIndex(0);
                    filter = new StringToWordVector();
                    filter.setAttributeIndices("last");
                    classifier = new FilteredClassifier();
                    classifier.setFilter(filter);
                    classifier.setClassifier(new NaiveBayes());
                    classifier.buildClassifier(trainData);
                    // Uncomment to see the classifier
                     System.out.println(classifier);
                    System.out.println("===== Training on filtered (training) dataset done =====");
            }
            catch (Exception e) {
                   e.printStackTrace();
            }
    }
    
   
    public void saveModel(String fileName) {
            try {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(classifier);
        out.close();
                     System.out.println("===== Saved model: " + fileName + " =====");
    }
            catch (IOException e) {
                    System.out.println("Problem found when writing: " + fileName);
            }
    }
    
   public void LearnDriver(String arffName, String DatName)
   {
	   NavieBayesLearner learner;           
       learner = new NavieBayesLearner();
       learner.loadDataset(arffName);
       // Evaluation mus be done before training
        
	  
       learner.learn();
      
       learner.saveModel(DatName);
       //learner.evaluate();
   }
   
   public String EvaluateDriver(String arffName)
   {
	   String Summary=null;
	   NavieBayesLearner learner;               
       learner = new NavieBayesLearner();
       learner.loadDataset(arffName);
       // Evaluation mus be done before training
       Summary= learner.evaluate();
	   return Summary;
   }
   
    public static void main (String[] args) 
    {
    	
    	NavieBayesLearner learner;               
            learner = new NavieBayesLearner();
            learner.loadDataset("NaiveBayesFacebookData.arff");
            // Evaluation mus be done before training
            //learner.evaluate();
            learner.evaluate();

            //learner.saveModel("NaiveBayesfacebookClassifier.dat");
            
    }
}
