import java.util.List;
import java.util.ListIterator;

/*
 * Author:  Javier Munoz, jmunoz2014@my.fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: Proj 21, Josephus
 */
public final class Josephus {
    private Josephus () {}
    private static final int TRIALS = 10;
    private static final double NANO_SECONDS_IN_A_SECOND = 1000000000.0;
    public static void main (final String[] args) throws ClassNotFoundException,
                                                         InstantiationException,
                                                         IllegalAccessException {
        final int step = Integer.parseInt(args[1]);
        int lastSoldier = 0;
        long startTime, endTime, total = 0;
        for (int i = 0; i < TRIALS; i++) {
            final Class<?> clazz = Class.forName(args[2]);
            @SuppressWarnings("unchecked")
            final java.util.List<Integer> list =
            (java.util.List<Integer>) clazz.newInstance();
            // Initialize
            final int dataSize = Integer.parseInt(args[0]);
            for (int j = 0; j < dataSize; j++) {
                list.add(j + 1);
            }
            startTime = System.nanoTime();
            lastSoldier += josephus(list, step);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        final long averageTime = total / TRIALS;
        System.out.printf("Last Soldier: %d%nAverage Running Time: %f",
                lastSoldier / TRIALS, averageTime / NANO_SECONDS_IN_A_SECOND);
    }
    public static int josephus (final List<Integer> list, final int step) {
        final int survivor;
        int stepsRemaining = step;
        while (list.size() > 1) {
            final ListIterator<Integer> itr = list.listIterator();
            while (itr.hasNext()) {
                boolean restartStep = true;
                while (stepsRemaining > 0 && itr.hasNext()) {
                    itr.next();
                    stepsRemaining--;
                    if (stepsRemaining > 0 && !itr.hasNext()) {
                        restartStep = false;
                    }
                }
                if (stepsRemaining == 0) {
                    itr.remove();
                }
                if (restartStep) {
                    stepsRemaining = step;
                }
            }
        }
        survivor = list.get(0);
        return survivor;
    }
}
