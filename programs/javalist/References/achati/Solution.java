import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        
        LinkedList<Integer> list = new LinkedList<Integer>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            list.add(sc.nextInt());
        }
        
        int q = sc.nextInt();
        while(sc.hasNext()){
            String query = sc.nextLine();
            if(query.equalsIgnoreCase("Insert")){
                int x = sc.nextInt();
                int y = sc.nextInt();
                list.add(x,y);
            }
            if(query.equalsIgnoreCase("Delete")){
                int x = sc.nextInt();
                list.remove(x);
            }
        }
    for(int element:list){
        System.out.print(element+" ");
    }
    }
}
