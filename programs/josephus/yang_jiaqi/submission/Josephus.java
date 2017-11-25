/*
 * Author:  Jiaqi Yang, jyang@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Josephus
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus () {}
    public static final int TEN = 10;
    public static int josephus (final List<Integer> list, final int count) {
        int temp = 0;
        while (list.size() > 1) {

            final ListIterator<Integer> iter = list.listIterator();
            while (iter.hasNext()) {
                iter.next();
                temp++;
                if ((temp % count) == 0) {
                    iter.remove();
                }
            }
        }
        return list.get(0);

    }
    public static void main (final String[] args) throws ClassNotFoundException,
     InstantiationException, IllegalAccessException {

         final int n = Integer.parseInt(args[0]);
         final int count = Integer.parseInt(args[1]);
         final Class<?> clazz = Class.forName(args[2]);
         @SuppressWarnings("unchecked")
         final java.util.List<Integer> list
             = (java.util.List<Integer>) clazz.newInstance();

         for (int i = 1; i <= n; i++) {
           list.add(i);
         }
         long startTime, endTime, total = 0;
         int result = 0;
         for (int i = 0; i < TEN; i++) {
             startTime = System.nanoTime();
             result = josephus(list, count);
             endTime = System.nanoTime();
             total += endTime - startTime;

         }
         final long averageTime = total / TEN;
         System.out.println("Last Soilder: " + result);
         System.out.println("Average Running Time: " + averageTime);
     }
}
