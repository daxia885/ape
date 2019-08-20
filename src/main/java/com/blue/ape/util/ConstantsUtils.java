package com.blue.ape.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantsUtils {
	/**
	 * 分页时，每页查询的数量
	 */
	public static final int PAGE_SIZE_DEFAULT = 20;	
	/**
	 * 所有入参的校验的编码都以001开头
	 */
	public static final String PAGE_NUMBER_IS_NULL = "00101";	//当前页码为空

	public static final Map<String, String> RETURN_ERROR_MAP = new HashMap<>();
	static {
		RETURN_ERROR_MAP.put(PAGE_NUMBER_IS_NULL, "当前页码不可为空！");
	}
}
