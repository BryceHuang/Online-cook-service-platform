package ofs.dao;

import java.sql.SQLException;

import ofs.javabean.Order;

public class CookDAOJDBC extends BasicDao{
	
	
	//根据要求获取对应的厨师
	public int[] findCid(String location){
		int cid[]=new int[10];
		initConnection();		
		//String sql = "select  Cook_id from sys_cook where Cook_Location=? and Is_Aduit=1 ORDER BY Cook_Score desc limit 10";
		String sql="select  cook_id from sys_cook where Is_Aduit=1 and substring(location_code,1,4)=substring(?,1,4) ORDER BY Cook_Score desc limit 10";
		//String sql = "select  Cook_id from sys_cook where Is_Aduit=1 ORDER BY Cook_Score desc limit 10";
		
		try {
			int i=0;
			st = cn.prepareStatement(sql);
			st.setString(1, location);
			rs = st.executeQuery();
			while(rs.next()&&i<cid.length){				
				cid[i]=rs.getInt("cook_id");
				i++;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return cid;
		
	}
	//获取所有的用户的id
	public int[] findCid2(){
		int count=this.findCookCount();
		int cid[]=new int[count];
		initConnection();		
		String sql = "select  cook_id from sys_cook where cook_status <> 0";		
		try {
			int i=0;
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()&&i<cid.length){				
				cid[i]=rs.getInt("cook_id");
				i++;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return cid;
		
	}
	
	//获取所有的用户的id
	public int[] findUid(){
		int count=this.findUserCount();
		int uid[]=new int[count];
		initConnection();		
		String sql = "select  user_id from sys_user where user_status <> 0";		
		try {
			int i=0;
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()&&i<uid.length){				
				uid[i]=rs.getInt("user_id");
				i++;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return uid;
		
	}
	
	//获取所有的用户的id
		public int[] findSid(){
			int count=this.findServerCount();
			int sid[]=new int[count];
			initConnection();		
			String sql = "select  admin_id from sys_admin where admin_status <> 0 and admin_level>1";		
			try {
				int i=0;
				st = cn.prepareStatement(sql);
				rs = st.executeQuery();
				while(rs.next()&&i<sid.length){				
					sid[i]=rs.getInt("admin_id");
					i++;				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return sid;
			
		}
	//获取用户数量 
		public int findUserCount(){
			initConnection();					
			String sql = "select count(*) from Sys_user where user_status <> 0";
			int i=0;
			try {
				
				st = cn.prepareStatement(sql);
				rs = st.executeQuery();
				if(rs.next())
				{
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
		
		//获取厨师数量 
		public int findCookCount(){
			initConnection();					
			String sql = "select count(*) from Sys_cook where cook_status <> 0";
			int i=0;
			try {
				
				st = cn.prepareStatement(sql);
				rs = st.executeQuery();
				if(rs.next())
				{
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
		
		//获取厨师数量 
		public int findServerCount(){
			initConnection();					
			String sql = "select count(*) from Sys_admin where admin_status <> 0 and admin_level > 1";
			int i=0;
			try {
				
				st = cn.prepareStatement(sql);
				rs = st.executeQuery();
				if(rs.next())
				{
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
	
	//接单的时候要做的操作，把信息表里面的状态更新一下
		public int updateInfoOrder(Integer oid){
			initConnection();
			int count=0;
			String sql = "update sys_order_recieve_info set order_status=2 where order_id=? ";
			try {
				int i=0;
				st = cn.prepareStatement(sql);
				st.setInt(1, oid);
				count=st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			
			return count;
		}
		
		public int[] getOrderScoreCount(int cid)
		{
			int[] scoreCount=new int[2];
			initConnection();		
			String sql = "select (select count(1) from sys_order where cook_id=? and Order_Score_ToCook>60) as count1, (select count(1) from sys_order where cook_id=? and Order_Score_ToCook<=60) as count2 from dual";
			try {
				st = cn.prepareStatement(sql);
				st.setInt(1, cid);
				st.setInt(2, cid);
				rs = st.executeQuery();
				if (rs.next()){
					scoreCount[0]=rs.getInt("count1");
					scoreCount[1]=rs.getInt("count2");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return scoreCount;
		}
		public Integer[] getTotalFinishedCount(int uid)
		{
			Integer  scoreCount[]=new Integer[2];
			initConnection();		
			String sql = "select count(1) as count1,AVG(Order_Score_ToCook)  as avgscore from sys_order where  Cook_id=? and Order_Status=6 and Order_Score_ToCook>=0";
			try {
				st = cn.prepareStatement(sql);
				st.setInt(1, uid);
				rs = st.executeQuery();
				if (rs.next()){
					scoreCount[0]=rs.getInt("count1");	
					scoreCount[2]=rs.getInt("avgscore");	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				closeResultSet();
				closeStatement();
				closeConnection();
			}
			return scoreCount;
		}

}
