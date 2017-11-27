import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchI {

	public static int binarySearch(int target, ArrayList<Integer> list) {

		int low = 0;
		int high = list.size() - 1;
		
		while (low <= high) {
			
			if (low >= high && list.get(low) != target) {
				// it is not in the list
				return -1;
			}

			int midpoint = (low + high) / 2;

			if (list.get(midpoint) == target) {
				return midpoint;
			} else if (list.get(midpoint) < target) {
				// bring lower bound up
				low = midpoint + 1;
			} else if (list.get(midpoint) > target) {
				// bring higher bound down
				high = midpoint; // BUG
			}			
		}

		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		final ArrayList<Integer> data  = new ArrayList<> (args.length);
		int v;
		try (final Scanner stdin = new Scanner (System.in)) {
			v = stdin.nextInt();
			while (stdin.hasNextInt()) data.add (stdin.nextInt());
		}

		final int i = binarySearch (v, data);
		if (i==-1) {
			System.out.printf ("%d not found%n", v);
		} else {
			System.out.printf ("%d found at index %d%n", v, i);
		}
		System.out.close();
	}

}
