//name: Haonan Di
//andrew id: hdi
package hw3;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Score {
	
	IntegerProperty gameId = new SimpleIntegerProperty();
	StringProperty puzzleWord = new SimpleStringProperty();
	IntegerProperty timeStamp = new SimpleIntegerProperty();
	FloatProperty score = new SimpleFloatProperty();
	private StringProperty gameName = new SimpleStringProperty();
	private StringProperty scoreString = new SimpleStringProperty();
	
	Score() {}
	
	
	Score(int gameId, String puzzleWord, int timeStamp, float score){
		// set the gameName and bind it to the score Chart so that the score chart will display the correct name
		this.gameId.set(gameId); 
		this.puzzleWord.set(puzzleWord);
		this.timeStamp.set(timeStamp);
		this.score.set(score);
		if (gameId == 0) {
			this.gameName.set("Hangman");
		}else {
			this.gameName.set("Twister");
		}
		scoreString.set(String.format("%.2f", score));
	}

	@Override
	public String toString() {
		// write the data to the score.csv
		String tmp = gameId.get() + "," + puzzleWord.get() + "," + timeStamp.get() + "," + String.format("%.6f", score.get());
		return tmp;
	}
	
	
	// some getters and setters
	public String getGameName() {
		return gameName.get();
	}
	public String getScoreString() {
		return scoreString.get();
	}
	

	public int getGameId() {
		return gameId.get();
	}
	public void setGameId(int gameId) {
		this.gameId.set(gameId);;
	}
	IntegerProperty GameIdProperty() {
		return gameId;
	}
	

	public String getPuzzleWord() {
		return puzzleWord.get();
	}
	public void setPuzzleWord(String puzzleWord) {
		this.puzzleWord.set(puzzleWord);;
	}
	StringProperty PuzzleWordProperty() {
		return puzzleWord;
	}
	

	public int getTimeStamp() {
		return timeStamp.get();
	}
	public void setTimeStamp(int timeStamp) {
		this.timeStamp.set(timeStamp);;
	}
	IntegerProperty TimeStampProperty() {
		return timeStamp;
	}
	

	public float getScore() {
		return score.get();
	}
	public void setScore(float score) {
		this.score.set(score);
	}
	FloatProperty ScoreProperty() {
		return score;
	}
	
	
}
