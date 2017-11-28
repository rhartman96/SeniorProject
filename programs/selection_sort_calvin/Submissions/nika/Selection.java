import java.util.ArrayList;
import java.util.Scanner;
 
 
public class Selection {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<Integer>();
       
        while(sc.hasNextInt()) {
            a.add(sc.nextInt());
        }
       
        for (int i = 0; i < a.size(); i++)
            for (int j = i + 1; j < a.size(); j++)
                if (a.get(i) > a.get(j)){
                int tmp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, tmp);
            }
        System.out.println(a);
       
    }
}