package hw3_shuwu;
/**
 * name: Shu Wu
 * andrew id: shuwu
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordNerdModel {

    static final String WORDS_FILE_NAME = "data/wordsFile.txt";
    static final String SCORE_FILE_NAME = "data/scores.csv";
    static String[] wordsFromFile;
    ObservableList<Score> scoreList;

    //read words from file
    static void readWordsFile(String wordsFilename) {
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

    void writeScore(String scoreString) {

        try {
            File csv = new File(SCORE_FILE_NAME);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            bw.write(scoreString);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void readScore() {
        File file = new File(SCORE_FILE_NAME);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder fileContent = new StringBuilder();
        while (fileScanner.useDelimiter("\n").hasNext()) {
            fileContent.append(fileScanner.next() + "\n");
        }
        fileScanner.close();

        String[] infos = fileContent.toString().split("\n");

        List<Score> scores = new ArrayList<>();
        for (String info : infos) {
            String[] curScore = info.split(",");
            Score score = new Score(Integer.parseInt(curScore[0]), curScore[1], Integer.parseInt(curScore[2]), Float.parseFloat(curScore[3].trim()));

            scores.add(score);
        }

        scoreList = FXCollections.observableArrayList(scores);
    }


}
