import java.util.List;
import java.util.ListIterator;

public final class Josephus {
    private Josephus() {}
    private static final int TESTNUM = 10;

    public static void main (final String[] args) throws
        ClassNotFoundException, InstantiationException, IllegalAccessException {
        long startTime, endTime, total = 0;
        int lastSoldier = 0;
        final int size = Integer.parseInt(args[0]);
        final int stepNum = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName (args[2]);
        @SuppressWarnings("unchecked")
            final java.util.List<Integer> list = (java.util.List<Integer>)
            clazz.newInstance();

        for (int i = 0; i < TESTNUM; i++) {
            for (int j = 1; j <= size; j++) {
                list.add(j);
            }
            startTime = System.nanoTime();
            lastSoldier = sort (list, stepNum);
            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        final long averageTime = total / TESTNUM;
        System.out.println("Last Soldier: " + lastSoldier);
        System.out.println("Average Running Time: " + averageTime);
    }

    public static int sort (final List<Integer> list, final int stepNum) {
        int count = 1;
        while (list.size() > 1) {
            final ListIterator<Integer> someList = list.listIterator();

            while (someList.hasNext()) {
                someList.next();
                if (count % stepNum == 0) {
                    someList.remove();
                }
                count++;
            }
        }
        return list.get(0);
    }
}
