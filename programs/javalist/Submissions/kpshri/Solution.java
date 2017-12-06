import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        List<Integer> arr = new ArrayList();
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr.add(Integer.valueOf(str[i]));
        }
        int q = Integer.valueOf(br.readLine());
        for (int i = 0; i < q; i++) {
            String in = br.readLine();
            if (in.equals("Insert")) {
                String[] inVal = br.readLine().split(" ");
                arr.add(Integer.valueOf(inVal[0]),Integer.valueOf(inVal[1]));
            }
            if(in.equals("Delete")){
                int inVal = Integer.valueOf(br.readLine());
                arr.remove(arr.get(inVal));
            }
        }
        
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
}
