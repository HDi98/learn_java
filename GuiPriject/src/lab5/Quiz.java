//author: Haonan Di
// Andrew ID: hdi
package lab5;

public abstract class Quiz {
	String[] questionString;  	//questionString contains the question to be shown in questionLabel. 
							 	//For MathQuiz, it will have 3 elements: operand1, operator, operand2. 
								//For example, if the expression is 5+6 then questionString will have 
								//"5" at index-0, "+" at index-1, and "6" at index-2
								//For EnglighQuiz, it will have two elements: word and its meaning. 
	
	static boolean answerChecked;		//Set to false when a new question is created. Set to true when its answer has been checked.
	
	static int score;			//points earned for correct answers so far.  
	static int totalCount;		//total quiz-questions answered
	
	/** createQuestion() populates the question strings elements */
	abstract void createQuestion(); 
	
	/** checkAnswer() compares the answer with expected result, 
	 * increments score and returns true if the answer is correct, 
	 * else returns false */
	abstract boolean checkAnswer(String answer); 
}
