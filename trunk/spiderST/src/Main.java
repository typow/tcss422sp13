import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Main {
	
	/**
	 * A constant which is the maximum website this program will run. 
	 */
	private static int MAXDEPTH = 10000;
	
public static void main(String[] args) throws IOException {
		
		ArrayDeque<URL> url_queue = new ArrayDeque<>();
		ArrayDeque<BigStruct> pagebuffer = new ArrayDeque<>();
		ArrayDeque<BigStruct> gatherqueue = new ArrayDeque<>();
		ArrayList<String> wordlist = new ArrayList<String>();
		Map<String, Integer> mainmap = new TreeMap<String, Integer>();
		final Scanner input = new Scanner(System.in);	
		
		final String main_url;
	    int amount = gettingInput("How many words do you want to check? ");
		getInput(input, wordlist, amount);
		System.out.print("Enter the URL: ");
		main_url = input.next();
		int depth = gettingDepth("How many websites would you like to search through? ");
		
		if (depth == 0) {
			depth = MAXDEPTH;
		}
		
	    url_queue.addFirst(new URL(main_url)); 
	  
	    
	    while (depth > 0 && !url_queue.isEmpty()) {
	    	URL url = url_queue.getFirst();
	    	BigStruct data = new BigStruct(null, "");
	    	
	    	try {
				Document doc = Jsoup.connect(url.toExternalForm()).get();
				
				data.setDoc(doc);
				data.setURL(url.toString());
												
			} catch (IOException e) {
				// Throw away links that don't work
			}
	    }
	    
	}
	
	/**
	 * getInput() helper method gets the list of words from user input.
	 * @param the_input the input
	 * @param the_wordlist list of word
	 * @return the url
	 */
	public static void getInput(final Scanner the_input, final ArrayList<String> the_wordlist, int the_amount) {  
		for (int i = 0; i < the_amount; i++) {
			System.out.print("Enter a word: ");
			the_wordlist.add(the_input.next());
		}
	}

	/**
	 * Checks for valid input.
	 * If not, it will prompt users for the input again.
	 * @param thetxt the text
	 * @return integer
	 */
	public static int gettingInput(String thetxt) {
	    boolean validinput = false;
	    int result = 0;
	    while (validinput == false) {
	    	Scanner console = new Scanner(System.in);
	    	System.out.print(thetxt);
	    	if (console.hasNextInt()) {
	    		result = console.nextInt();
	    		if (result >= 1) {
	    			validinput = true;
	    		} else {
	    			System.out.println("That is not a valid size! Must be a value greater than 0! ");
	    		}
	    	} else {
	    		System.out.println("Invalid entry! Must be an integer value! ");
	    	}
	    }
	    return result;
	}
	
	/**
	 * Another helper method checks for valid input.
	 * @param thetxt the text
	 * @return integer
	 */
	public static int gettingDepth(String thetxt) {
	    boolean validinput = false;
	    int result = 0;
	    while (validinput == false) {
	    	Scanner console = new Scanner(System.in);
	    	System.out.print(thetxt);
	    	if (console.hasNextInt()) {
	    		result = console.nextInt();
	    		if (result  <= MAXDEPTH && result  >= 0) {
	    			validinput = true;
	    		} else {
	    			System.out.println("That is not a valid size! Must be a zero or greater, maximum value allowed is 10,000!/n Enter 0 for the max value. ");
	    		}
	    	} else {
	    		System.out.println("Invalid entry! Must be an integer value! ");
	    	}
	    }
	    return result;
	}
	
	
	
}
