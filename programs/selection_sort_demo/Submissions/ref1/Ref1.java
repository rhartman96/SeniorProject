import java.util.ArrayList;
import java.util.Scanner;

public class Ref1 {

    public static void sort(ArrayList<Integer> data) {
        ArrayList<Integer> sorted = new ArrayList<Integer>();

        while (data.size() > 0) {
            int min = data.get(0);
            int minIndex = 0;
            for (int j = 0; j < data.size(); j++) {
                if (data.get(j) < min) {
                    min = data.get(j);
                    minIndex = j;
                }
            }
            sorted.add(min);
            data.remove(minIndex);
        }
        data = sorted;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> data = new ArrayList<>();
        while (sc.hasNextInt()) {
            data.add(sc.nextInt());
        }

        sort(data);
        System.out.println(data);
    }
}
