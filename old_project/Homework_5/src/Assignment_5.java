import java.util.Scanner;

public class Assignment_5 {

	
	public static void sorting_program() {
		
		char input;
		int signal = 0; //when to leave the loop
		Scanner readInput = new Scanner(System.in);
		
		System.out.print("Welcome to sorting program\n\n\t1. Title\n\t2. Rank\n\t3. Date\n\t4. Stars\n\t5. Likes\n\n");
		
		while (signal == 0) {
			System.out.print("Enter your choice between 1 and 5 only: ");
			input = readInput.next().charAt(0);//read the input
			
			switch (input) {
			case '1':;
			case '2':;
			case '3':;
			case '4':;
			case '5': 
				System.out.printf("\nYou entered valid choice %c\nThank you for giving your choice.", input); 
				signal = 1; //now get the correct input and time to leave the loop
				break;
			
			default:
				System.out.print("You have not entered a number between 1 and 5. Try again.\n");
			}
			
		}
		
	}
	
	
	public static void getTwoFloat() {
		
		float input1, input2;
		int signal = 0;
		
		
		System.out.print("Welcome to get two floats program.\n\n");
		
		while(signal == 0) {
			// scanner need to be re-defined so that the try-catch won't return any bug.
			Scanner readInput = new Scanner(System.in);		
			try {
				System.out.print("Enter two floats separated by a space: ");
				input1 = readInput.nextFloat();
				input2 = readInput.nextFloat();
				System.out.printf("You entered two valid floats: %5.2f and %5.2f\nThank you for giving two floats", input1, input2);
				break;
			}
			catch (Exception e) {
				System.out.println("You have entered an invalid input. Try again.");
			}
			
		}
		
		
	}
	
	public static void weeklyTemp() {
		//print the daily temperature
		int weeklyTemp[] = {69, 70, 71, 68, 66, 71, 70};
		int i, max = 0, min = weeklyTemp[0];
		float total = 0;
		
		for (i = 0; i<7; i++) {
			System.out.printf("The temperature on day %d was %d\n", (i+1),weeklyTemp[i]);
			
			if (weeklyTemp[i] > max) {
				max = weeklyTemp[i];
			}
			
			if (weeklyTemp[i] < min) {
				min = weeklyTemp[i]; //compare the min with every one
			}
			total += (float) weeklyTemp[i];
		}
		System.out.println();
		
		System.out.printf("The Minimum temperature is %d\n", min);
		System.out.printf("The Maximum temperature is %d\n", max);
		System.out.printf("The average temperature for the week is %5.2f\n", total/7.0);
		
		System.out.print("\nThank you for using my homework #5 solution!");
	}
	
	
	public static void main(String[] args) {
		
		sorting_program();
		getTwoFloat();
		weeklyTemp();
	}
	
}
