package ofs.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ofs.dao.OrderDaoJDBC;
import ofs.exception.EbpException;
import ofs.id.Convert;
import ofs.javabean.Cook;
import ofs.javabean.NotDoneOrder;
import ofs.javabean.Order;
import ofs.javabean.Path;
import ofs.javabean.User;
import ofs.javabean.UserLoginLog;
import ofs.md5.IpUtil;
import ofs.md5.MyMD5Util;
import ofs.service.CookMoneyRecordService;
import ofs.service.CookService;
import ofs.service.MessageService;
import ofs.service.OrderService;
import ofs.service.UserMoneyRecordService;
import ofs.service.UserService;

import org.apache.commons.io.FileUtils;
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
@Namespace("/user")
@ParentPackage("struts-default")
public class UserAction extends ActionSupport {
	@Resource
	private UserService userService;
	@Resource
	private UserMoneyRecordService userMRService;
	@Resource
	private OrderService orderService;
	@Resource
    private UserMoneyRecordService userMoneyRecordService;
	@Resource
    private CookMoneyRecordService cookMoneyRecordService;
	@Resource
	private MessageService messageService;
	@Resource
	private CookService cookService;
	
	
	private String username; //用户使用的昵称
	private String password;
	private String forward;
	

	private int uid;
	private int oid; 
	private int level;      //用户级别
	private String email;
    private int age;
    private int score;
	

	private int status;
	private String name;   //取前端的用户名，用户自己取的那个
	private int gender;
	private String idCard;  //身份证号码
	private String address;
	private String telno;
	private Date regTime;
	private double money; //原来的余额
	private double addmoney; //充值的钱
	private int page;
	private Date beginTime;
	private Date endTime;

	private String upAccMethod;
	
	private String inputcheckcode;


	private File headerPicture;
	private String headerPictureFileName;
	
	public File getHeaderPicture() {
		return headerPicture;
	}

	public void setHeaderPicture(File headerPicture) {
		this.headerPicture = headerPicture;
	}

	public String getHeaderPictureFileName() {
		return headerPictureFileName;
	}

	public void setHeaderPictureFileName(String headerPictureFileName) {
		this.headerPictureFileName = headerPictureFileName;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}


	public double getAddmoney() {
		return addmoney;
	}

	public void setAddmoney(double addmoney) {
		this.addmoney = addmoney;
	}


	
	public String getInputcheckcode() {
		return inputcheckcode;
	}

	public void setInputcheckcode(String inputcheckcode) {
		this.inputcheckcode = inputcheckcode;
	}

	public String getUpAccMethod() {
		return upAccMethod;
	}

