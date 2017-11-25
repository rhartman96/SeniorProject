/*
 * Author:  Chenlei Zhu, czhu2017@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Proj Extra, Where is Watson?
 */
import java.util.Scanner;

public class Where {
    public static int[]array;
    public static int m, n, k, q;
    public static Scanner sc = new Scanner(System.in);

    public static int[] getArray (final int j) {
        array = new int[j];
        for (int i = 0; i < j; i++) {
            array[i] = sc.nextInt();
        }
        return (array);
    }

    public void rotateIt (final int[]arr, final int p) {
        final int temp = arr[0];
        int i;
        for (i = 0; i < p - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[i] = temp;
    }

    public void printIt (final int[]arr, final int size) {
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main (final String[] args) {
        // TODO Auto-generated method stub
        n = sc.nextInt();
        k = sc.nextInt();
        q = sc.nextInt();
        array = getArray(n);
        final Where rotate = new Where();
        rotate.rotateIt(array, n);
        rotate.printIt(array, n);
        }
    }
