package ofs.javabean;

public class NotDoneOrder {  //显示订单的
	private int order_id;  //真正的唯一订单号码
	private String custom_name;
	private String custom_place;
	private String custom_tel;
	private Integer is_booked;
	private Double order_price;
	private Integer order_status;
	private Integer order_kind;
	private String order_remark;
	private String serve_time;
	private String start_time;
    
	//以下是historyorder页面多显示的字段
	private String order_comment;
    private Double order_score;
    private Double order_score_toCook;
    
    public String getOrder_comment() {
		return order_comment;
	}
	public void setOrder_comment(String order_comment) {
		this.order_comment = order_comment;
	}
	public Double getOrder_score() {
		return order_score;
	}
	public void setOrder_score(Double order_score) {
		this.order_score = order_score;
	}
	public Double getOrder_score_toCook() {
		return order_score_toCook;
	}
	public void setOrder_score_toCook(Double order_score_toCook) {
		this.order_score_toCook = order_score_toCook;
	}
    
	public NotDoneOrder(int order_id, String custom_name,
			String custom_place, String custom_tel, Integer is_booked,
			Double order_price, Integer order_status, Integer order_kind,
			String order_remark, String serve_time, String start_time,
			String order_comment, Double order_score, Double order_score_toCook) {
		super();
		this.order_id = order_id;
		this.custom_name = custom_name;
		this.custom_place = custom_place;
		this.custom_tel = custom_tel;
		this.is_booked = is_booked;
		this.order_price = order_price;
		this.order_status = order_status;
		this.order_kind = order_kind;
		this.order_remark = order_remark;
		this.serve_time = serve_time;
		this.start_time = start_time;
		this.order_comment = order_comment;
		this.order_score = order_score;
		this.order_score_toCook = order_score_toCook;
	}
	public NotDoneOrder(){}
    
	public NotDoneOrder(int order_id, String custom_name,
			String custom_place, String custom_tel, Integer is_booked,
			Double order_price, Integer order_status, Integer order_kind,
			String order_remark, String serve_time, String start_time) {
		super();
		this.order_id = order_id;
		this.custom_name = custom_name;
		this.custom_place = custom_place;
		this.custom_tel = custom_tel;
		this.is_booked = is_booked;
		this.order_price = order_price;
		this.order_status = order_status;
		this.order_kind = order_kind;
		this.order_remark = order_remark;
		this.serve_time = serve_time;
		this.start_time = start_time;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getCustom_name() {
		return custom_name;
	}
	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}
	public String getCustom_place() {
		return custom_place;
	}
	public void setCustom_place(String custom_place) {
		this.custom_place = custom_place;
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
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	

}
