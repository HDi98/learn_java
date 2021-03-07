package hw1;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHangman {	
	static Hangman  hangman;
	StringBuilder userInput;
	
	@BeforeEach
	public void setupTest() {
		hangman = new Hangman();
		hangman.hangmanRound = new HangmanRound();
		hangman.hangmanRound.setPuzzleWord("syllabus");
		hangman.hangmanRound.setClueWord("-y--a-u-");
		userInput = new StringBuilder();
	}
	
	@Test
	public void testCountWordsFile() {
		String words[] = Game.readFile("wordsFile.txt");
		assertEquals(20000, words.length);
		
	}
	
	@Test
	public void testCountMiniWordsFile() {
		String words[] = Game.readFile("miniWordsFile.txt");
		assertEquals(30, words.length);
	}
	
	@Test
	public void testCountDashes() {
		assertEquals ("Test count dashes", 5, hangman.countDashes(hangman.hangmanRound.getClueWord()));		
	}
	@Test
	public void testNextTryCorrectClue() {
		assertEquals(Hangman.RIGHT_MESSAGE_INDEX, hangman.nextTry('b', userInput));
		assertEquals("b", userInput.toString());
	}
	@Test
	public void testNextTryWrongClue() {
		assertEquals(Hangman.WRONG_MESSAGE_INDEX, hangman.nextTry('e', userInput));
		assertEquals("e", userInput.toString());  
	}
	@Test
	public void testNextTryPartOfClue() {
		assertEquals(Hangman.PART_OF_CLUE_MESSAGE_INDEX, hangman.nextTry('y', userInput));
		assertEquals("", userInput.toString());  //this should not be added to userInput
	}
	@Test
	public void testNextTryRepeatClue() {
		hangman.nextTry('x', userInput);
		assertEquals(Hangman.ALREADY_ENTERED_MESSAGE_INDEX, hangman.nextTry('x', userInput)); //enter 'x' again
		assertEquals("x", userInput.toString());  //'x' added only once to userInput
	}
	@Test
	public void testHitCount() {
		hangman.nextTry('a', userInput);		//part of clue. Trial not counted
		hangman.nextTry('b', userInput);		//hit. Trial# 1
		hangman.nextTry('c', userInput);		//miss. Trial# 2
		hangman.nextTry('d', userInput);		//miss. Trial# 3
		assertEquals( 1, hangman.hangmanRound.getHitCount());
	}
	@Test
	public void testMissCount() {
		hangman.nextTry('a', userInput);		//part of clue. Trial not counted
		hangman.nextTry('b', userInput);		//hit. Trial# 1
		hangman.nextTry('c', userInput);		//miss. Trial# 2
		hangman.nextTry('d', userInput);		//miss. Trial# 3
		assertEquals( 2, hangman.hangmanRound.getMissCount());
	}
	
	@Test
	public void testGetScoreWithHitAndMiss() {
		hangman.nextTry('a', userInput);		//part of clue. Trial not counted
		hangman.nextTry('b', userInput);		//hit. Trial# 1
		hangman.nextTry('c', userInput);		//miss. Trial# 2
		hangman.nextTry('d', userInput);		//miss. Trial# 3
		assertEquals( 0.5f, hangman.getScore(), 0);
	}
	
	@Test
	public void testGetScoreWithAllHitsAndNoMiss() {
		hangman.nextTry('a', userInput);		//part of clue. Trial not counted
		hangman.nextTry('b', userInput);		//hit. Trial# 1
		hangman.nextTry('s', userInput);		//hit. Trial# 2
		hangman.nextTry('l', userInput);		//hit. Trial# 3
		assertEquals( 3.0f, hangman.getScore(), 0);
	}
	
	@Test
	public void testGetScoreWithAllMissNoHits() {
		hangman.nextTry('a', userInput);		//part of clue. Trial not counted
		hangman.nextTry('x', userInput);		//miss. Trial# 1
		hangman.nextTry('t', userInput);		//miss. Trial# 2
		hangman.nextTry('z', userInput);		//miss. Trial# 3
		assertEquals( 0f, hangman.getScore(), 0);
	}
}
