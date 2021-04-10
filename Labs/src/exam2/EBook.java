//name: Haonan Di
//andrew id: hdi
package exam2;

public class EBook extends Book implements Downloadable{

	String format;
	int size;
	EBook(String title, String year, String author, int pages, String format, int size) {
		super(title, year, author, pages);
		this.format = format;
		this.size = size;
	}

	@Override
	public int download() {
		// TODO Auto-generated method stub
		System.out.println("Downloading eBook: " + title + " by " + author + " for next " + (int) ((float)size/(float)Downloadable.INTERNET_SPEED) +" seconds");
		return (int) ((float)size/(float)INTERNET_SPEED);
	}
	
	@Override
	public int enjoy() {
		System.out.println("Reading eBook: "+ title + " by " + author +" for next " + pages*Book.PAGE_READING_TIME + " minutes");
		return pages*PAGE_READING_TIME;
	}

}
