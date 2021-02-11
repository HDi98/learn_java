import java.util.Scanner;

public class homework3 {
	
	public static void exercise_1() {
		
		int yourAge;
		
		Scanner readInput = new Scanner(System.in);
		System.out.printf("How old are you?: ");
		yourAge = readInput.nextInt();
		
		if (yourAge < 13) {
			System.out.printf("You are a kid");	
		}
		else if (yourAge <= 19) {
			System.out.printf("You are a teenager");
		}
		else {
			System.out.printf("You are an adult");
		}
		
	}
	
	public static void exercise_2() {
		float firstN; 
		float secondN; 
		char operator;
		char endSignal;
		Scanner readInput = new Scanner(System.in);
		while (true) {
			
			System.out.printf("Type a number, operator, number --" + "separated by a space: ");
			firstN = readInput.nextFloat();
			operator = readInput.next().charAt(0); 
			secondN = readInput.nextFloat();
		
			if (operator == '+') {
				System.out.printf("%5.2f + %5.2f = %5.2f",firstN, secondN, firstN + secondN);
			}
			else if (operator == '-') {
				System.out.printf("%5.2f - %5.2f = %5.2f",firstN, secondN, firstN - secondN);
			}
			else if (operator == '*') {
				System.out.printf("%5.2f * %5.2f = %5.2f",firstN, secondN, firstN * secondN);
			}
			else if (operator == '/') {
				System.out.printf("%5.2f / %5.2f = %5.2f",firstN, secondN, firstN / secondN);
			}
			else if (operator == '%') {
				System.out.printf("%5.2f %% %5.2f = %5.2f",firstN, secondN, firstN % secondN);
			}
			else {
				System.out.printf("Unknown operator");
			}
			System.out.printf("\n\n");
			
			System.out.printf("Do you want to continue with the calculator?\nPress \"y\" to continue: ");
			endSignal = readInput.next().charAt(0);
			if (endSignal != 'y') {
				break;
			}
		}
	}
	
	public static void exercise_3() {
		int a,b,c, max;
		char signal;
		Scanner readInput = new Scanner(System.in);
	
		while (true) {
			System.out.printf("Please enter number a, b and c, seperate by a space: ");
			a = readInput.nextInt();
			b = readInput.nextInt();
			c = readInput.nextInt();
			
			max = (a>b)?a:b;
			max = (max>c)?max:c;
			
			System.out.printf("The biggest one is %d\nPress y to re-run this program: ", max);
			
			signal = readInput.next().charAt(0);
			if (signal != 'y') {
				break;
			}
			
		}
	}
	public static void main(String[] args) {
		//exercise_1();
		//exercise_2();
		exercise_3();
	}
}
