import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        List<Integer> liste = new ArrayList();
        for(int i=0;i<l;i++){
            liste.add(sc.nextInt());
        }
        int s = sc.nextInt();
        for(int i=0;i<s;i++){
            String statment=sc.next();
            if(statment.compareTo("Insert")==0) liste.add(sc.nextInt(),sc.nextInt());
            else liste.remove(sc.nextInt());
        }
        
        
        for(int i=0;i<l;i++) System.out.print(liste.get(i)+" ");
    }
}
