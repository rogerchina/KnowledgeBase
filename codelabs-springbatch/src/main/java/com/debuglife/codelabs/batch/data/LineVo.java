package com.debuglife.codelabs.batch.data;

/**
 * <b>文本行逻辑对象</b><br>
 */
public class LineVo {

	/** 行号 */
	private int id;
	/** 名 */
	private String givenName;
	/** 姓 */
	private String familyName;
	/** 全名 */
	private String fullName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
