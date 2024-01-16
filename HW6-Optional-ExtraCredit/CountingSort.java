package HW6;

public class CountingSort {
	
	/**
	 * Sorting algorithm that sorts integer values in an array from least to greatest.
	 * Does not use comparisons (making it more efficient than most sorting algorithms,
	 * given its subsequent runtime of O(n)).
	 * @param A : int[] with numbers to be sorted.
	 * @return An int[] with same elements as A, but sorted from least to greatest.
	 */
	public static int[] sort(int[] A) {
		if(A.length == 0) {
			throw new NullPointerException("ERROR! Array is empty.");
		}
		
		int [] B = new int[A.length];
	
		int aMax = A[0];
		
		for(int i = 0; i < A.length; i++) {
			if(A[i] > aMax) {
				aMax = A[i];
			}
		}
		
		int[] C = new int[Math.abs(aMax) + 1];
		
		int k = C.length;
		
		for(int i = 0; i < k; i++) {
			C[i] = 0;
		}
		
		for(int j = 0; j < A.length; j++) {
			C[A[j]] = C[A[j]] + 1;
		}
		
		for(int i = 1; i < k; i++) {
			C[i] = C[i] + C[i - 1];
		}
		
		for(int j = A.length - 1; j >= 0; j--) {
			B[C[A[j]] - 1] = A[j];
			C[A[j]] = C[A[j]] - 1;
		}
		
		return B;
		
	}
	
}
