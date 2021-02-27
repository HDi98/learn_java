package lab2;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestOscarAnalyzer {

	static OscarAnalyzer oscarAnalyzer; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		oscarAnalyzer = new OscarAnalyzer();
		oscarAnalyzer.loadData();
	}

	@Test
	public void testLoadData() {
		assertEquals("Test data length", 100, oscarAnalyzer.oscarStrings.length);
	}
	
	@Test
	public void testFoundActor() {
		oscarAnalyzer.searchString = "matt damon";
		assertEquals("Test actor", "Actor", oscarAnalyzer.searchActorOrMovie());
	}
	
	@Test
	public void testMoviesOfFoundActor() {
		oscarAnalyzer.searchString = "Morgan Freeman";
		oscarAnalyzer.searchActorOrMovie();
		String[] results = oscarAnalyzer.resultStrings;
		assertEquals("Test number of movies", 2, results.length);
		assertEquals("Test 1st movie", true, results[0].trim().equalsIgnoreCase("Million Dollar Baby"));
		assertEquals("Test 2nd movie", true, results[1].trim().equalsIgnoreCase("Invictus"));
	}
	
	@Test
	public void testFoundMovie() {
		oscarAnalyzer.searchString = "Ali";
		assertEquals("Test movie", "Movie", oscarAnalyzer.searchActorOrMovie());
	}
	
	@Test
	public void testActorsInFoundMovie() {
		oscarAnalyzer.searchString = "Ali";
		oscarAnalyzer.searchActorOrMovie();
		String[] results = oscarAnalyzer.resultStrings;
		assertEquals("Test number of actors", 2, results.length);
		assertEquals("Test 1st actor", true, results[0].trim().equalsIgnoreCase("Will Smith"));
		assertEquals("Test 2nd movie", true, results[1].trim().equalsIgnoreCase("Jon Voight"));
	}
	
	@Test
	public void testNotFound() {
		oscarAnalyzer.searchString = "xyz";
		assertEquals("Test not found", null, oscarAnalyzer.searchActorOrMovie());
		
	}
}
