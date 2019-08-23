package com.blue.ape.config;

import java.util.Iterator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.blue.ape.util.ConstantsUtils;

/**
 * 切面类，用于封装接口返回内容
 * @author daxia
 *
 */
@Aspect
@Component
public class ReturnAspectAop extends ConstantsUtils {
	
	@Pointcut("execution(public * com.blue.ape.web.*.page*(..))")
	public void returnContent() {
		
	}

	@Around("returnContent()")
	public Object around(ProceedingJoinPoint pjp) {
		Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
		try {
			Object result = pjp.proceed();
			if (null == result) {
				return new ResultInfo(result, "处理成功");
			}
			//判断是否有错误码
			Iterator<String> iter = RETURN_ERROR_MAP.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				if (result.equals(key)) {
					return new ResultInfo(key, RETURN_ERROR_MAP.get(key), "处理失败");
				}
			}
			return new ResultInfo(result, "处理成功");
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			return new ResultInfo("0021", e.getMessage(), "发生了内部错误");
		}
	}
	
	public class ResultInfo {
		private String messageCode = "0000";		//返回码
		private Object content;		//返回数据
		private Object info;			//提示信息
		
		public String getMessageCode() {
			return messageCode;
		}
		public void setMessageCode(String messageCode) {
			this.messageCode = messageCode;
		}
		public Object getContent() {
			return content;
		}
		public void setContent(Object content) {
			this.content = content;
		}
		public Object getInfo() {
			return info;
		}
		public void setInfo(Object info) {
			this.info = info;
		}
		
		public ResultInfo() {
		}
		
		public ResultInfo(Object content, Object info) {
			super();
			this.content = content;
			this.info = info;
		}
		
		public ResultInfo(String messageCode, Object content, Object info) {
			super();
			this.messageCode = messageCode;
			this.content = content;
			this.info = info;
		}
	}
}
