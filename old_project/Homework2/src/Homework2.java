import java.util.Scanner;

public class Homework2 {

	public static void exercise_1() {
		int radius = 2;
		double area;
		final double pi = 3.142;
		
		area = pi * radius * radius;
		
		System.out.printf("The area is %5.2f", area);
		
	}
	
	
	public static void exercise_2() {
		
		int radius;
		double area;
		double pi;
		
		Scanner readInput = new Scanner(System.in);
		
		System.out.print("\nEnter the radius: ");
		radius = readInput.nextInt();
		System.out.print("\nEnter the PI: ");
		pi = readInput.nextDouble();
		System.out.print("\n");
		
		area = pi * radius * radius;
		
		System.out.printf("The area is %5.2f", area);
	}
	
	
	public static void exercise_3() {
		/*double area;
		final double pi = 3.142;
		
		Scanner readInput = new Scanner(System.in);
		
		System.out.printf("\nEnter the radius: ");
		byte newRadius = readInput.nextByte();
		
		area = pi * newRadius * newRadius;
		
		System.out.print("The area is: "); System.out.println(area);
		//extra line feed
		System.out.println();
		System.out.print("What is your first name?: "); 
		char yourInitial = readInput.next().charAt(0);
		System.out.println("Hello Mr. " + yourInitial + ".");
		//the input buffer will still have enter character '\n' // so that needs to be cleaned.
		//You can do that by using .nextLine() method. 
		readInput.nextLine();*/
		
		Scanner readInput = new Scanner(System.in);
		
		System.out.println("Input your fisrt name: ");
		String FirstName = readInput.next();
		readInput.nextLine();
		
		System.out.println("Input your last name: ");
		String LastName = readInput.next();
		readInput.nextLine();
		
		System.out.println("Input your city: ");
		String City = readInput.next();
		readInput.nextLine();
		
		System.out.println("Input your Zip: ");
		String Zip = readInput.next();
		readInput.nextLine();
		
		
		System.out.printf("5185 is fun course.\n\n");
		System.out.printf("First Name  \tLast Name\tCity\tZip\n"); 
		System.out.printf("----------- \t---------\t---\t--\n");		
		System.out.print(FirstName + "\t" + LastName + "\t" + City + "\t" + Zip + "\n");
		System.out.printf("\n");
		
		//extra line feed
		System.out.println();
		System.out.printf("How do you print double quotes?\n"); 
		System.out.printf("Who said\"Test Scores Can Be Used ....\"\n");
		
	}
	
	
	public static void main(String[] args) {
		exercise_1();
		exercise_2();
		exercise_3();
	}
	
}
