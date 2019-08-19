package com.blue.ape.entity;
/**
 * 单词
 * @author daxia
 *
 */
public class Word extends BasicEntity {
	
	private String english;	//英文
	private String chinese;	//中文
	private String voicePath;	//语音路径
	
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
	
}
