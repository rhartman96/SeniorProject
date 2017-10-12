import java.util.ArrayList;
import java.util.Scanner;

public final class Selection {

   /*
     Sort the array list 'data' into ascending order using selection sort.

     The expression 'data.size()' is the number of elements in the list.
     The expression 'data.get(i)' is the integer value of the ith element of the list.
     The statement 'data.set(i,v)' sets the ith element of the list to the integer value 'v'.
   */

   public static void sort (ArrayList data) {
      for (int numSorted = 0; numSorted < (int)data.size(); ++numSorted) {
          int runningMin = (int)data.get(numSorted);
          int minIndex = numSorted;
          for (int currIndex = numSorted + 1; currIndex < data.size(); ++currIndex)
              if ((int)data.get(currIndex) < runningMin) {
                  runningMin = (int)data.get(currIndex);
                  minIndex = currIndex;
              }
          data.set(minIndex, data.get(numSorted));
          data.set(numSorted, runningMin);
      }
   }

   /*
     The main program reads the data to sort from the standard input
     stream and prints out the result.
   */
   public static void main (final String [] args) {
      final ArrayList data  = new ArrayList<> ();
      try (final Scanner stdin = new Scanner (System.in)) {
            while (stdin.hasNextInt()) data.add (stdin.nextInt());
      }
      sort (data);
      System.out.println (data);
   }
}