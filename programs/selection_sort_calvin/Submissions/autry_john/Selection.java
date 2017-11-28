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
	   //iterate through entire array
	   for(int i = 0; i < data.size(); i++)
	   {
		   //start out with index 0 as min
		   int minimum = (int)data.get(0);
		   
		   //iterate through array, starting with current spot, to find min
		   for(int x = i++; x < data.size(); x++)
		   {
			   //if current index is less than min, make it the new min
			   if((int)data.get(i) < minimum)
			   {
				   minimum = (int)data.get(i);
			   }
		   }
		   
		   //set the current to spot to min of unsorted
		   data.set(i, minimum);
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