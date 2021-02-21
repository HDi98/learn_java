//name: Haonan Di, andrew id: hdi

package lab1;

import java.util.Scanner;

public class NumParser {
	double sum, max, min; //to store the results to be printed
	int count; //to count the valid numbers parsed

	/** DO NOT change the main method **/
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the numbers separated by a space or a comma");
		String numbers = input.nextLine();
		NumParser np = new NumParser();
		np.parseCalculate(numbers);
		np.printResults();
		input.close();
	}

	/**The parseCalculate method takes a string as input parameter and parses
	 * out the valid numbers from it. While parsing, it also calculates sum, max, min, and count.
	 * If it finds any invalid token, it discards it and prints it out as a discarded token 
	 * @param numbers
	 */
	void parseCalculate(String numbers) {
		//Write your code here
		Scanner num = new Scanner(numbers);
		count = 0;
		max = - Double.MAX_VALUE;  //discuss with classmates and find the max/min value for double
		
		min = Double.MAX_VALUE;
		while (num.hasNext()) {
			// regex
			String token = num.useDelimiter("[\\s,]+").next();
			if (token.matches("[+-]?[0-9]+[.]?[0-9]*")) {
				double tmp = Double.parseDouble(token);
				count++;
				sum += tmp;
				if(max < tmp) max = tmp;
				if(min > tmp) min = tmp;
			}
			else {
				System.out.println("Discarded token is: " + token);
			}
		}
		num.close();
		if (max == - Double.MAX_VALUE) {
			max = 0;
		}
		if (min == Double.MAX_VALUE) {
			min = 0;
		}
	}

	/** The printResult method prints the output as shown 
	 * in the lab-handout
	 */
	private void printResults() {
		//Write your code here
		System.out.println("Sum is " + sum);
		System.out.println("Average is " + sum/count);
		System.out.println("Max is " + max);
		System.out.println("Min is " + min);
		
	}
}
