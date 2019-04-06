import edu.stanford.nlp.ling.CoreAnnotations;
	import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
	import edu.stanford.nlp.pipeline.Annotation;
	import edu.stanford.nlp.pipeline.StanfordCoreNLP;
	import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
	import edu.stanford.nlp.trees.Tree;
	import edu.stanford.nlp.util.CoreMap;
	import java.util.*; 

public class Sentiment {
		 
	    public int findSentiment(String line) {
	 
	        Properties props = new Properties();
	        //set what the annotator will do 
	        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
	        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	        int mainSentiment = 0;
	        if (line != null && line.length() > 0) {
	            int longest = 0;
	            
	            // annotate the input string 
	            Annotation annotation = pipeline.process(line);
	            
	            // iterate through the CoreMaps for each sentance, which are classes mapped to specific 
	            // types used to hold annotations
	            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
	                
	            	// create the parse tree with sentiment annotations 
	            	Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
	            	
	            	//pass the tree through the recursive neural network 
	                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
	                
	                String partText = sentence.toString();
	                if (partText.length() > longest) {
	                    mainSentiment = sentiment;
	                    longest = partText.length();
	                }
	            }
	        }
	        return mainSentiment; 
	        
	 
	    }
}
