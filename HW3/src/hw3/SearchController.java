//name: haonan di
//andrew id: hdi
package hw3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchController extends WordNerdController {
	
	SearchView searchView;

	@Override
	void startController() {
		//to be implemented in HW3
		searchView = new SearchView();
		WordNerdModel.readScoreFile(WordNerdModel.SCORE_FILE_NAME);
		
		searchView.setupView();
		
		// set up the combo box
		ObservableList<String> comboboxWord = FXCollections.observableArrayList();
		comboboxWord.add("All games");
		comboboxWord.add("Hangman");
		comboboxWord.add("Twister");
		
		searchView.gameComboBox.setItems(comboboxWord);
		searchView.gameComboBox.setValue("All games");
		
		setupBindings();
	}

	void refreshSearchTable(ObservableList<Score> chooseData) {
		searchView.searchTableView.getItems().clear();
		searchView.searchTableView.getItems().addAll(chooseData);
	}
	
	boolean iscontain(String s1, String s2) {
		int[] charcnts1 = new int[26];
		int[] charcnts2 = new int[26];
		
		for(int i = 0; i < 26; i++) {
			charcnts1[i] = 0;
			charcnts2[i] = 0;
		}
		
		for (int i = 0; i < s1.length(); i++) {
			int tmp1 = s1.charAt(i) - 'a';
			charcnts1[tmp1]++;
		}
		for (int i = 0; i < s2.length(); i++) {
			int tmp1 = s2.charAt(i) - 'a';
			charcnts2[tmp1]++;
		}
		
		//compare
		for (int i = 0; i < 26; i++) {
			if (charcnts1[i] < charcnts2[i]) {
				return false;
			}
		}		
		return true;
	}
	
	@Override
	void setupBindings() {
		//to be implemented in HW3
		
		//change the view when select from the comboBox
		searchView.gameComboBox.valueProperty().addListener((observable, oldValue, newValue) ->{
			if (searchView.gameComboBox.getSelectionModel().getSelectedIndex() >= 0) {
				int newChoice = searchView.gameComboBox.getSelectionModel().getSelectedIndex();
				String inputTokens = searchView.searchTextField.getText();
				switch (newChoice) {
				case 0: {
					ObservableList<Score> tmplst = FXCollections.observableArrayList();
					for (Score s: WordNerdModel.scoreFromFile) {
						if (iscontain(s.getPuzzleWord(), inputTokens)) {
							tmplst.add(s);
						}
					}
					refreshSearchTable(tmplst);
					break;
				}
				case 1: {
					ObservableList<Score> tmplst = FXCollections.observableArrayList();
					for (Score s: WordNerdModel.scoreFromFile) {
						if (s.getGameId() == 0 && iscontain(s.getPuzzleWord(), inputTokens)) {
							tmplst.add(s);
						}
					}
					refreshSearchTable(tmplst);
					break;
				}
				case 2: {
					ObservableList<Score> tmplst = FXCollections.observableArrayList();
					for (Score s: WordNerdModel.scoreFromFile) {
						if (s.getGameId() == 1 && iscontain(s.getPuzzleWord(), inputTokens)) {
							tmplst.add(s);
						}
					}
					refreshSearchTable(tmplst);
					break;
				}
				}
				//test code
				//System.out.println(searchView.gameComboBox.getSelectionModel().getSelectedIndex());
			}
		});
		
		
		// finish the binding for searchTextField part
		// a terrible bug happens! when deleting there would be no more things
		searchView.searchTextField.textProperty().addListener((observable, oldValue, newValue) ->{
			System.out.println("change from " + oldValue + " to " + newValue);
			ObservableList<Score> tmplst = FXCollections.observableArrayList();
			String inputTokens = searchView.searchTextField.getText();
			
			for (Score s: WordNerdModel.scoreFromFile) {
				String tmp = s.getPuzzleWord();
				if (iscontain(tmp, inputTokens)) {
					if (searchView.gameComboBox.getSelectionModel().getSelectedIndex() > 0) {
						if (searchView.gameComboBox.getValue().equals(s.getGameName())) {
							tmplst.add(s);
						}
					}else {
						tmplst.add(s);
					}
					
				}
			}
			refreshSearchTable(tmplst);
		});
	}

}
