package ofs.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ofs.dao.OrderDaoJDBC;
import ofs.exception.EbpException;
import ofs.javabean.Cook;
import ofs.javabean.Order;
import ofs.javabean.Path;
import ofs.javabean.User;
import ofs.md5.IpUtil;
import ofs.service.CookMoneyRecordService;
import ofs.service.CookService;
import ofs.service.MessageService;
import ofs.service.OrderService;
import ofs.service.UserService;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/cook")
@ParentPackage("struts-default")
public class CookAction extends ActionSupport {
	@Resource
	private UserService userService;
	@Resource
	private CookService cookService;
	@Resource
	private OrderService orderService;
	@Resource
	private MessageService messageService;
	@Resource
    private CookMoneyRecordService cookMoneyRecordService;
	
	private String username;
	private String password;
	private String forward;
	private String inputcheckcode;

	
	private Integer oid;
	private String data; //返回
	
	
	private File upload;
	private String uploadFileName;
	
	private File idPicture;
	private String idPictureFileName;
	private File cookPicture;
	private String cookPictureFileName;
	private File healthPicture;
	private String healthPictureFileName;
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

	public File getIdPicture() {
		return idPicture;
	}

	public void setIdPicture(File idPicture) {
		this.idPicture = idPicture;
	}

	public String getIdPictureFileName() {
		return idPictureFileName;
	}

	public void setIdPictureFileName(String idPictureFileName) {
		this.idPictureFileName = idPictureFileName;
	}

	public File getCookPicture() {
		return cookPicture;
	}

	public void setCookPicture(File cookPicture) {
		this.cookPicture = cookPicture;
	}

	public String getCookPictureFileName() {
		return cookPictureFileName;
	}

	public void setCookPictureFileName(String cookPictureFileName) {
		this.cookPictureFileName = cookPictureFileName;
	}

	public File getHealthPicture() {
		return healthPicture;
	}

	public void setHealthPicture(File healthPicture) {
		this.healthPicture = healthPicture;
	}

	public String getHealthPictureFileName() {
		return healthPictureFileName;
	}