	public void setUpAccMethod(String upAccMethod) {
		this.upAccMethod = upAccMethod;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	// 登陆
	@Action(value = "loginAction", results = {
			@Result(name = "success", type = "redirect", location = "/user/userindex2.jsp"),
			@Result(name = "input", location = "/user/Login.jsp") })
	public String login() {
		User user = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkcode=(String)request.getSession().getAttribute("checkCode");
		String ip=IpUtil.getIp(request);
		try {
			if(checkcode.equals(inputcheckcode)){
				user = userService.getUser(username);
				String pwdInDb=user.getUser_password();
				Integer status=user.getUser_status();
				//boolean md5=MyMD5Util.validPassword(password, pwdInDb);			 
				if(!pwdInDb.equals(password)){
					this.addActionError("用户名或密码错误！");
					return "input";
				}
				if(status==0)
				{
					this.addActionError("该用户被禁用，请联系管理员处理！");
					return "input";
				}
			}
			else{
				this.addActionError("验证码错误!");
				return "input";
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return "input";
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		userService.insertLog(ip, username, 1);  //插入用户的ip信息
		forward = (String) session.getAttribute("loginUrl");
		if (forward == null) {
			forward = "/user/index.jsp";
		}

		return "success";
	}

	// 退出
	@Action(value = "logoutAction", results = { @Result(name = "success", location = "/user/Login.jsp") })
	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		session.removeAttribute("user");

		return "success";
	}

	// 注册
	@Action(value = "registrationAction", results = {
			@Result(name = "success", location = "/user/Login.jsp"),
			@Result(name = "input", location = "/user/Registration.jsp") })
	public String registration() {
		User user = null;
		//  �������System.out.println("���볤��̫���Ĳ���");
		try {	
			user = userService.register(username, password);
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return "input";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "success";
	}
	
	//用户申请变更
	@Action(value="changeOrderAction",results={
			@Result(name="success",location="/user/MyOrders2.jsp"),
			@Result(name = "input", location = "/user/index.jsp")})
	public String changeOrder() throws EbpException, IOException{
		HttpServletRequest req=ServletActionContext.getRequest();				
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");  
		response.setCharacterEncoding("utf-8");	
	    HttpSession session=req.getSession();
	    User user =(User)session.getAttribute("user");	    
	    Integer uid=user.getUser_id();
	    String changeDetail=req.getParameter("changedetail");  //获取用户填写的变更申请	    
		String changeApplicant="user"+String.valueOf(uid); //申请人
		userService.changeOrder(oid, uid, changeDetail, changeApplicant);	
		String title="系统消息";
		String content="您的订单号为"+oid+"的订单的变更申请已成功提交给客服处理！";			
		int messageId=messageService.insertMessage("user", "system", uid, title, content); 
        PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
		return "success";
	}
    
	//用户对订单进行评分操作 评分之后要将厨师的金额更新，包括插入厨师的金额记录 厨师的消息 用户的消息
	@Action(value="scoreOrderAction",results={
			@Result(name="success",location="/user/MyOrders2.jsp"),
			@Result(name = "input", location = "/user/MyOrders2.jsp")})
	public String socreOrder() throws EbpException, IOException{
		HttpServletRequest req=ServletActionContext.getRequest();
	 	HttpServletResponse response=ServletActionContext.getResponse();
	    response.setContentType("text/json");  
	    response.setCharacterEncoding("utf-8");
	 	Double orderScore=Double.parseDouble(req.getParameter("orderscore"));
	 	Double cookScore=Double.parseDouble(req.getParameter("cookscore"));
	 	Integer oid=Integer.parseInt(req.getParameter("scoreOrderId"));
	 	String orderComment=req.getParameter("orderComment");
	 	//MessageAction message=new MessageAction();
	 	Order order=orderService.gerOrderById(oid);
	 	Integer cookId=order.getCook_id();
	 	Integer userId=order.getUser_id();
	 	Cook cook=cookService.getCookById(cookId);
	 	Double originalMoney=cook.getCook_money();
	 	Double operateMoney=order.getOrder_price();
	 	Double finalMoney=originalMoney+operateMoney;
	 	cook.setCook_money(finalMoney);
	 	int i=userService.scoreOrder(oid, orderScore, cookScore,orderComment);
	 	cookService.updateCook2(cook);
	 	cookMoneyRecordService.insertRecord(cookId, 1, originalMoney, operateMoney, null, null);
	 	String title="系统消息";
	 	String content="您对订单号为"+oid+"的订单评分成功！";
		int messageId=messageService.insertMessage("user", "system", userId, title, content); 
        PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
        messageService.insertMessage("cook", "system", cookId, "系统消息", "您的订单号为"+oid+"的订单服务费已到账！");
		return "success";
	}
	
	// 更新用户信息
	@Action(value = "updateUserAction", results = {
			@Result(name = "success", location = "/user/UpdateUser2.jsp"),
			@Result(name = "input", location = "/user/UpdateUser2.jsp") })
	public String updateUser() {
		User user = null;
		System.out.print("Success");
		try {
			//String encryptedPwd = MyMD5Util.getEncryptedPwd(password);
			
			user = userService.updateForUser(username,name,password, gender, age,address,email ,telno ,idCard);
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return "input";
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "success";
	}
	
	
	// 更新用户信息 新版本
		@Action(value = "updateUserAction2", results = {
				@Result(name = "success", location = "/user/index.jsp"),
				@Result(name = "input", location = "/user/UpdateUser.jsp") })
		public String updateUser2() throws EbpException, IOException {
			User user = null;
			
			 HttpServletRequest request = ServletActionContext.getRequest();
			 HttpServletResponse response=ServletActionContext.getResponse();
			 response.setContentType("text/html;charset=utf-8");
			 String area=request.getParameter("area");
			 //System.out.println("--------------"+area);
			try {				
				user = userService.updateForUser2(username,name, gender,address,email ,telno,area);
			} catch (Exception e) {
				this.addActionError(e.getMessage());
				return "input";
			}			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			String content="您的个人信息已更新！";
            String title="用户信息更新提醒";
            Integer userId=user.getUser_id();
            int messageId=messageService.insertMessage("user", "system", userId, title, content);
            //session.setAttribute("newMessageId", messageId);            
            PrintWriter pw = response.getWriter();
            pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
            pw.flush();
            pw.close();
			return "success";
		}
	
	
		
	//用户充值操作
	@Action(value = "updateMoneyAction", results = {
			@Result(name = "success", location = "/user/MyPurse2.jsp"),
			@Result(name = "input", location = "/user/MyPurse2.jsp") })
	public String updateBalance() throws EbpException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8"); 
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        Integer userId=user.getUser_id();
        User user2=userService.getUserById(userId);
		Integer payKind=Integer.parseInt(request.getParameter("paykind"));
		
		Double originalMoney=user2.getUser_money();
		Double finalMoney=addmoney + originalMoney;
		user.setUser_money(finalMoney);
		user = userService.updateMoney(username, finalMoney);			
		int i=userMRService.insertRecord(uid, 1, originalMoney, addmoney, null, null,payKind);				
		session.setAttribute("user", user);
		request.setAttribute("upAccMethod", upAccMethod);
		request.setAttribute("money", finalMoney);
		String content="充值"+addmoney+"元成功，余额为"+finalMoney+"!";
        String title="系统消息";
        int messageId=messageService.insertMessage("user", "system", userId, title, content);            
        PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
		return "success";
	}
	
	//用户 支付操作
		@Action(value = "payOrderAction", results = {
				@Result(name = "success", location = "/user/MyOrders2.jsp"),
				@Result(name = "input", location = "/user/MyOrders2.jsp") })
		public String payOrder() throws EbpException, IOException {
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("text/json");  
	        response.setCharacterEncoding("utf-8");
			HttpSession session = req.getSession();
			User user=(User)session.getAttribute("user");
			Integer userId=user.getUser_id();
			User user2=userService.getUserById(userId);
			Integer payKind=Integer.parseInt(req.getParameter("paykind"));
			Integer oid=Integer.parseInt(req.getParameter("oid"));
			Double price=Double.parseDouble(req.getParameter("price"));				
			 if(payKind==1)
	            {
	            	if(user.getUser_money()<price/2)
	                {
	                	System.out.println("余额不足以支付本订单的订金，请充值或者选择其他支付方式！");
	        			String content="余额不足以支付本订单的订金，请充值或者选择其他支付方式！";
	        	        String title="系统消息";
	        	        int messageId=messageService.insertMessage("user", "system", userId, title, content);            
	        	        PrintWriter pw = response.getWriter();
	        	        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
	        	        pw.flush();
	        	        pw.close();
	                	return "input";
	                }
	            	Double originalMoney=user.getUser_money();
	            	Double operateMoney=price/2;
	            	user.setUser_money(originalMoney-price/2);
	              	userService.updateUser(user);
	              	
	            	userMoneyRecordService.insertRecord(userId, 3, originalMoney, operateMoney, null, null, payKind);
	            }
	            else if(payKind==2||payKind==3)
	            {
	            	Double originalMoney=user.getUser_money();
	            	Double operateMoney=price/2;
	            	userMoneyRecordService.insertRecord(userId, 3, originalMoney, operateMoney, null, null, payKind);
	            }	
			orderService.updateOrderStatus(oid, 5);
			session.setAttribute("user", user);
			String content="支付订单尾款"+price/2+"成功!";
	        String title="系统消息";
	        int messageId=messageService.insertMessage("user", "system", userId, title, content);            
	        PrintWriter pw = response.getWriter();
	        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
	        pw.flush();
	        pw.close();
			return "success";
		}
	
    //用户提现操作
	@Action(value="cashMoneyAction",results = {
			@Result(name = "success", location = "/user/MyPurse2.jsp"),
			@Result(name = "input", location = "/user/MyPurse2.jsp") })
	public String cashMoney() throws EbpException, IOException{
		HttpServletRequest req=ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json");  
        response.setCharacterEncoding("utf-8");
		MessageAction message=new MessageAction();
		HttpSession session = req.getSession();
		User user=(User)session.getAttribute("user");
		Integer userId=user.getUser_id();
		Double cashmoney=Double.parseDouble(req.getParameter("cashmoney"));
		Double money=user.getUser_money();
		String bankKind=req.getParameter("bankKind");
		String cardId=req.getParameter("cardId");
		if(cashmoney>money)
		{


			String content="提现金额过多，超过余额！";
	        String title="系统消息";
	        int messageId=messageService.insertMessage("user", "system", userId, title, content);            
	        PrintWriter pw = response.getWriter();
	        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
	        pw.flush();
	        pw.close();
			return "input";
					
		}
			user = userService.updateMoney(username, money-cashmoney);
			int i=userMRService.insertRecord(userId, 2, money, cashmoney, bankKind, cardId,null);
		session.setAttribute("user", user);
		req.setAttribute("upAccMethod", upAccMethod);
		req.setAttribute("money", money);
		String content="提现"+cashmoney+"元到账户"+cardId+"成功,余额"+user.getUser_money()+"!";
		String title="系统消息";
		int messageId=messageService.insertMessage("user", "system", userId, title, content); 
        PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();	
		return "success";
	}
	
	
	
	//用户取消订单操作 取消时要将预付的订金还给用户,更新信息接收表的订单状态和发送消息
	@Action(value="cancelOrderAction",results = {
			@Result(name = "success", location = "/user/MyOrders2.jsp"),
			@Result(name = "input", location = "/user/MyOrders2.jsp") })
	public String cancelOrder() throws EbpException, IOException{
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json");  
        response.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		OrderDaoJDBC orderDaoJDBC=new OrderDaoJDBC();
		User user=(User)session.getAttribute("user");
		Integer uid=user.getUser_id();
		User user2=userService.getUserById(uid);
		Integer oid =Integer.parseInt(req.getParameter("oid"));
		Double orderPrice=orderService.gerOrderById(oid).getOrder_price();
		Double originalMoney=user2.getUser_money();
		Double finalMoney=originalMoney+orderPrice/2;
		orderService.updateOrderStatus(oid, 0);
		orderDaoJDBC.updateOrderRecieveInfoOrderStatus(oid, 0);
		user2.setUser_money(finalMoney);
		userService.updateUser(user2);
		userMoneyRecordService.insertRecord(uid, 1, originalMoney, orderPrice/2, null, null, 4);
		session.setAttribute("user", user2);
		String title="系统消息";
		String content="订单取消成功！";			
		int messageId=messageService.insertMessage("user", "system", uid, title, content); 
        PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
		return "success";
	}
	
	//用户更改密码操作
	@Action(value="changePassword",results = {
			@Result(name = "success", location = "/user/UpdateUserPassword.jsp"),
			@Result(name = "input", location = "/user/UpdateUserPassword.jsp") })
	public String changePassword() throws EbpException, IOException{
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");  
	    response.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		String passwordInDb=user.getUser_password();
		Integer uid=user.getUser_id();
		String password=req.getParameter("password1");
		if(passwordInDb.equals(password)){
			this.addActionError("修改后密码与原密码一样，请重新输入！");
			return "input";
		}		
		User user2=userService.getUserById(uid);
		user2.setUser_password(password);
		userService.updateUser(user2);
		session.setAttribute("user", user2);				
		String title="系统消息";
		String content="密码修改成功！";			
		int messageId=messageService.insertMessage("user", "system", uid, title, content); 
        PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();	
		return "success";
	}
	
	//用户获取订单
	@Action(value="getMyOrderScoreCount")
	public void getMyOrderScoreCount() throws  IOException{
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");  
	    response.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		int uid=user.getUser_id();
		List<Order> orders=orderService.getUserOrderByUid(uid);		
		String  jsonString =Convert.convertListtoJsonString(orders);	
        PrintWriter pw = response.getWriter();
        pw.write(jsonString);
        pw.flush();
        pw.close();	
		return ;
	}

	
 	
 	
 	//用户上传头像的操作
 	@Action(value="updateUserHeaderPictureAction",results = {
			@Result(name = "success", location = "/user/UpdateUser2.jsp"),
			@Result(name = "input", location = "/user/UpdateUser2.jsp") })
 	public String updateUserHeaderPicture() throws EbpException, IOException
 	{
 		HttpServletRequest req=ServletActionContext.getRequest();				
	 	HttpSession session=req.getSession();
	 	User user=(User)session.getAttribute("user");
	 	int uid=user.getUser_id();
	 	User user2=userService.getUserById(uid);
	 	String basicPath=Path.getUserPath();
	 	String path=basicPath+String.valueOf(uid); 
	 	String pathInDb="images/UserImages/"+String.valueOf(uid)+"/head-icon.jpg";   //数据库里面存的相对路径，就是images之后的路径存起来
	 	user2.setUser_picture(pathInDb);
	 	userService.updateUser(user2);
	 	//System.out.println("test---------------------------");
	 	upfile(headerPicture,"head-icon.jpg",path,uid);		
	 	//System.out.println("test2222222222---------------------------");
	 	return "success";
 	}
	
	//上传文件方法
 	public boolean upfile(File file,String newFileName,String basicPath,Integer uid){

		 if(file != null){  
			 	String pathOnServer = ServletActionContext.getServletContext().getRealPath("/images")+"/UserImages/"+String.valueOf(uid)+"/"+newFileName;  			 	
			    File   dirFile;
			    boolean bFile;
			    dirFile = new File(basicPath);
		        bFile   = dirFile.exists();
		        if( bFile == true )
		        	
		        {
		          System.out.println("The folder exists.");
		        }
		        else
		        {
		          System.out.println("The folder do not exist,now trying to create a one...");
		          bFile = dirFile.mkdir();
		          if( bFile == true )
		          {
		            System.out.println("Create successfully!");
		          }
		          else
		          {
		            System.out.println("Disable to make the folder,please check the disk is full or not.");

		          }
		        }
	            File diskFile = new File(basicPath + "//" + newFileName); 
	            File serverFile=new File(pathOnServer);
 
	            try {
	            	FileUtils.copyFile(file, serverFile);   //把文件传到服务器上
					FileUtils.copyFile(file, diskFile);		//把文件传到本地固定的文件夹里面 后期此代码要删除
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
	         return true;
		 }
		 else
			 return false;
	}
 	
 	//获取订单的列表的前三条数据
 		public List<NotDoneOrder> getFirstThreeOrder2(List<NotDoneOrder> orders1)
 		{
 			List<NotDoneOrder> orders2=new ArrayList();
 			for(int i=0;i<3&&i<orders1.size();i++)
 			{
 				orders2.add(orders1.get(i));
 			}
 			return orders2;
 		}
	
}
