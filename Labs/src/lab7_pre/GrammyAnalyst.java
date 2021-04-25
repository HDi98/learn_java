package lab7_pre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/** GrammyAnalyst takes Grammys.txt to provide two reports and one search functionality
 */
public class GrammyAnalyst {
	/**initialize these member variables with appropriate data structures **/
	List<Nomination> nominations;  
	Map<String, List<Nomination>> grammyMap;  
	List<Artist> artists;
	
	public static void main(String[] args) {
		GrammyAnalyst ga = new GrammyAnalyst();
		ga.loadNominations();
		ga.loadGrammyMap();
		System.out.println("*********** Grammy Report ****************");
		ga.printGrammyReport();
		System.out.println("*********** Search Artist ****************");
		System.out.println("Enter artist name");
		Scanner input = new Scanner(System.in);
		String artist = input.nextLine();
		ga.searchGrammys(artist);
		ga.loadArtists();
		System.out.println("*********** Artists Report ****************");
		ga.printArtistsReport();
		input.close();
	}
	
	/**loadNominations() reads data from Grammys.txt and 
	 * populates the nominations list, where each element is a Nomination object
	 */
	void loadNominations() {
		//write your code here
		nominations = new ArrayList<>();
		try {
			Scanner fileScanner = new Scanner(new File("Grammys.txt"));
			while (fileScanner.hasNextLine()) {
				String[] tmp = fileScanner.nextLine().toString().split(";");
				Nomination tmpNomi = new Nomination(tmp[0].trim(), tmp[1].trim(), tmp[2].trim());
				nominations.add(tmpNomi);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**loadGrammyMap uses artist name in lower case as the key, and a list of 
	 * all nominations for that artist as its value. Hint: use 'nominations' list 
	 * created in previous method to populate this map.
	 */
	void loadGrammyMap() {
		//write your code here
		grammyMap = new HashMap<>();
		for (Nomination n: nominations) {
			if (Objects.isNull(grammyMap.get(n.artist.toLowerCase()))) {
				List<Nomination> tmp = new ArrayList<>();
				tmp.add(n);
				grammyMap.put(n.artist.toLowerCase(), tmp);
			}
			else {
				grammyMap.get(n.artist.toLowerCase()).add(n);
			}
		}
	} 
	
	/**loadArtists loads the artists array List. Each Artist object in it should have
	 * artist's name in proper case, i.e., as read from data file, and 
	 * a list of nominations for that artist. Hint: use 'grammyMap' created in 
	 * previous method to populate this list
	 */
	void loadArtists() {
		//write your code here
		artists = new ArrayList<>();
		for (Map.Entry<String, List<Nomination>> n: grammyMap.entrySet()) {
			Artist tmpArt = new Artist(n.getKey(), n.getValue());
			artists.add(tmpArt);
		}
	}
	
	/**printGrammyReport prints report as shown in the handout */
	void printGrammyReport() {
		//write your code here
		Collections.sort(nominations);
		int sign = 1;
		for (Nomination n: nominations) {
			System.out.println(sign + ". " + n.artist + ": " + n.title + ", " + n.category);
			sign++;
		}
	}
	
	/**printArtistReport prints report as shown in the handout */
	void printArtistsReport() {
		//write your code here
		Collections.sort(artists);
		int sign = 1;
		for (Artist a: artists) {
			System.out.println(sign + ". " + a.name + ": " + a.nominations.size());
			sign++;
		}
	}
	
	/**searchGrammys takes a string as input and makes a case-insensitive
	 * search on grammyMap. If found, it prints data about all nominations
	 * as shown in the handout.
	 */
	void searchGrammys(String artist) {
		//write your code here
	}
	
}
