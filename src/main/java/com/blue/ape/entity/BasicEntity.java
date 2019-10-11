package com.blue.ape.entity;

import java.util.Date;

public class BasicEntity {
	
	private Long id;
	private Integer status = 0;
	private Date createTime;
	private Date updateTime;
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public BasicEntity writeDateOnCreate(BasicEntity basic) {
		Date now = new Date();
		basic.setCreateTime(now);
		basic.setUpdateTime(now);
		return basic;
	}
	
	public BasicEntity writeDateOnUpdate(BasicEntity basic) {
		basic.setUpdateTime(new Date());
		return basic;
	}

}
