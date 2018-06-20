package ofs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.util.JSONUtils;
import ofs.dao.CookDAOJDBC;
import ofs.dao.OrderDaoJDBC;
import ofs.id.PageSet;
import ofs.javabean.Cook;
import ofs.javabean.CookMessage;
import ofs.javabean.OnlineOrder;
import ofs.javabean.Order;
import ofs.javabean.SysCookMoneyRecord;
import ofs.javabean.SysUserMoneyRecord;
import ofs.javabean.User;
import ofs.service.CookMoneyRecordService;
import ofs.service.MessageService;
import ofs.service.OrderService;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 厨师获取信息的类
 * */
public class CookDataAction extends ActionSupport  {
	@Resource
	private OrderService orderService;
	@Resource
	private MessageService messageService;
	@Resource
	private CookMoneyRecordService cookMoneyRecordService;
	
	
	private PageSet pageSet;
	private String offset;  //第几页	
	private String search; //查询参数
	private String sort;   //排序列
	private String sortOrder;  //排序命令 asc，desc
	private String  limit;  //页面大小	
	private String getOrderKind; 

	
	public String getGetOrderKind() {
		return getOrderKind;
	}

	public void setGetOrderKind(String getOrderKind) {
		this.getOrderKind = getOrderKind;
	}

	
	public PageSet getPageSet() {
		return pageSet;
	}

	public void setPageSet(PageSet pageSet) {
		this.pageSet = pageSet;
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

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	
	
	
	private Map<String,Object> jsonData;  	
	private Map<String,Object> recievedOrder;
	private Map<String,Object> servingOrder;
	private Map<String,Object> histroyOrder;
	private Map<String,Object> onlineOrder;
	private Map<String,Object> allOrders;
	private Map<String,Object> scoringOrders;
	private Map<String,Object> messageData;
	private Map<String,Object> moneyHistroyData;
	private Map<String,Object> changingData; 
	
	
	public Map<String, Object> getChangingData() {
		return changingData;
	}

	public void setChangingData(Map<String, Object> changingData) {
		this.changingData = changingData;
	}

	public Map<String, Object> getMoneyHistroyData() {
		return moneyHistroyData;
	}

	public void setMoneyHistroyData(Map<String, Object> moneyHistroyData) {
		this.moneyHistroyData = moneyHistroyData;
	}

	public Map<String, Object> getMessageData() {
		return messageData;
	}

	public void setMessageData(Map<String, Object> messageData) {
		this.messageData = messageData;
	}

	public Map<String, Object> getScoringOrders() {
		return scoringOrders;
	}

	public void setScoringOrders(Map<String, Object> scoringOrders) {
		this.scoringOrders = scoringOrders;
	}

	public Map<String, Object> getAllOrders() {
		return allOrders;
	}

	public void setAllOrders(Map<String, Object> allOrders) {
		this.allOrders = allOrders;
	}

	public Map<String, Object> getOnlineOrder() {
		return onlineOrder;
	}

	public void setOnlineOrder(Map<String, Object> onlineOrder) {
		this.onlineOrder = onlineOrder;
	}

	public Map<String, Object> getServingOrder() {
		return servingOrder;
	}

	public void setServingOrder(Map<String, Object> servingOrder) {
		this.servingOrder = servingOrder;
	}
	public Map<String,Object> getJsonData() {  
	        return jsonData;  
	    }  
	  
	public void setJsonData(Map<String,Object> jsonData) {  
	        this.jsonData = jsonData;  
	    }  
	
	public Map<String, Object> getRecievedOrder() {
		return recievedOrder;
	}

	public void setRecievedOrder(Map<String, Object> recievedOrder) {
		this.recievedOrder = recievedOrder;
	}

	public Map<String, Object> getHistroyOrder() {
		return histroyOrder;
	}

	public void setHistroyOrder(Map<String, Object> histroyOrder) {
		this.histroyOrder = histroyOrder;
	}
	//获取本用户的通知消息
	public String MyMessage(){
		messageData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		int cid = cook.getCook_id();
		List<CookMessage> messages=messageService.getCookMessageByCid(cid);
		List<CookMessage> messages2=this.pageSet4(messages, limit, offset);
		messageData.put("total", messages.size());
		messageData.put("rows", messages2);
		return SUCCESS;
	}
	public  String getOnlineOrders() throws IOException, SQLException {
		jsonData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		OrderDaoJDBC orderDAOJDBC=new OrderDaoJDBC();
		//Map<String,Object> map=new HashMap<String,Object>();	
		//List<Order> orders=orderService.getOnlineOrder();
		List<Order> orders=orderDAOJDBC.getOnlineOrder(cook.getCook_id());
		List<OnlineOrder> onlineOrderList = new ArrayList<OnlineOrder>();//要转换成页面显示的格式		
		for(int i=0;i<orders.size();i++)
		{
			int j=i+1;
			OnlineOrder onlineOrder = new OnlineOrder(j,
					orders.get(i).getOrder_id(),
			        orders.get(i).getCustom_Name(),
					orders.get(i).getCustom_Place(),
					orders.get(i).getCustom_Tel(),
					orders.get(i).getIs_booked(),
					orders.get(i).getOrder_price(),
					orders.get(i).getOrder_status(),
					orders.get(i).getOrder_kind(),
					orders.get(i).getOrder_remark(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orders.get(i).getServe_time()));
					onlineOrderList.add(onlineOrder);                            
		}
		jsonData.put("total", orders.size());
		jsonData.put("rows", onlineOrderList);
		System.out.println();
		/*
		 * JsonArray onlineOrders = new JsonArray();			
		JsonObject para = new JsonObject(); //加入total和rows两个参数
		para.addProperty("total", orders.size());
		para.addProperty("rows", 0);
		onlineOrders.add(para);*/
		
		//Map<String,Object> para2=new HashMap<String,Object>();
		
		return SUCCESS;
		
	}
	
	//获取厨师的在线可接的订单
	public String getOnlineOrders2() throws SQLException{
		onlineOrder=new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		OrderDaoJDBC orderDAOJDBC=new OrderDaoJDBC();
		List<Order> orders=orderDAOJDBC.getOnlineOrder(cook.getCook_id());
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}
		onlineOrder.put("total", orders.size());
		onlineOrder.put("rows", orders2);		
		return SUCCESS;
		
	}
	
