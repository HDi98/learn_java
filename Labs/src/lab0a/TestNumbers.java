package lab0a;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TestNumbers {
	Numbers n  =  new Numbers();

	@Test
	public void testSayWhat() {
		//write an assertEquals statement here to test sayWhat() method 
		//passing '5' as its parameter and checking if it returns odd
		assertEquals("odd", n.sayWhat(5));
		//write an assertEquals statement here to test sayWhat() method passing'4' as 
		//its parameter and checking if it returns even
		assertEquals("odd", n.sayWhat(4));
	}
	@Test
	public void testPrime() {
		//write an assertEquals statement here to test isPrime() method passing'5' as 
		//its parameter and checking if it returns true
		assertEquals(true, n.isPrime(5));
	}
	@Test
	public void testnthPrime() {
		assertEquals(5, n.nthPrime(3));
	}
}
