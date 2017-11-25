/*
 * Author:  Abdul Rahman Al Sabri, aalsabri2017@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Lab Assignment 21, Josephus Problem
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {

    private Josephus () {}
    private static final int TRIALS = 10;

    // sorting method
    public static int peopleSuicide (final List<Integer> list, final int size) {
        int totalCount = 1;
        while (list.size() > 1) {
            final ListIterator<Integer> someList = list.listIterator();
            while (someList.hasNext()) {
                someList.next();
                if (totalCount % size == 0) {
                    someList.remove();
                }
                totalCount++;
            }
        }
        return list.get(0);
    }

    // start of program
    public static void main (final String[] args) throws
    ClassNotFoundException, InstantiationException, IllegalAccessException {

        // inputs
        final int people = Integer.parseInt(args[0]);
        final int size = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName (args[2]);

        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>) clazz.
        newInstance();

        int lastSoldier = 0;
        long startTime, endTime, total = 0;

        for (int i = 0; i < TRIALS; i++) {
            list.clear();

            // initializing the list
            for (int k = 1; k <= people; k++) {
                list.add(k);
            }

            // calculating run time
            startTime = System.nanoTime();
            lastSoldier = peopleSuicide(list, size);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }

        final double averageTime = (total / 10.0) * Math.pow(10, -9);

        // prints out results
        System.out.println("Last Soldier: " + lastSoldier);
        System.out.print("Average Running Time: ");
        System.out.printf("%.10f", averageTime);
    }
}
