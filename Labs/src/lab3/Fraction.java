// name: Haonan Di
// Andrew Id: hdi
package lab3;

public class Fraction {

	int numerator;
	int denominator;
	public Fraction(){
		this.numerator = 1;
		this.denominator = 1;
	}
	public Fraction(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public String toString() {
		
		return Integer.toString(numerator)+ "/" + Integer.toString(this.denominator);
	}
	public double toDecimal() {
		double a = (double) numerator;
		double b = (double) denominator;
		return (float)a/b;
	}
	public Fraction add(Fraction f) {
		int newDenominator = this.denominator * f.denominator;
		int newNumerator = this.denominator*f.numerator + this.numerator*f.denominator;
		int gcd = findGCD(newNumerator, newDenominator);
				
		return new Fraction(newNumerator/gcd, newDenominator/gcd);		
	}
	int findGCD(int numerator, int denominator) {
		if (numerator == 0) {
			return 1;
		}
		if (denominator == 0) {
			return numerator;
		}
		
		if (numerator == denominator) {
			return numerator;
		}
		else if (numerator > denominator) {
			return findGCD(denominator, numerator%denominator);
		}
		else {
			return findGCD(numerator, denominator%numerator);
		}
	}
}
