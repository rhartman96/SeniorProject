/*
 * Author:  Thanart Pandey, tpandey2017@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Proj 21, Josephus
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private static final int TEN = 10;
    private static final int NINE = 9;

    private Josephus () {}

    public static int simulateJosephus (final List<Integer> data, final int step) {
        ListIterator<Integer> iter = data.listIterator();
        while (data.size() > 1) {
            if (iter.hasNext()) {
                iter.next();
//                System.out.println(iter.next());
            } else {
                iter = data.listIterator();
                iter.next();
//                System.out.println(iter.next());
            }
            for (int i = 1; i < step; i++) {
                if (iter.hasNext()) {
                    iter.next();
//                    System.out.println(iter.next());
                } else {
                    iter = data.listIterator();
                    iter.next();
//                    System.out.println(iter.next());
                }
            }
//            iter.previous();
//            System.out.println(iter.next());
            iter.remove();
            if (data.size() == 2) {
                data.remove(0);
            }
        }
        return data.get(0);
    }

    public static void main (final String[] args) throws ClassNotFoundException,
    InstantiationException,
    IllegalAccessException {
        final int datasize = Integer.parseInt(args[0]);
        final int step = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);

//        System.out.println(simulateJosephus(list, step));
//        System.out.println(list.size());
        long startTime, endTime, total = 0;
        int lastSoldier = 0;
        for (int i = 0; i < TEN; i++) {
            @SuppressWarnings("unchecked")
            final List<Integer> list = (List<Integer>) clazz.newInstance();
            for (int j = 1; j <= datasize; j++) {
                list.add(j);
            }
            startTime = System.nanoTime();
            simulateJosephus(list, step);
            lastSoldier = list.get(0);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        final long averageTime = total / 10;
        System.out.printf("Last Soldier: %d%n", lastSoldier);
        System.out.printf("Average running time: %f%n",
                           averageTime / (Math.pow(TEN, NINE)));
//        return averageTime;
    }

}
