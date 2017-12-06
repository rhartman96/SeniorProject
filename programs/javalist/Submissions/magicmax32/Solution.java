import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            array.add(in.nextInt());
        }
        int num_queries = in.nextInt();
        String instr = "";
        int index, temp;
        for (int i = 0; i < num_queries; i++){
            instr = in.next();
            index = in.nextInt();
            if (instr.equals("Insert")) {
                temp = in.nextInt();
                array.add(index,temp);
            } else if (instr.equals("Delete")) {
                array.remove(index);
            }
        }
        for (int i = 0; i < length; i++) {
            System.out.print(array.get(i));
            System.out.print(" ");
        }
    }
}