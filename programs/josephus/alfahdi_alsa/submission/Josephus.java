/*
 * Author:  AL SALT AL FAHDI, aalfahdi2017@fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: Lab Assignment #21  Josephus
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus () {}

    //Number of trials
    private static final double TRIALS = 10.0;

    //exceptions thrown in case of error
    public static void main (final String[] args) throws
    ClassNotFoundException, InstantiationException, IllegalAccessException {

        int lastSoldier = 0;
        final int soldiers = Integer.parseInt(args[0]);
        final int count = Integer.parseInt(args[1]);

        final Class<?> clazz = Class.forName (args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>)
        clazz.newInstance();

        long total = 0;

        for (int i = 0; i < TRIALS; i++) {
            list.clear();
            for (int j = 1; j <= soldiers; j++) {
                list.add(j);
            }

            final long start = System.nanoTime();
            lastSoldier = suicide(list, count);
            final long stop  = System.nanoTime();
            total = stop - start;
        }

        //gets the average time
        final double averageTime = (total / TRIALS) * Math.pow(10, -9);

        System.out.println("Last Soldier: " + lastSoldier);
        System.out.print("Average Running Time: ");
        System.out.printf("%.10f", averageTime);
        System.out.println();
    }

    public static int suicide (final List<Integer> list, final int stepSize) {
        int totalCount = 1;
        while (list.size() > 1) {
            final ListIterator<Integer> someList = list.listIterator();

            while (someList.hasNext()) {
                someList.next();
                if (totalCount % stepSize == 0) {
                    someList.remove();
                }
                totalCount++;
            }
        }
        return list.get(0);
    }
}
