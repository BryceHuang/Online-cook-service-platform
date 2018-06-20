package ofs.javabean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Ticket implements Serializable{
	@Id
	@GeneratedValue
	private int tid;
	private String descinfo;
	private Date startTime;
	private int amount;
	private int balance;
	private double price;
	private int status;
	private String picurl;
	private String miaoshu;
	
	
	public Ticket() {		
	}
	public Ticket(int tid, String descinfo, Date startTime, int amount,
			int balance, double price, int status,String picurl,String miaoshu) {
		this.tid = tid;
		this.descinfo = descinfo;
		this.startTime = startTime;
		this.amount = amount;
		this.balance = balance;
		this.price = price;
		this.status = status;
		this.picurl=picurl;
		this.miaoshu=miaoshu;
	}
	public Ticket(String descinfo, Date startTime, int amount, int balance,
			double price, int status,String picurl,String miaoshu) {
		this.descinfo = descinfo;
		this.startTime = startTime;
		this.amount = amount;
		this.balance = balance;
		this.price = price;
		this.status = status;
		this.picurl=picurl;
		this.miaoshu=miaoshu;
	}
	
	
	
	public String getMiaoshu() {
		return miaoshu;
	}
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getDescinfo() {
		return descinfo;
	}
	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Ticket [tid=" + tid + ", descinfo=" + descinfo + ", startTime="
				+ startTime + ", amount=" + amount + ", balance=" + balance
				+ ", price=" + price + ", status=" + status + "]";
	} 
	
}
