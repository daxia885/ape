package com.blue.ape.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConstantsUtils {
	
	public static String ACCESS_TOKEN;
	public static Date EXPIRES_DATE;
	
	/**
	 * 分页时，每页查询的数量
	 */
	public static final int PAGE_SIZE_DEFAULT = 20;	
	/**
	 * 所有入参的校验的编码都以001开头
	 */
	public static final String SUCCESS_CODE = "00000";			//处理成功
	public static final String PAGE_NUMBER_IS_NULL = "00101";	//当前页码为空
	public static final String USERNAME_IS_NULL = "00103";		//用户名为空
	public static final String PASSWORD_IS_NULL = "00104";		//密码为空
	
	public static final Map<String, String> RETURN_ERROR_MAP = new HashMap<>();
	static {
		RETURN_ERROR_MAP.put(SUCCESS_CODE, "处理成功！");
		RETURN_ERROR_MAP.put(PAGE_NUMBER_IS_NULL, "当前页码不可为空！");
		RETURN_ERROR_MAP.put(USERNAME_IS_NULL, "用户名不可为空！");
		RETURN_ERROR_MAP.put(PASSWORD_IS_NULL, "密码不可为空！");
	}
}
