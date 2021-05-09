package hw3_shuwu;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * name: Shu Wu
 * andrew id: shuwu
 */
public class Hangman extends Game{
	static final int MIN_WORD_LENGTH = 5; //minimum length of puzzle word
	static final int MAX_WORD_LENGTH = 10; //maximum length of puzzle word
	static final int HANGMAN_TRIALS = 10;  // max number of trials in a game
	static final int HANGMAN_GAME_TIME = 30; // max time in seconds for one round of game

	HangmanRound hangmanRound;
	WordNerdModel wordNerdModel;

	/** setupRound() is a replacement of findPuzzleWord() in HW1.
	 * It returns a new HangmanRound instance with puzzleWord initialized randomly drawn from wordsFromFile.
	* The puzzleWord must be a word between HANGMAN_MIN_WORD_LENGTH and HANGMAN_MAX_WORD_LENGTH.
	* Other properties of Hangmanround are also initialized here.
	*/
	@Override
	HangmanRound setupRound() {
		int size = WordNerdModel.wordsFromFile.length;
		int num = -1;
		String[] words = WordNerdModel.wordsFromFile;
		//generate a random number to get the puzzle in this trial
		while (num == -1 || words[num].length() < MIN_WORD_LENGTH || words[num].length() > MAX_WORD_LENGTH) {
			num = (int) (Math.random() * size);
		}
		hangmanRound = new HangmanRound();
		hangmanRound.setPuzzleWord(WordNerdModel.wordsFromFile[num]);
		hangmanRound.setIsRoundComplete(false);
		makeAClue(hangmanRound.getPuzzleWord());
		return hangmanRound;
	}


	/** Returns a clue that has at least half the number of letters in puzzleWord replaced with dashes.
	* The replacement should stop as soon as number of dashes equals or exceeds 50% of total word length.
	* Note that repeating letters will need to be replaced together.
	* For example, in 'apple', if replacing p, then both 'p's need to be replaced to make it a--le */
	@Override
	String makeAClue(String puzzleWord) {

		String clueWord = puzzleWord;
		int count = 0;
		int size = puzzleWord.length();

		//if the length of puzzleWord is even
		if (size % 2 == 0) {
			while (count < puzzleWord.length() / 2) {
				//generate a random index
				int index = (int) (Math.random() * puzzleWord.length());
				char curReplace = clueWord.charAt(index);
				if (curReplace == '_') continue;
				//count the number of charcaters that should be replaced
				for (char c : clueWord.toCharArray()) {
					if (count >= puzzleWord.length() / 2) break;
					if (c == curReplace) {
						count++;
					}
				}
				clueWord = clueWord.replace(clueWord.charAt(index), '_');
			}
		} else {
			//if the length of puzzleWord is odd
			while (count <= puzzleWord.length() / 2) {
				int index = (int) (Math.random() * puzzleWord.length());
				char curReplace = clueWord.charAt(index);
				if (curReplace == '_') continue;
				for (char c : clueWord.toCharArray()) {
					//For odd numbers, the number of dashes should more than half because the result is always rounded down
					if (count > puzzleWord.length() / 2) break;
					if (c == curReplace) {
						count++;
					}
				}
				clueWord = clueWord.replace(clueWord.charAt(index), '_');
			}
		}

		hangmanRound.setClueWord(clueWord);

		return clueWord;
	}

	/** countDashes() returns the number of dashes in a clue String */
	int countDashes(String word) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '_') count++;
		}
		return count;
	}

	/** getScoreString() returns a formatted String with calculated score to be displayed after
	 * each trial in Hangman. See the handout and the video clips for specific format of the string. */
	@Override
	String getScoreString() {
		if (hangmanRound.getMissCount() == 0) return "Hit: " + hangmanRound.getHitCount() + " Miss: 0" + ". Score: " + hangmanRound.getHitCount();
		String score = String.format("%.2f", (float)hangmanRound.getHitCount() / (float) hangmanRound.getMissCount());
		return "Hit: " + hangmanRound.getHitCount() + " Miss: " + hangmanRound.getMissCount() + ". Score: " + score;
	}

	/** nextTry() takes next guess and updates hitCount, missCount, and clueWord in hangmanRound.
	* Returns INDEX for one of the images defined in GameView (SMILEY_INDEX, THUMBS_UP_INDEX...etc.
	* The key change from HW1 is that because the keyboardButtons will be disabled after the player clicks on them,
	* there is no need to track the previous guesses made in userInputs*/
	@Override
	int nextTry(String guess) {

		//if the user made a wrong guess in the tenth try, this round is over
		//if there is only one missing character in the tenth try and the user
		//made a right guess, the user wins, otherwise the user loses
		if(hangmanRound.getMissCount() + hangmanRound.getHitCount() == HANGMAN_TRIALS - 1) {
			if(hangmanRound.getPuzzleWord().contains(guess + "")){

				hangmanRound.setHitCount(hangmanRound.getHitCount() + 1);
				if(countDashes(hangmanRound.getClueWord()) == 0){
					writeScore();
					return GameView.SMILEY_INDEX;
				}else{
					writeScore();
					return GameView.SADLY_INDEX;
				}
			}else {
				hangmanRound.setMissCount(hangmanRound.getMissCount() + 1);
				writeScore();
				return GameView.SADLY_INDEX;
			}
		}

		boolean isRight = false;
		if (hangmanRound.getPuzzleWord().contains(guess + "")) {

			//check if the guess is correct
			//if the guess is correct, update missCount and hitCount and clueWord in this round
			//if the guess is wrong, update missCount only
			StringBuilder newClueWord = new StringBuilder();
			for (int i = 0; i < hangmanRound.getPuzzleWord().length(); i++) {
				if (hangmanRound.getClueWord().charAt(i) == '_' && (hangmanRound.getPuzzleWord().charAt(i) + "").equals(guess)) {
					newClueWord.append(guess);
					isRight = true;
				} else {
					newClueWord.append(hangmanRound.getClueWord().charAt(i));
				}
			}

			if (isRight) {
				hangmanRound.setClueWord(newClueWord.toString());
				hangmanRound.setHitCount(hangmanRound.getHitCount() + 1);

				//if the game is over and the user succeed
				if(countDashes(hangmanRound.getClueWord()) == 0){
					writeScore();
					return GameView.SMILEY_INDEX;
				}
				return GameView.THUMBS_UP_INDEX;
			} else {
				hangmanRound.setMissCount(hangmanRound.getMissCount() + 1);
				return GameView.THUMBS_DOWN_INDEX;
			}

		}else{
			hangmanRound.setMissCount(hangmanRound.getMissCount() + 1);
			return GameView.THUMBS_DOWN_INDEX;
		}
	}

	private void writeScore(){
		wordNerdModel = new WordNerdModel();

		float score = 0;
		if(hangmanRound.getMissCount() != 0){
			score = hangmanRound.getHitCount() / hangmanRound.getMissCount();
		}else{
			score = hangmanRound.getHitCount();
		}

		//这个要加listener？？？？？？
		int timeStamp = (int)GameView.wordTimer.timeline.getCurrentTime().toSeconds();
		String scoreString = "0," + hangmanRound.getPuzzleWord() + "," + timeStamp + "," + score;
		wordNerdModel.writeScore(scoreString);
	}
}
