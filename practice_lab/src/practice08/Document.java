package practice08;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Document {

	String filename;
	StringBuilder fileContent = new StringBuilder();
	Document(String filename) {
		this.filename = filename;
	}
	void readFile() {
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			while (fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine()+"\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	abstract void collectDocInfo();
	abstract void printDocInfo();
}
