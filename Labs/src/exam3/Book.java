//name: Haonan Di
//Andrew id: hdi
package exam3;

import java.util.List;
import java.util.Objects;

public class Book implements Comparable<Book>{

	String title;
	List<String> authors;
	
	Book(String title){
		this.title = title;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return this.title.toLowerCase().equals(other.title.toLowerCase());
	}


	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		return this.title.toLowerCase().compareTo(o.title.toLowerCase());
	}

}
