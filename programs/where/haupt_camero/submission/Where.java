/*
 * Author:  Cameron Haupt, chaupt2013@fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: Extra Assignment, Where is Watson?
 */
import java.util.ArrayDeque;
import java.util.Scanner;

public final class Where {
    private Where () {}
    public static void main (final String[] args) {
        final int n;
        final int k;
        final int q;
        final ArrayDeque<Integer> ringBuffer;
        try (Scanner input = new Scanner(System.in)) {
            n = input.nextInt();
            k = input.nextInt();
            q = input.nextInt();
            ringBuffer = new ArrayDeque<Integer>(n);
            for (int i = 0; i < n; i++) {
                ringBuffer.addLast(input.nextInt());
            }
            for (int i = 0; i < k; i++) {
                ringBuffer.addFirst(ringBuffer.removeLast());
            }
            for (int i = 0; i < q; i++) {
                final int index = input.nextInt();
                final Object[] bufferArray = ringBuffer.toArray();
                System.out.println(bufferArray[index]);
            }
        }
    }
}
