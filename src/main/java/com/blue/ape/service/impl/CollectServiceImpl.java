package com.blue.ape.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.ape.dao.CollectDao;
import com.blue.ape.entity.Collect;
import com.blue.ape.service.CollectService;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

	@Autowired
	private CollectDao collectDao;
	
	@Override
	public int addCollect(Collect collect) {
		if (null != collect) {
			collect.setCreateTime(new Date());
			return collectDao.addCollect(collect);
		}
		return 0;
	}

	@Override
	public int deleteCollect(Collect collect) {
		if (null != collect) {
			return collectDao.deleteCollect(collect.getMember().getId(), collect.getWord().getId());
		}
		return 0;
	}

}
