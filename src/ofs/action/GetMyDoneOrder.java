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

import ofs.javabean.NotDoneOrder;
import ofs.javabean.Order;
import ofs.javabean.User;
import ofs.service.OrderService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class GetMyDoneOrder  extends ActionSupport{
	@Resource
	private OrderService orderService;
	
	private Map<String,Object> jsonData; 
	
	@JSON(serialize=false)
	public  String MyDoneOrder() throws IOException {
		jsonData = new HashMap<String,Object>();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");		
		int uid=user.getUser_id();	
		List<Order> orders=orderService.getOrderByStatus(uid,4);
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
		
		jsonData.put("total",orders.size());
		jsonData.put("rows", NotDoneOrderList);
		
		return SUCCESS;
	}
	
	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}



}
