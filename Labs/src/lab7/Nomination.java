//name: Haonan Di
//andrew id: hdi
package lab7;

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
	public String toString() {
		return "Nomination [category=" + category + ", title=" + title + ", artist=" + artist + "]";
	}


	@Override
	public int compareTo(Nomination o) {
		// TODO Auto-generated method stub
		return this.artist.toLowerCase().compareTo(o.artist.toLowerCase());
	}
}
