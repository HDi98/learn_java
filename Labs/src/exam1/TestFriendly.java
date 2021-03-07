package exam1;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFriendly {
	
	static String[] persons = {"Alice", "Bob", "Charles", "David", "Frank"};
	static String[][] personFriends = 
		{{"Bob", "Joe", "Sara", "Amy", "Frank", "Nancy"}, 				//Alice's friends
		{"Alice", "Nancy", "Peter", "Steve", "Frank", "Tim", "Amy"}, 	//Bob's friends
		{"Sara", "Kevin", "Peter", "Steve"}, 							//Charles friends
		{"Steve", "Amy"}, 												//David's friends
		{"Alice", "Bob", "Mary"}};										//Frank's friends

	static Friendly friendFinder;
	
	@BeforeClass
	public static void setupClass() {
		friendFinder = new Friendly();
		friendFinder.readFriends("friends.txt");
	}
	
	@Test
	public void test1_readFriends_personsLength() {
		assertEquals(persons.length, friendFinder.persons.length);
	}
	
	@Test
	public void test2_readFriends_personFriendsLength() {
		assertEquals(personFriends.length, friendFinder.personFriends.length);
	}
	
	@Test
	public void test3_readFriends_personNames() {
		assertEquals("Alice", friendFinder.persons[0]);
		assertEquals("Bob", friendFinder.persons[1]);
		assertEquals("Charles", friendFinder.persons[2]);
		assertEquals("David", friendFinder.persons[3]);
		assertEquals("Frank", friendFinder.persons[4]);
	}
	
	@Test
	public void test4_readFriends_personFriendsNamesLength() {
		assertEquals(2, friendFinder.personFriends[3].length);
	}
	
	@Test
	public void test5_readFriends_personFriendsNames() {
		assertEquals("Steve", friendFinder.personFriends[3][0]);
		assertEquals("Amy", friendFinder.personFriends[3][1]);
	}
	
	@Test
	public void test6_countFriends() {
		assertEquals("Test Alice's number of friends" , 5, friendFinder.findFriends("alice").length);
	}
	@Test
	public void test7_countCommonFriends_Zero() {
		assertEquals("Test count zero common friends", 0, friendFinder.countCommonFriends("david", "frank"));
	}
	@Test
	public void test8_countCommonFriends_NonZero() {
		assertEquals("Test count non-zero common friends", 2, friendFinder.countCommonFriends("alice", "bob"));
	}
	@Test
	public void test9_findCommonFriends_Zero() {
		assertEquals("Test find zero common friends", 0, friendFinder.findCommonFriends("david", "frank").length);
	}
	@Test
	public void test10_findCommonFriends_NonZero() {
		assertEquals("Test find non-zero common friends", 2, friendFinder.findCommonFriends("alice", "bob").length);
	}

}
