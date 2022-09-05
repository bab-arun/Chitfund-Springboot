package com.bb.chitfund.dto;

public class SchemeUserPendingPaymentDto {

	private int userId;
	private String userCode;
	private String userName;
	private long mobile;
	private int pendingPaymentCount;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public int getPendingPaymentCount() {
		return pendingPaymentCount;
	}

	public void setPendingPaymentCount(int pendingPaymentCount) {
		this.pendingPaymentCount = pendingPaymentCount;
	}

	public SchemeUserPendingPaymentDto() {
		super();
	}

	

}
