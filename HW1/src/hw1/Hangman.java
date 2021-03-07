package hw1;

import java.util.Scanner;

public class Hangman extends Game {

	static final int MIN_WORD_LENGTH = 5;
	static final int MAX_WORD_LENGTH = 10;
	static final int HANGMAN_TRIALS = 10; 

	//codes returned by nextTry() method for printing appropriate message
	
	static final int RIGHT_MESSAGE_INDEX = 0;
	static final int WRONG_MESSAGE_INDEX = 1;
	static final int ALREADY_ENTERED_MESSAGE_INDEX = 2;
	static final int PART_OF_CLUE_MESSAGE_INDEX = 3;
	static final int CONGRATULATIONS_MESSAGE_INDEX = 4;
	static final int LOST_MESSAGE_INDEX = 5;
	

	String[] messagesArray = { ":-) You got that right!", //RIGHT_MESSAGE_INDEX
			":-( Sorry! Got it wrong!", 		//WRONG_MESSAGE_INDEX
			":-o You already entered that!", 	//ALREADY_ENTERED_MESSAGE_INDEX
			":-\\ Part of the clue!", 			//PART_OF_CLUE_MESSAGE_INDEX
			":-D Congratulations! You won!",   	//CONGRATULATIONS_MESSAGE_INDEX
			":-( Sorry! You lost this one!"}; 	//LOST_MESSAGE_INDEX


	HangmanRound hangmanRound = new HangmanRound();

	// using to locally test code 
	/*public static void main(String[] args) {
		System.out.println("Hello World!");
		Hangman testgame = new Hangman();
		//System.out.println(testgame.findPuzzleWord());
		System.out.println(testgame.countDashes(testgame.makeAClue(testgame.findPuzzleWord())));
		testgame.startGame();
		
	}*/
	
	
	//create and initialize a new round, play, and then print final result
	@Override
	void startGame() {
		//write your code here
		//initialize the hangmanRound
		hangmanRound.setHitCount(0);
		hangmanRound.setMissCount(0);
		String pWord = findPuzzleWord();
		makeAClue(pWord);
		playRound();
	}

	//return a new puzzle word randomly selected from the Game's wordsFromFile. 
	@Override
	String findPuzzleWord() {
		//write your code here
		int choiceIndex = (int) (Math.random() * Game.wordsFromFile.length);
		hangmanRound.setPuzzleWord(Game.wordsFromFile[choiceIndex]);
		System.out.println(Game.wordsFromFile[choiceIndex]);
		return Game.wordsFromFile[choiceIndex];
	}

	//Runs a complete round, invoking nextTry() for each guess to be made by the player
	//Keeps track all inputs made by the player
	//A round goes as long as player has not guessed the complete puzzleWord 
	//and the number of guesses is less than HANGMAN_TRIALS
	//Prints the message from  messagesArray[] based on the code returned by nextTry()
	@Override
	void playRound() {
		//write your code here
		
		int cnt = 0;
		StringBuilder userInputs = new StringBuilder();
		Scanner input = new Scanner(System.in);
		//begin the round
		while (cnt < HANGMAN_TRIALS) {
			cnt++;
			System.out.println("The clue is: " + hangmanRound.getClueWord());
			System.out.println("***Trial# " + cnt + ". Enter your guess: " );
			char guess = input.next().charAt(0);
			int sign = nextTry(guess, userInputs);
			System.out.println(messagesArray[sign]);
			if (countDashes(hangmanRound.getClueWord()) == 0) {
				System.out.println(messagesArray[CONGRATULATIONS_MESSAGE_INDEX]);
				break;
			}
		}
		input.close();
		if (countDashes(hangmanRound.getClueWord()) != 0) {
			System.out.println(messagesArray[LOST_MESSAGE_INDEX]);
		}
		System.out.println("Your score is " + getScore());
		System.out.println("Bye Bye!");
	}

