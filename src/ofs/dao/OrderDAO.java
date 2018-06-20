package ofs.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.Order;
import ofs.javabean.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	private static final String SELECT_USER_ORDER = "from Order where user.username=? order by start_time desc";
	private static final String SELECT_USER_ORDER_By_Uid = "from Order where user_id=? order by start_time desc";
	private static final String SELECT_ALL_USER = "from User";
	private static final String SELECT_OnLine_ORDER = "from Order where order_status=1 order by start_time desc";
	private static final String SELECT_ORDER_BY_OID="from Order where order_id=? order by start_time desc";
	private static final String SELECT_Going_ORDER = "from Order where user_id=? and order_status<>? order by start_time desc";
	private static final String SELECT_Done_ORDER = "from Order where  user_id=? and order_status=? order by start_time desc";
	private static final String SELECT_ALL_ORDER="from Order order by start_time desc";
	private static final String SELECT_COOK_ORDER="from Order where cook_id=? and order_status=? order by start_time desc";
	private static final String SELECT_COOK_SCORE_ORDER="from Order where cook_id=? and order_status>=4 and order_score_ToUser=Null order by start_time desc";
	private static final String SELECT_ORDER_BY_CID="from Order where cook_id=? order by start_time desc";
	private static final String SELECT_USER_CHANGE_ORDER="from Order where is_change=? and SUBSTRING(change_applicant,1,4)='user' order by start_time desc";
	//private static final String SELECT_USER_CHANGE_ORDER="from Order where is_change=? ";

	private static final String SELECT_COOK_CHANGE_ORDER="from Order where is_change=? and SUBSTRING(change_applicant,1,4)='cook' order by start_time desc";
	private static final String SELECT_CHANGE_ORDER="from Order where is_change=? order by start_time desc";
	private static final String SELECT_ALL_CHANGE_ORDER="from Order where is_change=0  and user_id=? or is_change=1  and user_id=? or is_change=2 and user_id=? order by start_time desc ";
	private static final String SELECT_ALL_CHANGE_ORDER_COOK="from Order where is_change=0  and cook_id=? or is_change=1  and cook_id=? or is_change=2 and cook_id=? order by start_time desc ";
	
	//添加
	public int insertOrder(Order order) throws EbpException {
		int oid = 0;
		try {
			oid = (Integer) hibernateTemplate.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EbpException("添加订单失败");
		}
		return oid;
	}
	
	//返回所有订单
	@SuppressWarnings("unchecked")
	public List<Order> selectUserOrders(String username) {
		return hibernateTemplate.find(SELECT_USER_ORDER , new Object[]{username});
	}
	
	//返回所有订单
		
		public List<Order> selectAllOrders() {
			return hibernateTemplate.find(SELECT_ALL_ORDER);
		}
	//返回所有在线订单
	public List<Order> selectOnlineOrders() {
		int a=1;
		@SuppressWarnings("unchecked")
		List<Order> orders = hibernateTemplate.find(SELECT_OnLine_ORDER);
		/*
		 * for(int i=0;i<orders.size();i++)
		{
			System.out.println(orders.get(i).toString());
		}*/
			
		return orders;
	}
	
	//返回用户的所有订单
	public List<Order> selectOrderByUid(Integer uid){
		return hibernateTemplate.find(SELECT_USER_ORDER_By_Uid,new Object[]{uid});
	}
	//返回对应用户id及是status的订单
	public List<Order> selectOrderByStatusUser(int uid,Integer status) {
		
		@SuppressWarnings("unchecked")
		List<Order> orders = hibernateTemplate.find(SELECT_Done_ORDER,new Object[]{uid,status});	
		//System.out.println(orders.toString()+"   "+orders.size());
		return orders;
	}
	
	//给用户返回对应状态的订单
	public List<Order> selectOrderByStatus(int uid,Integer status) {
		
		@SuppressWarnings("unchecked")
		List<Order> orders = hibernateTemplate.find(SELECT_Going_ORDER,new Object[]{uid,status});	
		//System.out.println(orders.toString()+"   "+orders.size());
		return orders;
	}
	
	//获取cook的对应状态的订单
	public List<Order> selectOrderByStatusCook(Integer cid,Integer status){
		@SuppressWarnings("unchecked")
		List<Order> orders = hibernateTemplate.find(SELECT_COOK_ORDER,new Object[]{cid,status});	
		//System.out.println(orders.toString()+"   "+orders.size());
		return orders;
	}
	public List<Order> selectCookScoringOrder(Integer cid){
		List<Order> orders=hibernateTemplate.find(SELECT_COOK_SCORE_ORDER,new Object[]{cid});
		return orders;
 	}
	public List<Order> selectOrderByCid(Integer cid){
		return hibernateTemplate.find(SELECT_ORDER_BY_CID,new Object[]{cid});
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> selectOrderByIsChange(Integer isChange){
		return hibernateTemplate.find(SELECT_CHANGE_ORDER,new Object[]{isChange});		
	}
	public List<Order> selectAllChangeOrder(Integer uid){
		return hibernateTemplate.find(SELECT_ALL_CHANGE_ORDER,new Object[]{uid,uid,uid});		
	}

	public List<Order> selectAllChangeOrderCook(Integer cid){
		return hibernateTemplate.find(SELECT_ALL_CHANGE_ORDER,new Object[]{cid,cid,cid});		
	}
	public List<Order> selectOrderByStatus2(Integer cid,Integer status){
		List<Order> orders = hibernateTemplate.find(SELECT_ALL_ORDER+"",new Object[]{cid,status});
		return orders;
	}

	
	//获取厨师自己接到的订单
	//获取cid和status为给定的两种的订单
	public List<Order> selectOrderByStatus3(int uid,Integer status1,Integer status2) {
		
		@SuppressWarnings("unchecked")
		List<Order> orders = hibernateTemplate.find(SELECT_ALL_ORDER+"where cook_id=? and order_status=? or cook_id=? and order_status=?",
				new Object[]{uid,status1,uid,status2});	
		return orders;
	}
	//获取用户申请变更的订单
	public List<Order> selectOrderByStatus4(Integer status){
		@SuppressWarnings("unchecked")
		List<Order> orders=hibernateTemplate.find(SELECT_USER_CHANGE_ORDER,new Object[]{status});
		return orders;
	}
	//获取厨师申请变更的订单
	public List<Order> selectOrderByStatus5(Integer status){
		@SuppressWarnings("unchecked")
		List<Order> orders=hibernateTemplate.find(SELECT_COOK_CHANGE_ORDER,new Object[]{status});
		return orders;
	}		
	//用订单号码查询订单
	public Order selectOrderById(Integer oid){
		@SuppressWarnings("unchecked")
		List<Order> orders=hibernateTemplate.find(SELECT_ORDER_BY_OID,new Object[]{oid});
		return orders.get(0);
	}
	
	//更新订单
	public void updateOrder(Order order) throws EbpException{
		try {
			hibernateTemplate.update(order);			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new EbpException("更改订单信息失败");
		}
	}
}
