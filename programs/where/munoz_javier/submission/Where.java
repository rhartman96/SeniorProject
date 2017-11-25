import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Author:  Javier Munoz, jmunoz2014@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Extra, Where
 */
public final class Where {
    private Where () {}
    private static final Scanner STDIN =
            new Scanner (new BufferedInputStream (System.in));
    public static void main (final String[] args) {
        final int arrayLength = Integer.parseInt(STDIN.next());
        final int[] arr = new int[arrayLength];
        final int timesToRotate = Integer.parseInt(STDIN.next());
        final int numberOfQueries = Integer.parseInt(STDIN.next());
        for (int i = 0; i < arrayLength; i++) {
            arr[i] = Integer.parseInt(STDIN.next());
        }
        final int[] rotated = rotate(arr, timesToRotate);
        for (int i = 0; i < numberOfQueries; i++) {
            final int query = Integer.parseInt(STDIN.next());
            System.out.printf("%d%n", rotated[query]);
        }
    }
    private static int[] rotate (final int[] arr, final int n) {
        if (n == 0) {
            return arr;
        } else {
            final int[] rotatedArr = new int[arr.length];
            final int last = arr[arr.length - 1];
            for (int i = arr.length - 1; i > 0; i--) {
                rotatedArr[i] = arr[i - 1];
            }
            rotatedArr[0] = last;
            System.out.println(Arrays.toString(rotatedArr));
            return rotate(rotatedArr, n - 1);
        }

    }
}
