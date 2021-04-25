//name: haonan di
//andrew id: hdi
package hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestTwister {
	
	static Twister twister;
	static TwisterRound twisterRound;
	
	@BeforeClass
	public static void setupClass() {
		twister = new Twister();
		twisterRound = new TwisterRound();
		twisterRound.setPuzzleWord("alleged");
		String[] solutionWords = {"age", "ale", "all", "allege", "alleged", "deal", "gal", "gale", "gall", "gee", "gel", "glee", "lad", "lag", "lead", "led", "legal"};
		
		List<String> solutionWordsList = new ArrayList<>();
		solutionWordsList.addAll(Arrays.asList(solutionWords));
		twisterRound.setSolutionWordsList(solutionWordsList);
		for (String word : solutionWordsList) {
			twisterRound.setSolutionListsByWordLength(word);
		}
	}
	
	//this is a self-test for the setupClass method above.
	@Test
	public void testSolutionWordsCount() {
		assertEquals(17, twisterRound.getSolutionWordsList().size());
	}
	
	//test if it made five lists for words with lengths of 3, 4, 5, 6, 7
	@Test
	public void testGameListsCount() {
		assertEquals(5, twisterRound.getSolutionListsByWordLength().size()); 
	}
	
	//test length of all five lists
	@Test
	public void testGameListsLengths() {
		assertEquals(9, twisterRound.getSolutionListsByWordLength(0).size() );  //3 letters
		assertEquals(5, twisterRound.getSolutionListsByWordLength(1).size() );  //4 letters
		assertEquals(1, twisterRound.getSolutionListsByWordLength(2).size() );  //5 letters	
		assertEquals(1, twisterRound.getSolutionListsByWordLength(3).size() );  //6 letters	
		assertEquals(1, twisterRound.getSolutionListsByWordLength(4).size() );  //7 letters
	}
	
	//test contents of words with 3 letters
	@Test
	public void testGameLists0Content() {
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("age"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("ale"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("all"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("gal"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("gee"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("gel"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("lad"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("lag"));
		assertTrue(twisterRound.getSolutionListsByWordLength(0).contains("led"));
	}
	
	//test contents of words with 4 letters
	@Test
	public void testGameLists1Content() {
		assertTrue(twisterRound.getSolutionListsByWordLength(1).contains("deal"));
		assertTrue(twisterRound.getSolutionListsByWordLength(1).contains("gale"));
		assertTrue(twisterRound.getSolutionListsByWordLength(1).contains("gall"));
		assertTrue(twisterRound.getSolutionListsByWordLength(1).contains("glee"));
		assertTrue(twisterRound.getSolutionListsByWordLength(1).contains("lead"));
	}

	//test contents of words with 5 letters
	@Test
	public void testGameLists2Content() {
		assertTrue(twisterRound.getSolutionListsByWordLength(2).contains("legal"));
	}
	
	//test contents of words with 6 letters
	@Test
	public void testGameLists3Content() {
		assertTrue(twisterRound.getSolutionListsByWordLength(3).contains("allege"));
	}
	
	//test contents of words with 7 letters
	@Test
	public void testGameLists4Content() {
		assertTrue(twisterRound.getSolutionListsByWordLength(4).contains("alleged"));
	}
}
