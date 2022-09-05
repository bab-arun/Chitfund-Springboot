package com.bb.chitfund.dto;

import java.sql.Date;

public class UserSchemeCountDto {

	private int id;
	private String schemeName;
	private long schemeAmount;
	private long numberOfUser;
	private long schemeUserCount;
	private long payAmount;
	private int schemeDuration;
	private Date startDate;
	private Date endDate;

	public long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public long getSchemeAmount() {
		return schemeAmount;
	}

	public void setSchemeAmount(long schemeAmount) {
		this.schemeAmount = schemeAmount;
	}

	public long getNumberOfUser() {
		return numberOfUser;
	}

	public void setNumberOfUser(long numberOfUser) {
		this.numberOfUser = numberOfUser;
	}

	public long getSchemeUserCount() {
		return schemeUserCount;
	}

	public void setSchemeUserCount(long schemeUserCount) {
		this.schemeUserCount = schemeUserCount;
	}

	public int getSchemeDuration() {
		return schemeDuration;
	}

	public void setSchemeDuration(int schemeDuration) {
		this.schemeDuration = schemeDuration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	public UserSchemeCountDto() {
		super();
	}

}
