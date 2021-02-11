import java.util.*;
import java.io.*;
/**
 * @author Haonan Di
 * 
 * I mainly use this program to read, process and write information. I figure out how those massive functions are related (File, FileReader, BufferedReader, StreamTokenizer, PrintWriter etc.
 * I will use this program to help with my final, for now I think just store the input data in ArrayList can meet the requirement, I will try right now.
 *
 */
public class Exercise5 {

	/**
	 * @param args
	 */
	
	
	public static void processCLArguments(String[] args) {
		// I cannot figure out what this problem mean, so I just test if args contains two strings for java to read
		
		if (args.length != 2) {
			System.out.println("Usage: java Exercise5 inputFile outputFile");
		}
		else {
			System.out.println("Input will be read from: " + args[0]);
			System.out.println("Output will be written into: " + args[1]);

		}
		
		
		
	}
	
	public static void processInputOutputFiles(String[] args) {
		//read the disk file first
		//whole thought: first read the file, then use tokenizer to seperate those words and stored in different part, in final I will implement ArrayList to store the value.
	
		String fileName = args[0], outputFile = args[1];
		BufferedReader bufferedReader = null;
		StreamTokenizer myTokenizer;
		int nextToken;
		String firstName,middleName = null, lastName, outputString; 
		//I found in your demo video that a fatal error happens in your example II, I find it's because a person have a middle name to cause such error, so I add one.
		double Q1, Q2, Q3, Q4, Mid1, Mid2, Final;
		PrintWriter txtPrintStream = null;
		File inputFile = new File(fileName);
		
		
		
		//read the file line by line
		try {
			
			FileReader fileStream = new FileReader(inputFile);
			bufferedReader = new BufferedReader(fileStream);
			myTokenizer = new StreamTokenizer(bufferedReader);
			myTokenizer.whitespaceChars(',', ',');
			
			
			txtPrintStream = new PrintWriter(new FileOutputStream(outputFile, true));
			/*String s;
			char i = '1';
			while ((s = bufferedReader.readLine()) != null) {
				System.out.println("Student #" + i + ": " +s);
				i += 1;
			}
			
			bufferedReader.close();*/
			
		//use tokenizer to seperate such things
		
			nextToken = myTokenizer.nextToken();
			char i = '1';
			while (nextToken != StreamTokenizer.TT_EOF) {
				
				middleName = null;
				
				firstName = myTokenizer.sval;
				nextToken = myTokenizer.nextToken();
				lastName = myTokenizer.sval;
				nextToken = myTokenizer.nextToken();
				if (nextToken == StreamTokenizer.TT_WORD) {
					middleName = lastName;
					lastName = myTokenizer.sval;
					nextToken = myTokenizer.nextToken();
				}
				//the organize of the data, derived from your demo video.
				Q1 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				Q2 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				Q3 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				Q4 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				Mid1 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				Mid2 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				Final = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				
			
				//nextToken = myTokenizer.nextToken(); //last null
				//nextToken = myTokenizer.nextToken();
				
				//I want to make output clean, so I write a test about whether middle name exists.
				if (middleName == null) {
					System.out.println("Student #" + i + ": " + firstName + ' '
							+ lastName + ", " + Q1 + ", " + Q2 + ", "
							+ Q3 + ", "+ Q4 + ", "+ Mid1 + ", "+ Mid2 + ", "+ Final + ", ");
					
					
					//combine the string
					outputString = "Student #" + i + ": \"" + firstName + ' '
							+ lastName + "\" whose raw scores are: " + Q1 + ", " + Q2 + ", "
							+ Q3 + ", "+ Q4 + ", "+ Mid1 + ", "+ Mid2 + ", "+ Final + ", ";
					
				}
				else {
					
					System.out.println("Student #" + i + ": " + firstName + ' '
							+ middleName +' '+ lastName + ", " + Q1 + ", " + Q2 + ", "
							+ Q3 + ", "+ Q4 + ", "+ Mid1 + ", "+ Mid2 + ", "+ Final + ", ");
					
					
					
					outputString = "Student #" + i + ": \"" + firstName + ' '
							+ middleName +' '+ lastName + "\" whose raw scores are: " + Q1 + ", " + Q2 + ", "
							+ Q3 + ", "+ Q4 + ", "+ Mid1 + ", "+ Mid2 + ", "+ Final + ", ";
					
				}
				i++;
				txtPrintStream.write(outputString + '\n');
				
				
			} //end of while
			
			bufferedReader.close();
			
		}
		catch (IOException err) {
			System.out.println(err);
		}
		
		txtPrintStream.close();
		
		
		
		//generate the output file on the disk
		
		//PrintWriter txtPrintStream = null;
		//String outputFile = args[1];
		
		//try {
		//	txtPrintStream = new PrintWriter(new FileOutputStream(outputFile));
			
		//}
		
		
	}
	
	
	//9.3
	/*
	 * a)How do I supply arguments into my program
	 * Two ways to do that: first way is set the properties of the class file, edit it and add argument to it. 
	 * Second way is using run-run configuration in eclipse to do that
	 * 
	 * b)How do I process these argument?
	 * It's simple, just use args[0], args[1].... to direct call those arguments. In final I want to input from console not from the terminal window (i'm using MAC), I'm quite sure it should be more handy.
	 * 
	 * 
	 */
	
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
	
		processCLArguments(args);
		processInputOutputFiles(args);
		
	}

}
