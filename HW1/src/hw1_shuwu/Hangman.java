package hw1_shuwu;

import java.util.Scanner;

public class Hangman extends Game {

    static final int MIN_WORD_LENGTH = 5;
    static final int MAX_WORD_LENGTH = 10;
    static final int HANGMAN_TRIALS = 10;

    //codes returned by nextTry() method for printing appropriate message
    static final int RIGHT_MESSAGE_INDEX = 0;
    static final int WRONG_MESSAGE_INDEX = 1;
    static final int ALREADY_ENTERED_MESSAGE_INDEX = 2;
    static final int PART_OF_CLUE_MESSAGE_INDEX = 3;
    static final int CONGRATULATIONS_MESSAGE_INDEX = 4;
    static final int LOST_MESSAGE_INDEX = 5;


    String[] messagesArray = {":-) You got that right!", //RIGHT_MESSAGE_INDEX
            ":-( Sorry! Got it wrong!",        //WRONG_MESSAGE_INDEX
            ":-o You already entered that!",    //ALREADY_ENTERED_MESSAGE_INDEX
            ":-\\ Part of the clue!",            //PART_OF_CLUE_MESSAGE_INDEX
            ":-D Congratulations! You won!",    //CONGRATULATIONS_MESSAGE_INDEX
            ":-( Sorry! You lost this one!"};    //LOST_MESSAGE_INDEX


    HangmanRound hangmanRound;

    //create and initialize a new round, play, and then print final result
    @Override
    void startGame() {
        Hangman hangman = new Hangman();
        hangman.playRound();
    }

    //return a new puzzle word randomly selected from the Game's wordsFromFile.
    @Override
    String findPuzzleWord() {
        int size = Game.wordsFromFile.length;
        int num = -1;
        String[] words = Game.wordsFromFile;
        //generate a random number to get the puzzle in this trial
        while (num == -1 || words[num].length() < MIN_WORD_LENGTH || words[num].length() > MAX_WORD_LENGTH) {
            num = (int) (Math.random() * size);
        }
        return Game.wordsFromFile[num];
    }

    //Runs a complete round, invoking nextTry() for each guess to be made by the player
    //Keeps track all inputs made by the player
    //A round goes as long as player has not guessed the complete puzzleWord
    //and the number of guesses is less than HANGMAN_TRIALS
    //Prints the message from  messagesArray[] based on the code returned by nextTry()
    @Override
    void playRound() {
        Scanner input = new Scanner(System.in);
        StringBuilder userInputs = new StringBuilder();
        hangmanRound = new HangmanRound();
        hangmanRound.setPuzzleWord(findPuzzleWord());
        hangmanRound.setClueWord(makeAClue(hangmanRound.getPuzzleWord()));
        for (int i = 1; i < HANGMAN_TRIALS + 1; i++) {
            if (hangmanRound.getIsRoundComplete()) break;

            //request user input
            System.out.println("The clue is: " + hangmanRound.getClueWord());
            System.out.print("***Trial# " + i + ". Enter your guess: ");
            char guess = input.next().charAt(0);

            //return the message code in this trial and print out message
            int messageCode = nextTry(guess, userInputs);
            if (messageCode == 0) {
                System.out.println(messagesArray[Hangman.RIGHT_MESSAGE_INDEX]);
            } else if (messageCode == 1) {
                System.out.println(messagesArray[Hangman.WRONG_MESSAGE_INDEX]);
            } else if (messageCode == 2) {
                System.out.println(messagesArray[Hangman.ALREADY_ENTERED_MESSAGE_INDEX]);
                i--;
            } else if (messageCode == 3) {
                System.out.println(messagesArray[Hangman.PART_OF_CLUE_MESSAGE_INDEX]);
                i--;
            }

            hangmanRound.setIsRoundComplete(hangmanRound.getClueWord().equals(hangmanRound.getPuzzleWord()));
        }

        //This round is over
        if (hangmanRound.getIsRoundComplete()) {
            System.out.println("The word is: " + hangmanRound.getPuzzleWord());
            System.out.println(messagesArray[Hangman.CONGRATULATIONS_MESSAGE_INDEX]);
            System.out.printf("Your score is %.2f\n", getScore());
            System.out.println("Bye Bye!");
        } else {
            System.out.println("The word is: " + hangmanRound.getPuzzleWord());
            System.out.println(messagesArray[Hangman.LOST_MESSAGE_INDEX]);
            System.out.printf("Your score is %.2f\n", getScore());
            System.out.println("Bye Bye!");
        }

    }

