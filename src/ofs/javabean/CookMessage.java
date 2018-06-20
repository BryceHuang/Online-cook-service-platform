package ofs.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cook_message")
public class CookMessage  implements Serializable {  //厨师的信息表
	@Id
	@GeneratedValue
	private Integer message_id;
	private String message_sender;
	private Integer message_reciever;
	private String message_title;
	private String message_content;
	private Integer is_read;
	private Date send_time;
	public CookMessage(){}
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public String getMessage_sender() {
		return message_sender;
	}
	public void setMessage_sender(String message_sender) {
		this.message_sender = message_sender;
	}
	public Integer getMessage_reciever() {
		return message_reciever;
	}
	public void setMessage_reciever(Integer message_reciever) {
		this.message_reciever = message_reciever;
	}
	public String getMessage_title() {
		return message_title;
	}
	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	public Integer getIs_read() {
		return is_read;
	}
	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}
	public CookMessage(Integer message_id, String message_sender,
			Integer message_reciever, String message_title,
			String message_content, Date send_time) {
		super();
		this.message_id = message_id;
		this.message_sender = message_sender;
		this.message_reciever = message_reciever;
		this.message_title = message_title;
		this.message_content = message_content;
		this.send_time = send_time;
	}
	
	public CookMessage(Integer message_id, String message_sender,
			Integer message_reciever, String message_title,
			String message_content, Integer is_read, Date send_time) {
		super();
		this.message_id = message_id;
		this.message_sender = message_sender;
		this.message_reciever = message_reciever;
		this.message_title = message_title;
		this.message_content = message_content;
		this.is_read = is_read;
		this.send_time = send_time;
	}
	@Override
	public String toString() {
		return "CookMessage [message_id=" + message_id + ", message_sender="
				+ message_sender + ", message_reciever=" + message_reciever
				+ ", message_title=" + message_title + ", message_content="
				+ message_content + ", is_read=" + is_read + ", send_time="
				+ send_time + "]";
	}
	
	
	
}
