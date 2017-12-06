import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
       List<Integer> l=new ArrayList<>();
       Scanner s=new Scanner(System.in);
       int n=s.nextInt();
       for(int i=0;i<n;i++)
           l.add(s.nextInt());
       for(;s.hasNext();){
       String t = s.next();
       if(t.equals("Insert"))
       {
           l.add(s.nextInt(), s.nextInt());
       }
       if(t.equals("Delete"))
       {
           l.remove(s.nextInt());
       }
       }
        for(;!l.isEmpty();)
       {
           System.out.print(l.remove(0)+" ");
       }
   }
   }