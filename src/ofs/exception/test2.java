package ofs.exception;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test2 {
	public static void main(String[] args) throws IOException {	
		int n;
		int k;
		int d1;
		int d2;
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int linenum = sc.nextInt(); 
		String str[]=new String[linenum];              
        for(int i=0;i<linenum;i++)
		{
        	str[i]=br.readLine();			
		}  
       
		for(int i=0;i<linenum;i++)
		{
			 n= str[i].charAt(0)-'0';
			 k=str[i].charAt(2)-'0';
			 d1=str[i].charAt(4)-'0';
			 d2=str[i].charAt(6)-'0';
			if(n%3!=0)
			{
				System.out.println("no");
				continue;
			}
			if(n==k+d1+d2||n==2*d1-d2+k)
			{
				System.out.println("yes");
				continue;				
			}
			else
				System.out.println("no");
		}
        
        
        sc.close();
		br.close();
        
		
	}

}
/*
 * for(int i=3;i<linenum;i++)
{
	str[i]=sc.nextLine();
}

for(int i=0;i<str[i].length();i++)
{
	int n=str[i].indexOf(0);
	int k=str[i].indexOf(2);
	int d1=str[i].indexOf(4);
	int d2=str[i].indexOf(6);
	if(n%3!=0)
	{
		System.out.println("no");
		continue;
	}
	if(n==k+d1+d2||n==2*d1-d2+k)
	{
		System.out.println("yes");
		continue;				
	}
	else
		System.out.println("no");
}
 for(int i=0;i<linenum;i++)
		{
      	System.out.println(str[i]);	
      	System.out.print(str[i].charAt(0)+"  "+str[i].indexOf(2)+"  "+str[i].indexOf(4)+"  "+str[i].indexOf(6)+"  ");	
		} */