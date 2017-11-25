import java.util.ListIterator;

public class Josephus {


    public static void main (String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final int N = Integer.parseInt(args[0]);
        final int K = Integer.parseInt(args[1]);

        final Class<?> clazz = Class.forName(args[2]);
        @SuppressWarnings("unchecked")
        final java.util.List<Integer> list = (java.util.List<Integer>) clazz.newInstance();
        ListIterator<Integer> iter = list.listIterator();

        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        int count = 1;

        long startTime, endTime, total = 0;

        for (int i = 0; i < 10; i++) {
            // Shuffle

            startTime = System.nanoTime();
            while (list.size() > 1) {
                iter = list.listIterator();
                while (iter.hasNext()) {
                    count++;
                    iter.next();
                    if (count == K) {
                        iter.remove();
                        count = 0;
                    }
                }
            }

            endTime = System.nanoTime();
            total += endTime - startTime;
       }
       final long averageTime = total / 10;

       System.out.println("Last Soldier: " + list.get(0));
       System.out.println("Average Running Time: " + averageTime);
    }
}
