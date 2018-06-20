package ofs.javabean;

import java.sql.Timestamp;

/**
 * SysOrderCookRecord entity. @author MyEclipse Persistence Tools
 */
public class SysOrderCookRecord extends AbstractSysOrderCookRecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SysOrderCookRecord() {
	}

	/** full constructor */
	public SysOrderCookRecord(Integer cookId, Integer orderAmount,
			Double orderMoneyAdmount, Integer orderNormal,
			Integer orderAbnormal, Double normalRate, Timestamp renewTime) {
		super(cookId, orderAmount, orderMoneyAdmount, orderNormal,
				orderAbnormal, normalRate, renewTime);
	}

}
