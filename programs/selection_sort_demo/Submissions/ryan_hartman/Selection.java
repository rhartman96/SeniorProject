import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public final class Selection {

   /*
     Sort the array list 'data' into ascending order using selection sort.

     The expression 'data.size()' is the number of elements in the list.
     The expression 'data.get(i)' is the integer value of the ith element of the list.
     The statement 'data.set(i,v)' sets the ith element of the list to the integer value 'v'.
   */

   /*
     The main program reads the data to sort from the standard input
     stream and prints out the result.
   */
   public static void main (final String [] args) {
      final List<Integer> data  = new LinkedList<Integer> ();
      try (final Scanner stdin = new Scanner (System.in)) {
            while (stdin.hasNextInt()) {
              data.add(stdin.nextInt());
            }
      }
      for(int i = 0; i < data.size(); i++) {
        int minIndex = i;
        int minValue = data.get(i);
        for(int j = i+1; j < data.size(); j++) {
          if(data.get(j) < data.get(minIndex)) {
            minIndex = j;
            minValue = data.get(j);
          }
        }
        int temp = data.get(i);
        data.set(i, minValue);
        data.set(minIndex, temp);
      }
      // sort (data);
      System.out.println (data);
   }
}