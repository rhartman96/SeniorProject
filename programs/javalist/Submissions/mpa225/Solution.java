import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        LinkedList<Integer> ll = new LinkedList<>();
        for(int i=0; i < x; i++){
            ll.add(sc.nextInt());
        }
        int querys = sc.nextInt();
        for(int i=0; i < querys; i++){
            if(sc.next().equals("Insert")){
                int index = sc.nextInt();
                int value = sc.nextInt();
                ll.add(index, value);
            }else{
                int index = sc.nextInt();
                ll.remove(index);
            }
        }
        for(int i=0; i < x ; i++){
            System.out.print(ll.get(i) + " ");
        }
    }
}