	//Takes next guess and prior userInputs. 
	//updates hitCount, missCount, and clueWord in hangmanRound
	//updates userInputs
	//returns code for message to be printed
	int nextTry(char guess, StringBuilder userInputs) {
		//write your code here
		// 0: guess is in puzzleword but not in clueWord			
		/*if (!hangmanRound.getClueWord().contains("-")){
			return CONGRATULATIONS_MESSAGE_INDEX;
		}
		if (userInputs.length() >= HANGMAN_TRIALS) {
			return LOST_MESSAGE_INDEX;
		}*/
		if (hangmanRound.getPuzzleWord().contains(Character.toString(guess))) {
			
			if (hangmanRound.getClueWord().contains(Character.toString(guess))) {
				if (userInputs.indexOf(Character.toString(guess)) != -1) {
					//userInputs.append(guess);
					return ALREADY_ENTERED_MESSAGE_INDEX;
				}
				else {
					//userInputs.append(guess);
					return PART_OF_CLUE_MESSAGE_INDEX;
				}
				
			}
			else {
				
				
				userInputs.append(guess);
				hangmanRound.setHitCount(hangmanRound.getHitCount()+1);
				//update clueWord
				int leng = hangmanRound.getPuzzleWord().length();
				StringBuilder clueNew = new StringBuilder(hangmanRound.getClueWord());
				for(int i = 0; i < leng; i++) {
					if (hangmanRound.getPuzzleWord().charAt(i) == guess) {
						clueNew.setCharAt(i, guess);
					}
				}
				hangmanRound.setClueWord(clueNew.toString());
				return RIGHT_MESSAGE_INDEX;
			}
		}
				
		else {
			userInputs.append(guess);
			hangmanRound.setMissCount(hangmanRound.getMissCount()+1);
			return WRONG_MESSAGE_INDEX;
		}		
	}

	
	//Returns a clue that has at least half the number of letters 
	//in puzzleWord replaced with dashes.
	//The replacement should stop as soon as number of dashes equals or exceeds 50% of total word length. 
	//Note that repeating letters will need to be replaced together.
	//For example, in 'apple', if replacing p, then both 'p's need to be replaced to make it a--le
	@Override
	String makeAClue(String puzzleWord) {
		// in order to make it fun, we generate random selected char from puzzleWord to finish this session
		int leng = puzzleWord.length();
		int cnt = 0;
		int rand;
		StringBuilder p = new StringBuilder(puzzleWord);
		while (cnt < (leng+1) / 2){
			rand = (int)(Math.random() * leng);
			while (p.charAt(rand) == '-') {
				// if already hash the char, then re-generate the key
				rand = (int)(Math.random() * leng);
			}
			String tmp = Character.toString(p.charAt(rand));
			// test code
			System.out.println(tmp);
			p.setCharAt(rand, '-');
			cnt++;
			while (p.indexOf(tmp) != -1) {
				p.setCharAt(p.indexOf(tmp), '-');
				cnt++;
			}
		}
				
		// need to improve the behavior, to avoid such case: apple --> appl- --> app-- --> a----
		//test code
		System.out.println(p.toString());
		hangmanRound.setClueWord(p.toString());
		return p.toString();
	}

	//returns the number of dashes in a clue string 
	int countDashes(String word) {
		//write your code here
		StringBuilder cntDash = new StringBuilder(word);
		int init = cntDash.indexOf("-");
		int cnt = 0;
		while (init != -1) {
			init = cntDash.indexOf("-", init+1);
			cnt++;
		}
		return cnt;
	}

	//returns score based on Hangman rules
	@Override
	float getScore() {
		//write your code here
		float hitcnt = (float)hangmanRound.getHitCount();
		float miscnt = (float)hangmanRound.getMissCount();
		if (miscnt == 0) {
			// only have two decimal
			return (float)(Math.round(hitcnt*100))/100;
		}
		else {
			return (float)(Math.round(hitcnt/miscnt*100))/100;
		}
	}
}
