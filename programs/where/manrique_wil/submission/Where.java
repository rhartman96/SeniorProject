/*
* Author:  Wilfred Manrique, wmanrique2014@my.fit.edu
* Course:  CSE 1002, Section 01, Fall 2017
* Project: where
*/
/*****************************************************************************************
 * EXTRA CREDIT Assignment from Make-up Day #2 (November 18th, 2017)
 *
 * GIVEN CONSTRAINTS:
 *     Array Size       :: 1 <= n  <= 10^5
 *     Element Value    :: 1 <= ai <= 10^5
 *     Number Rotations :: 1 <= k  <= 10^5
 *     Number Queries   :: 1 <= q  <= 500
 *     Index of Query   :: 1 <= m  <= n-1
 ****************************************************************************************/

import java.util.ArrayList;
import java.util.Scanner;

public final class Where {
    private Where () {}

    static class Watson {
        // Member fields
        ArrayList<Integer> array;

        // Constructor(s)
        Watson (final int n) {
            array = new ArrayList<Integer>(n);
        }

        // Methods
        void add (final Integer ai) {
            this.array.add(ai);
        }

        ArrayList<Integer> rotationRight () {
            final ArrayList<Integer> temp = new ArrayList<Integer>(this.array.size());
            temp.add(this.array.get(this.array.size() - 1));
            for (int i = 0; i < this.array.size() - 1; i++) {
                temp.add(this.array.get(i));
            }
            return temp;
        }

        Integer get (final int index) {
            return this.array.get(index);
        }

        @Override
        public String toString () { // created for debugging purposes
            return this.array.toString();
        }
    }

    // Global Constants
    private static final int TEN_TO_THE_5TH = 100_000;
    private static final int FIVE_HUNDRED   = 500;

    public static void main (final String[] args) {
        final Scanner stdIn = new Scanner (System.in);

        // Accept three integer inputs
        assert stdIn.hasNextInt();
        final int n = stdIn.nextInt();            // array length
        assert (1 <= n) && (n <= TEN_TO_THE_5TH);
        assert stdIn.hasNextInt();
        final int k = stdIn.nextInt();            // number right circular rotations
        assert (1 <= k) && (k <= TEN_TO_THE_5TH);
        assert stdIn.hasNextInt();
        final int q = stdIn.nextInt();            // number queries
        assert (1 <= q) && (q <= FIVE_HUNDRED);
        stdIn.nextLine();                         // skip to next line

        // Take in n space-separated integers
        final Watson a = new Watson(n);
        for (int i = 0; i < n; i++) {
            assert stdIn.hasNextInt();
            final int ai = stdIn.nextInt();
            assert (1 <= ai) && (ai <= TEN_TO_THE_5TH);
            a.add(ai);
        }
        stdIn.nextLine(); // skip to next line

        // Perform k right circular rotations on the array
        for (int i = 0; i < k; i++) {
            a.array = a.rotationRight();
        }

        // Take in queries
        for (int i = 0; i < q; i++) {
            assert stdIn.hasNextInt();
            final int m = stdIn.nextInt();
            assert (0 <= m) && (m <= n - 1);
            final Integer am = a.get(m);
            System.out.println(am);
        }
        stdIn.close();
    }
}
