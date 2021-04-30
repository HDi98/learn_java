//name: haonan di
//andrew id: hdi
package hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.ObservableList;


public class Twister extends Game{

	static final int SOLUTION_LIST_COUNT = 5;
	static final int TWISTER_MAX_WROD_LENGTH = 7;
	static final int TWISTER_MIN_WROD_LENGTH = 3;
	static final int NEW_WORD_BUTTON_INDEX = 0;
	static final int TWIST_BUTTON_INDEX = 1;
	static final int CLEAR_BUTTON_INDEX = 2;
	static final int SUBMIT_BUTTON_INDEX = 3;
	static final int CLUE_BUTTON_SIZE = 75;
	static final int TWISTER_GAME_TIME = 120;
	static final int MIN_SOLUTION_WORDCOUNT = 10;
	
	TwisterRound twisterRound;
	
	
	List<String> findSolutions(String puzzleWord){
		// my own defined method to find the words which can be twisted out of the current puzzleWord
		List<String> solutionPuzzle = new ArrayList<>();
		
		// use length-26 array to store the word count for both puzzleWord and the candidate word
		int [] puzzleCount = new int[26];
		// char a == int 97
		for(int i = 0; i < 26; i++) {
			// initialize the length-26 array
			puzzleCount[i] = 0;
		}
		for(char c: puzzleWord.toCharArray()) {
			int tmp = (int)c - 97;
			puzzleCount[tmp]++;
		}
		
		for (String s: WordNerdModel.wordsFromFile) {
			int signal = 1;
			
			int [] sCount = new int[26];
			for(int i = 0; i < 26; i++) {
				// initialize the length-26 array for candidate word
				sCount[i] = 0;
			}			
			for(char c: s.toCharArray()) {
				int tmp = (int)c - 97;
				sCount[tmp]++;
			}
			for (int i = 0; i < 26; i++) {
				if (puzzleCount[i] < sCount[i]) {
					// signal = 0 if any word count in candidate word > puzzleWord
					signal = 0;
					break;
				}
			}
			// check if candidate can be included in the solution list: length and signal
			if (signal == 1 && s.length() >= TWISTER_MIN_WROD_LENGTH && s.length() <= TWISTER_MAX_WROD_LENGTH) {
				solutionPuzzle.add(s);
			}
		}
		return solutionPuzzle;
	}
	
	@Override
	TwisterRound setupRound() {
		// find a new puzzleWord and initial the twisterRound
		while (true) {
			int choiceIndex = (int) (Math.random() * WordNerdModel.wordsFromFile.length);
			if (WordNerdModel.wordsFromFile[choiceIndex].length() <= TWISTER_MAX_WROD_LENGTH && WordNerdModel.wordsFromFile[choiceIndex].length() >= TWISTER_MIN_WROD_LENGTH) {
				String tmp = WordNerdModel.wordsFromFile[choiceIndex];
				if (findSolutions(tmp).size() >= MIN_SOLUTION_WORDCOUNT) {
					//initialize twisterRound here
					twisterRound = new TwisterRound();
					twisterRound.setPuzzleWord(tmp);
					twisterRound.setSolutionWordsList(findSolutions(tmp));
					twisterRound.setClueWord(makeAClue(tmp));
					for(String s: findSolutions(tmp)) {
						// add the solutions to the solution list
						twisterRound.setSolutionListsByWordLength(s);
					}
					break;
				}
				
			}
		}
		return twisterRound;
	}

	@Override
	// shuffle the clueword
	String makeAClue(String puzzleWord) {
		// use Collections.shuffle api to help implement this method
		List<String> tmpCharArray = new ArrayList<>();
		for (char c: puzzleWord.toCharArray()) {
			tmpCharArray.add(Character.toString(c));
		}
		Collections.shuffle(tmpCharArray);
		// to ensure its stability, use stringBuilder to add the after-shuffle tokens
		StringBuilder afterShuffle = new StringBuilder();
		for(String s: tmpCharArray) {
			afterShuffle.append(s);
		}
		return afterShuffle.toString();
	}

	@Override
	String getScoreString() {
		// score = total - submitted
		int out = 0;
		for (ObservableList<String> o: twisterRound.submittedListsByWordLength) {
			out += o.size();
		}
		// get the total submitted length
		out = twisterRound.solutionWordsList.size() - out;
		return "Twist to find " + Integer.toString(out) + " of " + Integer.toString(twisterRound.solutionWordsList.size()) + " words";
	}

	float getScore() {
		// score = total - submitted
		int out = 0;
		for (ObservableList<String> o: twisterRound.submittedListsByWordLength) {
			out += o.size();
		}
		// get the total submitted length
//		out = twisterRound.solutionWordsList.size() - out;
		return (float)out / (float)twisterRound.solutionWordsList.size();

	}
	
	@Override
	int nextTry(String guess) {
		// return the index for guess
		int tmpleng = guess.length() - TWISTER_MIN_WROD_LENGTH;
		if (twisterRound.getSolutionWordsList().contains(guess)) {
			if (twisterRound.getSubmittedListsByWordLength(tmpleng).contains(guess)) {
				return GameView.REPEAT_INDEX;
			}
			else {
				// add to submitted list: true && already submitted
				twisterRound.setSubmittedListsByWordLength(guess);			
				return GameView.THUMBS_UP_INDEX;
			}
		}
		else {
			// if wrong submission, whether submitted must be considered as thumbs down
			return GameView.THUMBS_DOWN_INDEX;
		}
	}

}
