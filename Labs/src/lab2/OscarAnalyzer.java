// name: Haonan Di
// Andrew Id: hdi
package lab2;

import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;
/**OscarAnalyzer takes data from Oscar.txt and allows the 
 * user to search for some string i.e. either an actor's name or a movie's name. 
 * If the search string is found, it tells whether the name is that of an actor or of a movie.
 * It then prints the names of Oscar-nominated movies by the found actor, 
 * or the names of actors that have been nominated for that movie.
 * @author ndwivedi
 */
public class OscarAnalyzer {
	
	//DO NOT CHANGE these member variables
	String[] oscarStrings; 		//data read from Oscar.txt
	String searchString;		//to be initialized by user input
	String[] resultStrings;		//carries all matching movies or actors found for the search string
	
	
	//Do not change the main method
	public static void main(String[] args) {
		OscarAnalyzer oa = new OscarAnalyzer();
		oa.loadData();
		oa.getUserInput();
		oa.printOutput(oa.searchActorOrMovie());
	}
	
	/**this method takes user input from keyboard and initializes searchString*/
	void getUserInput() {
		//write your code here.
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter your search string");
		searchString = userInput.nextLine().trim(); // in case of any white space entered
		userInput.close();
	}
	
	/**this method opens the Oscar.txt file and reads data.
	 * It initializes oscarStrings with the data from the file Oscar.txt.
	 * Note that each row in the file becomes one element in the array. 
	 */
	void loadData() {
		//write your code here.
		StringBuilder fileContent = new StringBuilder();
		File file = new File("Oscar.txt");
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (fileScanner.useDelimiter(",").hasNext()) {
			
			fileContent.append(fileScanner.next() + ",");
			
		}
		oscarStrings = fileContent.toString().trim().split("\n");
		
		fileScanner.close();
		/*for (String s: oscarStrings) {
			System.out.print(s);
			String[] tmpString = s.split(",");
			System.out.println(tmpString[2].trim().toLowerCase());
			break;
		}*/
	}
	
	/** this method does two things. First, it searches for the searchString in oscarStrings. 
	 * If it is found, it returns either the string 'Actor' or 'Movie' depending on 
	 * whether the searchString is the name of an actor or a movie. 
	 * Second, if initializes the array resultStrings with the found actor's movies, or the actors in the found movie.
	 * If the searchString is not found, it returns null.
	 * Note: the data has actor's name in the 3rd place in the string, and movie's name in the fourth place in the string.
	 * Note: the data has extra white-spaces between commas and text.
	 * Note: the user may enter correct name but in small or upper case. The search should be case-insensitive 
	 */	
	String searchActorOrMovie() {
		//write your code here.
		String ActorOrMovie = null;
		String result = "";
		for (String s: oscarStrings) {
			String[] tmpString = s.split(",");
			try {
				if (tmpString[2].trim().toLowerCase().equals(searchString.toLowerCase())) {
					searchString = tmpString[2].trim();
					if (result == "") {
						result += tmpString[3];
					}
					else {
					result += ";" + tmpString[3];
					}
					ActorOrMovie = "Actor";
				}
				if (tmpString[3].trim().toLowerCase().equals(searchString.toLowerCase())) {
					searchString = tmpString[3].trim();
					if (result == "") {
						result += tmpString[2];
					}
					else {
						result += ";" + tmpString[2];
					}
					ActorOrMovie = "Movie";
				}
			} catch (Exception e) {
				
			}
			
		}
		
		if (result != "") {
			
			resultStrings = result.split(";");
		}
		return ActorOrMovie;
	}
	
	/**printOutput() takes actorOrMovie as returned by searchActorOrMovie as a parameter. 
	 * It accordingly prints the output as shown in the problem description. 
	 */
	void printOutput(String actorOrMovie)  {
		//write your code here.
		
		//divide this into two part: append the list of resultStrings; print out
		if (actorOrMovie == null) {
			System.out.println("Sorry! No match found!");
		}
		else if (actorOrMovie == "Actor"){
			System.out.println("Actor: " + searchString);
			System.out.println("Movies by this actor");
			for (int i = 0; i < resultStrings.length; i++) {
				System.out.println(Integer.toString(i) + ". " + resultStrings[i]);
			}
		}
		else if (actorOrMovie == "Movie") {
			System.out.println("Movie: " + searchString);
			System.out.println("Actors in this movie");
			for (int i = 0; i < resultStrings.length; i++) {
				System.out.println(Integer.toString(i) + ". " + resultStrings[i]);
			}
		}
		
	}
}
