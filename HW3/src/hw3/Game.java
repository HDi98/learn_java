//name: haonan di
//andrew id: hdi

package hw3;

public abstract class Game {
	
	/**setupRound() looks creates a new GameRound instance (Hangman or Twister)
	 * and initializes it various properties */
	abstract GameRound setupRound();
	
	/** makeAClue() returns a clue string to be displayed as per the game rules (Hangman or Twister) */
	abstract String makeAClue(String puzzleWord);
	
	/** getScoreString() calculates the score and returns a string to be displayed 
	 * as per the format required for the game (Hangman or Twister)  */
	abstract String getScoreString();
	
	/** nextTry() processes the next entry submitted made by the player. 
	 * In case of Hangman, it is a single character.
	 * In case of Twister, it is a word.
	 * The method returns the index of the image to be displayed on smileyButton.
	 */
	abstract int nextTry(String guess);
	
}
