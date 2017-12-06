import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        scanner.nextLine();
        int m = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < m; i++) {
            String s = scanner.nextLine();
            if(s.equals("Insert")) {
                int index = scanner.nextInt();
                int number = scanner.nextInt();
                scanner.nextLine();
                list.add(index, number);
            }
            if(s.equals("Delete")) {
                int index = Integer.parseInt(scanner.nextLine());
                list.remove(index);
            }
        }
        for(Integer i : list) {
            System.out.format("%d ", i);
        }
    }
}
