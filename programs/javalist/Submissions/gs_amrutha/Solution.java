import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.InputStream;
import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        LinkedList<Integer> list = new LinkedList();
        int size = scanner.nextInt();
        for(int i=0; i<size; i++){
            list.add(scanner.nextInt());
           
        }
        int queries = scanner.nextInt();
        //String query = scanner.nextLine();
        for(int l=0; l<(2*queries); l++){
            String query = scanner.nextLine();
            if(query.equalsIgnoreCase("Insert")) {
                int index = scanner.nextInt();
                list.add(index, scanner.nextInt());
            
            } else if(query.equalsIgnoreCase("Delete")) {
                while(scanner.hasNextInt()) list.remove(scanner.nextInt());
            }
        }
        
        
        for(int j=0; j<size; j++){
            System.out.print(list.get(j) +" ");
        }
        
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
