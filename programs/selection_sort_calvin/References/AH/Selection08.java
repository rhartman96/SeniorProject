import java.util.Scanner;
import java.util.ArrayList;

// Comparison wrong way
public final class Selection08 {

   public static void sort (final ArrayList<Integer> data) {
      final int n = data.size();
      for (int i=0; i<n-1; i++) {
         int small = i;
         for (int j=i+1; j<n; j++) {
            if (data.get(j)>data.get(small)) small=j;    // Line 13
         }
         //Swap
         final Integer temp = data.get(i);
         data.set(i, data.get(small));
         data.set(small, temp);
      }
   }

   public static void main (final String [] args) {
      final ArrayList<Integer> data  = new ArrayList<> ();

      try (Scanner stdin = new Scanner (System.in)) {
            while (stdin.hasNextInt()) data.add (stdin.nextInt());
      }

      sort (data);

      System.out.println (data);
      System.out.close();
   }

}
