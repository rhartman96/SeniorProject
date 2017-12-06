import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        List<Integer> listing = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        int loop = in.nextInt();
        //System.out.println("loop:"+ loop);
        for (int i=0;i<loop;i++) {
            listing.add(in.nextInt());
        }
        int tt = in.nextInt();
        //System.out.println("tt:"+ tt);
        for (int j=0;j<tt;j++) {
            String steps = in.next();
            //System.out.println("steps:"+ steps);
            if (steps.equals("Insert")) {
                listing.add(in.nextInt(),in.nextInt());
            } else if (steps.equals("Delete")) {
                listing.remove(in.nextInt());
            }
        }
        for (int i=0;i<loop;i++) {
            System.out.print(listing.get(i) + " ");
        }
    }
}
