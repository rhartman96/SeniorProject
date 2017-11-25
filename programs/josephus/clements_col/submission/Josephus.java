/**
 * Author:  Cole Clements, cclements2016@my.fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: Proj 21, Josephus
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus () {}

    public static final int TRIALS = 10;

    public static int josephus (final List<Integer> soldiers, final int steps) {
        int counter = 1;
        final ListIterator<Integer> iterSoldiers = soldiers.listIterator();
        while (soldiers.size() > 1) {
            if (iterSoldiers.hasNext()) {
                iterSoldiers.next();
                if (counter % steps == 0) {
                    iterSoldiers.remove();
                }
                counter++;
            } else {
                //resets to beginning of soldiers array
                while (iterSoldiers.hasPrevious()) {
                    iterSoldiers.previous();
                }
            }
        }
        final int lastSoldier = soldiers.get(0);
        soldiers.clear();
        return lastSoldier;
    }

    public static void main (final String[] args)
            throws ClassNotFoundException,
                   IllegalAccessException, InstantiationException {

        final int numOfSoldiers = Integer.parseInt(args[0]);
        final int steps = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> soldiers =
             (java.util.List<Integer>) clazz.newInstance();

        //begin Josephus problem
        long startTime, endTime, total = 0;
        int lastSoldier = 0;
        for (int i = 0; i < TRIALS; i++) {
            //create array of soldiers
            for (int num = 0; num < numOfSoldiers; num++) {
                soldiers.add(num + 1);
            }
            //begin testing
            startTime = System.nanoTime();
            lastSoldier = josephus(soldiers, steps);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        final long averageTime = total / TRIALS;
        final double time = averageTime * Math.pow(10, -9);

        System.out.printf("Last Soldier: %d%n", lastSoldier);
        System.out.printf("Average Running Time: %.10f%n", time);
    }
}
