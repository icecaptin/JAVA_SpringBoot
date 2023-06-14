package edu.pnu.dao;

import java.util.List;

import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberInterface memberInterface;
	private List<MemberVO> list;

	public MemberService() { // 이 둘을 주석처리로 왔다리 갔다리 할꾸얌
		memberInterface = new MemberDaoH2Impl();

//		memberInterface = new MemberDaoListImpl();		
	}

	public List<MemberVO> getMembers() {
		
		return memberInterface.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberInterface.getMember(id);
	}

	public MemberVO addMember(MemberVO member) {

		return memberInterface.addMember(member);
	}

	public MemberVO updateMember(MemberVO member) {

		return memberInterface.updateMember(member);
	}

	public int deleteMember(Integer id) {

		return memberInterface.deleteMember(id);
	}

}
