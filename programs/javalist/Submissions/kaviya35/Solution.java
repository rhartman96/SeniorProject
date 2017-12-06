import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
       public static void main(String[] args) {
              LinkedList li=new LinkedList();
              Scanner sc=new Scanner(System.in);
              int total=sc.nextInt();
              for(int i=0;i<total;i++)
              {
                     li.add(sc.nextInt());
              }
           while(sc.hasNext())
               {
              String str=sc.next();
           
             if(str.equals("Insert"))
              {
                     int index=sc.nextInt();
                     int num=sc.nextInt();
                     li.add(index,num);
             }
              
              if(str.equals("Delete"))
              {
                   
                     int num=sc.nextInt();
                    
                    li.remove(num);
              }}
              for(Object temp: li)
                  
              System.out.print(temp+" ");
       }
}
    
