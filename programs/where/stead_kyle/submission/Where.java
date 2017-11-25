/*
 * Author:  Kyle Stead, kstead2016@my.fit.edu
 * Course:  CSE 1002, Section 02, Fall 2017
 * Project: Where
 */

import java.util.Scanner;

public final class Where {

    private Where () {}

    public static void main (final String[] args) {
        final Scanner stdin = new Scanner (System.in, "US-ASCII");
        final int n = stdin.nextInt();
        final int k = stdin.nextInt();
        final int q = stdin.nextInt();

        final int[] ai = new int[n];
        for (int i = 0; i < n; i++) {
            ai[i] = stdin.nextInt();
        }

        final int[] ai2 = rotate(ai, k);
        for (int i = 0; i < q; i++) {
            System.out.println(ai2[stdin.nextInt()]);
        }
        stdin.close();
    }

    private static int[] rotate (final int[] ai, final int k) {
        final int[] ai2 = new int[ai.length];
        for (int i = 0; i < ai.length; i++) {
            ai2[i] = ai[(i - k + ai.length) % ai.length];
        }
        return ai2;
    }
}
