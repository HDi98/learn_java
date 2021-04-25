package lab7_pre;

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
		return o.nominations.size() - this.nominations.size();
	}
}
