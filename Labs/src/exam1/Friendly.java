// name: Haonan Di
// andrew id: hdi
package exam1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Friendly {

	String[] persons;
	String[][] personFriends;

	public static void main(String[] args) {
		Friendly friendly = new Friendly();
		friendly.readFriends("friends.txt");
		friendly.getInputOutput();
	}

	//do not change this method
	void getInputOutput() {
		int choice = 0;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("*** Welcome to Friendly! ***");
			System.out.println("1. Find the number of friends a person has");
			System.out.println("2. Find the number of common friends between two persons");
			System.out.println("3. Find the names of common friends between two persons");
			System.out.println("4. Exit");
			choice = input.nextInt();
			input.nextLine(); //clear the buffer
			switch (choice) {
			case 1: {
				System.out.println("Enter the person's name"); 
				String name = input.nextLine();
				String[] friends = findFriends(name);
				if (friends != null) {
					System.out.printf("%s has %d friends%n", name, friends.length);
					int count = 0;
					for (String s : friends ) {
						System.out.println(++count + ". " + s );
					}
				} else System.out.println("Sorry! No friends found!");
				System.out.println("-----------------------------");
				break;
			}
			case 2: {
				System.out.println("Enter first person's name");
				String name1 = input.nextLine();
				System.out.println("Enter second person's name");
				String name2 = input.nextLine();
				System.out.printf("%s and %s have %d common friends%n", name1, name2, countCommonFriends(name1, name2));
				System.out.println("-----------------------------");
				break;
			}
			case 3: {
				System.out.println("Enter first person's name");
				String name1 = input.nextLine();
				System.out.println("Enter second person's name");
				String name2 = input.nextLine();
				String[] commonFriends = findCommonFriends(name1, name2);
				if (commonFriends != null) {
					System.out.printf("%s and %s have %d common friends%n", name1, name2, commonFriends.length);
					int count = 0;
					for (String s : commonFriends) {
						System.out.println(++count + ". " + s);
					}
				} else System.out.println("Sorry! No match found!");
				System.out.println("-----------------------------");
				break;
			}
			default: System.out.println("Goodbye!");break;
			}
		} while (choice != 4);
		input.close();
	}


	/** readFriends() reads friends.txt and 
	 * populates persons and personFriends arrays
	 */
	void readFriends(String filename) {
		//write your code here
		try {
			Scanner input = new Scanner(new File(filename));
			StringBuilder fileContent = new StringBuilder();
			while (input.hasNextLine()) {
				fileContent.append(input.nextLine() + "\n");
			}
			String[] friendRows = fileContent.toString().split("\n");
			personFriends = new String[friendRows.length][];
			persons = new String[friendRows.length];
			for (int i = 0; i < friendRows.length; i++) {
				persons[i] = friendRows[i].split(":")[0];
				personFriends[i] = friendRows[i].split(",");
			}
			// process the first one of person Friends
			for (int i = 0; i < friendRows.length; i++) {
				personFriends[i][0] = personFriends[i][0].split(":")[1];
			}
			
			// trim the stored data
			for (int i = 0; i < friendRows.length; i++) {
				for (int j = 0; j < personFriends[i].length; j ++) {
					personFriends[i][j] = personFriends[i][j].trim();
				}
			}
			
			//test 
			/*for (int i = 0; i < friendRows.length; i++) {
				System.out.print(persons[i] + ": ");
				for (int j = 0; j < personFriends[i].length; j ++) {
					System.out.print(personFriends[i][j] + ", ");
				}
				System.out.print("\n");
			} */
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** given a name, returns an array of friends a person has
	 * If the name is not found, it returns null
	 */
	String[] findFriends(String name) {
		//write your code here
		for(int i = 0; i < persons.length; i++) {
			if (name.toLowerCase().trim().equals(persons[i].toLowerCase()) ) {
				return personFriends[i];
			}
		}
		return null;
	}

	/** given two names, returns how many common friends they have */
	int countCommonFriends(String name1, String name2) {
		//write your code here
		int count1 = 0;
		for (int i = 0; i < persons.length; i++) {
			if (name1.toLowerCase().equals(persons[i].toLowerCase())) {
				for (int j = 0; j < persons.length; j++) {
					if (name2.toLowerCase().equals(persons[j].toLowerCase())) {
						for (int m = 0; m < personFriends[i].length; m++) {
							for (int n = 0; n < personFriends[j].length; n++) {
								if (personFriends[i][m].equals(personFriends[j][n])) {
									count1 ++;
								}
							}
						}
					}
				}
			}
		}
		return count1;
	}

	/**given two names, returns an array of names of common friends. 
	 * If there are no common friends, then it returns an empty array, i.e. array of size 0*/
	String[] findCommonFriends(String name1, String name2) {
		//write your code here
		StringBuilder commonlst = new StringBuilder();
		for (int i = 0; i < persons.length; i++) {
			if (name1.toLowerCase().equals(persons[i].toLowerCase()) ) {
				for (int j = 0; j < persons.length; j++) {
					if (name2.toLowerCase().equals(persons[j].toLowerCase())) {
						for (int m = 0; m < personFriends[i].length; m++) {
							for (int n = 0; n < personFriends[j].length; n++) {
								if (personFriends[i][m].equals(personFriends[j][n])) {
									commonlst.append(personFriends[i][m] + ",");
								}
							}
						}
					}
				}
			}
		}
		String [] returnlst = commonlst.toString().split(",");
		if (returnlst.length == 1 && returnlst[0].equals("")) {
			return new String[0];
		}
		return returnlst;
	}

}
