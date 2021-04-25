//name: haonan di
//andrew id: hdi
package hw3;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WordNerdView {
	MenuBar menuBar = new MenuBar();
	MenuItem wordSourceMenuItem = new MenuItem("Words from: " + WordNerdModel.WORDS_FILE_NAME);
	MenuItem exitMenuItem = new MenuItem("Exit");
	MenuItem aboutMenuItem = new MenuItem("About");
	
	ImageView[] mainButtonImageViews = new ImageView[WordNerd.MAIN_BUTTON_COUNT]; //images for main buttons
	GridPane openingGrid;
	
	Button[] mainButtons = new Button[WordNerd.MAIN_BUTTON_COUNT]; 

	void setupWordNerd(Button[] mainButtons) {
		openingGrid = new GridPane();
		openingGrid.setHgap(20);
		openingGrid.setVgap(20);
		setupImages();
		for (int i = 0; i < mainButtons.length; i++) {
			mainButtons[i] = new Button();
			mainButtons[i].setPrefSize(100, 100);
			mainButtons[i].setGraphic(mainButtonImageViews[i]);
			mainButtons[i].setStyle("-fx-background-color: lightblue");
			openingGrid.add(mainButtons[i], i%2, i/2);
		}
		setupExitButton();
		openingGrid.setAlignment(Pos.CENTER);
		WordNerd.root.setTop(setupMenus());
		WordNerd.root.setCenter(openingGrid);
	}

	//setup main button images
	void setupImages() {
		Image[] images = new Image[WordNerd.MAIN_BUTTON_COUNT]; 
		for (int i = 0; i < images.length; i++) {
			mainButtonImageViews[i] = new ImageView();
			mainButtonImageViews[i].setFitHeight(200);
			mainButtonImageViews[i].setFitWidth(200);
			mainButtonImageViews[i].setPreserveRatio(true);
			mainButtonImageViews[i].setSmooth(true);
			switch (i) {
			case 0: 
				images[i] = new Image(getClass().getClassLoader().getResource("hangman.png" ).toString());
				break;
			case 1: 
				images[i] = new Image(getClass().getClassLoader().getResource("twister.png").toString());
				break;
			case 2:
				images[i] = new Image(getClass().getClassLoader().getResource("scoreboard.png").toString());
				break;
			case 3: 
				images[i] = new Image(getClass().getClassLoader().getResource("search.png").toString()); 
			}
			mainButtonImageViews[i].setImage(images[i]);
		}
	}
	
	//sets up the menus
	MenuBar setupMenus() {
		GridPane topGrid = new GridPane();
		topGrid.setVgap(5);
		topGrid.setHgap(5);
		Menu wordNerdMenu = new Menu("WordNerd");
		Menu aboutMenu = new Menu("About");

		wordNerdMenu.getItems().addAll(wordSourceMenuItem, exitMenuItem);
		aboutMenu.getItems().add(aboutMenuItem);
		menuBar.getMenus().addAll(wordNerdMenu, aboutMenu);
		return menuBar;
	}
	
	
	/** setupExitButton() sets up the style for exit button */
	void setupExitButton() {
		WordNerd.exitButton.setPrefSize(WordNerd.GAME_SCENE_WIDTH, 20);
		WordNerd.exitButton.setStyle("-fx-background-color: gray," +
				" linear-gradient(lightblue, gray), " +
				" linear-gradient(lightblue 0%, white 49%, white 50%, lightblue 100%);" + 
				" -fx-background-insets: 0,1,2;"); 
		WordNerd.exitButton.setTextFill(Color.BLACK);
		WordNerd.exitButton.setFont(Font.font(15));
	}
}
