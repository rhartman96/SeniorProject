import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
       Scanner sc = new Scanner(System.in);
       sc.useDelimiter(System.getProperty("line.separator"));
       int n = new Integer(sc.next());
       List<String> myList = new ArrayList<String>();
       
       String list = sc.next();
       String[] seperated = list.split(" ");
       for (String number : seperated) {
           myList.add(number);
       }
       
       n = sc.nextInt();
       
       for (int i=0; i<n; i++) {
           String oper = sc.next();
           
           switch (oper) {
               case "Insert":
                   String toInsert = sc.next();
                   seperated = toInsert.split(" ");
                   int index = new Integer(seperated[0]);
                   if (index > myList.size()) {
                       myList.add(seperated[1]);
                   } else {
                       myList.add(index, seperated[1]);
                   }
                   break;
               
               case "Delete":
                   int toRemove = new Integer(sc.next());
                   myList.remove(toRemove);
                   break;
           }
       }
       
       for (String element : myList) {
           System.out.print(element + " ");
       }
    }
}