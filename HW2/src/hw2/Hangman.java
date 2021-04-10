package hw2;



public class Hangman extends Game{
	static final int MIN_WORD_LENGTH = 5; //minimum length of puzzle word
	static final int MAX_WORD_LENGTH = 10; //maximum length of puzzle word
	static final int HANGMAN_TRIALS = 10;  // max number of trials in a game
	static final int HANGMAN_GAME_TIME = 30; // max time in seconds for one round of game
	
	HangmanRound hangmanRound = new HangmanRound();
	
	/** setupRound() is a replacement of findPuzzleWord() in HW1. 
	 * It returns a new HangmanRound instance with puzzleWord initialized randomly drawn from wordsFromFile.
	* The puzzleWord must be a word between HANGMAN_MIN_WORD_LENGTH and HANGMAN_MAX_WORD_LEGTH. 
	* Other properties of Hangmanround are also initialized here. 
	*/
	@Override
	HangmanRound setupRound() {
		//write your code here
		//Initialize the puzzleword
		while (true) {
			int choiceIndex = (int) (Math.random() * WordNerdModel.wordsFromFile.length);
			if (WordNerdModel.wordsFromFile[choiceIndex].length() <= MAX_WORD_LENGTH && WordNerdModel.wordsFromFile[choiceIndex].length() >= MIN_WORD_LENGTH) {
				hangmanRound.setPuzzleWord(WordNerdModel.wordsFromFile[choiceIndex]);
				break;
			}
		}
		hangmanRound.setClueWord(makeAClue(hangmanRound.getPuzzleWord()));
		hangmanRound.setHitCount(0);
		hangmanRound.setMissCount(0);
		hangmanRound.setIsRoundComplete(false);
		
		return hangmanRound;
	}
	
	
	/** Returns a clue that has at least half the number of letters in puzzleWord replaced with dashes.
	* The replacement should stop as soon as number of dashes equals or exceeds 50% of total word length. 
	* Note that repeating letters will need to be replaced together.
	* For example, in 'apple', if replacing p, then both 'p's need to be replaced to make it a--le */
	@Override
	String makeAClue(String puzzleWord) {
		//write your code here
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
			//System.out.println(tmp);
			p.setCharAt(rand, '-');
			cnt++;
			while (p.indexOf(tmp) != -1) {
				p.setCharAt(p.indexOf(tmp), '-');
				cnt++;
			}
		}
						
		// need to improve the behavior, to avoid such case: apple --> appl- --> app-- --> a----
		//test code
		//System.out.println(p.toString());
		return p.toString();		
	}

	/** countDashes() returns the number of dashes in a clue String */ 
	int countDashes(String word) {
		StringBuilder cntDash = new StringBuilder(word);
		int init = cntDash.indexOf("_");
		int cnt = 0;
		while (init != -1) {
			init = cntDash.indexOf("_", init+1);
			cnt++;
		}
		return cnt;
	}
	
	/** getScoreString() returns a formatted String with calculated score to be displayed after
	 * each trial in Hangman. See the handout and the video clips for specific format of the string. */
	@Override
	String getScoreString() {
		//write your code here
		float hitcnt = (float)hangmanRound.getHitCount();
		float miscnt = (float)hangmanRound.getMissCount();
		if (miscnt == 0) {
			// only have two decimal
			
			return Float.toString((Math.round(hitcnt*100))/100);
		}
		else {
			
			return Float.toString((Math.round(hitcnt/miscnt*100))/100);
		}
	}
	

	/** nextTry() takes next guess and updates hitCount, missCount, and clueWord in hangmanRound. 
	* Returns INDEX for one of the images defined in GameView (SMILEY_INDEX, THUMBS_UP_INDEX...etc. 
	* The key change from HW1 is that because the keyboardButtons will be disabled after the player clicks on them, 
	* there is no need to track the previous guesses made in userInputs*/
	@Override
	int nextTry(String guess) {
		
		//Since those are banned: same with former input / input which contains in puzzleword	
		if (hangmanRound.getPuzzleWord().contains(guess) && !hangmanRound.getClueWord().contains(guess)) {
			hangmanRound.setHitCount(hangmanRound.getHitCount() + 1);
			return GameView.THUMBS_UP_INDEX;
			
		}
		else {
			hangmanRound.setMissCount(hangmanRound.getMissCount() + 1);
			return GameView.THUMBS_DOWN_INDEX;
		}
			
	}
}
