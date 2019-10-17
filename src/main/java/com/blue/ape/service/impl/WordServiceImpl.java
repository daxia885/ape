package com.blue.ape.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.ape.dao.WordDao;
import com.blue.ape.service.WordService;
import com.blue.ape.util.BaiduUtil;
import com.blue.ape.util.ConstantsUtils;

@Service("wordService")
public class WordServiceImpl extends ConstantsUtils implements WordService {

	@Autowired
	private WordDao wordDao;
	@Autowired
	private BaiduUtil baiduUtil;
	
	@Override
	public Object pageWordList(int pageNumber, int pageSize) {
		if (0 == pageNumber) {
			return PAGE_NUMBER_IS_NULL;
		}
		if (0 == pageSize) {
			pageSize = PAGE_SIZE_DEFAULT;
		}
		int startNum = (pageNumber - 1) * pageSize;
		List<Map<String, Object>> wordList = wordDao.pageWordList(startNum, pageSize);
		return wordList;
	}

	@Override
	public byte[] saveEnglishVoice(long id, String english) {
		byte[] voice = baiduUtil.getEnglishVoice(english);
		wordDao.updateWordVoice(id, voice);
		return voice;
	}

	@Override
	public Object pageWordListFromProcess(int pageNumber, int pageSize, int memberId) {
		if (0 == pageNumber) {
			return PAGE_NUMBER_IS_NULL;
		}
		if (0 == pageSize) {
			pageSize = PAGE_SIZE_DEFAULT;
		}
		int startNum = (pageNumber - 1) * pageSize;
		List<Map<String, Object>> wordList = wordDao.pageWordListFromProcess(startNum, pageSize, memberId);
		return wordList;
	}

}
