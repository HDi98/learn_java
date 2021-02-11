import java.util.Scanner;

public class Calculator_new {
	
	//divide the former file into several methods to make it more clear
		
	static int getUserChoice(Scanner readInput) {
		int signal = 0;
		char calChoice = '0';
		int out;
		
		System.out.print("\n\nWelcome to Haonan's Handy Calculator!\n\n\t1. Addition\n\t2. Subtraction\n\t3. Multiplication\n\t4. Division\n\t5. Exit\n\nWhat would you like to do? ");
		
		signal = 0; //this signal is tend for making a proper choice, when begin a new calculator loop, it need to be reset to 0
		
		//loop concerning different choice
		while (signal == 0) {
			calChoice = readInput.nextLine().charAt(0);
			
			//use switch to match to obtain the first guideline
			switch (calChoice) {
			
			case '1': 
				System.out.print("Please enter two floats to add, separated by a space: "); 
				signal = 1;
				break;
			case '2': 
				System.out.print("Please enter two floats to subtract, separated by a space: "); 
				signal = 1;
				break;
			case '3': 
				System.out.print("Please enter two floats to multiply, separated by a space: "); 
				signal = 1;
				break;
			case '4': 
				System.out.print("Please enter two floats to divide, separated by a space: "); 
				signal = 1;
				break;
			case '5': 
				//return ("You quit the program successfully!\n");
				//exit_signal = 1; //prepare to exit the big loop
				signal = 1;
				break;
			
			default: {
				System.out.print("You have entered an invalid character, please re-enter your choice between 1-5: \n");
				break;
				}
			}
		}
		
		out = calChoice - '0';
		return out;
	}
	
	//This method aims at getting the float array, the 'body' of our program
	static float[] getTwoFloats(int cal_choice) {
		//enter two value separated by a space, refer to hw5.2
		//Besides use nextFloat can also convert the input int number into float! So just nextFloat works well.
		int signal = 0;
		float input[] = new float[2];
		
		while (signal == 0) {
			
			Scanner readNum = new Scanner (System.in);
			//this 'scanner' need to be defined in the loop, otherwise it will not re-read the input from keyboard. 
			try {
				input[0] = readNum.nextFloat();
				input[1] = readNum.nextFloat();
				
				//test whether the second one is zero during user input session. 
				if (cal_choice == 4 && input[1] ==0) {
					System.out.print("You can't divide by zero, please re-enter both floats: ");
				//the exception case: input2 == 0, the division will be infinite.
				}
				else {
					signal = 1;
				}
				//break;
			}
			catch (Exception e) {
				System.out.println("You have entered an invalid float. Please re-enter: ");
			}	
		}
		
		return input;
		
	}
	
	
	//This method is to provide the outcome of the calculation.
	static void calProcess(int calChoice, float[] calFloat) {
		switch (calChoice) {
		case 1: 
			System.out.printf("Result of adding %5.2f and %5.2f is %5.2f\n\nPress enter key to continue ...", calFloat[0], calFloat[1], (calFloat[0] + calFloat[1])); 
			break;
		case 2: 
			System.out.printf("Result of subtracting %5.2f by %5.2f is %5.2f\n\nPress enter key to continue ...", calFloat[0], calFloat[1], (calFloat[0] - calFloat[1])); 
			break;
		case 3: 
			System.out.printf("Result of multiply %5.2f and %5.2f is %5.2f\n\nPress enter key to continue ...", calFloat[0], calFloat[1], (calFloat[0] * calFloat[1])); 
			break;
		case 4: 
			if (calFloat[1] == 0) {
				break;
			} //in fact this 'if' is not needed, if input2 = 0 it won't end the former loop. when entering this switch input2 won't be 0
			System.out.printf("Result of dividing %5.2f by %5.2f is %5.2f\n\nPress enter key to continue ...\n\n", calFloat[0], calFloat[1], (calFloat[0] / calFloat[1])); 
			break;
		case 5: 
			//return ("You quit the program successfully!\n");
			break;
		}
		
	}
	
	
	public static void main(String[] args) {
		
		float twoFloats[] = new float[2];
		int calChoice;
		Scanner readInput = new Scanner(System.in);
		
		
		while (true) { //the whole loop for the program, press enter key to continue
			
			calChoice = getUserChoice(readInput);
			if (calChoice == 5) {
				System.out.print("\nThank you for using Haonan's Handy Calculator");
				break;
			}
			twoFloats = getTwoFloats(calChoice);
			calProcess(calChoice, twoFloats);
			//readInput.nextLine();
			readInput.nextLine(); //press enter key to continue, to scanner the enter character. we have to have two nextLine() comment so that it will react properly!

		}
	}
}
