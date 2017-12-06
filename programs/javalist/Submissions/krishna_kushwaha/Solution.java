import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<n;i++)
            list.add(sc.nextInt());
        int q=sc.nextInt();
        for(int i = 0;i<q;i++){
            String action = sc.next();
            if(action.equals("Insert")){
                list.add(sc.nextInt(),sc.nextInt());
            }
            if(action.equals("Delete"))
                list.remove(list.get(sc.nextInt()));
        }
            for(Integer num:list)
                System.out.print(num+" ");
    }
}