package ofs.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_admin")
public class AdminUser  implements Serializable {
	@Id
	@GeneratedValue
	private Integer admin_id;
	
	private String admin_name;
	private String admin_rname;
    private String admin_password;	
	private Integer admin_level;  //1：管理员 （注意： 只要level比1大的都是客服）
	private Integer admin_group;
	private Date reg_time;
	private Integer admin_status;  //管理员的活动状态 1：活动 0：禁用
	public AdminUser(){}
	public String getAdmin_rname() {
		return admin_rname;
	}
	
	public Integer getAdmin_status() {
		return admin_status;
	}
	public void setAdmin_status(Integer admin_status) {
		this.admin_status = admin_status;
	}
	public void setAdmin_rname(String admin_rname) {
		this.admin_rname = admin_rname;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public Integer getAdmin_level() {
		return admin_level;
	}
	public void setAdmin_level(Integer admin_level) {
		this.admin_level = admin_level;
	}
	public Integer getAdmin_group() {
		return admin_group;
	}
	public void setAdmin_group(Integer admin_group) {
		this.admin_group = admin_group;
	}
	public Date getReg_time() {
		return reg_time;
	}
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}
	//包含所有参数的构造方法
	public AdminUser(Integer admin_id, String admin_name, String admin_rname,
			String admin_password, Integer admin_level, Integer admin_group,
			Date reg_time) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.admin_rname = admin_rname;
		this.admin_password = admin_password;
		this.admin_level = admin_level;
		this.admin_group = admin_group;
		this.reg_time = reg_time;
	}
	//少一个rname参数的构造方法
	public AdminUser(Integer admin_id, String admin_name, String admin_password,
			Integer admin_level, Integer admin_group, Date reg_time) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.admin_password = admin_password;
		this.admin_level = admin_level;
		this.admin_group = admin_group;
		this.reg_time = reg_time;
	}
	@Override
	public String toString() {
		return "AdminUser [admin_id=" + admin_id + ", admin_name=" + admin_name
				+ ", admin_password=" + admin_password + ", admin_level="
				+ admin_level + ", admin_group=" + admin_group + ", reg_time="
				+ reg_time + "]";
	}
	
	
	
}
/*
 * @GeneratedValue(generator="ass")
@GenericGenerator(name="ass" , strategy="assigned")*/
