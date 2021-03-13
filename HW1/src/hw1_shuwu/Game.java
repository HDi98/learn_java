package hw1_shuwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Game {
    public static final String WORDS_FILE_NAME = "wordsFile.txt";  //use this constant wherever file name is used.
    public static String[] wordsFromFile;        //stores words read from the word file in this array

    Game() {
        wordsFromFile = readFile(WORDS_FILE_NAME);
    }

    //readfile() returns an array of words read from the file
    public static String[] readFile(String filename) {
        //read the file
        File file = new File(filename);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //extract content of the file and put them in a StringBuilder
        //split the string into an array
        StringBuilder fileContent = new StringBuilder();
        while (fileScanner.hasNext()) {
            fileContent.append(fileScanner.next() + ",");
        }
        wordsFromFile = fileContent.toString().split(",");

        return wordsFromFile;
    }

    abstract void startGame();

    abstract String findPuzzleWord();

    abstract void playRound();

    abstract String makeAClue(String puzzleWord);

    abstract float getScore();
}
