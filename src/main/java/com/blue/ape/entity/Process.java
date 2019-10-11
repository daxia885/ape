package com.blue.ape.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 学习进度
 * @author daxia
 *
 */
public class Process implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Member member;
	private Word word;
	private Integer level;	//难易程度 1-我认识，2-想不起来
	private Date createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
