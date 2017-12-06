import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
                
        int n = sc.nextInt();
        
        ArrayList a = new ArrayList();
        
        for (int i=0; i<n;i++){
            a.add(sc.nextInt());          
        }
        sc.next();
        
        while (sc.hasNext()){
           String command =  sc.next();
        if (command.equals("Insert")){
            a.add(sc.nextInt(),sc.nextInt());
        }
        else if (command.equals("Delete")){
           a.remove(a.get(sc.nextInt())); 
        }
       }
        Iterator i = a.iterator();
        while (i.hasNext()){
            System.out.print(i.next()+ " ");
        }
    }
}
