//Sorry to submit my homework late since last submission, I have a surgery in early May, until now I fell much better and is going to catch up.
//Sorry for late submission.

import java.util.Scanner;

public class userChoice {
	
	public static void main(String[] arg) {
		
		Scanner readInput = new Scanner(System.in); //define the scanner
		float twoFloat[] = new float[2]; //define the float array to receive the data from getTwoFloats method
		
		getUserChoice(readInput);
		getTwoFloats(twoFloat); 
		//the readInput must be put into the while loop in getTwoFloats method, if it is passed by this pre-define, the Scanner will not end in the try-catch, and a endless loop will appear
		
	}
	
	static int getUserChoice(Scanner readInput) {
		char input;
		int signal = 0; //when to leave the loop
		int out = -1; //use -1 to prevent any potential error
		//Scanner readInput = new Scanner(System.in);
		
		System.out.print("Welcome to sorting program\n\n\t1. Title\n\t2. Rank\n\t3. Date\n\t4. Stars\n\t5. Likes\n\n");
		
		while (signal == 0) {
			System.out.print("Enter your choice between 1 and 5 only: ");
			input = readInput.nextLine().charAt(0);//read the input
			
			switch (input) {
			case '1':;
			case '2':;
			case '3':;
			case '4':;
			case '5': 
				System.out.printf("\nYou entered valid choice %c\nThank you for giving your choice.\n\n", input); 
				signal = 1; //now get the correct input and time to leave the loop
				out = input - '0'; //'input' is char, and 'out' is int, I come up this method to transform char to an int.
				break;
			
			default:
				System.out.print("You have not entered a number between 1 and 5. Try again.\n");
			}
			
		}
		return out;
	}
	
	
	static float[] getTwoFloats(float[] input) {
		//float input[] = new float [2];
		int signal = 0;
		
		
		System.out.print("Welcome to get two floats program.\n\n");
		
		while(signal == 0) {
			// scanner need to be re-defined so that the try-catch won't return any bug.
			Scanner readInput = new Scanner(System.in);		
			try {
				System.out.print("Enter two floats separated by a space: ");
				input[0] = readInput.nextFloat();
				input[1] = readInput.nextFloat();
				System.out.printf("You entered %5.2f and %5.2f successfully!\n", input[0], input[1]);
				System.out.printf("\n\tPress enter key to continue ..."); //here the press key to continue is nonsense, I will show this function in my midterm project.
				break;
				//return input;
			}
			catch (Exception e) {
				System.out.println("You have entered an invalid input. Try again.");
			}
			
		}
		return input;
	}
	
	
	

}
