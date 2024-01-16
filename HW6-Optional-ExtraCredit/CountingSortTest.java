package HW6;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CountingSortTest {

	@Test
	public void sortTest1() {
		// Generic test case with multiple, unsorted elements.
		int[] A = {2,5,3,0,2,3,0,3};
		int[] sortedA = CountingSort.sort(A);
		int[] expectedSortedA = {0,0,2,2,3,3,3,5};
		
		assertEquals(Arrays.toString(expectedSortedA), Arrays.toString(sortedA));
	}

	@Test
	public void sortTest2() {
		// Generic test case with multiple, unsorted elements.
		int[] B = {3,4,2,7,2,0,7,8,10,0};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {0,0,2,2,3,4,7,7,8,10};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test
	public void sortTest3() {
		// Generic test case with multiple, unsorted elements.
		int[] B = {7,5,21,65,64,65,22,84,10,12,14,11,14,84};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {5,7,10,11,12,14,14,21,22,64,65,65,84,84};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test
	public void sortTest4() {
		// Generic test case with multiple, unsorted elements.
		int[] B = {1,5,2,7,8,10,9,4,10,3,6,7,9};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {1,2,3,4,5,6,7,7,8,9,9,10,10};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test
	public void sortTest5() {
		// Generic test case with multiple, unsorted elements.
		int[] B = {10,75,100,50,78,25,15,28,30,57,89,20,10};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {10,10,15,20,25,28,30,50,57,75,78,89,100};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test
	public void sortTest6() {
		// Only one element in array.
		int[] B = {0};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {0};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test
	public void sortTest7() {
		// Multiple elements, but already sorted.
		int[] B = {0,1,2,3,4,5};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {0,1,2,3,4,5};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test
	public void sortTest8() {
		// Multiple elements, but in decreasing order.
		int[] B = {5,4,3,2,1,0};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {0,1,2,3,4,5};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test
	public void sortTest9() {
		// Multiple of the same value.
		int[] B = {7,7,7,7,7,7,7};
		int[] sortedB = CountingSort.sort(B);
		int[] expectedSortedB = {7,7,7,7,7,7,7};
		
		assertEquals(Arrays.toString(expectedSortedB), Arrays.toString(sortedB));
	}
	
	@Test(expected = NullPointerException.class)
	public void sortTestEmpty() {
		// No elements in array.
		int[] C = {};
		CountingSort.sort(C);
	}
	
}