	public void setHealthPictureFileName(String healthPictureFileName) {
		this.healthPictureFileName = healthPictureFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


	
	//注意引用的时候一定要加上关键字，否者无法调用对应方法，会一直显示空指针
	public String getInputcheckcode() {
		return inputcheckcode;
	}

	public void setInputcheckcode(String inputcheckcode) {
		this.inputcheckcode = inputcheckcode;
	}


	public CookService getCookService() {
		return cookService;
	}

	public void setCookService(CookService cookService) {
		this.cookService = cookService;
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

      //厨师注册
        @Action(value="registrationAction",results={
        		@Result(name = "success", type = "redirect", location = "/user/Login.jsp"),
				@Result(name = "input", location = "/cook/Registration.jsp")
        })
        public String regist(){
        	Cook cook = null;
    		
    		try {	
    			cook = cookService.regist(username, password);
    		} catch (Exception e) {
    			this.addActionError(e.getMessage());
    			return "input";
    		}
    		HttpServletRequest request = ServletActionContext.getRequest();
    		HttpSession session = request.getSession();
    		session.setAttribute("cook", cook);
    		return "success";
        }
	
	
	// 登陆
		@Action(value = "loginAction", results = {
				@Result(name = "success", type = "redirect", location = "/cook/index2.jsp"),
				@Result(name = "input", location = "/user/Login.jsp") })
		public String login() throws EbpException {
			Cook cook = null;
			HttpServletRequest request = ServletActionContext.getRequest();
			String checkcode=(String)request.getSession().getAttribute("checkCode");
			String ip=IpUtil.getIp(request);
			
			try {
				cook = cookService.getUser(username);
				String pwdInDb=cook.getCook_password();
				Integer status=cook.getCook_status();
				if(checkcode.equals(inputcheckcode)){
					
					//boolean md5=MyMD5Util.validPassword(password, pwdInDb);			 
					if(!pwdInDb.equals(password)){
						this.addActionError("账号或密码错误！");
						return "input";
					}
					if(status==0){
						this.addActionError("该账号被禁用，请联系管理员处理！");
						return "input";
					}
				}
				else{
					this.addActionError("验证码错误！");
					return "input";
				}
			} catch (Exception e) {
				this.addActionError(e.getMessage());
				return "input";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("cook", cook);
			userService.insertLog(ip, username, 2);  //插入厨师的ip信息
			forward = (String) session.getAttribute("loginUrl");
			if (forward == null) {
				forward = "/cook/index.jsp";
			}

			return "success";
		}

		// 退出
		@Action(value = "logoutAction", results = { @Result(name = "success", location = "/user/Login.jsp") })
		public String logout() {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			session.removeAttribute("cook");

			return "success";
		}
		
		/*获取所有的在线的订单数据
		 **/
		 @Action(value = "getOnlineOrderAction", results ={
				 @Result(name = "success",  location = "/cook/OnlineOrder.jsp"),
				 @Result(name = "input", location = "/cook/OnlineOrder.jsp") })
			public String getOnlineOrder() throws IOException {
			 	HttpServletRequest req=ServletActionContext.getRequest();
			 	HttpServletResponse resp=ServletActionContext.getResponse();
				PrintWriter out=resp.getWriter();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				HttpServletResponse response=ServletActionContext.getResponse();
				Cook cook=(Cook)request.getAttribute("cook");
				//Map<String,Object> map=new HashMap<String,Object>();
				
				List<Order> orders=orderService.getOnlineOrder();
				JsonArray onlineOrders = new JsonArray();			
				JsonObject para = new JsonObject(); //加入total和rows两个参数
				para.addProperty("total", orders.size());
				para.addProperty("rows", 5);
				onlineOrders.add(para);
				for(int i=0;i<orders.size();i++)
				{
					JsonObject json=new JsonObject();
					json.addProperty("custom_name", orders.get(i).getCustom_Name());
					json.addProperty("custom_place", orders.get(i).getCustom_Place());
					json.addProperty("custom_tel", orders.get(i).getCustom_Tel());
					json.addProperty("is_booked",orders.get(i).getIs_booked());
					json.addProperty("order_price", orders.get(i).getOrder_price());
					json.addProperty("order_status", orders.get(i).getOrder_status());
					json.addProperty("order_kind", orders.get(i).getOrder_kind());
					json.addProperty("order_remark", orders.get(i).getOrder_remark());
		             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		             String dateString = formatter.format(orders.get(i).getServe_time());
		            json.addProperty("serve_time", dateString);
		            onlineOrders.add(json);
		             
				}
				
				System.out.println("json:"+onlineOrders.toString());
				out.write(onlineOrders.toString());
				
				return "success";
				
				
						}
		 
		 //接单操作 注意接单之后要更新info订单中的状态信息
		 @Action(value = "receiveOrder", results ={@Result(name="success",location="/cook/OnlineOrder.jsp")})
			public String receiveOrder() throws IOException {
			 	HttpServletRequest req=ServletActionContext.getRequest();
			 	HttpServletResponse resp=ServletActionContext.getResponse();
				PrintWriter out=resp.getWriter();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				HttpServletResponse response=ServletActionContext.getResponse();
				Cook cook=(Cook)request.getAttribute("cook");
				//Map<String,Object> map=new HashMap<String,Object>();
				
				List<Order> orders=orderService.getOnlineOrder();
				JsonArray onlineOrders = new JsonArray();			
				JsonObject para = new JsonObject(); //加入total和rows两个参数
				para.addProperty("total", orders.size());
				para.addProperty("rows", 5);
				onlineOrders.add(para);
				for(int i=0;i<orders.size();i++)
				{
					JsonObject json=new JsonObject();
					json.addProperty("custom_name", orders.get(i).getCustom_Name());
					json.addProperty("custom_place", orders.get(i).getCustom_Place());
					json.addProperty("custom_tel", orders.get(i).getCustom_Tel());
					json.addProperty("is_booked",orders.get(i).getIs_booked());
					json.addProperty("order_price", orders.get(i).getOrder_price());
					json.addProperty("order_status", orders.get(i).getOrder_status());
					json.addProperty("order_kind", orders.get(i).getOrder_kind());
					json.addProperty("order_remark", orders.get(i).getOrder_remark());
		             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		             String dateString = formatter.format(orders.get(i).getServe_time());
		            json.addProperty("serve_time", dateString);
		            onlineOrders.add(json);
		             
				}
				
				System.out.println("json:"+onlineOrders.toString());
				out.write(onlineOrders.toString());
				
				return "success";
				
				
						}
		 
		 	@RequestMapping(value ="/onlineorder_data",method = RequestMethod.GET)
			@ResponseBody
			public JsonObject getOnlineOrders(@RequestParam(value="limit",required =false) Integer limit,
					@RequestParam(value="offset",required =false) Integer offset,
					@RequestParam(value="search",required =false) Integer search
					//@RequestParam(value="__proto__",required =false) Object  __proto__
				   ) {
				
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				HttpServletResponse response=ServletActionContext.getResponse();
				Cook cook=(Cook)request.getAttribute("cook");
				//Map<String,Object> map=new HashMap<String,Object>();
				
				List<Order> orders=orderService.getOnlineOrder();
				JsonArray onlineOrders = new JsonArray();			
				JsonObject para = new JsonObject(); //加入total和rows两个参数
				para.addProperty("total", orders.size());
				para.addProperty("rows", 5);
				onlineOrders.add(para);
				for(int i=0;i<orders.size();i++)
				{
					JsonObject json=new JsonObject();
					json.addProperty("custom_name", orders.get(i).getCustom_Name());
					json.addProperty("custom_place", orders.get(i).getCustom_Place());
					json.addProperty("custom_tel", orders.get(i).getCustom_Tel());
					json.addProperty("is_booked",orders.get(i).getIs_booked());
					json.addProperty("order_price", orders.get(i).getOrder_price());
					json.addProperty("order_status", orders.get(i).getOrder_status());
					json.addProperty("order_kind", orders.get(i).getOrder_kind());
					json.addProperty("order_remark", orders.get(i).getOrder_remark());
		             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		             String dateString = formatter.format(orders.get(i).getServe_time());
		            json.addProperty("serve_time", dateString);
		            onlineOrders.add(json);
		             
				}
				
				return onlineOrders.getAsJsonObject();
				
		 }
		 	
		 	
		    //厨师开始服务的操作
		 	 @Action(value = "StartServeAction", results ={@Result(name="success",location="/cook/MyOrders.jsp")})
				public String StartServe() throws IOException, EbpException {
				 	HttpServletRequest req=ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
			        response.setContentType("text/json");  
			        response.setCharacterEncoding("utf-8");						
					HttpSession session = req.getSession();					
					Cook cook=(Cook)session.getAttribute("cook");
					
					Integer cid=cook.getCook_id();
					int i=cookService.startServe(oid, cid);
					
					String title="系统消息";
					String content="订单号为"+oid+"的订单开始服务成功！";			
					int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
			       
					return "success";
					
					
							}
			    //厨师结束服务的操作
		 	 @Action(value = "StopServeAction", results ={@Result(name="success",location="/cook/MyOrders.jsp")})
				public String StopServe() throws IOException, EbpException {
				 	HttpServletRequest req=ServletActionContext.getRequest();
				 	HttpServletResponse resp=ServletActionContext.getResponse();					
					HttpSession session = req.getSession();					
					Cook cook=(Cook)session.getAttribute("cook");					
					Integer cid=cook.getCook_id();
					int i=cookService.stopServe(oid, cid);
					String title="系统消息";
					String content="订单号为"+oid+"的订单结束服务成功！";			
					int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
					return "success";
					
					
							}
		 	 
		 	 //厨师申请变更订单的操作
		 	 @Action(value = "changeOrderAction", results ={
		 			 @Result(name="success",location="/cook/MyOrders.jsp"),
		 			 @Result(name = "input", location = "/cook/index.jsp")}
		 	 
		 	 )
				public String changeOrder() throws IOException, EbpException {
				 	HttpServletRequest req=ServletActionContext.getRequest();
				 	HttpServletResponse resp=ServletActionContext.getResponse();					
					HttpSession session = req.getSession();
					String changeDetail=req.getParameter("changedetail");  //获取用户填写的变更申请
					Integer oid=Integer.parseInt(req.getParameter("oid"));
				    
					Cook cook=(Cook)session.getAttribute("cook");
					
					Integer cid=cook.getCook_id();
					String changeApplicant="cook"+String.valueOf(cid); //申请人
					
				    int i=cookService.changeOrder(oid, cid, changeDetail,changeApplicant);
				    String title="系统消息";
					String content="订单号为"+oid+"的订单变更申请成功发送！";			
					int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
					return "success";
					
					
							}
		 	 //厨师余额提现的操作 包括厨师信息更新 厨师的金额记录更新 厨师的消息部分更新
		 	 @Action(value = "cashMoneyAction", results ={
		 			 @Result(name="success",location="/cook/MyPurse.jsp"),
		 			 @Result(name = "input", location = "/cook/MyPurse.jsp")}
		 	 
		 	 )
				public String cashMoney() throws IOException, EbpException {
			 		HttpServletRequest req=ServletActionContext.getRequest(); 
					HttpServletResponse response = ServletActionContext.getResponse();
			        response.setContentType("text/json");  
			        response.setCharacterEncoding("utf-8");
					MessageAction message=new MessageAction();
					HttpSession session = req.getSession();
				    
					Cook cook=(Cook)session.getAttribute("cook");
					
					Integer cid=cook.getCook_id();
					Double cashmoney=Double.parseDouble(req.getParameter("cashmoney"));
					String bankKind=req.getParameter("bankKind");
					String cardId=req.getParameter("cardId");
					Double money=cook.getCook_money();
					if(cashmoney>money)
					{


						String content="提现金额过多，超过余额！";
				        String title="系统消息";
				        int messageId=messageService.insertMessage("cook", "system", cid, title, content);            
				        PrintWriter pw = response.getWriter();
				        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
				        pw.flush();
				        pw.close();
						return "input";
								
					}
					Double finalMoney=money-cashmoney;
					cook.setCook_money(finalMoney);
					cookService.updateCook2(cook);
					cookMoneyRecordService.insertRecord(cid, 2, money, cashmoney, bankKind, cardId);
				    Cook cook2=cookService.getCookById(cid);
				    session.setAttribute("cook", cook2);
				    String title="系统消息";
					String content="您提现"+cashmoney+"元到账户"+cardId+"成功,余额"+finalMoney+"元";			
					int messageId=messageService.insertMessage("cook", "system", cid, title, content);  
			        PrintWriter pw = response.getWriter();
			        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
			        pw.flush();
			        pw.close();
					return "success";
					
					
							}
		 	 //厨师申请对订单进行评分的操作
		 	 @Action(value = "scoreOrderAction", results ={
		 			 @Result(name="success",location="/cook/MyOrders.jsp"),
		 			 @Result(name="input",location="/cook/MyOrders.jsp")})
				public String scoreOrder() throws IOException, EbpException {
				 	HttpServletRequest req=ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
			        response.setContentType("text/json");  
			        response.setCharacterEncoding("utf-8");	
				 	Integer oid=Integer.parseInt(req.getParameter("scoreOrderId"));
				 	
				 	HttpSession session = req.getSession();
				 	Double score2=Double.parseDouble(req.getParameter("score"));														
				    int i=cookService.scoreOrderCook(oid, score2);
					Cook cook=(Cook)session.getAttribute("cook");
					
					Integer cid=cook.getCook_id();
				    String title="系统消息";
					String content="订单号为"+oid+"的订单评分成功！";			
					int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
					PrintWriter pw = response.getWriter();
					pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
				        pw.flush();
				        pw.close();
				    return "success";
					
							}
		 	
		 	  
		/*
		 * @RequestMapping(value="/testRequestMapping" )
		 	    public String testRequestMapping() {
		 	        System.out.println("testRequestMapping");
		 	        return SUCCESS;
		 	    }
			
			
				 @ResponseBody
		 @RequestMapping(value ="/onlineorder_data")
			
			public void getOnlineOrders() throws IOException {
			 HttpServletRequest req=ServletActionContext.getRequest();
			 HttpServletResponse resp=ServletActionContext.getResponse();
				PrintWriter out=resp.getWriter();
				
				HttpSession session = req.getSession();
				
				//Cook cook=(Cook)req.getAttribute("cook");
				//Map<String,Object> map=new HashMap<String,Object>();
				
				List<Order> orders=orderService.getOnlineOrder();
				JsonArray onlineOrders = new JsonArray();			
				JsonObject para = new JsonObject(); //加入total和rows两个参数
				para.addProperty("total", orders.size());
				para.addProperty("rows", 5);
				onlineOrders.add(para);
				for(int i=0;i<orders.size();i++)
				{
					JsonObject json=new JsonObject();
					json.addProperty("custom_name", orders.get(i).getCustom_Name());
					json.addProperty("custom_place", orders.get(i).getCustom_Place());
					json.addProperty("custom_tel", orders.get(i).getCustom_Tel());
					json.addProperty("is_booked",orders.get(i).getIs_booked());
					json.addProperty("order_price", orders.get(i).getOrder_price());
					json.addProperty("order_status", orders.get(i).getOrder_status());
					json.addProperty("order_kind", orders.get(i).getOrder_kind());
					json.addProperty("order_remark", orders.get(i).getOrder_remark());
		             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		             String dateString = formatter.format(orders.get(i).getServe_time());
		            json.addProperty("serve_time", dateString);
		            onlineOrders.add(json);
		             
				}
				System.out.println("json:"+onlineOrders.toString());
				out.write(onlineOrders.toString());
				
				
				
				
						}	
*/
		 	 //厨师申请审核 其实也就是把厨师的信息和照片信息存到数据库里面
		 	 @Action(value = "applyAduit", results ={
					 @Result(name = "success",  location = "/cook/ApplyInfoAduit.jsp"),
					 @Result(name = "input", location = "/cook/ApplyInfoAduit.jsp") })
				public String applyAduit() throws IOException, EbpException {
				 	HttpServletRequest req=ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
			        response.setContentType("text/json");  
			        response.setCharacterEncoding("utf-8");
				 	HttpSession session=req.getSession();
				 	Cook cook =(Cook)session.getAttribute("cook");
				 	Integer cid=cook.getCook_id();
				 	String idNumber=req.getParameter("idnumber");
				 	String license=req.getParameter("license");
				 	String healthid=req.getParameter("healthid");
				 	/*---------------------------------------------------------------------------------------------------------*/
				 	
				 	
				 	//String path =  "E:\\test\\backup\\2018-5-9\\OFS\\WebRoot\\images\\CookImages\\"+String.valueOf(cid);   //照片要存的路径
				 	String path =  Path.getCookPath()+String.valueOf(cid);   //照片要存的路径
				 	//System.out.println("----------------------路径是：  "+path);
				 	String pathInDb="images/CookImages/"+String.valueOf(cid)+"/";
				 	/*---------------------------------------------------------------------------------------------------------*/
				 	//System.out.println("-----------------"+idPicture.getName());
				 	int i=cookService.applyAduit(cid, 0, idNumber, license, healthid,pathInDb+"id.jpg",pathInDb+"license.jpg",pathInDb+"health.jpg");
				 	upfile(idPicture,"id.jpg",path,cid);
				 	upfile(cookPicture,"license.jpg",path,cid);
				 	upfile(healthPicture,"health.jpg",path,cid);
				    /*String title="系统消息";
					String content="您的厨师信息提交审核成功！";			
					int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
					PrintWriter pw = response.getWriter();
					pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
			        pw.flush();
			        pw.close();*/
					return "success";
					}
		
		 	 
		 	 
		 	 
		 	 
		 	 
		 	 
		 	//更新厨师信息
		 	@Action(value="updateCookAction",results = {
					@Result(name = "success", location = "/cook/UpdateCook.jsp"),
					@Result(name = "input", location = "/cook/UpdateCook.jsp") })
		 	public String updateCook() throws EbpException, IOException
		 	{
		 		HttpServletRequest req=ServletActionContext.getRequest();
				
				HttpServletResponse response = ServletActionContext.getResponse();
			    response.setContentType("text/json");  
			    response.setCharacterEncoding("utf-8");	
			 	HttpSession session=req.getSession();
			 	Cook cook=(Cook)session.getAttribute("cook");
			 	int cid=cook.getCook_id();
			 	String rName=req.getParameter("rName");
			 	String password=cook.getCook_password();
			 	String location=req.getParameter("location");
			 	Integer gender=Integer.parseInt(req.getParameter("gender"));
			 	Integer age=Integer.parseInt(req.getParameter("age"));
			 	String tel=req.getParameter("tel");
			 	Integer workedTime=Integer.parseInt(req.getParameter("workedTime"));
			 	String desc=req.getParameter("desc");
			 	String area=req.getParameter("area");
			 	try{
			 		Cook cook2=cookService.updateCook(cid, rName, password, location, gender, age, tel, workedTime, desc,area);
			 		System.out.print(gender);
			 		session.setAttribute("cook", cook2);
			 	}
			 	catch(Exception e){
			 		this.addActionError(e.getMessage());
			 		return "input";
			 	}
				String title="系统消息";
				String content="您的厨师信息更新成功！";			
				int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
		        PrintWriter pw = response.getWriter();
		        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
		        pw.flush();
		        pw.close();
		 		return "success";
		 	}
		 	
		 	
		 	//厨师上传头像的操作
		 	@Action(value="updateCookHeaderPictureAction",results = {
					@Result(name = "success", location = "/cook/UpdateCook.jsp"),
					@Result(name = "input", location = "/cook/UpdateCook.jsp") })
		 	public String updateCookHeaderPicture() throws EbpException, IOException
		 	{
		 		HttpServletRequest req=ServletActionContext.getRequest();				
			 	HttpSession session=req.getSession();
			 	System.out.println("----------------上传头像操作");
			 	Cook cook=(Cook)session.getAttribute("cook");
			 	int cid=cook.getCook_id();
			 	Cook cook2=cookService.getCookById(cid);
			 	String basicPath=Path.getCookPath();
			 	String path=basicPath+String.valueOf(cid);
			 	String pathInDb="images/CookImages/"+String.valueOf(cid)+"/head-icon.jpg";
			 	cook2.setCook_picture(pathInDb);
			 	cookService.updateCook2(cook2);
			 	session.setAttribute("cook", cook2);
			 	upfile(headerPicture,"head-icon.jpg",path,cid);			 	
			 	return "success";
		 	}
		 	
			//获取厨师的各个类型的订单的数量
			@Action(value="getOrderCountAction" ,results={
					@Result(name="success" ,location="/user/MyOrders2.jsp"),
			})
			public String getOrderCount() throws IOException {
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setContentType("charset=utf-8");
				HttpSession session = request.getSession();
				OrderDaoJDBC orderCount=new OrderDaoJDBC();
				Cook cook = (Cook)session.getAttribute("cook");
				Integer cid=cook.getCook_id();
				int[] count =new int[7];
				JSONObject orderCount2=new JSONObject();
				for(int i=0;i<7;i++)
				{				
					count[i]=orderCount.getOrderCountByStatusCook(cid, i);	
					orderCount2.put(String.valueOf(i), count[i]);
				}
				PrintWriter pw = response.getWriter();
				pw.write(orderCount2.toString());
		        pw.flush();
		        pw.close();			
				return "success";
			}
		 	
		 	
		 	
			@Action(value="changePassword",results = {
					@Result(name = "success", location = "/cook/UpdateCookPassword.jsp"),
					@Result(name = "input", location = "/cook/UpdateCookPassword.jsp" ) })
			public String changePassword() throws EbpException, IOException{
				HttpServletRequest req=ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/json");  
			    response.setCharacterEncoding("utf-8");
				HttpSession session=req.getSession();
				Cook cook = (Cook)session.getAttribute("cook");
				
				String passwordInDb=cook.getCook_password();
				Integer cid=cook.getCook_id();
				String password=req.getParameter("password1");
				if(passwordInDb.equals(password)){
					this.addActionError("修改后密码与原密码一样，请重新输入！");
					return "input";
				}		
				Cook cook2=cookService.getCookById(cid);
				cook2.setCook_password(password);
				cookService.updateCook2(cook2);
				session.setAttribute("cook", cook2);
				String title="系统消息";
				String content="密码修改成功！";			
				int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
		        PrintWriter pw = response.getWriter();
		        pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
		        pw.flush();
		        pw.close();	
				return "success";
			}
			
		 	 //厨师上传订单的菜单
		 	 @Action(value = "submitMenuAction", results ={
		 			 @Result(name="success",location="/cook/MyOrders.jsp"),
		 			 @Result(name="input",location="/cook/MyOrders.jsp")})
				public String submitMenu() throws IOException, EbpException {
				 	HttpServletRequest req=ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
					HttpSession session = req.getSession();
			        response.setContentType("text/json");  
			        response.setCharacterEncoding("utf-8");	
				 	Integer oid=Integer.parseInt(req.getParameter("oid"));
				 	String orderMenu=req.getParameter("orderMenu");					 	
				 	Order order=orderService.gerOrderById(oid);	
				 	Integer uid=order.getUser_id();
				 	order.setOrder_Menu(orderMenu);
					orderService.updateOrder(order);
				 	Cook cook=(Cook)session.getAttribute("cook");					
					Integer cid=cook.getCook_id();
				    String title="系统消息";
					String content="订单号为"+oid+"的订单上传订单菜单成功！";			
					int messageId=messageService.insertMessage("cook", "system", cid, title, content); 
					messageService.insertMessage("user", "system", uid, "订单信息更新提醒", "订单"+oid+"的菜单厨师已上传，请到待服务订单界面进行查看"); 
					PrintWriter pw = response.getWriter();
					pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
				        pw.flush();
				        pw.close();
				    return "success";					
					}
			
			
			
			
			
		 	
		 	//上传文件方法
		 	public boolean upfile(File file,String newFileName,String basicPath,Integer cid){
		 		 //System.out.println("upload----------------------------newFilename:"+newFileName);
		 		 //System.out.println("basicPath:"+basicPath+"");
		 		 //System.out.println("file:"+file.getName());
				 if(file != null){  
			            // 将商品图片上传到服务器上.  
			            //String path = ServletActionContext.getServletContext().getRealPath("/images");  
					    //String path =  "E:\\program\\eclipse project\\Server\\WebContent\\img";
			            // 创建文件类型对象:
					 	String pathOnServer = ServletActionContext.getServletContext().getRealPath("/images")+"/CookImages/"+String.valueOf(cid)+"/"+newFileName;  
					 	
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
				            //System.exit(1);
				          }
				        }
			            File diskFile = new File(basicPath + "//" + newFileName); 
			            File serverFile=new File(pathOnServer);
			            //File newFile =new File(basicPath+"//"+)
			            // 文件上传:  
			            try {
			            	FileUtils.copyFile(file, serverFile);
							FileUtils.copyFile(file, diskFile);
							
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
		 	
		 	/**upfile(idPicture,"id.jpg",path);
		    * @param path 文件目录 
		    * @param oldname  原来的文件名 
		    * @param newname 新文件名 
		    *
		    */
		 	 
		 	/*
		 	 *  public void renameFile(String path,String oldname,String newname){ 
		         if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
		             File oldfile=new File(path+"/"+oldname); 
		             File newfile=new File(path+"/"+newname); 
		             if(!oldfile.exists()){
		                 return;//重命名文件不存在
		             }
		             if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
		                 System.out.println(newname+"已经存在！"); 
		             else{ 
		                 oldfile.renameTo(newfile); 
		             } 
		         }else{
		             System.out.println("新文件名和旧文件名相同...");
		         }
		     }*/
	

}
