package com.blue.ape.entity;
/**
 * 单词
 * @author daxia
 *
 */
public class Word extends BasicEntity {
	
	private String category;	//类别 A B C
	private String english;	//英文
	private String chinese;	//中文
	private String voicePath;	//语音路径，初步考虑使用百度的语音合成API
	private String from;		//来源
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public String getVoicePath() {
		return voicePath;
	}
	public void setVoicePath(String voicePath) {
		this.voicePath = voicePath;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
}
