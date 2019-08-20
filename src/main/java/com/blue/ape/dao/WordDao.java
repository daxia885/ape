package com.blue.ape.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface WordDao {
	
	/**
	 * 按分页查询词语列表
	 * @return
	 */
	@Select("select english, chinese, voice_path from word order by english limit #{startNum}, #{pageSize}")
	@Results({
		@Result(property="voicePath", column="voice_path")
	})
	List<Map<String, Object>> pageWordList(@Param("startNum") int startNum, @Param("pageSize") int pageSize);

}
