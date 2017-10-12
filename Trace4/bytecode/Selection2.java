import java.util.Arrays;
import java.util.ArrayList;  // unused

public final class Selection2 {

   // Sort data[0]..data[n-1] into ascending order.
   public static void select (final AL data) {
      final int n = data.size();

      for (int i=0; i<n-1; i++) {

         //  At this point data[0 .. i-1] is sorted and
         //  contains the smallest elements in the data set.
         //  Now search for the index of next smallest element.
         int small = i;
         for (int j=i+1; j<n; j++) {
            if (data.get(j)<data.get(small)) small=j;
         }
         //Swap
         final Integer temp = data.get(i);
         data.set(i, data.get(small));
         data.set(small, temp);
         if (verbose) System.out.printf ("%2d: %s%n", i, data);
      }
   }

   private static boolean verbose  = System.getProperty ("verbose")!=null;

   public static void main (String [] args) throws NumberFormatException {
      final AL data  = new AL ();
      for (int i=0; i<args.length; i++) {
         data.add (Integer.parseInt (args[i]));
      }

      System.out.printf ("..: %s%n", data);
      select (data);
      System.out.printf ("**: %s%n", data);
   }
}
