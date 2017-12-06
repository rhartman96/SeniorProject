import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<Integer> l = new LinkedList<Integer>();
        String numbers = sc.nextLine();
        String[] nums = numbers.split(" ");
        for (String i:nums){
            l.add(Integer.parseInt(i));
        }
        int q = Integer.parseInt(sc.nextLine());
        for(int j=0; j<q; j++){
            String query = sc.nextLine();
            if(query.equalsIgnoreCase("Insert")){
                query = sc.nextLine();
                String[] num = query.split(" ");
                int x = Integer.parseInt(num[0]);
                int y = Integer.parseInt(num[1]);
                l.add(x, y);
            }
            else{
                int z = Integer.parseInt(sc.nextLine());
                l.remove(z);
            }
        }
        for(int k=0; k<l.size(); k++){
            System.out.print(l.get(k) + " ");
        }
    }
}
