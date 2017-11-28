import java.util.ArrayList;
import java.util.Scanner;

public final class Selection {
	
	public static void sort (ArrayList<Integer> data) {
		int arrayLength = data.size();
		for (int i = 0; i < arrayLength-1; i++) {
			for (int j = i+1; j < arrayLength; j++) {
					int numberAtJ = data.get(j);
					int numberAtI = data.get(i);
					if (numberAtJ < numberAtI) {
					data.set(j, numberAtI);
					data.set(i, numberAtJ);
				}
			}
		}
	}
	
	public static void main (final String[] args) {
		final ArrayList data = new ArrayList<> ();
		try (final Scanner stdin = new Scanner (System.in)) {
			while (stdin.hasNextInt()) data.add(stdin.nextInt());
		}
		sort (data);
		System.out.println(data);
	}
}
