package ofs.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import ofs.id.PageSet;
import ofs.javabean.NotDoneOrder;
import ofs.javabean.OnlineOrder;
import ofs.javabean.Order;
import ofs.javabean.SysUserMoneyRecord;
import ofs.javabean.User;
import ofs.javabean.UserMessage;
import ofs.service.MessageService;
import ofs.service.OrderService;
import ofs.service.UserMoneyRecordService;

import com.opensymphony.xwork2.ActionSupport;

//获取当前用户的未完成的订单
public class UserDataAction extends ActionSupport {
	@Resource
	private OrderService orderService;
	@Resource
	private UserMoneyRecordService userMoneyRecordService;
	@Resource
	private MessageService messageService;
	
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
	private Map<String,Object> histroyData; 
	private Map<String,Object> scoringData; 
	private Map<String,Object> notRecievedData;
	private Map<String,Object> notServedData;
	private Map<String,Object> needPayData;
	private Map<String,Object> servingData;
	private Map<String,Object> finishedData;  //已完成的订单
	private Map<String,Object> changingData; 
	private Map<String,Object> moneyHistroyData; 
	private Map<String,Object> messageData; 
	
	
	public Map<String, Object> getMessageData() {
		return messageData;
	}

	public void setMessageData(Map<String, Object> messageData) {
		this.messageData = messageData;
	}

	public Map<String, Object> getMoneyHistroyData() {
		return moneyHistroyData;
	}

	public void setMoneyHistroyData(Map<String, Object> moneyHistroyData) {
		this.moneyHistroyData = moneyHistroyData;
	}

	public Map<String, Object> getChangingData() {
		return changingData;
	}

	public void setChangingData(Map<String, Object> changingData) {
		this.changingData = changingData;
	}

	public Map<String, Object> getFinishedData() {
		return finishedData;
	}

	public void setFinishedData(Map<String, Object> finishedData) {
		this.finishedData = finishedData;
	}

	public Map<String, Object> getServingData() {
		return servingData;
	}

	public void setServingData(Map<String, Object> servingData) {
		this.servingData = servingData;
	}

	public Map<String, Object> getScoringData() {
		return scoringData;
	}

	public void setScoringData(Map<String, Object> scoringData) {
		this.scoringData = scoringData;
	}

	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	public Map<String, Object> getHistroyData() {
		return histroyData;
	}

	public void setHistroyData(Map<String, Object> histroyData) {
		this.histroyData = histroyData;
	}
    
	
	public Map<String, Object> getNotRecievedData() {
		return notRecievedData;
	}

	public void setNotRecievedData(Map<String, Object> notRecievedData) {
		this.notRecievedData = notRecievedData;
	}
	
	public Map<String, Object> getNotServedData() {
		return notServedData;
	}

	public void setNotServedData(Map<String, Object> notServedData) {
		this.notServedData = notServedData;
	}
	

	public Map<String, Object> getNeedPayData() {
		return needPayData;
	}

	public void setNeedPayData(Map<String, Object> needPayData) {
		this.needPayData = needPayData;
	}

