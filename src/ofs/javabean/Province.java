package ofs.javabean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 实体：省份
 *  */
@SuppressWarnings("serial")
@Entity
@Table(name="sys_province")
public class Province  implements Serializable {
	@Id
	private String province_id;
	private String province_code;
	private String province_name;
	public Province(){}
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public Province(String province_id, String province_code,
			String province_name) {
		super();
		this.province_id = province_id;
		this.province_code = province_code;
		this.province_name = province_name;
	}
	@Override
	public String toString() {
		return "Province [province_id=" + province_id + ", province_code="
				+ province_code + ", province_name=" + province_name + "]";
	}
	
}
