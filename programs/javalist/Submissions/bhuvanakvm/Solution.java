import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    List<Integer> list = new ArrayList<Integer>();
    for(int i=0;i<n;i++)   
        list.add(sc.nextInt());
    int q = sc.nextInt();
    for(int i=0;i<q;i++)
    {
        String p = sc.next();
        if(p.equals("Insert"))
        {
            int index = sc.nextInt();
            int value = sc.nextInt();
            list.add(index,value);
        }
        else
        {
            Integer index = sc.nextInt();
            list.remove(list.get(index));
        }
    }
    for(Integer i:list)
    {
        System.out.print(i+" ");
    }
    }
}
