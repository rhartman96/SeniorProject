import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List myList = new LinkedList();
        for( int i=0; i<N; i++){
            myList.add(sc.nextInt());
        }
        int Q = sc.nextInt();
        for(int i=0; i<Q; i++){
                sc.nextLine();
            String s = sc.nextLine();
                    int x = sc.nextInt();
            if(s.equals("Insert")){
            int y = sc.nextInt();
            myList.add(x,y);
            }else if(s.equals("Delete")){
            myList.remove(x);
            }
        }
        for(int i=0; i<N; i++){
            System.out.print(myList.get(i)+" ");}
 }
}
