package exam2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestMediaLib {
	static MediaLib mediaLib;

	@BeforeAll
	public static void setupClass() {
		mediaLib = new MediaLib();
		mediaLib.loadMedia("Media.txt");
	}

	@Test
	void testMediaLength() {
		assertEquals(10, mediaLib.media.length);
	}

	@Test
	void testMovieContent() {
		for (Media m : mediaLib.media) {
			switch (m.title.toLowerCase().trim()) {
			case "inception": {
				assertTrue (m instanceof Movie);
				assertEquals ("2010", m.year);
				assertEquals ("christopher nolan", ((Movie)m).director.toLowerCase().trim());
				assertEquals (148, ((Movie)m).duration);
				break;
			}
			case "selma": {
				assertTrue (m instanceof Movie); 
				assertEquals ("2014", m.year);
				assertEquals ("ava duvernay", ((Movie)m).director.toLowerCase().trim());
				assertEquals (128, ((Movie)m).duration);
				break;
			}
			default: break;
			}
		}
	}

	@Test
	void testBookContent() {
		for (Media m : mediaLib.media) {
			switch (m.title.toLowerCase().trim()) {
			case "post-truth": {
				assertTrue (m instanceof Book); 
				assertEquals ("2018", m.year);
				assertEquals ("lee mcintyre", ((Book)m).author.toLowerCase().trim());
				assertEquals (240, ((Book)m).pages);
				break;
			}
			case "the death of democracy": {
				assertTrue(m instanceof Book);
				assertEquals ("2018", m.year);
				assertEquals ("benjamin carter hett", ((Book)m).author.toLowerCase().trim());
				assertEquals (304, ((Book)m).pages);
			}
			default: break;
			}
		}
	}

	@Test
	void testEBookContent() {
		for (Media m : mediaLib.media) {
			switch (m.title.toLowerCase().trim()) {
			case "how the mind works": {
				assertTrue(m instanceof EBook);
				assertEquals ("1997", m.year);
				assertEquals ("pdf", ((EBook)m).format.toLowerCase().trim());
				break;
			}
			case "the cave and the light": {
				assertTrue(m instanceof EBook);
				assertEquals ("2014", m.year);
				assertEquals ("txt", ((EBook)m).format.toLowerCase().trim());
				break;
			}
			default: break;
			}
		}
	}

	@Test
	void testEBookDownload() {
		for (Media m : mediaLib.media) {
			switch (m.title.toLowerCase().trim()) {
			case "how the mind works": {
				assertEquals (2, ((EBook)m).download());
				break;
			}
			case "the cave and the light": {
				assertEquals (3, ((EBook)m).download());
				break;
			}
			default: break;
			}
		}
	}

	@Test
	void testMovieEnjoy() {
		for (Media m : mediaLib.media) {
			switch (m.title.toLowerCase().trim()) {
			case "inception": {
				assertEquals (148, ((Movie)m).enjoy());
				break;
			}
			case "selma": {
				assertEquals (128, ((Movie)m).enjoy());
				break;
			}
			default: break;
			}
		}
	}

	@Test
	void testBookEBookEnjoy() {
		for (Media m : mediaLib.media) {
			if (m instanceof Book ) {
				assertEquals (((Book)m).pages * Book.PAGE_READING_TIME, ((Book)m).enjoy());
			}
		}
	}
}
