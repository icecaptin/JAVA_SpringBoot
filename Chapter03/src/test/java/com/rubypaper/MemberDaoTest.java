package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

@SpringBootTest
public class MemberDaoTest {
	@Test
	public void insertMemberTest() {
		MemberDao memberDao = new MemberDao();

		for (int i = 1; i <= 10; i++) {
			memberDao.insertMember(Member.builder().name("name" + i).age(20 + i).nickname("nickname" + 1).build());

		}
	}
	@Test
	public void selectAllMemberTest() {
		MemberDao memberDao = new MemberDao();
		
		List<Member> list = memberDao.getMembers();
		for (Member m : list) {
			System.out.println(m);
		}
	}
}
