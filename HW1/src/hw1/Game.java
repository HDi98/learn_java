//author: Haonan Di
//author andrew id: hdi
package hw1;

import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;

public abstract class Game {
	public static final String WORDS_FILE_NAME = "wordsFile.txt";  //use this constant wherever file name is used. 
	public static String[] wordsFromFile;		//stores words read from the word file in this array

	Game() {
		wordsFromFile = readFile(WORDS_FILE_NAME);
	}

	//readfile() returns an array of words read from the file
	public static String[] readFile(String filename) {
		//write your code here 
		File file = new File(filename);
		Scanner fileScanner = null;
		
		try {
			fileScanner = new Scanner(file);
			StringBuilder fileContent = new StringBuilder();
			while (fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine() + "\n");
			}
			wordsFromFile = fileContent.toString().split("\n");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return wordsFromFile;
	}

	abstract void startGame();
	abstract String findPuzzleWord();
	abstract void playRound();
	abstract String makeAClue(String puzzleWord);
	abstract float getScore();
}
