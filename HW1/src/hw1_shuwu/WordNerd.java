package hw1_shuwu;

import java.util.Scanner;

public class WordNerd {

    //use these two constants for game choices and avoid using literals
    public final static int HANGMAN_GAME_ID = 1;
    public final static int TWISTER_GAME_ID = 2;

    static Scanner input = new Scanner(System.in);  //to be used across all classes for keyboard inputs

    //invokes getGameChoice and playWordNerd methods as needed
    // to display the menu and get the game started
    public static void main(String[] args) {
        WordNerd wordNerd = new WordNerd();
        int gameId = wordNerd.getGameChoice();
        wordNerd.playWordNerd(gameId);
    }

    //Displays the menu and returns the player's choice as
    //1 (for Hangman), 2 (for Twister), and 3 (for Exit).
    private int getGameChoice() {
        System.out.println("Welcome to WordNerd!");
        System.out.println("----------------------");
        System.out.println("1. Hangman\n2. Twister\n3. Exit");
        System.out.println("----------------------");
        return input.nextInt();
    }

    //given a gameID, starts the game.
    //For HW1, if gameId is 2, prints the message **** Coming Soon ****
    private void playWordNerd(int gameId) {
        if (gameId == 1) {
            Game game = new Hangman();
            game.startGame();
        } else if (gameId == 2) {
            System.out.println("**** COMING SOON ****");
            System.out.println("Bye Bye!");
        } else if (gameId == 3) {
            System.out.println("Bye Bye!");
        }
    }
}
