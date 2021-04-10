package practice08;

public class RegularDoc extends Document {

	int wordCount = 0;
	RegularDoc(String filename) {
		super(filename);
		super.readFile();
	}
	
	@Override
	void collectDocInfo() { 
		
		for (String s: fileContent.toString().split("\n")){
			wordCount += s.split(" ").length;
			
		}
	}
	
	@Override
	void printDocInfo() {
		collectDocInfo();
		System.out.println("This file has " + wordCount + " words");
	}
}
