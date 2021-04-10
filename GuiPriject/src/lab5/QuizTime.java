//author: Haonan Di
// Andrew ID: hdi
package lab5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class QuizTime extends Application{
	
	Quiz[] availableQuizzes = { new MathQuiz(), new EnglishQuiz() };  //Your handlers must use these two objects. No new quiz objects to be created.
	Quiz chosenQuiz; //should point to the quiz chosen by the user
	
	/********* GUI components *************/
	Label scoreLabel = new Label("Score: 0 out of 0");
	Button mathButton = new Button ("Math");
	Button englishButton = new Button("English");
	Button checkButton = new Button ("Check Answer");
	TextArea resultTextArea = new TextArea();
	Label questionLabel = new Label();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		/********* set test objects *************/
		setStage(stage);
		setAction();
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	void setStage(Stage stage) {
		GridPane root = new GridPane();

		scoreLabel.setPrefSize(600, 50);
		mathButton.setPrefSize(300, 50);
		englishButton.setPrefSize(300, 50);
		checkButton.setPrefSize(600, 50);
		questionLabel.setPrefSize(600, 50);
		resultTextArea.setPrefSize(600, 50);
		
		questionLabel.setStyle("-fx-font-size: 12;");
		questionLabel.setAlignment(Pos.CENTER);
		questionLabel.setWrapText(true);
		scoreLabel.setStyle("-fx-font-size: 15;");
		scoreLabel.setAlignment(Pos.CENTER);
		resultTextArea.setStyle("-fx-align: center");
		resultTextArea.setPromptText("Enter your answer here");
		root.add(scoreLabel, 0, 0, 2, 1);
		root.add(mathButton, 0, 1);
		root.add(englishButton, 1, 1);
		root.add(questionLabel, 0, 2, 2, 1);
		root.add(resultTextArea, 0, 3, 2, 1);
		root.add(checkButton, 0, 4, 2, 1);
		
		Scene scene = new Scene(root, 600, 200);
		stage.setTitle("Quiz Time");
		stage.setScene(scene);
		
	}

	
	/** setAction binds the buttons to their handlers */
	void setAction() {
		//enter your code here
		mathButton.setOnAction(new MathButtonHandler());
		englishButton.setOnAction(new EnglishButtonHandler());
		checkButton.setOnAction(new CheckButtonHandler());
	}
	
	
	/***************all event handlers below this line *******************/

	private class MathButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			resultTextArea.clear();
			chosenQuiz = availableQuizzes[0];
			chosenQuiz.createQuestion();
			questionLabel.setText("How much is " + chosenQuiz.questionString[0] + " " + chosenQuiz.questionString[1] + " " + chosenQuiz.questionString[2]);
			checkButton.setStyle("-fx-color:whitesmoke");
		}
		
	}
	
	private class EnglishButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			resultTextArea.clear();
			chosenQuiz = availableQuizzes[1];
			chosenQuiz.createQuestion();
			questionLabel.setText("What's the word for: " + chosenQuiz.questionString[1]);
			checkButton.setStyle("-fx-color:whitesmoke");
		}
		
	}
	
	private class CheckButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if(chosenQuiz == null) {
				return;
			}
			
			
			
			String result = resultTextArea.getText();
			if(chosenQuiz.checkAnswer(result)) {
				checkButton.setStyle("-fx-color:lightgreen");
				
			}
			else {
				checkButton.setStyle("-fx-color:red");
			}
			scoreLabel.setText("Score: " + Integer.toString(Quiz.score) + " out of " + Integer.toString(Quiz.totalCount));
			
		}
		
	}

}
