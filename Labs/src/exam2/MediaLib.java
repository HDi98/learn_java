//name: Haonan Di
//andrew id: hdi
package exam2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MediaLib {

	Media[] media; //to hold all types of media

	//please DO NOT change the main method
	public static void main(String[] args) {
		MediaLib mediaLib = new MediaLib();
		mediaLib.loadMedia("Media.txt");
		int index = 0;
		System.out.println("*** Welcome to MediaLib ***");
		for (Media m: mediaLib.media) {
			System.out.printf("%2d. %-10s %-20s \t %10s %n", ++index, m.getClass().getSimpleName(), m.title, m.year);
		}
		System.out.println("------------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the media number you would like to enjoy today?");
		int choice = input.nextInt();
		input.nextLine();
		input.close();
		mediaLib.selectMedia(choice);
	}

	//loadMedia reads the data from the file named filename 
	// and loads different types of media into media[] array
	void loadMedia(String filename) {
		//write your code here
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			StringBuilder fileContent = new StringBuilder();
			String[] mediafile;
			while (fileScanner.hasNextLine()) {
				fileContent.append(fileScanner.nextLine() + "\n");
			}
			mediafile = fileContent.toString().split("\n");
			//initialize the media array
			media = new Media[mediafile.length];
			for(int i = 0; i < mediafile.length; i++) {
				String[] tmp = mediafile[i].split(", ");
				if (tmp[0].toLowerCase().equals("ebook")) {
					media[i] = new EBook(tmp[1], tmp[4], tmp[2], Integer.parseInt(tmp[3]), tmp[5], Integer.parseInt(tmp[6]));
				}
				else if (tmp[0].toLowerCase().equals("movie")) {
					media[i] = new Movie(tmp[1], tmp[4], tmp[2], Integer.parseInt(tmp[3]));
				}
				else if (tmp[0].toLowerCase().equals("book")) {
					media[i] = new Book(tmp[1], tmp[4], tmp[2], Integer.parseInt(tmp[3]));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//selectMedia takes the user choice, uses it to index into media[]
	//and invokes the media' enjoy().
	//For EBooks, it needs to invoke download() before invoking enjoy()
	void selectMedia(int choice) {
		//write your code here
		if (media[choice-1] instanceof EBook) {
			((EBook) media[choice-1]).download();
			media[choice-1].enjoy();
		}
		else {
			media[choice-1].enjoy();
		}
	}

}
