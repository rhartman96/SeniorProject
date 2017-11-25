import java.util.Scanner;

public final class Where {

    private Where() {}

    public static int m, n, k, q;
    public static int[] a;
    public static int[] rotated;
    public static Scanner input = new Scanner(System.in);

    static int[] getArray (final int b) {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        return a;
    }

    public static int[] rotate (final int[] original) {
        final int[] rotated = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            rotated[(i + k) % original.length] = original[i];
        }
        return rotated;
    }

    public static void main (final String[] args) {
        n = input.nextInt();
        k = input.nextInt();
        q = input.nextInt();
        a = getArray(n);

        final int[] mA = new int[q];
        for (int i = 0; i < mA.length; i++) {
            mA[i] = input.nextInt();
        }
        rotated = rotate(a);
        for (int i = 0; i < mA.length; i++) {
            System.out.println(rotated[mA[i]]);
        }

    }
}
