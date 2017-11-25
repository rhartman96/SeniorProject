/*
 * Author: Mohammed Alzadjali, malzadjali2016@my.fit.edu
 * Course: CSE1002, Section 02, fall 2017
 * Project: josephus
 */

import java.util.List;
import java.util.ListIterator;

public final class Josephus {
private Josephus(){
}

    //number below used for a for loop in main
    private static final int TESTNUM = 10;

    //exceptions thrown in case of errors
    public static void main (final String[] args) throws
        ClassNotFoundException, InstantiationException, IllegalAccessException {

        int lastSoldier = 0;
        final int numOfPeople = Integer.parseInt(args[0]);
        final int stepSize = Integer.parseInt(args[1]);

        final Class<?> clazz = Class.forName (args[2]);
        @SuppressWarnings("unchecked")
            final java.util.List<Integer> list = (java.util.List<Integer>)
            clazz.newInstance();

        long total = 0;

        for (int i1 = 0; i1 < TESTNUM; i1++) {
            list.clear();
            for (int j = 1; j <= numOfPeople; j++) {
                list.add(j);
            }

            final long start = System.nanoTime();
            lastSoldier = suicide(list, stepSize);
            final long stop  = System.nanoTime();
            total = stop - start;
        }

        //getting  the average time by formula
        final double averageTime = (total / 10.0) * Math.pow(10, -9);

        System.out.println("Last Soldier: " + lastSoldier);
        System.out.println("Average Running Time: " + averageTime);
    }

    public static int suicide (final List<Integer> list, final int stepSize) {
        int tlCount = 1;
        while (list.size() > 1) {
            final ListIterator<Integer> someList = list.listIterator();

            while (someList.hasNext()) {
                someList.next();
                if (tlCount % stepSize == 0) {
                    someList.remove();
                }
                tlCount++;
            }
        }
        return list.get(0);
    }
}
