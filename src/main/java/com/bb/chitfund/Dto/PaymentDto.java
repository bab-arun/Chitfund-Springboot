package com.bb.chitfund.Dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class PaymentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String userId;
	private String userName;
	private String schemeId;
	private long schemeAmount;
	private long installmentAmount;
	private String nextInstallmentDate;
	private Date paidDate;
	private String paidAmountDate;
	private String paymentType;
	private String status;
	private String paidDatepay;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date installmentDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public long getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(long installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public Date getInstallmentDate() {
		return installmentDate;
	}

	public void setInstallmentDate(Date installmentDate) {
		this.installmentDate = installmentDate;
	}

	public PaymentDto() {
		super();
	}

	public long getSchemeAmount() {
		return schemeAmount;
	}

	public void setSchemeAmount(long schemeAmount) {
		this.schemeAmount = schemeAmount;
	}

	public String getNextInstallmentDate() {
		return nextInstallmentDate;
	}

	public void setNextInstallmentDate(String nextInstallmentDate) {
		this.nextInstallmentDate = nextInstallmentDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPaidAmountDate() {
		return paidAmountDate;
	}

	public void setPaidAmountDate(String paidAmountDate) {
		this.paidAmountDate = paidAmountDate;
	}

	public String getPaidDatepay() {
		return paidDatepay;
	}

	public void setPaidDatepay(String paidDatepay) {
		this.paidDatepay = paidDatepay;
	}

	public PaymentDto(int id, String userId, String userName, String schemeId, long schemeAmount,
			long installmentAmount, String nextInstallmentDate, Date paidDate, String paidAmountDate,
			String paymentType, String status, String paidDatepay, Date installmentDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.schemeId = schemeId;
		this.schemeAmount = schemeAmount;
		this.installmentAmount = installmentAmount;
		this.nextInstallmentDate = nextInstallmentDate;
		this.paidDate = paidDate;
		this.paidAmountDate = paidAmountDate;
		this.paymentType = paymentType;
		this.status = status;
		this.paidDatepay = paidDatepay;
		this.installmentDate = installmentDate;
	}

}