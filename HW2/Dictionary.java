package HW2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Dictionary {
	private ArrayList<DictionaryItem> dictArrayList;
	private ArrayList<String> wordList;
	
	/**
	 * Initializes dictArrayList and wordList.
	 */
	public Dictionary() {
		dictArrayList = new ArrayList<>(1300);
		wordList = new ArrayList<>(1300);
		readFile("ionDictionary.txt");
	}
	
	/**
	 * Initalizes dictArrayList and wordList with words from parameter filename.
	 * @param filename
	 */
	public Dictionary(String filename) {
		dictArrayList = new ArrayList<>(1300);
		wordList = new ArrayList<>(1300);
		readFile(filename);
	}
	
	/**
	 * Reads file of name filename and stores words in wordList and word-count pair in dictArrayList fields.
	 * Throws FileNotFoundException if the file is missing in the folder.
	 * 
	 * @param filename
	 */
	public void readFile(String filename) {
		File txtfile = new File(filename);
		Scanner sc;
		try {
			sc = new Scanner(txtfile);
			for(int i = 0; i < 4; i++) {
				sc.nextLine();
			}
			while(sc.hasNextLine()) {
				splitStoreLine(sc);
			}
		} catch(FileNotFoundException fnfe) {
			System.out.println("ERROR: File not found.");
		}
	}
	
	
	/**
	 * Splits the line read from the file and stores the word-count pairs in the defined arraylist.
	 * @param scan
	 */
	private void splitStoreLine(Scanner scan) {
		String word = null;
		int count = -1;
		
		String scanLine = scan.nextLine();
		String[] allElems = scanLine.split(" | ");
		String[] wordInfo = new String[2];
		
		// For loop ensuring that wordInfo contains word and count of word, and no white spaces or extra characters.
		for(int i = 0; i < allElems.length; i++) {
			if(allElems[i].equals(" ") == false && allElems[i].equals("|") == false) {
				if(wordInfo[0] == null) {
					wordInfo[0] = allElems[i];
				} else {
					wordInfo[1] = allElems[i];
				}
			}
		}
		
		// For loop assigning word to String word and count of word to int count.
		for(int i = 0; i < 2; i++) {
			if(i == 0) {
				word = wordInfo[i];
			} else if(i == 1) {
				count = Integer.parseInt(wordInfo[i]);
			}
		}
		
		// Creation of a DictionaryItem with word and count to add to dictArrayList.
		// Addition of word to wordList.
		DictionaryItem newWord = new DictionaryItem(word, count);
		dictArrayList.add(newWord);
		wordList.add(word);
	}
	
	/**
	 * Prints the three menu items, takes a number from the user indicating the operation chosen, and calls the helper method processMenuItem().
	 * Quits program when user enters 3 to quit.
	 */
	public void printMenu() {
		System.out.println("Welcome to the Ion Dictionary! This dictionary is created from the book Ion by Plato!");
		System.out.println("Please choose one of the following menu items indicated with 1-3");
		System.out.println("1: To print all the words in the dictionary, choose 1");
		System.out.println("2: To search a word in the dictionary, choose 2");
		System.out.println("3: To quit the program, choose 3");
		
		Scanner userInput = new Scanner(System.in);
		int inputNum = -1;
		
		boolean tfResult = processMenuItem(inputNum, userInput);
		
		if(tfResult == false) {
			System.out.println("Thanks for using Ion Dictionary! Bye!");
		}
	}
	
	/**
	 * Takes operation that user chose (menuItem) and Scanner object (scan) to read the word the user wishes to search in the dictionary.
	 * Calls the appropriate functions for each operation.
	 * @param menuItem
	 * @param scan
	 * @return true if user's input != 3; false if user's input == 3 (if user quits program)
	 */
	private boolean processMenuItem(int menuItem, Scanner scan) {
		
		boolean tf = true;
		
		while(tf == true) {
			
			menuItem = -1;
			scan = new Scanner(System.in);
			
			// Ensuring InputMismatchException is caught if user does not input an int value.
			try {
				menuItem = scan.nextInt();
			} catch(InputMismatchException ime) {
				System.out.println("ERROR: Input is not an int!");
			}
			
			
			if(menuItem == 3) {
				tf = false;
			} else if(menuItem == 1) {
				printDictionary();
				System.out.println("Please choose one of the following menu items indicated with 1-3");
				System.out.println("1: To print all the words in the dictionary, choose 1");
				System.out.println("2: To search a word in the dictionary, choose 2");
				System.out.println("3: To quit the program, choose 3");
			} else if(menuItem == 2){
				System.out.println("Please enter the word you would like to search:");
				scan = new Scanner(System.in);
				String wordToSearch = scan.nextLine();
				int wordCount = searchDictionary(wordToSearch);
				
				if(wordCount == 0) {
					System.out.println("The word '" + wordToSearch + "' does not exist in the Ion dictionary!");
				} else {
					System.out.println("The word '" + wordToSearch + "' occurred " + wordCount + " time(s) in the book!");
				}
				System.out.println("Please choose one of the following menu items indicated with 1-3");
				System.out.println("1: To print all the words in the dictionary, choose 1");
				System.out.println("2: To search a word in the dictionary, choose 2");
				System.out.println("3: To quit the program, choose 3");
			} else {
				System.out.println("ERROR! Please enter a number between 1 and 3.");
				System.out.println("Please choose one of the following menu items indicated with 1-3");
				System.out.println("1: To print all the words in the dictionary, choose 1");
				System.out.println("2: To search a word in the dictionary, choose 2");
				System.out.println("3: To quit the program, choose 3");
			}
		}
		
		return tf;
	}
	
	/**
	 * Prints the word list that was created from the dictionary text file.
	 */
	public void printDictionary() {
		System.out.println("All the words mentioned in the Ion book!");
		System.out.println("Words");
		System.out.println("-----");
		for(String printedWord : wordList) {
			System.out.println(printedWord);
		}
	}
	
	/**
	 * Calls the binarySearch() method to search for the word in wordList.
	 * Returns the count of that word from dictArrayList.
	 * @param word
	 * @return count of parameter word
	 */
	public int searchDictionary(String word) {
		int indexOfWord = binarySearch(word, 0, wordList.size());
		if(indexOfWord > wordList.size() || indexOfWord < 0) {	// if word does not exist within wordList, then 0 is returned (count of word is 0).
			return 0;
		} else {
			int wordCount = (dictArrayList.get(indexOfWord)).getCount();
			return wordCount;
		}
	}
	
	/**
	 * Helper method that performs the binary search algorithm on the sorted wordList arraylist.
	 * @param word
	 * @param low
	 * @param high
	 * @return index of parameter word
	 */
	private int binarySearch(String word, int low, int high) {
		int index = Integer.MAX_VALUE;
	    
		while (low <= high) {
	        int mid = low  + ((high - low) / 2);
	        if ((wordList.get(mid)).compareTo(word) < 0) {
	            low = mid + 1;
	        } else if ((wordList.get(mid)).compareTo(word) > 0) {
	            high = mid - 1;
	        } else if ((wordList.get(mid)).compareTo(word) == 0) {
	            index = mid;
	            break;
	        }
	    }
	    
	    return index;
	}
	
	public static void main(String[] args) {
		Dictionary ionDict = new Dictionary();
		ionDict.printMenu();
	}
	
}
