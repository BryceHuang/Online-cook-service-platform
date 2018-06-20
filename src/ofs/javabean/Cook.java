package ofs.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="sys_cook")
public class Cook implements Serializable  {
	@Id
	@GeneratedValue
	private Integer cook_id;
	private String cook_name;
	private String cook_rname;
	private String cook_password;
	private String cook_location;
	private Integer cook_gender;
	private Integer cook_age;
	private String cook_tel;
	private Double cook_score;
	private Integer cook_workedtime;
	private String cook_idnumber;
	private String cook_idurl;
	private String cook_license;
	private String cook_licenseurl;
	private String cook_healthid;
	private String cook_healthurl;
	private Integer cook_status;
	private String cook_picture;
	private String cook_desc;
	private Double cook_money;
	private Integer is_aduit; //审核的状态 0：未审核 1：审核通过 2：审核不通过
	private Integer aduit_person; //审核人
	private String aduit_detail;  //审核通过或者不通过的详细原因
	private Date reg_time;
	private String location_code;
	public Cook()
	{
		
	}
	
	public String getLocation_code() {
		return location_code;
	}

	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}

	public Cook(Integer cook_id, String cook_name, String cook_rname,
			String cook_password, String cook_location, Integer cook_gender,
			Integer cook_age, String cook_tel, Double cook_score,
			Integer cook_workedtime, String cook_idnumber, String cook_idurl,
			String cook_license, String cook_licenseurl, String cook_healthid,
			String cook_healthurl, Integer cook_status, String cook_picture,
			String cook_desc, Double cook_money, Integer is_aduit, Date reg_time) {
		super();
		this.cook_id = cook_id;
		this.cook_name = cook_name;
		this.cook_rname = cook_rname;
		this.cook_password = cook_password;
		this.cook_location = cook_location;
		this.cook_gender = cook_gender;
		this.cook_age = cook_age;
		this.cook_tel = cook_tel;
		this.cook_score = cook_score;
		this.cook_workedtime = cook_workedtime;
		this.cook_idnumber = cook_idnumber;
		this.cook_idurl = cook_idurl;
		this.cook_license = cook_license;
		this.cook_licenseurl = cook_licenseurl;
		this.cook_healthid = cook_healthid;
		this.cook_healthurl = cook_healthurl;
		this.cook_status = cook_status;
		this.cook_picture = cook_picture;
		this.cook_desc = cook_desc;
		this.cook_money = cook_money;
		this.is_aduit = is_aduit;
		this.reg_time = reg_time;
	}

	@Override
	public String toString() {
		return "Cook [cook_id=" + cook_id + ", cook_name=" + cook_name
				+ ", cook_rname=" + cook_rname + ", cook_password="
				+ cook_password + ", cook_location=" + cook_location
				+ ", cook_gender=" + cook_gender + ", cook_age=" + cook_age
				+ ", cook_tel=" + cook_tel + ", cook_score=" + cook_score
				+ ", cook_workedtime=" + cook_workedtime + ", cook_idnumber="
				+ cook_idnumber + ", cook_idurl=" + cook_idurl
				+ ", cook_license=" + cook_license + ", cook_licenseurl="
				+ cook_licenseurl + ", cook_healthid=" + cook_healthid
				+ ", cook_healthurl=" + cook_healthurl + ", cook_status="
				+ cook_status + ", cook_picture=" + cook_picture
				+ ", cook_desc=" + cook_desc + ", cook_money=" + cook_money
				+ ", reg_time=" + reg_time + "]";
	}
	public Cook(Integer cook_id, String cook_name, String cook_rname,
			String cook_password, String cook_location, Integer cook_gender,
			Integer cook_age, String cook_tel, Double cook_score,
			Integer cook_workedtime, String cook_idnumber, String cook_idurl,
			String cook_license, String cook_licenseurl, String cook_healthid,
			String cook_healthurl, Integer cook_status, String cook_picture,
			String cook_desc, Double cook_money, Date reg_time) {
		super();
		this.cook_id = cook_id;
		this.cook_name = cook_name;
		this.cook_rname = cook_rname;
		this.cook_password = cook_password;
		this.cook_location = cook_location;
		this.cook_gender = cook_gender;
		this.cook_age = cook_age;
		this.cook_tel = cook_tel;
		this.cook_score = cook_score;
		this.cook_workedtime = cook_workedtime;
		this.cook_idnumber = cook_idnumber;
		this.cook_idurl = cook_idurl;
		this.cook_license = cook_license;
		this.cook_licenseurl = cook_licenseurl;
		this.cook_healthid = cook_healthid;
		this.cook_healthurl = cook_healthurl;
		this.cook_status = cook_status;
		this.cook_picture = cook_picture;
		this.cook_desc = cook_desc;
		this.cook_money = cook_money;
		this.reg_time = reg_time;
	}
	
	public Cook(Integer cook_id, String cook_name, String cook_rname,
			String cook_password, String cook_location, Integer cook_gender,
			Integer cook_age, String cook_tel, Double cook_score,
			Integer cook_workedtime, String cook_idnumber, String cook_idurl,
			String cook_license, String cook_licenseurl, String cook_healthid,
			String cook_healthurl, Integer cook_status, String cook_picture,
			String cook_desc, Double cook_money, Integer is_aduit,
			Integer aduit_person, String aduit_detail, Date reg_time) {
		super();
		this.cook_id = cook_id;
		this.cook_name = cook_name;
		this.cook_rname = cook_rname;
		this.cook_password = cook_password;
		this.cook_location = cook_location;
		this.cook_gender = cook_gender;
		this.cook_age = cook_age;
		this.cook_tel = cook_tel;
		this.cook_score = cook_score;
		this.cook_workedtime = cook_workedtime;
		this.cook_idnumber = cook_idnumber;
		this.cook_idurl = cook_idurl;
		this.cook_license = cook_license;
		this.cook_licenseurl = cook_licenseurl;
		this.cook_healthid = cook_healthid;
		this.cook_healthurl = cook_healthurl;
		this.cook_status = cook_status;
		this.cook_picture = cook_picture;
		this.cook_desc = cook_desc;
		this.cook_money = cook_money;
		this.is_aduit = is_aduit;
		this.aduit_person = aduit_person;
		this.aduit_detail = aduit_detail;
		this.reg_time = reg_time;
	}

	public Integer getAduit_person() {
		return aduit_person;
	}

	public void setAduit_person(Integer aduit_person) {
		this.aduit_person = aduit_person;
	}

	public String getAduit_detail() {
		return aduit_detail;
	}

	public void setAduit_detail(String aduit_detail) {
		this.aduit_detail = aduit_detail;
	}

	public Integer getIs_aduit() {
		return is_aduit;
	}
	public void setIs_aduit(Integer is_aduit) {
		this.is_aduit = is_aduit;
	}
	public void setCook_score(Double cook_score) {
		this.cook_score = cook_score;
	}
	public Integer getCook_id() {
		return cook_id;
	}
	public void setCook_id(Integer cook_id) {
		this.cook_id = cook_id;
	}
	public String getCook_name() {
		return cook_name;
	}
	public void setCook_name(String cook_name) {
		this.cook_name = cook_name;
	}
	public String getCook_rname() {
		return cook_rname;
	}
	public void setCook_rname(String cook_rname) {
		this.cook_rname = cook_rname;
	}
	public String getCook_password() {
		return cook_password;
	}
	public void setCook_password(String cook_password) {
		this.cook_password = cook_password;
	}
	public String getCook_location() {
		return cook_location;
	}
	public void setCook_location(String cook_location) {
		this.cook_location = cook_location;
	}
	public Integer getCook_gender() {
		return cook_gender;
	}
	public void setCook_gender(Integer cook_gender) {
		this.cook_gender = cook_gender;
	}
	public Integer getCook_age() {
		return cook_age;
	}
	public void setCook_age(Integer cook_age) {
		this.cook_age = cook_age;
	}
	public String getCook_tel() {
		return cook_tel;
	}
	public void setCook_tel(String cook_tel) {
		this.cook_tel = cook_tel;
	}
	public double getCook_score() {
		return cook_score;
	}
	public void setCook_score(double cook_score) {
		this.cook_score = cook_score;
	}
	public Integer getCook_workedtime() {
		return cook_workedtime;
	}
	public void setCook_workedtime(Integer cook_workedtime) {
		this.cook_workedtime = cook_workedtime;
	}
	public String getCook_idnumber() {
		return cook_idnumber;
	}
	public void setCook_idnumber(String cook_idnumber) {
		this.cook_idnumber = cook_idnumber;
	}
	public String getCook_idurl() {
		return cook_idurl;
	}
	public void setCook_idurl(String cook_idurl) {
		this.cook_idurl = cook_idurl;
	}
	public String getCook_license() {
		return cook_license;
	}
	public void setCook_license(String cook_license) {
		this.cook_license = cook_license;
	}
	public String getCook_licenseurl() {
		return cook_licenseurl;
	}
	public void setCook_licenseurl(String cook_licenseurl) {
		this.cook_licenseurl = cook_licenseurl;
	}
	public String getCook_healthid() {
		return cook_healthid;
	}
	public void setCook_healthid(String cook_healthid) {
		this.cook_healthid = cook_healthid;
	}
	public String getCook_healthurl() {
		return cook_healthurl;
	}
	public void setCook_healthurl(String cook_healthurl) {
		this.cook_healthurl = cook_healthurl;
	}
	public Integer getCook_status() {
		return cook_status;
	}
	public void setCook_status(Integer cook_status) {
		this.cook_status = cook_status;
	}
	public String getCook_picture() {
		return cook_picture;
	}
	public void setCook_picture(String cook_picture) {
		this.cook_picture = cook_picture;
	}
	public String getCook_desc() {
		return cook_desc;
	}
	public void setCook_desc(String cook_desc) {
		this.cook_desc = cook_desc;
	}
	public double getCook_money() {
		return cook_money;
	}
	public void setCook_money(Double cook_money) {
		this.cook_money = cook_money;
	}
	public Date getReg_time() {
		return reg_time;
	}
	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}

	
	

}
