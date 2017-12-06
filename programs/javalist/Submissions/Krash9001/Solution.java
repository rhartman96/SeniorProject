import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        
        int numberCount = Integer.parseInt(in.nextLine());
        String[] strNumbers = in.nextLine().split(" ");
        
        List<Integer> numbers = new ArrayList<>();
        
        for (String s : strNumbers) {
            numbers.add(Integer.parseInt(s));
        }
        
        int operationsCount = Integer.parseInt(in.nextLine());
        
        for(int i = 0; i < operationsCount; i++) {
            String command = in.nextLine();
            String[] commandNum = in.nextLine().split(" ");
            if(command.equals("Insert")) {
                numbers.add(Integer.parseInt(commandNum[0]), Integer.parseInt(commandNum[1]));
            }
            else if(command.equals("Delete")) {
                numbers.remove(Integer.parseInt(commandNum[0]));
            }
            else {
                System.out.println("error");
            }
        }
        
        in.close();
        
        numbers.forEach(e -> System.out.print(e + " "));
    }
}
