package ofs.dao;

import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.CookMessage;
import ofs.javabean.ServerMessage;
import ofs.javabean.SysMessage;
import ofs.javabean.UserMessage;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_USER_MESSAGE="from UserMessage";
	private static final String SELECT_COOK_MESSAGE="from CookMessage";
	private static final String SELECT_SERVER_MESSAGE="from ServerMessage";
	private static final String SELECT_SYS_MESSAGE="from SysMessage";
	
	
	private static final String SELECT_USER_MESSAGE_ID="from UserMessage where message_id=? order by send_time desc";
	private static final String SELECT_COOK_MESSAGE_ID="from CookMessage where message_id=? order by send_time desc";
	private static final String SELECT_SERVER_MESSAGE_ID="from ServerMessage where message_id=? order by send_time desc";
	private static final String SELECT_SYS_MESSAGE_ID="from SysMessage where message_id=? order by send_time desc";
	
	
	
	private static final String SELECT_USER_MESSAGE_UID="from UserMessage where message_reciever=? order by send_time desc";
	private static final String SELECT_COOK_MESSAGE_CID="from CookMessage where message_reciever=? order by send_time desc";
	private static final String SELECT_SERVER_MESSAGE_SID="from ServerMessage where message_reciever=? order by send_time desc";
	
	private static final String AUTO_SELECT_USER_MESSAGE="from UserMessage where message_reciever=? and is_read=? order by send_time desc";
	private static final String AUTO_SELECT_COOK_MESSAGE="from CookMessage where message_reciever=? and is_read=? order by send_time desc";
	private static final String AUTO_SELECT_SERVER_MESSAGE="from ServerMessage where message_reciever=? and is_read=? order by send_time desc";
	private static final String AUTO_SELECT_SYS_MESSAGE="from SysMessage where message_reciever=? and is_read=? order by send_time desc";
	
	
	
	
	
	/**
	 * 注意默认添加消息的时候是设置为未读的，即是0
	 * */
	/*.................................消息插入操作...................................................*/
	//添加用户消息
	public int insertUserMessage(UserMessage message) throws EbpException{
		int i=0;
		try{
		i=(Integer) hibernateTemplate.save(message);
		} catch (Exception e) {
		throw new EbpException("添加用户消息失败");
		}
		//System.out.print("通知消息的id："+message.getMessage_id());
		return message.getMessage_id();
	}
	
	//添加厨师消息
	public int insertCookMessage(CookMessage message) throws EbpException{
		int i=0;
		try{
		i=(Integer) hibernateTemplate.save(message);
		} catch (Exception e) {
		throw new EbpException("添加厨师消息失败");
		}
		return message.getMessage_id();
	}
		//添加用户消息
	public int insertServerMessage(ServerMessage message) throws EbpException{
		int i=0;
		try{
		i=(Integer) hibernateTemplate.save(message);
		} catch (Exception e) {
		throw new EbpException("添加客服消息失败");
		}
		return message.getMessage_id();
	}
	
		//添加用户消息
	public int insertSysMessage(SysMessage message) throws EbpException{
		int i=0;
		try{
		i=(Integer) hibernateTemplate.save(message);
		} catch (Exception e) {
		throw new EbpException("添加系统消息失败");
		}
		return message.getMessage_id();
	}
	
	/*-----------------------------------按着msg的id查message------------------------------------------------*/
	public UserMessage selectUserMessageId(Integer id){
		List<UserMessage> userMessage=hibernateTemplate.find(SELECT_USER_MESSAGE_ID,new Object[]{id});		
		return userMessage.get(0);
		
	}
	public CookMessage selectCookMessageId(Integer id){
		List<CookMessage> cookMessage=hibernateTemplate.find(SELECT_COOK_MESSAGE_ID,new Object[]{id});		
		return cookMessage.get(0);
		
	}
	public ServerMessage selectServerMessageId(Integer id){
		List<ServerMessage> serverMessage=hibernateTemplate.find(SELECT_SERVER_MESSAGE_ID,new Object[]{id});		
		return serverMessage.get(0);
		
	}
	public SysMessage selectSysMessageId(Integer id){
		List<SysMessage> sysMessage=hibernateTemplate.find(SELECT_SYS_MESSAGE_ID,new Object[]{id});		
		return sysMessage.get(0);
		
	}
	
	/*-----------------------------------按着对应的角色的id查他的所有message------------------------------------------------*/
	public List<UserMessage> selectUserMessageUID(Integer uid){
		return hibernateTemplate.find(SELECT_USER_MESSAGE_UID,new Object[]{uid});
	}
	public List<CookMessage> selectCookMessageCID(Integer cid){
		return hibernateTemplate.find(SELECT_COOK_MESSAGE_CID,new Object[]{cid});
	}
	public List<ServerMessage> selectServerMessageSID(Integer sid){
		return hibernateTemplate.find(SELECT_SERVER_MESSAGE_SID,new Object[]{sid});
	}
	public List<SysMessage> selectUserMessageUID(){
		return hibernateTemplate.find(SELECT_SYS_MESSAGE);
	}	
	
	
	/*----------------------------------- ajax轮询要用的带条件的message------------------------------------------------*/
    //取未阅读的24小时之内的对应id的message
	public List<UserMessage> autoSelectUserMessage(Integer uid,Integer isRead){
		return hibernateTemplate.find(AUTO_SELECT_USER_MESSAGE,new Object[]{uid,isRead});
		
	}
	public List<UserMessage> autoSelectCookMessage(Integer cid,Integer isRead){
		return hibernateTemplate.find(AUTO_SELECT_COOK_MESSAGE,new Object[]{cid,isRead});
		
	}
	public List<UserMessage> autoSelectServerMessage(Integer sid,Integer isRead){
		return hibernateTemplate.find(AUTO_SELECT_SERVER_MESSAGE,new Object[]{sid,isRead});
		
	}
	
	
	public List<UserMessage> autoSelectSysMessage(Integer uid,Integer isRead){
		return hibernateTemplate.find(AUTO_SELECT_SYS_MESSAGE,new Object[]{uid,isRead});
		
	}
	
	
		
	
	
	
}
