// name: Haonan Di
// Andrew Id: hdi
package lab3;

public class MixedFraction extends Fraction{

	int naturalNumber;
	MixedFraction(int naturalNumber, int numerator, int denominator){
		this.naturalNumber = naturalNumber;
		super.numerator = numerator;
		super.denominator = denominator;
	}
	
	public String toString() {
		
		return Integer.toString(naturalNumber) + " " + Integer.toString(numerator)+ "/" + Integer.toString(this.denominator);
	}
	
	public double toDecimal() {
		double n = (double)naturalNumber;
		double n2 = (double)numerator;
		double d = (double)denominator;
		return (float)(n + n2/d);
	}
	
	public Fraction toFraction() {
		return new Fraction(numerator+naturalNumber*denominator, denominator);
	}
	
	
	public Fraction add(MixedFraction mf) {
		
		return this.toFraction().add(mf.toFraction());
	}
}
