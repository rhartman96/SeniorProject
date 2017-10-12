//package data.select;

import java.util.ArrayList;

// One error in inner loop
public final class Selection07 {

   // Sort data[0]..data[n-1] into ascending order.
   public static void select (final ArrayList<Integer> data) {
      final int n = data.size();

      for (int i=0; i<n-1; i++) {
         //  At this point data[0 .. i-1] is sorted and
         //  contains the smallest elements in the data set.
         //  Now search for the index of next smallest element.
         int small = i;
         for (int j=i; j<n; j++) {
            if (data.get(j)<data.get(small)) small=j;
         }
         //Swap
         final Integer temp = data.get(i);
         data.set(i, data.get(small));
         data.set(small, temp);
      }
   }

   public static void main (String [] args) throws NumberFormatException {
      final ArrayList<Integer> data  = new ArrayList<> ();
      for (int i=0; i<args.length; i++) {
         data.add (Integer.parseInt (args[i]));
      }
      select (data);
      System.out.println (data);
      System.out.close();
   }
}
