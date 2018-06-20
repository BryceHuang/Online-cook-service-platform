package ofs.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ofs.dao.CookDAOJDBC;
import ofs.dao.OrderDaoJDBC;
import ofs.exception.EbpException;
import ofs.javabean.Cook;
import ofs.javabean.Order;
import ofs.service.CookService;
import ofs.service.OrderService;
import ofs.SysThread.RecieveOrderThread;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@Namespace("/cook")
@ParentPackage("struts-default")
@Action("RecieveOrderAction")
@Results({
	@Result(name="input" , location="/cook/index.jsp")
})
public class RecieveOrderAction {
	//接单操作，需要更新订单中的厨师id，订单状态和订单确认时间
	@Resource
	private CookService cookService;
	@Resource
	private OrderService orderService;
	private Integer oid;
	private String data; //返回
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
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		CookDAOJDBC cookDAOJDBC=new CookDAOJDBC();
		//Integer oid2=Integer.parseInt(request.getParameter("oid"));
		//System.out.println("request里面取得oid"+oid2);
		String content="";
		Cook cook=(Cook)session.getAttribute("cook");
		Integer cookId=cook.getCook_id();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache"); 
		RecieveOrderThread thread=new RecieveOrderThread();
		thread.setCid(cookId);
		thread.setOid(oid);
		thread.run();
		int isGet=thread.getIsGet();
		PrintWriter pw = response.getWriter();
        if(isGet==1)
        	content="接单成功";
        if(isGet==0)
        	content="接单失败，该订单已被其它厨师接收";
        pw.write("{\"message_content\":\""+content+"\"}");
        pw.flush();
        pw.close();
				
		return null;
	}

}
