package lab0a;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestHelloWorld {
	HelloWorld hw   =  new HelloWorld();
	
	@Test
	public void testHelloWorld() {
		assertEquals("Testing hello world", "Hello CMU!", hw.sayHello("CMU"));
		assertEquals("Testing hello world", "Hello Heinz!", hw.sayHello("Heinz"));}

}
