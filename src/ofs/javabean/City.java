package ofs.javabean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体：城市
 *  */
@SuppressWarnings("serial")
@Entity
@Table(name="sys_city")
public class City  implements Serializable {
	@Id
	private String city_id;
	private String city_code;
	private String city_name;
	private String province_code;
	public City(){}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	@Override
	public String toString() {
		return "City [city_id=" + city_id + ", city_code=" + city_code
				+ ", city_name=" + city_name + ", province_code="
				+ province_code + "]";
	}
	public City(String city_id, String city_code, String city_name,
			String province_code) {
		super();
		this.city_id = city_id;
		this.city_code = city_code;
		this.city_name = city_name;
		this.province_code = province_code;
	}
	
	
}
