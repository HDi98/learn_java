package hw3_shuwu;
/**
 * name: Shu Wu
 * andrew id: shuwu
 */
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TwisterView extends GameView{

	static final int PLAY_BUTTON_COUNT = 4; //New word, Twist, Clear, and Submit buttons
	Label[] wordLengthLabels;  //shows letter count on the left of each solution list
	Label[] wordScoreLabels; //shows score for a letter count on the right of each solution list
	Button[] clueButtons ;  //clue buttons on the top
	Button[] answerButtons; //empty buttons below clue buttons
	Button[] playButtons;  //buttons for New Word, Twise, Clear, and Submit
	ListView<String>[] solutionListViews;  //lists that show he correct guesses made by the player

	TwisterView() {

		//initialize member variables
		wordLengthLabels = new Label[Twister.SOLUTION_LIST_COUNT];
		wordScoreLabels = new Label[Twister.SOLUTION_LIST_COUNT];
		playButtons = new Button[TwisterView.PLAY_BUTTON_COUNT];

		setupTopGrid();
		setupBottomGrid();
		setupSizesAlignmentsEtc();
	}

	@Override
	void setupTopGrid() {
		topGrid.add(clueGrid, 0, 0);
		topGrid.add(playButtonsGrid, 0, 2);

		//setup play buttons grid
		playButtons[0] = new Button("New Word");
		playButtons[1] = new Button("Twist");
		playButtons[2] = new Button("Clear");
		playButtons[3] = new Button("Submit");

		for (int col = 0; col < playButtons.length; col++) {
			playButtons[col].setPrefSize(120, 20);
			playButtons[col].setStyle("-fx-background-color: gray," +
					" linear-gradient(lightblue, gray), " +
					" linear-gradient(lightblue 0%, white 49%, white 50%, lightblue 100%);" +
					" -fx-background-insets: 0,1,2;");
			playButtons[col].setTextFill(Color.BLACK);
			playButtons[col].setFont(Font.font(15));

			playButtonsGrid.add(playButtons[col], col + 1, 0);
		}

		wordTimer = new WordTimer(Twister.TWISTER_GAME_TIME);
		playButtonsGrid.add(wordTimer.timerButton, 0, 0);
		playButtonsGrid.add(smileyButton, 5, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	void setupBottomGrid() {
		bottomGrid.getChildren().clear();
		bottomGrid.setAlignment(Pos.CENTER);
		bottomGrid.setVgap(5);
		bottomGrid.setMinSize(700, 300);
		//setup letter count buttons
		for (int i = 0; i < wordLengthLabels.length; i++ ) {
			wordLengthLabels[i] = new Label(String.format("%d", i + 3));  //starting with 3
			wordLengthLabels[i].setPrefSize(50, 50);

			wordLengthLabels[i].setStyle( "-fx-font: 15px Tahoma;" +
					" -fx-background-color: lightgray;");
			wordLengthLabels[i].setTextFill(Color.BLACK);
			wordLengthLabels[i].setAlignment(Pos.CENTER);
			bottomGrid.add(wordLengthLabels[i], 0, i);
		}

		//setup solution lists
		solutionListViews = new ListView[Twister.SOLUTION_LIST_COUNT];
		for (int i = 0; i < solutionListViews.length; i++) {
			solutionListViews[i] = new ListView<>();
			solutionListViews[i].setOrientation(Orientation.HORIZONTAL);
			solutionListViews[i].setPrefSize(525, 50);
			bottomGrid.add(solutionListViews[i], 1, i);
		}

		//setup word score buttons
		wordScoreLabels = new Label[Twister.SOLUTION_LIST_COUNT];
		for (int i = 0; i < wordScoreLabels.length; i++ ) {
			wordScoreLabels[i] = new Label(String.format("%d", i + 3));  //starting with 3
			wordScoreLabels[i].setPrefSize(50, 50);
			wordScoreLabels[i].setStyle("-fx-font: 15px Tahoma;" +
					" -fx-background-color: lightgray;");
			wordScoreLabels[i].setTextFill(Color.BLACK);
			wordScoreLabels[i].setAlignment(Pos.CENTER);
			bottomGrid.add(wordScoreLabels[i], 2, i);
		}
	}

	@Override
	void setupSizesAlignmentsEtc() {
		playButtonsGrid.setHgap(10);
		playButtonsGrid.setVgap(10);

		topGrid.setMinSize(WordNerd.GAME_SCENE_WIDTH, 200);
		topGrid.setAlignment(Pos.CENTER);
		topGrid.setHgap(10);
		topGrid.setVgap(10);

		bottomGrid.setAlignment(Pos.BASELINE_CENTER);
		clueGrid.setAlignment(Pos.CENTER);
	}

	//setupButtonsLookAndFeel() to be invoked in refreshGameRoundView
	//after new clueButtons and answerButtons are created
	void setupButtonsLookAndFeel() {
		for (int i = 0; i < clueButtons.length; i++) {
			clueButtons[i].setStyle("-fx-background-color: white," +
					" linear-gradient(lightblue, blue)," +
					" linear-gradient(lightblue 0%, white 49%, white 50%, lightblue 100%);" +
					" -fx-font: 20px Arial;" +
					" -fx-background-insets: 0,1,1;" +
					" -fx-padding: 3 30 3 30;" +
					" -fx-background-radius: 5em; " );
			clueButtons[i].setMinSize(75, 75);
		}
		for (int i = 0; i < answerButtons.length; i++) {
			answerButtons[i].setStyle("-fx-background-color: white," +
					" linear-gradient(lightgray, gray)," +
					" linear-gradient(lightgray 0%, white 49%, white 50%, lightgray 100%);" +
					" -fx-font: 20px Arial;" +
					" -fx-background-insets: 0,1,1;" +
					" -fx-padding: 3 30 3 30;" +
					" -fx-background-radius: 5em; " );
			answerButtons[i].setMinSize(75, 75);
		}
	}


	/**refreshGameRoundView() clears up previous game round and
	 * refreshes all components with info in the new gameRound */
	void refreshGameRoundView(GameRound gameRound) {

		clueGrid.getChildren().clear();
		clueButtons = new Button[gameRound.getClueWord().length()];
		answerButtons = new Button[gameRound.getClueWord().length()];


		for(int i = 0; i < gameRound.getClueWord().length(); i++){
			clueButtons[i] = new Button(gameRound.getClueWord().charAt(i) + "");
			clueButtons[i].setMinSize(Twister.CLUE_BUTTON_SIZE, Twister.CLUE_BUTTON_SIZE);
			answerButtons[i] = new Button();
			clueGrid.add(clueButtons[i], i , 0);
			clueGrid.add(answerButtons[i], i , 1);
		}

		setupButtonsLookAndFeel();


		//set up BottomGrid according to the length of solutionListsByWordLength in this round
		bottomGrid.getChildren().clear();
		bottomGrid.setAlignment(Pos.CENTER);
		bottomGrid.setVgap(5);
		bottomGrid.setMinSize(700, 300);

		int count = 0;
		for (int i = 0; i < Twister.SOLUTION_LIST_COUNT; i++){
			if(((TwisterRound)gameRound).getSolutionListsByWordLength(i).size() != 0){
				count++;
			}
		}

		//set up wordLengthLabels
		int index = 0;
		wordLengthLabels = new Label[count];
		for (int i = 0; i < Twister.SOLUTION_LIST_COUNT; i++) {
			if(((TwisterRound)gameRound).getSolutionListsByWordLength(i).size() != 0){
				wordLengthLabels[index] = new Label(String.format("%d", i + 3));  //starting with 3
				wordLengthLabels[index].setPrefSize(50, 50);
				wordLengthLabels[index].setStyle( "-fx-font: 15px Tahoma;" +
						" -fx-background-color: lightgray;");
				wordLengthLabels[index].setTextFill(Color.BLACK);
				wordLengthLabels[index].setAlignment(Pos.CENTER);
				bottomGrid.add(wordLengthLabels[index], 0, i);
				index++;
			}

		}


		//set up solutionListViews
		int index1 = 0;
		solutionListViews = new ListView[count];
		for (int i = 0; i < Twister.SOLUTION_LIST_COUNT; i++) {
			if(((TwisterRound)gameRound).getSolutionListsByWordLength(i).size() != 0){
				solutionListViews[index1] = new ListView<>();
				solutionListViews[index1].setOrientation(Orientation.HORIZONTAL);
				solutionListViews[index1].setPrefSize(525, 50);
				bottomGrid.add(solutionListViews[index1], 1, i);
				index1++;
			}

		}

		//set up wordScoreLabels
		wordScoreLabels = new Label[count];
		int index2 = 0;
		for (int i = 0; i < Twister.SOLUTION_LIST_COUNT; i++ ) {
			if(((TwisterRound)gameRound).getSolutionListsByWordLength(i).size() != 0){
				int size = ((TwisterRound)gameRound).getSolutionListsByWordLength(i).size();
				wordScoreLabels[index2] = new Label(0 + "/" + size);  //starting with 3
				wordScoreLabels[index2].setPrefSize(50, 50);
				wordScoreLabels[index2].setStyle("-fx-font: 15px Tahoma;" +
						" -fx-background-color: lightgray;");
				wordScoreLabels[index2].setTextFill(Color.BLACK);
				wordScoreLabels[index2].setAlignment(Pos.CENTER);
				bottomGrid.add(wordScoreLabels[index2], 2, i);
				index2++;
			}
		}

		wordTimer.restart(Twister.TWISTER_GAME_TIME);
	}
}

















