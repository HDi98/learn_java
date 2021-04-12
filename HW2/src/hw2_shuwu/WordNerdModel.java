package hw2_shuwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordNerdModel {

    static final String WORDS_FILE_NAME = "data/wordsFile.txt";
    static String[] wordsFromFile;

    //read words from file
    static void readWordsFile(String wordsFilename){
        File file = new File(wordsFilename);
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //use stringBuilder to store the words
        //and then split it to array
        StringBuilder fileContent = new StringBuilder();
        while (fileScanner.hasNext()) {
            fileContent.append(fileScanner.next() + "\n");
        }
        fileScanner.close();
        wordsFromFile = fileContent.toString().split("\n");
    }
}
