package com.bb.chitfund.dto;

public class UserMonthlyPendingPaymentDto {

	private String status;
	private String installmentDate;
	private String paidDate;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstallmentDate() {
		return installmentDate;
	}

	public void setInstallmentDate(String installmentDate) {
		this.installmentDate = installmentDate;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	

	public UserMonthlyPendingPaymentDto() {
		super();
	}

}
