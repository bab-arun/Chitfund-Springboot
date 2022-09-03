package com.bb.chitfund.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assign_scheme")
public class AssignScheme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user; // for join table

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheme_id")
	private Scheme scheme; // for join table

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

	public AssignScheme() {
		super();

	}

	public AssignScheme(int id, User user, Scheme scheme) {
		super();
		this.id = id;
		this.user = user;
		this.scheme = scheme;
	}

}
