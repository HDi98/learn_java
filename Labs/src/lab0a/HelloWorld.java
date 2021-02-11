package lab0a;

public class HelloWorld {
	/** The sayHello method prints Hello message for the 'name' passed to it as a  parameter.
	 * You need to   change the code so that it also returns the Hello message
	 * that is created in the method.
	 * First run the test cases without changing any code. 
	 * Then fix the code and run the test case again.
	 **/
	public String sayHello(String name) {
		String greeting =  "Hello "  +  name + "!";
		System.out.println(greeting);
		return greeting;   //change code here
	}

	/** The main method creates an   object of HelloWorld and invokes sayHello method 
	 * with a  String parameter 'name'*/
	public static void main(String[] args) {
		HelloWorld hw   = new HelloWorld();
		hw.sayHello("CMU");
		hw.sayHello("Heinz");
	}
}
