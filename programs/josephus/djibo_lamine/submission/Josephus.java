/*
 * Author:  Lamine Djibo, ldjibo2016@my.fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: Josephus
 */
import java.util.ListIterator;
import java.util.List;
 public class Josephus {
    private static final double BILLION = 1000000000.0;
    private static final int TEN = 10;

    private void holmes () {}

    public static Integer kill (final List<Integer> data, final int count) {
       //create iterator
       ListIterator<Integer> itr = data.listIterator();
       int skip = 1;

       while (data.size() > 1) {
         //get to the beginning of the list
         itr = data.listIterator();
         while (itr.hasNext()) {
           final Integer num = itr.next();

           //remove every 3 element
           if (skip % count == 0) {
             itr.remove();
           }
           skip++;
         }
       }
       return data.get(0);
    }

    public static void main (final String[] args)
           throws InstantiationException, ClassNotFoundException, IllegalAccessException {
       final int n = Integer.parseInt(args[0]);
       final int count = Integer.parseInt(args[1]);
       Integer survivor = 0;

       //create list
       final Class<?> clazz = Class.forName(args[2]);
       @SuppressWarnings("unchecked")
       final java.util.List<Integer> list = (java.util.List<Integer>) clazz.newInstance();

       //add values to the list
       for (int j = 1; j <= n; j++) {
          list.add(j);
       }

       //set timers to 0
       long startTime, endTime, total = 0;

       for (int k = 0;  k < TEN; k++) {
           //start time for trial
           startTime = System.nanoTime();

           survivor = kill (list, count);

           endTime = System.nanoTime();
           total += endTime - startTime;
        }

        final long averageTime = total / TEN;
        final double time = (double) averageTime / BILLION;

        System.out.println("Last soldier:" + survivor);
        System.out.println("Average Running Time: " + time);

    }
}
