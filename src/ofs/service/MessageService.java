package ofs.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ofs.dao.MessageDAO;
import ofs.exception.EbpException;
import ofs.javabean.CookMessage;
import ofs.javabean.ServerMessage;
import ofs.javabean.SysMessage;
import ofs.javabean.UserMessage;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Resource
	private MessageDAO messageDAO;
	

	//插入消息
	public int insertMessage(String kind,String sender,Integer reciever,String title,String content) throws EbpException{
		if(kind.equals("user")){
			int i=0;
			UserMessage usermessage=new UserMessage();
			usermessage.setMessage_sender(sender);
			usermessage.setMessage_reciever(reciever);
			usermessage.setMessage_title(title);
			usermessage.setMessage_content(content);
			usermessage.setSend_time(new Date());
			usermessage.setIs_read(0);
			i=messageDAO.insertUserMessage(usermessage);
			return i;
		}
		
		if(kind.equals("cook")){
			CookMessage cookmessage=new CookMessage();
			cookmessage.setMessage_sender(sender);
			cookmessage.setMessage_reciever(reciever);
			cookmessage.setMessage_title(title);
			cookmessage.setMessage_content(content);
			cookmessage.setSend_time(new Date());
			cookmessage.setIs_read(0);
			int i=messageDAO.insertCookMessage(cookmessage);
			return i;
		}
		if(kind.equals("server")){
			ServerMessage servermessage=new ServerMessage();
			servermessage.setMessage_sender(sender);
			servermessage.setMessage_reciever(reciever);
			servermessage.setMessage_title(title);
			servermessage.setMessage_content(content);
			servermessage.setSend_time(new Date());
			servermessage.setIs_read(0);
			int i=messageDAO.insertServerMessage(servermessage);
			return i;
		}
		if(kind.equals("sys")){
			SysMessage sysmessage=new SysMessage();
			sysmessage.setMessage_sender(sender);
			sysmessage.setMessage_reciever(reciever);
			sysmessage.setMessage_title(title);
			sysmessage.setMessage_content(content);
			sysmessage.setSend_time(new Date());
			sysmessage.setIs_read(0);
			int i=messageDAO.insertSysMessage(sysmessage);
			return i;
		}
		
		return 0;
	}
	
	
	
	
	//获取消息
	public UserMessage getUserMessageByID(Integer id){
		return messageDAO.selectUserMessageId(id);		
	}
	
	public CookMessage getCookMessageByID(Integer id){
		return messageDAO.selectCookMessageId(id);		
	}
	
	public ServerMessage getServerMessageByID(Integer id){
		return messageDAO.selectServerMessageId(id);		
	}
	
	public List<UserMessage> getUserMessageByUid(Integer uid){
		return messageDAO.selectUserMessageUID(uid);
	}
	public List<CookMessage> getCookMessageByCid(Integer cid){
		return messageDAO.selectCookMessageCID(cid);
	}
	public List<ServerMessage> getServerMessageBySid(Integer sid){
		return messageDAO.selectServerMessageSID(sid);
	}

	

}
