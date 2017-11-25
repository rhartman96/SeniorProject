
/* Jason Freeman freemanj2016@my.fit.edu
 * CSE 1002 fall 2017 section 01
 * Lab 21 Josephus
 */

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
public class Josephus {
    private Josephus () {}
    private static final int Trials = 10;

    public static void main(String[] args) throws ClassNotFoundException,
    InstantiationException, IllegalAccessException {
        final int people = Integer.parseInt(args[0]);
        final int skip = Integer.parseInt(args[1]);

        final Class<?> clazz = Class.forName(args[2]);
            @SuppressWarnings("unchecked")
            final java.util.List<Integer> list = (java.util.List<Integer>) clazz
                    .newInstance();
            long start, stop, total = 0;
            int lastOne = 0;
            for (int i = 0; i < Trials; i++) {
                start = System.nanoTime();
                for (int j = 0; j <= people; j++) {
                    list.add(j);
                }
                lastOne = Josephus(list, skip);
                stop = System.nanoTime();
                total = stop - start;
                list.clear();
            }
        final long averageTime = total / Trials; 
        System.out.printf ("Last one: %d, time: ", lastOne, averageTime);
    }
    public static int Josephus (final List<Integer> list, final int skip) {
        int temp = 0;
        while (list.size() > 1) {
            final ListIterator<Integer> itr = list.listIterator();
            while (itr.hasNext()) {
                itr.next();
                temp++;
                if ((temp % skip == 0)) {
                    itr.remove();
                }
            }
        }
        return list.get(0);
    }
}
