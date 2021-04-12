package hw2_test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class GameView {
	
	static final int MESSAGE_IMAGE_COUNT = 5; //smiley, thumbs up, thumbs down, repeat, sadly
	static final int SMILEY_INDEX = 0;
	static final int THUMBS_UP_INDEX = 1;
	static final int THUMBS_DOWN_INDEX = 2;
	static final int REPEAT_INDEX = 3;
	static final int SADLY_INDEX = 4;
	
	static final int SMILEY_TIMER_BUTTON_SIZE = 50;  //button size for smiley and timer buttons
	static WordTimer wordTimer;

	GridPane topGrid, clueGrid, bottomGrid, playButtonsGrid;
	Button smileyButton;

	ImageView[] smileyImageViews;
	
	Text topMessageText;
	
	abstract void setupSizesAlignmentsEtc();
	abstract void setupTopGrid();
	abstract void setupBottomGrid();
	abstract void refreshGameRoundView(GameRound gameRound);
	
	/**GameView() sets up the parts common to both Hangman and Twister.
	 * It creates the main grids, topMessageText
	 * and sets up the smileyButton.
	 */
	GameView() {
		//initialize member variables
		topGrid = new GridPane();
		clueGrid = new GridPane();
		bottomGrid = new GridPane();
		playButtonsGrid = new GridPane();
		smileyImageViews = new ImageView[MESSAGE_IMAGE_COUNT];
		topMessageText = new Text();
		
		//setup top message
		topMessageText.setFont(Font.font(20));

		//thanks to https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
		topMessageText.setStyle( "-fx-font: 30px Tahoma;" + 
				" -fx-fill: linear-gradient(from 0% 50% to 50% 100%, repeat, lightgreen 0%, lightblue 50%);" +
				" -fx-stroke: gray;" +
				" -fx-background-color: gray;" +
				" -fx-stroke-width: 1;") ;
		BorderPane.setAlignment(topMessageText, Pos.CENTER);
		//setup Smiley and Exit as they are both common to Hangman and Twister
		setupSmileyButton();
		WordNerd.root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	/**setupSmileyButton() initializes all smiley buttons
	 * with their images, sizes, styles, etc.*/
	void setupSmileyButton() {
		smileyButton  = new Button();
		for (int i = 0; i < smileyImageViews.length; i++) {
			smileyImageViews[i] = new ImageView();
			smileyImageViews[i].setFitHeight(30);
			smileyImageViews[i].setFitWidth(30);
			smileyImageViews[i].setPreserveRatio(true);
			smileyImageViews[i].setSmooth(true);
		}
		smileyImageViews[SMILEY_INDEX].setImage(new Image(getClass().getClassLoader().getResource("smiley.png").toString()));
		smileyImageViews[THUMBS_UP_INDEX].setImage(new Image(getClass().getClassLoader().getResource("thumbsup.png").toString()));
		smileyImageViews[THUMBS_DOWN_INDEX].setImage(new Image(getClass().getClassLoader().getResource("thumbsdown.png").toString()));
		smileyImageViews[REPEAT_INDEX].setImage(new Image(getClass().getClassLoader().getResource("repeat.png").toString()));
		smileyImageViews[SADLY_INDEX].setImage(new Image(getClass().getClassLoader().getResource("sadly.jpg").toString()));

		smileyButton.setStyle(" -fx-background-insets: 0,1,1;" + 
				" -fx-padding: 3 3 3 3;" + 
				" -fx-background-radius: 5em; " +
				" -fx-background-color: white; " +
				" -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		smileyButton.setMinSize(GameView.SMILEY_TIMER_BUTTON_SIZE, GameView.SMILEY_TIMER_BUTTON_SIZE);

		smileyButton.setGraphic(smileyImageViews[SMILEY_INDEX]);
		
	}


}
