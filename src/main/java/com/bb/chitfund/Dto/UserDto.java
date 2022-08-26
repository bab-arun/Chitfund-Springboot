package com.bb.chitfund.Dto;

import java.io.Serializable;

/**
 * 
 * @author user
 *
 */
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String userName;
	private String userCode;
	private String password;
	private long mobile;
	private String email;
	private String address;
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDto(int id, String userName, String userCode, String password, long mobile, String email, String address,
			String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.userCode = userCode;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.role = role;
	}

	public UserDto() {
		super();
	}
	
	

}
