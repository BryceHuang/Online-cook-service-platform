package ofs.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_user_money_record")
public class SysUserMoneyRecord  implements Serializable {
	@Id
	@GeneratedValue
	private Integer sys_user_money_record_id;
	private Integer user_id;
	private Integer operate_kind; //操作类型  1：充值  2：提现  3：支付
	private Double original_money;
	private Double left_money;
	private Double operate_money;
	private Date operate_time;
	private String bank_kind;  //银行类型
	private String card_id; //银行账户  提现时才需要使用
	private Integer pay_kind; //支付方式 1：余额 2：支付宝 3：微信 4:系统退款  只有在充值，支付的时候才使用 
	
	public SysUserMoneyRecord(){}
	
	public Integer getPay_kind() {
		return pay_kind;
	}

	public void setPay_kind(Integer pay_kind) {
		this.pay_kind = pay_kind;
	}

	public Double getOperate_money() {
		return operate_money;
	}

	public void setOperate_money(Double operate_money) {
		this.operate_money = operate_money;
	}

	public Integer getSys_user_money_record() {
		return sys_user_money_record_id;
	}
	public void setSys_user_money_record(Integer sys_user_money_record_id) {
		this.sys_user_money_record_id = sys_user_money_record_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
	
	public SysUserMoneyRecord(Integer sys_user_money_record_id, Integer user_id,
			Integer operate_kind, Double original_money, Double left_money,
			Double operate_money, Date operate_time, String bank_kind,
			String card_id) {
		super();
		this.sys_user_money_record_id = sys_user_money_record_id;
		this.user_id = user_id;
		this.operate_kind = operate_kind;
		this.original_money = original_money;
		this.left_money = left_money;
		this.operate_money = operate_money;
		this.operate_time = operate_time;
		this.bank_kind = bank_kind;
		this.card_id = card_id;
	}

	public SysUserMoneyRecord(Integer sys_user_money_record_id, Integer user_id,
			Integer operate_kind, Double original_money, Double left_money,
			Date operate_time, String bank_kind, String card_id) {
		super();
		this.sys_user_money_record_id = sys_user_money_record_id;
		this.user_id = user_id;
		this.operate_kind = operate_kind;
		this.original_money = original_money;
		this.left_money = left_money;
		this.operate_time = operate_time;
		this.bank_kind = bank_kind;
		this.card_id = card_id;
	}

	@Override
	public String toString() {
		return "SysUserMoneyRecord [sys_user_money_record_id="
				+ sys_user_money_record_id + ", user_id=" + user_id
				+ ", operate_kind=" + operate_kind + ", original_money="
				+ original_money + ", left_money=" + left_money
				+ ", operate_money=" + operate_money + ", operate_time="
				+ operate_time + ", bank_kind=" + bank_kind + ", card_id="
				+ card_id + ", pay_kind=" + pay_kind + "]";
	}

	
	

}
