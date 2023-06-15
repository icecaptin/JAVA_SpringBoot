package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@SpringBootTest
class Mission2ApplicationTests {

	@Autowired
	private MemberService memberService;

	@Test
	void contextLoads() {
		// 회원 정보 가져오기 테스트
		Member member = memberService.getMember(1L);
		System.out.println(member);
	}

}