	//获取厨师的所有订单数据
	public String getMyAllOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		Integer cid=cook.getCook_id();
		allOrders = new HashMap<String,Object>();  
		List<Order> orders=orderService.getOrderByCid(cid);
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}
		allOrders.put("total", orders.size());
		allOrders.put("rows", orders2);
		return SUCCESS;
	}
	public String getMyRecievedOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		int cid=cook.getCook_id();
		recievedOrder = new HashMap<String,Object>();  
		List<Order> orders=orderService.getOrderByStatusCook(cid, 2);
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}
		recievedOrder.put("total", orders.size());
		recievedOrder.put("rows", orders2);
		return SUCCESS;
	}
	public String getMyServingOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		int cid=cook.getCook_id();
		servingOrder = new HashMap<String,Object>();  
		List<Order> orders=orderService.getOrderByStatusCook(cid, 3);
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}
		servingOrder.put("total", orders.size());
		servingOrder.put("rows", orders2);
		return SUCCESS;
	}
	public String getMyScoringOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		int cid=cook.getCook_id();
		scoringOrders = new HashMap<String,Object>();  
		List<Order> orders=orderService.getCookScoringOrder(cid);
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}
		scoringOrders.put("total", orders.size());
		scoringOrders.put("rows", orders2);
		return SUCCESS;
	}
	
	//获取本厨师的历史订单，包括状态为 4、5、6的订单
	public String getMyHistroyOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		int cid=cook.getCook_id();
		histroyOrder = new HashMap<String,Object>();
		List<Order> allOrders=new ArrayList();
		List<Order> orders=orderService.getOrderByStatusCook(cid, 4);
		List<Order> orders2=orderService.getOrderByStatusCook(cid, 5);
		List<Order> orders3=orderService.getOrderByStatusCook(cid, 6);
		allOrders.addAll(orders);
		allOrders.addAll(orders2);
		allOrders.addAll(orders3);
		List<Order> orders4=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders4=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders4=this.pageSet(orders, limit, offset);					
		}
		histroyOrder.put("total", allOrders.size());
		histroyOrder.put("rows", orders4);
		return SUCCESS;
	}
	
	//获取本用户的金额操作的历史记录
	public String MyHistoryMoneyRecord(){
		moneyHistroyData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Cook cook=(Cook)session.getAttribute("cook");
		Integer cid=cook.getCook_id();
		List<SysCookMoneyRecord> moneyRecord=new ArrayList();
		moneyRecord=cookMoneyRecordService.getCookMoneyRecord(cid);
		//System.out.println("数据条数："+orders.size()+""+orders.toString());
		List<SysCookMoneyRecord> moneyRecord2=this.pageSet3(moneyRecord, limit, offset);
		moneyHistroyData.put("total", moneyRecord.size());
		moneyHistroyData.put("rows", moneyRecord2);
		return SUCCESS;
	}
	//获取正在变更的订单，转换给前端 对应的页面显示
		public  String MyChangingOrder() throws IOException {
			changingData = new HashMap<String,Object>();  
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Cook cook=(Cook)session.getAttribute("cook");	
			int cid=cook.getCook_id();			
			List<Order> orders=orderService.getCookChangingOrder(cid);
			List<Order> orders2=new ArrayList();
			if(getOrderKind.equals("2"))
			{
				orders2=this.getFirstThreeOrder(orders);
			}
			else
			{
				orders2=this.pageSet(orders, limit, offset);					
			}
			changingData.put("total",orders.size());
			changingData.put("rows", orders2);			
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
	public List<CookMessage> pageSet4(List<CookMessage> list,String limit,String offset){
		List<CookMessage> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((CookMessage) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}
	  
	public List<SysCookMoneyRecord> pageSet3(List<SysCookMoneyRecord> list,String limit,String offset){
		List<SysCookMoneyRecord> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((SysCookMoneyRecord) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}
	//获取订单的列表的前三条数据
		public List<Order> getFirstThreeOrder(List<Order> orders1)
		{
			List<Order> orders2=new ArrayList();
			for(int i=0;i<3&&i<orders1.size();i++)
			{
				orders2.add(orders1.get(i));
			}
			return orders2;
		}
}
