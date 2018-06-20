package ofs.javabean;

import java.util.Date;

public class OnlineOrder {

	private Integer orderId; //厨师显示页面的订单的序号
	private Integer order_id;  //真正的唯一订单号码
	private String custom_name;
	private String custom_place;
	private String custom_tel;
	private Integer is_booked;
	private Double order_price;
	private Integer order_status;
	private Integer order_kind;
	private String order_remark;
	private String serve_time;
	
	public OnlineOrder(){
		super();
	}
    
	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getCustom_place() {
		return custom_place;
	}

	public void setCustom_place(String custom_place) {
		this.custom_place = custom_place;
	}

	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCustom_name() {
		return custom_name;
	}

	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}

	public String getCustom_tel() {
		return custom_tel;
	}

	public void setCustom_tel(String custom_tel) {
		this.custom_tel = custom_tel;
	}

	public Integer getIs_booked() {
		return is_booked;
	}

	public void setIs_booked(Integer is_booked) {
		this.is_booked = is_booked;
	}

	public Double getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public Integer getOrder_kind() {
		return order_kind;
	}

	public void setOrder_kind(Integer order_kind) {
		this.order_kind = order_kind;
	}

	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	public String getServe_time() {
		return serve_time;
	}

	public void setServe_time(String serve_time) {
		this.serve_time = serve_time;
	}

	public OnlineOrder(Integer orderId, Integer oid, String custom_name,
			String custom_place, String custom_tel, Integer is_booked,
			Double order_price, Integer order_status, Integer order_kind,
			String order_remark, String serve_time) {
		super();
		this.orderId = orderId;
		this.order_id = oid;
		this.custom_name = custom_name;
		this.custom_place = custom_place;
		this.custom_tel = custom_tel;
		this.is_booked = is_booked;
		this.order_price = order_price;
		this.order_status = order_status;
		this.order_kind = order_kind;
		this.order_remark = order_remark;
		this.serve_time = serve_time;
	}

	
	
}
