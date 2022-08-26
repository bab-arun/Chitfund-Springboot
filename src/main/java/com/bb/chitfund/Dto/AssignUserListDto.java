package com.bb.chitfund.Dto;

import java.util.List;

public class AssignUserListDto {

	private List<String> userCodeList;
	private String schemeName;

	public List<String> getUserCodeList() {
		return userCodeList;
	}

	public void setUserCodeList(List<String> userCodeList) {
		this.userCodeList = userCodeList;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

}
