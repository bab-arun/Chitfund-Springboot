package com.bb.chitfund.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user; // for join table

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "scheme_id")
	private Scheme scheme; // for join table

	@Column(name = "installment_date")
	private Date installmentDate;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "paid_date")
	private Date paidDate;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "status")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
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

	public void setPaidDate(java.util.Date paidDate2) {
		this.paidDate = paidDate2;
	}

	public Payment() {
		super();
	}

	public Date getInstallmentDate() {
		return installmentDate;
	}

	public void setInstallmentDate(Date installmentDate) {
		this.installmentDate = installmentDate;
	}

	public Payment(int id, User user, Scheme scheme, Date installmentDate, Date paidDate, String paymentType,
			String status) {
		super();
		this.id = id;
		this.user = user;
		this.scheme = scheme;
		this.installmentDate = installmentDate;
		this.paidDate = paidDate;
		this.paymentType = paymentType;
		this.status = status;
	}

}
