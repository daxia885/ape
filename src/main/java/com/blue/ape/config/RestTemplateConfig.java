package com.blue.ape.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
    @Value("${remote.maxTotalConnect}")
	private int maxTotalConnect; //连接池的最大连接数默认为
	@Value("${remote.maxConnectPerRoute}")
	private int maxConnectPerRoute; //单个主机的最大连接数
	@Value("${remote.connectTimeout}")
	private int connectTimeout; //连接超时默认
	@Value("${remote.readTimeout}")
	private int readTimeout; //读取超时默认
	
	
	private final static Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
	
	//创建HTTP客户端工厂
	private ClientHttpRequestFactory createFactory() {
	    if (this.maxTotalConnect <= 0) {
	      SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	      factory.setConnectTimeout(this.connectTimeout);
	      factory.setReadTimeout(this.readTimeout);
	      return factory;
	    }
	    CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(this.maxTotalConnect)
	        .setMaxConnPerRoute(this.maxConnectPerRoute).build();
	    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
	        httpClient);
	    factory.setConnectTimeout(this.connectTimeout);
	    factory.setReadTimeout(this.readTimeout);
	    return factory;
	  }
	
	
	@Bean
    public RestTemplate restTemplate(){
		logger.info("RestTemplateConfig--->restTemplate开始");
		RestTemplate restTemplate = null;
		try {
			restTemplate = new RestTemplate(this.createFactory());
		    List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

		    //重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
		    HttpMessageConverter<?> converterTarget = null;
		    for (HttpMessageConverter<?> item : converterList) {
		      if (StringHttpMessageConverter.class == item.getClass()) {
		        converterTarget = item;
		        break;
		      }
		    }
		    if (null != converterTarget) {
		      converterList.remove(converterTarget);
		    }
		    converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		    //加入FastJson转换器
		    //converterList.add(new FastJsonHttpMessageConverter4());
		    
		    //解决微信返回text/plain的解析
		    //restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
		   
		} catch (Exception e) {
			logger.error("RestTemplateConfig--->restTemplate异常:"+e.getMessage());
		}
		logger.info("RestTemplateConfig--->restTemplate结束");
		return restTemplate;
    }
}
