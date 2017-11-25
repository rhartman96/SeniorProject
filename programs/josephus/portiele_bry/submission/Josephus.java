/*
 * Author: Bryan Portieles, bportieles2016@my.fit.edu
 * Course: Cse 1002, Section 01, Fall 2017
 * Project: Assignment #21 , Josphus Problem
*/

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private static final int TRIALS = 10;

    private Josephus() {
    }
    public static int josephusProblem (final List<Integer> data, final int steps) {
        final ListIterator<Integer> litr = data.listIterator();
        final int lastSoldier;
        int counter = 0;
        while (data.size() > 1) {
            if (litr.hasNext()) {
                counter++;
                litr.next();
                if (counter % steps == 0) {
                    litr.remove();
                    counter = 0;
                }
            } else {
                while (litr.hasPrevious()) {
                    litr.previous();
                }
            }

        }

        lastSoldier = data.get(0);
        data.remove(0);
        return lastSoldier;
    }

    public static void main (final String... args)
                            throws ClassNotFoundException,
                            InstantiationException,
                            IllegalAccessException {

        final int numbSoldiers = Integer.parseInt(args[0]);
        final int numbSteps = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list =
             (java.util.List<Integer>) clazz.newInstance();
        long startTime, endTime, total = 0;
        int lastSoldier = 0;

        for (int j = 0; j < TRIALS; j++) {

            for (int i = 1; i <= numbSoldiers; i++) { //adds soldiers to list
                list.add(new Integer(i));
            }
            startTime = System.nanoTime();
            lastSoldier = josephusProblem (list, numbSteps);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        final long averageTime = total / TRIALS;
        final double averageTimeSeconds = averageTime / 1000000000.0;
        System.out.println("Last Soldier: " + lastSoldier);
        System.out.println("Average Running Time: " + averageTimeSeconds);

    }

}
