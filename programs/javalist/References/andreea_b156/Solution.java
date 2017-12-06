import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        List L=new ArrayList();
        int i;
        for(i=0;i<n;i++)
            L.add(i,scan.nextInt());
        int q=scan.nextInt();
        for(int j=0;j<q;j++){
            String s=scan.next();
            if(s.equals("Insert")) L.add(scan.nextInt(),scan.nextInt());
            if(s.equals("Delete")) L.remove(scan.nextInt());
        }
        for(i=0;i<n;i++)
            System.out.print(L.get(i)+" ");
            
    }
}
