//name: Haonan Di
//andrew id: hdi
package lab7;

import java.util.List;

public class Artist implements Comparable<Artist>{

	String name;
	List<Nomination> nominations;
	
	Artist(String name, List<Nomination> nominations){
		this.name = name;
		this.nominations = nominations;
	}

	@Override
	public int compareTo(Artist o) {
		// TODO Auto-generated method stub
		if (o.nominations.size() == this.nominations.size()) {
			return this.name.compareTo(o.name);
		}
		else {
			return o.nominations.size() - this.nominations.size();
		}
		
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + ", nominations=" + nominations + "]";
	}
}
