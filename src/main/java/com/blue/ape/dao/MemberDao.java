package com.blue.ape.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.blue.ape.entity.Member;

public interface MemberDao {
	
	@Insert("insert into member (username, password, salt, phone, real_name, id_card, status, create_time, update_time, remark)"
			+ " values (#{username}, #{password}, #{salt}, #{phone}, #{realName}, #{idCard}, #{status}, #{createTime}, #{updateTime}, #{remark})")
	int addMember(Member member);
	
	int updateMemberPassword(@Param("id") long id, @Param("password") String password);

	@Select("select * from member where status = 0 and username = #{0}")
	@Results(id="member", value= {
			@Result(column="real_name", property="realName"),
			@Result(column="id_card", property="idCard"),
			@Result(column="create_time", property="createTime"),
			@Result(column="update_time", property="updateTime")
	})
	Member getMemberByUsername(String username);

}
