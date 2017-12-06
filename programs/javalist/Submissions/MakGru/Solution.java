import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        int n, q;
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            list.add(i, sc.nextInt());
        }
        q = sc.nextInt();
        for(int i = 0; i < q; i++) {
            String s = sc.next();
            if(s.equalsIgnoreCase("Insert")) {
                list.add(sc.nextInt(), sc.nextInt());
            } else {
                if(s.equalsIgnoreCase("Delete")) {
                    list.remove(list.get(sc.nextInt()));
                }
            }
        }
        for(int i: list) {
            System.out.print(i + " ");
        }
    }
}
