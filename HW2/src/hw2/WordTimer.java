package hw2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class WordTimer {

	Timeline timeline = new Timeline();
	
	Button timerButton;
	int startTime;

	WordTimer(int startTime) {
		//thanks to https://asgteach.com/2011/10/javafx-animation-and-binding-simple-countdown-timer-2/ 
		// Bind the timerLabel text property to the timeSeconds property
		this.startTime = startTime;
		IntegerProperty timeSeconds = new SimpleIntegerProperty(startTime);
		timerButton = new Button();
		timerButton.textProperty().bind(timeSeconds.asString());
		timerButton.setTextFill(Color.BLUE);
		timerButton.setPrefSize(40, 40);
		//thanks to http://fxexperience.com/2011/12/styling-fx-buttons-with-css/
		timerButton.setStyle("-fx-font-size: 1.5em;" +
				"-fx-background-color: white;" + 
				" -fx-background-insets: 0,1,1;" + 
				" -fx-padding: 3 3 3 3;" + 
				" -fx-background-radius: 5em; "  +
				" -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
				);

		timerButton.setMinSize(GameView.SMILEY_TIMER_BUTTON_SIZE, GameView.SMILEY_TIMER_BUTTON_SIZE);
		if (timeline != null) {
			timeline.stop();
		}
		
		timeSeconds.set(startTime);
		timeline.getKeyFrames().addAll( new KeyFrame(Duration.seconds(startTime+1),
				new KeyValue(timeSeconds, 0)));
		timeline.playFromStart();
		
	}
	
	void restart(int startTime) {
		IntegerProperty timeSeconds = new SimpleIntegerProperty(startTime);
		timerButton.textProperty().bind(timeSeconds.asString());
		timeSeconds.set(startTime);
		timeline.getKeyFrames().addAll( new KeyFrame(Duration.seconds(startTime+1),
				new KeyValue(timeSeconds, 0)));
		timeline.playFromStart();

	}
}

