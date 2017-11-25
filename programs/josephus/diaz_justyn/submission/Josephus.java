/*
* Author: Justyn Diaz, diazj2016@my.fit.edu
* Course: CSE 1002, Section 03, Fall 2017
* Project: Lab Assignment #21, Josephus Problem
*/

import java.util.List;
import java.util.ListIterator;

public final class Josephus {

    private Josephus () {}
    private static final int TRIALS = 10;

    public static int josephusProblem (final List<Integer> list, final int K) {
        int temp = 0;

        while (list.size() > 1) {
            final ListIterator<Integer> iterator = list.listIterator();
            while (iterator.hasNext()) {
                iterator.next();
                temp++;
                if ((temp % K) == 0) {
                    iterator.remove();
                }
            }
        }
        return list.get(0);

    }

    public static void main (final String[] args) throws Exception {
        final int N = Integer.parseInt(args[0]);
        final int K = Integer.parseInt(args[1]);
        int soldiers = 0;
        long startTime = 0;
        long endTime = 0;
        long total = 0;

        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list =
        (java.util.List<Integer>) clazz.newInstance();

        for (int i = 0; i < TRIALS; i++) {
            startTime = System.nanoTime();
            for (int j = 1; j <= N; j++) {
                list.add(j);
            }
            soldiers = josephusProblem(list, K);
            endTime = System.nanoTime();
            list.clear();
            total += endTime - startTime;
        }
        final long averageTime = total / TRIALS;
        System.out.println("Last Soldier: " + soldiers);
        System.out.println("Average Running Time: " + averageTime);
    }
}
