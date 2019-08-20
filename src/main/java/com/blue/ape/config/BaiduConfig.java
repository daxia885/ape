package com.blue.ape.config;

import org.springframework.beans.factory.annotation.Value;

public class BaiduConfig {
	
	@Value("${baidu.token.apikey}")
	private String apiKey;
	@Value("${baidu.token.secretkey}")
	private String secretKey;
	@Value("${baidu.token.url}")
	private String tokenUrl;		//获取token接口地址
	@Value("${baidu.voicesynthesis.url}")
	private String voiceSynthesisUrl;	//语音合成接口地址
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getTokenUrl() {
		return tokenUrl;
	}
	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}
	public String getVoiceSynthesisUrl() {
		return voiceSynthesisUrl;
	}
	public void setVoiceSynthesisUrl(String voiceSynthesisUrl) {
		this.voiceSynthesisUrl = voiceSynthesisUrl;
	}

}
