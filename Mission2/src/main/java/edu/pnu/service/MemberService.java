package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

@Service // MemberService를 스프링의 서비스 빈으로 정의합니다.
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
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
	
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}
	
	public void deleteMember(Long id) {
		memberDao.deleteMember(id);
	}
}
