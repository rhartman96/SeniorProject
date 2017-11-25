import java.util.List;
import java.util.ListIterator;

public class Josephus {
    private static final double TRIAL_DIVISOR = 10.0;
    private static final int TRIAL_LIMIT = 10;

    private void josephus () {}
    private static long totalTime = 0;

    public static void intializeSoilders (final List<Integer> list, final int size) {
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
    }

    public static int runJosephus (final List<Integer> list, final int steps) {
        int countStep = 1;
        while (list.size() > 1) {
            final ListIterator<Integer> itr = list.listIterator();
            final long startTime = System.nanoTime();
            while (itr.hasNext()) {
                itr.next();
                countStep++;
                if (countStep % steps == 0 && countStep != 0) {
                    itr.remove();
                }
            }
            final long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return list.get(0);
    }

    public static void main (final String[] args) throws InstantiationException,
                                IllegalAccessException, ClassNotFoundException {
        final Class<?> clazz = Class.forName(args[2]);
        final int stepSize = Integer.parseInt(args[1]);
        final int soilderSize = Integer.parseInt(args[0]);
        int survivor = 0;

        for (int i = 0; i < TRIAL_LIMIT; i++) {
            @SuppressWarnings("unchecked")
            final java.util.List<Integer> list =
                    (java.util.List<Integer>) clazz.newInstance();
            intializeSoilders(list, soilderSize);
            survivor = runJosephus(list, stepSize);
        }

        System.out.printf("Last Soldier: %d%n", survivor + 1);
        System.out.printf("Average Running Time: %.1f%n", totalTime / TRIAL_DIVISOR);

    }
}
