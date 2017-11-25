import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus () {}
    static Integer josephusMethod (final List<Integer> list, final int count) {
        //return the number of the last soldier standing
        final ListIterator<Integer> listIter = list.listIterator();
        int stepRemove = 1;
        while (list.size() != 1) {
            while (listIter.hasNext()) {
                listIter.next();
                if (stepRemove == count) {
                    listIter.remove();
                    stepRemove = 1;
                } else {
                    stepRemove++;
                }
            }
            while (listIter.hasPrevious()) {
                listIter.previous();
            }
        }
        return (listIter.next() + 1);
    }

    public static void main (final String[] args) throws ClassNotFoundException,
    InstantiationException, IllegalAccessException {
        final int numPersons = Integer.parseInt(args[0]);
        final int stepCount = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);
        final int numTrials = 10;
        long startTime = 0;
        long endTime = 0;
        double total = 0;
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list =
        (java.util.List<Integer>) clazz.newInstance();
        for (int i = 0; i < numPersons; i++) {
            list.add(i);
        }
        for (int i = 0; i < numTrials; i++) {
            startTime = System.nanoTime();
            josephusMethod(list, stepCount);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        final double averageTime = (total / numTrials);
        System.out.printf("Last Soldier: %d%nAverage Running Time: %f%n",
                josephusMethod(list, stepCount), averageTime);
    }
}
