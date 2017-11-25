/*
* Author:  Wilfred Manrique, wmanrique2014@my.fit.edu
* Course:  CSE 1002, Section 01, Fall 2017
* Project: 21 Josephus
*/

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus () {}

    private static final int TRIALS  = 10;
    private static final int BILLION = 1000000000;

    public static Integer executions (final List<Integer> soldiers, final int step) {
        ListIterator<Integer> pointer = soldiers.listIterator();
        while (soldiers.size() != 1) {
            for (int i = 0; i < step; i++) {
                if (!pointer.hasNext()) { // if at end of list
                    pointer = soldiers.listIterator(); // reset to start of list
                }
                pointer.next();
            }
            pointer.remove();
        }
        final Integer last = soldiers.get(0);
        soldiers.clear();
        return last;
    }

    public static double runTest (final Class<?> clazz, final int size, final int step)
            throws InstantiationException, IllegalAccessException {
        long startTime, endTime, total = 0;
        // Run & time multiple iterations, average results
        for (int i = 0; i < TRIALS; i++) {
            @SuppressWarnings("unchecked")
            final List<Integer> soldiers = (List<Integer>) clazz.newInstance();
            // Fill group
            for (int k = 1; k <= size; k++) {
                soldiers.add((Integer) k);
            }
            startTime = System.nanoTime();
            final Integer last = executions(soldiers, step);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        // Report average time (in seconds)
        return (double) (total / TRIALS) / BILLION;
    }

    public static void main (final String[] args)
            throws ClassNotFoundException, InstantiationException,
                    IllegalAccessException {  // <int> <int> <ListName>
        // Get group size
        final int size = Integer.parseInt(args[0]);
        // Get execution step
        final int step = Integer.parseInt(args[1]);
        // Construct list type
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final List<Integer> soldiers = (List<Integer>) clazz.newInstance();
        // Fill group
        for (int i = 1; i <= size; i++) {
            soldiers.add((Integer) i);
        }
        // get last soldier
        final Integer lastSoldier = executions(soldiers, step);
        // run performance tests
        final double averageTime = runTest(clazz, size, step);
        // output results
        System.out.printf("Last Soldier: %d%n", lastSoldier);
        System.out.printf("Average Running Time: %.10f%n", averageTime);
    }
}
