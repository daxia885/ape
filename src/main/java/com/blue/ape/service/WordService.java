package com.blue.ape.service;

public interface WordService {
	/**
	 * 按分页查询词语列表
	 * @return
	 */
	Object pageWordList(int pageNumber, int pageSize);
	
	byte[] saveEnglishVoice(long id, String english);

}
