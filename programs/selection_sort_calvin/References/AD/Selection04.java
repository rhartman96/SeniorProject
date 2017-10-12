import java.util.Scanner;
import java.util.ArrayList;

// Swap error
public final class Selection04 {

   public static void sort (final ArrayList<Integer> data) {
      final int n = data.size();

      for (int i=0; i<n-1; i++) {
         int small = i;
         for (int j=i+1; j<n; j++) {
            if (data.get(j)<data.get(small)) small=j;
         }
         //Swap
         data.set(i, data.get(small));  // line 17
         data.set(small, data.get(i));  // line 18
      }
   }

   public static void main (final String [] args) {
      final ArrayList<Integer> data  = new ArrayList<> ();

      try (final Scanner stdin = new Scanner (System.in)) {
            while (stdin.hasNextInt()) data.add (stdin.nextInt());
      }

      sort (data);

      System.out.println (data);
      System.out.close();
   }

}
