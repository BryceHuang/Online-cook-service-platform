package ofs.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login_log")
public class UserLoginLog implements Serializable{
	@Id
	@GeneratedValue
	private Integer login_log_id;
	private String user_name;
	private Integer user_type; //用户类型  1：普通用户 2：厨师  3：客服  4：管理员
	private String login_ip;
	private Date login_time;
	public UserLoginLog(){}
	public Integer getLogin_log_id() {
		return login_log_id;
	}
	public void setLogin_log_id(Integer login_log_id) {
		this.login_log_id = login_log_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	
	

}
