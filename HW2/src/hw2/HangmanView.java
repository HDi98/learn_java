package hw2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HangmanView extends GameView{
	Label[] clueLabels;  			//each label carries a letter or a dash in the clue
	Button[] keyboardButtons;		//buttons for user input used in alphaGrid
	Button newWordButton;			//button to refresh the game with a new word
	GridPane keyboardGrid;			//container for keyboardButtons 
	Label scoreLabel; 				//displays the scoreString
	
	HangmanView() {
		//initialize member variables
		newWordButton = new Button("New Word");	
		keyboardGrid = new GridPane();
		scoreLabel = new Label();
		//assemble the grids
		setupTopGrid();
		setupBottomGrid();
		setupSizesAlignmentsEtc();
	}
	
	@Override
	void setupSizesAlignmentsEtc() {
		//setup playButtonsGrid
		newWordButton.setPrefSize(120, 20);
		newWordButton.setStyle("-fx-background-color: gray," +
				" linear-gradient(lightblue, gray), " +
				" linear-gradient(lightblue 0%, white 49%, white 50%, lightblue 100%);" + 
				" -fx-background-insets: 0,1,2;"); 
		newWordButton.setTextFill(Color.BLACK);
		newWordButton.setFont(Font.font(15));
		newWordButton.setPrefSize(400, 20);
		playButtonsGrid.setVgap(10);
		playButtonsGrid.setHgap(10);
		
		topGrid.setAlignment(Pos.CENTER);
		clueGrid.setAlignment(Pos.CENTER);
		playButtonsGrid.setAlignment(Pos.CENTER);
		
		scoreLabel.setPrefSize(600, 30);
		scoreLabel.setStyle("-fx-background-color: lightblue; "
				+ "-fx-font: 20px Arial; ");
		scoreLabel.setAlignment(Pos.CENTER);
		
		for (int i = 0; i < 26; i++) {
			keyboardButtons[i].setPrefSize(100, 50);
			keyboardButtons[i].setStyle("-fx-background-color: lightblue," + 
					" linear-gradient(white, blue)," + 
					" linear-gradient(white 0%, white 49%, white 50%, lightblue 100%);" + 
					" -fx-font: 20px Arial;" +
					" -fx-background-insets: 0,1,1;"); 
		}
		keyboardGrid.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		bottomGrid.setAlignment(Pos.CENTER);
	}
	
	@Override
	void setupTopGrid() {
		topMessageText.setText("Guess the missing letters!"); //set Hangman message
		topGrid.add(clueGrid, 0, 0);
		topGrid.add(playButtonsGrid, 0, 1);
		
		wordTimer = new WordTimer(Hangman.HANGMAN_GAME_TIME);
		playButtonsGrid.add(wordTimer.timerButton, 0, 0);
		playButtonsGrid.add(newWordButton, 1, 0);
		playButtonsGrid.add(smileyButton, 2, 0);
	}
	

	@Override
	void setupBottomGrid() {
		bottomGrid.getChildren().clear();
		
		//setup keyboard grid
		keyboardGrid.add(scoreLabel, 0, 0, 6, 1);
		keyboardButtons = new Button[26];
		char alpha = 'a';
		for (int i = 0; i < 26; i++) {
			keyboardButtons[i] = new Button(Character.toString(alpha++));
		}
		for (int i = 0; i < 24; i++) {
			keyboardGrid.add(keyboardButtons[i], i % 6, ((int)((i)/6)) + 1); 
		}
		keyboardGrid.add(keyboardButtons[24], 2, 8);
		keyboardGrid.add(keyboardButtons[25], 3, 8);
		bottomGrid.add(keyboardGrid, 0, 1);	
	}
	
	/**refreshGameRoundView() clears up previous game round and prepares the clueGrid 
	 * with new clue labels using the new clueWord.
	 * It also refreshes the keyboardButtons, disabling those buttons that have letters
	 * in the clue*/
	@Override
	void refreshGameRoundView(GameRound	hangmanRound) {
		clueLabels = new Label[hangmanRound.getClueWord().length()];
		clueGrid.getChildren().clear();  //clear components from the previous game round
		//create new clue labels
		for (int i = 0; i < hangmanRound.getClueWord().length(); i++) { 
			clueLabels[i] = new Label();
			clueLabels[i].setText(Character.toString(hangmanRound.getClueWord().charAt(i)));
			clueLabels[i].setPrefSize(70, 70);
			clueLabels[i].setStyle(" -fx-background-insets: 0,1,2;" +
					" -fx-font: 30px Arial;" +
					" -fx-background-color: white;" +
					" -fx-background-insets: 0,1,1;" +
					"-fx-background-radius: 2em;"); 
			clueLabels[i].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			clueLabels[i].setAlignment(Pos.CENTER);
			clueGrid.add(clueLabels[i], i, 0);
		}
		
		scoreLabel.setText(""); //clear score label

		//refresh keyboardButtons, disabling only those that are in the new clueWord.
		for (int i = 0; i < keyboardButtons.length; i++) {
			if (hangmanRound.getClueWord().contains(keyboardButtons[i].getText())) 
				keyboardButtons[i].setDisable(true);
			else keyboardButtons[i].setDisable(false);
		}
	}

}
