package com.blue.ape.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.ape.dao.MemberDao;
import com.blue.ape.entity.Member;
import com.blue.ape.service.MemberService;
import com.blue.ape.util.DigestUtil;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Override
	public int addMember(Member member) {
		String salt = DigestUtil.getSalt();
		member.setSalt(salt);
		member.setPassword(DigestUtil.sha256Digest(member.getPassword() + salt));
		member.writeDateOnCreate(member);
		return memberDao.addMember(member);
	}

	@Override
	public boolean loginMember(Member member) {
		String password = member.getPassword();
		member = memberDao.getMemberByUsername(member.getUsername());
		String encodePwd = DigestUtil.sha256Digest(password + member.getSalt());
		if (encodePwd.equals(member.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistMember(Member member) {
		Member mem = memberDao.getMemberByUsername(member.getUsername());
		if (null != mem && null != mem.getId()) {
			return false;
		}
		return true;
	}

}
