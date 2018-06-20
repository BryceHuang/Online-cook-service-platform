package ofs.id;

import java.io.IOException;

import ofs.action.MessageAction;
import ofs.dao.CookDAOJDBC;
import ofs.dao.OrderDaoJDBC;
import ofs.exception.EbpException;

public class test {

	public static void main(String[] args) throws EbpException, IOException {
		// TODO Auto-generated method stub
		//MessageAction message=new MessageAction();
		//message.useMessageBox("user", "system", 2, "系统消息", "订单取消成功！");
		OrderDaoJDBC count=new OrderDaoJDBC();
		System.out.print(count.getOrderCountByStatusUser(1,1));

	}

}
