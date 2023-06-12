package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

public class MemberSerivce {
	private MemberDao memberDao;

	public MemberSerivce(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Member getMember(Long id) {
		return memberDao.getMember(id);
	}

	public List<Member> getMembers() {
		return memberDao.getMembers();
	}

	public void insertMember(Member member) {
		memberDao.insertMember(member);
	}
}
