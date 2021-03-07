package test_only;


public class for_test {
	

	
	
	public static void main(String[] args) {
		String a = "Hello";
		StringBuilder b = new StringBuilder(a);
		System.out.println(a.length());
		System.out.println(3/2);
		System.out.println(a.charAt(1));
		System.out.println(b.charAt(1));
		System.out.println(b.indexOf("H", 0));
		b.setCharAt(2, '-');
		System.out.println(b.toString());
	}

}