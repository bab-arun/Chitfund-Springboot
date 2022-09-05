package com.bb.chitfund.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class SchemePendingPaymentDto {

	private int id;
	private String schemeName;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date startDate;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date endDate;
	private int schemeDuration;
	private int numberOfUser;
	private int pendingPaymentCount;

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
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

	public int getSchemeDuration() {
		return schemeDuration;
	}

	public void setSchemeDuration(int schemeDuration) {
		this.schemeDuration = schemeDuration;
	}

	public int getNumberOfUser() {
		return numberOfUser;
	}

	public void setNumberOfUser(int numberOfUser) {
		this.numberOfUser = numberOfUser;
	}

	public int getPendingPaymentCount() {
		return pendingPaymentCount;
	}

	public void setPendingPaymentCount(int pendingPaymentCount) {
		this.pendingPaymentCount = pendingPaymentCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public SchemePendingPaymentDto() {
		super();
	}

}
