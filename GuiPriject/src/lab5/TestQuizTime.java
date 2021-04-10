//author: Haonan Di
// Andrew ID: hdi
package lab5;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQuizTime {
	
	Quiz[] availableQuizzes = { new MathQuiz(), new EnglishQuiz() };  
	Quiz chosenQuiz;

	@Test
	public void testMathQuizAdd() {
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.questionString = new String[3];
		chosenQuiz.questionString[0] = "10";
		chosenQuiz.questionString[1] = "+";
		chosenQuiz.questionString[2] = "5";
		assertTrue(chosenQuiz.checkAnswer("15"));
	}
	
	@Test
	public void testMathQuizSubtract() {
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.questionString = new String[3];
		chosenQuiz.questionString[0] = "10";
		chosenQuiz.questionString[1] = "-";
		chosenQuiz.questionString[2] = "5";
		assertTrue(chosenQuiz.checkAnswer("5"));
	}
	
	@Test
	public void testMathQuizMultiply() {
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.questionString = new String[3];
		chosenQuiz.questionString[0] = "10";
		chosenQuiz.questionString[1] = "*";
		chosenQuiz.questionString[2] = "5";
		assertTrue(chosenQuiz.checkAnswer("50"));
	}
	
	@Test
	public void testEnglishQuiz() {
		chosenQuiz = availableQuizzes[1];
		chosenQuiz.questionString = new String[2];
		chosenQuiz.questionString[0] = "Above"; 
		chosenQuiz.questionString[1] = "In or to a higher place; higher than; on or over the upper surface; over;" ;
		assertTrue(chosenQuiz.checkAnswer("above"));
		
	}
	
	@Test
	public void testQuizCorrectMathAnswerScore() {
		Quiz.score = 0;
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.questionString = new String[3];
		chosenQuiz.questionString[0] = "11";
		chosenQuiz.questionString[1] = "*";
		chosenQuiz.questionString[2] = "5";
		chosenQuiz.checkAnswer("55");
		assertEquals(1, Quiz.score); 
	}
	
	@Test
	public void testQuizInCorrectMathAnswerScore() {
		Quiz.score = 0;
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.questionString = new String[3];
		chosenQuiz.questionString[0] = "11";
		chosenQuiz.questionString[1] = "*";
		chosenQuiz.questionString[2] = "5";
		chosenQuiz.checkAnswer("50");
		assertEquals(0, Quiz.score); 
	}

	@Test
	public void testQuizCorrectEnglishAnswerScore() {
		Quiz.score = 0;
		chosenQuiz = availableQuizzes[1];
		chosenQuiz.questionString = new String[2];
		chosenQuiz.questionString[0] = "Abode";
		chosenQuiz.checkAnswer("abode");
		assertEquals(1, Quiz.score);
	}
	
	@Test
	public void testQuizTotalTestCount() {
		Quiz.totalCount = 0;
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.questionString = new String[3];
		chosenQuiz.questionString[0] = "11";
		chosenQuiz.questionString[1] = "*";
		chosenQuiz.questionString[2] = "5";
		chosenQuiz.checkAnswer("50");
		
		chosenQuiz = availableQuizzes[1];
		chosenQuiz.questionString = new String[2];
		chosenQuiz.questionString[0] = "Abode";
		chosenQuiz.checkAnswer("abode");
		assertEquals(2, Quiz.totalCount);
	}
	
	@Test
	public void testAnswerCheckedBeforeMathAnswer() {
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.createQuestion();
		assertFalse(Quiz.answerChecked);
	}
	
	@Test
	public void testAnswerCheckedAfterMathAnswer() {
		chosenQuiz = availableQuizzes[0];
		chosenQuiz.createQuestion();
		chosenQuiz.checkAnswer("1234");
		assertTrue(Quiz.answerChecked);
	}
	
	@Test
	public void testAnswerCheckedBeforeEnglishAnswer() {
		chosenQuiz = availableQuizzes[1];
		chosenQuiz.createQuestion();
		assertFalse(Quiz.answerChecked);
	}
	
	@Test
	public void testAnswerCheckedAfterEnglishAnswer() {
		chosenQuiz = availableQuizzes[1];
		chosenQuiz.createQuestion();
		chosenQuiz.checkAnswer("Some answer string");
		assertTrue(Quiz.answerChecked);
	}
}
