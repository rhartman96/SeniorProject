/*
 * Author:  Brock Williamson, bwilliamson2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 21 Josephus, compares arraylist vs linkedlist efficiency
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {

    private static final int TRIALS = 10;

    private Josephus () {}

    public static int simulate (final List<Integer> list, final int counter) {
        final ListIterator<Integer> iterator = list.listIterator();
        int size = list.size();
        //1 2 3x 4 5 1x 2 4 5x 2 4 2x 4
        int modder = 0;
        while (size > 1) {
            if (iterator.hasNext()) {
                modder = (modder + 1) % counter;
                if (modder == 0) {
                    size--;
                    //System.out.println(iterator.next() + " removed, size = " + size);
                    iterator.next();
                    iterator.remove();

                } else {
                    //System.out.println(iterator.next() + " alive");
                    iterator.next();
                }
            } else {
                while (iterator.hasPrevious()) {
                    iterator.previous();
                }
            }
        }
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return iterator.previous();
    }

    public static void main (final String... args) throws
    InstantiationException, IllegalAccessException, ClassNotFoundException {
        //get number of soldiers
        final int soldiers = Integer.parseInt(args[0]);

        //get iteration factor
        final int counter = Integer.parseInt(args[1]);

        //get arraylist or linkedlist from cmdline
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list =
        (java.util.List<Integer>) clazz.newInstance();

        long avgTime = 0, start, end;

        for (int a = 0; a < TRIALS; a++) {
            //initialize list w/ values
            for (int i = 0; i < soldiers; i++) {
                //soldiers begin from #1
                list.add(i + 1);
            }
            start = System.currentTimeMillis();
            //run simulation
            simulate(list, counter);
            end = System.currentTimeMillis();
            avgTime += (end - start);
        }
        System.out.println((avgTime * 1.0) / TRIALS);
    }

}

