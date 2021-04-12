//name: haonan di
//andrew id: hdi
package hw2_test;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TwisterRound extends GameRound{
	
	ObservableList<String> solutionWordsList;
	ObservableList<ObservableList<String>> submittedListsByWordLength;
	ObservableList<ObservableList<String>> solutionListsByWordLength;
	
	TwisterRound(){
		//initialize the variable
		solutionWordsList = FXCollections.observableArrayList();
		submittedListsByWordLength = FXCollections.observableArrayList();
		solutionListsByWordLength = FXCollections.observableArrayList();
		for(int i = Twister.TWISTER_MIN_WROD_LENGTH; i <= Twister.TWISTER_MAX_WROD_LENGTH; i++) {
			submittedListsByWordLength.add(FXCollections.observableArrayList());
			solutionListsByWordLength.add(FXCollections.observableArrayList());
		}
	}

	public List<String> getSolutionWordsList() {
		return solutionWordsList;
	}
	public void setSolutionWordsList(List<String> solutionWordsList) {
		this.solutionWordsList = FXCollections.observableArrayList(solutionWordsList);
	}
	public ObservableList<String> solutionWordsListProperty(){		
		return solutionWordsList;
	}
	
// part2 submitted list
	public ObservableList<ObservableList<String>> getSubmittedListsByWordLength() {
		// this function is not appear in the design paradigm, so I just return an list of lists
		return submittedListsByWordLength;
	}	
	public ObservableList<String> getSubmittedListsByWordLength(int letterCount) {
		return submittedListsByWordLength.get(letterCount);
	}
	public void setSubmittedListsByWordLength(String word) {
		int tmp = word.length() - Twister.TWISTER_MIN_WROD_LENGTH;
		submittedListsByWordLength.get(tmp).add(word);
	}
	public ObservableList<ObservableList<String>> submittedListsByWordLengthproperty(){
		return submittedListsByWordLength;
	}

// part3 solution list 
	public ObservableList<ObservableList<String>> getSolutionListsByWordLength() {
		return solutionListsByWordLength;
	}	
	public ObservableList<String> getSolutionListsByWordLength(int letterCount) {
		return solutionListsByWordLength.get(letterCount);
	}
	public void setSolutionListsByWordLength(String word) {
		int tmp = word.length() - Twister.TWISTER_MIN_WROD_LENGTH;
		//add the word to the targeted length
		this.solutionListsByWordLength.get(tmp).add(word);
	}	
	public ObservableList<ObservableList<String>> solutionListsByWordLengthproperty(){
		return solutionListsByWordLength;
	}

	
}
