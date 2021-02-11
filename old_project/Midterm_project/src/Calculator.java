import java.util.Scanner;

public class Calculator {

	public static void calculator() {
		
		char cal_choice = '0'; //initialize in case some 'no definition' error happens
		float input1 = 0, input2 = 0;
		int sig_choice = 0, enter_value = 0, exit_signal = 0;
		
		while (exit_signal == 0) { //when enter 5 quit the program
			Scanner readInput = new Scanner(System.in);
			System.out.print("Welcome to Haonan's Handy Calculator!\n\n\t1. Addition\n\t2. Subtraction\n\t3. Multiplication\n\t4. Division\n\t5. Exit\n\nWhat would you like to do? ");
			
			sig_choice = 0; //this signal is tend for making a proper choice, when begin a new calculator loop, it need to be reset to 0
			
			//first loop concerning different choice
			while (sig_choice == 0) {
				cal_choice = readInput.nextLine().charAt(0);
				
				//use switch to match to obtain the first guideline
				switch (cal_choice) {
				
				case '1': 
					System.out.print("Please enter two floats to add, separated by a space: "); 
					sig_choice = 1;
					break;
				case '2': 
					System.out.print("Please enter two floats to subtract, separated by a space: "); 
					sig_choice = 1;
					break;
				case '3': 
					System.out.print("Please enter two floats to multiply, separated by a space: "); 
					sig_choice = 1;
					break;
				case '4': 
					System.out.print("Please enter two floats to divide, separated by a space: "); 
					sig_choice = 1;
					break;
				case '5': 
					//return ("You quit the program successfully!\n");
					exit_signal = 1; //prepare to exit the big loop
					sig_choice = 1;
					break;
				
				default: {
					System.out.print("You have entered an invalid character, please re-enter your choice between 1-5: \n");
					break;
				}
			}
			}
			
			if (exit_signal == 1) {
				System.out.println("\nThank you for using Haonan's Handy Calculater");
				break; //exit the big loop, in fact we don't need to write the condition for this big while loop, here we just break out.
			}
			
			//enter two value separated by a space, refer to hw5.2
			//Besides use nextFloat can also convert the input int number into float! So just nextFloat works well.
			while (enter_value == 0) {
				
				Scanner readNum = new Scanner (System.in);
				//this 'scanner' need to be defined in the loop, otherwise it will not re-read the input from keyboard. 
				try {
					input1 = readNum.nextFloat();
					input2 = readNum.nextFloat();
					
					if (cal_choice == '4' && input2 ==0) {
						System.out.print("You can't divide by zero, please re-enter both floats: ");
					//the exception case: input2 == 0, the division will be infinite.
					}
					else {
						enter_value = 1;
					}
					//break;
				}
				catch (Exception e) {
					System.out.println("You have entered an invalid float. Please re-enter: ");
				}	
			}
			
			//after get the input, then use cal_choice to calculate everything
			switch (cal_choice) {
				case '1': 
					System.out.printf("Result of adding %5.2f and %5.2f is %5.2f\n\nPress enter key to continue ...", input1, input2, (input1 + input2)); 
					break;
				case '2': 
					System.out.printf("Result of subtracting %5.2f by %5.2f is %5.2f\n\nPress enter key to continue ...", input1, input2, (input1 - input2)); 
					break;
				case '3': 
					System.out.printf("Result of multiply %5.2f and %5.2f is %5.2f\n\nPress enter key to continue ...", input1, input2, (input1 * input2)); 
					
					break;
				case '4': 
					if (input2 == 0) {
						break;
					} //in fact this 'if' is not needed, if input2 = 0 it won't end the former loop. when entering this switch input2 won't be 0
					System.out.printf("Result of dividing %5.2f by %5.2f is %5.2f\n\nPress enter key to continue ...\n\n", input1, input2, (input1 / input2)); 
					break;
				case '5': 
					//return ("You quit the program successfully!\n");
					break;
			}
			//readInput.nextLine();
			readInput.nextLine(); //press enter key to continue, to scanner the enter character. we have to have two nextLine() comment so that it will react properly!
			//System.out.println("\nPress enter key to continue ...");
			
			
		}
		

	}	
	
	
	public static void main(String[] args) {
		
		calculator();
		
	}
}
