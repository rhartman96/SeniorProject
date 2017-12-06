import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] a = br.readLine().split(" ");
        List<Integer> list = new ArrayList<Integer>();
        for(String s : a) {
            list.add(Integer.parseInt(s));
        }
        
        int q = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=q; i++) {
            String type = br.readLine();
            if(type.equals("Insert")) {
                String[] temp = br.readLine().split(" ");
                list.add(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            }
            else {
                int g = Integer.parseInt(br.readLine());
                list.remove(list.get(g));
            }
        }
        
        for(Integer k : list) {
            System.out.print(k + " ");
        }
    }
}
