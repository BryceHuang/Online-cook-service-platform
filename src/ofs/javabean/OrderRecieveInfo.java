package ofs.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_order_recieve_info")
public class OrderRecieveInfo implements Serializable { //接单时用来存储需要的的信息
	@Id
	@GeneratedValue
	private Integer info_id;
	private Integer user_id;
	private Integer cook_id;
	private Integer order_id;
	private Integer Order_status;  //1：刚发布 2：已近接单了 不同的厨师在接单的时候都要根据这个来去查询，所以在接单之前要确认这个订单是否被别的厨师接了 0:订单已被取消了，相当于这个状态的接收订单信息已经没有用了
	private Date update_time;
	public OrderRecieveInfo(){}
	
	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getInfo_id() {
		return info_id;
	}
	public void setInfo_id(Integer info_id) {
		this.info_id = info_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getCook_id() {
		return cook_id;
	}
	public void setCook_id(Integer cook_id) {
		this.cook_id = cook_id;
	}
	public Integer getOrder_status() {
		return Order_status;
	}
	public void setOrder_status(Integer order_status) {
		Order_status = order_status;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public OrderRecieveInfo(Integer info_id, Integer user_id, Integer cook_id,
			Integer order_status, Date update_time) {
		super();
		this.info_id = info_id;
		this.user_id = user_id;
		this.cook_id = cook_id;
		Order_status = order_status;
		this.update_time = update_time;
	}
	
	public OrderRecieveInfo(Integer info_id, Integer user_id, Integer cook_id,
			Integer order_id, Integer order_status, Date update_time) {
		super();
		this.info_id = info_id;
		this.user_id = user_id;
		this.cook_id = cook_id;
		this.order_id = order_id;
		Order_status = order_status;
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "OrderRecieveInfo [info_id=" + info_id + ", user_id=" + user_id
				+ ", cook_id=" + cook_id + ", order_id=" + order_id
				+ ", Order_status=" + Order_status + ", update_time="
				+ update_time + "]";
	}

	
	

}
