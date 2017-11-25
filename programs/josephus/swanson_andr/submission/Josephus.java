import java.util.ListIterator;
import java.util.List;

/*
 * Author:  Andrea Swanson, aswanson2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 21 Josephus
 */
public final class Josephus {
    private Josephus() {};
    @SuppressWarnings("unchecked")
    public static int lastStanding (final List<Integer> list2, final int counter) {
        ListIterator<Integer> iter = list2.listIterator();
        int count = 0;
        while (list2.size() > 1) {
            iter = list2.listIterator();
            while (iter.hasNext()) {
                //for (int = 0)
                @SuppressWarnings("unused")
                final Integer lives = iter.next();
                count++;
                //@SuppressWarnings("unused")
                /*final Integer dies = iter.next();*/
                if (count == counter) {
                    iter.remove();
                    count = 0;
                }
            }
        }
        return list2.listIterator().next();
    }
    public static void main (final String[] args) throws
    ClassNotFoundException, InstantiationException, IllegalAccessException {
        final int size = Integer.parseInt(args[0]);
        final int counter = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list =
        (java.util.List<Integer>) clazz.newInstance();
        final ListIterator<Integer> iter2 = list.listIterator();
        long startTime = 0;
        long endTime = 0;
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }
        startTime = System.nanoTime();
        System.out.println("Last Soldier: " + lastStanding(list, counter));
        endTime = System.nanoTime();
        final long duration = endTime - startTime;
        System.out.println("Run time: " + duration);

}
}


