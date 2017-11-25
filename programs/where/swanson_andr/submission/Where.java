import java.util.Scanner;

/*
 * Author:  Andrea Swanson, aswanson2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Bonus: Where
 */
public final class Where {
    private Where () {};
    public static void main (final String[] args) {
        @SuppressWarnings("resource")
        final Scanner in = new Scanner (System.in);
        final int n = in.nextInt();
        final int k = in.nextInt();
        final int q = in.nextInt();
        final int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        final int lastIndex = array.length - 1;
        for (int p = 0; p < k; p++) {
            final int newFront = array[lastIndex];
            for (int i = lastIndex; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = newFront;
        }
        for (int i = 0; i < q; i++) {
            System.out.println(array[in.nextInt()]);
        }
    }
}


