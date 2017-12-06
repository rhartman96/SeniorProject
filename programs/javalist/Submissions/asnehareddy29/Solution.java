import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        List<Integer> listdata = new ArrayList<>();
        
        int len = Integer.parseInt(sc.nextLine());
        String[] str = sc.nextLine().split(" ");
        
        for(String s: str)
            {
            listdata.add(Integer.parseInt(s));
        }
        
        int noof_queries = Integer.parseInt(sc.nextLine());
        for(int i=0 ; i< noof_queries; i++)
            {
            String type = sc.nextLine();
            if(type.equalsIgnoreCase("Insert"))
            {
                String[] d = sc.nextLine().split(" ");
                listdata.add(Integer.parseInt(d[0]), Integer.parseInt(d[1]));
            }
            if(type.equalsIgnoreCase("Delete")){
                int id = Integer.parseInt(sc.nextLine());
                listdata.remove(id);
            }
            
        }
        
        for(int x : listdata)
            {
            System.out.print(x + " ");
        }
        
    }
}
