import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchR {

	public static int binarySearch(int low, int high, final int target, ArrayList<Integer> list) {

		if (low >= high && list.get(low) != target) {
			// it is not in the list
			return -1;
		}

		int midpoint = (low + high) / 2;

		if (list.get(midpoint) == target) {
			return midpoint;
		} else if (list.get(midpoint) < target) {
			// bring lower bound up
			return binarySearch(midpoint+1, high, target, list);
		} else {
			// bring higher bound down
			return binarySearch(low, midpoint-1, target, list);
		}

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ArrayList<Integer> data  = new ArrayList<> (args.length);
		int v;
		try (final Scanner stdin = new Scanner (System.in)) {
			v = stdin.nextInt();
			while (stdin.hasNextInt()) data.add (stdin.nextInt());
		}

		final int i = binarySearch (0, data.size()-1, v, data);
		if (i==-1) {
			System.out.printf ("%d not found%n", v);
		} else {
			System.out.printf ("%d found at index %d%n", v, i);
		}
		System.out.close();
	}

}
