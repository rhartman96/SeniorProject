import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        
        int sizeOfList = scr.nextInt();
        List<Integer> lstOfInt = new ArrayList<>();
        
        for (int i = 0; i < sizeOfList; i++) {
            lstOfInt.add(i, scr.nextInt());
        }
        
        int numOfTestCase = scr.nextInt();
        int index = 0;
        int valueToInsert = 0;
        
        String testCase = "Insert";
        
        for (int i = 0; i < numOfTestCase; i++) {
            if(scr.next().equals(testCase)){
                // insert element
                index = scr.nextInt();
                valueToInsert = scr.nextInt();
                
                if(index <= sizeOfList){
                    lstOfInt.add(index, valueToInsert);
                }
            } else {
                // delete element
                index = scr.nextInt();
                if(index <= sizeOfList){
                    lstOfInt.remove(index);
                }
            }
        }
        
        // Print stdout
        for (Integer integer : lstOfInt) {
            System.out.print(String.valueOf(integer) + " ");
        }
    }
}
