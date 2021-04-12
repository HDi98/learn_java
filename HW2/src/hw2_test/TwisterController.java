//name: haonan di
//andrew id: hdi
package hw2_test;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TwisterController extends WordNerdController{

	TwisterView twisterView;
	Twister twister;
	
	@Override
	void startController() {
		// copying and modifying from the hangmanController!
		twisterView = new TwisterView();
		twister = new Twister();
		twister.twisterRound = twister.setupRound();
		twisterView.refreshGameRoundView(twister.twisterRound);
		setupBindings();
		
		VBox lowerPanel = new VBox();
		lowerPanel.getChildren().add(twisterView.bottomGrid);
		lowerPanel.getChildren().add(WordNerd.exitButton);
		lowerPanel.setAlignment(Pos.CENTER);
		
		WordNerd.root.setTop(twisterView.topMessageText);
		WordNerd.root.setCenter(twisterView.topGrid);
		WordNerd.root.setBottom(lowerPanel);
		
		//bind newWordButton to start a new round, refresh the view,
		//restart the timer, and setupBindings for new HangmanRound
		twisterView.playButtons[0].setOnAction(new NewButtonHandler());
		twisterView.playButtons[1].setOnAction(new TwistButtonHandler());
		twisterView.playButtons[2].setOnAction(new ClearButtonHandler());
		twisterView.playButtons[3].setOnAction(new SubmitButtonHandler());
		
		
		//attach handlers to all clue/answer buttons
		for (int i = 0; i < twisterView.clueButtons.length; i++) {
			twisterView.clueButtons[i].setOnAction(new ClueButtonHandler());
			twisterView.answerButtons[i].setOnAction(new AnswerButtonHandler());
		}
	}
	
	@Override
	void setupBindings() {
		// copy and modify from hangmanController -- setupBindings
		
		//Bind a listener to hangmanRound's clueWordProperty 
		//so that whenever it changes, the clueLabels should also 
		//change in hangmanView 
		twister.twisterRound.clueWordProperty().addListener((observable, oldValue, newValue) -> {
			for (int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
				twisterView.clueButtons[i].setText(String.format("%s", newValue.charAt(i)));
			}
		});

				
		//When timer runs out, set smiley to sadly, isRoundComplete to true
		GameView.wordTimer.timeline.setOnFinished(event -> { 
			twisterView.smileyButton.setGraphic(twisterView.smileyImageViews[GameView.SADLY_INDEX]);
			twister.twisterRound.setIsRoundComplete(true);
		});
		
		//bind the roundComplete result to the view
		for(int i = 1; i <=3; i++) {
			twisterView.playButtons[i].disableProperty().bind(twister.twisterRound.isRoundCompleteProperty());
		}
		twisterView.clueGrid.disableProperty().bind(twister.twisterRound.isRoundCompleteProperty());

	}
	
	private class TwistButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// see in video twister timed out: it should shuffle the word exists in the clueButton
			StringBuilder tmp = new StringBuilder();
			for (int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
				if (!twisterView.clueButtons[i].getText().equals("")) {
					tmp.append(twisterView.clueButtons[i].getText());
					twisterView.clueButtons[i].setText("");
				}
			}
			String tmp1 = twister.makeAClue(tmp.toString());
			// reset the clueButtons
			for(int i = 0; i < tmp1.length(); i++) {
				twisterView.clueButtons[i].setText(Character.toString(tmp1.charAt(i)));
			}
			
		}
		
	}
	
	private class ClueButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// change the button to blank and move it to answerButton
			Button b = (Button)arg0.getSource();
			String tmp = b.getText();
			b.setText("");
			for (int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
				if (twisterView.answerButtons[i].getText().equals("")) {
					twisterView.answerButtons[i].setText(tmp);
					break;
				}
			}
		}
		
	}
	private class AnswerButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// reverse function of clueButtonHandler, when click on answerButtons then it would return to the clueButtons
			Button b = (Button)arg0.getSource();
			String tmp = b.getText();
			b.setText("");
			for (int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
				String tmpNew = twisterView.clueButtons[i].getText();
				if (tmpNew.equals("")) {
					twisterView.clueButtons[i].setText(tmp);
					break;
				}
			}
		}
		
	}
	private class ClearButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// return the word in answerButton back to clueButton accordingly
			ArrayList<Integer> not_null = new ArrayList<>();
			for(int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
				if (!twisterView.answerButtons[i].getText().equals("")) {
					not_null.add(i);
					
				}
//				else {
//					String tmp = twisterView.answerButtons[i].getText();
//					twisterView.answerButtons[i].setText("");
//					for (int j = 0; j < twister.twisterRound.getClueWord().length(); j++) {
//						if (twisterView.clueButtons[j].getText().equals("")) {
//							twisterView.clueButtons[j].setText(tmp);
//							break;
//						}
//					}
//				}//end of if-else
			}
			for (Integer k: not_null) {
				String tmp = twisterView.answerButtons[k].getText();
				twisterView.answerButtons[k].setText("");
				for (int j = 0; j < twister.twisterRound.getClueWord().length(); j++) {
					if (twisterView.clueButtons[j].getText().equals("")) {
						twisterView.clueButtons[j].setText(tmp);
						break;
					}
				}
			}
		}
		
	}
	private class SubmitButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// also note that submit do not clear the answerButtons and clueButtons
			// part 1: generate the word in answer Button
			StringBuilder tmpClue = new StringBuilder();
			for(int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
				tmpClue.append(twisterView.answerButtons[i].getText());
			}
			String tmpClue1 = tmpClue.toString();
				
			
			// match the answer button and add tmpClue1 to twisterRound
			int n = twister.nextTry(tmpClue1);
			
			
			//System.out.println(tmpClue1 + n + "\n" + twister.twisterRound.getPuzzleWord());

			//renew the smiley button
			twisterView.smileyButton.setGraphic(twisterView.smileyImageViews[n]);
			// renew wordScoreLabels: *****related to solutionListViews, Unsovable*****
			int n1 = tmpClue1.length() - Twister.TWISTER_MIN_WROD_LENGTH;
			switch(n) {
			case GameView.THUMBS_UP_INDEX:{
				//calculate the total correct word to renew the topmessage
				int total_count = 0;
				for (int i = 0; i < twister.twisterRound.getSubmittedListsByWordLength().size(); i++) {
					total_count += twister.twisterRound.getSubmittedListsByWordLength(i).size();
				}
				twisterView.topMessageText.setText("Twist to find " + Integer.toString(twister.twisterRound.getSolutionWordsList().size() - total_count) + " of " 
							+ Integer.toString(twister.twisterRound.getSolutionWordsList().size()) + " words");
				// refresh the clueGrid
				for(int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
					
					twisterView.clueButtons[i].setText(Character.toString(twister.twisterRound.getClueWord().charAt(i)));
					twisterView.answerButtons[i].setText("");
				}
				
				// it means right, add to twisterView
				twisterView.solutionListViews[n1].setItems(twister.twisterRound.getSubmittedListsByWordLength(n1));
				// refresh the wordScoreLabels
				twisterView.wordScoreLabels[n1].setText(String.format("%d/%d", twister.twisterRound.getSubmittedListsByWordLength(n1).size(), twister.twisterRound.getSolutionListsByWordLength(n1).size()));
				break;
			}
			
			case GameView.REPEAT_INDEX:{
				break;
			}
			
			case GameView.THUMBS_DOWN_INDEX: break;
			
			}
						
			
			// check if the game ends: seems also related to solutionListViews, count the num
			int count = 0;
			for (int i = 0; i < twister.twisterRound.getSubmittedListsByWordLength().size(); i++) {
				count += twister.twisterRound.getSubmittedListsByWordLength(i).size();
			}
			if (count == twister.twisterRound.getSolutionWordsList().size()) {
				// won the game: round is over
				GameView.wordTimer.timeline.stop();
				twister.twisterRound.setIsRoundComplete(true);
				twisterView.smileyButton.setGraphic(twisterView.smileyImageViews[GameView.SMILEY_INDEX]);
			}
		}
		
	}
	private class NewButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			
			// copy and modify from hangmanController newButton
			twister.twisterRound = twister.setupRound();
			//twisterView = new TwisterView();
			twisterView.refreshGameRoundView(twister.twisterRound);
			GameView.wordTimer.restart(Twister.TWISTER_GAME_TIME);
			startController();
			setupBindings();
			
			
			//test code, print the list to help check
			for (String s: twister.twisterRound.getSolutionWordsList()) {
				System.out.println(s);
			}
			
		}
		
	}
}
