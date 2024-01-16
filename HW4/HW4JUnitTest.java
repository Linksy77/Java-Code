package HW4;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW4JUnitTest {

	@Test
	public void testAdd1() {
		Treap treap1 = new Treap();
		assertEquals(true, treap1.add(4, 19));
	}
	
	@Test
	public void testAdd2() {
		Treap treap1 = new Treap();
		assertEquals(true, treap1.add(4));
	}
	
	@Test
	public void testAdd3() {
		Treap treap1 = new Treap();
		assertEquals(false, treap1.add(null));
	}
	
	@Test
	public void testMultipleAdd1() {
		Treap treap1 = new Treap();
		treap1.add(4,19);
		treap1.add(2,31);
		treap1.add(6,70);
		treap1.add(1,84);
		assertEquals(false, treap1.add(4,19));
	}
	
	@Test
	public void testMultipleAdd2() {
		Treap treap1 = new Treap();
		treap1.add(4,19);
		treap1.add(2,31);
		treap1.add(6,70);
		treap1.add(1,84);
		assertEquals(true, treap1.add(3,12));
	}
	
	@Test
	public void testMultipleAdd3() {
		Treap treap1 = new Treap();
		treap1.add(4,19);
		treap1.add(2,31);
		treap1.add(6,70);
		assertEquals(false, treap1.add(null));
	}
	
	@Test
	public void testFind1() {
		Treap treap1 = new Treap();
		treap1.add(4,19);
		treap1.add(7,21);
		treap1.add(3,70);
		assertEquals(true, treap1.find(4));
	}
	
	@Test
	public void testFind2() {
		Treap treap1 = new Treap();
		treap1.add(6,70);
		treap1.add(4,20);
		treap1.add(10,42);
		treap1.add(2,96);
		assertEquals(false, treap1.find(null));
	}

	@Test
	public void testFind3() {
		Treap treap1 = new Treap();
		treap1.add(6,70);
		treap1.add(4,20);
		treap1.add(10,42);
		treap1.add(2,96);
		assertEquals(false, treap1.find(11));
	}
	
	@Test
	public void testDelete1() {
		Treap treap1 = new Treap();
		treap1.add(6,70);
		treap1.add(4,20);
		treap1.add(10,42);
		treap1.add(2,96);
		assertEquals(true, treap1.delete(10));
	}
	
	@Test
	public void testDelete2() {
		Treap treap1 = new Treap();
		treap1.add(6,70);
		treap1.add(4,20);
		treap1.add(10,42);
		treap1.add(2,96);
		assertEquals(false, treap1.delete(null));
	}
	
	@Test
	public void testDelete3() {
		Treap treap1 = new Treap();
		treap1.add(6,70);
		treap1.add(4,20);
		treap1.add(10,42);
		treap1.add(2,96);
		treap1.add(3,25);
		assertEquals(false, treap1.delete(22));
	}
	
	@Test
	public void testtoString1() {
		Treap treap1 = new Treap();
		treap1.add(4,19); 
		treap1.add(2,31); 
		treap1.add(6,70); 
		treap1.add(1,84);
		treap1.add(3,12); 
		treap1.add(5,83); 
		treap1.add(7,26);
		String expected1 = "(key = 1 , priority = 84)\n"
				+ " null\n"
				+ " (key = 5 , priority = 83)\n"
				+ "  (key = 2 , priority = 31)\n"
				+ "   null\n"
				+ "   (key = 4 , priority = 19)\n"
				+ "    (key = 3 , priority = 12)\n"
				+ "     null\n"
				+ "     null\n"
				+ "    null\n"
				+ "  (key = 6 , priority = 70)\n"
				+ "   null\n"
				+ "   (key = 7 , priority = 26)\n"
				+ "    null\n"
				+ "    null\n";
		assertEquals(expected1, treap1.toString());
	}
	
	public void testtoString2() {
		Treap treap1 = new Treap();
		treap1.add(4,19); 
		treap1.add(2,31); 
		treap1.add(6,70); 
		treap1.add(1,84);
		treap1.add(3,12); 
		treap1.add(5,83); 
		treap1.add(7,26);
		treap1.delete(1);
		String expected1 = "(key = 5 , priority = 83)\n"
				+ " (key = 2 , priority = 31)\n"
				+ "  null\n"
				+ "  (key = 4 , priority = 19)\n"
				+ "   (key = 3 , priority = 12)\n"
				+ "    null\n"
				+ "    null\n"
				+ "   null\n"
				+ " (key = 6 , priority = 70)\n"
				+ "  null\n"
				+ "  (key = 7 , priority = 26)\n"
				+ "   null\n"
				+ "   null ";
		assertEquals(expected1, treap1.toString());
	}
	
	public void testtoString3() {
		Treap treap1 = new Treap();
		treap1.add(4,19); 
		treap1.add(2,31); 
		treap1.add(6,70); 
		treap1.add(1,84);
		treap1.add(3,12); 
		treap1.add(5,83); 
		treap1.add(7,26);
		treap1.delete(1);
		treap1.delete(5);
		String expected1 = "(key = 6 , priority = 83)\n"
				+ " (key = 2 , priority = 31)\n"
				+ "  null\n"
				+ "  (key = 4 , priority = 19)\n"
				+ "   (key = 3 , priority = 12)\n"
				+ "    null\n"
				+ "    null\n"
				+ "   null\n"
				+ " (key = 7 , priority = 26)\n"
				+ "  null\n"
				+ "  null ";
		assertEquals(expected1, treap1.toString());
	}
	
	public void testtoString4() {
		Treap treap1 = new Treap();
		treap1.add(4,19); 
		treap1.add(2,31); 
		treap1.add(6,70); 
		treap1.add(1,84);
		treap1.add(3,12); 
		treap1.add(5,83); 
		treap1.add(7,26);
		treap1.delete(1);
		treap1.delete(5);
		treap1.delete(4);
		String expected1 = "(key = 6 , priority = 83)\n"
				+ " (key = 2 , priority = 31)\n"
				+ "  null\n"
				+ "  (key = 3 , priority = 12)\n"
				+ "   null\n"
				+ "   null\n"
				+ " (key = 7 , priority = 26)\n"
				+ "  null\n"
				+ "  null ";
		assertEquals(expected1, treap1.toString());
	}
	
}
