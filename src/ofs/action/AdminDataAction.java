package ofs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import ofs.javabean.AdminUser;
import ofs.javabean.Cook;
import ofs.javabean.Order;
import ofs.javabean.User;
import ofs.service.AdminUserService;
import ofs.service.CookService;
import ofs.service.OrderService;
import ofs.service.UserService;

/**
 * 功能说明：admin下的所有的返回json数据给前端的table请求的action
 * 
 * */
public class AdminDataAction  extends ActionSupport{
	/**
	 * 
	 */
	
	@Resource
	private UserService userService;
	@Resource
	private CookService cookService;
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private OrderService adminOrderService;
	

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

	
	
	
	
	private Map<String,Object> usersData;
	private Map<String,Object> cooksData;
	private Map<String,Object> serversData;
	private Map<String,Object> ordersData;
	public Map<String, Object> getOrdersData() {
		return ordersData;
	}
	public void setOrdersData(Map<String, Object> ordersData) {
		this.ordersData = ordersData;
	}
	public Map<String, Object> getServersData() {
		return serversData;
	}
	public void setServersData(Map<String, Object> serversData) {
		this.serversData = serversData;
	}
	public Map<String, Object> getCooksData() {
		return cooksData;
	}
	public void setCooksData(Map<String, Object> cooksData) {
		this.cooksData = cooksData;
	}
	public Map<String, Object> getUsersData() {
		return usersData;
	}
	public void setUsersData(Map<String, Object> usersData) {
		this.usersData = usersData;
	}
	
	public String allUsers(){
		usersData = new HashMap<String,Object>();  
		
		List<User> users=userService.getAllUsers();
		List<User> users2=this.pageSetUser(users, limit, offset);
		usersData.put("total", users.size());
		usersData.put("rows", users2);
		return  SUCCESS;
	}
	
	public String allCooks(){
		cooksData = new HashMap<String,Object>();  
		
		List<Cook> cooks=new ArrayList<Cook>();
		List<Cook> cooks2=new ArrayList<Cook>();
		cooks=cookService.getAllCooks();
		cooks2=this.pageSetCook(cooks, limit, offset);
		cooksData.put("total", cooks.size());
		cooksData.put("rows", cooks2);
		return  SUCCESS;
	}
	
	public String allServers(){
		serversData = new HashMap<String,Object>();  
		
		List<AdminUser> servers=new ArrayList<AdminUser>();
		List<AdminUser> servers2=new ArrayList<AdminUser>();
		servers=adminUserService.getAdminers();
		servers2=this.pageSetAdminUser(servers, limit, offset);
		serversData.put("total", servers.size());
		serversData.put("rows", servers2);
		return  SUCCESS;
	}
	
	
	public String allOrders(){
		HttpServletRequest reqeust= ServletActionContext.getRequest();  
		List<Order> orders2=new ArrayList();
		ordersData=new HashMap<String,Object>();
		List<Order> orders=adminOrderService.getAllOrders();		
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		orders2=this.pageSet(orders, limit, offset);
		ordersData.put("total", orders.size());
		ordersData.put("rows", orders2);
		return SUCCESS;
	}
	
	
	public List<Order> pageSet(List<Order> list,String limit,String offset){
		List<Order> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((Order) list.get(i));
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
			}						
		return list2;
	}
	
	
}
