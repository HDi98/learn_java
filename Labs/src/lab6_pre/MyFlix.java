package lab6_pre;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MyFlix {
	List<Movie> moviesList = new ArrayList<>();
	List<Genre> genresList = new ArrayList<>();
	String[] movieDBStrings;

	//do not change this method
	public static void main(String[] args) {
		MyFlix myFlix = new MyFlix();
		myFlix.movieDBStrings = myFlix.readMovieDB("MoviesDB.tsv");
		myFlix.loadMovies();
		myFlix.loadGenres();
		Collections.sort(myFlix.moviesList);
		Collections.sort(myFlix.genresList);
		myFlix.showMenu();
	}

	//do not change this method
	//showMenu() displays the menu for the user to choose from,
	//takes required inputs, and invokes related methods
	void showMenu() {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("*** Welcome to MyFlix ***"); 
			System.out.println("1. Search for a movie");
			System.out.println("2. List of genres");
			System.out.println("3. Exit");
			choice = input.nextInt();
			input.nextLine();
			switch (choice) {
			case 1: {
				System.out.println("Enter the string to search in movie names");
				String searchString = input.nextLine();
				printSearchResults(findMovies(searchString));
				break;
			}
			case 2: {
				printGenreReport();
				break;
			}
			case 3: System.out.println("Bye Bye!"); break;
			default: break;
			}
		} while (choice != 3);
		input.close();
	}

	//do not change this method
	//readMovieDB reads all data from the MovieDB file
	//and loads each row as a string in movieDBStrings
	String[] readMovieDB(String filename) {
		StringBuilder movies = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNextLine()) {
				movies.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return movies.toString().split("\n");
	}

	//loadMovies use data in movieDBStrings to create Movie objects 
	//and add them to moviesList.
	void loadMovies() {
		//write your code here
		for (String s: movieDBStrings) {
			String[] tmp = s.split("\t");
			Movie newMovie = new Movie(tmp[0], tmp[1]);
			for (int i = 2; i < tmp.length; i++) {
				newMovie.movieGenres.add(tmp[i]);
			}
			moviesList.add(newMovie);
		}
		
	}

	//loadGenres uses data in moviesList to create Genre objects 
	//and add them to genresList
	void loadGenres() {
		//write your code here
		for(Movie m: moviesList) {
			for(String mGenre: m.movieGenres) {
				int sign = 0;
				Genre tmpG = new Genre(mGenre);
				for (Genre g: genresList) {
					if (tmpG.equals(g)){
						g.genreMovies.add(m);
						sign = 1;
						break;
					}
				}
				if (sign == 0) {
					genresList.add(tmpG);
					genresList.get(genresList.size() -1).genreMovies.add(m);
				}
			}	
		}
		//Collections.sort(genresList);
	}

	//findMovies() returns a list of Movie objects 
	//that have the searchString in their names
	List<Movie> findMovies(String searchString) {
		//write your code here
		List<Movie> findMovie = new ArrayList<>();
		for (Movie m: moviesList) {
			String tmpString = m.movieName.toLowerCase();
			if (tmpString.contains(searchString.toLowerCase())) {
				findMovie.add(m);
			}
		}
		//Collections.sort(findMovie);
		return findMovie;
	}

	//print the search output as shown in Fig.2. 
	//You may use this printf statement:System.out.printf("%3d. %-50s\tYear: %s\n", ++count, movie.movieName, movie.movieYear);
	void printSearchResults(List<Movie> searchResults) {
		//write your code here
		for(int i = 0; i < searchResults.size(); i++) {
			System.out.printf("%3d. %-50s\tYear: %s\n", i+1, searchResults.get(i).movieName, searchResults.get(i).movieYear);
		}
	}
	
	//print the genre summary report as shown in Figure 3. 
	//You may use this printf statement:System.out.printf("%3d. %-15s Number of movies: %,6d%n", ++count, genre.genreName, genre.genreMovies.size());
	void printGenreReport() {
		//write your code here
		for(int i = 0; i < genresList.size(); i++) {
			System.out.printf("%3d. %-15s Number of movies: %,6d%n", i+1, genresList.get(i).genreName, genresList.get(i).genreMovies.size());

		}
	}
}
