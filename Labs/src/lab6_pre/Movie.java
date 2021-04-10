package lab6_pre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie implements Comparable<Movie>{

	String movieName;
	String movieYear;
	List<String> movieGenres;
	
	Movie(String movieName, String movieYear){
		this.movieName = movieName;
		this.movieYear = movieYear;
		movieGenres = new ArrayList<>();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(movieName, movieYear);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return other.movieName.toLowerCase().equals(this.movieName.toLowerCase()) && other.movieYear.equals(this.movieYear);
	}
	
	@Override
	public int compareTo(Movie m) {
		// TODO Auto-generated method stub
		return this.movieName.compareTo(m.movieName);
	}

}
