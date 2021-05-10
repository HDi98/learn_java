package hw3_shuwu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;


public class SearchController extends WordNerdController {
	SearchView searchView;

	//this is a placeholder class. Will be completed in HW3

	@Override
	void startController() {
		wordNerdModel.readScore();
		searchView = new SearchView();

		searchView.setupView();
		setupBindings();

		searchView.gameComboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue ov, String t, String t1) {
				if(t1.equalsIgnoreCase("Hangman")){
					searchView.setupSearchTableView(0);
				}else if(t1.equalsIgnoreCase("Twister")){
					searchView.setupSearchTableView(1);
				}else{
					searchView.setupSearchTableView(2);
				}
			}
		});

	}

	@Override
	void setupBindings() {
		searchView.searchTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int id = 0;
				String game = searchView.gameComboBox.getValue();
//				System.out.println("game" + game);
				if(game.equalsIgnoreCase("Twister")){
					id = 1;
				}else if(game.equalsIgnoreCase("All games")){
					id = 2;
				}
				searchView.setupSearchTableView(id);
			}
		});



	}



}
