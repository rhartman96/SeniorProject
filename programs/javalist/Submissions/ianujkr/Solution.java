import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < t; i++){
           int z = in.nextInt();
           list.add(z);     
        }
        int v = in.nextInt();
        try{
            for(int o = 0; o < v-1; v++){
                String s = in.next();
                if(s.equals("Insert")){
                    int b = in.nextInt();
                    int u = in.nextInt();
                    list.add(b, u);
                } 
                else if(s.equals("Delete")){
                    int u = in.nextInt();
                    list.remove(u);
                } 
            }
        }
        catch(NoSuchElementException e){
            System.out.println(Arrays.toString(list.toArray()).replace("[", "").replace("]", "").replace(",", ""));
            System.exit(0);
        }
        System.out.println(Arrays.toString(list.toArray()).replace("[", "").replace("]", "").replace(",", ""));
    }
}
