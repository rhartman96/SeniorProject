package data.binary;

import java.util.Scanner;
import java.util.ArrayList;

public final class Binary17 {

   public static int search (final int v, final ArrayList<Integer> data) {
      return search (v, data, 0, data.size());
   }

   public static int search (final int v, final ArrayList<Integer> data, final int last) {
      return search (v, data, 0, last);
   }

   // Search everything from 'first' to 'last-1' (inclusive)
   public static int search (final int v, final ArrayList<Integer> data, final int first, final int last) {
      assert 0<=first && first<=data.size();
      assert 0<=last && last<=data.size();
      int low = first, high = last-1;
      while (low <= high) {
         final int mid = (low+high)>>>1;  // works even when + overflows
         final int w = data.get(mid);
         if (w == v) {
            return mid;    // EXIT; v found
         } else if (w < v) {
            // Everything from 'low' to 'mid' (inclusive) is excluded.
            low = mid+1;
         } else {
            assert w > v;
            // Everything from 'mid' to 'high' (inclusive) is excluded.
            high = mid-1;
         }
      }
      return -1;  // v not found
   }

   public static void main (final String [] args) {
      final ArrayList<Integer> data  = new ArrayList<> (args.length);
      int v;
      try (final Scanner stdin = new Scanner (System.in)) {
            v = stdin.nextInt();
            while (stdin.hasNextInt()) data.add (stdin.nextInt());
      }
      // Java knows 'v' is initialized !
      final int i = search (v, data);
      if (i==-1) {
         System.out.printf ("%d not found%n", v);
      } else {
         System.out.printf ("%d found at index %d%n", v, i);
      }
      System.out.close();
   }
}
