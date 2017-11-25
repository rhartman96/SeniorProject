/*
 * Author:  Kyle Stead, kstead2016@my.fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: 21 Josephus
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus () {}
    private static final double BILLION = 1E9;
    private static final int ITERATIONS = 10;
    public static void main (final String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        final int soldierCount = Integer.parseInt(args[0]);
        final int skips = Integer.parseInt(args[1]);

        final Class<?> classArg = Class.forName(args[2]);

        final boolean internalTest = args.length >= 1 + 1 + 1 + 1;
/*        final List<Integer> list = (List<Integer>) classArg.newInstance();

        for (int i = 0; i < soldierCount; i++) {
            list.add(i + 1);
        }*/
        long startTime, endTime, total = 0;
        int answer = -1;
        for (int i = 0; i < ITERATIONS; i++) {
            final List<Integer> list = (List<Integer>) classArg.newInstance();

            for (int j = 0; j < soldierCount; j++) {
                list.add(j + 1);
            }
            startTime = System.nanoTime();
            answer = execute(list, skips);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        final long averageTime = total / ITERATIONS;
        if (internalTest) {
            System.out.print(averageTime);
        } else {
            System.out.printf("Last Soldier: %d%n", answer);
            System.out.printf("Average Running Time: %.10f%n", averageTime / BILLION);
        }

    }
    private static int execute (final List<Integer> soldiers, final int skips) {
        ListIterator<Integer> soldierIterator = soldiers.listIterator();
        int counter = 0;

        while (soldiers.size() > 1) {
            if (soldierIterator.nextIndex() == soldiers.size()) {
                soldierIterator = soldiers.listIterator();
            }
            soldierIterator.next();
            if (++counter % skips == 0) {
                soldierIterator.remove();
            }
        }

        return (soldiers.size() > 0) ? soldiers.get(0) : 0;
    }
}
