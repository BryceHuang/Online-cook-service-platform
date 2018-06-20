package ofs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ofs.dao.OrderDaoJDBC;
import ofs.exception.EbpException;
import ofs.javabean.AdminUser;
import ofs.javabean.Cook;
import ofs.javabean.Order;
import ofs.javabean.User;
import ofs.md5.IpUtil;
import ofs.service.AdminUserService;
import ofs.service.CookService;
import ofs.service.OrderService;
import ofs.service.UserService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/admin")
@ParentPackage("struts-default")
public class AdminUserAction extends ActionSupport {
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private UserService userService;
	@Resource
	private CookService cookService;
	@Resource
	private OrderService orderService;
	private String username;
	private String password;
	private String forward;
	
	private int page;
	private Date beginTime;
	private Date endTime;
	private String name;
	private String idCard;
	private String telno;
	
	
	private String inputcheckcode;

	

	public String getInputcheckcode() {
		return inputcheckcode;
	}

	public void setInputcheckcode(String inputcheckcode) {
		this.inputcheckcode = inputcheckcode;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	// 登陆
	@Action(value = "loginAction", results = {
			@Result(name = "success", type = "redirect", location = "/admin/index.jsp"),
			@Result(name = "input", location = "/user/Login.jsp") })
	public String login() {
		AdminUser admin = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkcode=(String) request.getSession().getAttribute("checkCode");
		String ip=IpUtil.getIp(request);
		try {
			if(checkcode.equals(inputcheckcode)){ //
				admin = adminUserService.getUser(username, password);
			}
			else{
				this.addActionError("验证码错误!");
				return "input";
			}

		} catch (EbpException e) {
			this.addActionError(e.getMessage());
			return "input";
		}
		if(!admin.getAdmin_level().equals(null)&&admin.getAdmin_level()==1)
		{
			userService.insertLog(ip, username, 4);  //插入管理员的ip信息
		}
		else
			userService.insertLog(ip, username, 3); //插入客服的ip信息
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin);

		forward = (String) session.getAttribute("loginUrl");

		return "success";
	}

	// 退出
	@Action(value = "logoutAction", results = { @Result(name = "success", location = "/user/Login.jsp"), })
	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		session.removeAttribute("admin");

