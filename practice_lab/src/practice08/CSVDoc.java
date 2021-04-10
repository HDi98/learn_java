package practice08;

public class CSVDoc extends Document {

	int rowCount;
	int columnCount;
	
	CSVDoc(String filename) {
		super(filename);
		super.readFile();
		// TODO Auto-generated constructor stub
	}

	@Override
	void collectDocInfo() {
		// TODO Auto-generated method stub
		String[] f = fileContent.toString().split("\n");
		rowCount = f.length;
		columnCount = f[0].split(",").length;
	}

	@Override
	void printDocInfo() {
		// TODO Auto-generated method stub
		collectDocInfo();
		
		System.out.println(filename + " has " + rowCount + " rows and " + columnCount + " columns");
	}

}
