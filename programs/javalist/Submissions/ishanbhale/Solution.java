import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
      Scanner sc = new Scanner(System.in);
        int elements_count = sc.nextInt();
        for (int i = 0; i<elements_count;i++){
            list.add(sc.nextInt());
        }
        int queries_count = sc.nextInt();
        for (int j=0;j<queries_count;j++){
            String op = sc.next();
            if(op.equalsIgnoreCase("Insert")){
                list.add(sc.nextInt(),sc.nextInt());
                sc.nextLine();
             }
            else if(op.equalsIgnoreCase("Delete")){            
                list.remove(sc.nextInt());
            }
        }
        for(Integer lt : list){
            System.out.print(lt+" ");
        }
    }
}
