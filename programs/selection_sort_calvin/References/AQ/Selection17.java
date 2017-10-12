import java.util.Scanner;
import java.util.ArrayList;

// Inner loop increment bad
public final class Selection17 {

   // Sort data[0]..data[n-1] into ascending order.
   public static void sort (final ArrayList<Integer> data) {
      final int n = data.size();

      for (int i=0; i<n-1; i++) {
         //  At this point data[0 .. i-1] is sorted and
         //  contains the smallest elements in the data set.
         //  Now search for the index of next smallest element.
         int small = i;
         for (int j=i+1; j<n; i++) {    // Line 17
            if (data.get(j)<data.get(small)) small=j;
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
