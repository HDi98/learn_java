//name: Haonan Di
//Andrew id: hdi
package exam3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class BookLibrary {

	List<Book> booksList;  //stores all books
	Map<String, List<Book>> authorsMap;  //stores all authors' names in their original 'case' as keys and their list of books as value
	String[] bookRecords; //carries data read from the file

	//do not change this method
	public static void main(String[] args) {
		
		BookLibrary bookLibrary = new BookLibrary();
		bookLibrary.readFile("BookAuthors.txt");
		bookLibrary.loadBooksList();
		bookLibrary.loadAuthorsMap();
		
		Scanner input = new Scanner(System.in);
		System.out.println("*** Welcome to Book Library ***");
		System.out.println("1. Print Books List");
		System.out.println("2. Print Authors Map");
		switch (input.nextInt()) {
		case 1: bookLibrary.printBooksList(); break;
		case 2: bookLibrary.printAuthorsMap(); break;
		default: break;
		}
		System.out.println("** Bye Bye! **"); 
		input.close();
	}

	
	//do not change this method
	void readFile(String filename) {
		StringBuilder fileData = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNext()) {
				fileData.append(input.nextLine() + "\n");
			}
			bookRecords = fileData.toString().split("\n");
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	/**loadBooksList() loads data from bookRecords into
	 * booksList
	 */
	void loadBooksList() {
		//write your code here
		booksList = new ArrayList<>();
		
		for (String s: bookRecords) {
			String[] tmp = s.split("\t");
			Book b = new Book(tmp[0]);
			b.authors = new ArrayList<>();
			for (int i = 1; i < tmp.length; i++) {
				b.authors.add(tmp[i]);
			}
			booksList.add(b);
		}
		Collections.sort(booksList);
	}

	/**loadAuthorsMap() loads data from booksList into
	 * authorsMap
	 */
	void loadAuthorsMap() {
		//write your code here
		authorsMap = new TreeMap<>();
		for (Book b: booksList) {
			for(String s: b.authors) {
				if (authorsMap.containsKey(s)) {
					authorsMap.get(s).add(b);
				}
				else {
					List<Book> tmplst = new ArrayList<>();
					tmplst.add(b);
					authorsMap.put(s, tmplst);
				}
			}
		}
	}

	/**printBooksList() prints the output
	 * as shown in the handout
	 */
	void printBooksList() {
		//write your code here
		int count = 1;
		for (Book b: booksList) {
			System.out.print(count + ". " + b.title + "; ");
			for (String s: b.authors) {
				System.out.print(s + "; ");
			}
			System.out.println();
			count++;
		}
	}
	
	/**printAuthorsMap prints the output
	 * as shown in the handout. 
	 */
	void printAuthorsMap() {
		//write your code here
		int count = 1;
		for (Map.Entry<String, List<Book>> a: authorsMap.entrySet()) {
			System.out.println(count + ". Books by " + a.getKey());
			for (Book b: a.getValue()) {
				System.out.println("   -" + b.title);
			}
			count++;
		}
	}

}
