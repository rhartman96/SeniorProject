import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        
        if(n<= 4000 && n>=1){
        List<Integer> li = new ArrayList<Integer>();
        for(int i=0; i<n;i++){
            li.add(s.nextInt());
        }
          
        int q = s.nextInt();    
        String query = "";
            while(s.hasNext()){
                query = s.next();
            if(query.equals("Insert")){
                li.add(s.nextInt(),s.nextInt());
            }else if(query.equals("Delete")){
                li.remove(s.nextInt());
            }
            }
            
        for(int i:li){
            System.out.print(i+" ");
        }
    }  
    }
}
