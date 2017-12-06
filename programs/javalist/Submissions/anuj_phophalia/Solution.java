import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        List<String> list = new ArrayList<String>();
        List<String> finalList = new ArrayList<String>();
        
        Scanner  sc = new Scanner(System.in);
        int size = sc.nextInt();
        
        while(sc.hasNext()){
            list.add(sc.nextLine());
        }
        
        for(int i=0;i<list.size()-1;i++){
            
            if(i == 1){
                String str = list.get(i);
                String[] strArr = str.split("\\s");
                for(String s: strArr){
                    finalList.add(s);    
                }  
            }
            if(list.get(i).equals("Insert")){
                String tmp = list.get(i+1);
                String[] strArr1 = tmp.split("\\s");
                String loc = strArr1[0];
                String ele = strArr1[1];
                finalList.add(Integer.parseInt(loc),ele);
                
            }
            if(list.get(i).equals("Delete")){
                String delEle = list.get(i+1);                
                finalList.remove(Integer.parseInt(delEle));
            }
            
        }
        String res = "";
        for(int j=0;j<finalList.size();j++){
            if(j == 0){
                res = finalList.get(j);
            }else{
                res = res + " " + finalList.get(j);    
            }
            
        }
        
        System.out.println(res);
    }
}
