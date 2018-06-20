package ofs.javabean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_order")
public class Order implements Serializable{
	@Id
	@GeneratedValue
	private Integer order_id;	
	private Integer user_id;
	private Integer cook_id;
	private String Custom_Name;   //订单顾客姓名
	private String Custom_Place;   //地点
    private String Custom_Tel;    //订单顾客的手机号
	private Integer is_booked;        //原来是用来记录是否付了订金  现在改为是否支付了 1：付了定金 2：付了全款
	private Double order_price;    //订单的价格̬
	private Integer order_status;     //1：刚发布；2：刚接单；3：正服务；4：服务已经结束； 5：（需要用户支付尾款） 6：订单结束  0：已被取消
	private Integer order_has_material;
	private Integer order_kind;
	private String order_foodlist;     //具体菜式 暂时用于存储菜系和订单种类
	private String order_remark;  //订单备注
	private String order_comment;
	private String order_comment_url;
	private Double order_score;
	private Double order_score_ToCook;
	private Double order_score_ToUser;
	private Date start_time;  //订单的发布时间
	private Date serve_time;  //订单设置的服务时间
	private Date insure_time; //接单的时间
	private Date serve_sure_time; //确认服务的时间   在接单之后，开始服务的时候更新
	private Date end_time;    //订单的结束时间 用户评分之后就算结束
	private Integer is_change; //0:刚申请 1：已变更通过 2：变更不通过
	private Integer change_person;
	private String change_applicant; //记录申请的人，插入的时候拼接上user或者cook，区分是普通用户还是厨师申请的
	private Date change_time;
	private String change_detail;
	private String Location_Code; //地点的编码
	private String Order_Menu;
	
	
	/*
	 * //多对一
	@ManyToOne
	//连接字段 更改name的值就会导致admin_user的注入有问题，框架全蹦，不知道为什么
	@JoinColumn(name="uid")
	private User user;*/
	
	
   /*
    * 	//一对多
	*/

	public Order() {
	}

	
	public String getOrder_Menu() {
		return Order_Menu;
	}


	public void setOrder_Menu(String order_Menu) {
		Order_Menu = order_Menu;
	}


	public String getLocation_Code() {
		return Location_Code;
	}


	public void setLocation_Code(String location_Code) {
		Location_Code = location_Code;
	}


	public String getChange_applicant() {
		return change_applicant;
	}

	public void setChange_applicant(String change_applicant) {
		this.change_applicant = change_applicant;
	}

	public Date getServe_sure_time() {
		return serve_sure_time;
	}

	public void setServe_sure_time(Date serve_sure_time) {
		this.serve_sure_time = serve_sure_time;
	}



	
	public String getCustom_Place() {
		return Custom_Place;
	}


	public void setCustom_Place(String custom_Place) {
		Custom_Place = custom_Place;
	}



	
	public String getCustom_Tel() {
		return Custom_Tel;
	}

	public void setCustom_Tel(String custom_Tel) {
		Custom_Tel = custom_Tel;
	}



	public String getCustom_Name() {
		return Custom_Name;
	}



	public void setCustom_Name(String custom_Name) {
		Custom_Name = custom_Name;
	}




/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	




	public Integer getOrder_id() {
		return order_id;
	}



	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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



	public Integer getOrder_has_material() {
		return order_has_material;
	}



	public void setOrder_has_material(Integer order_has_material) {
		this.order_has_material = order_has_material;
	}



	public Integer getOrder_kind() {
		return order_kind;
	}



	public void setOrder_kind(Integer order_kind) {
		this.order_kind = order_kind;
	}





	public String getOrder_foodlist() {
		return order_foodlist;
	}

	public void setOrder_foodlist(String order_foodlist) {
		this.order_foodlist = order_foodlist;
	}

