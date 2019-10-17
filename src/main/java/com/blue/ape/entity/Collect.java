package com.blue.ape.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 * @author daxia
 *
 */
public class Collect implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Member member;	//收藏者
	private Word word;		//收藏的英文
	private Date createTime;
	private boolean isCollect;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Word getWord() {
		return word;
	}
	public void setWord(Word word) {
		this.word = word;
	}
	public boolean isCollect() {
		return isCollect;
	}
	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
	
}