		return "success";
	}
	//更新厨师信息
	@Action(value="updateCook", results = { @Result(name = "success", location = "/admin/AdminCooks.jsp"),})
	public String updateCook() throws EbpException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Integer cookId=Integer.parseInt(request.getParameter("cookId"));
		String cookName=request.getParameter("cookName");
		String cookRname=request.getParameter("cookRname");
		String cookPassword=request.getParameter("cookPassword");
		String cookLocation=request.getParameter("cookLocation");
		Integer cookGender=request.getParameter("cookGender").isEmpty() ? null:Integer.parseInt(request.getParameter("cookGender"));
		String cookTel=request.getParameter("cookTel");
		Double cookScore= request.getParameter("cookScore").isEmpty() ? null : Double.parseDouble(request.getParameter("cookScore"));
		Integer cookWorkedtime=Integer.parseInt(request.getParameter("cookWorkedtime"));
		Integer cookAge=request.getParameter("cookAge").isEmpty() ? null:Integer.parseInt(request.getParameter("cookAge"));
		String cookIdnumber=request.getParameter("cookIdnumber");
		String cookLicense=request.getParameter("cookLicense");
		String cookHealthid=request.getParameter("cookHealthid");
		Integer cookStatus=request.getParameter("cookStatus").isEmpty()? null:Integer.parseInt(request.getParameter("cookStatus"));
		String cookDesc=request.getParameter("cookDesc");
		Double cookMoney=request.getParameter("cookMoney").isEmpty() ? null:Double.parseDouble(request.getParameter("cookMoney"));
		//java.util.Date regTime=Date.valueOf(request.getParameter("regTime"));
		Cook cook=cookService.getCookById(cookId);
		cook.setCook_name(cookName);
		cook.setCook_rname(cookRname);
		cook.setCook_password(cookPassword);
		cook.setCook_location(cookLocation);
		cook.setCook_gender(cookGender);
		cook.setCook_tel(cookTel);
		cook.setCook_score(cookScore);
		cook.setCook_workedtime(cookWorkedtime);
		cook.setCook_idnumber(cookIdnumber);
		cook.setCook_license(cookLicense);
		cook.setCook_healthid(cookHealthid);
		cook.setCook_status(cookStatus);
		cook.setCook_desc(cookDesc);
		cook.setCook_money(cookMoney);
		cook.setCook_age(cookAge);
		//cook.setReg_time(regTime);
		cookService.updateCook2(cook);
	 	PrintWriter pw = response.getWriter();
		String content="您对厨师id为"+String.valueOf(cookId)+"的厨师信息更新成功！";
        String title="操作成功提醒";
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
		return "success";
	}
	//更新用户信息
	@Action(value="updateUser", results = { @Result(name = "success", location = "/admin/AdminUsers.jsp"),})
	public String updateUser() throws EbpException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String userRname = request.getParameter("userRname");
		String userPassword = request.getParameter("userPassword");
		Integer userGender =request.getParameter("userGender").isEmpty()? null:Integer.parseInt(request.getParameter("userGender"));
		Integer userAge =request.getParameter("userAge").isEmpty()? null:Integer.parseInt(request.getParameter("userAge"));
		String userLocation = request.getParameter("userLocation");
		String userEmail = request.getParameter("userEmail");
		String userTel = request.getParameter("userTel");
		Integer userStatus = request.getParameter("userStatus").isEmpty() ? null : Integer.parseInt(request.getParameter("userStatus"));
		String userSpecific = request.getParameter("userSpecific");
		Double userScore = request.getParameter("userScore").isEmpty() ? null: Double.parseDouble(request.getParameter("userScore"));
		Double userMoney = request.getParameter("userMoney").isEmpty() ? null: Double.parseDouble(request.getParameter("userMoney")) ;
		//Date regTime = request.getParameter("regTime");
		User user=userService.getUserById(userId);		
		user.setUser_id(userId);
		user.setUser_name(userName);
		user.setUser_rname(userRname);
		user.setUser_password(userPassword);
		user.setUser_gender(userGender);
		user.setUser_age(userAge);
		user.setUser_location(userLocation);
		user.setUser_email(userEmail);
		user.setUser_tel(userTel);
		user.setUser_status(userStatus);
		user.setUser_specific(userSpecific);
		user.setUser_score(userScore);
		user.setUser_money(userMoney);
		userService.updateUser(user);
	 	PrintWriter pw = response.getWriter();
		String content="您对用户id为"+String.valueOf(userId)+"的用户信息更新成功！";
        String title="操作成功提醒";
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
		return "success";
	}
	//更新客服信息
		@Action(value="updateServerAction",results={
				 @Result(name="success",location="/admin/AdminServers.jsp"),
	 			 @Result(name="input",location="/admin/AdminServers.jsp")})
		public String updateServer() throws EbpException, IOException{
		 	HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			Integer adminId=Integer.parseInt(request.getParameter("adminId"));
			String adminName = request.getParameter("adminName");
			String adminPassword = request.getParameter("adminPassword");
			String adminRname = request.getParameter("adminRname");
			Integer adminStatus = request.getParameter("adminStatus").isEmpty() ? null : Integer.parseInt(request.getParameter("adminStatus"));
			Integer adminLevel = request.getParameter("adminLevel").isEmpty() ? null :Integer.parseInt(request.getParameter("adminLevel"));
			Integer adminGroup=Integer.parseInt(request.getParameter("adminGroup"));
			AdminUser server=adminUserService.getAdminById(adminId);
			server.setAdmin_name(adminName);
			server.setAdmin_password(adminPassword);
			server.setAdmin_rname(adminRname);
			server.setAdmin_status(adminStatus);
			server.setAdmin_level(adminLevel);
			server.setAdmin_group(adminGroup);
			adminUserService.updateAdminUser(server);
		 	PrintWriter pw = response.getWriter();
			String content="您对用户号为"+String.valueOf(adminId)+"的用户信息更新成功！";
            String title="操作成功提醒";
            pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
            pw.flush();
            pw.close();
			return "success";
			
		}
	//更新订单信息
	@Action(value="updateOrder", results = { @Result(name = "success", location = "/admin/AdminOrders.jsp"),})
	public String updateOrder() throws EbpException, ParseException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Integer orderId=Integer.parseInt(request.getParameter("orderId"));
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer cookId = request.getParameter("cookId").isEmpty() ? null:Integer.parseInt(request.getParameter("cookId"));
		String customName = request.getParameter("customName");
		String customPlace = request.getParameter("customPlace");
		String customTel = request.getParameter("customTel");
		Integer isBooked = Integer.parseInt(request.getParameter("isBooked"));
		Double orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
		Integer orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
		Integer orderKind =request.getParameter("orderKind").isEmpty()? null: Integer.parseInt(request.getParameter("orderKind"));
		Integer orderHasMeterial=request.getParameter("orderHasMeterial").isEmpty()?null: Integer.parseInt(request.getParameter("orderHasMeterial"));
		Double orderScoreToCook=request.getParameter("orderScoreToCook").isEmpty()?null:Double.parseDouble(request.getParameter("orderScoreToCook"));
		Double orderScoreToUser=request.getParameter("orderScoreToUser").isEmpty()?null:Double.parseDouble(request.getParameter("orderScoreToUser"));
		String changeApplicant=request.getParameter("changeApplicant");
		String orderFoodlist = request.getParameter("orderFoodlist");
		String orderRemark = request.getParameter("orderRemark");
		String orderComment = request.getParameter("orderComment");
		Double orderScore = request.getParameter("orderScore").isEmpty() ? null : Double.parseDouble(request.getParameter("orderScore"));
		String startTime = request.getParameter("startTime");
		String serveTime = request.getParameter("serveTime");
		String insureTime = request.getParameter("insureTime");
		Integer isChange = request.getParameter("isChange").isEmpty()? null: Integer.parseInt(request.getParameter("isChange"));
		Integer changePerson =request.getParameter("changePerson").isEmpty() ? null:Integer.parseInt(request.getParameter("changePerson"));
		String changeTime = request.getParameter("changeTime");
		String changeDetail = request.getParameter("changeDetail");		
		SimpleDateFormat dateform=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Order order=orderService.gerOrderById(orderId);	
		order.setOrder_has_material(orderHasMeterial);
		order.setOrder_score_ToCook(orderScoreToCook);
		order.setOrder_score_ToUser(orderScoreToUser);
		order.setChange_applicant(changeApplicant);
		order.setUser_id(userId);
		order.setCook_id(cookId);
		order.setCustom_Name(customName);
		order.setCustom_Place(customPlace);
		order.setCustom_Tel(customTel);
		order.setIs_booked(isBooked);
		order.setOrder_price(orderPrice);
		order.setOrder_status(orderStatus);
		order.setOrder_kind(orderKind);
		order.setOrder_foodlist(orderFoodlist);
		order.setOrder_remark(orderRemark);
		order.setOrder_comment(orderComment);
		order.setOrder_score(orderScore);
		order.setStart_time(startTime.isEmpty()? null:dateform.parse(startTime));
		order.setServe_time(serveTime.isEmpty()? null:dateform.parse(serveTime));
		order.setInsure_time(insureTime.isEmpty()? null:dateform.parse(insureTime));
		order.setIs_change(isChange);
		order.setChange_person(changePerson);
		order.setChange_person(changePerson);
		order.setChange_time(changeTime.isEmpty()? null:dateform.parse(changeTime));
		order.setChange_detail(changeDetail);
		orderService.updateOrder(order);
	 	PrintWriter pw = response.getWriter();
		String content="您对订单号为"+String.valueOf(orderId)+"的订单信息更新成功！";
        String title="操作成功提醒";
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
		return "success";
	}
	
	
	//获取各个省份的订单数量
	@Action(value = "getCountryOrderCountAction")
	public void getCountryOrderCount() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		JSONArray countryOrderCount=new JSONArray();
		OrderDaoJDBC orderCount=new OrderDaoJDBC();
		countryOrderCount=orderCount.getProvince();
		
		for(int i=0;i<countryOrderCount.length();i++)
		{
			JSONObject a=(JSONObject) countryOrderCount.get(i);
			if(a.get("name").equals("内蒙"))
			{
				a.put("name", "内蒙古");
				countryOrderCount.put(i, a);
			}
			if(a.get("name").equals("黑龙"))
			{
				a.put("name", "黑龙江");
				countryOrderCount.put(i, a);
			}
		}
		JSONObject b=new JSONObject();
		b.put("totalCount", orderCount.getOrderCount());
		countryOrderCount.put(b);
		PrintWriter pw = response.getWriter();
		pw.write(countryOrderCount.toString());
        pw.flush();
        pw.close();

	}
	
	//获取各个菜系的订单数量
	@Action(value = "getCuisineOrderCountAction")
	public void getCuisineOrderCount() throws IOException{

		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		JSONArray countryOrderCount=new JSONArray();
		OrderDaoJDBC orderCount=new OrderDaoJDBC();		
		JSONObject orderCount1=new JSONObject();
		JSONObject orderCount2=new JSONObject();
		JSONObject orderCount3=new JSONObject();
		JSONObject orderCount4=new JSONObject();
		JSONObject orderCount5=new JSONObject();
		JSONObject orderCount6=new JSONObject();
		JSONObject orderCount7=new JSONObject();
		JSONObject orderCount8=new JSONObject();		
		orderCount1.put("value", orderCount.getOrderCuisineCountSystem("川菜"));
		orderCount1.put("name", "川菜");
		orderCount2.put("value", orderCount.getOrderCuisineCountSystem("鲁菜"));
		orderCount2.put("name", "鲁菜");
		orderCount3.put("value", orderCount.getOrderCuisineCountSystem("粤菜"));
		orderCount3.put("name", "粤菜");
		orderCount4.put("value", orderCount.getOrderCuisineCountSystem("苏菜"));
		orderCount4.put("name", "苏菜");
		orderCount5.put("value", orderCount.getOrderCuisineCountSystem("浙菜"));
		orderCount5.put("name", "浙菜");
		orderCount6.put("value", orderCount.getOrderCuisineCountSystem("闽菜"));
		orderCount6.put("name", "闽菜");
		orderCount7.put("value", orderCount.getOrderCuisineCountSystem("湘菜"));
		orderCount7.put("name", "湘菜");
		orderCount8.put("value", orderCount.getOrderCuisineCountSystem("徽菜"));
		orderCount8.put("name", "徽菜");
		countryOrderCount.put(orderCount1);
		countryOrderCount.put(orderCount2);
		countryOrderCount.put(orderCount3);
		countryOrderCount.put(orderCount4);
		countryOrderCount.put(orderCount5);
		countryOrderCount.put(orderCount6);
		countryOrderCount.put(orderCount7);
		countryOrderCount.put(orderCount8);
		PrintWriter pw = response.getWriter();
		pw.write(countryOrderCount.toString());
        pw.flush();
        pw.close();

	}
	
	
