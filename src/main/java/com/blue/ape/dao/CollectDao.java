package com.blue.ape.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.blue.ape.entity.Collect;

public interface CollectDao {

	@Insert("insert into collect (member_id, word_id, create_time) "
			+ "values (#{member.id}, #{word.id}, #{createTime})")
	int addCollect(Collect collect);
	
	@Delete("delete from collect where member_id = #{memberId} and word_id = #{wordId}")
	int deleteCollect(@Param("memberId") long memberId, @Param("wordId") long wordId);
}
