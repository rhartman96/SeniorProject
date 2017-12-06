import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int listSize = Integer.parseInt(sc.nextLine());
        ArrayList<String> list = new ArrayList<String>();
        String[] nums = sc.nextLine().split(" ");
        
        for (int i = 0; i < nums.length; i += 1) {
            list.add(nums[i]);
        }
        
        int numQueries = Integer.parseInt(sc.nextLine());
        
        for (int i = 0; i < numQueries; i += 1) {
            int index;
            String operation = sc.nextLine();
            // System.out.println(operation);
            switch(operation) {
                case "Insert":
                String[] parts = sc.nextLine().split(" ");
                index = Integer.parseInt(parts[0]);
                String value = parts[1];
                // System.out.println("Inserting " + value + " at " + index);
                list.add(index, value);
                break;
                case "Delete":
                index = Integer.parseInt(sc.nextLine());
                // System.out.println("Removing item at: " + index);
                list.remove(index);
                break;
            }
        }
        
        String output = "";
        
        for (String i: list) {
            output += i + " ";
        }
        
        output.trim();
        
        System.out.println(output);
    }
}
