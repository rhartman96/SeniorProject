/*
 * Author: Charles Taaffe, ctaaffe2016@my.fit.edu
 * Course: CSE 1002, Section 01, Fall 2017
 * Project: Proj 21, Josephus
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus() {};
    private static final int NUM_TRIALS = 11;

    public static void main (final String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        // Take the initial number of soldiers and the skip count from the
        // command line
        final int size = Integer.parseInt(args[0]);
        final int skip = Integer.parseInt(args[1]);
        // Take the decision to use either array list or linked list from the
        // command line
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>) clazz
                .newInstance();
        final StopWatch sw = new StopWatch(true);

        for (int i = 0; i < NUM_TRIALS; i++) {
            sw.start();
            for (int j = 1; j <= size; j++) {
                list.add(j);
            }
            josephus(list, skip);
            sw.stop();
            if (i == NUM_TRIALS - 1) {
                System.out.println ("Last Soldier: " + list);
            }
            list.clear();
        }
        final double averageTime = sw.getAverageTime();
        System.out.println ("Average Runnning Time: " + averageTime);

    }

    // Solution to the josephus problem
    public static int josephus (final List<Integer> list, final int skip) {
        int temp = 0;
        while (list.size() > 1) {
            final ListIterator<Integer> itr = list.listIterator();
            while (itr.hasNext()) {
                itr.next();
                temp++;
                if ((temp % skip) == 0) {
                    itr.remove();

                }

            }
        }
        return list.get(0);

    }

}
