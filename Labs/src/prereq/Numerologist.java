//name: dihaonan 
//Andrew id: hdi
package prereq;

import java.util.Scanner;

public class Numerologist {
	
	//DO NOT change this method.
	public static void main(String[] args) {
		Numerologist n = new Numerologist();
		System.out.println("Enter an integer");
		Scanner input = new Scanner(System.in);
		int number = input.nextInt();
		System.out.println("Your lucky number is " + n.getLuckyNumber(number));
		input.close();
	}
	
	
	int getNewNumber(int num1) {
		//this method is aim to get the next integer generated by our alogorithm.
		int out = 0; //this is the output value for this method
		int temp;
		while (num1 != 0) {
			temp = num1%10; //split the last number from the former one
			out += temp;
			num1 = num1 / 10; //integer division.
		}
		
		return out;
	}
	
	
	int getLuckyNumber(int num) {
		//write your code here
		int l;
		num = Math.abs(num); //first ignore the minus sign for num
		l = getNewNumber(num);
		while(l>=10) { //l<10 is the end for this loop and we get the lucky number
			l = getNewNumber(l);
		}
		
		return l;
	}
}

