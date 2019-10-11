package com.blue.ape.entity;

/**
 * 用户
 * @author daxia
 *
 */
public class Member extends BasicEntity {
	
	private String username;		//用户名
	private String password;		//密码
	private String salt;			//盐
	private String phone;		//手机号
	private String realName;		//真实姓名
	private String idCard;		//身份证号
	
	private boolean isRemember = false;	//记住我
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public boolean isRemember() {
		return isRemember;
	}
	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}
	
}
