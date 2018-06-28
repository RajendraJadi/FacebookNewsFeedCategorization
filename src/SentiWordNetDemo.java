
import java.io.*;

public class SentiWordNetDemo {

	/**
	 * String that stores the text to guess its polarity.
	 */
	String text;
	
	/**
	 * SentiWordNet object to query the polarity of a word.
	 */
	SentiWordNetDemoCode sentiwordnet=null;
	SentiWordNetDemo()
	{
		try {
	          sentiwordnet = new SentiWordNetDemoCode("SentiWordNet.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
			

 	/**
	 * This method loads the text to be classified.
	 * @param fileName The name of the file that stores the text.
	 */
	public void load(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			text = "";
			while ((line = reader.readLine()) != null) {
				line=line.toLowerCase();
                text = text + " " + line;
            }
			System.out.println("===== Loaded text data: " + fileName + " =====");
			reader.close();
			System.out.println(text);
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}

	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use all POS, say "yes" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyAllPOSY() {
	
		int count = 0;
		try {
			
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			
			String feeling = "";
			
			for (int i = 1; i < tokens.length; ++i) {
				
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals(""))
				{
					// Search as adjetive
				
					feeling = sentiwordnet.extract(tokens[i],"a");
					
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
							
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as noun
					feeling = sentiwordnet.extract(tokens[i],"n");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						 System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as adverb
					feeling = sentiwordnet.extract(tokens[i],"r");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						 System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as verb
					feeling = sentiwordnet.extract(tokens[i],"v");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						 System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			 System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "yes" in case of 0
		if (count >= 0) 
			return "yes";
		else return "no";
	}
	
	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use all POS, say "no" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyAllPOSN() {
	
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";
			for (int i = 0; i < tokens.length; ++i) {
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as noun
					feeling = sentiwordnet.extract(tokens[i],"n");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as adverb
					feeling = sentiwordnet.extract(tokens[i],"r");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as verb
					feeling = sentiwordnet.extract(tokens[i],"v");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "no" in case of 0
		if (count > 0) 
			return "yes";
		else return "no";
	}
	
	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use only ADJ, say "yes" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyADJY() {
	
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";
			for (int i = 0; i < tokens.length; ++i) {
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "yes" in case of 0
		if (count >= 0) 
			return "yes";
		else return "no";
	}
	
	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use only ADJ, say "no" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyADJN() {
	
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";
			for (int i = 0; i < tokens.length; ++i) {
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "no" in case of 0
		if (count > 0) 
			return "yes";
		else return "no";
	}
 
	/**
	 * Main method.
	 * Usage: java SentiWordNetDemo <file>
	 * @param args The command line args.
	 */
	public static void main (String[] args)
	{
		SentiWordNetDemo classifier;
		
			classifier = new SentiWordNetDemo();
			classifier.load("senti.txt");
			// Comment the approaches you do not want to check
			System.out.println(classifier.classifyAllPOSY());
			//System.out.println(classifier.classifyAllPOSN());
			//System.out.println(classifier.classifyADJY());
			//System.out.println(classifier.classifyADJN());			
		
	}
}
