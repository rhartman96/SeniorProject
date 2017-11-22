import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * Java Collections Correct Binary Search
 */

public class BinarySearchReference1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while(sc.hasNextInt()) {
			arr.add(sc.nextInt());
		}
		int i = Collections.binarySearch(arr, v);
		if (i==-1) {
	         System.out.printf ("%d not found%n", v);
	    }else {
	         System.out.printf ("%d found at index %d%n", v, i);
	    }
	}
}
