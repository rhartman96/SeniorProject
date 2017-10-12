import java.util.ArrayList;
import java.util.Scanner;

public final class Selection {
   public static void sort (ArrayList data) {
		int l = data.size();
		int a = 0;
		int b = 0;
		for (int i = 0; i < l; i++) {
			a = (int)data.get(i);
			for (int j = i; j < l; i++) {
				b = (int)data.get(j);
				if (a < b) {
					data.set(a,b);
				}
			}
		}	
   }

   public static void main (final String [] args) {
      final ArrayList data  = new ArrayList<> ();
      try (final Scanner stdin = new Scanner (System.in)) {
            while (stdin.hasNextInt()) data.add (stdin.nextInt());
      }
      sort (data);
      System.out.println (data);
   }
}