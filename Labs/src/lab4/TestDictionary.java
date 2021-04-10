//name: Haonan Di
//andrew id: hdi
package lab4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestDictionary {
	
	static WordWhiz wordWhiz = new WordWhiz();
	
	@BeforeClass 
	public static void setupClass() {
		wordWhiz.loadReferences();
		
	}
	/******************* Test Dictionary data load ****************/
	
	@Test
	public void testThesaurusWordDataLenght() {
		assertEquals(100, wordWhiz.wordReferences[1].wordData.length);
	}
	
	/************* Test WordWhiz getMeaning *************/
	@Test
	public void testWordWhizGetMeaning() {
		assertEquals(5, wordWhiz.getMeanings(2, "abacus").length);
	}
	
	/***************** Test Dictionary lookup **************/

	@Test
	public void testDictionaryWordFoundCount() {
		String[] meanings = wordWhiz.getMeanings(2, "aband");
		assertEquals(2, meanings.length);
	}
	
	@Test
	public void testDictionaryWordFoundContent() {
		String[] meanings = wordWhiz.getMeanings(2, "aband");
		boolean found = false;
		for (String meaning: meanings) {
			if (meaning.trim().equalsIgnoreCase("To abandon."))
				found = true;
		}
		assertTrue(found);
	}
	
	@Test
	public void testDictionaryWordNotFound() {
		assertTrue(wordWhiz.getMeanings(2, "abc") == null);
	}
	
}