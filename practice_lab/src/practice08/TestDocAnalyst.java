package practice08;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDocAnalyst {
	DocAnalyst[] docAnalysts = new DocAnalyst[2];
	
	@Before
	public void setUp() throws Exception {
		docAnalysts[0] = new DocAnalyst();
		docAnalysts[1] = new DocAnalyst();
		
		docAnalysts[0].document = new RegularDoc("sample.txt"); 
		docAnalysts[1].document = new CSVDoc("books.csv");
		for (DocAnalyst docAnalyst: docAnalysts) {
			docAnalyst.analyzeDoc(docAnalyst.document.filename);
			docAnalyst.document.collectDocInfo();
		}
	}

	@Test
	public void test1_regularWordCount() {
		assertEquals(24, ((RegularDoc)docAnalysts[0].document).wordCount);
	}
	
	@Test
	public void test2_csvRowCount() {
		assertEquals(6, ((CSVDoc)docAnalysts[1].document).rowCount);
	}
	
	@Test
	public void test3_csvColumnCount() {
		assertEquals(4, ((CSVDoc)docAnalysts[1].document).columnCount);
	}
}