    //Takes next guess and prior userInputs.
    //updates hitCount, missCount, and clueWord in hangmanRound
    //updates userInputs
    //returns code for message to be printed
    int nextTry(char guess, StringBuilder userInputs) {
        int messageCode = -1;
        boolean isRight = false;

        if (hangmanRound.getClueWord().contains(guess + "")) {
            //the guess is part of the clue
            return 3;
        } else if (userInputs.toString().contains(guess + "")) {
            //user has already input the guess
            return 2;
        } else {
            //check if the guess is correct
            //if the guess is correct, update the clue word
            StringBuilder newClueWord = new StringBuilder();
            for (int i = 0; i < hangmanRound.getPuzzleWord().length(); i++) {
                if (hangmanRound.getClueWord().charAt(i) == '-' && hangmanRound.getPuzzleWord().charAt(i) == guess) {
                    newClueWord.append(guess);
                    isRight = true;
                } else {
                    newClueWord.append(hangmanRound.getClueWord().charAt(i));
                }
            }
            //update missCount and hitCount in this round
            if (isRight) {
                hangmanRound.setClueWord(newClueWord.toString());
                hangmanRound.setHitCOunt(hangmanRound.getHitCount() + 1);
                messageCode = 0;
            } else {
                hangmanRound.setMissCount(hangmanRound.getMissCount() + 1);
                messageCode = 1;
            }
        }

        //update userInputs
        userInputs.append(guess);
        return messageCode;
    }

    //Returns a clue that has at least half the number of letters
    //in puzzleWord replaced with dashes.
    //The replacement should stop as soon as number of dashes equals or exceeds 50% of total word length.
    //Note that repeating letters will need to be replaced together.
    //For example, in 'apple', if replacing p, then both 'p's need to be replaced to make it a--le
    @Override
    String makeAClue(String puzzleWord) {
        String clueWord = puzzleWord;
        int count = 0;
        int size = puzzleWord.length();

        //if the length of puzzleWord is even
        if (size % 2 == 0) {
            while (count < puzzleWord.length() / 2) {
                //generate a random index
                int index = (int) (Math.random() * puzzleWord.length());
                char curReplace = clueWord.charAt(index);
                if (curReplace == '-') continue;
                //count the number of charcaters that should be replaced
                for (char c : clueWord.toCharArray()) {
                    if (count >= puzzleWord.length() / 2) break;
                    if (c == curReplace) {
                        count++;
                    }
                }
                clueWord = clueWord.replace(clueWord.charAt(index), '-');
            }
        } else {
            //if the length of puzzleWord is odd
            while (count <= puzzleWord.length() / 2) {
                int index = (int) (Math.random() * puzzleWord.length());
                char curReplace = clueWord.charAt(index);
                if (curReplace == '-') continue;
                for (char c : clueWord.toCharArray()) {
                    //For odd numbers, the number of dashes should more than half because the result is always rounded down
                    if (count > puzzleWord.length() / 2) break;
                    if (c == curReplace) {
                        count++;
                    }
                }
                clueWord = clueWord.replace(clueWord.charAt(index), '-');
            }
        }

        hangmanRound.setClueWord(clueWord);
        return clueWord;
    }

    //returns the number of dashes in a clue string
    int countDashes(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '-') count++;
        }
        return count;
    }

    //returns score based on Hangman rules
    @Override
    float getScore() {
        //if player made no misses, then the score equals the number of hits
        if (hangmanRound.getMissCount() == 0) return hangmanRound.getHitCount();
        else return (float) hangmanRound.getHitCount() / (float) hangmanRound.getMissCount();
    }
}
