import java.util.ListIterator;
import java.util.List;

public final class Josephus {

    private Josephus() {}

    public static void main (final String[] args) throws ClassNotFoundException,
                                                        InstantiationException,
                                                        IllegalAccessException {
        final long size = Long.parseLong(args[0]);
        final int step = Integer.parseInt(args[1]);
        final Class<?> clazz = Class.forName(args[2]);

        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>)
                                               clazz.newInstance();

        long startTime, endTime, total = 0;
        int answer = -1;
        final int ten = 10;

        for (int i = 0; i < ten; i++) {
            for (int j = 1; j < size + 1; j++) {
                list.add(j);
            }

            startTime = System.nanoTime();

            answer = jose(list, step);

            endTime = System.nanoTime();
            total += endTime - startTime;
        }
        System.out.println("Last soldier: " + answer);

        final long averageTime = total / ten;
        System.out.printf("Average Running Time: %d%n", averageTime);
    }

    public static int jose (final List<Integer> list, final int step) {
        int count = 2;

        while (list.size() > 1) {
            final ListIterator<Integer> iter = list.listIterator();
            while (iter.hasNext()) {
                iter.next();
                count = count + 1;
                if (count == step) {
                    iter.remove();
                    count = 0;
                }
            }
        }
        final int answer = list.get(0);
        return answer;
    }
}
