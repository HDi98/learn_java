package hw3_shuwu;
/**
 * name: Shu Wu
 * andrew id: shuwu
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Twister extends Game {

    static final int SOLUTION_LIST_COUNT = 5;
    static final int TWISTER_MAX_WORD_LENGTH = 7;
    static final int TWISTER_MIN_WORD_LENGTH = 3;
    static final int NEW_WORD_BUTTON_INDEX = 0;
    static final int TWIST_BUTTON_INDEX = 1;
    static final int CLEAR_BUTTON_INDEX = 2;
    static final int SUBMIT_BUTTON_INDEX = 3;
    static final int CLUE_BUTTON_SIZE = 75;
    static final int TWISTER_GAME_TIME = 120;
    static final int MIN_SOLUTION_WORDCOUNT = 10;
    TwisterRound twisterRound;
    WordNerdModel wordNerdModel;

    /**
     * SetupRound returns a new TwisterRound instance with puzzleWord initialized randomly drawn from wordsFromFile.
     * The puzzleWord must be a word between TWISTER_MIN_WORD_LENGTH and TWISTER_MAX_WORD_LENGTH.
     * The number of solutions must be above MIN_SOLUTION_WORDCOUNT.
     * Other properties of twisterRound are also initialized here.
     */
    TwisterRound setupRound() {
        while (true) {
            int size = WordNerdModel.wordsFromFile.length;
            int num = -1;
            String[] words = WordNerdModel.wordsFromFile;

            //generate a random number to get the puzzle in this trial
            while (num == -1 || words[num].length() < TWISTER_MIN_WORD_LENGTH || words[num].length() > TWISTER_MAX_WORD_LENGTH) {
                num = (int) (Math.random() * size);
            }

            twisterRound = new TwisterRound();
            twisterRound.setPuzzleWord(words[num]);
            makeAClue(twisterRound.getPuzzleWord());

            String puzzle = twisterRound.getPuzzleWord();
            List<String> solustions = new ArrayList<>();

            //initialized SolutionWordsList
            for (String tmpWord : WordNerdModel.wordsFromFile) {
                if (tmpWord.length() < TWISTER_MIN_WORD_LENGTH || tmpWord.length() > TWISTER_MAX_WORD_LENGTH) continue;
                int[] helper = new int[26];
                for (int i = 0; i < puzzle.length(); i++) {
                    helper[puzzle.charAt(i) - 'a']++;
                }

                boolean isSolution = true;
                for (char c : tmpWord.toCharArray()) {
                    if (helper[c - 'a'] <= 0) {
                        isSolution = false;
                    }
                    helper[c - 'a']--;
                }

                if (isSolution) {
                    solustions.add(tmpWord);
                    twisterRound.setSolutionListsByWordLength(tmpWord);
                }
            }

            if (solustions.size() >= MIN_SOLUTION_WORDCOUNT) {
                twisterRound.setSolutionWordsList(solustions);
                return twisterRound;
            }
        }
    }


    //twist the puzzleWord and make a new clue
    String makeAClue(String puzzleWord) {
        int[] index = new int[puzzleWord.length()];
        Random random = new Random();
        StringBuilder newWord = new StringBuilder();
        for (int i = 0; i < puzzleWord.length(); i++) {
            int tmp = random.nextInt(puzzleWord.length());
            while (index[tmp] != 0) {
                tmp = random.nextInt(puzzleWord.length());
            }
            newWord.append(puzzleWord.charAt(tmp));
            index[tmp] = 1;
        }
        twisterRound.setClueWord(newWord.toString());
        return newWord.toString();
    }

    //show the number of total solutions and submitted solutions
    String getScoreString() {
        int totalCount = twisterRound.getSolutionWordsList().size();
        int submittedCount = 0;

        for (int count = TWISTER_MIN_WORD_LENGTH; count <= TWISTER_MAX_WORD_LENGTH; count++) {
            submittedCount += twisterRound.getSubmittedListsByWordLength(count - TWISTER_MIN_WORD_LENGTH).size();

        }
        return "Twist to find " + (totalCount - submittedCount) + " of " + totalCount + " words";
    }

    int nextTry(String guess) {
        //if the length of the guess word is below TWISTER_MIN_WORD_LENGTH
        //then it is wrong
        if (guess.length() < TWISTER_MIN_WORD_LENGTH) {
            return GameView.THUMBS_DOWN_INDEX;
        }

        //check if the answer is correct or if the user made a repeated guess
        if (twisterRound.getSolutionWordsList().contains(guess)) {
            if (twisterRound.getSubmittedListsByWordLength(guess.length() - TWISTER_MIN_WORD_LENGTH).contains(guess)) {
                return GameView.REPEAT_INDEX;
            } else {
                twisterRound.setSubmittedListsByWordLength(guess);

                //check if the game is over by comparing the length of submitted words
                //and the length of solution words
                int count = 0;
                for (int i = 0; i < twisterRound.getSubmittedListsByWordLength().size(); i++) {
                    count += twisterRound.getSubmittedListsByWordLength().get(i).size();
                }

                if (count == twisterRound.getSolutionWordsList().size()) {
                    twisterRound.setIsRoundComplete(true);
                    writeScore();
                    return GameView.SMILEY_INDEX;
                }
                return GameView.THUMBS_UP_INDEX;
            }
        } else {
            return GameView.THUMBS_DOWN_INDEX;
        }
    }


    private void writeScore(){
        wordNerdModel = new WordNerdModel();

        float score = 0;
        int submittedLength = 0;
        int totalLength = 0;
        for(int i = 0; i <= TWISTER_MAX_WORD_LENGTH - TWISTER_MIN_WORD_LENGTH; i++){
            submittedLength += twisterRound.getSubmittedListsByWordLength(i).size();
            totalLength += twisterRound.getSolutionListsByWordLength(i).size();
        }

        score = (float) submittedLength / totalLength;

        int timeStamp = (int)GameView.wordTimer.timeline.getCurrentTime().toSeconds();
        String scoreString = "1," + twisterRound.getPuzzleWord() + "," + timeStamp + "," + score;
        wordNerdModel.writeScore(scoreString);
    }


}
