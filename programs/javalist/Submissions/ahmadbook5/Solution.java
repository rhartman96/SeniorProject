import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
    
    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
    String str = "";    
        String insert = "Insert";
        String delete = "Delete";
        List<Integer> listofints = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        
        if(sc.hasNext()){
            //try{
            int N = sc.nextInt(); // number of elements in the initial list
               // System.out.println(N);
           if(N<1 || N >4000){throw new Exception("Invalid value for N");}
                else{
                
                for(int i = 0; i <N; i++){
                    
                   
                    int L = sc.nextInt(); //this is the members of the list
                       
                       listofints.add(L); //integers L are then added to the arraylist
           //System.out.println(listofints.get(i));    
                
            
                }
           
                   
            int Q = sc.nextInt(); //the number of queries
                //System.out.println(Q);
                if(Q<1 ||Q > 4000){throw new Exception("Invalid value for Q");}
                else{
            int count = 0;
            int reader = 0;
                    while(sc.hasNext()){
            for(int z = 0; z<Q; z++){
               
                //if(sc.hasNextLine()){
                    
                str = sc.nextLine();//scans the string query statement either Insert or Delete
                   // System.out.println(str);
                if((!Objects.equals(str,insert))  || (!Objects.equals(str,delete))){
                    //System.out.println("true");
                    //System.out.println(str);
                   // if(Objects.equals(str,"Insert")){System.out.println("true"); System.out.println(str);}
                    /*while((!Objects.equals(str,insert))  || (!Objects.equals(str,delete))){
                        System.out.println("False");
                        str = sc.nextLine();
                        System.out.println(str);
                                             
                    }*/
                  
                }
                      //System.out.println(str);
                    
                
                    
                
                                        
                              
                    if(str.equals(insert)){ //did not use switch statement b/c causes issues with logic in the code
                    int index = sc.nextInt();
                    int insertedval = sc.nextInt();
                    listofints.add(index,insertedval);
                    }
                    
                    else if(str.equals(delete)){
                    
                    int indexfordelete = sc.nextInt();
                    //System.out.println(indexfordelete);
                    listofints.remove(indexfordelete);
                   
                    }
                }
                }
            //}catch(Exception q){System.out.println("Invalid value for Q");}
                
                
            
            for(int x = 0; x<listofints.size(); x++){
                System.out.print(listofints.get(x)+ " ");
            }
            }
               // }catch(Exception e){System.out.println("Invalid value for N");}
           
    }
}
    }
}
    
