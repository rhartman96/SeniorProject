import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
                Scanner sc =new Scanner(System.in);
        int n=Integer.parseInt(sc.nextLine());
        ArrayList<Integer> data=new ArrayList<>();
            String s=sc.nextLine();
            String [] masS=s.split("\\s");
            for (int j=0;j<masS.length;j++)
            {
                data.add(Integer.parseInt(masS[j]));
            }

        int q=Integer.parseInt(sc.nextLine());
        for (int i=0;i<q;i++){
            String command=sc.nextLine();
            if (command.equals("Delete")){
                data.remove(Integer.parseInt(sc.nextLine()));
            }
            if (command.equals("Insert")){
                int x=sc.nextInt();
                int y=sc.nextInt();
                sc.nextLine();
                data.add(x,y);
            }
        }
        for(int i=0;i<data.size();i++) System.out.print(data.get(i)+" ");
        System.out.println();
    }
}