	@JSON(serialize=false)
	public  String MyGoingOrder() throws IOException {
		jsonData = new HashMap<String,Object>(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		//System.out.println("session里面取到的user："+user.toString());
		int uid=user.getUser_id();
		//String uid="user000001";
		//List<Order> orders=orderService.getOrderByStatus(uid,4);
		List<Order> orders=orderService.getUserOrderByUid(uid);
		//System.out.print(orders.toString()+"  "+orders.size());	
		//List<Order> orders2=orderService.getOnlineOrder();
		List<NotDoneOrder> NotDoneOrderList = new ArrayList<NotDoneOrder>();//要转换成页面显示的格式
		List<NotDoneOrder> NotDoneOrderList2 = new ArrayList<NotDoneOrder>();//要转换成页面显示的格式
		for(int i=0;i<orders.size();i++)
		{			
			NotDoneOrder notDoneOrder = new NotDoneOrder(
					orders.get(i).getOrder_id(),
			        orders.get(i).getCustom_Name(),
					orders.get(i).getCustom_Place(),
					orders.get(i).getCustom_Tel(),
					orders.get(i).getIs_booked(),
					orders.get(i).getOrder_price(),
					orders.get(i).getOrder_status(),
					orders.get(i).getOrder_kind(),
					orders.get(i).getOrder_remark(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orders.get(i).getServe_time()),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orders.get(i).getStart_time()));
			NotDoneOrderList.add(notDoneOrder);                            
		}
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		if(getOrderKind.equals("2"))
		{
			NotDoneOrderList2=this.getFirstThreeOrder2(NotDoneOrderList);
			
		}
		else
		{
			NotDoneOrderList2=this.pageSet2(NotDoneOrderList, limit, offset);		
		}
		jsonData.put("total",orders.size());
		jsonData.put("rows", NotDoneOrderList2);		
		return SUCCESS;
	}
	
	
	//获取本用户的刚发布的订单，即是还未接受的订单
	public String MyNotRecievedOrder(){
		notRecievedData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		int uid=user.getUser_id();
		List<Order> orders=orderService.getOrderByStatusUser(uid,1);
		//System.out.println("数据条数："+orders.size()+""+orders.toString());
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}
		notRecievedData.put("total", orders.size());
		notRecievedData.put("rows", orders2);
		return SUCCESS;
	}
	
	//获取本用户的未服务的订单
	public String MyNotServedOrder(){
		notServedData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		int uid=user.getUser_id();
		List<Order> orders=orderService.getOrderByStatusUser(uid,2);
		//System.out.println("数据条数："+orders.size()+""+orders.toString());
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}	
		notServedData.put("total", orders.size());
		notServedData.put("rows", orders2);
		return SUCCESS;
	}	
	
	//获取本用户的通知消息
	public String MyMessage(){
		messageData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		int uid=user.getUser_id();
		List<UserMessage> messages=messageService.getUserMessageByUid(uid);
		List<UserMessage> messages2=this.pageSet4(messages, limit, offset);
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		messageData.put("total", messages.size());
		messageData.put("rows", messages2);
		return SUCCESS;
	}
	
	
	//获取本用户的金额操作的历史记录
	public String MyHistoryMoneyRecord(){
		moneyHistroyData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		int uid=user.getUser_id();
		List<SysUserMoneyRecord> moneyRecord=new ArrayList();
		moneyRecord=userMoneyRecordService.getMyRecord(uid);
		//System.out.println("数据条数："+orders.size()+""+orders.toString());
		List<SysUserMoneyRecord> moneyRecord2=this.pageSet3(moneyRecord, limit, offset);
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		moneyHistroyData.put("total", moneyRecord.size());
		moneyHistroyData.put("rows", moneyRecord2);
		return SUCCESS;
	}
	
	//获取本用户的未支付的订单
	public String MyNeedPayOrder(){
		needPayData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		int uid=user.getUser_id();
		List<Order> orders=orderService.getOrderByStatusUser(uid,4);
		//System.out.println("数据条数："+orders.size()+""+orders.toString());
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}		
		needPayData.put("total", orders.size());
		needPayData.put("rows", orders2);
		return SUCCESS;
	}
	
	//获取本用户的厨师正进行服务的订单
	public String MyServingOrder(){
		servingData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		int uid=user.getUser_id();
		List<Order> orders=orderService.getOrderByStatusUser(uid,3);
		//System.out.println("数据条数："+orders.size()+""+orders.toString());
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}		
		servingData.put("total", orders.size());
		servingData.put("rows", orders2);
		return SUCCESS;
	}
	
	//获取我的全部未完成的订单
	public  String MyDoneOrder() throws IOException {
		histroyData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");		
		int uid=user.getUser_id();	
		List<Order> orders=orderService.getOrderByStatusUser(uid, 5);
	    List<NotDoneOrder> NotDoneOrderList = new ArrayList<NotDoneOrder>();//要转换成页面显示的格式
		for(int i=0;i<orders.size();i++)
		{			
			NotDoneOrder notDoneOrder = new NotDoneOrder(
					orders.get(i).getOrder_id(),
			        orders.get(i).getCustom_Name(),
					orders.get(i).getCustom_Place(),
					orders.get(i).getCustom_Tel(),
					orders.get(i).getIs_booked(),
					orders.get(i).getOrder_price(),
					orders.get(i).getOrder_status(),
					orders.get(i).getOrder_kind(),
					orders.get(i).getOrder_remark(),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orders.get(i).getServe_time()),
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orders.get(i).getStart_time()),
					orders.get(i).getOrder_comment(),
					orders.get(i).getOrder_score(),
					orders.get(i).getOrder_score_ToCook());
			NotDoneOrderList.add(notDoneOrder);                            
		}
		
		histroyData.put("total",orders.size());
		histroyData.put("rows", NotDoneOrderList);
		
		return SUCCESS;
	}
	
	
	//获取待评分的订单，转换给前端 对应的页面显示评分，并且结束订单
	public  String MyScoringOrder() throws IOException {
		scoringData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");		
		int uid=user.getUser_id();			
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		List<Order> orders=orderService.getOrderByStatusUser(uid, 5);
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}		
		scoringData.put("total",orders.size());
		scoringData.put("rows", orders2);
		
		return SUCCESS;
	}
	
	//获取已完成的订单，转换给前端 对应的页面显示
	public  String MyFinishedOrder() throws IOException {
		finishedData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");		
		int uid=user.getUser_id();			
		List<Order> orders=orderService.getOrderByStatusUser(uid, 6);
		List<Order> orders2=new ArrayList();
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}	
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		finishedData.put("total",orders.size());
		finishedData.put("rows", orders2);
		
		return SUCCESS;
	}
	//获取正在变更的订单，转换给前端 对应的页面显示
	public  String MyChangingOrder() throws IOException {
		changingData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");		
		int uid=user.getUser_id();			
		List<Order> orders=orderService.getOrderByIsChange(1,1,uid);
		List<Order> orders2=new ArrayList();
		
		if(getOrderKind.equals("2"))
		{
			orders2=this.getFirstThreeOrder(orders);
		}
		else
		{
			orders2=this.pageSet(orders, limit, offset);					
		}	
		//System.out.println("------------------类型:"+getOrderKind+"-------------------");
		changingData.put("total",orders.size());
		changingData.put("rows", orders2);
		
		return SUCCESS;
	}
	
	
	public List<NotDoneOrder> pageSet2(List<NotDoneOrder> list,String limit,String offset){
		List<NotDoneOrder> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((NotDoneOrder) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
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
	
	
	public List<SysUserMoneyRecord> pageSet3(List<SysUserMoneyRecord> list,String limit,String offset){
		List<SysUserMoneyRecord> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((SysUserMoneyRecord) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}
	
	public List<UserMessage> pageSet4(List<UserMessage> list,String limit,String offset){
		List<UserMessage> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((UserMessage) list.get(i));
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
