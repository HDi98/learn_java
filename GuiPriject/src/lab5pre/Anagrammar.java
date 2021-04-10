package lab5pre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Anagrammar {
	String[] words;		//stores all words read from words.txt
	boolean isInDictionary = false; //true if the clue word exists in words.txt
	boolean hasAnagrams = false;	//true if the clue word has anagrams
	String[] anagramArray;	//stores all anagrams of clue-word, if found
	
	/**loadWords method reads the file and loads all words 
	 * into the words[] array */
	void loadWords(String filename) {
		//write your code here
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			StringBuilder fileContent = new StringBuilder();
			while(fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.next() + "\n");
			}
			words = fileContent.toString().split("\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** findAnagrams method traverses the words array and looks 
	 * for anagrams of the clue. While doing so, if the clue-word itself is found in the 
	 * words array, it sets the isInDictionary to true.
	 * If it finds any anagram of the clue, it sets the hasAnagram to true. 
	 * It loads the anagram into the anagramArray. 
	 * If no anagrams found, then anagramArray remains an array with size 0. 
	 * */
	void findAnagrams(String clue) {
		//write your code here
		for (String s: words) {
			if (s.equals(clue)) {
				isInDictionary = true;
			}
		}
	}

	boolean isAnagram(String clue, String candidate) {
		if (clue.length() != candidate.length()) {
			return false;
		}
		char[] cluearray = clue.toCharArray();
		char[] candidateArray = candidate.toCharArray();
		Arrays.sort(cluearray);
		Arrays.sort(candidateArray);
		
		return false;
	}
}
