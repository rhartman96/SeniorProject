import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * Linear search
 */

public class BinarySearchReference2 {
	
	/*
	 * Search for an integer that is passed in and return the index of it.
	 */
	public static int search(ArrayList<Integer> arr, int v) {
		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i) == v) {
				//found it!
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while(sc.hasNextInt()) {
			arr.add(sc.nextInt());
		}
		int i = search(arr, v);
		if (i==-1) {
	         System.out.printf ("%d not found%n", v);
	    }else {
	         System.out.printf ("%d found at index %d%n", v, i);
	    }
	}
}
