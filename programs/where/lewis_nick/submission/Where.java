import java.util.Scanner;
import java.util.LinkedList;
public class Where {
    private void where () {}
    public static void main (final String[] args) {
        final Scanner stdin = new Scanner(System.in);
        final LinkedList<Integer> list = new LinkedList<>();
        final int size = stdin.nextInt();
        final int roTime = stdin.nextInt();
        final int queries = stdin.nextInt();

        for (int i = 0; i < size; i++) {
            list.addLast(stdin.nextInt());
        }

        for (int i = 0; i < roTime; i++) {
            final int myCatch = list.pollLast();
            list.addFirst(myCatch);
        }

        for (int i = 0; i < queries; i++) {
            System.out.println(list.get(stdin.nextInt()));
        }
    }
}
