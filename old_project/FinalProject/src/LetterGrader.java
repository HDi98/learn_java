/**
 * This programs aims at receiving, processing and printing out the letter grade for each students
 * receiving: use StreamTokenizer to separate and store grade for Q1-Final 
 * Processing: 1)calculate the letter grade. 2)sorting
 * printing: write the letter grade into a file, displaying statistics for the class.
 * 
 */
import java.util.*;
import java.io.*;

/**
 * @author Haonan Di
 *
 */
public class LetterGrader {
	
	//first declare status for such object
	private String inputName, outputName;
	private ArrayList name = new ArrayList <String>();
	private ArrayList Q1 = new ArrayList <Double>();
	private ArrayList Q2 = new ArrayList <Double>();
	private ArrayList Q3 = new ArrayList <Double>();
	private ArrayList Q4 = new ArrayList <Double>();
	private ArrayList Mid1 = new ArrayList <Double>();
	private ArrayList Mid2 = new ArrayList <Double>();
	private ArrayList Final = new ArrayList <Double>();
	private ArrayList FinalGrade = new ArrayList();
	private ArrayList LetterGrade = new ArrayList();
	private ArrayList OutGrade = new ArrayList();
	int count = 0;
	
	
	 //constructor
	public LetterGrader(String [] args) {
		super();
		inputName = args[0];
		outputName = args[1];
	}


	public void readScore() {
		//read the file and process it, then allocate each data into different parts of arraylist.
		
		BufferedReader bufferedReader = null;
		StreamTokenizer myTokenizer;
		int nextToken;
		String firstName,middleName = null, lastName, name; 
		//I found in your demo video that a fatal error happens in your example II, I find it's because a person have a middle name to cause such error, so I add one.
		//double Q1, Q2, Q3, Q4, Mid1, Mid2, Final;
		File inputFile = new File(inputName);
		
		
		//test whether the input file exists
		if (!inputFile.exists()) {
			System.out.println("Input file " + inputName + " does not exist");
			System.exit(2);
		}
		
		 
		try {
			//read line by line
			FileReader fileStream = new FileReader(inputFile);
			bufferedReader = new BufferedReader(fileStream);
			myTokenizer = new StreamTokenizer(bufferedReader);
			myTokenizer.whitespaceChars(',', ',');
			//separate the input line
			nextToken = myTokenizer.nextToken();
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
				Q1.add( myTokenizer.nval);
				nextToken = myTokenizer.nextToken();
				Q2.add(myTokenizer.nval);
				nextToken = myTokenizer.nextToken();
				Q3.add(myTokenizer.nval);
				nextToken = myTokenizer.nextToken();
				Q4.add(myTokenizer.nval);
				nextToken = myTokenizer.nextToken();
				Mid1.add(myTokenizer.nval);
				nextToken = myTokenizer.nextToken();
				Mid2.add(myTokenizer.nval);
				nextToken = myTokenizer.nextToken();
				Final.add(myTokenizer.nval);
				nextToken = myTokenizer.nextToken();
			
			
				//add those things to my arraylist
				if (middleName == null) {
					name = firstName + ' ' + lastName;
				}
				else {
					name = firstName + ' ' + middleName + ' ' + lastName;
				}
				
				this.name.add(name);
				count ++;
			}//end of input process
			
			bufferedReader.close(); //close after use
			
		}//end try
		catch (IOException err) {
			System.out.println(err);
		}
		
	}
	
	public void calcLetterGrade() {
		//get the letter grade from the grade, use multiple if-else
		double finalGrade;
		
		for(int i =0; i < count; i ++) {
			finalGrade = (double)Q1.get(i) * (double)0.1 + (double)Q2.get(i) * (double)0.1 + (double)Q3.get(i) * (double)0.1 +
					(double)Q4.get(i) * (double)0.1 + (double)Mid1.get(i) * (double)0.2 + (double)Mid2.get(i) * (double)0.15 + 
					(double)Final.get(i) * (double)0.25;
			this.FinalGrade.add(finalGrade);
			
			//have the letterGrade
			
			if (finalGrade >= 90) {
				LetterGrade.add('A');
			}
			else if (finalGrade >= 80) {
				LetterGrade.add('B');
			}
			else if (finalGrade >= 70) {
				LetterGrade.add('C');
			}
			else if (finalGrade >= 60) {
				LetterGrade.add('D');
			}
			else {
				LetterGrade.add('F');
			}
		}
		
	}
	
	private void sortM() {
		//Combine the name and the letter grade and sort them
		
		//first combine the name and the letter grade
		for(int i =0; i < count; i ++) {
			OutGrade.add((String)name.get(i) + '\t' + LetterGrade.get(i));
		}
		
		Collections.sort(OutGrade);
		
	}
	
	public void printGrade() {
		
		//print the name and the letter grade into the output file
		PrintWriter txtPrintStream = null;
		sortM();
		
		try {
			txtPrintStream = new PrintWriter(new FileOutputStream(outputName, true));

			txtPrintStream.println("Letter grade for " + count + "students given in " + inputName + " is: \n");
			for(int i = 0; i < count; i++) {
				txtPrintStream.println((String)OutGrade.get(i));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		
		System.out.println("Letter grade has been calculated for students listed in input file " + inputName + " and written to output file " + outputName);
		
		txtPrintStream.close(); //close it
	}
	
	
	private double avg(ArrayList grade) {
		//average method
		
		double sum = 0, avg = 0;
		for(int i =0; i < count; i ++) {
			sum += (double)grade.get(i);
		}
		avg = sum/(count);
		
		return avg;
	}
	
	private int min(ArrayList grade) {
		//min method
		
		Number temp = (Number) grade.get(0);
		
		for(int i = 1; i < count; i ++) {
			if (temp.intValue() > ((Number)grade.get(i)).intValue()) {
				temp = (Number)grade.get(i);
			}
		}//end of for loop
		
		return temp.intValue();
	}
	
	private int max(ArrayList grade) {
		//max method
		
		Number temp = (Number) grade.get(0);
		
		for(int i = 1; i < count; i ++) {
			if (temp.intValue() < ((Number)grade.get(i)).intValue() ){
				temp = (Number)grade.get(i);
			}
		}//end of for loop
		
		return temp.intValue();
	}
	
	public void displayAverages() {
		//using method above and print the average, min, max
		
		System.out.println("\nHere is the class averages: ");
		System.out.println("\tQ1\tQ2\tQ3\tQ4\tMid1\tMid2\tFinal");
		System.out.printf("Average:%5.2f\t%5.2f\t%5.2f\t%5.2f\t%5.2f\t%5.2f\t%5.2f\n", 
				avg(Q1),avg(Q2),avg(Q3),avg(Q4),avg(Mid1),avg(Mid2),avg(Final));
		System.out.println("Minimum:" + min(Q1) + '\t' + min(Q2) + '\t' + min(Q3) + '\t' +
				min(Q4) + '\t' + min(Mid1) + '\t' + min(Mid2) + '\t' + min(Final));
		System.out.println("Maximum:" + max(Q1) + '\t' + max(Q2) + '\t' + max(Q3) + '\t' +
				max(Q4) + '\t' + max(Mid1) + '\t' + max(Mid2) + '\t' + max(Final));
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LetterGrader letterGrader = new LetterGrader(args);
		//LetterGrader is your main class,
		letterGrader.readScore(); 
		letterGrader.calcLetterGrade(); 
		letterGrader.printGrade(); 
		letterGrader.displayAverages();
	}

}
