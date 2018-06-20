package ofs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import ofs.dao.CookDAOJDBC;
import ofs.dao.OrderDaoJDBC;
import ofs.exception.EbpException;
import ofs.javabean.AdminUser;
import ofs.javabean.Cook;
import ofs.javabean.Order;
import ofs.javabean.User;
import ofs.javabean.UserMessage;
import ofs.service.AdminUserService;
import ofs.service.CookService;
import ofs.service.MessageService;
import ofs.service.OrderService;
import ofs.service.ServerService;
import ofs.service.UserMoneyRecordService;
import ofs.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/server")
@ParentPackage("struts-default")
public class ServerAction extends ActionSupport {
	@Resource
	private CookService cookService;
	@Resource
	private ServerService serverService;
	@Resource
	private AdminUserService adminUserService;
	@Resource
    private UserMoneyRecordService userMoneyRecordService;
	@Resource
	private OrderService orderService;
	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;
	
	
	private Integer cid;
	private String data;
	private String forward;
	private String inputcheckcode;
	
	public String getInputcheckcode() {
		return inputcheckcode;
	}
	public void setInputcheckcode(String inputcheckcode) {
		this.inputcheckcode = inputcheckcode;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	// 登陆
	@Action(value = "loginAction", results = {
			@Result(name = "success", type = "redirect", location = "/server/index.jsp"),
			@Result(name = "input", location = "/user/Login.jsp") })
	public String login() throws EbpException {
		AdminUser server = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkcode=(String)request.getSession().getAttribute("checkCode");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		server=adminUserService.getUser2(username, password);
		String pwdInDb=server.getAdmin_password();
		
		try {
			if(checkcode.equals(inputcheckcode)){
				
				//boolean md5=MyMD5Util.validPassword(password, pwdInDb);			 
				if(!pwdInDb.equals(password)){
					this.addActionError("用户名或密码错误！");
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
		session.setAttribute("server", server);
		forward = (String) session.getAttribute("loginUrl");
		

		return "success";
	}

	//审核操作
	@Action(value = "AduitAction", results ={@Result(name="success",location="/server/AduitCooks.jsp")})
	public String aduitCook() throws IOException, EbpException {
	 	HttpServletRequest req=ServletActionContext.getRequest();
	 	HttpServletResponse resp=ServletActionContext.getResponse();
	 	HttpSession session=req.getSession();
	 	AdminUser server=(AdminUser)session.getAttribute("server");
	 	Integer sid=server.getAdmin_id();
		Integer cid=Integer.parseInt(req.getParameter("cid"));
		Integer aduitStatus=Integer.parseInt(req.getParameter("aduitStatus"));
		String aduitDetail=req.getParameter("aduitDetail");
		serverService.aduitCook(sid, cid, aduitStatus, aduitDetail);
		int messageId=messageService.insertMessage("server", "system", sid, "系统消息", "您对厨师用户"+String.valueOf(cid)+"的信息审核操作成功！"); 
		if(aduitStatus==1)
		{
			int messageId2=messageService.insertMessage("cook", "system", cid, "系统消息", "您的厨师身份认证信息被审核通过！"); 
		}
		else if(aduitStatus==0)
		{
			int messageId2=messageService.insertMessage("cook", "system", cid, "系统消息", "您的厨师身份认证信息未被审核通过！"); 
		}

		
		/*		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		PrintWriter out = resp.getWriter();
		 * out.print(data);
		out.flush();
		out.close();*/
		return "success";
		
		
				}
	
	//变更受理操作 要将金额返还给用户，还要更新订单的状态 包括在信息表里面的  还有把消息发送给厨师和用户 金额操作还要写到金额记录表里面
	@Action(value ="ChangeOrder",results = {@Result(name="success",location="/server/ChangeOrderCook.jsp")})
	public String changeOrder() throws EbpException, IOException{
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json");  
        response.setCharacterEncoding("utf-8");
	 	HttpSession session=req.getSession();	 	
		OrderDaoJDBC orderDaoJDBC=new OrderDaoJDBC();
	 	AdminUser server=(AdminUser)session.getAttribute("server");
	 	Integer oid=Integer.parseInt(req.getParameter("oid"));
	 	Integer sid=server.getAdmin_id();
	 	Integer isChange=Integer.parseInt(req.getParameter("isChange"));
	 	String changeDetail=req.getParameter("changeDetail"); 
	 	Order order=orderService.gerOrderById(oid);
	 	Integer cookId=order.getCook_id();
	 	Double orderPrice=order.getOrder_price();
	 	Integer userId=order.getUser_id();
	 	User user=userService.getUserById(userId);
	 	Double finalMoney=user.getUser_money()+orderPrice/2;
		if(isChange==1)
		{
			serverService.changeOrder(oid, sid, isChange, changeDetail);
			orderDaoJDBC.updateOrderRecieveInfoOrderStatus(oid, 0);
			userMoneyRecordService.insertRecord(userId, 1, user.getUser_money(), orderPrice/2, null, null, 4);
			user.setUser_money(finalMoney);
			userService.updateUser(user);	

		 	String title="系统消息";
		 	String content="您将订单号为的"+oid+"订单变更为未接受状态成功!";
			int messageId=messageService.insertMessage("server", "system", sid, title, content); 
	        PrintWriter pw = response.getWriter();
	        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
	        pw.flush();
	        pw.close();
	        messageService.insertMessage("user", "system", userId, "系统消息", "您的订单号为的"+oid+"订单变更为未接受状态成功!");   
	        messageService.insertMessage("cook", "system", cookId, "系统消息", "您接收的订单号为的"+oid+"订单的变更已受理通过!"); 
			
		}
		if(isChange==2)
		{ 
			serverService.changeOrder(oid, sid, isChange, changeDetail);

		 	String title="系统消息";
		 	String content="您对订单号为的"+oid+"的订单的变更处理成功，订单的状态未被改变!";
			int messageId=messageService.insertMessage("server", "system", sid, title, content); 
	        PrintWriter pw = response.getWriter();
	        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
	        pw.flush();
	        pw.close();
	        messageService.insertMessage("cook", "system", cookId, "系统消息", "您接收的订单号为的"+oid+"订单的变更未被通过!");
		}
		return "success";
		
	}
	//变更受理操作
	@Action(value ="ChangeOrder2",results = {@Result(name="success",location="/server/ChangeOrderUser.jsp")})
	public String changeOrder2() throws EbpException, IOException{
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json");  
        response.setCharacterEncoding("utf-8");
	 	HttpSession session=req.getSession();	 	
		OrderDaoJDBC orderDaoJDBC=new OrderDaoJDBC();
		MessageAction message=new MessageAction();
	 	AdminUser server=(AdminUser)session.getAttribute("server");
	 	Integer oid=Integer.parseInt(req.getParameter("oid"));
	 	Integer sid=server.getAdmin_id();
	 	Integer isChange=Integer.parseInt(req.getParameter("isChange"));
	 	String changeDetail=req.getParameter("changeDetail"); 
	 	Order order=orderService.gerOrderById(oid);
	 	Integer cookId=order.getCook_id();
	 	Double orderPrice=order.getOrder_price();
	 	Integer userId=order.getUser_id();
	 	User user=userService.getUserById(userId);
	 	Double finalMoney=user.getUser_money()+orderPrice/2;
	 	
	 	System.out.println("test---------------------");
	 	System.out.println(cookId+"   "+oid);
		if(isChange==1)
		{
			serverService.changeOrder(oid, sid, isChange, changeDetail);
			orderDaoJDBC.updateOrderRecieveInfoOrderStatus(oid, 0);
			userMoneyRecordService.insertRecord(userId, 1, user.getUser_money(), orderPrice/2, null, null, 4);
			user.setUser_money(finalMoney);
			userService.updateUser(user);				
			messageService.insertMessage("user", "system", userId, "系统消息", "您的订单号为的"+oid+"订单的变更申请已受理通过!");   
			messageService.insertMessage("cook", "system", cookId, "系统消息", "您接收的订单号为的"+oid+"订单的已被变更为未接收状态!"); 
			String title="系统消息";
		 	String content="您将订单号为的"+oid+"订单变更为未接受状态成功!";
			int messageId=messageService.insertMessage("server", "system", sid, title, content); 
	        PrintWriter pw = response.getWriter();
	        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
	        pw.flush();
	        pw.close();
			
			
		}
		if(isChange==2&&!cookId.equals(null))
		{ 
			serverService.changeOrder(oid, sid, isChange, changeDetail);
			
			messageService.insertMessage("cook", "system", cookId, "系统消息", "您接收的订单号为的"+oid+"订单的变更未被通过!");
			String title="系统消息";
		 	String content="您对订单号为的"+oid+"的订单的处理成功!";
			int messageId=messageService.insertMessage("server", "system", sid, title, content); 
	        PrintWriter pw = response.getWriter();
	        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
	        pw.flush();
	        pw.close();
		}
		return "success";
		
	}
	// 退出
	@Action(value = "logoutAction", results = { @Result(name = "success", location = "/user/Login.jsp"), })
	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		session.removeAttribute("sever");

		return "success";
	}

	
	
	//发布通知消息的操作
	@Action(value = "createNotice", results = { 
			@Result(name = "success", location = "/server/CreateNotice.jsp"), 
			@Result(name = "input", location = "/server/index.jsp")})
	public String createNotice() throws EbpException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();		
        response.setContentType("text/json");  
        response.setCharacterEncoding("utf-8");
		AdminUser server=(AdminUser)session.getAttribute("server");
		Integer sid=server.getAdmin_id();
		CookDAOJDBC ids=new CookDAOJDBC();
		String reciever=request.getParameter("reciever");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String recieverKind="";
		if(reciever.equals("1"))  //用户
		{
			recieverKind="用户";
			int uid[]=ids.findUid();
			for(int i=0;i<uid.length;i++)
			{
				messageService.insertMessage("user", "server"+String.valueOf(sid), uid[i], title, content);
			}
		}
		else if(reciever.equals("2")) //厨师
		{
			recieverKind="厨师";
			int cid[]=ids.findCid2();
			for(int i=0;i<cid.length;i++)
			{
				messageService.insertMessage("cook", "server"+String.valueOf(sid), cid[i], title, content);
			}
			
		}
		else if(reciever.equals("3")) //客服
		{
			recieverKind="客服";
			int sid2[]=ids.findSid();
			for(int i=0;i<sid2.length;i++){
				messageService.insertMessage("server", "server"+String.valueOf(sid), sid2[i], title, content);
			}			
		}
		String title2="系统消息";
		String content2="您给"+recieverKind+"发布通知消息的操作成功!";
		messageService.insertMessage("server", "System", sid, "系统消息", content2);		
		PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+title2+"\",\"message_content\":\""+content2+"\"}");
        pw.flush();
        pw.close();
		return "success";
	}

	
	
	
	
	
	

}
