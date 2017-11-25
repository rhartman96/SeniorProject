
/*
 * Author:  Chenlei Zhu, czhu2017@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Proj 21, Josephus Problem
 */
import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus() {
    }
    static final int TRIALS = 10;
    public static void main (final String[] args) throws ClassNotFoundException,
    InstantiationException, IllegalAccessException {
        long totalTime = 0;
        final int size = Integer.parseInt(args[0]);
        final int count = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>)
        clazz.newInstance();
        int num = 0;
        for (int j = 0; j < size; j++) {
            list.add(j + 1);
            }
        for (int i = 0; i < 1; i++) {
            final long startTime = System.nanoTime();
            num = Solution(list, count);
            final long endTime = System.nanoTime();
            totalTime = endTime - startTime;
            }
        final long averageTime = totalTime / TRIALS;
        System.out.println("Last Soldier: " + num);
        System.out.println("Average Running Time: " + averageTime);
        }
    public static int Solution (final List<Integer> data, final int count) {
        int num = 0;
        ListIterator<Integer> itre = data.listIterator();
        while (data.size() > 1) {
            itre = data.listIterator();
            while (itre.hasNext()) {
                itre.next();
                num++;
                if (num == count) {
                    itre.remove();
                    num = 0;
                    }
                }
            }
        itre = data.listIterator();
        return itre.next();
        }
    }
