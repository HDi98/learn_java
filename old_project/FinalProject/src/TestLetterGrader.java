
/**
 * @author Haonan Di
 * This is a test program
 *
 */
public class TestLetterGrader {

	/**
	 * @param args
	 */
	
	//test the valid input arguments
	
	public static void processCLArguments(String[] args) {
		//derived from HW9: test valid arguments
		
		if(args.length < 2) {
			System.out.println("Usage: javafile lettergrader inputFile outputFile");
			System.exit(1);
		}
		else {
			System.out.println("Input will be read from:"+ args[0] +
			"\nOutput will be written into: " + args[1]);
			System.out.println(); 
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//test the argument
		processCLArguments(args);
		
		
		LetterGrader letterGrader = new LetterGrader(args);
		//LetterGrader is your main class,
		letterGrader.readScore(); 
		letterGrader.calcLetterGrade(); 
		letterGrader.printGrade(); 
		letterGrader.displayAverages();
	}

}
