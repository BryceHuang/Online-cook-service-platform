package ofs.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ofs.exception.EbpException;
import ofs.javabean.UserMessage;
import ofs.service.MessageService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

@Controller
@Namespace("/user")
@ParentPackage("struts-default")
public class MessageAction {
	@Resource
	private MessageService messageService;
	@Resource
	private MessageService messsageService;
	
	//获取用户消息的实体类
	@Action(value="getUserMessageAction" ,results={
			@Result(name="success" ,location="/index.jsp"),
			@Result(name="input" ,location="/index.jsp")
	})
	public String getUserMessage() throws IOException{		
		UserMessage message=new UserMessage();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer messageId=(Integer) session.getAttribute("newMessageId");
		message=messageService.getUserMessageByID(messageId);
	
        PrintWriter pw = response.getWriter();
        pw.write("{\"message_title\":\""+message.getMessage_title()+"\",\"message_content\":\""+message.getMessage_content()+"\"}");
        pw.flush();
        pw.close();
        // #ddd System.out.println("{\"message_title\":\""+message.getMessage_title()+"\",\"message_content\":\""+message.getMessage_content()+"\"}");
		return "success";	
	}
	
	public int useMessageBox(String kind,String sender,Integer reciever,String title,String content) throws EbpException, IOException{				

	        System.out.println(kind);
	        System.out.println(sender);
	        System.out.println(reciever);
	        System.out.println(title);	        
	        System.out.println(content);
	        
	        System.out.print(kind+"      "+sender+"      "+reciever+"      "+title+"         "+content);
            int messageId=messsageService.insertMessage(kind, sender, reciever, title, content);            

            return messageId;
	}
	public int useMessageBox2(String kind,String sender,Integer reciever,String title,String content) throws EbpException, IOException{				        
        int messageId=messsageService.insertMessage(kind, sender, reciever, title, content);            
        return messageId;
}


}
