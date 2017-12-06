import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
public class Li {
    public static void main(String[] args) {
        int m=0,p=0;
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        ArrayList<Integer> l=new ArrayList<>();
        for(int i=0;i<n;i++){
         l.add(sc.nextInt());
        }
        int q=sc.nextInt();
        String []str=new String[q];
       for(int i=0;i<q;i++){
            str[i]=sc.next();
            if(str[i].equals("Insert")){
                int x=sc.nextInt();
                int y=sc.nextInt();
                l.add(x, y);
            }else if(str[i].equals("Delete")){
                int z=sc.nextInt();
                l.remove(l.get(z));
            }
        }
        Iterator i=l.iterator();
while(i.hasNext()){
    Integer i1=(Integer)i.next();
    System.out.print(i1+" ");
}
    }
}
