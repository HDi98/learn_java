import java.util.Scanner;

public class Homework4_loops {

	
	
	/*public static void exercise_1() {
		
		//4.1 (a) i++ loop
		int i = 0;
		while(i++<10) {
			System.out.println("Hello World: " + i);
		}
		
		//for case a), it will execute 10 times (i from 0 to 9), i++ will execute after 'while'
		
		i = 0;
		while (++i < 10) {
			System.out.println("Hello World: " + i);
		}
		//for case b), it will execute 9 times (i from 1 to 9), ++i will execute before 'while'
		
		while (++i < 10) {
			System.out.println("Hello World: " + i);
		}
		//for case c), it will not execute since i=10 before entering into this loop
		
	}
	
	public static void exercise_2() {
		
		//use while loop to generate the box
		
		int count_hor = 0;
		int count_verti = 0;
		int hor = 20;
		int verti = 10;
		int count_new = 0;
		
		while(count_hor++ < hor) {
			System.out.print('-');	
		}
		System.out.print('\n'); //a line feed to continue to the next line
		
		while (count_verti++ < (verti - 2)) { // the vertical length
			count_new = 0; //initialize 
			System.out.print('|');
			
			while (count_new++ < (hor - 2)) { //the space inside the line
				System.out.print(' ');
			}
			System.out.println('|');
			
		}
		// the bottom line
		count_hor = 0;
		while(count_hor++ < hor) {
			System.out.print('-');	
		}
		System.out.print('\n'); //a line feed to continue to the next line
	
	}
	
	public static void exercise_3() { 
		//use do...while loop to rewrite the program above
		//just change i++ to ++i
		int count_hor = 0;
		int count_verti = 0;
		int hor = 20;
		int verti = 10;
		int count_new = 0;
		
		do{
			System.out.print('-');	
		}
		while(++count_hor < hor) ;
		System.out.print('\n'); //a line feed to continue to the next line
		
		do{ // the vertical length
			count_new = 0; //initialize 
			System.out.print('|');
			
			do{ //the space inside the line
				System.out.print(' ');
			}
			while (++count_new < (hor - 2));
			System.out.println('|');
			
		}
		while (++count_verti < (verti - 2));
		// the bottom line
		count_hor = 0;
		do{
			System.out.print('-');	
		}
		while(++count_hor < hor); 
		System.out.print('\n'); //a line feed to continue to the next line
	
	}
	
	public static void exercise_4() {
		// change the former one to for loop

		
		int hor = 20;
		int verti = 10;
		int count_new;
		
		for(int count_hor = 0; count_hor < hor; count_hor++) {
			System.out.print('-');	
		}
		System.out.print('\n'); //a line feed to continue to the next line
		
		for(int count_verti = 0;count_verti < (verti - 2); count_verti++) { // the vertical length
			System.out.print('|');
			
			for(count_new = 0; count_new < (hor-2); count_new++) { //the space inside the line
				System.out.print(' ');
			}
			System.out.println('|');
			
		}
		// the bottom line
		for(int count_hor = 0; count_hor < hor; count_hor++) {
			System.out.print('-');	
		}
		System.out.print('\n'); //a line feed to continue to the next line
		
	}
	
	public static void exercise_5() {
		//use user-input values to replace height, width, and character
		
		char ver_char, hor_char;
		int hor,verti;
		int count_new;
		Scanner readInput = new Scanner(System.in);

		System.out.println("Please input the horizontal character: "); // former horizontal char is '-'
		hor_char = readInput.next().charAt(0);
		System.out.println("Please input the vertical character: "); // former vertical char is '|'
		ver_char = readInput.next().charAt(0);
		System.out.println("Please input the height and width, sperate by space: ");
		verti = readInput.nextInt();
		hor = readInput.nextInt();
				
		for(int count_hor = 0; count_hor < hor; count_hor++) {
			System.out.print(hor_char);	
		}
		System.out.print('\n'); //a line feed to continue to the next line
		
		for(int count_verti = 0;count_verti < (verti - 2); count_verti++) { // the vertical length
			System.out.print(ver_char);
			
			for(count_new = 0; count_new < (hor-2); count_new++) { //the space inside the line
				System.out.print(' ');
			}
			System.out.println(ver_char);
			
		}
		// the bottom line
		for(int count_hor = 0; count_hor < hor; count_hor++) {
			System.out.print(hor_char);	
		}
		System.out.print('\n'); //a line feed to continue to the next line
		
		
	}
	
	
	public static void exercise_6() {
		//ask user whether to continue
		char ver_char, hor_char,adjust;
		int hor,verti;
		int count_new;
		Scanner readInput = new Scanner(System.in);

		do{
			System.out.println("Please input the horizontal character: "); // former horizontal char is '-'
			hor_char = readInput.next().charAt(0);
			System.out.println("Please input the vertical character: "); // former vertical char is '|'
			ver_char = readInput.next().charAt(0);
			System.out.println("Please input the height and width, sperate by space: ");
			verti = readInput.nextInt();
			hor = readInput.nextInt();
					
			for(int count_hor = 0; count_hor < hor; count_hor++) {
				System.out.print(hor_char);	
			}
			System.out.print('\n'); //a line feed to continue to the next line
			
			for(int count_verti = 0;count_verti < (verti - 2); count_verti++) { // the vertical length
				System.out.print(ver_char);
				
				for(count_new = 0; count_new < (hor-2); count_new++) { //the space inside the line
					System.out.print(' ');
				}
				System.out.println(ver_char);
				
			}
			// the bottom line
			for(int count_hor = 0; count_hor < hor; count_hor++) {
				System.out.print(hor_char);	
			}
			System.out.print('\n'); //a line feed to continue to the next line
			
			System.out.print("Do you want to generate more box? Press y to continue: ");
			adjust = readInput.next().charAt(0);
		}
		while (adjust == 'y');	
		
	}
*/	
	public static void main(String[] args) {
		
		
		/*exercise_1();
		exercise_2();
		exercise_3();
		exercise_4();
		exercise_5();
		exercise_6();*/
		int yourAge = 34;
		if (yourAge >= 0)
		            if (yourAge == 0)
		                    System.out.println("You are not born yet");
		else
		           System.out.println("You are an adult");
		System.out.println("You are a kid");	
	
	
	
	}
	
}
