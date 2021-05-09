package hw3_shuwu;
/**
 * name: Shu Wu
 * andrew id: shuwu
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Collections;

public class TwisterController extends WordNerdController {

    TwisterView twisterView;
    Twister twister;

    /**
     * startController() creates new Twister, TwisterView, invokes setupRound() to create a new twisterRound,
     * refreshes the view for next round, and invokes setupBindings to bind the new
     * TwisterRound properties with GUI components.
     */
    void startController() {
        twisterView = new TwisterView();
        twister = new Twister();
        twister.twisterRound = twister.setupRound();

        twisterView.topMessageText.setText("Twist to find " +
                twister.twisterRound.getSolutionWordsList().size() + " words!");

        twisterView.refreshGameRoundView(twister.twisterRound);
        setupBindings();

        VBox lowerPanel = new VBox();
        lowerPanel.getChildren().add(twisterView.bottomGrid);
        lowerPanel.getChildren().add(WordNerd.exitButton);
        lowerPanel.setAlignment(Pos.CENTER);

        WordNerd.root.setTop(twisterView.topMessageText);
        WordNerd.root.setCenter(twisterView.topGrid);
        WordNerd.root.setBottom(lowerPanel);

        //bind clueButtons
        for (int i = 0; i < twisterView.clueButtons.length; i++) {
            twisterView.clueButtons[i].setOnAction(new ClueButtonHandler());
        }

        //bind answerButtons
        for (int i = 0; i < twisterView.clueButtons.length; i++) {
            twisterView.answerButtons[i].setOnAction(new AnswerButtonHandler());
        }

        //bind four playButtons
        twisterView.playButtons[Twister.NEW_WORD_BUTTON_INDEX].setOnAction(new NewButtonHandler());
        twisterView.playButtons[Twister.TWIST_BUTTON_INDEX].setOnAction(new TwistButtonHandler());
        twisterView.playButtons[Twister.CLEAR_BUTTON_INDEX].setOnAction(new ClearButtonHandler());
        twisterView.playButtons[Twister.SUBMIT_BUTTON_INDEX].setOnAction(new SubmitButtonHandler());

    }

    void setupBindings() {
        //Bind a listener to twiserRound's clueWordProperty
        //so that whenever it changes, the clueLabels should also
        //change in twisterView
        twister.twisterRound.clueWordProperty().addListener((observable, oldValue, newValue) -> {
            for (int i = 0; i < twister.twisterRound.getClueWord().length(); i++) {
                twisterView.clueButtons[i].setText(String.format("%s", newValue.charAt(i)));
            }
        });

        //When timer runs out, set smiley to sadly, isRoundComplete to true
        GameView.wordTimer.timeline.setOnFinished(event -> {
            twisterView.smileyButton.setGraphic(twisterView.smileyImageViews[GameView.SADLY_INDEX]);
            writeScore();
            twister.twisterRound.setIsRoundComplete(true);
        });



        //Bind disable property to twisterRound's isRoundCompleteProperty.
        //This means that when round is complete, the twistButton, clearButton,
        // submitButton and clueGrid should be disabled

        twisterView.playButtons[1].disableProperty().bind(twister.twisterRound.isRoundCompleteProperty());
        twisterView.playButtons[2].disableProperty().bind(twister.twisterRound.isRoundCompleteProperty());
        twisterView.playButtons[3].disableProperty().bind(twister.twisterRound.isRoundCompleteProperty());
        twisterView.clueGrid.disableProperty().bind(twister.twisterRound.isRoundCompleteProperty());

    }

    // EventHandler attached to Clear play button.
    // Clears the letters in answerButtons, if any, and moves them to empty slots in clueButtons
    class ClearButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            StringBuilder tmpAnswer = new StringBuilder();
            for (int i = 0; i < twisterView.answerButtons.length; i++) {
                tmpAnswer.append(twisterView.answerButtons[i].getText());
                twisterView.answerButtons[i].setText("");
            }
            int index = 0;
            for (int i = 0; i < twisterView.clueButtons.length; i++) {
                if (twisterView.clueButtons[i].getText().length() == 0) {
                    if (tmpAnswer.length() > index) {
                        twisterView.clueButtons[i].setText(tmpAnswer.charAt(index++) + "");
                    }
                }
            }
        }
    }

    // EventHandler attached to Submit button.
    class SubmitButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            //get the answer form answerButtons
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < twisterView.answerButtons.length; i++) {
                if (twisterView.answerButtons[i].getText().length() != 0) {
                    answer.append(twisterView.answerButtons[i].getText());
                }
            }

            //set smileyImageViews according to the result of this try
            int index = twister.nextTry(answer.toString());
            twisterView.smileyButton.setGraphic(twisterView.smileyImageViews[index]);

            //if the guess is correct or this round is successful
            //clear the answerButtons and twist the puzzleWord
            if (index == GameView.THUMBS_UP_INDEX || index == GameView.SMILEY_INDEX) {
                twister.makeAClue(twister.twisterRound.getPuzzleWord());
                for (int i = 0; i < twisterView.answerButtons.length; i++) {
                    twisterView.answerButtons[i].setText("");
                }

                //show the correct guess in solutionListView and renew the wordScoreLabel
                for (int i = 0; i < twisterView.wordLengthLabels.length; i++) {
                    if (answer.length() == Integer.parseInt(twisterView.wordLengthLabels[i].getText())) {
                        int curIndex = answer.length() - Twister.TWISTER_MIN_WORD_LENGTH;
                        Collections.sort(twister.twisterRound.getSubmittedListsByWordLength(curIndex));
                        twisterView.solutionListViews[i].setItems(twister.twisterRound.getSubmittedListsByWordLength(curIndex));
                        int size = twister.twisterRound.getSolutionListsByWordLength(curIndex).size();
                        twisterView.wordScoreLabels[i].setText(twister.twisterRound.getSubmittedListsByWordLength(curIndex).size() + "/" + size);
                        break;
                    }
                }

                twisterView.topMessageText.setText(twister.getScoreString());
            }

            //if the try is successful or timeout,
            //stop the timer
            if (index == GameView.SMILEY_INDEX || index == GameView.SADLY_INDEX) {
                System.out.println("=============到这儿啦！！！！");
                writeScore();
                GameView.wordTimer.timeline.stop();
                System.out.println("--------到这儿啦2");
                twister.twisterRound.setIsRoundComplete(true);
            }
        }
    }

    // EventHandler attached to New button. Starts a new round of game.
    class NewButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            twister.twisterRound = twister.setupRound();
            twisterView.refreshGameRoundView(twister.twisterRound);
            startController();
