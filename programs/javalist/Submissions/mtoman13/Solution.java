import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> list = new ArrayList();
        for(int i = 0; i < n; i++){
            list.add(in.nextInt());
        }
        int q = in.nextInt();
        String query = "";
        int index = 0;
        while(q-->0){
            query = in.next();
            if(query.equalsIgnoreCase("insert")){
                index = in.nextInt();
                Integer data = in.nextInt();
                if(index >= 0){
                    list.add(index, data);
                }
            }
            else if(query.equalsIgnoreCase("delete")){
                index = in.nextInt();
                if(index >= 0){
                    list.remove(index);
                }
            }
        }
        for(int i = 0; i < n; i++){
            System.out.print(list.get(i) + " ");
        }
    }
}
