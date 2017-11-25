
import java.util.List;
import java.util.Iterator;

/*
 * Author:      John Linn, jlinn2016@my.fit.edu
 * Course:      CSE 1002, Section 04, Fall 2017
 * Project:     Proj 21, Josephus Problem
 */

public final class Josephus {
    private Josephus () {}
    public static final int TEN = 10;
    public static void main (final String[] args)  throws ClassNotFoundException,
    InstantiationException, IllegalAccessException {
        final int num = Integer.parseInt(args[0]);
        final int num1 = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);

        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list
            = (java.util.List<Integer>) clazz.newInstance();

        for (int i = 1; i <= num; i++) {
            list.add(i);
        }

        final int count = num1;
       // int last = iterate(list, count);

        //timming
        long startTime = 0;
        long endTime = 0;
        long total = 0;
        int last = 0;

        for (int i = 0; i < TEN; i++) {
            startTime = System.nanoTime();
            last = iterate(list, count);
            endTime = System.nanoTime();
            total += (endTime - startTime);
       }
       //System.out.println(endTime + "    " + startTime);
       final long averageTime = total / TEN;
       System.out.println("Last Soldier: " + last);
       System.out.println("Average Running Time: " + averageTime);
    } //main

    public static int iterate (final List<Integer> list, final int count) {
        //ListIterator<Integer> list1 = list.listIterator();
        int lastMan = 1;
        while (list.size() > 1) {
            final Iterator<Integer> list1 = list.iterator();
            while (list1.hasNext()) {
                list1.next();
                if ((lastMan % count) == 0) {
                    list1.remove();
                }
                lastMan++;
            }
        }
        return list.get(0);
    }

} //end
