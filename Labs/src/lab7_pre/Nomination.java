package lab7_pre;

public class Nomination implements Comparable<Nomination>{

	String category;
	String title;
	String artist;
	
	Nomination(String category, String title, String artist){
		this.category = category;
		this.title = title;
		this.artist = artist;
	}
	
	@Override
	public int compareTo(Nomination o) {
		// TODO Auto-generated method stub
		return this.artist.toLowerCase().compareTo(o.artist.toLowerCase());
	}
}
