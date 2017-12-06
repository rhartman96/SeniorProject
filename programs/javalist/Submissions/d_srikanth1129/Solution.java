import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
         List<Integer> list = new ArrayList<Integer>();
        Scanner input = new Scanner(System.in);
        int elements = input.nextInt();
        for(int i=0;i<elements;i++){
            list.add(input.nextInt());
        }
        int test = input.nextInt();
        input.nextLine();
        while(test>0){
            if(input.nextLine().equalsIgnoreCase("Insert")){
                list.add(input.nextInt(),input.nextInt());
            }else {
                list.remove(input.nextInt());
            }
            test--;
           
            input.nextLine();
        }
        input.close();
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
       
    }
}
