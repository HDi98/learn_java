package hw2_shuwu;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class WordNerd extends Application{

	static final int MAIN_BUTTON_COUNT = 4;  //Hangman, Twister, Scoreboard, Search
	static final int GAME_COUNT = 2; //Hangman, Twister
	static final int HANGMAN_ID = 0;
	static final int TWISTER_ID = 1;
	static final int SCOREBOARD_ID = 2;
	static final int SEARCH_ID = 3;

	static final int GAME_SCENE_WIDTH = 700;
	static final int GAME_SCENE_HEIGHT = 600;

	static BorderPane root = new BorderPane();

	//exitButton is created here because it is used both in Hangman and Twister and
	//needs access to menuBar and openingGrid to come back to opening scene
	//It is made static  because all other views need it
	static Button exitButton = new Button("Exit");

	WordNerdView wordNerdView = new WordNerdView(); //will contain opening view

	static WordNerdController[] wordNerdControllers = new WordNerdController[MAIN_BUTTON_COUNT];  //controllers for four mainButtons

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene;
		primaryStage.setTitle("The Word Nerd");
		scene = new Scene(root, GAME_SCENE_WIDTH, GAME_SCENE_HEIGHT);
		wordNerdView.setupWordNerd(wordNerdView.mainButtons);
		WordNerdModel.readWordsFile(WordNerdModel.WORDS_FILE_NAME);
		setupActions();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	/** setupActions() binds mainButtons to their respective controllers,
	 * exitButton to its event handler actions, aboutMenuItem to its handler.
	 * It also binds wordSourceMenuItem to open the wordSource file
	 * and invokes WordNerdModel.readWordsFile() to load the words
	 */
	void setupActions() {
		//create controllers
		wordNerdControllers[WordNerd.HANGMAN_ID] = new HangmanController();
		wordNerdControllers[WordNerd.TWISTER_ID] = new TwisterController();
		wordNerdControllers[WordNerd.SCOREBOARD_ID] = new ScoreController();
		wordNerdControllers[WordNerd.SEARCH_ID] = new SearchController();

		//bind mainButtons to their controllers
		for (int i = 0; i < MAIN_BUTTON_COUNT; i++) {
			int index = i;
			wordNerdView.mainButtons[i].setOnAction(event -> wordNerdControllers[index].startController());  //polymorphism in action
		}

		//exit button should bring back from other views to wordNerdView
		exitButton.setOnAction(e -> {
			root.getChildren().clear();
			root.setTop(wordNerdView.menuBar);
			root.setCenter(wordNerdView.openingGrid);
			GameView.wordTimer.timeline.stop();
		});

		//bind handler for changing word source file
		wordNerdView.wordSourceMenuItem.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select file");
			fileChooser.setInitialDirectory(new File("data")); //local path
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("CSV Files", "*.txt"),
					new ExtensionFilter("All Files", "*.*"));
			File file = null;
			if ((file = fileChooser.showOpenDialog(null)) != null) {
				WordNerdModel.readWordsFile(file.getAbsolutePath());
				wordNerdView.wordSourceMenuItem.setText("Words from: " + file.getName());
			}
		});

		wordNerdView.aboutMenuItem.setOnAction(new AboutHandler());
		wordNerdView.exitMenuItem.setOnAction(event -> Platform.exit());
	}

	private class AboutHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("WordNerd");
			alert.setContentText("Version 2.0 \nRelease 1.0\nCopyright CMU\n");
			Image image = new Image(getClass().getClassLoader().getResource("minion.png").toString());
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitWidth(150);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			alert.setGraphic(imageView);
			alert.showAndWait();
		}
	}

}
