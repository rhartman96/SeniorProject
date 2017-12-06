import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args)throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < k; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        int query = Integer.parseInt(br.readLine());
        while(query-->0){
            String str = br.readLine();
            if(str.equalsIgnoreCase("Insert")){
                int index = 0;
                int val = 0;
                String[] strArr1 = br.readLine().split("\\s+");
                index = Integer.parseInt(strArr1[0]);
                val = Integer.parseInt(strArr1[1]);
                list.add(index, val);
            }else{
                int index = Integer.parseInt(br.readLine());
                if(index >= 0 && index < list.size()){
                   list.remove(index);
                }
            }
        }
        
        for(int i = 0; i < k; i++){
          System.out.print(list.get(i) + " ");
        }
        
    }
}
