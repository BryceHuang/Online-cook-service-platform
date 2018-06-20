package ofs.exception;

import java.util.List;
import java.util.Scanner;

import ofs.dao.CookDAO;
import ofs.dao.OrderDAO;
import ofs.dao.UserDAO;
import ofs.javabean.Cook;
import ofs.javabean.Order;
import ofs.javabean.User;

public class test5 {

	public static void main(String[] args) throws EbpException {
		CookDAO udao=new CookDAO();
		Cook user=udao.selectCook("cook");
		System.out.print(user.toString());
	}

	
}
