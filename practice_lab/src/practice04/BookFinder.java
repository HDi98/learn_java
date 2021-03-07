package practice04;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class BookFinder {

	String fileName;
	String searchString;
	
	void getUserInputs() {
		Scanner newScanner = new Scanner(System.in);
		System.out.println("Enter the file name: ");
		fileName = newScanner.nextLine();
		
		System.out.println("Enter what you are looking for: ");
		searchString = newScanner.nextLine();
		newScanner.close();
	}
	
	void loadRecords() {
		StringBuilder fileContent = new StringBuilder();
		File file = new File(fileName);
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		BookFinder n = new BookFinder();
	}
}
