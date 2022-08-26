package com.bb.chitfund.Dto;

import java.io.Serializable;
//import java.sql.Date;
import java.sql.Date;

public class SchemeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String schemeName;
	private long schemeAmount;
	private long numberOfUser;
	private long payAmount;
	private int schemeDuration;
	private Date startDate;
	private Date endDate;
	private long collectedSchemeAmount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}

	public int getSchemeDuration() {
		return schemeDuration;
	}

	public void setSchemeDuration(int schemeDuration) {
		this.schemeDuration = schemeDuration;
	}

	public long getCollectedSchemeAmount() {
		return collectedSchemeAmount;
	}

	public void setCollectedSchemeAmount(long collectedSchemeAmount) {
		this.collectedSchemeAmount = collectedSchemeAmount;
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

	public SchemeDto(int id, String schemeName, long schemeAmount, long numberOfUser, long payAmount,
			int schemeDuration, Date startDate, Date endDate, long collectedSchemeAmount) {
		super();
		this.id = id;
		this.schemeName = schemeName;
		this.schemeAmount = schemeAmount;
		this.numberOfUser = numberOfUser;
		this.payAmount = payAmount;
		this.schemeDuration = schemeDuration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.collectedSchemeAmount = collectedSchemeAmount;
	}

	public SchemeDto() {
		super();
	}

}
