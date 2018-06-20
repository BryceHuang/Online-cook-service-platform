package ofs.id;

import org.hibernate.Session;

import com.mysql.jdbc.Connection;

import ofs.dao.*;

import ofs.exception.EbpException;

public class IdCoder {
	 public static void main(String args[]) {
		 
	 }
	
	 
	 
	 
	 
	 /*public String createUID() throws EbpException{
		String uid=null;
		UserDaoJDBC userdao=new UserDaoJDBC();		 
		int maxUid=null;
		maxUid = userdao.findMaxUid();
		if(maxUid.equals(null))
			maxUid="user1";
		int firstnum=0;
		for(int i=0;i<maxUid.length();i++)
		{
			char a=maxUid.charAt(i);
			if(a>='0'&&a<='9')
			{
				 firstnum = i;
				 break;
			}
			
		}
		int  idnum=Integer.parseInt(maxUid.substring(firstnum, maxUid.length()));
		idnum++;		 
		uid="user"+String.valueOf(idnum);
	    return uid;*/
	 
	 
		/*
		 * if(idnum>=0&&idnum/10<1)
		{
			uid="user0000"+String.valueOf(idnum);
		}
		if(idnum>=10&&idnum/100<1)
		{
			uid="user000"+String.valueOf(idnum);
		}
		if(idnum>=100&&idnum/1000<1)
		{
			uid="user00"+String.valueOf(idnum);
		}
		if(idnum>=1000&&idnum/10000<1)
		{
			uid="user0"+String.valueOf(idnum);
		}
		else
		 */ 
	 }
	 //
	/*
	 *  public String createOID() throws EbpException{
		String oid=null;
		OrderDaoJDBC orderdao=new OrderDaoJDBC();		 
		String maxOid=null;
		maxOid=orderdao.findMaxOid();
		if(maxOid.equals(null))
			maxOid="Order1";
		int firstnum=0;
		for(int i=0;i<maxOid.length();i++)
		{
			char a=maxOid.charAt(i);
			if(a>='0'&&a<='9')
			{
				 firstnum = i;
				 break;
			}
			
		}
		int  idnum=Integer.parseInt(maxOid.substring(firstnum, maxOid.length()));
		idnum++;		 
		oid="order"+String.valueOf(idnum);
	    return oid;
	
	 }*/
	 
