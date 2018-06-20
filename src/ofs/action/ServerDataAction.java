package ofs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import ofs.javabean.AdminUser;
import ofs.javabean.Cook;
import ofs.javabean.CookMessage;
import ofs.javabean.Order;
import ofs.javabean.ServerMessage;
import ofs.javabean.User;
import ofs.javabean.UserMessage;
import ofs.service.AdminUserService;
import ofs.service.CookService;
import ofs.service.MessageService;
import ofs.service.OrderService;
import ofs.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 客服要处理的所有的返回前端的数据
 * */
public class ServerDataAction extends ActionSupport{
	@Resource 
	private OrderService orderService;
	@Resource
	private UserService userService;
	@Resource
	private CookService cookService;
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private MessageService messageService;

	private String offset;  //第几页	
	private String search; //查询参数
	private String sort;   //排序列
	private String sortOrder;  //排序命令 asc，desc
	private String  limit;  //页面大小
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

	
	private Map<String,Object> userChangeOrder;  //用户变更订单数据
	private Map<String,Object> cookChangeOrder;  //厨师变更订单数据
	private Map<String,Object> aduitCook;        //厨师审核
	private Map<String,Object> groupUser;        //客服分组管理
	private Map<String,Object> messageData;

	
	public Map<String, Object> getMessageData() {
		return messageData;
	}
	public void setMessageData(Map<String, Object> messageData) {
		this.messageData = messageData;
	}
	public Map<String, Object> getUserChangeOrder() {
		return userChangeOrder;
	}

	public void setUserChangeOrder(Map<String, Object> userChangeOrder) {
		this.userChangeOrder = userChangeOrder;
	}

	public Map<String, Object> getCookChangeOrder() {
		return cookChangeOrder;
	}

	public void setCookChangeOrder(Map<String, Object> cookChangeOrder) {
		this.cookChangeOrder = cookChangeOrder;
	}

	public Map<String, Object> getAduitCook() {
		return aduitCook;
	}

	public void setAduitCook(Map<String, Object> aduitCook) {
		this.aduitCook = aduitCook;
	}

	public Map<String, Object> getGroupUser() {
		return groupUser;
	}

	public void setGroupUser(Map<String, Object> groupUser) {
		this.groupUser = groupUser;
	}
	
	

	//获取用户申请的变更的订单的数据
	public String getUserChangingOrder(){
		userChangeOrder=new HashMap<String,Object>();
		List<Order> orders=orderService.getChangeOrder(0,"user");
		List<Order> orders2=new ArrayList();
		orders2=this.pageSet(orders, limit, offset);
		userChangeOrder.put("total", orders.size());
		userChangeOrder.put("rows", orders2);		
		return SUCCESS;
	}
	
	//获取厨师申请的变更的订单的数据
	public String getCookChangingOrder(){
		cookChangeOrder=new HashMap<String,Object>();
		List<Order> orders=orderService.getChangeOrder(0,"cook");
		List<Order> orders2=new ArrayList();
		orders2=this.pageSet(orders, limit, offset);
		cookChangeOrder.put("total", orders.size());
		cookChangeOrder.put("rows", orders2);		
		return SUCCESS;
	}
	
	//获取为审核的厨师的数据
	public String getCookByAduit(){
		aduitCook=new HashMap<String,Object>();
		List<Cook> cooks=cookService.getCookByAduit(0);
		List<Cook> cooks2=new ArrayList();
		cooks2=this.pageSetCook(cooks, limit, offset);
		aduitCook.put("total", cooks.size());
		aduitCook.put("rows", cooks2);		
		return SUCCESS;
	}
	//获取客服的通知消息
	public String getServerMessage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		AdminUser server=(AdminUser)session.getAttribute("server");
		Integer sid=server.getAdmin_id();
		messageData=new HashMap<String,Object>();
		List<ServerMessage> serverMessage=messageService.getServerMessageBySid(sid);
		List<ServerMessage> serverMessage2=new ArrayList<ServerMessage>();
		serverMessage2=this.pageSetMessage(serverMessage, limit, offset);
		messageData.put("total", serverMessage.size());
		messageData.put("rows", serverMessage2);		
		return SUCCESS;
	}
	
	//获取各个分组的客服的数据
	public String getServerByGroup(){
		groupUser=new HashMap<String,Object>();
		List<AdminUser> servers=adminUserService.getAdminByGroup(1);
		groupUser.put("total", servers.size());
		groupUser.put("rows", servers);		
		return SUCCESS;
	}
	
	

	
	public List<Order> pageSet(List<Order> list,String limit,String offset){
		List<Order> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((Order) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}
	
	
	public List<Cook> pageSetCook(List<Cook> list,String limit,String offset){
		List<Cook> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((Cook) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}

	public List<User> pageSetUser(List<User> list,String limit,String offset){
		List<User> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((User) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}
	
	public List<AdminUser> pageSetAdminUser(List<AdminUser> list,String limit,String offset){
		List<AdminUser> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((AdminUser) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}
	
	public List<ServerMessage> pageSetMessage(List<ServerMessage> list,String limit,String offset){
		List<ServerMessage> list2=new ArrayList<ServerMessage>();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((ServerMessage) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}
	


	

}
