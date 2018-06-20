package ofs.exception;

import ofs.dao.CookDAOJDBC;
import ofs.dao.OrderDaoJDBC;

public class getOrderStatus2 implements Runnable {		
	static int oid;
	static int cid;
	static int isGet;
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		getOrderStatus2.oid = oid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		getOrderStatus2.cid = cid;
	}
	public void run() {
		method();
	}
	
	public static int getIsGet() {
		return isGet;
	}
	public static void setIsGet(int isGet) {
		getOrderStatus2.isGet = isGet;
	}
	public static void method() {
	      synchronized(getOrderStatus2.class) {	
	            try {
	                OrderDaoJDBC orderDao=new OrderDaoJDBC();
	                CookDAOJDBC cookDAOJDBC=new CookDAOJDBC();
	   	         	int status=orderDao.getOrderStatusByOid(oid);
	   	         	if(status!=1)
	   	         	{
	   	         		System.out.println("该订单已被其它厨师接收！");
	   	         		return;
	   	         	}
	   	         	if(status==1)
	   	         	{
	   	         		isGet=orderDao.getOrderCook(oid, cid);
	   	         		cookDAOJDBC.updateInfoOrder(oid);
	   	         	}
	               System.out.println(Thread.currentThread().getName()+"正在取订单的状态值,取得的状态为：" +status);//+ ":" + (count++)
	               Thread.sleep(1);
	            } catch (InterruptedException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	
		
}
