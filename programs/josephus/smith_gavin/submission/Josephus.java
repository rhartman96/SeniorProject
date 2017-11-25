/*
 * Author: Gavin Giovanni Smith, smithg2016@my.fit.edu
 * Course: CSE 1002, Section 02, Fall 2017
 * Project: Jospehus
 */
import java.util.ListIterator;
public class Josephus {
	private static int TRIAL = 10;
	public static void main (final String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		final int n = Integer.parseInt(args[0]);
		final int k = Integer.parseInt(args[1]);
		final Class<?> clazz = Class.forName(args[2]);
		long time = 0; long startTime = 0; long finalTime = 0;
		Integer finalSoldier = 0;
		for (int i = 0; i < TRIAL; i++) {
			@SuppressWarnings("unchecked")
			final java.util.List<Integer> list = (java.util.List<Integer>) clazz.newInstance();
			for (int j = 0; j < n; j++) {
				list.add(j + 1);
			}
			startTime = System.nanoTime();
			finalSoldier = delete(list, k);
			finalTime = System.nanoTime();
			time += finalTime - startTime;
		}
		System.out.printf("The last soldier standing is: %s%n", finalSoldier);
		System.out.printf("Average running time is: %s", (time / TRIAL));
	}
	
	public static Integer delete (final java.util.List<Integer> data, final int k) {
		ListIterator<Integer> iterator = data.listIterator();
		while (data.size() > 1) {
			for (int i = 0; i < k; i++) {
				if (!iterator.hasNext()) {
					iterator = data.listIterator();
				}
				final int x = iterator.next();
			}
			iterator.remove();
		}
		return data.get(0);
	}
}
