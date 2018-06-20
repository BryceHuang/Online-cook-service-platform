package ofs.javabean;


public class SimsSellRecord {

	private Integer sellRecordId;

	private String srNumber;

	private String action;

	public SimsSellRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SimsSellRecord(Integer sellRecordId, String srNumber, String action) {
		super();
		this.sellRecordId = sellRecordId;
		this.srNumber = srNumber;
		this.action = action;
	}

	public Integer getSellRecordId() {
		return sellRecordId;
	}

	public void setSellRecordId(Integer sellRecordId) {
		this.sellRecordId = sellRecordId;
	}

	public String getSrNumber() {
		return srNumber;
	}

	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

