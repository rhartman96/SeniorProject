/*
 * Author:  Qiushi Hu, qhu2015@fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: Proj 21,  Josephus
 */
import java.util.List;
import java.util.ListIterator;

public class Josephus {
    private static final int TIMES = 10;
    public static int suicide (final List<Integer> list, final int k) {
        int index = 0;
        while (list.size() > 1) {
            final ListIterator<Integer> itr = list.listIterator();
            while (itr.hasNext()) {
                itr.next();
                index++;
                if (index % k == 0) {
                    itr.remove();
                }
            }
        }
        return list.get(0);
    }

    public static void main (final String[] args)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        final int n = Integer.parseInt(args[0]);
        final int k = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName (args[2]);
        final StopWatch sw = new StopWatch(true);
        int lastSoldier = 0;
        for (int i = 0; i < TIMES; i++) {
            @SuppressWarnings("unchecked")
            final java.util.List<Integer> list =
                (java.util.List<Integer>) clazz.newInstance();
            for (int j = 0; j < n; j++) {
                list.add(j + 1);
            }

            sw.start();
            // get the last solider left alive
            lastSoldier = suicide(list, k);
            sw.stop();
        }
        final double averageTime = sw.getAverageTime();
        System.out.println("Last Soldier: " + lastSoldier);
        System.out.printf("Average Running Time: %.10f", averageTime);
    }
}