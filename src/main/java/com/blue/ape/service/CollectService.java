package com.blue.ape.service;

import com.blue.ape.entity.Collect;

public interface CollectService {
	
	int addCollect(Collect collect);
	
	int deleteCollect(long memberId, long wordId);

}
