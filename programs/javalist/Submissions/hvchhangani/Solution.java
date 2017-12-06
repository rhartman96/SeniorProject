import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            list.add(scan.nextInt());
        }
        int m = scan.nextInt();
        for(int i = 0; i < m; i++){
            String action = scan.next();
            
            if(action.equals("Insert")){
                list.add(scan.nextInt(), scan.nextInt());
            }
            if(action.equals("Delete")){
                list.remove(scan.nextInt());
            }
        }
        for(int i = 0; i < n; i++){
            System.out.print(list.get(i) + " ");
        }
        
    }
}
