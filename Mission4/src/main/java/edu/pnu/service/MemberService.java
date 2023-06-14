package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberInterface memberDAO;
	private LogDao logDAO;

	public MemberService() {
		// List
//		memberDAO = new MemberDAOListImpl();

		// H2
		memberDAO = new MemberDaoH2Impl();

		logDAO = new LogDao();
	}

	public List<MemberVO> getMembers() {
		List<MemberVO> members = memberDAO.getMembers();
		String sqlstring = "SELECT * FROM members"; // 수정: 예시로 임의의 SQL문 지정
		boolean success = !members.isEmpty(); // 수정: 회원 정보가 비어있지 않으면 성공으로 간주
		logDAO.addLog("GET", sqlstring, success);
		return members;
	}

	public MemberVO getMember(int id) {
		MemberVO member = memberDAO.getMember(id);
		String sqlstring = "SELECT * FROM members WHERE id = " + id; // 수정: 예시로 임의의 SQL문 지정
		boolean success = member != null; // 수정: 회원 정보가 null이 아니면 성공으로 간주
		logDAO.addLog("GET", sqlstring, success);
		return member;
	}

	public MemberVO addMember(MemberVO member) {
		MemberVO addedMember = memberDAO.addMember(member);
		String sqlstring = "INSERT INTO members (id, name) VALUES (" + member.getId() + ", '" + member.getName() + "')"; // 수정: 예시로 임의의 SQL문 지정
		boolean success = addedMember != null; // 수정: 회원 추가가 성공했을 때만 성공으로 간주
		logDAO.addLog("POST", sqlstring, success);
		return addedMember;
	}

	public MemberVO updateMember(MemberVO member) {
		MemberVO updatedMember = memberDAO.updateMember(member);
		String sqlstring = "UPDATE members SET name = '" + member.getName() + "' WHERE id = " + member.getId(); // 수정: 예시로 임의의 SQL문 지정
		boolean success = updatedMember != null; // 수정: 회원 정보 업데이트가 성공했을 때만 성공으로 간주
		logDAO.addLog("PUT", sqlstring, success);
		return updatedMember;
	}

	public int deleteMember(Integer id) {
		int deletedCount = memberDAO.deleteMember(id);
		String sqlstring = "DELETE FROM members WHERE id = " + id; // 수정: 예시로 임의의 SQL문 지정
		boolean success = deletedCount > 0; // 수정: 회원 정보 삭제가 성공했을 때만 성공으로 간주
		logDAO.addLog("DELETE", sqlstring, success);
		return deletedCount;
	}

}
