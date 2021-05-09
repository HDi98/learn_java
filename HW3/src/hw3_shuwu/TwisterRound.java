package hw3_shuwu;

/**
 * name: Shu Wu
 * andrew id: shuwu
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class TwisterRound extends GameRound{
    private ObservableList<String> solutionWordsList;

    private ObservableList<ObservableList<String>> submittedListsByWordLength;

    private ObservableList<ObservableList<String>> solutionListsByWordLength;


    //initialized three private variables in the constructor
    TwisterRound() {
        int min = Twister.TWISTER_MIN_WORD_LENGTH;
        int max = Twister.TWISTER_MAX_WORD_LENGTH;

        solutionWordsList = FXCollections.observableArrayList();
        submittedListsByWordLength = FXCollections.observableArrayList();
        solutionListsByWordLength = FXCollections.observableArrayList();

        for (int i = min; i <= max; i++) {
            submittedListsByWordLength.add(FXCollections.observableArrayList());
            solutionListsByWordLength.add(FXCollections.observableArrayList());
        }
    }

    public void setSolutionWordsList(List<String> solutionWordsList) {
        this.solutionWordsList = FXCollections.observableList(solutionWordsList);
    }

    public List<String> getSolutionWordsList() {
        return solutionWordsList;
    }

    public ObservableList<String> solutionWordsListProperty() {
        return solutionWordsList;
    }

    void setSubmittedListsByWordLength(String word) {
        submittedListsByWordLength.get(word.length() - Twister.TWISTER_MIN_WORD_LENGTH).add(word);
    }

    ObservableList<ObservableList<String>> getSubmittedListsByWordLength() {
        return submittedListsByWordLength;
    }

    ObservableList<String> getSubmittedListsByWordLength(int count) {
        return submittedListsByWordLength.get(count);
    }

    ObservableList<ObservableList<String>> submittedListsByWordLengthProperty() {
        return submittedListsByWordLengthProperty();
    }

    public void setSolutionListsByWordLength(String word) {
        solutionListsByWordLength.get(word.length() - Twister.TWISTER_MIN_WORD_LENGTH).add(word);
    }


    //letterCount is index
    public ObservableList<String> getSolutionListsByWordLength(int letterCount) {
        return solutionListsByWordLength.get(letterCount);
    }

    public ObservableList<ObservableList<String>> getSolutionListsByWordLength() {
        return this.solutionListsByWordLength;
    }

    public ObservableList<ObservableList<String>> solutionListsByWordLengthProperty() {
        return solutionListsByWordLength;
    }

}

