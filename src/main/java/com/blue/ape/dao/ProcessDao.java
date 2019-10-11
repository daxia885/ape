package com.blue.ape.dao;
import org.apache.ibatis.annotations.Insert;

import com.blue.ape.entity.Process;

public interface ProcessDao {

	@Insert("insert into process (member_id, word_id, level, create_time)"
			+ " values (#{member.id}, #{word.id}, #{level}, #{createTime})")
	int addProcess(Process process);
}
