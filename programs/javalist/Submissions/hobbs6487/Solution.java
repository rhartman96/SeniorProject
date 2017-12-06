import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int length = scan.nextInt();
        List<Integer> a = new ArrayList<Integer>();
        // read in array
        for(int i = 0 ; i < length; i++){
            int val = scan.nextInt();
            // Fill array a here
            a.add(new Integer(val));
        }
        
        String command = scan.nextLine();
        while(scan.hasNext()){
            if(command.equalsIgnoreCase("insert")){
                int x = scan.nextInt();
                int y = scan.nextInt();
                a.add(x, y);
            }else if(command.equalsIgnoreCase("delete")){
                int x = scan.nextInt();
                a.remove(x);
            }
            command = scan.hasNext() ? scan.nextLine() : null;
        }
        
        String output = "";
        for(int i = 0; i < a.size(); i++){
            output += a.get(i) + " ";
        }
        
        output = output.trim();
        System.out.println(output);
        
    }
}
