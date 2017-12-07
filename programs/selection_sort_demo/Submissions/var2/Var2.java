import java.util.ArrayList;
import java.util.Scanner;

public class Var2 {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> data = new ArrayList<>();

        while (sc.hasNextInt()) {
            data.add(sc.nextInt());
        }

        for (int i = 0; i < data.size(); i++) {
            int a = data.get(i);
            for (int j = i; j < data.size(); j++) {
                int b = data.get(j);
                if (a < b) {
                    data.set(a, b);
                }
            }
        }
        System.out.println(data);

    }

}
