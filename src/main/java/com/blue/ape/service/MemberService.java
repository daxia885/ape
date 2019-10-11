package com.blue.ape.service;

import com.blue.ape.entity.Member;

public interface MemberService {
	
	int addMember(Member member);

	boolean loginMember(Member member);
	
	boolean isExistMember(Member member);

}
