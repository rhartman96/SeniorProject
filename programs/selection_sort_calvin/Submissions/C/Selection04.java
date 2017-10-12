//package data.select;
import java.util.Scanner;

import java.util.ArrayList;

// Swap error
public final class Selection04 {

   public static void select (final ArrayList<Integer> data) {
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

   public static void main (String [] args) throws NumberFormatException {
    //   final ArrayList<Integer> data  = new ArrayList<> ();
    //   for (int i=0; i<args.length; i++) {
    //      data.add (Integer.parseInt (args[i]));
    //   }
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> data = new ArrayList<>();
    while (sc.hasNextInt()) {
        data.add(sc.nextInt());
    }
      select (data);
   }
}
