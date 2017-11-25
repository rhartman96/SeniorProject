import java.util.Scanner;

/*
 * Author:      John Linn, jlinn2016@my.fit.edu
 * Course:      CSE 1002, Section 04, Fall 2017
 * Project:     Extra Credit, Where
 */
public final class Where {
    private Where () {}
    public static int n;
    public static  int k;
    public static  int q;


    public static void main (final String[] args) {
        @SuppressWarnings("resource")
        final Scanner sc = new Scanner (System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        q = sc.nextInt();

        final int[] list1 = new int[n];
        final int[] mlist = new int[q];

        for (int i = 0; i < n; i++) {
            list1[i] = sc.nextInt();
        }

        for (int j = 0; j < k; j++) {
            final int temp = list1[n - 1];
            for (int r = n - 1; r > 0; r--) {
                list1[r] = list1[r - 1];
            }
            list1[0] = temp;
        }

        for (int o = 0; o < q; o++) {
            mlist[o] = sc.nextInt();
        }

        for (int p = 0; p < q; p++) {
            System.out.println(list1[mlist[p]]);
        }
    }

}