//获取过去七天的系统的订单成交总额
	@Action(value = "getTotalMoneyAction")
	public void getTotalMoney() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		JSONArray totalMoney=new JSONArray();
		OrderDaoJDBC orderDao=new OrderDaoJDBC();		
		for(int i=-7;i<0;i++)
		{
			totalMoney.put(orderDao.getDayTotalMoney(i));
		}
		PrintWriter pw = response.getWriter();
		pw.write(totalMoney.toString());
        pw.flush();
        pw.close();
	}
	

/*
 * 	// 显示所有用户
	@Action(value = "allUserAction", results = { @Result(name = "success", location = "/admin/ShowAllUsers.jsp") })
	public String allUserList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("page", page);
		List<User> users = new ArrayList<User>();
		// ��ҳ��
		int totalPage = 0;
		// ��ѯ���ж���
		totalPage = userService.getUserCount();
		users = userService.getAllUser(OrderListAction.MAXLINES, page);

		session.setAttribute("totalPage", totalPage);
		session.setAttribute("users", users);
		session.setAttribute("maxLines", OrderListAction.MAXLINES);

		return "success";
	}*/

/*
 * 	// 所有时间用户
	@Action(value = "allTimeUserAction", results = { @Result(name = "success", location = "/admin/ShowAllUsers.jsp") })
	public String allTimeUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		session.setAttribute("page", page);
		session.setAttribute("beginTime", beginTime);
		session.setAttribute("endTime", endTime);

		int totalPage = 10; //userService.getTimeUserCount(beginTime, endTime);
		List<User> users = userService.getAllUser(totalPage, totalPage);
		//getTimeUser(OrderListAction.MAXLINES,page, beginTime, endTime);

		session.setAttribute("totalPage", totalPage);
		session.setAttribute("users", users);
		session.setAttribute("maxLines", OrderListAction.MAXLINES);

		return "success";
	}
*/
	/*// 按条件查询
	@Action(value = "conditionUserAction", results = { @Result(name = "success", location = "/admin/ShowAllUsers.jsp") })
	public String conditionUsers() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("page", page);

		session.setAttribute("name", name);
		session.setAttribute("idCard", idCard);
		session.setAttribute("telno", telno);

		List<User> users = new ArrayList<User>();
		// ��ҳ��
		int totalPage = 0;
		// ��������ѯ�����û�
		totalPage = userService.getConditionUserCount(name, idCard, telno);
		users = userService.getConditionUser(OrderListAction.MAXLINES, page,
				name, idCard, telno);

		session.setAttribute("totalPage", totalPage);
		session.setAttribute("users", users);
		session.setAttribute("maxLines", OrderListAction.MAXLINES);

		return "success";
	}*/
}
