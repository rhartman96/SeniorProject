import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int sizeL = reader.nextInt();
        List<Integer> L = new ArrayList<Integer>();
        for (int i=0;i<sizeL;i++) {
            L.add(reader.nextInt());
        }
        int q = reader.nextInt();
        reader.skip(Pattern.compile("\n"));
        for (int i=0;i<q;i++) {
            String str = reader.nextLine().trim();
         switch(str){
                case "Insert":
                    int index1 = reader.nextInt();
                    int num = reader.nextInt();
                    L.add(index1, num);
                    break;
                case "Delete":
                    int index2 = reader.nextInt();
                    L.remove(index2);
                    break;
                default:
             System.out.print("ERROR");  
                    break;
              }   
            if (reader.hasNextLine()) {
             reader.skip(Pattern.compile("\n"));
            }
        }
            Iterator it = L.iterator();
               Object elem = it.next(); 
            System.out.print(elem.toString() + " ");    
            while(it.hasNext()) {
               elem = it.next(); 
            System.out.print(elem.toString() + " ");
            }
        }}
