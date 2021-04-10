package lab5pre;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAnagrammar {
	static Anagrammar ag;

	@BeforeClass
	public static void setupBeforeClass() {
		ag = new Anagrammar();
		ag.loadWords("words.txt");
	}
	
	@Before
	public void setupTest() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		
	}

	@Test
	public void testInDictionaryNoAnagram() {
		ag.findAnagrams("complex");
		assertEquals("Test in dictionary", true, ag.isInDictionary);
		assertEquals("Test has anagrams", false, ag.hasAnagrams);
	}
	
	@Test
	public void testInDictionaryHasAnagram() {
		ag.findAnagrams("free");
		assertEquals("Test in dictionary", true, ag.isInDictionary);
		assertEquals("Test has anagrams", true, ag.hasAnagrams);
	}

	@Test
	public void testNotInDictionaryHasAnagram() {
		ag.findAnagrams("abc");
		assertEquals("Test in dictionary", false, ag.isInDictionary);
		assertEquals("Test has anagrams", true, ag.hasAnagrams);
	}
	
	@Test
	public void testNotInDictionaryNoAnagram() {
		ag.findAnagrams("xyz");
		assertEquals("Test in dictionary", false, ag.isInDictionary);
		assertEquals("Test has anagrams", false, ag.hasAnagrams);
	}
	
	@Test
	public void testWordHasAnagrams() {
		ag.findAnagrams("rope");
		assertEquals(1, ag.anagramArray.length);
	}
	
	@Test
	public void testWordNoAnagrams() {
		ag.findAnagrams("xyz");
		assertEquals(0, ag.anagramArray.length);
		for (String s: ag.anagramArray) System.out.println(s + ".");
	}
	
	@Test
	public void testWordHasAnagramsContent() {
		ag.findAnagrams("free");
		assertTrue(Arrays.asList(ag.anagramArray).contains("reef"));
		assertTrue(Arrays.asList(ag.anagramArray).contains("feer"));
	}
}
