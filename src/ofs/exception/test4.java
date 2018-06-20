package ofs.exception;

import java.util.Scanner;

public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);       
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[]=new int[n];
        int b[]=new int[n];
        int num=0;        
        for(int i = 0; i < n; i++){
            a[i]=sc.nextInt();
            }
        for(int i=0;i<n;i++) {        	
        	for(int z=i+1;z<n;z++)
        	{
        		if(a[i]-a[z]==k||a[z]-a[i]==k)
        		{
        			b[i]=z;
        			break;
        		}        		    
        	}
         }
        for(int i = 0; i < n; i++){         
        	  if(b[i]!=0)
        		  num++;
            } 
       System.out.print(num);
        } 
    
        
    }

