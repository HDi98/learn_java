//name: haonan di
//andrew id: hdi
package hw3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
		// derived from previous hw
		try {
			Scanner fileScanner = new Scanner(new File(wordsFilename));
			StringBuilder fileContent = new StringBuilder();
			while (fileScanner.hasNextLine()) {
				String tmp = fileScanner.nextLine();
				fileContent.append(tmp + "\n");				
				if (!tmp.matches("^[a-zA-Z]*$")) {
					// the exception will be catch in WordNerd.java
					throw new InvalidWordSourceException("Check word source format!");
				}
			}
			wordsFromFile = fileContent.toString().split("\n");
		} 
//		catch (InvalidWordSourceException i) {
//			i.printStackTrace();
//		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static void readScoreFile(String scoreFilename) {
		// nearly same with readWordsFile
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
				String score2decimal = String.format("%.2f", Float.parseFloat(tmpLine[3]));
				scoreFromFile.add(new Score(Integer.parseInt(tmpLine[0]), tmpLine[1], Integer.parseInt(tmpLine[2]), Float.parseFloat(score2decimal)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static void writeScore(String scoreString) {
		// use bufferedWriter to write into the file
		System.out.println("I'm writing into score file!");
		// use para=true to add to the current score file
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(SCORE_FILE_NAME, true))) {
			bw.write(scoreString + "\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
