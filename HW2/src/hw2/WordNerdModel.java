//name: haonan di
//andrew id: hdi
package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordNerdModel {
	// this method is derived from my hw1
	public static String[] wordsFromFile;
	public static String WORDS_FILE_NAME = "data/wordsFile.txt";
	
	static void readWordsFile(String wordsFilename) {
		try {
			Scanner fileScanner = new Scanner(new File(wordsFilename));
			StringBuilder fileContent = new StringBuilder();
			while (fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine() + "\n");
			}
			wordsFromFile = fileContent.toString().split("\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
