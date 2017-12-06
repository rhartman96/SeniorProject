import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int y=sc.nextInt();
        sc.nextLine();
        ArrayList<Integer> r=new ArrayList<>();
       String h=sc.nextLine();
        String[] j=h.split(" ");
        for(int i=0;i<j.length;i++)
            {
           
            r.add(Integer.parseInt(j[i]));
        }
      
        int k=sc.nextInt();
        sc.nextLine();
  for(int i=0;i<k;i++)
      { String que=sc.nextLine();
       if(que.equals("Insert"))
           {
           String nj=sc.nextLine();
            String hk[]=nj.split(" ");
           r.add(Integer.parseInt(hk[0]),Integer.parseInt(hk[1]));
           
       }       
       if(que.equals("Delete"))
        {
           String num=sc.nextLine();
           r.remove(Integer.parseInt(num));
       
       } 
     
  }
    
        for(int i:r)
            {System.out.print(i+" ");}
}
}
