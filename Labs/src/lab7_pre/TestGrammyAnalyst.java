package lab7_pre;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestGrammyAnalyst {
	
	static GrammyAnalyst grammyAnalyst;
	
	@BeforeClass
	public static void setupClass() {
		grammyAnalyst = new GrammyAnalyst();
		grammyAnalyst.loadNominations();
		grammyAnalyst.loadGrammyMap();
		grammyAnalyst.loadArtists();
	}

	/******** test nominations **************/
	@Test
	public void test1_NominationsSize() {
		assertEquals("Test nominations size", 106, grammyAnalyst.nominations.size());
	}
	
	/******** test grammyMap **************/

	@Test
	public void test2_GrammyMapSize() {
		assertEquals("Test grammyMap size", 87, grammyAnalyst.grammyMap.size());
	}
	@Test
	public void test3_grammyMapContainsKey() {
		assertEquals("Test grammyMap contains key", true, grammyAnalyst.grammyMap.containsKey("adele"));
	}
	@Test
	public void test4_grammyMapContainsValues() {
		assertEquals("Test grammyMap contains key", 4, grammyAnalyst.grammyMap.get("adele").size());
	}
	
	/******** test artists **************/
	
	@Test
	public void test5_ArtistsSize() {
		assertEquals("Test artists size", 87, grammyAnalyst.artists.size());
	}
	@Test
	public void test6_artistsHighestNominations() {
		Collections.sort(grammyAnalyst.artists);
		assertEquals("Test Beyonce has max records", 6, grammyAnalyst.artists.get(0).nominations.size());
	}

}
