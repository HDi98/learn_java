//name: Haonan Di
//andrew id: hdi
package lab4;

import java.util.Scanner;

public class WordWhiz {

	static final String THESAURUS = "Thesaurus.txt"; 	//must use this constant to open the file
	static final String DICTIONARY = "Dictionary.txt"; 	//must use this constant to open the file

	WordReference[] wordReferences = new WordReference[2]; //to hold instances of Thesaurus and Dictionary


	//do not change this method
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		WordWhiz wordWhiz = new WordWhiz();
		wordWhiz.loadReferences();
		
		//print menu and get user input
		System.out.println("*** Welcome to WordWhiz ***");
		System.out.println("1. Look up Thesaurus");
		System.out.println("2. Look up Dictionary");
		System.out.println("Enter your choice");
		int choice = input.nextInt();
		input.nextLine();
		System.out.println("Enter word");
		String word = input.nextLine();
		int count = 0;
		String[] meanings = wordWhiz.getMeanings(choice, word);
		
		//print meanings on console
		if (meanings != null) {
			for (String s: meanings) {
				System.out.printf("%d. %s\n", ++count, s);
			}
		} else {
			System.out.println("Sorry! Word not found!");
		}
		input.close();
	}
	
	//The loadReferece() method initializes wordReferences array with an instance of Thesaurus
	// at its 0th index and instance of Dictionary at its 1st index.
	void loadReferences() {
		//write your code here
		
		wordReferences[0] = new Thesaurus(THESAURUS);
		wordReferences[1] = new Dictionary(DICTIONARY);
		
	}

	//The getMeanings() uses choice to get the wordReferences (Thesaurus or Dictionary), 
	//invokes its lookup() method polymorphically, and returns its array of meanings. 
	String[] getMeanings(int choice, String word) {
		//write your code here
		if (choice == 1) {
			return wordReferences[0].lookup(word);
		}
		else if (choice == 2) {
			return wordReferences[1].lookup(word);
		}
		return null;
	}
}
