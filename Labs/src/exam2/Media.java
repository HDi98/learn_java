//name: Haonan Di
//andrew id: hdi
package exam2;

public abstract class Media {

	String title;
	String year;
	abstract int enjoy();
	
	Media(String title, String year){
		this.title = title;
		this.year = year;
	}
}
