import java.util.Scanner;
/**
 * @author Haonan Di
 *
 */
public class drawBox {

	/**
	 * @param args
	 */
	
	static void drawHorizontalLine(int width, char hChar1) {
		
		for (int x = 0; x < width; x ++) {
			
			System.out.print(hChar1);
		}
		System.out.print("\n");
	}
	
	static void drawVerticalLine(int height, int width, char vChar1) {
		
		for(int x=0;x< height-2;x++) {
			System.out.print(vChar1); 
			for (int y=0;y < width-2;y++) {
				System.out.print(" "); 
				
			}
			System.out.print("" + vChar1 + "\n");
		}
		
	}
	
	
	static void drawBoxM(int height, int width, char hChar1, char vChar1) {
		//call drawHorizontalLine twice to make sure
		drawHorizontalLine(width, hChar1);
		drawVerticalLine(height, width, vChar1);
		drawHorizontalLine(width, hChar1);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//directly copy from original program
		System.out.println("\nUsing for-loop and user values: ");
		int x, y;
		char hChar1, vChar1; 
		int ht1, wd1;
		char answer = 'y';
		Scanner input1 = new Scanner(System.in);
		while (answer == 'y') {
			System.out.print("\nPlease enter height of a box: "); 
			ht1 = input1.nextInt();
			System.out.print("\nPlease enter width of a box: ");
			wd1 = input1.nextInt();
			input1.nextLine(); //clean the buffer 
			System.out.print("\nPlease enter the horizontal charcters to draw box: "); 
			hChar1 = input1.nextLine().charAt(0);
			System.out.print("\nPlease enter the vertical charcters to draw box: ");
			vChar1 = input1.nextLine().charAt(0);
			
			
			drawBoxM(ht1, wd1, hChar1, vChar1);
			System.out.print("\n\n");
			System.out.print("Continue? Type 'y' for yes: ");
			answer = input1.nextLine().charAt(0);
		}
		System.out.println("\n\nThank you for using my draw box program");
	}

}
