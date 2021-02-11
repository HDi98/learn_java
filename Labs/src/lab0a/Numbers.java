package lab0a;

public class Numbers {
	public static void main(String[] args) {
		Numbers n =  new Numbers();
		System.out.println(n.sayWhat(5));
	}
	//fix this method
	public String sayWhat(int number) {
		return "odd";
	}
	//fix this method
	public boolean isPrime(int number) {
		for (int i  =  2;   i  < number; i++) {   
			//can you improve this loop??
			if   (number %  i  ==   0) 
				return false;
		}
		return true;
	}
	
	public int nthPrime(int number) {
		int k = 2;
		int count = 1;
		while (count < number) {
			k ++;
			if (isPrime(k)) {
				count += 1;
			}
		}
		return k;
	}
}
