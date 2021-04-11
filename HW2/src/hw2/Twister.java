package hw2;

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
		List<String> solutionPuzzle = new ArrayList<>();
		
		int [] puzzleCount = new int[26];
		// char a == int 97
		for(int i = 0; i < 26; i++) {
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
				sCount[i] = 0;
			}			
			for(char c: s.toCharArray()) {
				int tmp = (int)c - 97;
				sCount[tmp]++;
			}
			for (int i = 0; i < 26; i++) {
				if (puzzleCount[i] < sCount[i]) {
					signal = 0;
					break;
				}
			}
			
			if (signal == 1 && s.length() >= TWISTER_MIN_WROD_LENGTH && s.length() <= TWISTER_MAX_WROD_LENGTH) {
				solutionPuzzle.add(s);
			}
		}
		return solutionPuzzle;
	}
	
	@Override
	TwisterRound setupRound() {
		// TODO Auto-generated method stub
		// first part: find a new puzzleWord
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		int out = 0;
		for (ObservableList<String> o: twisterRound.submittedListsByWordLength) {
			out += o.size();
		}
		out = twisterRound.solutionWordsList.size() - out;
		return "Twist to find " + Integer.toString(out) + " of " + Integer.toString(twisterRound.solutionWordsList.size()) + " words";
	}

	@Override
	int nextTry(String guess) {
		// TODO Auto-generated method stub
		int tmpleng = guess.length() - TWISTER_MIN_WROD_LENGTH;
		if (twisterRound.getSolutionWordsList().contains(guess)) {
			
			if (twisterRound.getSubmittedListsByWordLength(tmpleng).contains(guess)) {
				return GameView.REPEAT_INDEX;
			}
			else {
				twisterRound.setSubmittedListsByWordLength(guess);
				//twisterRound.setSolutionListsByWordLength(guess);
				return GameView.THUMBS_UP_INDEX;
			}
		}
		else {
//			if (guess.length() >=  TWISTER_MIN_WROD_LENGTH && guess.length() <= TWISTER_MAX_WROD_LENGTH) {
//				twisterRound.setSubmittedListsByWordLength(guess);
//			}			
			return GameView.THUMBS_DOWN_INDEX;
		}
	}

}
