import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        //in = new Scanner(System.in);
        in.nextLine();
        String val = in.nextLine();
        String[] temp = val.split("\\s+");
        //System.out.println("val: " + val);
        
        for(int i = 0; i < temp.length; i++)
        {
            int value = Integer.parseInt(temp[i]);
            list.add(value);
        }
        
        int query = in.nextInt();
        in.nextLine();
        for(int i = 0; i < query; i++)
        {
            //in = new Scanner(System.in);
            String op = in.nextLine();
            if(op.equals("Insert"))
            {
                //in = new Scanner(System.in);
                String val2 = in.nextLine();
                String[] temp2 = val2.split("\\s+");
                list.add(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]));
            }
            else if(op.equals("Delete"))
            {
                int indice = in.nextInt();
                list.remove(indice);
                in.nextLine();
            }
            
        }
        
        for(int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }
       
        
    }
}
