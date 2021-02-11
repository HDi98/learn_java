package lab0b;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBMICalculator {
	
	BMICalculator bmiCalc = new BMICalculator();

	@Test
	public void test1_CalculateBMI() {
		assertEquals("Test calc BMI", 25.845589, bmiCalc.calculateBMI(170, 68), 0.000001);
	}
	
	@Test
	public void test2_CalculateRoundedBMI() {
		assertEquals("Test rounded BMI", 26, bmiCalc.calculateRoundedBMI(170, 68));
	}

	@Test
	public void test3_FindBMIStatus_Underweight() {
		assertEquals("Test BMI status", "Underweight", bmiCalc.findBMIStatus(18));
	}
	
	@Test
	public void test4_FindBMIStatus_Normal() {
		assertEquals("Test BMI status", "Normal", bmiCalc.findBMIStatus(20));
	}
	
	@Test
	public void test5_FindBMIStatus_Overweight() {
		assertEquals("Test BMI status", "Overweight", bmiCalc.findBMIStatus(25));
	}
	
	@Test
	public void test6_FindBMIStatus_Obese() {
		assertEquals("Test BMI status", "Obese", bmiCalc.findBMIStatus(30));
	}
}
