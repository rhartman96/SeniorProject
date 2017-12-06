import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int listNum = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < listNum; i++){
            arr.add(in.nextInt());
        }
        
        int queryNum = in.nextInt();
        String query;
        int x, y;
        for(int j = 0; j< queryNum; j++){
            query = in.next();
            if (query.equals("Insert")){
                x = in.nextInt();
                y = in.nextInt();
                arr.add(x, y);
            }
            else{
                x = in.nextInt();
                arr.remove(x);
            }
        }
        for (int i = 0; i < listNum; i++){
            System.out.print(arr.get(i) + " ");
        }
        
    }
}
