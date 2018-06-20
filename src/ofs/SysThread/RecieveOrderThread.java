package ofs.SysThread;

import ofs.dao.CookDAOJDBC;
import ofs.dao.OrderDaoJDBC;
import ofs.exception.getOrderStatus2;

public class RecieveOrderThread {	
	static int oid;
	static int cid;
	static int isGet;	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		RecieveOrderThread.oid = oid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		RecieveOrderThread.cid = cid;
	}
	public void run() throws Exception {
		boolean flag = true;
		while(flag) {
		flag = method();		
		}
	}		
		
	public int getIsGet() {
		return isGet;
	}
	public void setIsGet(int isGet) {
		RecieveOrderThread.isGet = isGet;
	}
	public static boolean method() throws Exception {
	      synchronized(getOrderStatus2.class) {	
	            try {
	                OrderDaoJDBC orderDao=new OrderDaoJDBC();
	                CookDAOJDBC cookDAOJDBC=new CookDAOJDBC();
	   	         	int status=orderDao.getOrderStatusByOid(oid);
	   	         	if(status!=1)
	   	         	{	   	         		
	   	         		isGet=0;
	   	         		System.out.println("该订单已被其它厨师接收！");
	   	         		return false;
	   	         	}
	   	         	if(status==1)
	   	         	{
	   	         		isGet=1;
	   	         		orderDao.getOrderCook(oid, cid);
	   	         		cookDAOJDBC.updateInfoOrder(oid);
	   	         		System.out.println(Thread.currentThread().getName()+"正在取订单的状态值,取得的状态为：" +status);//+ ":" + (count++)
	   	         		return false;
	   	         	}	              
	               Thread.sleep(500);
	            } catch (InterruptedException e) {	          
	               e.printStackTrace();
	               throw new Exception("厨师接单的线程出错，请联系管理员处理！");
	            }	            
	               return true;
	         }
	      }
	
}
