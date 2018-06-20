package ofs.javabean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_cook_money_record")
public class SysCookMoneyRecord {
	@Id
	@GeneratedValue
	private Integer sys_cook_money_record_id;
	private Integer cook_id;
	private Integer operate_kind; //操作类型  1：充值  2：提现  充值是用户在订单评价之后才给厨师充值的
	private Double original_money;
	private Double left_money;
	private Double operate_money;
	private Date operate_time;
	private String bank_kind;  //银行类型
	private String card_id; //银行账户  提现时才需要使用
	public SysCookMoneyRecord(){}
	public Integer getSys_user_money_record_id() {
		return sys_cook_money_record_id;
	}
	public void setSys_user_money_record_id(Integer sys_cook_money_record_id) {
		this.sys_cook_money_record_id = sys_cook_money_record_id;
	}
	public Integer getUser_id() {
		return cook_id;
	}
	public void setUser_id(Integer user_id) {
		this.cook_id = user_id;
	}
	public Integer getOperate_kind() {
		return operate_kind;
	}
	public void setOperate_kind(Integer operate_kind) {
		this.operate_kind = operate_kind;
	}
	public Double getOriginal_money() {
		return original_money;
	}
	public void setOriginal_money(Double original_money) {
		this.original_money = original_money;
	}
	public Double getLeft_money() {
		return left_money;
	}
	public void setLeft_money(Double left_money) {
		this.left_money = left_money;
	}
	public Double getOperate_money() {
		return operate_money;
	}
	public void setOperate_money(Double operate_money) {
		this.operate_money = operate_money;
	}
	public Date getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}
	public String getBank_kind() {
		return bank_kind;
	}
	public void setBank_kind(String bank_kind) {
		this.bank_kind = bank_kind;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	@Override
	public String toString() {
		return "SysCookMoneyRecord [sys_user_money_record_id="
				+ sys_cook_money_record_id + ", user_id=" + cook_id
				+ ", operate_kind=" + operate_kind + ", original_money="
				+ original_money + ", left_money=" + left_money
				+ ", operate_money=" + operate_money + ", operate_time="
				+ operate_time + ", bank_kind=" + bank_kind + ", card_id="
				+ card_id + "]";
	}
	public SysCookMoneyRecord(Integer sys_cook_money_record_id,
			Integer cook_id, Integer operate_kind, Double original_money,
			Double left_money, Double operate_money, Date operate_time,
			String bank_kind, String card_id) {
		super();
		this.sys_cook_money_record_id = sys_cook_money_record_id;
		this.cook_id = cook_id;
		this.operate_kind = operate_kind;
		this.original_money = original_money;
		this.left_money = left_money;
		this.operate_money = operate_money;
		this.operate_time = operate_time;
		this.bank_kind = bank_kind;
		this.card_id = card_id;
	}
	


}
