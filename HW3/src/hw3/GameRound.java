//name: haonan di
//andrew id: hdi
package hw3;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameRound {
	
	private StringProperty puzzleWord = new SimpleStringProperty();
	private BooleanProperty isRoundComplete = new SimpleBooleanProperty();
	private StringProperty clueWord = new SimpleStringProperty();
	
	//puzzleWord
	public void setPuzzleWord(String puzzleWord) { this.puzzleWord.set(puzzleWord); }
	public String getPuzzleWord() { return puzzleWord.get(); }
	public StringProperty puzzleWordProperty() { return puzzleWord; }

	//clueWord
	public String getClueWord() { return clueWord.get(); }
	public void setClueWord(String clueWord) { this.clueWord.set(clueWord); }
	public StringProperty clueWordProperty() { return clueWord; }
	
	//isRoundComplete
	public boolean getIsRoundComplete() { 	return isRoundComplete.get(); }
	public void setIsRoundComplete(boolean isRoundComplete) { this.isRoundComplete.set(isRoundComplete); }
	public BooleanProperty isRoundCompleteProperty() { return isRoundComplete; }
	

	
}
