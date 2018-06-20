package ofs.javabean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_user")
public class User implements Serializable {
	@Id
	@GeneratedValue
	private Integer user_id;
	private String user_name;//�û���
	private String user_rname;  //�û�������
	private String user_password;
	private Integer user_gender;  //1:男 0：女
	private Integer user_age;
	private String user_location;
	private Integer user_level;   //用户的分级 1：普通用户
	private String user_email;
	private String user_tel;
	private Integer user_status;   //0：禁用 1：活动
	private String user_specific;
	private Double user_score;
	private String user_picture;
	private Double user_money;
	private Date reg_time;
	private String user_pay_password;  //用户的支付密码
	private String location_code;
	private Integer online_status; //1：在线 0：离线
	private Date last_login_time; 
	//һ�Զ�
	/*
	 * @OneToMany(mappedBy="user")
	private List<Order> orders;*/

	public User() {		
	}
	
	
	
	
	public User(Integer user_id, String user_name, String user_rname,
			String user_password, Integer user_gender, Integer user_age,
			String user_location, Integer user_level, String user_email,
			String user_tel, Integer user_status, String user_specific,
			Double user_score, String user_picture, Double user_money,
			Date reg_time) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_rname = user_rname;
		this.user_password = user_password;
		this.user_gender = user_gender;
		this.user_age = user_age;
		this.user_location = user_location;
		this.user_level = user_level;
		this.user_email = user_email;
		this.user_tel = user_tel;
		this.user_status = user_status;
		this.user_specific = user_specific;
		this.user_score = user_score;
		this.user_picture = user_picture;
		this.user_money = user_money;
		this.reg_time = reg_time;
	}




	public String getLocation_code() {
		return location_code;
	}




	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}




	public User(String user_rname, String user_name, String user_password,
			Integer user_gender, Integer user_age, String user_location,
			Integer user_level, String user_email, String user_tel,
			Integer user_status, String user_specific, Double user_score,
			String user_picture, Double user_money, Date reg_time
			) {
		
		
		this.user_name = user_name;
		this.user_rname=user_rname;
		this.user_password = user_password;
		this.user_gender = user_gender;
		this.user_age = user_age;
		this.user_location = user_location;
		this.user_level = user_level;
		this.user_email = user_email;
		this.user_tel = user_tel;
		this.user_status = user_status;
		this.user_specific = user_specific;
		this.user_score = user_score;
		this.user_picture = user_picture;
		this.user_money = user_money;
		this.reg_time = reg_time;
		
	}
	
	public User(Integer user_id, String user_name, String user_rname,
			String user_password, Integer user_gender, Integer user_age,
			String user_location, Integer user_level, String user_email,
			String user_tel, Integer user_status, String user_specific,
			Double user_score, String user_picture, Double user_money,
			Date reg_time, String user_pay_password) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_rname = user_rname;
		this.user_password = user_password;
		this.user_gender = user_gender;
		this.user_age = user_age;
		this.user_location = user_location;
		this.user_level = user_level;
		this.user_email = user_email;
		this.user_tel = user_tel;
		this.user_status = user_status;
		this.user_specific = user_specific;
		this.user_score = user_score;
		this.user_picture = user_picture;
		this.user_money = user_money;
		this.reg_time = reg_time;
		this.user_pay_password = user_pay_password;
	}




	public Integer getOnline_status() {
		return online_status;
	}




	public void setOnline_status(Integer online_status) {
		this.online_status = online_status;
	}




	public Date getLast_login_time() {
		return last_login_time;
	}




	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}




	public String getUser_pay_password() {
		return user_pay_password;
	}




	public void setUser_pay_password(String user_pay_password) {
		this.user_pay_password = user_pay_password;
	}




	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_rname() {
		return user_rname;
	}
	public void setUser_rname(String user_rname) {
		this.user_rname = user_rname;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Integer getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(Integer user_gender) {
		this.user_gender = user_gender;
	}

	public Integer getUser_age() {
		return user_age;
	}

	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}

	public String getUser_location() {
		return user_location;
	}

	public void setUser_location(String user_location) {
		this.user_location = user_location;
	}

	public Integer getUser_level() {
		return user_level;
	}

	public void setUser_level(Integer user_level) {
		this.user_level = user_level;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public Integer getUser_status() {
		return user_status;
	}

	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}

	public String getUser_specific() {
		return user_specific;
	}

	public void setUser_specific(String user_specific) {
		this.user_specific = user_specific;
	}

	public Double getUser_score() {
		return user_score;
	}

	public void setUser_score(Double user_score) {
		this.user_score = user_score;
	}

	public String getUser_picture() {
		return user_picture;
	}

	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}

	public Double getUser_money() {
		return user_money;
	}

	public void setUser_money(Double user_money) {
		this.user_money = user_money;
	}

	public Date getReg_time() {
		return reg_time;
	}

	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}


	



	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", user_password=" + user_password + ", user_gender="
				+ user_gender + ", user_age=" + user_age + ", user_location="
				+ user_location + ", user_level=" + user_level
				+ ", user_email=" + user_email + ", user_tel=" + user_tel
				+ ", user_status=" + user_status + ", user_specific="
				+ user_specific + ", user_score=" + user_score
				+ ", user_picture=" + user_picture + ", user_money="
				+ user_money + ", reg_time=" + reg_time + ", orders=]";
	}	
		
}
