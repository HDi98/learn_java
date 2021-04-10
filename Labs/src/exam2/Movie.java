//name: Haonan Di
//andrew id: hdi
package exam2;

public class Movie extends Media{

	String director;
	int duration;
	Movie(String title, String year, String director, int duration) {
		super(title, year);
		this.director = director;
		this.duration = duration;
	}

	@Override
	int enjoy() {
		
		System.out.println("Watching movie: " + title + " by " + director + " for next " + duration + " minutes");
		return duration;
	}

}
