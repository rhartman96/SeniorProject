import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner mScanner = new Scanner(System.in);
        int N = mScanner.nextInt();
        mScanner.nextLine();
        
        List<String> mList = new ArrayList<String>(Arrays.asList(mScanner.nextLine().split("\\ ")));
        int Q = mScanner.nextInt();
        mScanner.nextLine();
        
        for (int i=0; i < Q; i++) {
            if (mScanner.next().contains("Insert")) {
                mList.add(mScanner.nextInt(), String.valueOf((mScanner.nextInt())));
            } else {
                mList.remove(mScanner.nextInt());
            }
        }
        
        String mOutput = "";
        
        for (String mString: mList) {
            mOutput += mString + " ";
        }
        
        System.out.print(mOutput);       
       
    }
}
