package lab6_pre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Genre implements Comparable<Genre>{

	String genreName;
	List<Movie> genreMovies;
	
	Genre(String genreName){
		this.genreName = genreName;
		genreMovies = new ArrayList<>();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(genreName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return other.genreName.toLowerCase().equals(this.genreName.toLowerCase());
	}
	
	@Override
	public int compareTo(Genre g) {
		// TODO Auto-generated method stub
		return g.genreMovies.size() - this.genreMovies.size();
	}

}
