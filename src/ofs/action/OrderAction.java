package ofs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ofs.dao.CookDAOJDBC;
import ofs.dao.OrderDaoJDBC;
import ofs.javabean.Order;
import ofs.javabean.User;
import ofs.service.MessageService;
import ofs.service.OrderRecieveInfoService;
import ofs.service.OrderService;
import ofs.service.UserMoneyRecordService;
import ofs.service.UserService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


@Controller
@Namespace("/user")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport {
	@Resource
	private OrderService orderService;

	@Resource
	private UserService userService;
	@Resource
	private OrderRecieveInfoService orderRecieveInfoService;
	@Resource
    private UserMoneyRecordService userMoneyRecordService;
	@Resource
	private MessageService messsageService;
	
	private int orderkind;
	private String username;  //用于接受前端的数据，与name属性相对应，是订单使用者的姓名
	private String tel;
	private String dtp_input1;  //可能会因为格式原因接收参数有问题  接收订单的服务时间
	private String place;
	private String message;
	
	

	
	
	//返回金额
	/*
	 * @Action(value="billAction" ,results={
			@Result(name="success" ,location="/user/BookingSucc.jsp"),
			@Result(name="input" ,location="/user/ShowShoppingCart.jsp")
	})
	public String bill() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//��session�л�ȡ���ﳵ
		List<Cart> carts = (List<Cart>)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		int sumAmount = (Integer)session.getAttribute("sumAmount");
		
		Order order = null;
		try {
			if(user.getUser_money()>sumAmount){
				ticketService.updateTicketBalance(carts);
				//user=userService.updateBlance("1212",user.getUser_money()-sumAmount);
				session.setAttribute("user",user);
			}
			else{
				this.addFieldError("NoMoney","余额不足，请充值!");
				return "input";
			}
		} catch (EbpException e) {
			return "input";
		}
		
		
		
		try {
			//order = orderService.saveOrder(carts, user, sumAmount);
		} catch (EbpException e) {
			this.addActionError(e.getMessage());
			return "input";
		}
		
		session.setAttribute("order", order);
		
		//���session�еĹ��ﳵ
		session.removeAttribute("cart");
		request.setAttribute("cart", carts);
		return "success";
	}*/
	
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}





	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getOrderKind() {
		return orderkind;
	}

	public void setOrderKind(int orderKind) {
		this.orderkind = orderKind;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDtp_input1() {
		return dtp_input1;
	}

	public void setDtp_input1(String dtp_input1) {
		this.dtp_input1 = dtp_input1;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

		//产生订单要做的操作包括插入订单，插入接单信息表的记录，根据支付方式更新用户的金额表和金额信息
		@Action(value="createOrderAction" ,results={
				@Result(name="success" ,location="/user/CreateOrder2.jsp"),
				@Result(name="input" ,location="/user/CreateOrder2.jsp")
		})
		public String createOrder() throws Exception {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession();
			Double price=Double.parseDouble(request.getParameter("price"));	
			Integer paykind=Integer.parseInt(request.getParameter("paykind"));
			String cuisineLevel=request.getParameter("cuisinelevel");
			String cuisineSystem=request.getParameter("cuisinesystem");
			String cuisineKind=request.getParameter("cuisinekind");
			String foodList=cuisineLevel+";"+cuisineSystem+";"+cuisineKind;
			String locationCode=request.getParameter("area");
			CookDAOJDBC cookDAO=new CookDAOJDBC();
			int cid[]=cookDAO.findCid(locationCode);
			User user = (User)session.getAttribute("user");
		    int uid=user.getUser_id();		   		   
			User userInDB=userService.getUserById(uid);           
            if(paykind==1)
            {
            	if(userInDB.getUser_money()<price/2)
                {
            		
                	System.out.println("余额不足以支付本订单的订金，请充值或者选择其他支付方式！");
                	throw new Exception("余额不足以支付本订单的订金，请充值或者选择其他支付方式！");
                }
            	Double originalMoney=userInDB.getUser_money();
            	Double operateMoney=price/2;
              	userInDB.setUser_money(originalMoney-price/2);
              	userService.updateUser(userInDB);
            	userMoneyRecordService.insertRecord(uid, 3, originalMoney, operateMoney, null, null, paykind);
            }
            else if(paykind==2||paykind==3)
            {
            	Double originalMoney=userInDB.getUser_money();
            	Double operateMoney=price/2;
            	userMoneyRecordService.insertRecord(uid, 3, originalMoney, operateMoney, null, null, paykind);
            }
            int oid2=orderService.saveOrder(uid,orderkind, username, tel, dtp_input1, place, message,price,foodList,locationCode);
			//发布订单的时候把符合要求的信息插入进数据库当中
            for(int i=0;i<cid.length;i++){
            	 int result=orderRecieveInfoService.insertInfo(uid, oid2, cid[i]);
            	 if(result==0)
            		 System.out.print("插入记录失败");           	 
            }
            session.setAttribute("user", userInDB);            
            String content="菜式包含："+foodList+"的订单创建成功！";
            String title="订单创建成功提醒";
            int messageId=messsageService.insertMessage("user", "system", uid, title, content);           
            PrintWriter pw = response.getWriter();
            pw.write("{\"message_title\":\""+title+"\",\"message_content\":\""+content+"\"}");
            pw.flush();
            pw.close();
			return "success";
		}
		
	//获取用户所有的订单
	@Action(value="userOrdersAction" ,results={
			@Result(name="success" ,location="/user/ShowOrders.jsp"),
	})
	public String userOrders() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Order> orders = orderService.getUserOrders(user.getUser_name());
		
		
		
		request.setAttribute("maps", null);
		return "success";
	}
	
	//获取用户所有的订单
		@Action(value="getOrderCountAction" ,results={
				@Result(name="success" ,location="/user/MyOrders2.jsp"),
		})
		public String getOrderCount() throws IOException {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("charset=utf-8");
			HttpSession session = request.getSession();
			OrderDaoJDBC orderCount=new OrderDaoJDBC();
			User user = (User)session.getAttribute("user");
			Integer uid=user.getUser_id();
			int[] count =new int[7];
			JSONObject orderCount2=new JSONObject();
			for(int i=0;i<7;i++)
			{				
				count[i]=orderCount.getOrderCountByStatusUser(uid, i);	
				orderCount2.put(String.valueOf(i), count[i]);
			}
			PrintWriter pw = response.getWriter();
			pw.write(orderCount2.toString());
	        pw.flush();
	        pw.close();			
			return "success";
		}
	
}

