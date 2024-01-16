package anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	/**
	 * Invoked by the constructor for the class Anagrams.
	 * Builds the has table letterTable, which consists of entries for
	 * each (lowercase) letter of the alphabet, with each associated with
	 * their respective prime number.
	 */
	public void buildLetterTable() {
		char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		letterTable = new HashMap<Character,Integer>(letters.length);
		for(int i = 0; i < primes.length; i++) {
		    letterTable.put(letters[i], primes[i]);
		}
	}

	/**
	 * Constructor for the class Anagrams.
	 */
	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	/**
	 * Computes the hash code of the string s (passed as an argument), and
	 * adds this word to the hash table anagramTable.
	 * @param s
	 */
	public void addWord(String s) {
		long sKey = myHashCode(s);
		if(anagramTable.containsKey(sKey)) {
			if(!anagramTable.get(sKey).contains(s)) {
				anagramTable.get(sKey).add(s);
			}
		} else {
			ArrayList<String> newArr = new ArrayList<String>();
			newArr.add(s);
			anagramTable.put(sKey, newArr);
		}
	}
	
	/**
	 * Given a string s, this method computes its hash code.
	 * All anagrams of a word receive the same has code.
	 * Throws an IllegalArgumentException if the string s is empty.
	 * @param s
	 * @return
	 */
	public long myHashCode(String s) {
		if(s == "") {
			throw new IllegalArgumentException("ERROR! String is empty.");
		} else {
			long key = 1;
			for(int i = 0; i < s.length(); i++) {
				key *= letterTable.get(s.charAt(i));
			}
			return key;
		}
	}
	
	/**
	 * Receives the name of a text file containing words (one per line),
	 * and builds the has table anagramTable using the addWord method.
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * Returns a list of the entries in the anagramTable that have the largest number of anagrams.
	 * Called by the main method.
	 * @return
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntryArr = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		int maxNumOfEntries = 0;
	    for(Map.Entry<Long, ArrayList<String>> currEntry : anagramTable.entrySet()) {
	    	if(currEntry.getValue().size() > maxNumOfEntries) {
	    		maxNumOfEntries = currEntry.getValue().size();
	    	}
	    }
	    for(Map.Entry<Long, ArrayList<String>> currEntry : anagramTable.entrySet()) {
	    	if(currEntry.getValue().size() == maxNumOfEntries) {
	    		maxEntryArr.add(currEntry);
	    	}
	    }
	    return maxEntryArr;
	}
	
	/**
	 * Reads all the strings in a file, places them in the has table
	 * of anagrams, and then iterates over the has table to report
	 * which words have the largest number of anagrams.
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
