package ofs.service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ofs.dao.CookDAO;
import ofs.dao.OrderDAO;
import ofs.dao.UserDAO;
import ofs.dao.UserLoginLogDAO;
import ofs.exception.EbpException;
import ofs.id.IdCoder;
import ofs.javabean.Cook;
import ofs.javabean.Order;
import ofs.javabean.User;
import ofs.javabean.UserLoginLog;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Resource
	private UserDAO userDAO;
	@Resource 
	private OrderDAO orderDAO;
	@Resource
	private CookDAO cookDAO;
	@Resource
	private UserLoginLogDAO userLoginLogDAO;

	// 登陆查询
	public User getUser(String username) throws EbpException {
		return userDAO.selectUser(username);
	}

	// 注册
	public User register(String username, String password)
			throws EbpException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		String str = df.format(date);
		java.util.Date regTime = new java.util.Date();
		String user_id=null;
		IdCoder coder=new IdCoder();
		//user_id=coder.createUID();
		/*
		 * try {
			regTime = new Date(df.parse(str).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		User user = new User(null,username,password,1,-1,null,5,null,null,1,null,100.0,null,0.0,regTime);
		
		int uid = userDAO.insertUser(user);
		//user.setUid(uid);
		
		return user;
	}
	public User updateUser(User user) throws EbpException{
		Integer uid=user.getUser_id();
		userDAO.updateUser(user);
		return userDAO.selectUserByID(uid);
		
	}

	// 用户信息更新
	public User update(String user_name, String user_password,
			int user_gender, int user_age, String user_location,
			int user_level, String user_email, String user_tel,
			int user_status, String user_specific, double user_score,
			String user_picture, double user_money) throws EbpException {
		User user = new User();
        user.setUser_name(user_name);
        user.setUser_password(user_password);
        user.setUser_gender(user_gender);
        user.setUser_age(user_age);
        user.setUser_location(user_location);
        user.setUser_level(user_level);
        user.setUser_email(user_email);
        user.setUser_tel(user_tel);
        user.setUser_status(user_status);
        user.setUser_specific(user_specific);
        user.setUser_score(user_score);
        user.setUser_picture(user_picture);
        user.setUser_money(user_money);
		userDAO.updateUser(user);

		return user;
	}
	//用户信息更新 用户版本
	public User updateForUser(String user_name,String user_rname,String user_password,
			int user_gender, int user_age, String user_location,
			 String user_email, String user_tel,String user_specific) throws EbpException {
		User user = userDAO.selectUser(user_name);
        user.setUser_rname(user_rname);
        user.setUser_password(user_password);
        user.setUser_gender(user_gender);
        user.setUser_age(user_age);
        user.setUser_location(user_location);        
        user.setUser_email(user_email);
        user.setUser_tel(user_tel);       
        user.setUser_specific(user_specific);       
		userDAO.updateUser(user);
		return user;
	}
	//用户信息更新 用户版本
	public User updateForUser2(String user_name,String user_rname, int gender,String user_location, String user_email, String user_tel,String locationCode) throws EbpException {
		User user = userDAO.selectUser(user_name);
        user.setUser_rname(user_rname);
        user.setUser_gender(gender);    
        user.setUser_location(user_location);        
        user.setUser_email(user_email);
        user.setUser_tel(user_tel); 
        user.setLocation_code(locationCode);
		userDAO.updateUser(user);
		return user;
	}
	
	
	//用户申请变更订单操作
	public int changeOrder(Integer oid,Integer uid,String changeDetail,String applicant) throws EbpException{
		Order order=orderDAO.selectOrderById(oid);
		Date now =new Date();
		/*if(order.equals(null)||order.getChange_applicant().equals(null))
			return 0;*/
		if(order.equals(null)==false){
			order.setIs_change(0);
			order.setChange_detail(changeDetail);
			order.setChange_applicant(applicant);
			orderDAO.updateOrder(order);
			return 1;
			
		}
		
		return 1;
	}
	
	/*Cook cook=cookDAO.selectCookById(cid);
			Double newMoney=cook.getCook_money()+order.getOrder_price();
			cookDAO.updateCook(cook);
			cook.setCook_money(newMoney);*/
	//用户评分操作 评分时结束订单流程 
		public int scoreOrder(Integer oid,Double orderScore,Double cookScore,String comment) throws EbpException{
			Order order=orderDAO.selectOrderById(oid);
			Integer cid=order.getCook_id();
			
			/*if(order.equals(null)|!order.getOrder_score().equals(null)||!order.getOrder_score_ToCook().equals(null))
				return 0;*/
			if(!order.equals(null)){
				order.setOrder_score(orderScore);
				order.setOrder_score_ToCook(cookScore);
				Date now=new Date();
				order.setEnd_time(now);
				order.setOrder_status(6);
				order.setOrder_comment(comment);			
				orderDAO.updateOrder(order);				
				
				return 1;
				
			}
			
			return 1;
		}

	//根据uid获得user
	public User getUserById(Integer uid){
		return userDAO.selectUserByID(uid);
		
	}
		
	// �޸����
	 public User updateMoney(String string, double balance) throws EbpException {
		User user = userDAO.selectUser(string);
		user.setUser_money(balance);
		userDAO.updateUser(user);		
		return user;
	}

	// ��ȡ�����û�
	public List<User> getAllUser(int maxLines, int page) {
		return userDAO.selectAllUser(maxLines, page);
	}
	
	public void insertLog(String ip,String userName,Integer userType){
		UserLoginLog userLoginLog =new UserLoginLog();
		Date date=new Date();
		userLoginLog.setLogin_ip(ip);
		userLoginLog.setUser_name(userName);
		userLoginLog.setUser_type(userType);
		userLoginLog.setLogin_time(date);
		userLoginLogDAO.insertLog(userLoginLog);
	}
	

	
	// ��ȡ�ӿ�ʼ���ڵ��������ڵ��û�
	/*
	 * // ��ȡ������������ѯ��ҳ��
	public int getUserCount() {
		// ������
		int userListCnt = userDAO.selectOrderCount();
		// ��ҳ��
		int totalPage = 0;
		if (userListCnt % OrderListAction.MAXLINES == 0) {
			totalPage = userListCnt / OrderListAction.MAXLINES;
		} else {
			totalPage = userListCnt / OrderListAction.MAXLINES + 1;
		}
		return totalPage;
	}


	 * 按照时间起终查询用户
	 * public List<User> getTimeUser(int maxLines, int page, Date begin, Date end) {
		return userDAO.selectUserByTime(maxLines, page, begin, end);
	}*/

	// ��ȡ������������ѯ��ҳ��
	/*
	 * public int getTimeUserCount(Date begin, Date end) {
		// ������
		int userListCnt = userDAO.selectTimeUserCount(begin, end);

		// ��ҳ��
		int totalPage = 0;
		if (userListCnt % TicketAction.MAXLINES == 0) {
			totalPage = userListCnt / TicketAction.MAXLINES;
		} else {
			totalPage = userListCnt / TicketAction.MAXLINES + 1;
		}
		return totalPage;
	}
	
	*
	*
	*
	*	// ��ȡ��������ϲ�ѯ��ҳ��
	public int getConditionUserCount(String name, String idCard, String telno) {
		// ������
		int userListCnt = userDAO.conditionUserCount(name, idCard, telno);
		// ��ҳ��
		int totalPage = 0;
		if (userListCnt % OrderListAction.MAXLINES == 0) {
			totalPage = userListCnt / OrderListAction.MAXLINES;
		} else {
			totalPage = userListCnt / OrderListAction.MAXLINES + 1;
		}
		return totalPage;
	}
	*/

	// ��ȡ��������ϲ�ѯ�û�
	public List<User> getConditionUser(int maxLines, int page, String name,
			String idCard, String telno) {
		List<User> users = userDAO.selectConditionUser(maxLines, page, name,
				idCard, telno);
		return users;
	}



	// �޸��û�״̬
	public void updateUserStatus(String uid, int status) throws EbpException {
		// ��ѯ�û�
		User user = userDAO.selectUserById(uid);
		// �޸��û�״̬
		if(status==0) status=1;
		else status=0;
		user.setUser_status(status);
		userDAO.updateUser(user);
	}

	//获取所有的用户信息
		public List<User> getAllUsers() {
			return userDAO.selectAllUsers();
		}
}
