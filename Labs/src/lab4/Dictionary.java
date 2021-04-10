//name: Haonan Di
//andrew id: hdi
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary extends WordReference {

	Dictionary(String filename){
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			StringBuilder fileContent = new StringBuilder();
			while (fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine() + "\n");
			}
			String[] fileLine = fileContent.toString().split("\n");
			wordData = new String[fileLine.length][2];
			for(int i = 0; i < fileLine.length; i++) {
				wordData[i][0] = fileLine[i].split("\\(")[0].trim();
				int k = fileLine[i].indexOf(")");
				wordData[i][1] = fileLine[i].substring(k+1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	String[] lookup(String word) {
		// TODO Auto-generated method stub
		StringBuilder n = new StringBuilder();
		for(int i = 0; i < wordData.length; i++) {
			if (wordData[i][0].toLowerCase().equals(word.toLowerCase())) {
				n.append(wordData[i][1] + "\n");
			}
		}
		if (n.length() == 0) {
			return null;
		}
		else {
			return n.toString().split("\n");
		}
		
	}

}
