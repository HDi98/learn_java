//author: Haonan Di
// Andrew ID: hdi
package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EnglishQuiz extends Quiz{

	String[] wordStrings;
	EnglishQuiz(){
		try {
			Scanner fileScanner = new Scanner(new File("EnglishQuizWords.txt"));
			StringBuilder fileContent = new StringBuilder();
			while(fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine() + "\n");
			}
			wordStrings = fileContent.toString().split("\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	void createQuestion() {
		// TODO Auto-generated method stub
		Quiz.answerChecked = false;
		int pick = (int) (Math.random()*wordStrings.length);
		questionString = wordStrings[pick].split(": ");
		
	}

	@Override
	boolean checkAnswer(String answer) {
		// TODO Auto-generated method stub
		
		if (answer.toLowerCase().equals(questionString[0].toLowerCase())) {
			if (!Quiz.answerChecked) {
				Quiz.score++;
				Quiz.totalCount++;
			}
			Quiz.answerChecked = true;
			return true;
		}
		else {
			if (!Quiz.answerChecked) {
				Quiz.totalCount++;
			}
			Quiz.answerChecked = true;
			return false;
		}
		
	}
	

}
