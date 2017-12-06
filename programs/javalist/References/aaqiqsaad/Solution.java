import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List L = new ArrayList();
        for (int i=0; i<N; i++){
            L.add(in.nextInt());
        }
        int Q = in.nextInt();
        //System.out.println(Q);
        
        while(in.hasNextLine()){
            String Query = in.nextLine();
            if (Query.contains("Insert")){
                //System.out.println("contains insert");
                int x = in.nextInt();
                int y = in.nextInt();
                L.add(x,y);
               // System.out.println(x);
               // System.out.println(y);
            }
            else if (Query.contains("Delete")){
               // System.out.println("contains delete");
                int x = in.nextInt();
                L.remove(x);
            }
        }
        /*for (int i=0; i<Q; i++){
            String Query = in.nextLine();
            if (Query.contains("Insert")){
                System.out.println("contains insert");
                int x = in.nextInt();
                int y = in.nextInt();
                L.add(x,y);
                System.out.println(x);
                System.out.println(y);
            }
            else if (Query.contains("Delete")){
                System.out.println("contains delete");
                int x = in.nextInt();
                L.remove(x);
            }   
        }*/
        in.close();
        for (int i=0; i<L.size(); i++){
            System.out.print(L.get(i) + " ");
        }
    }
}
