package anagrams;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

public class AnagramsTest {

	@Test
	public void buildLetterTableTest() {
		// Checking characters' respective Integer values (key value pairs).
		Anagrams a = new Anagrams();
		assertEquals((Integer)2, a.letterTable.get('a'));
		assertEquals((Integer)3, a.letterTable.get('b'));
		assertEquals((Integer)5, a.letterTable.get('c'));
		assertEquals((Integer)7, a.letterTable.get('d'));
		assertEquals((Integer)11, a.letterTable.get('e'));
		assertEquals((Integer)61, a.letterTable.get('r'));
		assertEquals((Integer)83, a.letterTable.get('w'));
		assertEquals((Integer)89, a.letterTable.get('x'));
		assertEquals((Integer)97, a.letterTable.get('y'));
		assertEquals((Integer)101, a.letterTable.get('z'));
	}
	
	@Test
	public void maxEntriesTest1() {
		// Test using example given in HW document.
		Anagrams a = new Anagrams();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals(maxEntries.toString(), "[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]");
	}
	
	@Test
	public void maxEntriesTest2() {
		// Adding only anagrams of one set of characters.
		Anagrams a = new Anagrams();
		a.addWord("abc");
		a.addWord("bca");
		a.addWord("cba");
		a.addWord("bac");
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals(a.anagramTable.toString(), "{30=[abc, bca, cba, bac]}");
		assertEquals(maxEntries.toString(), "[30=[abc, bca, cba, bac]]");
	}
	
	@Test
	public void maxEntriesTest3() {
		// Adding various words; only two anagrams.
		Anagrams a = new Anagrams();
		a.addWord("desserts");
		a.addWord("stressed");
		a.addWord("word");
		a.addWord("clock");
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals(a.anagramTable.toString(), "{1665727=[word], 1347725=[clock], 1103306056391=[desserts, stressed]}");
		assertEquals(maxEntries.toString(), "[1103306056391=[desserts, stressed]]");
	}
	
	@Test
	public void maxEntriesTest4() {
		// Adding various words; no anagrams of one another.
		Anagrams a = new Anagrams();
		a.addWord("dessert");
		a.addWord("stressed");
		a.addWord("word");
		a.addWord("clock");
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals(a.anagramTable.toString(), "{1665727=[word], 16467254573=[dessert], 1347725=[clock], 1103306056391=[stressed]}");
		assertEquals(maxEntries.toString(), "[1665727=[word], 16467254573=[dessert], 1347725=[clock], 1103306056391=[stressed]]");
	}
	
	@Test
	public void maxEntriesTest5() {
		// Adding the same word multiple times.
		Anagrams a = new Anagrams();
		a.addWord("am");
		a.addWord("am");
		a.addWord("am");
		a.addWord("am");
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals(a.anagramTable.toString(), "{82=[am]}");
		assertEquals(maxEntries.toString(), "[82=[am]]");
	}
	
	@Test
	public void maxEntriesTest6() {
		// Adding the same word multiple times, along with anagrams.
		Anagrams a = new Anagrams();
		a.addWord("am");
		a.addWord("am");
		a.addWord("am");
		a.addWord("am");
		a.addWord("act");
		a.addWord("cat");
		a.addWord("below");
		a.addWord("tac");
		a.addWord("elbow");
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals(a.anagramTable.toString(), "{82=[am], 710=[act, cat, tac], 4763121=[below, elbow]}");
		assertEquals(maxEntries.toString(), "[710=[act, cat, tac]]");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void maxEntriesTest7() {
		// Adding an empty string; should throw exception.
		Anagrams a = new Anagrams();
		a.addWord("am");
		a.addWord("act");
		a.addWord("");
	}

	@Test
	public void myHashCodeTests() {
		// Checking hash codes to make sure myHashCode gives the expected hash code values.
		Anagrams a = new Anagrams();
		assertEquals(a.myHashCode("abhorred"), 1535158086);
		assertEquals(a.myHashCode("abysmal"), 118307796);
		assertEquals(a.myHashCode("foret"), 29108651);
		assertEquals(a.myHashCode("nascent"), 967526230);
		assertEquals(a.myHashCode("parcel"), 13158310);
		assertEquals(a.myHashCode("spice"), 4492015);
		assertEquals(a.myHashCode("thorp"), 204981899);
		assertEquals(a.myHashCode("widow"), 52129063);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void myHashCodeExceptionTest() {
		// Getting the hash code for an empty string.
		Anagrams a = new Anagrams();
		a.myHashCode("am");
		a.myHashCode("act");
		a.myHashCode("");
	}
	
}
