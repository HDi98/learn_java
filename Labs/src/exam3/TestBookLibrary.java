package exam3;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBookLibrary {
	static BookLibrary bookLibrary;

	@BeforeClass
	public static void setupClass() {
		bookLibrary = new BookLibrary();
		bookLibrary.readFile("BookAuthors.txt");
		bookLibrary.loadBooksList();
		bookLibrary.loadAuthorsMap();
	}

	@Test
	public void testBookListSize() {
		assertEquals(16, bookLibrary.booksList.size());
	}

	@Test
	public void testBookContentsTitle() {
		assertTrue(bookLibrary.booksList.contains(new Book("Algorithms")));
	}

	@Test
	public void testBookContentsAuthors() {
		Book book = null;
		for (Book b : bookLibrary.booksList) {
			if (b.title.equalsIgnoreCase("Algorithm Design")) {
				book = b; 
				break;
			}
		}
		assertTrue(book != null);
		assertEquals(2, book.authors.size());
	}

	@Test
	public void testAuthorsMapSize() {
		assertEquals(23, bookLibrary.authorsMap.size());
	}

	@Test
	public void testAuthorsMapContentKey() {
		assertTrue(bookLibrary.authorsMap.containsKey("Anany Levitin"));
	}
	
	@Test
	public void testAuthorsMapContentValue() {
		assertEquals(2, bookLibrary.authorsMap.get("Anany Levitin").size());
	}
	
	@Test
	public void testBookHashCode() {
		Book b1 = new Book("ABC");
		Book b2 = new Book("ABC");
		assertEquals(b1.hashCode(), b2.hashCode());
	}
}
