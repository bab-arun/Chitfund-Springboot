package com.bb.chitfund.Dto;

import java.io.Serializable;
import java.util.List;

public class AssignSchemeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String userId;
	private String schemeId;
	private List<String> userScheme;

	public List<String> getUserScheme() {
		return userScheme;
	}

	public void setUserScheme(List<String> userScheme) {
		this.userScheme = userScheme;
	}

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

	public AssignSchemeDto(int id, String userId, String schemeId) {
		super();
		this.id = id;
		this.userId = userId;
		this.schemeId = schemeId;
	}

	public AssignSchemeDto() {
		super();
	}

}
