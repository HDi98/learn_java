package lab3;

import java.util.Scanner;

//**** do not change anything in this class ****
public class FractionAction {
	Scanner input = new Scanner (System.in);
	
	public static void main(String[] args) {	
		FractionAction fractionAction = new FractionAction();
		System.out.println("*** WELCOME TO FRACTION ACTION ***");
		System.out.println("1. Add Fractions");
		System.out.println("2. Add Mixed Fractions");
		System.out.println("*** Enter your choice ***");
		int choice = fractionAction.input.nextInt();
		switch (choice) {
			case 1: fractionAction.addFractions(); break;
			case 2: fractionAction.addMixedFractions(); break;
			default: break;
		}
	}
	
	void addFractions() {
		System.out.println("------- Input first fraction -------");
		System.out.println("Numerator: ");
		int n1 = input.nextInt();
		System.out.println("Denominator: ");
		int d1 = input.nextInt();
		
		System.out.println("------- Input second fraction -------");
		System.out.println("Numerator: ");
		int n2 = input.nextInt();
		System.out.println("Denominator: ");
		int d2 = input.nextInt();
		
		Fraction f1 = new Fraction(n1, d1);
		Fraction f2 = new Fraction(n2, d2);

		System.out.println("f1 = " + f1);
		System.out.println("f2 = " + f2);
		
		Fraction f3 = f1.add(f2);
		System.out.println("f1 + f2 = " +     f1.add(f2) );
		System.out.println("The sum in decimal is: " + f3.toDecimal());
	}
	
	void addMixedFractions() {
		System.out.println("------- Input first mixed fraction -------");
		System.out.println("Natural number");
		int natural1 = input.nextInt();
		System.out.println("Numerator: ");
		int numerator1 = input.nextInt();
		System.out.println("Denominator: ");
		int denominator1 = input.nextInt();
		
		System.out.println("------- Input second mixed fraction -------");
		System.out.println("Natural number");
		int natural2 = input.nextInt();
		System.out.println("Numerator: ");
		int numerator2 = input.nextInt();
		System.out.println("Denominator: ");
		int denominator2 = input.nextInt();
		
		MixedFraction mf1 = new MixedFraction(natural1, numerator1, denominator1);
		MixedFraction mf2 = new MixedFraction(natural2, numerator2, denominator2);

		System.out.println("mf1 = " + mf1);
		System.out.println("mf2 = " + mf2);
		
		Fraction f3 = mf1.add(mf2);
		System.out.println("mf1 + mf2 = " +     mf1.add(mf2) );
		System.out.println("The sum in decimal is: " + f3.toDecimal());
	}
}
