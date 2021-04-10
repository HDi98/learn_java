//name: Haonan Di
//andrew id: hdi
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Thesaurus extends WordReference {

	Thesaurus(String filename){
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			StringBuilder fileContent = new StringBuilder();
			while (fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine() + "\n");
			}
			String[] fileLine = fileContent.toString().split("\n");
			int len1 = fileLine.length;
			int len2 = 0;
			for(int i = 0; i < len1; i++) {
				if (fileLine[i].split(",").length > len2) {
					len2 = fileLine[i].split(",").length;
				}
			}
			wordData = new String[len1][len2];
			for(int i = 0; i < len1; i++) {
				for (int j = 0; j < fileLine[i].split(",").length; j++) {
					wordData[i][j] = fileLine[i].split(",")[j].trim();
				}
			}
			fileScanner.close();
			//System.out.println(len1 +" " + len2 + " " + wordData.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	String[] lookup(String word) {
		// TODO Auto-generated method stub
		int line = -1;
		for (int i = 0; i < wordData.length; i++) {
			for(int j = 0; j < wordData[i].length; j++) {
				if (wordData[i][j] == null) {
					break;
				}
				if (wordData[i][j].toLowerCase().equals(word.toLowerCase())) {
					line = i;
				}
			}
		}
		if (line == -1) {
			return null;
		}
		else {
			StringBuilder tmp = new StringBuilder();
			for(int k = 0; k < wordData[line].length; k++) {
				if (wordData[line][k] != null && !wordData[line][k].equals(word)) {
					tmp.append(wordData[line][k] + "\n");
				}
			}
			return tmp.toString().split("\n");
		}
		
	}

}
