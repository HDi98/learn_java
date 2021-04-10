package practice08;

import java.util.Scanner;

public class DocAnalyst {

	Document document;
	void analyzeDoc(String filename) {
		if (filename.matches(".*\\.csv$")) {
			document = new CSVDoc(filename);
		}
		else if (filename.matches(".*\\.txt$")) {
			document = new RegularDoc(filename);
		}
	}
	
	public static void main(String[] args) {
		String filename;
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter file name");
		filename = userInput.nextLine();
		DocAnalyst newDoc = new DocAnalyst();
		newDoc.analyzeDoc(filename);
		newDoc.document.printDocInfo();
		userInput.close();
	}
	
}
