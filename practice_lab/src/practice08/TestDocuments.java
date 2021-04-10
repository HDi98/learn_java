package practice08;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDocuments {
	Document[] documents = new Document[2];
	
	@Before
	public void setUp() throws Exception {
		documents[0] = new RegularDoc("sample.txt"); 
		documents[1] = new CSVDoc("books.csv");
		for (Document document: documents) {
			document.collectDocInfo();
		}
	}

	@Test
	public void test1_regularWordCount() {
		assertEquals(24, ((RegularDoc)documents[0]).wordCount);
	}
	
	@Test
	public void test2_csvRowCount() {
		assertEquals(6, ((CSVDoc)documents[1]).rowCount);
	}
	
	@Test
	public void test3_csvColumnCount() {
		assertEquals(4, ((CSVDoc)documents[1]).columnCount);
	}
	
}
