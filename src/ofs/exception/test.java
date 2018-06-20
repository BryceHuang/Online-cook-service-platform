package ofs.exception;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class test {

	static String uid=null;
	public static void main(String[] args) {
		int idnum=2;
		if(idnum>=0&&idnum/10<1)
		{
			uid="user000".toString()+String.valueOf(idnum);
		}
		if(idnum>9&&idnum/100<1)
		{
			uid="user000"+String.valueOf(idnum);
		}
		if(idnum/1000<1)
		{
			uid="user00"+String.valueOf(idnum);
		}
		if(idnum/10000<1)
		{
			uid="user0"+String.valueOf(idnum);
		}
		else 
			uid="user"+String.valueOf(idnum);
		System.out.println("user000".toString()+String.valueOf(idnum));
		System.out.println(idnum/10);
		System.out.println(idnum/10<1);
		System.out.println(uid);
	}

	
}
