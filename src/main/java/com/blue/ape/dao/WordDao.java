package com.blue.ape.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WordDao {
	
	/**
	 * 按分页查询词语列表
	 * @return
	 */
	@Select("select id, english, chinese, voice, voice_path from word where status=0 order by english limit #{startNum}, #{pageSize}")
	@Results({
		@Result(property="voicePath", column="voice_path")
	})
	List<Map<String, Object>> pageWordList(@Param("startNum") int startNum, @Param("pageSize") int pageSize);

	@Update("update word set voice = #{voice} where id = #{id}")
	void updateWordVoice(@Param("id") long id, @Param("voice") byte[] voice);

	@Select("select id, english, chinese, voice, voice_path from word "
			+ "where id > (select max(word_id) from process where member_id=#{memberId}) and status=0 "
			+ "order by english limit #{startNum}, #{pageSize}")
	@Results({
		@Result(property="voicePath", column="voice_path")
	})
	List<Map<String, Object>> pageWordListFromProcess(@Param("startNum") int startNum, @Param("pageSize") int pageSize, @Param("memberId") int memberId);
}
