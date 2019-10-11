package com.blue.ape.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.ape.dao.ProcessDao;
import com.blue.ape.entity.Process;
import com.blue.ape.service.ProcessService;

@Service("processService")
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessDao processDao;
	
	@Override
	public int addProcess(Process process) {
		if (null != process) {
			process.setCreateTime(new Date());
			return processDao.addProcess(process);
		}
		return 0;
	}

}
