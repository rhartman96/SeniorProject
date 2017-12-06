import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    int size = sc.nextInt();
    
    ArrayList al = new ArrayList();
    for(int i =0; i<size; i++)
    {
        int al1 = sc.nextInt();
        al.add(al1);
        
    }
    int q = sc.nextInt();
String qa = sc.nextLine();
//  System.out.println(qa);
            for(int i=0; i<q; i++){
        String q1 = sc.nextLine();
//      System.out.println(q1 + " q1");
    //  String qq = sc.nextLine();
        String q2 = sc.nextLine();
    //  System.out.println(q2 +"q2");
        String a[] = q2.split(" ",2);
    //          Integer.parseInt(a[0]);//new int[2];
        
        //int c2 = sc.nextInt();
        if(q1.equals("Insert"))
        {
            al.add(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
    //      System.out.println("inserted "+ al);
            }
        else if(q1.equals("Delete"))
        {
    
            al.remove(Integer.parseInt(a[0]));
        //  System.out.println("deleted "+al);
        }
            }
                Object o[] = al.toArray();
            for(int i = 0 ; i< size; i++)
            {
                System.out.print( o[i] + " ");
            }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
