package ofs.dao;

import java.sql.SQLException;

import ofs.javabean.User;

public class UserDaoJDBC extends BasicDao {
	
	
	//
	public int findMaxUid(){
		User user = null;		
		initConnection();		
		String sql = "select max(user_id) from Sys_User";
		try {
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()){
				user = new User();
				user.setUser_id(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeResultSet();
			closeStatement();
			closeConnection();
		}
		return user.getUser_id();
	}
	
	//返回及格订单评分和不及格评分订单数量
	public int[] getOrderScoreCount(int uid)
	{
		int[] scoreCount=new int[2];
		initConnection();		
		String sql = "select (select count(1) from sys_order where User_id=? and Order_Score_ToUser>60) as count1, (select count(1) from sys_order where User_id=? and Order_Score_ToUser<=60) as count2 from dual";
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, uid);
			st.setInt(2, uid);
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
	
	//返回已完成订单量以及订单评分
	public Integer[] getTotalFinishedCount(int uid)
	{
		Integer[]  scoreCount=new Integer[2];
		initConnection();		
		String sql = "select count(1) as count1,AVG(Order_Score_ToUser)  as avgscore from sys_order where  User_id=? and Order_Status=6 and Order_Score_ToUser>=0";
		try {
			st = cn.prepareStatement(sql);
			st.setInt(1, uid);
			rs = st.executeQuery();
			if (rs.next()){
				scoreCount[0]=rs.getInt("count1");	
				scoreCount[1]=rs.getInt("avgscore");		
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
