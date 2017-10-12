import java.util.ArrayList;
import java.util.Scanner;

public final class Selection {

   /*
     Sort the array list 'data' into ascending order using selection sort.

     The expression 'data.size()' is the number of elements in the list.
     The expression 'data.get(i)' is the integer value of the ith element of the list.
     The statement 'data.set(i,v)' sets the ith element of the list to the integer value 'v'.
   */

   public static void sort (ArrayList<Integer> data) {
	   ArrayList<Integer> sorted = new ArrayList<Integer>();

	   //Sort all values of data
	   while(data.size() > 0){
		   //Loop through data, searching for the minimum element
		   int minimum = Integer.MAX_VALUE;
		   int min_index = 0;
		   for (int i = 0; i < data.size(); i++){
			   if (data.get(i) < minimum){
				   minimum = data.get(i);
				   min_index = i;
			   }
		   }
		   sorted.add(minimum);
		   data.remove(min_index);
	   }
	   data = sorted;

   }

   /*
     The main program reads the data to sort from the standard input
     stream and prints out the result.
   */
   public static void main (final String [] args) {
      final ArrayList<Integer> data  = new ArrayList<Integer> ();
      try (final Scanner stdin = new Scanner (System.in)) {
            while (stdin.hasNextInt()) {
              data.add(stdin.nextInt());
            }
      }
      for(int i = 0; i < data.size(); i++) {
        int minIndex = i;
        int minValue = data.get(i);
        for(int j = i+1; j < data.size(); j++) {
          if(data.get(j) > data.get(minIndex)) {
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
