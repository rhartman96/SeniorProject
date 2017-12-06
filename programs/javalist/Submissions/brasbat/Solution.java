import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       //read list
       int size =  sc.nextInt();
        List<Integer> list = new ArrayList<>(5);
        for(int i = 0; i< size;i++)
        {
            list.add(sc.nextInt());
        }
        int numOps = sc.nextInt();
        for(int i = 0; i<numOps; i++)
        {
            sc.nextLine();
            String op = sc.nextLine();
           // System.out.println(op);
            if(op.equals("Insert"))
            {
                int pos = sc.nextInt();
                int val = sc.nextInt();
                list.add(pos,val);
            }
            if(op.equals("Delete"))
            {
                int pos = sc.nextInt();
                
               list.remove(list.get(pos));
            }
          
        }
        for(Integer v : list)
        {
            System.out.print(v +" ");
        }
        System.out.println();
    }
}