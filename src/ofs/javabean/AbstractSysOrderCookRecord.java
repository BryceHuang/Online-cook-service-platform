package ofs.javabean;

import java.sql.Timestamp;

/**
 * AbstractSysOrderCookRecord entity provides the base persistence definition of
 * the SysOrderCookRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSysOrderCookRecord implements
		java.io.Serializable {

	// Fields

	private Integer sysOrderCookRecordId;
	private Integer cookId;
	private Integer orderAmount;
	private Double orderMoneyAdmount;
	private Integer orderNormal;
	private Integer orderAbnormal;
	private Double normalRate;
	private Timestamp renewTime;

	// Constructors

	/** default constructor */
	public AbstractSysOrderCookRecord() {
	}

	/** full constructor */
	public AbstractSysOrderCookRecord(Integer cookId, Integer orderAmount,
			Double orderMoneyAdmount, Integer orderNormal,
			Integer orderAbnormal, Double normalRate, Timestamp renewTime) {
		this.cookId = cookId;
		this.orderAmount = orderAmount;
		this.orderMoneyAdmount = orderMoneyAdmount;
		this.orderNormal = orderNormal;
		this.orderAbnormal = orderAbnormal;
		this.normalRate = normalRate;
		this.renewTime = renewTime;
	}

	// Property accessors

	public Integer getSysOrderCookRecordId() {
		return this.sysOrderCookRecordId;
	}

	public void setSysOrderCookRecordId(Integer sysOrderCookRecordId) {
		this.sysOrderCookRecordId = sysOrderCookRecordId;
	}

	public Integer getCookId() {
		return this.cookId;
	}

	public void setCookId(Integer cookId) {
		this.cookId = cookId;
	}

	public Integer getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Double getOrderMoneyAdmount() {
		return this.orderMoneyAdmount;
	}

	public void setOrderMoneyAdmount(Double orderMoneyAdmount) {
		this.orderMoneyAdmount = orderMoneyAdmount;
	}

	public Integer getOrderNormal() {
		return this.orderNormal;
	}

	public void setOrderNormal(Integer orderNormal) {
		this.orderNormal = orderNormal;
	}

	public Integer getOrderAbnormal() {
		return this.orderAbnormal;
	}

	public void setOrderAbnormal(Integer orderAbnormal) {
		this.orderAbnormal = orderAbnormal;
	}

	public Double getNormalRate() {
		return this.normalRate;
	}

	public void setNormalRate(Double normalRate) {
		this.normalRate = normalRate;
	}

	public Timestamp getRenewTime() {
		return this.renewTime;
	}

	public void setRenewTime(Timestamp renewTime) {
		this.renewTime = renewTime;
	}

}