package ofs.javabean;

import java.io.Serializable;

public class Cart implements Serializable {
	private int tid;
	private String descinfo;
	private double price;
	private int quantity;
	private double amount;
	
	
	public Cart() {
	}


	public Cart(int tid, String descinfo, double price, int quantity,
			double amount) {
		this.tid = tid;
		this.descinfo = descinfo;
		this.price = price;
		this.quantity = quantity;
		this.amount = amount;
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


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Cart [tid=" + tid + ", descinfo=" + descinfo + ", price="
				+ price + ", quantity=" + quantity + ", amount=" + amount + "]";
	}
	
	
}
