import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    public static void main(String[] args) {
     Scanner sc = new Scanner (System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<Integer> iList = new LinkedList<Integer>();
        //while (sc.hasNext()){
            String input[] = sc.nextLine().split(" ");
            for (int i = 0;i< input.length;i++) {
                iList.add(Integer.parseInt(input[i]));
            }
                int qN = sc.nextInt();
            sc.nextLine();
       while (sc.hasNext()){
            String action = sc.nextLine();
            if(action.equalsIgnoreCase("Insert")){
            String qA[] = sc.nextLine().trim().split(" ");
        //          System.out.println(qA[1]+"");
            int index = Integer.parseInt(qA[0]);
            Integer value = Integer.parseInt(qA[1]);
              
                iList.add(index,value);
                 
            } else if (action.equalsIgnoreCase("Delete")) {
                 
                try{
               Integer   delValue = sc.nextInt();
                  //     System.out.println(delValue+"");
                    if(delValue!= null){
                        iList.remove(delValue.intValue());
                                       }
                } catch(InputMismatchException i){
                   // System.out.println(delV+""+ i.getMessage());
                }
                
              
            }
        }
        String h = iList.toString().replaceAll(",","");
       h = h.substring(1,h.length()-1);
   
             System.out.println(h)     ;
      //  }
    }
}
