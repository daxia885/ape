package com.blue.ape.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.blue.ape.config.BaiduConfig;

@Component
public class BaiduUtil extends ConstantsUtils {
	
	@Autowired
	private BaiduConfig baiduConfig;
	@Autowired
	private RestTemplate restTemplate;
	
	public void getAccessToken() {
		String url = baiduConfig.getTokenUrl().trim();
		String apiKey = baiduConfig.getApiKey().trim();
		String secretKey = baiduConfig.getSecretKey().trim();
		url += "?grant_type=client_credentials&client_id="+ apiKey +"&client_secret="+ secretKey;
		JSONObject tokenJson = restTemplate.getForEntity(url, JSONObject.class).getBody();
		ACCESS_TOKEN = tokenJson.getString("access_token");
		long mills = new Date().getTime() + tokenJson.getLongValue("expires_in")*1000;
		EXPIRES_DATE = new Date(mills);
	}
	
	public byte[] getEnglishVoice(String english) {
		if (StringUtils.isEmpty(english)) {
			return null;
		}
		if (null == EXPIRES_DATE || new Date().after(EXPIRES_DATE)) {
			getAccessToken();
		}
		String url = baiduConfig.getVoiceSynthesisUrl();
		url += "?lan=zh&cuid=8c:85:90:55:23:84&ctp=1&tok="+ ACCESS_TOKEN +"&tex="+ english;
		byte[] audio = restTemplate.getForEntity(url, byte[].class).getBody();
		return audio;
	}
	
	public void getText2Audio(HttpServletRequest request, String text) {
		if (null == EXPIRES_DATE || new Date().after(EXPIRES_DATE)) {
			getAccessToken();
		}
		String url = baiduConfig.getVoiceSynthesisUrl();
		url += "?lan=zh&cuid=8c:85:90:55:23:84&ctp=1&tok="+ ACCESS_TOKEN +"&tex="+ text;
//		Map<String, Object> param = new HashMap<>();
//		param.put("tex", text);
//		param.put("tok", ACCESS_TOKEN);
//		param.put("ctp", "1");
//		param.put("cuid", "8c:85:90:55:23:84");
//		param.put("lan", "zh");
//		Object result = restTemplate.postForEntity(url, param, Object.class).getBody();
		byte[] audio = restTemplate.getForEntity(url, byte[].class).getBody();
		String audioPath = System.getProperty("user.dir") +"/src/main/resources/audio/";
		System.out.println(audioPath);
		byteToFile(audio, audioPath + text +".mp3");
	}
	
	private void byteToFile(byte[] audio, String filePath) {
		File audioFile = new File(filePath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(audioFile);
			fos.write(audio);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