	public String getOrder_remark() {
		return order_remark;
	}



	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}



	public String getOrder_comment() {
		return order_comment;
	}



	public void setOrder_comment(String order_comment) {
		this.order_comment = order_comment;
	}



	public String getOrder_comment_url() {
		return order_comment_url;
	}



	public void setOrder_comment_url(String order_comment_url) {
		this.order_comment_url = order_comment_url;
	}



	public Double getOrder_score() {
		return order_score;
	}



	public void setOrder_score(Double order_score) {
		this.order_score = order_score;
	}



	public Double getOrder_score_ToCook() {
		return order_score_ToCook;
	}



	public void setOrder_score_ToCook(Double order_score_ToCook) {
		this.order_score_ToCook = order_score_ToCook;
	}



	public Double getOrder_score_ToUser() {
		return order_score_ToUser;
	}



	public void setOrder_score_ToUser(Double order_score_ToUser) {
		this.order_score_ToUser = order_score_ToUser;
	}



	public Date getStart_time() {
		return start_time;
	}



	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}



	public Date getEnd_time() {
		return end_time;
	}



	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}




	public Integer getIs_change() {
		return is_change;
	}



	public void setIs_change(Integer is_change) {
		this.is_change = is_change;
	}



	public Integer getChange_person() {
		return change_person;
	}



	public void setChange_person(Integer change_person) {
		this.change_person = change_person;
	}



	public Date getChange_time() {
		return change_time;
	}



	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}



	public String getChange_detail() {
		return change_detail;
	}



	public void setChange_detail(String change_detail) {
		this.change_detail = change_detail;
	}







	public Order(Integer order_id, Integer user_id, Integer cook_id,
			Integer is_booked, Double order_price, Integer order_status,
			Integer order_has_material, Integer order_kind, String food_list,
			String order_remark, String order_comment,
			String order_comment_url, Double order_score,
			Double order_score_ToCook, Double order_score_ToUser,
			Date start_time, Date serve_time, Date insure_time, Date end_time,
			Integer is_change, Integer change_person, Date change_time,
			String change_detail, User user) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.cook_id = cook_id;
		this.is_booked = is_booked;
		this.order_price = order_price;
		this.order_status = order_status;
		this.order_has_material = order_has_material;
		this.order_kind = order_kind;
		this.order_foodlist = food_list;
		this.order_remark = order_remark;
		this.order_comment = order_comment;
		this.order_comment_url = order_comment_url;
		this.order_score = order_score;
		this.order_score_ToCook = order_score_ToCook;
		this.order_score_ToUser = order_score_ToUser;
		this.start_time = start_time;
		this.serve_time = serve_time;
		this.insure_time = insure_time;
		this.end_time = end_time;
		this.is_change = is_change;
		this.change_person = change_person;
		this.change_time = change_time;
		this.change_detail = change_detail;
		//this.user = user;
		
	}



	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id
				+ ", cook_id=" + cook_id + ", is_booked=" + is_booked
				+ ", order_price=" + order_price + ", order_status="
				+ order_status + ", order_has_material=" + order_has_material
				+ ", order_kind=" + order_kind + ", food_list=" + order_foodlist
				+ ", order_remark=" + order_remark + ", order_comment="
				+ order_comment + ", order_comment_url=" + order_comment_url
				+ ", order_score=" + order_score + ", order_score_ToCook="
				+ order_score_ToCook + ", order_score_ToUser="
				+ order_score_ToUser + ", start_time=" + start_time
				+ ", serve_time=" + serve_time + ", insure_time=" + insure_time
				+ ", end_time=" + end_time + ", is_change=" + is_change
				+ ", change_person=" + change_person + ", change_time="
				+ change_time + ", change_detail=" + change_detail + ", user= ]";
	}



	



	public Order(Integer order_id, Integer user_id, Integer cook_id,
			String custom_Name, Integer is_booked, Double order_price,
			Integer order_status, Integer order_has_material, Integer order_kind,
			String food_list, String order_remark, String order_comment,
			String order_comment_url, Double order_score,
			Double order_score_ToCook, Double order_score_ToUser,
			Date start_time, Date serve_time, Date insure_time, Date end_time,
			Integer is_change, Integer change_person, Date change_time,
			String change_detail, User user) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.cook_id = cook_id;
		Custom_Name = custom_Name;
		this.is_booked = is_booked;
		this.order_price = order_price;
		this.order_status = order_status;
		this.order_has_material = order_has_material;
		this.order_kind = order_kind;
		this.order_foodlist = food_list;
		this.order_remark = order_remark;
		this.order_comment = order_comment;
		this.order_comment_url = order_comment_url;
		this.order_score = order_score;
		this.order_score_ToCook = order_score_ToCook;
		this.order_score_ToUser = order_score_ToUser;
		this.start_time = start_time;
		this.serve_time = serve_time;
		this.insure_time = insure_time;
		this.end_time = end_time;
		this.is_change = is_change;
		this.change_person = change_person;
		this.change_time = change_time;
		this.change_detail = change_detail;
		//this.user = user;
		
	}

	public Date getServe_time() {
		return serve_time;
	}



	public void setServe_time(Date serve_time) {
		this.serve_time = serve_time;
	}



	public Date getInsure_time() {
		return insure_time;
	}



	public void setInsure_time(Date insure_time) {
		this.insure_time = insure_time;
	}



	public Order(Date commitTime, Integer sumAmount, User user2) {
		// TODO Auto-generated constructor stub
	}
	
	public Order(Integer order_id, Integer user_id, Integer cook_id,
			String custom_Name, String custom_Place, String custom_Tel,
			Integer is_booked, Double order_price, Integer order_status,
			Integer order_has_material, Integer order_kind,
			String order_foodlist, String order_remark, String order_comment,
			String order_comment_url, Double order_score,
			Double order_score_ToCook, Double order_score_ToUser,
			Date start_time, Date serve_time, Date insure_time,
			Date serve_sure_time, Date end_time, Integer is_change,
			Integer change_person, Date change_time, String change_detail) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.cook_id = cook_id;
		Custom_Name = custom_Name;
		Custom_Place = custom_Place;
		Custom_Tel = custom_Tel;
		this.is_booked = is_booked;
		this.order_price = order_price;
		this.order_status = order_status;
		this.order_has_material = order_has_material;
		this.order_kind = order_kind;
		this.order_foodlist = order_foodlist;
		this.order_remark = order_remark;
		this.order_comment = order_comment;
		this.order_comment_url = order_comment_url;
		this.order_score = order_score;
		this.order_score_ToCook = order_score_ToCook;
		this.order_score_ToUser = order_score_ToUser;
		this.start_time = start_time;
		this.serve_time = serve_time;
		this.insure_time = insure_time;
		this.serve_sure_time = serve_sure_time;
		this.end_time = end_time;
		this.is_change = is_change;
		this.change_person = change_person;
		this.change_time = change_time;
		this.change_detail = change_detail;
	}

	public Order(Integer order_id, Integer user_id, Integer cook_id,
			String custom_Name, String custom_Place, String custom_Tel,
			Integer is_booked, Double order_price, Integer order_status,
			Integer order_has_material, Integer order_kind,
			String order_foodlist, String order_remark, String order_comment,
			String order_comment_url, Double order_score,
			Double order_score_ToCook, Double order_score_ToUser,
			Date start_time, Date serve_time, Date insure_time,
			Date serve_sure_time, Date end_time, Integer is_change,
			Integer change_person, String change_applicant, Date change_time,
			String change_detail) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.cook_id = cook_id;
		Custom_Name = custom_Name;
		Custom_Place = custom_Place;
		Custom_Tel = custom_Tel;
		this.is_booked = is_booked;
		this.order_price = order_price;
		this.order_status = order_status;
		this.order_has_material = order_has_material;
		this.order_kind = order_kind;
		this.order_foodlist = order_foodlist;
		this.order_remark = order_remark;
		this.order_comment = order_comment;
		this.order_comment_url = order_comment_url;
		this.order_score = order_score;
		this.order_score_ToCook = order_score_ToCook;
		this.order_score_ToUser = order_score_ToUser;
		this.start_time = start_time;
		this.serve_time = serve_time;
		this.insure_time = insure_time;
		this.serve_sure_time = serve_sure_time;
		this.end_time = end_time;
		this.is_change = is_change;
		this.change_person = change_person;
		this.change_applicant = change_applicant;
		this.change_time = change_time;
		this.change_detail = change_detail;
	}

     

	
}
