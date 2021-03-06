//package data.binary;

/*
(low+high)>>>1;  // works even when + overflows
*/

import java.util.Scanner;
import java.util.ArrayList;

public final class Binary03 {

   // Search everything from 'first' to 'last-1' (inclusive)
   public static int search (final int v, final ArrayList<Integer> data, final int first, final int last) {
      assert 0<=first && first<=data.size();
      assert 0<=last && last<=data.size();
      int low = first, high = last-1;
      while (low <= high) {
         final int mid = low+high/2;
         if (data.get(mid) == v) {
            return mid;    // EXIT; v found
         } else if (data.get(mid) < v) {
            // Everything from 'low' to 'mid' is excluded.
            low = mid+1;
         } else {
            assert data.get(mid) > v;
            // Everything from 'mid' to 'high' is excluded.
            high = mid-1;
         }
      }
      return -1;  // v not found
   }

   public static int search (final int v, final ArrayList<Integer> data) {
      return search (v, data, 0, data.size());
   }

   public static int search (final int v, final ArrayList<Integer> data, final int last) {
      return search (v, data, 0, last);
   }

   public static void main (final String [] args) {
      final ArrayList<Integer> data  = new ArrayList<> (args.length);
      int v;
      try (final Scanner stdin = new Scanner (System.in)) {
            v = stdin.nextInt();
            while (stdin.hasNextInt()) data.add (stdin.nextInt());
      }

      final int i = search (v, data);
      if (i==-1) {
         System.out.printf ("%d not found%n", v);
      } else {
         System.out.printf ("%d found at index %d%n", v, i);
      }
      System.out.close();
   }
}
