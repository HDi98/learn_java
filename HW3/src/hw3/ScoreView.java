package hw3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ScoreView {
	GridPane scoreGrid = new GridPane();

	void setupView() {
		scoreGrid.setPrefSize(WordNerd.GAME_SCENE_WIDTH, WordNerd.GAME_SCENE_WIDTH);
		
		//label at the top
		Text scoreboardLabel = new Text("Score Board");
		scoreboardLabel.setStyle( "-fx-font: 30px Tahoma;" + 
				" -fx-fill: linear-gradient(from 0% 50% to 50% 100%, repeat, lightgreen 0%, lightblue 50%);" +
				" -fx-stroke: gray;" +
				" -fx-background-color: gray;" +
				" -fx-stroke-width: 1;") ;
		
		scoreGrid.add(scoreboardLabel, 0, 0, 2, 1);
		scoreGrid.add(WordNerd.exitButton, 0, 3, 2, 1);
		
		scoreGrid.setVgap(10);
		scoreGrid.setHgap(10);
		scoreGrid.setPadding(new Insets(50,50,50,50));
		scoreGrid.setStyle("-fx-background-color: white");
		scoreGrid.setAlignment(Pos.CENTER);
	}
}
