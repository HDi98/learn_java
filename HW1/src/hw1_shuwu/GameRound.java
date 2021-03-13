package hw1_shuwu;

public class GameRound {

    private String puzzleWord;
    private String clueWord;
    private boolean isRoundComplete;

    //write all getter and setter methods
    public String getPuzzleWord() {
        return puzzleWord;
    }

    public void setPuzzleWord(String puzzleWord) {
        this.puzzleWord = puzzleWord;
    }

    public String getClueWord() {
        return clueWord;
    }

    public void setClueWord(String clueWord) {
        this.clueWord = clueWord;
    }

    public boolean getIsRoundComplete() {
        return isRoundComplete;
    }

    public void setIsRoundComplete(boolean roundComplete) {
        this.isRoundComplete = roundComplete;
    }
}
