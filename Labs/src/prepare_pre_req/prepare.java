package prepare_pre_req;

import java.util.Scanner;

public class prepare {

	
	private static int printSumTopK(int topk) {
		int out1 = 0;
		for(int i = 0; i < topk; i++) {
			out1 += (i+1);
		}
		return out1;
	}
	
	private static boolean isPrimeNum(int p) {
		int i;
		if(p <= 1) {
			return false;
		}
		for(i = 2; i<=Math.sqrt(p); i++) {
			if (p%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private static void printFirstKprimeNum(int k) {
		int count = 0;
		int i = 2;
		while(count < k) {
			if (isPrimeNum(i)) {
				System.out.println(i);
				count += 1;
			}
			i++;
		}
	}
	
	private static void getAndSort(int n) {
		
	}
	
	public static void main(String[] arg) {
		
		System.out.println(printSumTopK(10));
		printFirstKprimeNum(10);
		System.out.println(isPrimeNum(2));
		System.out.println("Hello World");
	}
}
