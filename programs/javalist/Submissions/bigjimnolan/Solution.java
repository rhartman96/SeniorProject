import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        try {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            BufferedReader readList= new BufferedReader(new InputStreamReader(System.in));
            int orginial_list_size = Integer.parseInt(readList.readLine());
            LinkedList<String> list = new LinkedList<String>(Arrays.asList(readList.readLine().split(" ")));
            int operations = Integer.parseInt(readList.readLine());
            String command = readList.readLine();
            do {
                String[] values = readList.readLine().split(" ");
                switch (command) {
                    case "Insert":
                       /* if (list.size()<Integer.parseInt(values[0])) {
                            list.add
                        }*/
                        list.add(Integer.parseInt(values[0]),values[1]);
                    break;
                    case "Delete":
                        list.remove(Integer.parseInt(values[0]));
                    break;
                }
                
            } while ((command=readList.readLine())!=null);
            printList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void printList(LinkedList list) {
        if (list.size()==1) {
           System.out.print(list.pop()+"\n");
           return;
        } 
        System.out.print(list.pop()+" ");
        printList(list);
    }
}
