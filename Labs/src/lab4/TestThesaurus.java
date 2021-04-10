//name: Haonan Di
//andrew id: hdi
package lab4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestThesaurus {
	
	static WordWhiz wordWhiz = new WordWhiz();
	
	@BeforeClass 
	public static void setupClass() {
		wordWhiz.loadReferences();
	}
		
	/******************* Test Thesaurus data length ****************/
	@Test
	public void testThesaurusWordDataLength() {
		assertEquals(1387, wordWhiz.wordReferences[0].wordData.length);
	}
	
	/************* Test WordWhiz getMeaning *************/
	@Test
	public void testWordWhizGetMeaning() {
		assertEquals(3, wordWhiz.getMeanings(1, "achieve").length);
	}
	
	/***************** Test Thesaurus lookup **************/
	@Test
	public void testThesaurusWordFoundCount() {
		String[] meanings = wordWhiz.wordReferences[0].lookup("achieve");
		assertEquals(3, meanings.length);
	}
	
	@Test
	public void testThesaurusWordFoundContent() {
		String[] meanings = wordWhiz.wordReferences[0].lookup("achieve");
		boolean found = false;
		for (String meaning: meanings) {
			if (meaning.trim().equalsIgnoreCase("accomplish"))
				found = true;
		}
		assertTrue(found);
	}
	
	@Test
	public void testThesaurusWordNotFound() {
		assertTrue(wordWhiz.wordReferences[0].lookup("abc") == null);
	}
}