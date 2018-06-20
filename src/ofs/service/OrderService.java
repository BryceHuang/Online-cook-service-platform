package ofs.service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ofs.dao.OrderDAO;
import ofs.dao.OrderDaoJDBC;

import ofs.exception.EbpException;
import ofs.javabean.Cart;

import ofs.javabean.Order;
import ofs.javabean.User;

import org.springframework.stereotype.Service;


@Service
public class OrderService {
	@Resource
	private OrderDAO orderDAO;

	
	//保存订单
	/*
	 * public Order saveOrder(List<Cart> carts , User user , int sumAmount) throws EbpException {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		String str = df.format(date);
		Date commitTime = null;
		try {
			commitTime = new Date(df.parse(str).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Order order = new Order(commitTime, sumAmount, user);
		List<OrderList> ols = new ArrayList<OrderList>();
		for(Cart c : carts) {
			String descinfo = c.getDescinfo();
			double price = c.getPrice();
			int quantity = c.getQuantity();
			double amount = c.getAmount();
			
			OrderList ol = new OrderList(descinfo, price, quantity, amount, order);
			ols.add(ol);
		}
		
		//���涩���Ͷ�����
		int oid = orderDAO.insertOrder(order);
		orderListDAO.insertOrderList(ols);
		//order.setOrder_id(oid);
		
		return order;
	}*/
	
	//保存订单
	public int saveOrder(int uid,int orderKind,String username,String tel,String serveTime,String place,String message,Double price,String foodList,String locationCode) throws EbpException, ParseException{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date commitTime = new Date();
		Date serveTime2= null;
		serveTime2 = new Date(df2.parse(serveTime).getTime());
		// date = new Date(System.currentTimeMillis());
		//String str = df.format(date);
		/*
		 * try {
			commitTime = new Date(df.parse(str).getTime());
			serveTime2 = new Date(df2.parse(serveTime).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 * */
		
		OrderDaoJDBC orderDao =new OrderDaoJDBC();
		int oid=orderDao.insertOrder(uid,orderKind,username,tel,place,message,commitTime,serveTime2,price,foodList,locationCode);
		
		/*
		 * Order order=null;
		

		OrderDAO orderdao=new OrderDAO();
		
		order.setUser_id(uid);
		order.setOrder_id(oid);
		order.setOrder_kind(orderKind);
		order.setCustom_Name(username);
		order.setCustom_Tel(tel);
		order.setCustom_Place(place);
		order.setOrder_remark(message);
		order.setStart_time((java.sql.Date) commitTime);
		order.setServe_time((java.sql.Date) serveTime2);
		order.setIs_booked(1);
		order.setOrder_status(1);
		int a=orderdao.insertOrder(order);
		 * */
		
		return oid;
	}
	
	//获取用户的所有订单
	public List<Order> getUserOrderByUid(Integer uid){
		return orderDAO.selectOrderByUid(uid);
	}
	//
	public List<Order> getUserOrders(String username) {
		return orderDAO.selectUserOrders(username);
	}
	
	//获取所有的订单数据
	public List<Order> getAllOrders() {
		return orderDAO.selectAllOrders();
	}
	
	//获取当前在线的订单
	public List<Order> getOnlineOrder()
	{
		//OrderDaoJDBC orderDao =new OrderDaoJDBC();
		//return orderDao.getOnlineOrder();
		
		return orderDAO.selectOnlineOrders();
	}
	//获取cook自己接的单
	public List<Order> getMyServingOrder(Integer cid,Integer status1,Integer status2){
		return orderDAO.selectOrderByStatus3(cid, status1, status2);
		
		
	}
	public List<Order> getOrderByCid(Integer cid){
		return orderDAO.selectOrderByCid(cid);
	}
	
	//获取用户的订单状态为0的，即用户的申请变更的订单
	public List<Order> getChangeOrder(Integer status,String role)
	{
		if(role.equals("user"))
			return orderDAO.selectOrderByStatus4(status);
		if(role.equals("cook"))
			return orderDAO.selectOrderByStatus5(status);
		return null;
	}
	
	
	public List<Order> getOrderByStatus(int uid,Integer status)   //给用户获取订单的服务
	{
		
		return orderDAO.selectOrderByStatus(uid,status);
	}
	
	public List<Order> getOrderByStatusUser(int uid,Integer status)   //给用户获取对应状态订单的服务
	{
		
		return orderDAO.selectOrderByStatusUser(uid, status);
	}
	
	public List<Order> getOrderByStatusCook(Integer cid,Integer status)   //给用户获取订单的服务
	{
		
		return orderDAO.selectOrderByStatusCook(cid,status);
	}
	public List<Order> getCookScoringOrder(int cid){
		return orderDAO.selectCookScoringOrder(cid);
	}
	
	public List<Order> getCookChangingOrder(int cid){
		return orderDAO.selectAllChangeOrderCook(cid);
	}
	public List<Order> getOrderByIsChange(int isChange,int needAll,Integer uid){
		List<Order> orders=new ArrayList();
		if(needAll==1)
		{
			orders=orderDAO.selectAllChangeOrder(uid);
			
		}
		if(needAll==0)
		{
			orders=orderDAO.selectOrderByIsChange(isChange);
		}
		return orders;
		
	}
	public Order gerOrderById(Integer oid){
		return orderDAO.selectOrderById(oid);
	}
	
	public int updateOrderStatus(Integer oid,Integer status){
		Order order=orderDAO.selectOrderById(oid);
		order.setOrder_status(status);
		try {
			orderDAO.updateOrder(order);
			return 1;
		} catch (EbpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}
	public int updateOrder(Order order) throws EbpException{
		orderDAO.updateOrder(order);
		return 1;
	}
	
	
	
	
}

