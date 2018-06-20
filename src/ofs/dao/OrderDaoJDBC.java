package ofs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import ofs.DbConnect.DbConnection;
import ofs.javabean.Order;
import ofs.javabean.Province;


public class OrderDaoJDBC extends BasicDao {
	
	public int findMaxOid(){
		Order order = null;		
		initConnection();		
		String sql = "select max(order_id) from Sys_Order";
		try {
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()){
				order = new Order();
				order.setOrder_id(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return order.getOrder_id();
	}
	//用户发布订单的时候
	public Integer insertOrder(int uid,int orderKind,String username,String tel,String place,String message,Date startTime,Date serveTime,Double price,String foodList,String locationCode)  //建立表格时的数据插入
	{
		
		
		//System.out.println("吃饭的地点："+place);
		Order order = null;			
		initConnection();			
		int oid=0;
		String sql = "insert into Sys_Order (user_id,custom_name,custom_place,custom_tel,order_kind,order_remark,start_time,serve_time,order_price,order_foodlist,location_code,is_booked,order_status) values (?,?,?,?,?,?,?,?,?,?,?,1,1)";
		try {
			st = cn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, uid);			
			st.setString(2, username);
			st.setString(3, place);  
			st.setString(4, tel);			
			st.setInt(5, orderKind);
			st.setString(6, message);
			st.setTimestamp(7, new Timestamp(startTime.getTime()));;
			st.setTimestamp(8, new Timestamp(serveTime.getTime()));
			st.setDouble(9, price);
			st.setString(10, foodList);
			st.setString(11, locationCode);
			st.executeUpdate();
			rs=st.getGeneratedKeys();  //获取刚插入的自增列的id
            if(rs.next()){
                oid=rs.getInt(1);
            }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		
		return oid;
	}
	List<Order> orders=new ArrayList();
	//厨师查找在线的订单 即查询自己可以接的订单 
	public List<Order> getOnlineOrder(Integer cid) throws SQLException{
		
		initConnection();
		
		String sql = "select * from sys_order where  Order_Status=1 and Order_id in (select order_id from sys_order_recieve_info where cook_id=? and order_status=1) order by start_time desc";
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, cid);
			rs = st.executeQuery();
			while(rs.next()){
				Order order=new Order();
				order.setOrder_id(rs.getInt("order_id"));
				order.setUser_id(rs.getInt("user_id"));
				order.setCook_id(rs.getInt("cook_id"));
				order.setCustom_Name(rs.getString("custom_Name"));
				order.setCustom_Place(rs.getString("custom_Place"));
				order.setCustom_Tel(rs.getString("custom_Tel"));
				order.setIs_booked(rs.getInt("is_booked"));
				order.setOrder_price(rs.getDouble("order_price"));
				order.setOrder_status(rs.getInt("order_status"));
				order.setOrder_has_material(rs.getInt("order_has_material"));
				order.setOrder_kind(rs.getInt("order_kind"));
				order.setOrder_foodlist(rs.getString("order_foodlist"));
				order.setOrder_remark(rs.getString("order_remark"));
				order.setStart_time(rs.getDate("start_time"));
				order.setServe_time(rs.getDate("serve_time"));
				order.setServe_sure_time(rs.getDate("serve_sure_time"));				
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return orders;
	}
		
	public List<Order> selectOrderByStatus(String uid,Integer status) throws SQLException{
		List<Order> orders=null;
		initConnection();
		List list = new ArrayList();
		//ResultSetMetaData md = rs.getMetaData();
		//int columnCount =md.getColumnCount();
		String sql = "select * from Sys_Order where user_id = ? and order_status<> ?";
		try {
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()){
				
				list.add("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return orders;
	}
	
	
	//订单变更和取消的时候的时候要把订单的信息变更一下
	public void updateOrderRecieveInfoOrderStatus(Integer oid,Integer status){

		initConnection();
		String sql = "update sys_order_recieve_info set order_status=? where order_id=? ";
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, status);			
			st.setInt(2, oid);
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}

	}
	
	//通过areaCode去取完整的地点的名称
	public  Map<String,String> getLocationName(String areaCode){
		Map<String,String> locationName=new HashMap<String,String>();
		initConnection();
		String sql="SELECT Province_Name,City_Name,Area_Name,sys_province.Province_Code,sys_city.City_Code,sys_area.Area_Code from sys_area,sys_city,sys_province where  Area_Code=? and sys_city.City_Code=sys_area.City_Code and sys_province.Province_Code=sys_city.Province_Code";
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, areaCode);
			rs=st.executeQuery();
			while(rs.next()){
				locationName.put("provinceName", rs.getString("Province_Name"));
				locationName.put("cityName", rs.getString("City_Name"));
				locationName.put("areaName", rs.getString("Area_Name"));
				locationName.put("provinceCode", rs.getString("Province_Code"));
				locationName.put("cityCode", rs.getString("City_Code"));
				locationName.put("areaCode", rs.getString("Area_Code"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		
		return locationName;
	}
	public int getOrderCountByStatusUser(int uid,int status){
		initConnection();
		int orderCount=0;
		String sql="select count(1) from sys_order where User_id=? and Order_Status=?";		
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, uid);
			st.setInt(2, status);
			rs = st.executeQuery();
			if (rs.next()){
				orderCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return orderCount;
	}
	public int getOrderCountByStatusCook(int cid,int status){
		initConnection();
		int orderCount=0;
		String sql="select count(1) from sys_order where cook_id=? and Order_Status=?";		
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, cid);
			st.setInt(2, status);
			rs = st.executeQuery();
			if (rs.next()){
				orderCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return orderCount;
	}
	
	public int getOrderCuisineCountSystem(String cuisine){
		initConnection();
		int orderCount=0;
		String sql="select count(1) from sys_order where substring(order_foodlist,4,2)=? ";		
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, cuisine);
			rs = st.executeQuery();
			if (rs.next()){
				orderCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return orderCount;
	}
	
	public int getOrderCuisineCountUser(String cuisine,Integer uid){
		initConnection();
		int orderCount=0;
		String sql="select count(1) from sys_order where substring(order_foodlist,4,2)=? and user_id=?";		
		try {
			st = cn.prepareStatement(sql);
			st.setString(1, cuisine);
			st.setInt(2, uid);
			rs = st.executeQuery();
			if (rs.next()){
				orderCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return orderCount;
	}
	//获取每个省份的订单数量
	public JSONArray getProvince(){
		initConnection();
		JSONArray provinceOrderCount=new JSONArray();	
		String sql="select subString(province_name,1,2) as Province_Name,count(1) as count from sys_order,sys_province where substring(sys_order.Location_Code,1,3)=substring(sys_province.Province_Code,1,3) GROUP BY substring(Location_Code,1,3) ";		
		try {
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()){
				JSONObject provinceCount=new JSONObject();
				provinceCount.put("name", rs.getString("province_name"));
				provinceCount.put("value", String.valueOf(rs.getInt("count")));
				provinceOrderCount.put(provinceCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return provinceOrderCount;
	}
	
	//获取每个省份的订单数量
		public int getOrderCount(){
			initConnection();
			int i=0;
			String sql="select count(1) from sys_order where Location_Code <> '' ";		
			try {
				st = cn.prepareStatement(sql);
				rs = st.executeQuery();
				if (rs.next()){
					i=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return i;
		}

		public int getOrderStatusByOid(Integer oid)
		{
			initConnection();
			int status=-1; //默认返回-1的值，说明是查询异常
			String sql="select Order_Status from sys_order where Order_id=? ";		
			try {
				st = cn.prepareStatement(sql);
				st.setInt(1, oid);
				rs = st.executeQuery();
				if (rs.next()){
					status=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return status;
			
		}

		public int getOrderCook(int oid,int cid)
		{
			initConnection();
			int count=0;
			String sql="update sys_order set Order_Status=2,Cook_id=?,insure_Time=now() where Order_id=?";		
			try {
				st = cn.prepareStatement(sql);
				st.setInt(1, cid);
				st.setInt(2, oid);
				count = st.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return count;
			
		}
		//select IFNULL(sum(Order_Price),0) as totalmoney,count(1) as totalcount from sys_order where DateDiff(End_Time,CURDATE()) =-1
		//查找过去?天的系统的完成订单的总金额  day赋值为-1时表示昨天
		public Double getDayTotalMoney(int day)
		{			
			initConnection();
			Double totalMoney=0.0;
			String sql="select IFNULL(sum(Order_Price),0) as totalmoney from sys_order where DateDiff(End_Time,CURDATE()) = ? ";		
			try {
				st = cn.prepareStatement(sql);
				st.setInt(1, day);
				rs = st.executeQuery();
				if(rs.next())
				{
					totalMoney=rs.getDouble("totalmoney");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return totalMoney;
		}
		
		//查找过去?天的系统的完成订单的总数量  day赋值为-1时表示昨天
		public int getDayTotalCount(int day)
		{			
			initConnection();
			int totalCount=0;
			String sql="select IFNULL(count(1),0) as totalcount from sys_order where DateDiff(End_Time,CURDATE()) = ? ";		
			try {
				st = cn.prepareStatement(sql);
				st.setInt(1, day);
				rs = st.executeQuery();
				if(rs.next())
				{
					totalCount=rs.getInt("totalcount");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return totalCount;
		}
		
		
}
