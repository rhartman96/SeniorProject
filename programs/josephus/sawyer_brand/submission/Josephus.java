/*
 * Author:  Brandon Sawyer, bsawyer2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project:  21 Josephus
 *
 */

import java.util.Iterator;
import java.util.List;


public final class Josephus {
    private Josephus(){
    }

    public static void joshephus (final List<Integer> list, final int skipNum) {

        Iterator<Integer> itr = list.listIterator();

        while (list.size () > 1) {
            for (int i = 1; i <= skipNum; i++) {
                if (!itr.hasNext()) {
                    itr = list.iterator();
                }
                itr.next();
            }
            itr.remove();
        }
        itr = list.iterator();
    }

    public static void main (final String[] args) throws ClassNotFoundException,
                                                         InstantiationException,
                                                         IllegalAccessException {

        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final List<Integer> list = (List<Integer>) clazz.newInstance();

        long startTime, endTime, total = 0;
        final int amountNum = Integer.parseInt(args[0]);
        final int skipNum = Integer.parseInt(args[1]);
        final int accuracy = 10;

        for (int j = 1; j <= amountNum; j++) {
            list.add(j);
        }

            for (int i = 0; i < accuracy; i++) {

                startTime = System.nanoTime();

                joshephus(list, skipNum);

                endTime = System.nanoTime();
                total += endTime - startTime;

           }
            final long averageTime = total / accuracy;

        System.out.println("Last Soldier: " + list.get(0));
        System.out.println("Average Running Time: " + averageTime);
    }
}
