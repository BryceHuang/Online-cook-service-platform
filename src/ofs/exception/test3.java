package ofs.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test3 {
	public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while((line=br.readLine())!=null) {
            int n = Integer.parseInt(line); //n个学生
            int[] array = new int[n];       //n个整数
            line = br.readLine();
            String[] str = line.split(" ");
            for(int i=0; i<n; i++) {
                array[i] = Integer.parseInt(str[i]);

            }
            line = br.readLine();
            String[] str2 = line.split(" ");
            int k = Integer.parseInt(str2[0]);
            int d = Integer.parseInt(str2[1]);

        }

    }
}