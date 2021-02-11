import java.util.Scanner;


class OOPCalculator{
	
	private char calChoice;
	private float twoFloats[] = new float [2];
	Scanner readInput = new Scanner(System.in);
	
	//generate accessor and mutator method
	public char getCalChoice() {
		return calChoice;
	}

	public void setCalChoice(char calChoice) {
		this.calChoice = calChoice;
	}

	public float[] getTwoFloats() {
		return twoFloats;
	}

	public void setTwoFloats(float[] twoFloats) {
		this.twoFloats = twoFloats;
	}

	
	//from now on this should be the method, or say, the behavior of OOPCalculator object
	int askCalcChoice() {
		//this method aims at read the input choice from the user
		//compared to midterm project, I revised several parts
		//1. allow to use A/a to represent 'add', so is the other four
		//2. change the attribute of calChoice from int to char, because char is more stable and reliable than int
		//so I also slightly modify your test program because askCalcChoice returns char
		int signal = 0;
		
		
		System.out.print("\n\nWelcome to Haonan's Handy Calculator!\n\n\t1. Addition\n\t2. Subtraction\n\t3. Multiplication\n\t4. Division\n\t5. Exit\n\nWhat would you like to do? ");
		
		signal = 0; //this signal is tend for making a proper choice, when begin a new calculator loop, it need to be reset to 0
		
		//loop concerning different choice
		while (signal == 0) {
			calChoice = readInput.nextLine().charAt(0);
			
			//use switch to match to obtain the first guideline
			switch (calChoice) {
			
				case '1':;
				case 'A':;
				case 'a':
					calChoice = '1';
					System.out.print("Please enter two floats to add, separated by a space: "); 
					signal = 1;
					break;
				case '2':;
				case 'S':;
				case 's':
					calChoice = '2';
					System.out.print("Please enter two floats to subtract, separated by a space: "); 
					signal = 1;
					break;
				case '3': ;
				case 'M':;
				case 'm':
					calChoice = '3';
					System.out.print("Please enter two floats to multiply, separated by a space: "); 
					signal = 1;
					break;
				case '4':;
				case 'D':;
				case 'd':
					calChoice = '4';
					System.out.print("Please enter two floats to divide, separated by a space: "); 
					signal = 1;
					break;
				case '5':;
				case 'X':;
				case 'x':
					calChoice = '5';
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
		
		return calChoice;
	}
	
	//This method aims at getting the float array, the 'body' of our program
	void askTwoValues() {
		//enter two value separated by a space, refer to hw5.2
		//Besides use nextFloat can also convert the input int number into float! So just nextFloat works well.
		int signal = 0;
		
		while (signal == 0) {
			
			Scanner readNum = new Scanner (System.in);
			//this 'scanner' need to be defined in the loop, otherwise it will not re-read the input from keyboard. 
			try {
				twoFloats[0] = readNum.nextFloat();
				twoFloats[1] = readNum.nextFloat();
				
				//test whether the second one is zero during user input session. 
				if (calChoice == '4' && twoFloats[1] ==0) {
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
		
	}
	
	
	//This method is to provide the outcome of the calculation.
	void displayResults() {
		
		switch (calChoice) {
		case '1':
		
			System.out.printf("Result of adding %5.2f and %5.2f is %5.2f\n\nPress enter key to continue ...", twoFloats[0], twoFloats[1], (twoFloats[0] + twoFloats[1])); 
			break;
		
		case '2':
			System.out.printf("Result of subtracting %5.2f by %5.2f is %5.2f\n\nPress enter key to continue ...", twoFloats[0], twoFloats[1], (twoFloats[0] - twoFloats[1])); 
			break;
		
		case '3': 
			System.out.printf("Result of multiply %5.2f and %5.2f is %5.2f\n\nPress enter key to continue ...", twoFloats[0], twoFloats[1], (twoFloats[0] * twoFloats[1])); 
			break;
		
		case '4':
			if (twoFloats[1] == 0) {
				break;
			} //in fact this 'if' is not needed, if input2 = 0 it won't end the former loop. when entering this switch input2 won't be 0
			System.out.printf("Result of dividing %5.2f by %5.2f is %5.2f\n\nPress enter key to continue ...\n\n", twoFloats[0], twoFloats[1], (twoFloats[0] / twoFloats[1])); 
			break;
		
		case '5':
			//return ("You quit the program successfully!\n");
			break;
		}
		readInput.nextLine();
	}
	
	void displayBye(){
		//in fact I am quite confused about this method. I know at first you assume this method to also display the functionality of 'press enter to continue'
		//However your loop can well achieve the goal, and this function could not achieve such functionality.
		System.out.println("\nThank you for using Haonan's Handy OOPCalculator");
		//readInput.nextLine();
	}
	
}



public class TestCalculator {
	
	public static void main(String[] args) {
		OOPCalculator calc = new OOPCalculator();
		while (calc. askCalcChoice () != '5'){ //it will set choice
			calc. askTwoValues (); //it will set two values 
			calc.displayResults(); //do calc, display result
		//and wait on press enter key
		}
		calc.displayBye(); //thanks the user for using and waits for press enter key } //end main
	}
}
