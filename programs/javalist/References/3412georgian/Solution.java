import java.util.*;
class Solution
{
public static void main(String args[])
{
	ArrayList al=new ArrayList();
	Scanner sc=new Scanner(System.in);
	int n=sc.nextInt();
	for(int i=0;i<n;i++)
	{
		int x1=sc.nextInt();
		al.add(x1);
	}
	int q=sc.nextInt();
	for(int i=0;i<q;i++)
	{
		String s=sc.next();
		if(s.equals("Insert"))
		{
			int x=sc.nextInt();
			int y=sc.nextInt();
			al.add(x,y);
		}
		else
		{
			int x=sc.nextInt();
			al.remove(x);
		}
	}
	for(int i=0;i<n;i++)
	{
		System.out.print(al.get(i)+" ");
	}
}
}