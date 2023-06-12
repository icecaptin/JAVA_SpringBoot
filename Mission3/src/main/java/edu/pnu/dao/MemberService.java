package edu.pnu.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	private MemberInterface memberDao;
//	private MemberInterface memberDao_List;
	
	public MemberService(MemberInterface memberDao) {
		this.memberDao = memberDao;
//	    this.memberDao_List = memberDao_List;
	}
	
	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
//		return memberDao_List.getMember(id);
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
//		return memberDao_List.getMembers();
	}
	
	public MemberVO addMember(MemberVO member) {
		return memberDao.addMember(member);
//		return memberDao_List.addMember(member);
	}
	
	public MemberVO updateMember(MemberVO member) {
		return memberDao.updateMember(member);
//		return memberDao_List.updateMember(member);
	}
	
	public int deleteMember(Integer id) {
		return memberDao.deleteMember(id);
//		return memberDao_List.getMember(id);
	}
}
