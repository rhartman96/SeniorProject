import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * When searching for the element, this search algorithm goes the wrong direction in the sorted array.
 */

public class BinarySearchReference7 {
	
	/*
	 * Search for an integer that is passed in and return the index of it.
	 */
	public static int search(ArrayList<Integer> arr, int v) {
		return searchHelper(arr,0, arr.size(), v);
	}
	
	public static int searchHelper(ArrayList<Integer> arr, int start, int end, int v) {
		//System.out.println("Start: " + start + ", end: " + end);
		if(start >= 0 && arr.get(start) == v) {
			return start;
		}
		if(end < arr.size() && arr.get(end) == v) {
			return end;
		}
		if(end-start <= 0) {
			//list is empty, can't find the element
			return -1;
		}
		
		int mid = ((end-start) / 2) + start;
		if(v == arr.get(mid)) {
			//we found it!
			return mid;
		}else if(v > arr.get(mid)) {
			//its left of mid
			return searchHelper(arr, start, mid-1, v);
		}
		//its right of mid
		return searchHelper(arr, mid+1, end, v);
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
