//name: haonan di
//andrew id: hdi
package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WordNerdModel {
	// this method is derived from my hw1
	public static String[] wordsFromFile;
	public static String WORDS_FILE_NAME = "data/wordsFile.txt";
	public static ObservableList<Score> scoreFromFile;
	public static String SCORE_FILE_NAME = "data/scores.csv";
	
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
	
	static void readScoreFile(String scoreFilename) {
		scoreFromFile = FXCollections.observableArrayList();
		try {
			String[] tmpFiles;
			Scanner fileScanner = new Scanner(new File(scoreFilename));
			StringBuilder fileContent = new StringBuilder();
			while (fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine() + "\n");
			}
			tmpFiles = fileContent.toString().split("\n");
			for (int i = 0; i < tmpFiles.length; i++) {
				String[] tmpLine = tmpFiles[i].split(",");
				scoreFromFile.add(new Score(Integer.parseInt(tmpLine[0]), tmpLine[1], Integer.parseInt(tmpLine[2]), Float.parseFloat(tmpLine[3])));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}