//            GameView.wordTimer.restart(Twister.TWISTER_GAME_TIME);
            setupBindings();
        }
    }


    // EventHandler attached to Twist button. Twists the word currently displayed in clueButtons
    class TwistButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            //get the answer from answerButtons
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < twisterView.answerButtons.length; i++) {
                ans.append(twisterView.answerButtons[i].getText());
            }

            StringBuilder clues = new StringBuilder();
            for (int i = 0; i < twisterView.clueButtons.length; i++) {
                if (twisterView.clueButtons[i].getText().length() != 0) {
                    clues.append(twisterView.clueButtons[i].getText());
                }
            }

            //make a new clue according to the existing clue in clueButtons
            String newClue = twister.makeAClue(clues.toString());

            //set the new clue in clueButtons
            for (int i = 0; i < newClue.length(); i++) {
                twisterView.clueButtons[i].setText(newClue.charAt(i) + "");
            }
            for (int i = newClue.length(); i < twisterView.clueButtons.length; i++) {
                twisterView.clueButtons[i].setText("");
            }
        }
    }


    // EventHandler attached to all clueButtons.
    // Takes the alphabet in clicked clueButton,
    // finds the next empty slot starting from left in answerButtons,
    // and puts the alphabet in it. Clears the clicked clueButton
    class ClueButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button b = (Button) event.getSource();
            String alphabet = b.getText();
            b.setText("");
            for (int i = 0; i < twisterView.clueButtons.length; i++) {
                if (twisterView.answerButtons[i].getText().length() == 0) {
                    twisterView.answerButtons[i].setText(alphabet);
                    break;
                }
            }
        }
    }


    // EventHandler attached to all answerButtons.
    // Takes the alphabet in clicked answerButton,
    // finds the next empty slot starting from left in clueButtons,
    // and puts the alphabet in it. Clears the clicked answerButton
    class AnswerButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button b = (Button) event.getSource();
            String tmp = b.getText();
            b.setText("");
            for (int i = 0; i < twisterView.answerButtons.length; i++) {
                if (twisterView.clueButtons[i].getText().length() == 0) {
                    twisterView.clueButtons[i].setText(tmp);
                    break;
                }
            }
        }
    }


    private void writeScore(){
        wordNerdModel = new WordNerdModel();

        float score = 0;
        int submittedLength = 0;
        int totalLength = 0;
        for(int i = 0; i <= Twister.TWISTER_MAX_WORD_LENGTH - Twister.TWISTER_MIN_WORD_LENGTH; i++){
            submittedLength += twister.twisterRound.getSubmittedListsByWordLength(i).size();
            totalLength += twister.twisterRound.getSolutionListsByWordLength(i).size();
        }

        score = (float) submittedLength / (float)totalLength;
        int timeStamp = (int)GameView.wordTimer.timeline.getCurrentTime().toSeconds();
        String scoreString = "1," + twister.twisterRound.getPuzzleWord() + "," + timeStamp + "," + score;
        wordNerdModel.writeScore(scoreString);
    }

}
