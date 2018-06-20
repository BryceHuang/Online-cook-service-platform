package ofs.javabean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体：地区
 *  */

@SuppressWarnings("serial")
@Entity
@Table(name="sys_area")
public class Area  implements Serializable{
	@Id
	private String area_id;
	private String area_code;
	private String area_name;
	private String city_code;
	public Area(){}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public Area(String area_id, String area_code, String area_name,
			String city_code) {
		super();
		this.area_id = area_id;
		this.area_code = area_code;
		this.area_name = area_name;
		this.city_code = city_code;
	}
	@Override
	public String toString() {
		return "Area [area_id=" + area_id + ", area_code=" + area_code
				+ ", area_name=" + area_name + ", city_code=" + city_code + "]";
	}
	

}
