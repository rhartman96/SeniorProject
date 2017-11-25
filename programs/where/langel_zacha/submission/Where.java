/*
 * Author:  Zachary Langel, zlangel2014@my.fit.edu
 * Course:  CSE 1002, Fall 2017
 * Project: Extra - Where is Watson?
 */
import java.util.Scanner;
public class Where {
	public static void main (String[] args) {
		Scanner kb = new Scanner(System.in);
		int c = 100000; // c = constraint
		int ai; // will be element in the array at index i
		int m = 0; // will be the index of the array element to be found
		
		int n = kb.nextInt(); // n = array length
		while (n < 1 || n > c) {
			System.out.println("Must be between 1 and 10,000 inclusive, try again.");
			n = kb.nextInt();
		}
		
		int k = kb.nextInt(); // k = number of rotations
		while (k < 1 || k > c) {
			System.out.println("Must be between 1 and 10,000 inclusive, try again.");
			k = kb.nextInt();
		}
		
		int q = kb.nextInt(); // q = number of queries
		while (q < 1 || q > 500) {
			System.out.println("Must be between 1 and 500 inclusive, try again.");
			q = kb.nextInt();
		}
		
		int[] array = new int[n]; // array
		for (int i = 0; i < n; i++){
			ai = kb.nextInt(); // ai is assigned
			while (ai < 1 || ai > c) {
				System.out.println("Must be between 1 and 10,000 inclusive, try again.");
				ai = kb.nextInt();
			}
			array[i] = ai;
		}
		
		int[] queries = new int[q]; // array of the queries
		for (int i = 0; i < q; i++) {
			m = kb.nextInt(); // m is assigned
			while (m < 0 || m >= n) {
				System.out.println("Must be between 0 and the array length - 1 inclusive, try again.");
				m = kb.nextInt();
			}
			queries[i] = m;
		}
		
		int[] arrayB = new int[n];
		for (int i = 0; i < k; i++) {		// loop for number of rotations
			for (int B = 0; B < n; B++) {	// loop for saving array's old values
				arrayB[B] = array[B];
			}
			for (int j = 0; j < n; j++) {	// loop for swapping array's values
				if (j == (0)){
					array[j] = arrayB[n-1];
				} else {
					array[j] = arrayB[j-1]; 
				}
			}
		}
		
		System.out.println();
		for (int i = 0; i < q; i++) {
			System.out.println(array[queries[i]]);
		}
	}
}
