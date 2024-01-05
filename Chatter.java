package a6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Chatter {
	
    private String greeting;
    private String goodbye;
    private String responsesPath;

    /**
     * The constructor builds a Chatter object, with 
     * set greeting, goodbye, and potential response phrases.
     * @param greeting a fixed String greeting
     * @param goodbye a fixed String goodbye
     * @param responsesPath the filename for a file with possible chatbot responses
     */
    public Chatter(String greeting, String goodbye, String responsesPath) {
    	this.greeting = greeting;
    	this.goodbye = goodbye;
    	this.responsesPath = responsesPath;
    }
    
    /**
     * Loads lines from a file into a chatbot response map. 
     * The lines have the format of a word followed by
     * a response for the word. The word should be all lowercase and 
     * the response can be any String.
     *           
     * If the file is not found, prints an error and returns an 
     * empty HashMap. Do not use a throws to send the error somewhere
     * else. Deal with it in this method.
     * 
     * @param filePath the String path to the file
     * @return a HashMap with each word as a key and response as a value
     */
    private static HashMap<String, String> loadResponsesFromFile(String filePath) {
    	HashMap<String, String> responses = new HashMap<>();
    	try {
    	File file = new File(filePath);
    	Scanner fileRead = new Scanner(file);
    	String value = "";
    	String key = "";
    	while (fileRead.hasNextLine()){
    		String[] array = fileRead.nextLine().split(" ", 2);
    		key = array[0];
            value = array[1];
    		responses.put(key, value);
    	}
    	}
    	catch(FileNotFoundException e) {
        	System.out.println("File not found");
    	}
    	return responses;
    	
    }
    
    /**
     * Gives the single, set greeting phrase.
     * 
     * @return the greeting String.
     */
    public String sayHello() {
    	return greeting;
    }
    
    /**
     * Gives the single, set goodbye phrase.
     * 
     * @return the goodbye String.
     */
    public String sayGoodbye() {
    	return goodbye;
    }
    
     
    /**
     * Provides a default response if no match is found. The default
     * response should be "Please tell me more about " followed by the 
     * last word in the userPhrase. Assume there is at least one word in the userPhrase.
     * 
     * @param userPhrase a sentence of lower-case words and no punctuation. Assume there is at least one word.
     * @return a response that matches one of the words in userPhrase or a default response.
     */
    public String respondToPhrase(String userPhrase) {
    	Scanner phraseScan = new Scanner(userPhrase);
    	String word = null;
    	HashMap<String, String> responsesHashMap = loadResponsesFromFile(responsesPath);
    	ArrayList<String> phraseList = new ArrayList<>();
    	while (phraseScan.hasNext()) {
    		word = phraseScan.next();
    		if (responsesHashMap.containsKey(word)) {
    			phraseList.add(responsesHashMap.get(word));
    		}
    	}
    	
    	if(phraseList.size() == 0) {
    		return "Please tell me more about " + word;	
    	}
    	
    	Random random = new Random();
    	
    	int index = random.nextInt(phraseList.size());
    	
		return phraseList.get(index);
		   		
    }
    
}