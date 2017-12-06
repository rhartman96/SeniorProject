import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        LinkedList<Integer> l=new LinkedList();
        int n,i,x,y;
        String ss=new String();
        Scanner s=new Scanner(System.in);
        n=s.nextInt();  s.nextLine();
        for(i=1;i<=n;i++)
            {
            x=s.nextInt();
            l.add(x);
        }
        n=s.nextInt();  s.nextLine();
        for(i=1;i<=n;i++)
            {
            ss=s.nextLine();
            if(ss.equals("Insert"))
                {
                x=s.nextInt();  y=s.nextInt();  s.nextLine();
                l.add(x,y);
            }
            else
                {
                ss=s.nextLine();  
                l.remove(Integer.parseInt(ss));
            }
        }
        for(int z: l)
            System.out.print(z+" ");
    }
}
