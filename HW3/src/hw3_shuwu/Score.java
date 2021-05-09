package hw3_shuwu;

import javafx.beans.property.*;

import java.text.DecimalFormat;

public class Score {
    private IntegerProperty gameId;
    private StringProperty puzzleWord;
    private IntegerProperty timeStamp;
    private FloatProperty score;
    private String scoreString;

    public String getGameName() {
        return gameName.get();
    }

    public StringProperty gameNameProperty() {
        return gameName;
    }

    private StringProperty gameName;

    public String getScoreString() {
        return scoreString;
    }

    public static String roundByScale(float v, int scale) {

        String formatStr = "0.";
        for(int i=0;i<scale;i++){
            formatStr = formatStr + "0";
        }
        return new DecimalFormat(formatStr).format(v);
    }

    Score(int gameId, String puzzleWord, int timeStamp, float score) {
        this.gameId = new SimpleIntegerProperty();
        this.puzzleWord = new SimpleStringProperty();
        this.timeStamp = new SimpleIntegerProperty();
        this.score = new SimpleFloatProperty();
        this.gameName = new SimpleStringProperty();

        this.gameId.setValue(gameId);
        this.puzzleWord.setValue(puzzleWord);
        this.timeStamp.setValue(timeStamp);
        this.score.setValue(score);

        this.scoreString = roundByScale(score, 2);

        if (gameId == 0) {
            this.gameName.setValue("Hangman");
        } else if (gameId == 1) {
            this.gameName.setValue("Twister");
        }



    }

    public int getGameId() {
        return gameId.getValue();
    }

    public void setGameId(int gameId) {
        this.gameId.setValue(gameId);
    }

    public String getPuzzleWord() {
        return puzzleWord.getValue();
    }

    public void setPuzzleWord(String puzzleWord) {
        this.puzzleWord.setValue(puzzleWord);
    }

    public int getTimeStamp() {
        return timeStamp.getValue();
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp.setValue(timeStamp);
    }

    public float getScore() {
        return score.getValue();
    }

    public void setScore(float score) {
        this.score.setValue(score);
    }
}
