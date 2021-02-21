package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNumParser {
	
	NumParser numParser = new NumParser();

	@Test
	public void test1_NoInput() {
		numParser.parseCalculate("");
		assertEquals("Test sum", 0, numParser.sum, 0);
		assertEquals("Test max", 0, numParser.max, 0);
		assertEquals("Test min", 0, numParser.min, 0);
		assertEquals("Test count", 0, numParser.count, 0);
	}
	
	@Test
	public void test2_IntsAndSpaces() {
		numParser.parseCalculate("12 22 -5");
		assertEquals("Test sum", 29, numParser.sum, 0);
		assertEquals("Test max", 22, numParser.max, 0);
		assertEquals("Test min", -5, numParser.min, 0);
		assertEquals("Test count", 3, numParser.count, 0);
	}
	
	@Test
	public void test3_Mixed() {
		numParser.parseCalculate("10.5, 23, -32");
		assertEquals("Test sum", 1.5, numParser.sum, 0);
		assertEquals("Test max", 23, numParser.max, 0);
		assertEquals("Test min", -32, numParser.min, 0);
		assertEquals("Test count", 3, numParser.count, 0);
	}
	
	@Test
	public void test4_SomeGarbage1() {
		numParser.parseCalculate("23 abc 42, -33.5, 2a");
		assertEquals("Test sum", 31.5, numParser.sum, 0);
		assertEquals("Test max", 42, numParser.max, 0);
		assertEquals("Test min", -33.5, numParser.min, 0);
		assertEquals("Test count", 3, numParser.count, 0);
	}
	
	@Test
	public void test5_SomeGarbage2() {
		numParser.parseCalculate("abc 33 77");
		assertEquals("Test sum", 110.0, numParser.sum, 0);
		assertEquals("Test max", 77, numParser.max, 0);
		assertEquals("Test min", 33, numParser.min, 0);
		assertEquals("Test count", 2, numParser.count, 0);
	}
	
	@Test
	public void test6_AllGarbage() {
		numParser.parseCalculate("cb 4jf +4f */$");
		assertEquals("Test sum", 0, numParser.sum, 0);
		assertEquals("Test max", 0, numParser.max, 0);
		assertEquals("Test min", 0, numParser.min, 0);
		assertEquals("Test count", 0, numParser.count, 0);
	}
	
	

}
