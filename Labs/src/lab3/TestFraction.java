package lab3;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestFraction {

	@Test
	public void test1_DefaultConstructor() {
		Fraction f = new Fraction();
		assertEquals(1, f.numerator);
		assertEquals(1, f.denominator);
	}
	
	@Test
	public void test2_NonDefaultConstructor() {
		Fraction f = new Fraction(3, 4);
		assertEquals(3, f.numerator);
		assertEquals(4, f.denominator);
	}
	
	@Test
	public void test3_GCDWithDivisor() {
		Fraction f = new Fraction();
		assertEquals("Testing 12/4 ", 4, f.findGCD(12,4));
	}
	
	@Test
	public void test4_GCDWithSmallerNumerator() {
		Fraction f = new Fraction();
		assertEquals("Testing 5/15 ", 5, f.findGCD(5, 15));
	}
	@Test
	public void test5_GCDWithDenominatorZero() {
		Fraction f = new Fraction();
		assertEquals("Testing 1/0 ", 1, f.findGCD(1,0));
	}
	
	@Test
	public void test6_GCDWithNumeratorZero() {
		Fraction f = new Fraction();
		assertEquals("Testing 0/1 ", 1, f.findGCD(0,1));
	}
	
	@Test
	public void test7_GCDWithoutDivisor() {
		Fraction f = new Fraction();
		assertEquals("Testing 13/7 ", 1, f.findGCD(13,7));
	}
	
	@Test
	public void test8_ToString() {
		Fraction f = new Fraction(13, 7);
		assertEquals("Testing toString", "13/7", f.toString());
	}
	
	@Test
	public void test9_Add() {
		Fraction f1 = new Fraction (1, 2);
		Fraction f2 = new Fraction (3, 4);
		Fraction f3 = f1.add(f2);
		assertEquals(5, f3.numerator);
		assertEquals(4, f3.denominator);
	}

	@Test
	public void test10_ToDecimal() {
		Fraction f = new Fraction (1, 2);
		assertEquals(0.5, f.toDecimal(), 0D);		
	}
	
	@Test
	public void test11_MixedFraction_NonDefaultConstructor() {
		MixedFraction mf = new MixedFraction(3, 1, 2);
		assertEquals(3, mf.naturalNumber);
		assertEquals(1, mf.numerator);
		assertEquals(2, mf.denominator);
	}
	
	@Test
	public void test12_MixedFraction_toString() {
		MixedFraction mf = new MixedFraction(3, 1, 2);
		assertEquals("3 1/2", mf.toString());
	}
	
	@Test
	public void test13_MixedFraction_toDecimal() {
		MixedFraction mf = new MixedFraction(3, 1, 2);
		assertEquals(3.5, mf.toDecimal(), 0);
	}
	
	@Test
	public void test14_AddMixedFraction() {
		MixedFraction mf1 = new MixedFraction(1, 1, 2);
		MixedFraction mf2 = new MixedFraction(1, 1, 3);
		Fraction f = mf1.add(mf2);
		assertEquals(17, f.numerator);
		assertEquals(6, f.denominator);
	}
}
