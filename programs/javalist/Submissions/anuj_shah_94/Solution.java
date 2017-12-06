import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args)throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List list = new ArrayList();
        String line = br.readLine();
        int initializeCapacity = Integer.parseInt(line);
        line = br.readLine();
        String[] numbers = new String[initializeCapacity];
        while(true){
            numbers = line.split(" ");
            if(numbers.length == initializeCapacity ){
                break;
            }
            line = br.readLine();
        }
        for(int i = 0 ; i < initializeCapacity ; i++){
            list.add(numbers[i]);
        }
        
        while((line = br.readLine()) != null){
            if(line.equals("Insert")){
                line = br.readLine();
                String[] temp = line.split(" ");
                if(temp.length == 2){
                    list.add(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                }
            } else if(line.equals("Delete")) {
                line = br.readLine();
                String[] temp = line.split(" ");
                if(temp.length == 1){
                    list.remove(Integer.parseInt(temp[0]));
                } 
            }
        }
        for(int i = 0 ; i < list.size() ; i++){
            System.out.print(list.get(i) +" ");
        }
    }
        
}
