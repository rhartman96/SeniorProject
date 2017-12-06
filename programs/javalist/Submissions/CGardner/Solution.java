import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner s = new Scanner(System.in);
        int length = s.nextInt();
        int x = 0;
        int y = 0;
        int z = 0;
        
        List<Integer> l = new ArrayList<>();
        
        for(int i = 0; i<length; i++)
            {
            int value = s.nextInt();
            l.add(value);//adds value to arraylist
        }
        
        int noq = s.nextInt(); // number of queries
        
            for(int a = 0; a < noq; a++)
            {
            String query = s.next(); //Query word
            //System.out.println("query: " + query);
        
        if(query.equals("Insert"))
            {
            x = s.nextInt();
          //  System.out.println("x: " + x);
            y = s.nextInt();
           // System.out.println("y: " + y);
            
            l.add(x,y);
            }
        if(query.equals("Delete"))
            {
            z = s.nextInt();
            //System.out.println("z: " + z);
            l.remove(z);
        }
        }
        
        for(int b = 0; b<length; b++)
            {
          System.out.print(l.get(b) + " ");
        }
        
    }
}