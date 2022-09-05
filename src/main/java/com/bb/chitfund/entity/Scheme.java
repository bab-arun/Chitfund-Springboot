package com.bb.chitfund.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "scheme")
public class Scheme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "scheme_name")
	private String schemeName;

	@Column(name = "scheme_amount")
	private long schemeAmount;

	@Column(name = "number_of_user")
	private long numberOfUser;

	@Column(name = "pay_amount")
	private long payAmount;

	@Column(name = "scheme_duration")
	private int schemeDuration;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheme", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<AssignScheme> assignScheme;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheme", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Payment> payment;

	private long collectedSchemeAmount;

	public long getCollectedSchemeAmount() {
		return collectedSchemeAmount;
	}

	public void setCollectedSchemeAmount(long collectedSchemeAmount) {
		this.collectedSchemeAmount = collectedSchemeAmount;
	}

	public List<AssignScheme> getAssignScheme() {
		return assignScheme;
	}

	public void setAssignScheme(List<AssignScheme> assignScheme) {
		this.assignScheme = assignScheme;
	}

	public Scheme() {
		super();
	}

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

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
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

	

}
